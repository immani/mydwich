class CompanyUrlMappings {

	static mappings = {

        "/admin/company/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="company"
            action = $action
        }

        "/admin/company/deliveryaddress/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="deliveryaddress"
            action = $action
        }

        "/admin/company/partnership/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="partnership"
            action = $action
        }

        "/admin/company/deliveryaddress/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="deliveryaddress"
            action = $action
        }

        "/admin/company/user/basket/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="deliveryaddress"
            action = $action
        }

        "/admin/company/user/payment/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="deliveryaddress"
            action = $action
        }

    }
}
