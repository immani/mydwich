package com.immani.mydwich

class BasketlineController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [basketLineInstanceList: BasketLine.list(params), basketLineInstanceTotal: BasketLine.count()]
    }

    def create = {
        def basketLineInstance = new BasketLine()
        basketLineInstance.properties = params
        return [basketLineInstance: basketLineInstance]
    }

    def save = {
        def basketLineInstance = new BasketLine(params)
        if (basketLineInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'basketLine.label', default: 'BasketLine'), basketLineInstance.id])}"
            redirect(action: "show", id: basketLineInstance.id)
        }
        else {
            render(view: "create", model: [basketLineInstance: basketLineInstance])
        }
    }

    def show = {
        def basketLineInstance = BasketLine.get(params.id)
        if (!basketLineInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'basketLine.label', default: 'BasketLine'), params.id])}"
            redirect(action: "list")
        }
        else {
            [basketLineInstance: basketLineInstance]
        }
    }

    def edit = {
        def basketLineInstance = BasketLine.get(params.id)
        if (!basketLineInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'basketLine.label', default: 'BasketLine'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [basketLineInstance: basketLineInstance]
        }
    }

    def update = {
        def basketLineInstance = BasketLine.get(params.id)
        if (basketLineInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (basketLineInstance.version > version) {
                    
                    basketLineInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'basketLine.label', default: 'BasketLine')] as Object[], "Another user has updated this BasketLine while you were editing")
                    render(view: "edit", model: [basketLineInstance: basketLineInstance])
                    return
                }
            }
            basketLineInstance.properties = params
            if (!basketLineInstance.hasErrors() && basketLineInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'basketLine.label', default: 'BasketLine'), basketLineInstance.id])}"
                redirect(action: "show", id: basketLineInstance.id)
            }
            else {
                render(view: "edit", model: [basketLineInstance: basketLineInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'basketLine.label', default: 'BasketLine'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def basketLineInstance = BasketLine.get(params.id)
        if (basketLineInstance) {
            try {
                basketLineInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'basketLine.label', default: 'BasketLine'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'basketLine.label', default: 'BasketLine'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'basketLine.label', default: 'BasketLine'), params.id])}"
            redirect(action: "list")
        }
    }
}
