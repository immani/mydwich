package com.immani.mydwich

import org.apache.shiro.SecurityUtils

class HomeController {
    //TODO: To be completed
    def index = {
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
    }
}
