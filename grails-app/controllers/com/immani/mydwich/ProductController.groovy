package com.immani.mydwich

class ProductController {
    def imageService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def save = {
        User user = session.user.merge()
        String restaurantname = user.restaurant.name.toString().toLowerCase().encodeAsURL()

        def productInstance = new Product(params["product"])
        productInstance.restaurant = user.restaurant

        def f = request.getFile('picture.file')
        if (f.size > 0){
            def pictureInstance = new Picture(params["picture"])
            productInstance.picture = pictureInstance
            def folder = grailsAttributes.getApplicationContext().getResource("/restimages/${restaurantname}/products").getFile()
            if (!folder.exists()){
                String rootfolder = grailsAttributes.getApplicationContext().getResource("/").getFile().toString()
                Boolean success = new File(rootfolder + "/restimages/${restaurantname}/products").mkdir()
            }
            imageService.resizeandthumb(pictureInstance, f, folder.toString())
        }
        if (productInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'product.label', default: 'Product'), productInstance.id])}"
            redirect(action: "show", id: productInstance.id)
        }
        else {
            render(view: "create", model: [productInstance: productInstance])
        }
    }

    def show = {
        Product productInstance = Product.get(params.id)
        def pictureInstance = productInstance.picture
        if (!productInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
            redirect(action: "list")
        }
        else {
            [productInstance: productInstance, pictureInstance: pictureInstance]
        }
    }

    def edit = {
        def productInstance = Product.get(params.id)
        def PictureInstance = productInstance.picture
        if (!productInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [productInstance: productInstance]
        }
    }

    def update = {
        User user = session.user.merge()
        String restaurantname = user.restaurant.name.toString().toLowerCase().encodeAsURL()
        def productInstance = Product.get(params["product"].id)
        productInstance.properties = params["product"]

        if (productInstance) {
            if (params["product"].version) {
                def version = params["product"].version.toLong()
                if (productInstance.version > version) {

                    productInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'product.label', default: 'Product')] as Object[], "Another user has updated this Product while you were editing")
                    render(view: "edit", model: [productInstance: productInstance, pictureInstance: pictureInstance])
                    return
                }
            }
            def f = request.getFile('picture.file')
            if (f.size > 0){
                //On efface la picture précédente
                def pictureInstance = new Picture(params["picture"])
                productInstance.picture = pictureInstance
                pictureInstance.product = productInstance
                def folder = grailsAttributes.getApplicationContext().getResource("/restimages/${restaurantname}/products").getFile()
                if (!folder.exists()){
                    String rootfolder = grailsAttributes.getApplicationContext().getResource("/").getFile().toString()
                    Boolean success = new File(rootfolder + "/restimages/${restaurantname}/products").mkdir()
                }
                imageService.resizeandthumb(pictureInstance, f, folder.toString())
            }
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
                Picture pictureInstance = productInstance.picture
                if (pictureInstance){
                    String restaurantname = productInstance.restaurant.name.toString().toLowerCase().encodeAsURL()
                    def imagefile = grailsAttributes.getApplicationContext().getResource("/restimages/${restaurantname}/products/${pictureInstance.filename}").getFile()
                    def thumbimagefile = grailsAttributes.getApplicationContext().getResource("/restimages/${restaurantname}/products/thumb_${pictureInstance.filename}").getFile()
                    imagefile.delete()
                    thumbimagefile.delete()
                    productInstance.picture = null
                    pictureInstance.delete(flush: true)
                }

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

    def list = {
        User user = session.user.merge()
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def productlist = user.restaurant.products

        render(view: "list", model: [productInstanceList: productlist, productInstanceTotal: productlist.size()])
    }

    def create = {
        def user = session.user.merge()
        def newproduct= new Product(restaurant: user.restaurant)
        render(view: "create", model: [productInstance: newproduct])
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

    def search = {
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
