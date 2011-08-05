package com.immani.mydwich

class User_companyController {
    def index = {

    }

    def createuserpayment = {
        User currentuser = session.user.merge()
        session.userpayment = session.userpayment == null ? new Userpayment(user: currentuser): session.userpayment;
        render(view: "/userpayment/create", model: [userpaymentInstance: session.userpayment])
    }

     /**
     * List of the payments of the current user
     */
    def listuserpayment = {
        User user = session.user.merge()
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def userpaymentlist = Userpayment.findAllByUser(user, params)
        render(view: "/userpayment/list", model: [userpaymentInstanceList: userpaymentlist, userpaymentInstanceTotal: userpaymentlist.size()])
    }

    def listpartnerrestaurant = {
        User user = session.user.merge()
        if (user.company){
            DeliveryAddress da = params.da ? DeliveryAddress.get(params.da) : user.defaultda
            if (!da){da = user.company.deliveryAddresses.asList()[0]}
            session.deliveryaddress = da
            def restaurantInstanceList = da.partnerships.restaurant
            render(view: "listpartners", model: [dalist: user.company.deliveryAddresses , da: da,restaurantInstanceList: restaurantInstanceList, restaurantInstanceTotal: restaurantInstanceList.size()])
        }
    }

    /**
     * Called to display the catalog and order products
     */
    def showrestaurantcatalog = {
        Restaurant restaurantInstance = Restaurant.get(params.id)
        def productscategories
        if (!restaurantInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), params.id])}"
            redirect(action: "list")
        }
        else {
            if (params.productcategory == null){
                productscategories = restaurantInstance.productsCategories.sort({a,b-> a.catorder.compareTo(b.catorder)})
                render(view: "catalog", model: [productcategoriesInstanceList: productscategories, restaurantInstance: restaurantInstance, productcategoriesInstanceTotal: productscategories.size()])
            }
            else{
                productscategories = ProductCategory.get(params.productcategory)
                render(view: "catalog", model: [productcategoriesInstanceList: productscategories, restaurantInstance: restaurantInstance])
            }

        }
    }

    /**
     * This action is called from the catalog via an AJAX call and displays a dialogbox where the user selects the options to be added (salade, cornichons, sauces...)
     */
    def showproductajax = {
        Product selproduct = Product.get(params.id)
        def productCategories = selproduct.productCategories
        def productOptionCategories = productCategories.prodOptionCategories.flatten().unique().sort()
        def pictureInstance = selproduct.picture
        render(view: "showproductajax", model: [productOptionCategories: productOptionCategories, selproduct: selproduct, pictureInstance: pictureInstance])
    }

    /**
     * Function called via AJAX and that retrieves the list of products for a specific restaurant AND category
     */
    def showproductlist = {

        //TODO: unifier les action showproductlist et showrestaurant catalog Via if XHR Request
        Restaurant restaurantInstance = Restaurant.get(params.id)
        if (!restaurantInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), params.id])}"
            redirect(action: "list")
        }
        else {
            def productscategories
            if (params.productcategory == "all"){
                productscategories = restaurantInstance.productsCategories.sort({a,b-> a.catorder.compareTo(b.catorder)})
                render(template: "productlist", model: [productcategoriesInstanceList: productscategories, restaurantInstance: restaurantInstance, productcategoriesInstanceTotal: productscategories.size()])
            }
            else{
                productscategories = ProductCategory.get(params.productcategory)
                render(template: "productlist", model: [productcategoriesInstanceList: productscategories, restaurantInstance: restaurantInstance])
            }
        }
    }

    /**
     * This action is called from the catalog via an AJAX call and displays a dialogbox where the user selects the options to be added (salade, cornichons, sauces...)
     */
    def selectproductoptions = {
        Product selproduct = Product.get(params.id)
        def productCategories = selproduct.productCategories
        def productOptionCategories = productCategories.prodOptionCategories.flatten().unique().sort()
        render(view: "addproduct", model: [productOptionCategories: productOptionCategories, selproduct: selproduct])
    }

     def search = {
         //TODO: idéalement le search devrait utiliser comme Template _productlist au lieu de _productsearch mais pb des catégories...
        def q = params.q ?: null
        def searchResults
        if(q) {
            if(q.toString().length()>2){
                searchResults = [
                        productsResults: trySearch { Product.search(q +"*", [max:10]) },
                        q: q.encodeAsHTML()
                ]
            }
        }
        render(template:"productsearch", model: searchResults)
    }

    def trySearch(Closure callable) {
        try {
            return callable.call()
        } catch(Exception e) {
            log.debug "Search Error: ${e.message}", e
            return []
        }
    }




    // TODO: Managing errors retrieved by basket service (null pointers are managed by database constraints)
    /**
     * This method receives (via AJAX) the infos for a product to be added to the basket (Qty...) and the list of options and sends back the answer to the browser "productadded"
     */
    def addproduct = {
        User user = session.user.merge()
        Basket basket = session.basket
        Product product = Product.get(params["product"].productid)
        Integer quantity = params["product"].quantity.toInteger()
        String comment = params["product"].comment
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

        if(!basket){
            session.basket = new Basket(user:user, deliveryAddress: session.da, restaurant: product.restaurant )
        }
        session.basket.addProduct(product, quantity, options, comment)
        render("productadded")
    }


    /**
     * called via AJAX to display a summary of the basket in a side pane
     */
    def renderbasketajax = {
        Basket basketInstance = session.basket
        if (basketInstance){
            def basketLines = basketInstance?.basketLines
            render(view: "basket_ajax", model: [basketInstance: basketInstance, basketLines: basketLines])
        }
        else{
            render(view: "basket_ajax")
        }

    }

    /**
     * retieves the current basket of the logged user and displays it
     */
    def showcurrentbasket = {
        Basket basketInstance = session.basket
        if (basketInstance){
                    def basketLines = basketInstance?.basketLines
                    render(view: "showcurrentbasket" , model: [basketInstance: basketInstance, basketLines: basketLines])
        }
        else{
            flash.message = "${message(code: 'basket.current.not.exist')}"
            redirect(action: "index")
        }

    }

    def removeline = {
        Basket basketInstance = session.basket
        if (basketInstance){
            Integer m = (params.id).toInteger()
            BasketLine bl = basketInstance?.basketLines[m]
        //    basketInstance?.basketLines?.remove(bl)
            basketInstance.removeBasketLine(bl)
            redirect(action:"showcurrentbasket")
        }
    }

    def changelineqty = {
        Basket basketInstance = session.basket
        if (basketInstance){
            Integer m = (params.id).toInteger()
            Integer qty = (params.qty).toInteger()
            BasketLine bl = basketInstance?.basketLines[m]
            redirect(action:"showcurrentbasket")
        }
    }

    /**
     * Displays the list of baskets for the current user
     */
    def listorders = {
        User user = session.user.merge()
        def basketlist = user.baskets
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        render(view:"listorders", model:[basketInstanceList: basketlist, basketInstanceTotal: basketlist.size()])
    }


    def listusertodayorders = {
        def user = session.user.merge()
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def basketlist = Basket.findAllByUserAndOrderDate(user,new Date(), params)
        render(view:"listorders", model:[basketInstanceList: basketlist, basketInstanceTotal: basketlist.size()])
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
                redirect(controller: "user_company", action:"showrestaurantcatalog", params:[message:flash.message])
            }
        }
    }
}
