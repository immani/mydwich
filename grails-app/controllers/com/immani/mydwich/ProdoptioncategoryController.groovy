package com.immani.mydwich

class ProdoptioncategoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }



    def save = {
        def prodOptionCategoryInstance = new ProdOptionCategory(params)
        if (prodOptionCategoryInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'prodOptionCategory.label', default: 'ProdOptionCategory'), prodOptionCategoryInstance.id])}"
            redirect(action: "show", id: prodOptionCategoryInstance.id)
        }
        else {
            render(view: "create", model: [prodOptionCategoryInstance: prodOptionCategoryInstance])
        }
    }

    def show = {
        def prodOptionCategoryInstance = ProdOptionCategory.get(params.id)
        if (!prodOptionCategoryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prodOptionCategory.label', default: 'ProdOptionCategory'), params.id])}"
            redirect(action: "list")
        }
        else {
            [prodOptionCategoryInstance: prodOptionCategoryInstance]
        }
    }

    def edit = {
        def prodOptionCategoryInstance = ProdOptionCategory.get(params.id)
        if (!prodOptionCategoryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prodOptionCategory.label', default: 'ProdOptionCategory'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [prodOptionCategoryInstance: prodOptionCategoryInstance]
        }
    }

    def update = {
        def prodOptionCategoryInstance = ProdOptionCategory.get(params.id)
        if (prodOptionCategoryInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (prodOptionCategoryInstance.version > version) {
                    
                    prodOptionCategoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'prodOptionCategory.label', default: 'ProdOptionCategory')] as Object[], "Another user has updated this ProdOptionCategory while you were editing")
                    render(view: "edit", model: [prodOptionCategoryInstance: prodOptionCategoryInstance])
                    return
                }
            }
            prodOptionCategoryInstance.properties = params
            if (!prodOptionCategoryInstance.hasErrors() && prodOptionCategoryInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'prodOptionCategory.label', default: 'ProdOptionCategory'), prodOptionCategoryInstance.id])}"
                redirect(action: "show", id: prodOptionCategoryInstance.id)
            }
            else {
                render(view: "edit", model: [prodOptionCategoryInstance: prodOptionCategoryInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prodOptionCategory.label', default: 'ProdOptionCategory'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def prodOptionCategoryInstance = ProdOptionCategory.get(params.id)
        if (prodOptionCategoryInstance) {
            try {
                prodOptionCategoryInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'prodOptionCategory.label', default: 'ProdOptionCategory'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'prodOptionCategory.label', default: 'ProdOptionCategory'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prodOptionCategory.label', default: 'ProdOptionCategory'), params.id])}"
            redirect(action: "list")
        }
    }

    def create = {
        User user = session.user.merge()
        ProdOptionCategory prodOptionCategoryInstance= new ProdOptionCategory(restaurant: user.restaurant)
        render(view: "create", model:[prodOptionCategoryInstance: prodOptionCategoryInstance])
    }

    def List = {
        User user = session.user.merge()
        if (user.restaurant == null){
            flash.message = "The current user doesn't belong to a restaurant"
            render(view: "/info")
        }
        else{
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            def prodoptcatlist = ProdOptionCategory.findAllByRestaurant(user.restaurant, params)

            render(view:"list", model:[prodOptionCategoryInstanceList: prodoptcatlist, prodOptionCategoryInstanceTotal: prodoptcatlist.size()])
        }
    }
}