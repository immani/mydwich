package com.immani.mydwich

class PartnershipController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    User user = session.user.merge()


    def restRequestPartnership = {
        Restaurant restaurant = user.restaurant
        DeliveryAddress deliveryaddress = DeliveryAddress.get(params.id)
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
        def deliveryAddress =  DeliveryAddress.get(params.deliveryaddressid)
        def restaurant = Restaurant.get(params.restaurantid)
        def comment = params.comment
        def originator = params.originator
        Partnership.requestPartnership(restaurant, deliveryAddress, originator, comment )

        if (user.restaurant){
            redirect(action: "restListRequestedPartnerships" )
        }else {
            redirect(action: "daListRequestedPartnerships");
        }
    }

    def daListRequestedPartnerships = {
       def partnershipInstanceList = user.company.retrieveRequestedPartnerships();
       render(view: "list", model:[partnershipInstanceList: partnershipInstanceList, partnershipInstanceTotal: partnershipInstanceList.size()])
    }

    def daListValidatedPartnerships = {
       def partnershipInstanceList = user.company.retrieveValidatedPartnerships();
       render(view: "list", model:[partnershipInstanceList: partnershipInstanceList, partnershipInstanceTotal: partnershipInstanceList.size()])
    }

    def daListWaitingPartnerships = {
       def partnershipInstanceList = user.company.retrieveWaitingPartnerships();
       render(view: "list", model:[partnershipInstanceList: partnershipInstanceList, partnershipInstanceTotal: partnershipInstanceList.size()])
    }

     def restListRequestedPartnerships = {
       def partnershipInstanceList =  user.restaurant.retrieveRequestedPartnerships();
       render(view: "list", model:[partnershipInstanceList: partnershipInstanceList, partnershipInstanceTotal: partnershipInstanceList.size()])
    }

    def restListValidatedPartnerships = {
       def partnershipInstanceList =  user.restaurant.retrieveValidatedPartnerships();
       render(view: "list", model:[partnershipInstanceList: partnershipInstanceList, partnershipInstanceTotal: partnershipInstanceList.size()])
    }

    def restListWaitingPartnerships = {
       def partnershipInstanceList = user.restaurant.retrieveWaitingPartnerships()
       render(view: "list", model:[partnershipInstanceList: partnershipInstanceList, partnershipInstanceTotal: partnershipInstanceList.size()])
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
