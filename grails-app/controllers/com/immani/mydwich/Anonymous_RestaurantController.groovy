package com.immani.mydwich

import com.immani.mydwich.DeliveryAddress
import com.immani.mydwich.Restaurant
import com.immani.mydwich.User
import grails.converters.JSON

class Anonymous_RestaurantController {
    def index = {
        render(view: "index")
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        render(view:"/restaurant/list", model: [restaurantInstanceList: Restaurant.list(params), restaurantInstanceTotal: Restaurant.count()])
    }

    def listpartnerrestaurant = {
        User user = session.user.merge()
        if (user.company){
            DeliveryAddress da = params.da ? DeliveryAddress.get(params.da) : user.defaultda
            def restaurantInstanceList = da.partnerships.restaurant
            render(view: "/restaurant/listpartners", model: [dalist: user.company.deliveryAddresses , da: da,restaurantInstanceList: restaurantInstanceList, restaurantInstanceTotal: restaurantInstanceList.size()])
        }
    }

    def getbyname = {
        //TODO: replaceall pas suffisant, prévoir espace, accent? etc...
        Restaurant restaurantInstance = Restaurant.findByName(params.name.decodeURL())
        def pictureInstanceList = restaurantInstance.pictures
        if (!restaurantInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), params.id])}"
            redirect(action: "list")
        }
        else {
            switch(params.page){
                case null:
                    render(view: "/restaurant/public", model: [restaurantInstance: restaurantInstance, pictureInstanceList: pictureInstanceList ])
                    break
                case "map":
                    render(view: "/restaurant/map", model: [restaurantInstance: restaurantInstance, pictureInstanceList: pictureInstanceList])
                    break
                case "info":
                    render(view: "/restaurant/info", model: [restaurantInstance: restaurantInstance, pictureInstanceList: pictureInstanceList])
                    break
                case "menu":
                    //   params.id = restaurantInstance.id
                    //    def productscategories = restaurantInstance.productsCategories?.sort({a,b-> a.catorder.compareTo(b.catorder)})
                    //    render(view: "/product/catalog", model: [productcategoriesInstanceList: productscategories, restaurantInstance: restaurantInstance, productcategoriesInstanceTotal: productscategories.size()])
                    redirect(controller: "anonymous_Product", action: "showrestaurantcatalog", id:restaurantInstance.id)
                    break
            }
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
            params.name = restaurantInstance.name.encodeAsURL()
            params.remove("id")
            redirect(action: "getbyname", params: params )
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
            def pictureInstanceList = restaurantInstance.pictures
            render(view: "show", model: [restaurantInstance: restaurantInstance, pictureInstanceList: pictureInstanceList ])
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
