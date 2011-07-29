package com.immani.mydwich

import grails.converters.JSON

class RestaurantController {
    def geocoderService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        render(view: "index")
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [restaurantInstanceList: Restaurant.list(params), restaurantInstanceTotal: Restaurant.count()]
    }

    def save = {
        def results = geocoderService.geocode(params.address, params.zip, params.city, params.country )
        def restaurantInstance = new Restaurant(params + results)
        if (restaurantInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), restaurantInstance.id])}"
            redirect(action: "show", id: restaurantInstance.id)
        }
        else {
            render(view: "create", model: [restaurantInstance: restaurantInstance])
        }
    }

    def show = {
        def restaurantInstance = Restaurant.get(params.id)
        def pictureInstanceList = restaurantInstance.pictures
        if (!restaurantInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), params.id])}"
            redirect(action: "list")
        }
        else {
            //TODO: One step to remove (redirect alors que le model est déjà OK
            //TODO: check encodeURL Vs replaceAll
           /* params.name = restaurantInstance.name.encodeAsURL()
            params.remove("id")
            redirect(action: "getbyname", params: params )*/
            render(view: "show", model: [restaurantInstance: restaurantInstance, pictureInstanceList: pictureInstanceList ])
        }
    }

    def edit = {
        def restaurantInstance = Restaurant.get(params.id)
        if (!restaurantInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [restaurantInstance: restaurantInstance]
        }
    }

    def update = {
        def restaurantInstance = Restaurant.get(params.id)
        if (restaurantInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (restaurantInstance.version > version) {

                    restaurantInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'restaurant.label', default: 'Restaurant')] as Object[], "Another user has updated this Restaurant while you were editing")
                    render(view: "edit", model: [restaurantInstance: restaurantInstance])
                    return
                }
            }
            def results = geocoderService.geocode(params.address, params.zip, params.city, params.country )

            restaurantInstance.properties = params + results
            if (!restaurantInstance.hasErrors() && restaurantInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), restaurantInstance.id])}"
                redirect(action: "show", id: restaurantInstance.id)
            }
            else {
                render(view: "edit", model: [restaurantInstance: restaurantInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def restaurantInstance = Restaurant.get(params.id)
        if (restaurantInstance) {
            try {
                restaurantInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Display the profile of a restaurant for the current restaurant user
     */
    def showprofilerestaurant = {
        User user = session.user.merge()
        Restaurant restaurantInstance = user.restaurant
        if (!restaurantInstance) {
            flash.message = "User doesn't belong to a Restaurant"
            render(view: "/info")
        }
        else {
            def pictureInstanceList = restaurantInstance.pictures
            render(view: "show", model: [restaurantInstance: restaurantInstance, pictureInstanceList: pictureInstanceList ])
        }
    }

    /**
     * Edit the restaurant for the current restaurant user
     */
    def editprofilerestaurant = {
        User user = session.user.merge()
        Restaurant restaurantInstance = user.restaurant
        if (!restaurantInstance) {
            flash.message = "User doesn't belong to a Restaurant"
            render(view: "/info")
        }
        else {
            render(view: "edit", model: [restaurantInstance: restaurantInstance])
        }
    }


    def listdeliveryaddressinrange = {
        User user = session.user.merge()
        Restaurant restaurant = user.restaurant
        def deliveryAddressList = user.restaurant.retrieveNearbyDeliveryAddresses();
        if(params.json){
            render deliveryAddressList as JSON
        }
        else{
            render(view: "listdeliveryaddressinrange", model: [restaurantInstance: restaurant,  deliveryAddressInstanceList: deliveryAddressList, deliveryAddressInstanceTotal: deliveryAddressList.size()])
        }
    }
}
