package com.immani.mydwich

class ProdoptionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "listprodoptionrestaurant", params: params)
    }

    def list = {
        User user = session.user.merge()
        if (user.restaurant == null){
            flash.message = "The current user doesn't belong to a restaurant"
            render(view: "/info")
        }
        else{
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            def productCategories = user.restaurant.productsCategories
            def prodOptionCategories = productCategories.prodOptionCategories.flatten().unique().sort()
            def prodoptlist = prodOptionCategories.productCategories
            render(view:"list", model:[prodOptionInstanceList: prodoptlist, prodOptionInstanceTotal: prodoptlist.size()])
        }
    }

    def listforprodoptioncategory = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        ProdOptionCategory prodoptioncategory =ProdOptionCategory.get(params.prodoptcatid)
        def prodoptlist = prodoptioncategory.options
        render(view:"list", model:[prodoptioncategoryInstance: prodoptioncategory, prodOptionInstanceList: prodoptlist, prodOptionInstanceTotal: prodoptlist.size()])

    }


    def create = {
        ProdOptionCategory prodOptionCat = ProdOptionCategory.get(params.prodoptioncategory)
        User user = session.user.merge()
        ProdOption prodOptionInstance = new ProdOption(restaurant: user.restaurant, prodOptionCategory: prodOptionCat)
        render(view: "create", model: [prodOptionInstance: prodOptionInstance])
    }

    def save = {
        def prodOptionInstance = new ProdOption(params)
        if (prodOptionInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'prodOption.label', default: 'ProdOption'), prodOptionInstance.id])}"
            redirect(action: "show", id: prodOptionInstance.id)
        }
        else {
            render(view: "create", model: [prodOptionInstance: prodOptionInstance])
        }
    }

    def show = {
        def prodOptionInstance = ProdOption.get(params.id)
        if (!prodOptionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prodOption.label', default: 'ProdOption'), params.id])}"
            redirect(action: "list")
        }
        else {
            [prodOptionInstance: prodOptionInstance]
        }
    }

    def edit = {
        def prodOptionInstance = ProdOption.get(params.id)
        if (!prodOptionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prodOption.label', default: 'ProdOption'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [prodOptionInstance: prodOptionInstance]
        }
    }

    def update = {
        def prodOptionInstance = ProdOption.get(params.id)
        if (prodOptionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (prodOptionInstance.version > version) {

                    prodOptionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'prodOption.label', default: 'ProdOption')] as Object[], "Another user has updated this ProdOption while you were editing")
                    render(view: "edit", model: [prodOptionInstance: prodOptionInstance])
                    return
                }
            }
            prodOptionInstance.properties = params
            if (!prodOptionInstance.hasErrors() && prodOptionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'prodOption.label', default: 'ProdOption'), prodOptionInstance.id])}"
                redirect(action: "show", id: prodOptionInstance.id)
            }
            else {
                render(view: "edit", model: [prodOptionInstance: prodOptionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prodOption.label', default: 'ProdOption'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def prodOptionInstance = ProdOption.get(params.id)
        if (prodOptionInstance) {
            try {
                prodOptionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'prodOption.label', default: 'ProdOption'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'prodOption.label', default: 'ProdOption'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prodOption.label', default: 'ProdOption'), params.id])}"
            redirect(action: "list")
        }
    }
}
