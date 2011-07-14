class UrlMappings {
    static mappings = {
        /*
         "/anonymous/$controller/$action?/$id?"{
            controller="anonymous/$controller"
            action = $action
        }*/

        "/product/$action?/$id?"{
           controller="anonymous_Product"
           action = $action
        }

        "/restaurant/$action?/$id?"{
           controller="anonymous_Restaurant"
           action = $action
        }

         "/showrestaurant/$name/$page?"{
            constraints {
                // apply constraints here
            }
            controller="anonymous_Restaurant"
            action = "getbyname"

        }


        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }

	    "500"(view:'/error')

	    "/"{
            controller="home"
        }

        "/registration/company"{
            controller="registration"
            action = "registercompany"
        }

        "/registration/restaurant"{
            controller="registration"
            action = "registerrestaurant"
        }

        "/registration/user"{
            controller="registration"
            action = "registeruser"
        }
	}
}