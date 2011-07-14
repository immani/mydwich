package com.immani.mydwich

class PublicController {


    def listrestaurants = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        render(view: "/restaurant/list", model:[restaurantInstanceList: Restaurant.list(params), restaurantInstanceTotal: Restaurant.count()])
    }

        /**
     * Called to display the catalog and order products
     */
    def showrestaurantcatalog = {
        Restaurant restaurantInstance = Restaurant.get(params.id)
        def productscategories
        if (!restaurantInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), params.id])}"
            redirect(controller: "anonymous_Restaurant", action: "list")
        }
        else {
            if (params.productcategory == null){
                productscategories = restaurantInstance.productsCategories.sort({a,b-> a.catorder.compareTo(b.catorder)})
                render(view: "/product/catalog", model: [productcategoriesInstanceList: productscategories, restaurantInstance: restaurantInstance, productcategoriesInstanceTotal: productscategories.size()])
            }
            else{
                productscategories = ProductCategory.get(params.productcategory)
                render(view: "/product/catalog", model: [productcategoriesInstanceList: productscategories, restaurantInstance: restaurantInstance])
            }

        }
    }

        /**
     * Function called via AJAX and that retrieves the list of products for a specific restaurant AND category
     */
    def showproductlist ={
        Restaurant restaurantInstance = Restaurant.get(params.id)
        if (!restaurantInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), params.id])}"
            redirect(action: "list")
        }
        else {
            def productscategories
            if (params.productcategory == null){
                productscategories = restaurantInstance.productsCategories.sort({a,b-> a.catorder.compareTo(b.catorder)})
                render(view: "productlistajax", model: [productcategoriesInstanceList: productscategories, restaurantInstance: restaurantInstance, productcategoriesInstanceTotal: productscategories.size()])
            }
            else{
                productscategories = ProductCategory.get(params.productcategory)
                render(view: "productlistajax", model: [productcategoriesInstanceList: productscategories, restaurantInstance: restaurantInstance])
            }
        }
    }
}
