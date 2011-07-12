package com.immani.mydwich

import org.hibernate.Query

class PartnershipController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def sessionFactory

    User user = session.user.merge()
    /*
    def index = {
        redirect(action: "list", params: params)
    }



    def create = {
        def partnershipInstance = new Partnership()
        partnershipInstance.properties = params
        return [partnershipInstance: partnershipInstance]
    }

    def save = {
        def partnershipInstance = new Partnership(params)
        if (partnershipInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'partnership.label', default: 'Partnership'), partnershipInstance.id])}"
            redirect(action: "show", id: partnershipInstance.id)
        }
        else {
            render(view: "create", model: [partnershipInstance: partnershipInstance])
        }
    }

    def show = {
        def partnershipInstance = Partnership.get(params.id)
        if (!partnershipInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'partnership.label', default: 'Partnership'), params.id])}"
            redirect(action: "list")
        }
        else {
            [partnershipInstance: partnershipInstance]
        }
    }

    def edit = {
        def partnershipInstance = Partnership.get(params.id)
        if (!partnershipInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'partnership.label', default: 'Partnership'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [partnershipInstance: partnershipInstance]
        }
    }

    def update = {
        def partnershipInstance = Partnership.get(params.id)
        if (partnershipInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (partnershipInstance.version > version) {

                    partnershipInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'partnership.label', default: 'Partnership')] as Object[], "Another user has updated this Partnership while you were editing")
                    render(view: "edit", model: [partnershipInstance: partnershipInstance])
                    return
                }
            }
            partnershipInstance.properties = params
            if (!partnershipInstance.hasErrors() && partnershipInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'partnership.label', default: 'Partnership'), partnershipInstance.id])}"
                redirect(action: "show", id: partnershipInstance.id)
            }
            else {
                render(view: "edit", model: [partnershipInstance: partnershipInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'partnership.label', default: 'Partnership'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def partnershipInstance = Partnership.get(params.id)
        if (partnershipInstance) {
            try {
                partnershipInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'partnership.label', default: 'Partnership'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'partnership.label', default: 'Partnership'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'partnership.label', default: 'Partnership'), params.id])}"
            redirect(action: "list")
        }
    } */


    def restSubmitPartnership = {
        Restaurant restaurant = user.restaurant;
        DeliveryAddress da= DeliveryAddress.get(params.daid)
        Partnership partnershipInstance = Partnership.requestPartnership(restaurant, da)

        if (partnershipInstance.save(flush: true)) {
            //TODO:Mail à la company + message au restaurant ....
            redirect(uri: '/')
        }
        else {
            render(view: "/partnership/request", model:[partnershipInstance: partnership])
        }
    }

    //Specific to the Company/Delivery Address

    // Request new partnerships
    def restRequestPartnership = {
        Restaurant restaurant = user.restaurant
        DeliveryAddress deliveryaddress = DeliveryAddress.get(params.daid)
        Partnership partnership = new Partnership();
        partnership.restaurant = restaurant
        partnership.deliveryAddress = deliveryaddress
        partnership.originator = "restaurant"
        render(view: "request", model:[partnershipInstance: partnership])
    }

    def daRequestPartnership = {
        Restaurant restaurant = Restaurant.get(params.restid)
        DeliveryAddress deliveryaddress = DeliveryAddress.get(params.daid)
        Partnership partnership = new Partnership();
        partnership.restaurant = restaurant
        partnership.deliveryAddress = deliveryaddress
        partnership.originator = "company"
        render(view: "request", model:[partnershipInstance: partnership])
    }

    // Save a requested partnerships
     def savePartnership = {
        Partnership partnership = new Partnership();
        partnership.deliveryAddress =  DeliveryAddress.get(params.deliveryaddressid)
        partnership.restaurant = Restaurant.get(params.restaurantid)
        partnership.comment = params.comment
        partnership.originator = params.originator
        partnership.isvalidated = false
        partnership.save()
        redirect(uri: "/partnership/daListRequestedPartnerships");
    }

    // TODO add originator company criteria
    def daListRequestedPartnerships = {
       def currentSession = sessionFactory.currentSession
       User user = session.user.merge()
       List deliveryAddresses = user.company.deliveryAddresses.asList()
       Query query = currentSession.createSQLQuery("select partnership.* from partnership where delivery_address_id in (?1) and isvalidated = false and originator='company'");
       query.setParameterList("1", deliveryAddresses);
       def partnershipInstanceList = query.addEntity(Partnership.class).list();
       render(view: "listrequested", model:[partnershipInstanceList: partnershipInstanceList, partnershipInstanceTotal: partnershipInstanceList.size()])
    }

    def daListValidatedPartnerships = {
       def currentSession = sessionFactory.currentSession
       User user = session.user.merge()
       List deliveryAddresses = user.company.deliveryAddresses.asList()
       Query query = currentSession.createSQLQuery("select partnership.* from partnership where delivery_address_id in (?1) and isvalidated = true");
       query.setParameterList("1", deliveryAddresses);
       def partnershipInstanceList = query.addEntity(Partnership.class).list();
       render(view: "listvalidated", model:[partnershipInstanceList: partnershipInstanceList, partnershipInstanceTotal: partnershipInstanceList.size()])
    }

    def daListWaitingPartnerships = {
       def currentSession = sessionFactory.currentSession
       User user = session.user.merge()
       List deliveryAddresses = user.company.deliveryAddresses.asList()
       Query query = currentSession.createSQLQuery("select partnership.* from partnership where delivery_address_id in (?1) and isvalidated = false and originator='restaurant'");
       query.setParameterList("1", deliveryAddresses);
       def partnershipInstanceList = query.addEntity(Partnership.class).list();
       render(view: "listwaiting", model:[partnershipInstanceList: partnershipInstanceList, partnershipInstanceTotal: partnershipInstanceList.size()])
    }

     def restListRequestedPartnerships = {
       def currentSession = sessionFactory.currentSession
       Restaurant restaurant = user.restaurant
       Query query = currentSession.createSQLQuery("select partnership.* from partnership where restaurant_id = (?1) and isvalidated = false and originator='restaurant'");
       query.setParameterList("1", restaurant);
       def partnershipInstanceList = query.addEntity(Partnership.class).list();
       render(view: "listrequested", model:[partnershipInstanceList: partnershipInstanceList, partnershipInstanceTotal: partnershipInstanceList.size()])
    }

    def restListValidatedPartnerships = {
       def currentSession = sessionFactory.currentSession
       Restaurant restaurant = user.restaurant
       Query query = currentSession.createSQLQuery("select partnership.* from partnership where restaurant_id = (?1) and isvalidated = true");
       query.setParameterList("1", restaurant);
       def partnershipInstanceList = query.addEntity(Partnership.class).list();
       render(view: "listvalidated", model:[partnershipInstanceList: partnershipInstanceList, partnershipInstanceTotal: partnershipInstanceList.size()])
    }

    def restListWaitingPartnerships = {
       def currentSession = sessionFactory.currentSession
       Restaurant restaurant = user.restaurant
       Query query = currentSession.createSQLQuery("select partnership.* from partnership where restaurant_id = (?1) and isvalidated = false and originator='company'");
       query.setParameterList("1", restaurant);
       def partnershipInstanceList = query.addEntity(Partnership.class).list();
       render(view: "listwaiting", model:[partnershipInstanceList: partnershipInstanceList, partnershipInstanceTotal: partnershipInstanceList.size()])
    }

    def validatePartnership = {
        Partnership.validatePartnership(Partnership.get(params.id))
        if (user.restaurant){
            redirect(uri: "/partnership/restListValidatedPartnerships");
        }else {
            redirect(uri: "/partnership/daListValidatedPartnerships");
        }
    }

    def RemovePartnership = {
        Partnership.removePartnership(Partnership.get(params.id))
    }
}
