package com.immani.mydwich
import com.megatome.grails.RecaptchaService

class RegistrationController {
    def geocoderService

    RecaptchaService recaptchaService
    def index = {
        render(view:'/recaptcha')
    }

    def save = {
        def recaptchaOK = true
        if (!recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)) {
            recaptchaOK = false
        }
        else {
            recaptchaOK = true
        }
        render(view:'/recaptcha',model:[recaptcha:recaptchaOK])
    }

    /**
     * Creates a new Company, User and Delivery Address
     */
    def registercompanyFlow = {

        companyinfo {

            on("next") {
                def coresults
                try {
                    coresults = geocoderService.geocode(params.address, params.zip, params.city, params.country )
                }
                catch (Exception exception){
                   flow.companyInstance = new Company(params)
                   flow.companyInstance.validate()
                   flash.error = exception.getMessage()
                   return error()
                }

                flow.companyInstance = new Company(params + coresults)
                flow.companyInstance.validate() ? success() : error()

            }.to "userinfo"

            on("cancel").to "cancel"
        }

        userinfo {
            on("next") {
                flow.usernameleft = params.usernameleft
                params.username = params.usernameleft + "@" + flow.companyInstance.domain
                flow.userInstance = new User(params)
                Role companyRole = Role.findByName("companyadmin")
                flow.userInstance.addToRoles(companyRole)
                flow.deliveryAddressInstance = new DeliveryAddress(flow.companyInstance.properties)
                flow.deliveryAddressInstance.name = ""
                flow.userInstance.validate() ? success() : error()
            }.to "deliveryinfo"

            on("back").to "companyinfo"
            on("cancel").to "cancel"
        }

        deliveryinfo {
            on("next") {
                def daresults
                try {
                    daresults = geocoderService.geocode(params.address, params.zip, params.city, params.country )
                }
                catch (Exception exception){
                   flow.deliveryAddressInstance.validate()
                   flash.error = exception.getMessage()
                   return error()
                }

                flow.deliveryAddressInstance = new DeliveryAddress(params + daresults)
                flow.deliveryAddressInstance.company = flow.companyInstance
                flow.deliveryAddressInstance.validate() ? success() : error()
            }.to "review"

            on("back").to "userinfo"
            on("cancel").to "cancel"
        }
        review {
            on("confirm") {

            }.to "persist"

            on("success").to "end"
        }

        persist {
            action {
                emailConfirmationService.sendConfirmation(flow.userInstance.username, "Please confirm your email address", [from:"mydwich@immani.com"])
                flow.companyInstance.addToUsers(flow.userInstance)
                flow.companyInstance.addToDeliveryAddresses(flow.deliveryAddressInstance)
                flow.companyInstance.save()
            }

            on("success").to "end"
        }

        end {
            redirect(controller: "company", action: "show")
        }

        cancel {
            redirect(uri: "/")
        }
    }

    def registerrestaurantFlow = {
        //TODO: Check Roles!!!
        restaurantinfo {

            on("next") {
                def restresults
                try {
                    restresults = geocoderService.geocode(params.address, params.zip, params.city, params.country )
                }
                catch (Exception exception){
                   flow.companyInstance = new Restaurant(params)
                   flash.message = exception.getMessage()
                   return error()
                }
                flow.restaurantInstance = new Restaurant(params + restresults)
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
