class UserUrlMappings {

	static mappings = {

        "/user/profile/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="user"
            action = $action
        }

        "/user/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="user_company"
            action = $action
        }
    }
}
