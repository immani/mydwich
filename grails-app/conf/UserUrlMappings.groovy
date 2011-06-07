class UserUrlMappings {

	static mappings = {

        "/user/$action?/$id?"{
            constraints {
                // apply constraints here
            }
            controller="user"
            action = $action
        }
    }
}
