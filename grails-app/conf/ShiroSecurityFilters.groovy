import com.immani.mydwich.User
import org.apache.shiro.SecurityUtils

/**
 * Generated by the Shiro plugin. This filters class protects all URLs
 * via access control by convention.
 */
class ShiroSecurityFilters {
    def filters = {

        notProduct(controller:'product') {
            return true
        }
        all(uri: "/**") {
            before = {
                // Ignore direct views (e.g. the default main index page).
                if (!controllerName) return true
                if(controllerName == "registration") return true

                // Access control by convention.
                accessControl()
            }
        }
    }
}
