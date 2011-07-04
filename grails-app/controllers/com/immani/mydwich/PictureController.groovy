package com.immani.mydwich

import java.awt.Font
import java.awt.Color

class PictureController {
    def burningImageService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        User user = session.user.merge()
        Restaurant restaurant = user.restaurant
        def pictureList = restaurant.pictures
      //  params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [pictureInstanceList: pictureList, pictureInstanceTotal: pictureList.size()]
    }

    def create = {
        User user = session.user.merge()
        def pictureInstance = new Picture(restaurant: user.restaurant)
        pictureInstance.properties = params
        [pictureInstance: pictureInstance]
    }

    def save = {
        User user = session.user.merge()
        String restaurantname = user.restaurant.name.toString().toLowerCase().encodeAsURL()
        def pictureInstance = new Picture(params)
        def f = request.getFile('file')
        if (f.getOriginalFilename().substring(f.getOriginalFilename().indexOf('.')) == ".gif" ){
            pictureInstance.filename = f.getOriginalFilename().substring(0 , f.getOriginalFilename().indexOf('.')) + '.jpg'
        }
        else{
            pictureInstance.filename = f.getOriginalFilename()
        }
        //pictureInstance.filename = f.getOriginalFilename().encodeAsURL()
        pictureInstance.contentType = f.getContentType()
        pictureInstance.file = f.getBytes()

        def restfolder = grailsAttributes.getApplicationContext().getResource("/restimages/${restaurantname}").getFile().toString()
        if (!restfolder){
            //TODO: Create Folder
            restfolder = new File(grailsAttributes.getApplicationContext().getResource("/restimages/${restaurantname}/").getFile().toString())
        }
        def watermark =  grailsAttributes.getApplicationContext().getResource("/images/burn.png").getFile().toString()
        def originalFileName

        burningImageService.doWith(f, restfolder)
        .execute {
            it.scaleApproximate(1024, 768)
            originalFileName = it.watermark(watermark, ['top':10, 'left': 10])
        }
        .execute ('thumb_' + (pictureInstance.filename.substring(0, pictureInstance.filename.indexOf('.'))) , {
            it.scaleAccurate(200, 200)
        })
        /*.execute {img ->
                        img.text(Color.WHITE, new Font('Arial', Font.PLAIN, 30), {
                            it.write("text one", 10, 10)
                            it.write("text two", 100, 100)
                            it.write("text three", 200, 200)
                        })
                   }
        */
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
