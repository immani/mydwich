package com.immani.mydwich

class PartnershipController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    User user = session.user.merge()
    /*
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [partnershipInstanceList: Partnership.list(params), partnershipInstanceTotal: Partnership.count()]
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


    //Specific to the restaurant
    def restListValidatedPartnerships = {
        Restaurant restaurant = user.restaurant
        List partnershipstList = Partnership.findAllByRestaurantAndIsvalidated(restaurant,true);
        render(view: "list", model:[partnershipInstanceList: partnershipstList, partnershipInstanceTotal: partnershipstList.size()])
    }

    def restListRequestedPartnerships = {
        Restaurant restaurant = user.restaurant
        List partnershipstList = Partnership.findAllByRestaurantAndIsvalidated(restaurant,false)
        render(view: "list", model:[partnershipInstanceList: partnershipstList, partnershipInstanceTotal: partnershipstList.size()])
    }

    def restRequestPartnership = {
        Restaurant restaurant = user.restaurant
        DeliveryAddress da = DeliveryAddress.get(params.daid)
        Partnership partnership = Partnership.requestPartnership(restaurant , da)
        render(view: "requestfromrestaurant", model:[partnershipInstance: partnership])
    }

    def restSubmitPartnership = {
        Restaurant restaurant = user.restaurant;
        DeliveryAddress da= DeliveryAddress.get(params.daid)
        Partnership partnershipInstance = Partnership.requestPartnership(restaurant, da)

        if (partnershipInstance.save(flush: true)) {
            //TODO:Mail à la company + message au restaurant ....
            redirect(uri: '/')
        }
        else {
            render(view: "/partnership/requestfromrestaurant", model:[partnershipInstance: partnership])
        }
    }

    //Specific to the Company/Delivery Address
    def daListValidatedPartnerships = {
        DeliveryAddress da = DeliveryAddress.get(params.id)
        List partnershipstList = Partnership.findAllByDeliveryAddressAndIsvalidated(da,true)
        render(view: "list", model:[partnershipInstanceList: partnershipstList, partnershipInstanceTotal: partnershipstList.size()])

    }

    def daListRequestedPartnerships = {
        DeliveryAddress da = DeliveryAddress.get(params.id)
        List partnershipstList = Partnership.findAllByDeliveryAddressAndIsvalidated(da,false)
        render(view: "list", model:[partnershipInstanceList: partnershipstList, partnershipInstanceTotal: partnershipstList.size()])

    }

    def daRequestPartnership = {
        Restaurant restaurant = Restaurant.get((params.restid))
        DeliveryAddress da = DeliveryAddress.get(params.id)
        Partnership partnership = Partnership.requestPartnership(restaurant, da)
        render(view: "requestfromdeliveryaddress", model:[partnershipInstance: partnership])
    }


    //Common to the restaurant and Company/DeliveryAddress
    def ValidatePartnership = {
        Partnership.validatePartnership(Partnership.get(params.id))
    }

    def RemovePartnership = {
        Partnership.removePartnership(Partnership.get(params.id))
    }

}
