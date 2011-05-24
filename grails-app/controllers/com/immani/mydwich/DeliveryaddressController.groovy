package com.immani.mydwich

class DeliveryaddressController {
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [deliveryAddressInstanceList: DeliveryAddress.list(params), deliveryAddressInstanceTotal: DeliveryAddress.count()]
    }

    def create = {
        def deliveryAddressInstance = new DeliveryAddress()
        deliveryAddressInstance.properties = params
        return [deliveryAddressInstance: deliveryAddressInstance]
    }

    def save = {
        def deliveryAddressInstance = new DeliveryAddress(params)
        if (deliveryAddressInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'deliveryAddress.label', default: 'DeliveryAddress'), deliveryAddressInstance.id])}"
            redirect(action: "show", id: deliveryAddressInstance.id)
        }
        else {
            render(view: "create", model: [deliveryAddressInstance: deliveryAddressInstance])
        }
    }

    def show = {
        def deliveryAddressInstance = DeliveryAddress.get(params.id)
        if (!deliveryAddressInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'deliveryAddress.label', default: 'DeliveryAddress'), params.id])}"
            redirect(action: "list")
        }
        else {
            [deliveryAddressInstance: deliveryAddressInstance]
        }
    }

    def edit = {
        def deliveryAddressInstance = DeliveryAddress.get(params.id)
        if (!deliveryAddressInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'deliveryAddress.label', default: 'DeliveryAddress'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [deliveryAddressInstance: deliveryAddressInstance]
        }
    }

    def update = {
        def deliveryAddressInstance = DeliveryAddress.get(params.id)
        if (deliveryAddressInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (deliveryAddressInstance.version > version) {
                    
                    deliveryAddressInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'deliveryAddress.label', default: 'DeliveryAddress')] as Object[], "Another user has updated this DeliveryAddress while you were editing")
                    render(view: "edit", model: [deliveryAddressInstance: deliveryAddressInstance])
                    return
                }
            }
            deliveryAddressInstance.properties = params
            if (!deliveryAddressInstance.hasErrors() && deliveryAddressInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'deliveryAddress.label', default: 'DeliveryAddress'), deliveryAddressInstance.id])}"
                redirect(action: "show", id: deliveryAddressInstance.id)
            }
            else {
                render(view: "edit", model: [deliveryAddressInstance: deliveryAddressInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'deliveryAddress.label', default: 'DeliveryAddress'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def deliveryAddressInstance = DeliveryAddress.get(params.id)
        if (deliveryAddressInstance) {
            try {
                deliveryAddressInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'deliveryAddress.label', default: 'DeliveryAddress'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'deliveryAddress.label', default: 'DeliveryAddress'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'deliveryAddress.label', default: 'DeliveryAddress'), params.id])}"
            redirect(action: "list")
        }
    }

/**
     * Create a delivery address for the current user's company
     */
    def createdeliveryaddress = {
        User user = session.user.merge()
        if (user.company == null){
            flash.message = "The current user doesn't belong to a company "
            render(view: "/info")
        }
        else{
            DeliveryAddress deliveryAddressInstance = new DeliveryAddress(company: user.company)
            render(view: "create", model: [deliveryAddressInstance: deliveryAddressInstance])
        }
    }

    /**
     * List of all delivery addresses belonging to the current user's company
     */
    def listdeliveryaddress = {
        User user = session.user.merge()
        if (user.company == null){
            flash.message = "The current user doesn't belong to a company"
            render(view: "/info")
        }
        else{
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            def deliveryAddressList = user.company.deliveryAddresses
            render(view: "list", model: [deliveryAddressInstanceList: deliveryAddressList, deliveryAddressInstanceTotal: deliveryAddressList.size()])
        }
    }
}