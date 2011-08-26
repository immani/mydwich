package com.immani.mydwich

class PictureController {
    def imageService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "listrestaurantpictures", params: params)
    }

    def listrestaurantpictures = {
        User user = session.user.merge()
        Restaurant restaurant = user.restaurant
        def pictureList = restaurant.pictures
        render(view: "list", model: [pictureInstanceList: pictureList, pictureInstanceTotal: pictureList.size()])
    }

    def createrestaurantpicture = {
        User user = session.user.merge()
        def pictureInstance = new Picture(restaurant: user.restaurant)
        pictureInstance.properties = params
        render(view:"create", model:[pictureInstance: pictureInstance])
    }

    def save = {
        User user = session.user.merge()
        //Restaurant restaurant = user.restaurant
        String restaurantname = user.restaurant.name.toString().toLowerCase().encodeAsURL()
        def pictureInstance = new Picture(params)
        pictureInstance.restaurant = user.restaurant

        def f = request.getFile('file')
        if (f.size > 0){
            def restfolder = grailsAttributes.getApplicationContext().getResource("/restimages/${restaurantname}").getFile()
            if (!restfolder.exists()){
                String rootfolder = grailsAttributes.getApplicationContext().getResource("/").getFile().toString()
                new File(rootfolder + "/restimages/${restaurantname}").mkdir()
            }
            imageService.resizeandthumb(pictureInstance, f, restfolder.toString())
        }
        if (pictureInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'picture.label', default: 'Picture'), pictureInstance.id])}"
            redirect(action: "show", id: pictureInstance.id)
        }
        else {
            render(view: "create", model: [pictureInstance: pictureInstance])
        }
    }

    def show = {
        def pictureInstance = Picture.get(params.id)
        if (!pictureInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'picture.label', default: 'Picture'), params.id])}"
            redirect(action: "list")
        }
        else {
            [pictureInstance: pictureInstance]
        }
    }

    def edit = {
        def pictureInstance = Picture.get(params.id)
        if (!pictureInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'picture.label', default: 'Picture'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [pictureInstance: pictureInstance]
        }
    }

    def update = {
        def pictureInstance = Picture.get(params.id)
        if (pictureInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (pictureInstance.version > version) {

                    pictureInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'picture.label', default: 'Picture')] as Object[], "Another user has updated this Picture while you were editing")
                    render(view: "edit", model: [pictureInstance: pictureInstance])
                    return
                }
            }
            pictureInstance.properties = params
            if (!pictureInstance.hasErrors() && pictureInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'picture.label', default: 'Picture'), pictureInstance.id])}"
                redirect(action: "show", id: pictureInstance.id)
            }
            else {
                render(view: "edit", model: [pictureInstance: pictureInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'picture.label', default: 'Picture'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def pictureInstance = Picture.get(params.id)
        if (pictureInstance) {
            try {
                String restaurantname = pictureInstance.restaurant.name.toString().toLowerCase().encodeAsURL()
                def imagefile = grailsAttributes.getApplicationContext().getResource("/restimages/${restaurantname}/${pictureInstance.filename}").getFile()
                def thumbimagefile = grailsAttributes.getApplicationContext().getResource("/restimages/${restaurantname}/thumb_${pictureInstance.filename}").getFile()
                imagefile.delete()
                thumbimagefile.delete()
                pictureInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'picture.label', default: 'Picture'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'picture.label', default: 'Picture'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'picture.label', default: 'Picture'), params.id])}"
            redirect(action: "list")
        }
    }
}
