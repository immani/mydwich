package com.immani.mydwich
import grails.converters.*
import org.apache.shiro.SecurityUtils

class RestaurantController {
    def geocoderService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def geocode = {
        def result = geocoderService.geocode(params.address, params.zip, params.city, params.country)
        render result as JSON
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [restaurantInstanceList: Restaurant.list(params), restaurantInstanceTotal: Restaurant.count()]
    }


    def create = {
        def restaurantInstance = new Restaurant()
        restaurantInstance.properties = params
        return [restaurantInstance: restaurantInstance]
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

    def getbyname = {
        //TODO: replaceall pas suffisant, prévoir espace, accent? etc...
        Restaurant restaurantInstance = Restaurant.findByName(params.name.decodeURL())
        if (!restaurantInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), params.id])}"
            redirect(action: "list")
        }
        else {
            render(view: "show", model: [restaurantInstance: restaurantInstance])
        }
    }

    def show = {
        def restaurantInstance = Restaurant.get(params.id)
        if (!restaurantInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), params.id])}"
            redirect(action: "list")
        }
        else {
            //TODO: One step to remove (redirect alors que le model est déjà OK
            //TODO: check encodeURL Vs replaceAll
            params.name = restaurantInstance.name.encodeAsURL()
            params.remove("id")
            redirect(action: "getbyname", params: params.name )
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
     * Display the list of restaurants as JSON
     */
    def listasjson = {
        def result = Restaurant.list()
        render result as JSON
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
            render(view: "show", model: [restaurantInstance: restaurantInstance])
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

    def search = {
        def q = params.q ?: null
        def searchResults
        if(q) {
            if(q.toString().length()>2){
                searchResults = [
                        restaurantResults: trySearch { Restaurant.search(q, [max:10]) },
                        //  artistResults: trySearch { Artist.search(q, [max:10]) },
                        //  songResults: trySearch { Song.search(q, [max:10]) },
                        q: q.encodeAsHTML()
                ]
            }
        }
        render(template:"searchResults", model: searchResults)
    }
    def trySearch(Closure callable) {
        try {
            return callable.call()
        } catch(Exception e) {
            log.debug "Search Error: ${e.message}", e
            return []
        }
    }
}
