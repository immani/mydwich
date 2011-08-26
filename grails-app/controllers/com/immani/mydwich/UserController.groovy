package com.immani.mydwich

import org.apache.shiro.crypto.hash.Sha256Hash

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        render(view: "/user_company/index")
    }
    def create = {
        User user = session.user.merge()
        User newuser
        if (user.restaurant != null){
            newuser = new User(restaurant: user.restaurant)
        }
        else if(user.company != null) {
            newuser = new User(company: user.company, username: 'youremail@' + user.company.domain)
        }
        else{
            flash.message = "You are not authorized to perform that operation"
            return render(view: "/info")
        }
        render(view: "create", model: [userInstance: newuser])
    }

    def show = {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
        else {
            [userInstance: userInstance]
        }
    }

    def edit = {
        def userInstance = User.get(params.id)
            if (!userInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
                redirect(action: "list")
            }
            else {
                return [userInstance: userInstance]
            }

    }

    def save = {
        def userInstance = new User(params)
        User user = session.user.merge()
        if (user.restaurant != null){
            user.restaurant.addToUsers(userInstance)
        }
        if (user.company != null){
            user.company.addToUsers(userInstance)
            DeliveryAddress da = DeliveryAddress.get(params.defaultdaid)
            userInstance.defaultda = da
        }
        userInstance.passwordHash = new Sha256Hash(params.passwordHash).toHex()
        userInstance.isvalidated= true
        if (userInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
            redirect(action: "show", id: userInstance.id)
        }
        else {
            render(view: "create", model: [userInstance: userInstance])
        }
    }

    def update = {
        def userInstance = User.get(params.id)
        if (userInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (userInstance.version > version) {
                    userInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'user.label', default: 'User')] as Object[], "Another user has updated this User while you were editing")
                    render(view: "edit", model: [userInstance: userInstance])
                    return
                }
            }

            userInstance.properties = params
            userInstance.defaultda = DeliveryAddress.get(params.defaultda);
            userInstance.save(flush: true)
            if (userInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
                redirect(action: "show", id: userInstance.id)
            }
            else {
                render(view: "edit", model: [userInstance: userInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def userInstance = User.get(params.id)
        if (userInstance) {
            try {
                userInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
    }


    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        User user = session.user.merge()
        def userlist
        if (user.restaurant != null){
            userlist = User.findAllByRestaurant(user.restaurant, params)
        }
        else if(user.company != null) {
            userlist = User.findAllByCompany(user.company, params)
        }
        else{
            flash.message = "You are not authorized to perform that operation"
            return render(view: "/info")
        }
        [userInstanceList: userlist, userInstanceTotal: userlist.size()]
    }


/**
 * Show current user profile
 */
    def showuserprofile = {
        User user = session.user.merge()
        render(view: "show", model:[userInstance: user])
    }

/**
 * Edit current user profile
 */
    def edituserprofile = {
        User user = session.user.merge()
        render(view: "edit", model:[userInstance: user])
    }

    def changepasswordinit = {
        User user = session.user.merge()
        render(view: "changepassword", model:[userInstance: user])
    }

    def changepassword = {
        User user = session.user.merge()
        if (new Sha256Hash(params.oldpassword).toHex() == user.passwordHash){     //check old password matches
            if(params.newpassword == params.newpasswordconfirm) {
                user.passwordHash = new Sha256Hash(params.newpassword).toHex()
                session.user = user.save(flush: true)
                flash.message = "${message(code: 'user.password.changed.successfully')}"
                redirect(action: "showuserprofile")
            }
            else{
                //TODO: Check for Errors generation...
                user.errors.reject('user.password.doesnotmatch',                         // Error code within the grails-app/i18n/message.properties
                        ['password', 'class User'] as Object[],                          // Groovy list cast to Object[]
                        '[Property [{0}] of class [{1}] does not match confirmation]')   // Default mapping string
                user.errors.rejectValue('newpasswordconfirm',                                                 // Field in view to highlight using <g:hasErrors> tag
                        'user.password.doesnotmatch')                               // i18n error code
                render(view: "changepassword", model: [userInstance: user])
            }
        }
        else{
            user.errors.reject('user.oldpassword.notcorrect',                                    // Error code within the grails-app/i18n/message.properties
                    ['password', 'class User'] as Object[],                          // Groovy list cast to Object[]
                    '[The Old Password is not correct]')   // Default mapping string
            //    user.errors.rejectValue('oldpassword',                                                 // Field in view to highlight using <g:hasErrors> tag
            //                'user.oldpassword.notcorrect')                               // i18n error code
            render(view: "changepassword", model: [userInstance: user])
        }
    }
}
