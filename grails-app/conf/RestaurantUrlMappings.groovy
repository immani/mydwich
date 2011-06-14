class RestaurantUrlMappings {

	static mappings = {

        "/restaurant/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="restaurant"
            action = $action
        }

        "/restaurant/menu/$id"{
            constraints {
                // apply constraints here
            }
            controller="product"
            action = "showrestaurantcatalog"

        }

        "/showrestaurant/$name/$page?"{
            constraints {
                // apply constraints here
            }
            controller="restaurant"
            action = "getbyname"

        }

        "/product/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="product"
            action = $action
        }

        "/restaurant/product_category/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="productcategory"
            action = $action
        }

        "/restaurant/product_option_category/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="prodoptioncategory"
            action = $action
        }

        "/restaurant/product_option/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="prodoption"
            action = $action
        }

    }
}
