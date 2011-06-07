class UrlMappings {
    static mappings = {

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