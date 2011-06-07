package com.immani.mydwich.admin
import com.immani.mydwich.*
class ProductadminController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [productInstanceList: Product.list(params), productInstanceTotal: Product.count()]
    }

    def create = {
        redirect(action: "createproductrestaurant", params: params)

        /*def productInstance = new Product()
        productInstance.properties = params
        return [productInstance: productInstance]*/
    }

    def save = {
        def productInstance = new Product(params)
        if (productInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'product.label', default: 'Product'), productInstance.id])}"
            redirect(action: "show", id: productInstance.id)
        }
        else {
            render(view: "create", model: [productInstance: productInstance])
        }
    }

    def show = {
        def productInstance = Product.get(params.id)
        if (!productInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
            redirect(action: "list")
        }
        else {
            [productInstance: productInstance]
        }
    }

    def edit = {
        def productInstance = Product.get(params.id)
        if (!productInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [productInstance: productInstance]
        }
    }

    def update = {
        def productInstance = Product.get(params.id)
        if (productInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (productInstance.version > version) {

                    productInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'product.label', default: 'Product')] as Object[], "Another user has updated this Product while you were editing")
                    render(view: "edit", model: [productInstance: productInstance])
                    return
                }
            }
            productInstance.properties = params
            if (!productInstance.hasErrors() && productInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'product.label', default: 'Product'), productInstance.id])}"
                redirect(action: "show", id: productInstance.id)
            }
            else {
                render(view: "edit", model: [productInstance: productInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def productInstance = Product.get(params.id)
        if (productInstance) {
            try {
                productInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
            redirect(action: "list")
        }
    }

    def listproductrestaurant = {
        User user = session.user.merge()
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def productlist = user.restaurant.products
        //def productlist = Product.findAllByRestaurant(user.restaurant, params)
        render(view: "list", model: [productInstanceList: productlist, productInstanceTotal: productlist.size()])
    }

    def createproductrestaurant = {
        def user = session.user.merge()
        def newproduct= new Product(restaurant: user.restaurant)
        render(view: "create", model: [productInstance: newproduct])
    }

    def showrestaurantcatalog = {
        Restaurant restaurantInstance = Restaurant.get(params.id)
        def products
        def productscategories
        if (!restaurantInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), params.id])}"
            redirect(action: "list")
        }
        else {
            if (params.productcategory == null){
                productscategories = restaurantInstance.productsCategories
                render(view: "catalog", model: [productcategoriesInstanceList: productscategories, restaurantInstance: restaurantInstance, productcategoriesInstanceTotal: productscategories.size()])
            }
            else{
                productscategories = ProductCategory.get(params.productcategory)
                render(view: "catalog", model: [productcategoriesInstanceList: productscategories, restaurantInstance: restaurantInstance])
            }

        }
    }

    def showrestaurantcatalogold = {
        Restaurant restaurantInstance = Restaurant.get(params.id)
        def products
        if (!restaurantInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), params.id])}"
            redirect(action: "list")
        }
        else {

            if (params.productcategory == null){
                //    products = Product.findAllByRestaurant(restaurantInstance)
                products = Product.findAllByRestaurant(restaurantInstance, [sort:"name_fr"])
            }
            else{
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

                def prodcategory = []
                prodcategory << ProductCategory.get(params.productcategory)
                products = Product.findAllByProductCategories(prodcategory)
            }
            render(view: "catalog", model: [productInstanceList: products, restaurantInstance: restaurantInstance, productInstanceTotal: products.size()])
        }
    }

}
