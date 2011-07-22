package com.immani.mydwich

class BasketController {
    //TODO: Internationalization
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]



    /**
     * Displays the list of baskets for the current company
     */
    def listbycompany = {
        //TODO: Make sure user has role com.immani.mydwich.admin
        Company company = session.user.merge().company
        def basketlist = company.users.baskets
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [basketInstanceList: basketlist(params), basketInstanceTotal: basketlist.size()]
    }

    /**
     * Displays a basket . We check before user has rights...
     */
    def show = {
        Basket basketInstance = Basket.get(params.id)
        if (!basketInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'basket.label', default: 'Basket'), params.id])}"
            redirect(action: "list")
        }
        else {
            if (hasrights(basketInstance)){
                    [basketInstance: basketInstance]
            }
        }
    }

    /**
     * saves a basket
     */
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

    /**
     * edit a basket
     */
    def edit = {
        //TODO: when can we edit
        def basketInstance = Basket.get(params.id)
        if (!basketInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'basket.label', default: 'Basket'), params.id])}"
            redirect(action: "list")
        }
        else {
            if (hasrights(basketInstance)){
                return [basketInstance: basketInstance]
            }
        }
    }

    /**
     * update a basket
     */
    def update = {
        //TODO: when can we update
        def basketInstance = Basket.get(params.id)
        if (basketInstance) {
            if (hasrights(basketInstance)){
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
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'basket.label', default: 'Basket'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Deletes a basket
     */
    def delete = {
        //TODO: when can we delete?
        Basket basketInstance = Basket.get(params.id)
        if (hasrights(basketInstance)){
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
        render(view: "/user_company/listorders", model: [basketInstanceList: criteria.list(query), basketInstanceTotal: criteria.count(query)])
    }

    def listrestaurantorders = {
        def user = session.user.merge()
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def basketlist = Basket.findAllByRestaurant(user.restaurant, params)
        render(view: "/user_company/listorders", model: [basketInstanceList: basketlist, basketInstanceTotal: basketlist.size()])
    }

    def listrestauranttodayorders = {
        def user = session.user.merge()
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def basketlist = Basket.findAllByRestaurantAndOrderDate(user.restaurant,new Date(), params)
        render(view: "/user_company/listorders", model: [basketInstanceList: basketlist, basketInstanceTotal: basketlist.size()])
    }
}
