package com.immani.mydwich

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authz.AuthorizationException

class BasketController {
    //TODO: Internationalization
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def basketService;

    /**
     * @return returns the current user
     */
    private User currentuser(){
        return session.user.merge()
    }

    /**
     * Checks if a companyuser has to right to perform an action toward a basket; ex: show, edit, update
     * @param basketInstance
     * @return
     */
    private boolean hasrights(Basket basketInstance){
        if(currentuser() == basketInstance.user || ((currentuser().company == basketInstance.user.company) && SecurityUtils.subject.hasRole("companyadmin"))){
            return true
        }
        throw new AuthorizationException("User is not allowed to perform that operation")
    }

    /**
     * This action is called from the catalog via an AJAX call and displays a dialogbox where the user selects the options to be added (salade, cornichons, sauces...)
     */
    def selectproductpptions = {
        Product selproduct = Product.get(params.id)
        def productCategories = selproduct.productCategories
        def productOptionCategories = productCategories.prodOptionCategories.flatten().unique().sort()
        render(view: "addproduct", model: [productOptionCategories: productOptionCategories, selproduct: selproduct])
    }

    /**
     * called via AJAX to display a summary of the basket in a side pane
     */
    def renderbasketajax = {
        Basket basketInstance = session.basket
        def basketLines = basketInstance?.basketLines
        render(view: "basket_ajax", model: [basketInstance: basketInstance, basketLines: basketLines])
    }

    // TODO: Managing errors retrieved by basket service (null pointers are managed by database constraints)
    /**
     * This method receives (via AJAX) the infos for a product to be added to the basket (Qty...) and the list of options and sends back the answer to the browser "productadded"
     */
    def addproduct = {

        log.debug 'addproduct method started'
        User user = currentuser() //session.user
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

    /**
     * retieves the current basket of the logged user and displays it
     */
    def showcurrentbasket = {
        Basket basketInstance = session.basket
        def basketLines = basketInstance?.basketLines
        [basketInstance: basketInstance, basketLines: basketLines]
    }

    /**
     * Checkout flow for validating the basket
     */
    def checkoutFlow = {
        showcurrentbasket{
            on('next').to 'finalize'
            on('back').to 'backtorestaurantcatalog'

        }

        backtorestaurantcatalog {
            action {
                flash.message = "back to restaurant"
                redirect(controller:"product", action:"showrestaurantcatalog", params:[message:flash.message])
            }
        }
    }



    /**
     * Displays the list of baskets for the current user
     */
    def listbyuser = {
        User user = currentuser()
        def basketlist = user.baskets
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [basketInstanceList: basketlist(params), basketInstanceTotal: basketlist.size()]
    }

    /**
     * Displays the list of baskets for the current company
     */
    def listbycompany = {
        //TODO: Make sure user has role admin
        Company company = currentuser().company
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
