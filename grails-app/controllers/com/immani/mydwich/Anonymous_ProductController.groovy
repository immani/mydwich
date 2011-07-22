package com.immani.mydwich
import com.immani.mydwich.Product
import com.immani.mydwich.User

class Anonymous_ProductController {
    def index = {
        redirect(action: "list", params: params)
    }

    def show = {
        Product productInstance = Product.get(params.id)
        def pictureInstance = productInstance.picture
        if (!productInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
            redirect(action: "list")
        }
        else {
            render(view: "/product/list", model:[productInstance: productInstance, pictureInstance: pictureInstance])
        }
    }

    def list = {
        User user = session.user.merge()
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def productlist = user.restaurant.products

        render(view: "/product/list", model: [productInstanceList: productlist, productInstanceTotal: productlist.size()])
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
        render(template:"/product/searchResults", model: searchResults)
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
