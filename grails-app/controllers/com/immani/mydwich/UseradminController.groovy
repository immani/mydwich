package com.immani.mydwich

class UseradminController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create = {
        def userInstance = new User()
        userInstance.properties = params
        return [userInstance: userInstance]
    }

    // TODO: manage roles for the newly created users
    def save = {
        def userInstance = new User(params)
        User user = session.user.merge()
        if (user.restaurant != null){
            user.restaurant.addToUsers(userInstance)
            Role restaurantRole = Role.findByName("restaurant")
            userInstance.addToRoles(restaurantRole)
        }
        if (user.company != null){
            user.company.addToUsers(userInstance)
            Role companyRole = Role.findByName("company")
            userInstance.addToRoles(companyRole)
        }
        if (userInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
            redirect(action: "show", id: userInstance.id)
        }
        else {
            render(view: "create", model: [userInstance: userInstance])
        }
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
            if (!userInstance.hasErrors() && userInstance.save(flush: true)) {
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



    //TODO: check error messages sent
    /**
     * Create a user for the current user restaurant
     */
    def createuserrestaurant = {
        User user = session.user.merge()
        if (user.restaurant == null){
            flash.message = "The current user doesn't belong to a restaurant"
            render(view: "/info")
        }
        else{
            User newuser = new User(restaurant: user.restaurant)
            render(view: "create", model: [userInstance: newuser])
        }
    }

    /**
     * List of all users belonging to the current user's restaurant
     */
    def listuserrestaurant = {
        User user = session.user.merge()
        if (user.restaurant == null){
            flash.message = "The current user doesn't belong to a restaurant"
            render(view: "/info")
        }
        else{
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            def userlist = User.findAllByRestaurant(user.restaurant, params)
            render(view: "list", model: [userInstanceList: userlist, userInstanceTotal: userlist.size()])
        }
    }

    /**
     * Create a user for the current user's company
     */
    def createusercompany = {
        User user = session.user.merge()
        if (user.company == null){
            flash.message = "The current user doesn't belong to a company"
            render(view: "/info")
        }
        else{
            User newuser = new User(company: user.company)
            render(view: "create", model: [userInstance: newuser])
        }
    }

    /**
     * List all the users belonging to the current user company
     */
    def listuserscompany = {
        User user = session.user.merge()
        if (user.company == null){
            flash.message = "The current user doesn't belong to a company"
            render(view: "/info")
        }
        else{
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            def userlist = User.findAllByCompany(user.company, params)
            render(view: "list", model: [userInstanceList: userlist, userInstanceTotal: userlist.size()])
        }
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
    /**
     * Creates a new Company, User and Delivery Address
     */
    def registercompanyadminuserFlow = {

        companyinfo {

            on("next") {
                flow.companyInstance = new Company(params)
                flow.companyInstance.validate() ? success() : error()
            }.to "userinfo"

            on("cancel").to "cancel"
        }

        userinfo {
            on("next") {
                flow.usernameleft = params.usernameleft
                params.username = params.usernameleft + "@" + flow.companyInstance.domain
                flow.userInstance = new User(params)
                Role companyRole = Role.findByName("company")
                flow.userInstance.addToRoles(companyRole)
                flow.userInstance.validate() ? success() : error()
            }.to "deliveryinfo"

            on("back").to "companyinfo"
            on("cancel").to "cancel"
        }

        deliveryinfo {
            on("confirm") {
                flow.deliveryAddressInstance = new DeliveryAddress(params)
                flow.deliveryAddressInstance.company = flow.companyInstance
                flow.deliveryAddressInstance.validate() ? success() : error()
            }.to "persist"

            on("back").to "userinfo"
            on("cancel").to "cancel"
        }


        persist {
            action {
                flow.companyInstance.addToUsers(flow.userInstance)
                flow.companyInstance.addToDeliveryAddresses(flow.deliveryAddressInstance)
                flow.companyInstance.save()
            }

            on("success").to "end"
        }

        end {
            redirect(uri: "/")
        }

        cancel {
            redirect(uri: "/")
        }
    }

    def registerrestaurantadminuserFlow = {

        restaurantinfo {

            on("next") {
                flow.restaurantInstance = new Restaurant(params)
                flow.restaurantInstance.validate() ? success() : error()
            }.to "userinfo"

            on("cancel").to "cancel"
        }

        userinfo {
            on("next") {
                flow.userInstance = new User(params)
                Role restaurantRole = Role.findByName("restaurant")
                flow.userInstance.addToRoles(restaurantRole)
                flow.userInstance.validate() ? success() : error()
            }.to "persist"

            on("back").to "restaurantinfo"
            on("cancel").to "cancel"
        }

        persist {
            action {
                flow.restaurantInstance.addToUsers(flow.userInstance)
                flow.restaurantInstance.save()

                sendMail {
                    from "admin@mydwich.com"
                    to flow.userInstance.username
                    subject "Welcome to mydwich ${flow.userInstance.firstname} ${flow.userInstance.lastname}"
                    html "<b>Hello</b> ${flow.userInstance.firstname} ${flow.userInstance.lastname} <br />" +
                            "Welcome to myDwich application, to start using the application: <br />" +
                            "http://mydwich.immani.com:8080/"
                }
            }

            on("success").to "end"
        }

        end {
            redirect(controller: "restaurant", action:"list")
        }

        cancel {
            redirect(url: "/index")
        }
    }
       def registeruserFlow = {

        mail {

            on("next") {
                String username = params.username
                Integer pos = username.indexOf('@')
                String domain = username.substring(pos + 1)
                def matchingcompanies = Company.findAllWhere(domain: domain)
                if (matchingcompanies.size() == 1){
                    flow.companyInstance = matchingcompanies[0]
                    flow.userInstance = new User(params)
                    success()
                }
                else if (matchingcompanies.size() == 0){
                    flash.message = ${message(code: "flow.company.not.found", args: [domain])}
                //    flash.message = "flow.company.not.found"
                //    flash.args = ["domain"]
                    error()
                }
            }.to "userinfo"

            on("cancel").to "cancel"
        }

        userinfo {
            on("next") {
                flow.userInstance.properties = params
                Role companyRole = Role.findByName("company")
                flow.userInstance.addToRoles(companyRole)
                flow.userInstance.validate() ? success() : error()
            }.to "persist"

            on("back").to "userinfo"
            on("cancel").to "cancel"
        }



        persist {
            action {
                flow.companyInstance.addToUsers(flow.userInstance)
                flow.userInstance.save()
            }

            on("success").to "end"
        }

        end {
            redirect(url: "/")
        }

        cancel {
            redirect(url: "/")
        }
    }
}
