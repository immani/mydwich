package com.immani.mydwich

class UserpaymentadminController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    /*
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userpaymentInstanceList: Userpayment.list(params), userpaymentInstanceTotal: Userpayment.count()]
    }

    def create = {
        def userpaymentInstance = new Userpayment()
        userpaymentInstance.properties = params
        return [userpaymentInstance: userpaymentInstance]
    }
    */

    def save = {
        def userpaymentInstance = new Userpayment(params)
        if (userpaymentInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'userpayment.label', default: 'Userpayment'), userpaymentInstance.id])}"
            redirect(action: "show", id: userpaymentInstance.id)
        }
        else {
            render(view: "create", model: [userpaymentInstance: userpaymentInstance])
        }
    }

    def show = {
        def userpaymentInstance = Userpayment.get(params.id)
        if (!userpaymentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userpayment.label', default: 'Userpayment'), params.id])}"
            redirect(action: "list")
        }
        else {
            [userpaymentInstance: userpaymentInstance]
        }
    }

    def edit = {
        def userpaymentInstance = Userpayment.get(params.id)
        if (!userpaymentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userpayment.label', default: 'Userpayment'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [userpaymentInstance: userpaymentInstance]
        }
    }

    def update = {
        def userpaymentInstance = Userpayment.get(params.id)
        if (userpaymentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (userpaymentInstance.version > version) {
                    
                    userpaymentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'userpayment.label', default: 'Userpayment')] as Object[], "Another user has updated this Userpayment while you were editing")
                    render(view: "edit", model: [userpaymentInstance: userpaymentInstance])
                    return
                }
            }
            userpaymentInstance.properties = params
            if (!userpaymentInstance.hasErrors() && userpaymentInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'userpayment.label', default: 'Userpayment'), userpaymentInstance.id])}"
                redirect(action: "show", id: userpaymentInstance.id)
            }
            else {
                render(view: "edit", model: [userpaymentInstance: userpaymentInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userpayment.label', default: 'Userpayment'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def userpaymentInstance = Userpayment.get(params.id)
        if (userpaymentInstance) {
            try {
                userpaymentInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'userpayment.label', default: 'Userpayment'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'userpayment.label', default: 'Userpayment'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userpayment.label', default: 'Userpayment'), params.id])}"
            redirect(action: "list")
        }
    }

     /**
     * Creates a new payment for the current user
     */
    def createuserpayment = {
        User currentuser = session.user.merge()
        Userpayment userpaymentInstance = new Userpayment(user: currentuser)
        userpaymentInstance.properties = params
        render(view: "create", model: [userpaymentInstance: userpaymentInstance])
    }

     /**
     * List of the payments of the current user
     */
    def listuserpayment = {
        User user = session.user.merge()
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def userpaymentlist = Userpayment.findAllByUser(user, params)
        render(view: "list", model: [userpaymentInstanceList: userpaymentlist, userpaymentInstanceTotal: userpaymentlist.size()])
    }
}
