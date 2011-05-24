class RestaurantUrlMappings {

	static mappings = {

        "/restaurant/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="restaurant"
            action = $action
        }

        "/restaurant/user/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="user"
            action = $action
        }

        "/product/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="product"
            action = $action
        }

        "/product_category/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="productcategory"
            action = $action
        }

        "/product_option_category/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="prodoptioncategory"
            action = $action
        }

        "/product_option/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="prodoption"
            action = $action
        }

    }
}
