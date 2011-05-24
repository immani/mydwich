package com.immani.mydwich

class ProducttagController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [productTagInstanceList: ProductTag.list(params), productTagInstanceTotal: ProductTag.count()]
    }

    def create = {
        def productTagInstance = new ProductTag()
        productTagInstance.properties = params
        return [productTagInstance: productTagInstance]
    }

    def save = {
        def productTagInstance = new ProductTag(params)
        if (productTagInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'productTag.label', default: 'ProductTag'), productTagInstance.id])}"
            redirect(action: "show", id: productTagInstance.id)
        }
        else {
            render(view: "create", model: [productTagInstance: productTagInstance])
        }
    }

    def show = {
        def productTagInstance = ProductTag.get(params.id)
        if (!productTagInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'productTag.label', default: 'ProductTag'), params.id])}"
            redirect(action: "list")
        }
        else {
            [productTagInstance: productTagInstance]
        }
    }

    def edit = {
        def productTagInstance = ProductTag.get(params.id)
        if (!productTagInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'productTag.label', default: 'ProductTag'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [productTagInstance: productTagInstance]
        }
    }

    def update = {
        def productTagInstance = ProductTag.get(params.id)
        if (productTagInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (productTagInstance.version > version) {
                    
                    productTagInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'productTag.label', default: 'ProductTag')] as Object[], "Another user has updated this ProductTag while you were editing")
                    render(view: "edit", model: [productTagInstance: productTagInstance])
                    return
                }
            }
            productTagInstance.properties = params
            if (!productTagInstance.hasErrors() && productTagInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'productTag.label', default: 'ProductTag'), productTagInstance.id])}"
                redirect(action: "show", id: productTagInstance.id)
            }
            else {
                render(view: "edit", model: [productTagInstance: productTagInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'productTag.label', default: 'ProductTag'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def productTagInstance = ProductTag.get(params.id)
        if (productTagInstance) {
            try {
                productTagInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'productTag.label', default: 'ProductTag'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'productTag.label', default: 'ProductTag'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'productTag.label', default: 'ProductTag'), params.id])}"
            redirect(action: "list")
        }
    }
}
