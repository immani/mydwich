package com.immani.mydwich.admin

import com.immani.mydwich.*
class BasketadminController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def basketService;

    def index = {
        redirect(action: "list", params: params)
    }

    // TODO: Managing errors retrieved by basket service (null pointers are managed by database constraints)
    def addproduct = {

        log.debug 'addproduct method started'
        User user = session.user
        Basket basket = session.basket
        Product product = Product.get(params["product"].productid)
        Integer quantity = params["product"].quantity[0].toInteger()

        log.debug("creating the selected options collection for product: ${params["product"].productid}")
        def options = []
        for(itemp in params["option"]) {
            if (!itemp.key.toString().startsWith("_")) {
                itemp.value.each {
                    optionval ->
                    def prodoption = ProdOption.get(optionval)
                    options.add(prodoption)
                }
            }
        }

        log.debug("calling basketService addproduct method")
        session.basket = basketService.addProduct(user,basket,product,quantity, options)

        render("productadded")
    }
    /* catch (Error e) {
        flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'basket.label', default: 'Basket'), params.id])}"
        render("productnotadded")
    }*/

    def showbasket = {
        Basket basketInstance = session.basket
        def basketLines = basketInstance?.basketLines
        [basketInstance: basketInstance, basketLines: basketLines]
    }

    def checkoutFlow = {
        showbasket{
            on('next').to 'finalize'
            on('back').to 'backtorestaurantcatalog'


           // render(view: "/basket/basket", )
        //    render(view:"/basket/basket")

        }

        backtorestaurantcatalog {
            action {
                flash.message = "back to restaurant"
                redirect(controller: "anonymous_Product", action:"showrestaurantcatalog", params:[message:flash.message])
            }
        }
    }

    def savebasket = {
        Basket basket = session.basket
        basket.save()
        if (basket.hasErrors()){
            println basket.errors
        }
        log.debug("basket saved succesfully")
        redirect(action: "list", params: params)
    }

    def selectOptions = {
        Product selproduct = Product.get(params.id)
        def productCategories = selproduct.productCategories
        def productOptionCategories = productCategories.prodOptionCategories.flatten().unique().sort()
        render(view: "addproduct", model: [productOptionCategories: productOptionCategories, selproduct: selproduct])
    }

    def renderbasketajax = {
        Basket basketInstance = session.basket
        def basketLines = basketInstance?.basketLines
        render(view: "basket_ajax", model: [basketInstance: basketInstance, basketLines: basketLines])
    }



    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [basketInstanceList: Basket.list(params), basketInstanceTotal: Basket.count()]
    }



    def show = {
        def basketInstance = Basket.get(params.id)
        if (!basketInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'basket.label', default: 'Basket'), params.id])}"
            redirect(action: "list")
        }
        else {
            [basketInstance: basketInstance]
        }
    }



    def delete = {
        def basketInstance = Basket.get(params.id)
        if (basketInstance) {
            try {
                basketInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'basket.label', default: 'Basket'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'basket.label', default: 'Basket'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'basket.label', default: 'Basket'), params.id])}"
            redirect(action: "list")
        }
    }


    def listuserorders = {
        def user = session.user.merge()
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def basketlist = Basket.findAllByUser(user, params)
        [basketInstanceList: basketlist, basketInstanceTotal: basketlist.size()]
    }

    def listusertodayorders = {
        def user = session.user.merge()
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def basketlist = Basket.findAllByUserAndOrderDate(user,new Date(), params)
        [basketInstanceList: basketlist, basketInstanceTotal: basketlist.size()]
    }

    def listcompanyorders = {
        def user = session.user.merge()
        def company = user.company
        def companyusers = company.users
        def basketlist = companyusers.baskets
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        render(view: "list", model: [basketInstanceList: basketlist, basketInstanceTotal: basketlist.size()])
    }

    def listcompanytodayorders = {
        //TODO: to be extended
        def user = session.user.merge()
        def company = user.company
        def companyusers = company.users
        Basket basketlist = companyusers.baskets
        def today = new Date()
        def queryMap = [ company: user.company, dateCreated: today ]
        def query = {
            // go through the query map
            queryMap.each { key, value ->
                // if we have a list assume a between query
                if(value instanceof List) {
                    // use the spread operator to invoke
                    between(key, *value)
                } else {
                    like(key,value)
                }
            }
        }

        def criteria = Basket.createCriteria()

        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        render(view: "list", model: [basketInstanceList: criteria.list(query), basketInstanceTotal: criteria.count(query)])
    }

    def listrestaurantorders = {
        def user = session.user.merge()
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def basketlist = Basket.findAllByRestaurant(user.restaurant, params)
        render(view: "list", model: [basketInstanceList: basketlist, basketInstanceTotal: basketlist.size()])
    }

    def listrestauranttodayorders = {
        def user = session.user.merge()
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def basketlist = Basket.findAllByRestaurantAndOrderDate(user.restaurant,new Date(), params)
        render(view: "list", model: [basketInstanceList: basketlist, basketInstanceTotal: basketlist.size()])
    }
}


/*
def create = {
    def basketInstance = new Basket()
    basketInstance.properties = params
    return [basketInstance: basketInstance]
}

def save = {
    def basketInstance = new Basket(params)
    if (basketInstance.save(flush: true)) {
        flash.message = "${message(code: 'default.created.message', args: [message(code: 'basket.label', default: 'Basket'), basketInstance.id])}"
        redirect(action: "show", id: basketInstance.id)
    }
    else {
        render(view: "create", model: [basketInstance: basketInstance])
    }
}

def edit = {
    def basketInstance = Basket.get(params.id)
    if (!basketInstance) {
        flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'basket.label', default: 'Basket'), params.id])}"
        redirect(action: "list")
    }
    else {
        return [basketInstance: basketInstance]
    }
}

def update = {
    def basketInstance = Basket.get(params.id)
    if (basketInstance) {
        if (params.version) {
            def version = params.version.toLong()
            if (basketInstance.version > version) {

                basketInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'basket.label', default: 'Basket')] as Object[], "Another user has updated this Basket while you were editing")
                render(view: "edit", model: [basketInstance: basketInstance])
                return
            }
        }
        basketInstance.properties = params
        if (!basketInstance.hasErrors() && basketInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.updated.message', args: [message(code: 'basket.label', default: 'Basket'), basketInstance.id])}"
            redirect(action: "show", id: basketInstance.id)
        }
        else {
            render(view: "edit", model: [basketInstance: basketInstance])
        }
    }
    else {
        flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'basket.label', default: 'Basket'), params.id])}"
        redirect(action: "list")
    }
}
*/