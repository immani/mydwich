class RestaurantUrlMappings {

	static mappings = {

        "/admin/restaurant/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="restaurant"
            action = $action
        }

        "/admin/restaurant/menu/$id"{
            constraints {
                // apply constraints here
            }
            controller="product"
            action = "showrestaurantcatalog"

        }


        "/admin/restaurant/product/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="product"
            action = $action
        }

        "/admin/restaurant/product_category/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="productcategory"
            action = $action
        }

        "/admin/restaurant/product_option_category/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="prodoptioncategory"
            action = $action
        }

        "/admin/restaurant/product_option/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="prodoption"
            action = $action
        }

    }
}
