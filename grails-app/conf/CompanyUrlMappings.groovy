class CompanyUrlMappings {

	static mappings = {

        "/company/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="company"
            action = $action
        }

        "/company/deliveryaddress/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="deliveryaddress"
            action = $action
        }

        "/company/partnership/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="partnership"
            action = $action
        }

        "/company/deliveryaddress/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="deliveryaddress"
            action = $action
        }

        "/company/user/basket/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="deliveryaddress"
            action = $action
        }

        "/company/user/payment/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="deliveryaddress"
            action = $action
        }

    }
}
