package com.immani.mydwich

import org.apache.shiro.SecurityUtils

class HomeController {
    //TODO: To be completed
    def index = {
        User user = User.findByUsername(SecurityUtils.getSubject().principal.toString())
        if (user == null) {
            return redirect(controller: "public", action: "listrestaurants")
        }
        if (user.company != null){
            if (user.isadmin){
                redirect(controller: "company")
            }
            else{
                redirect(controller: "user")
            }
        }
        else if(user.restaurant != null){
            redirect(controller: "anonymous_Restaurant")
        }

        /*
        if (SecurityUtils.subject.hasRole("restaurantadmin")){
            redirect(controller: "restaurant")
        }
        else if(SecurityUtils.subject.hasRole("restaurant")) {
           redirect(controller: "restaurant")
        }
        else if(SecurityUtils.subject.hasRole("companyadmin")) {
           redirect(controller: "company")
        }
         else if(SecurityUtils.subject.hasRole("company")) {
           redirect(controller: "user")
        }

        else{
            //flash.message = "You are not authorized to perform that operation"
            redirect(controller: "public", action: "listrestaurants")
        }
        */
    }
}
