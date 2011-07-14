package com.immani.mydwich

class PublicController {

    def listrestaurants = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        render(view:"/restaurant/list", model:[restaurantInstanceList: Restaurant.list(params), restaurantInstanceTotal: Restaurant.count()])
    }
}
