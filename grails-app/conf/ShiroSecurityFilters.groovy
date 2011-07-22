import org.apache.shiro.SecurityUtils
import com.immani.mydwich.*
/**
 * Generated by the Shiro plugin. This filters class protects all URLs
 * via access control by convention.
 */
class ShiroSecurityFilters {
    String loccontrollerName
    String locactionName
    String locinstanceid

    //TODO: Check if we can retrieve these variables in myAccess without setting them...

    def filters = {
        /*
        notProduct(controller: 'anonymous_Product') {
            return true
        }

        notRestaurant(controller: 'anonymous_Restaurant') {
            return true
        }
        */

        all(uri: "/**") {
            before = {
                // Ignore direct views (e.g. the default main index page).
                if (!controllerName) return true
                if(controllerName == "user_company") return true
                if(controllerName == "anonymous_Product") return true
                if(controllerName == "anonymous_Restaurant") return true
                if(controllerName == "home") return true
                if(controllerName == "registration") return true
                if(controllerName == "emailConfirmation") return true  //Used by plugin Email Confirmation

                // Access control by convention.
                loccontrollerName = controllerName
                locactionName = actionName
                locinstanceid = params.id
                accessControl(myAccess)

            }
        }
    }

    def myAccess = {
        // immani.com:Domain:actions:id
        if (locinstanceid == null){
            //In this case we don't need to check anything as the user isn't accessing any specific Instance
            return true
        }
        //TODO:Check how we can call the closure without having to define local variable  locactionName, loccontrollerName
        User user = User.findByUsername(SecurityUtils.getSubject().principal.toString())
        // Check that the user has the required permission for the target controller/action.
        def permString = new StringBuilder()

        //Schema is: companydomain Or Restaurantid:username:controllername:actionname
        switch (loccontrollerName){


            case "company":
                //user can manipulate it's company and only
                Company company = Company.get(locinstanceid)
                permString << company.domain << ':*:' << loccontrollerName << ':' << (locactionName ?: "index")
                break

            case "deliveryaddress":
                //user can manipulate it's company and only
                DeliveryAddress da = DeliveryAddress.get(locinstanceid)
                permString << da.company.domain << ':' << da.id << ':' << loccontrollerName << ':' << (locactionName ?: "index")
                break

            case "restaurant":
                //user can manipulate it's restaurant and only
                Restaurant restaurant = Restaurant.get(locinstanceid)
                permString << restaurant.id << ':*:' << loccontrollerName << ':' << (locactionName ?: "index")
                break

            case "product":
                //user can manipulate it's restaurant and only
                Product product = Product.get(locinstanceid)
                permString << product.restaurant.id << ':*:' << loccontrollerName << ':' << (locactionName ?: "index")
                break

            case "picture":
                //user can manipulate it's restaurant and only
                Picture picture = Picture.get(locinstanceid)
                permString << picture.restaurant.id << ':*:' << loccontrollerName << ':' << (locactionName ?: "index")
                break

            case "prodoptioncategory":
                //user can manipulate it's restaurant and only
                ProdOptionCategory prodoptioncat =  ProdOptionCategory.get(locinstanceid)
                permString << prodoptioncat.restaurant.id << ':*:' << loccontrollerName << ':' << (locactionName ?: "index")
                break

            case "prodoption":
                //user can manipulate it's restaurant and only
                ProdOption prodoption = ProdOption.get(locinstanceid)
                permString << prodoption.prodOptionCategory.restaurant.id << ':*:' << loccontrollerName << ':' << (locactionName ?: "index")
                break

            case "productcategory":
                //user can manipulate it's restaurant and only
                ProductCategory productCategory = ProductCategory.get(locinstanceid)
                permString << productCategory.restaurant.id << ':*:' << loccontrollerName << ':' << (locactionName ?: "index")
                break

            case "partnership":
                if (user.company != null){
                    permString << user.company.domain << ':*:' << loccontrollerName << ':' << (locactionName ?: "index")
                }
                else{
                    permString << user.restaurant.id << ':*:' << loccontrollerName << ':' << (locactionName ?: "index")
                }
                break

            case ["user"]:
                User destusr = User.get(locinstanceid)
            if (user.company != null){
                    permString << destusr.company.domain << ':' << destusr.username << ':' << loccontrollerName << ':' << (locactionName ?: "index")
                }
                else{
                    permString << destusr.restaurant.id << ':' << destusr.username << ':' << loccontrollerName << ':' << (locactionName ?: "index")
                }
                break

            case "basket":
                Basket basket = Basket.get(locinstanceid)
                permString << basket.user.company.id << ':' << basket.user.username << ':' << loccontrollerName << ':' << (locactionName ?: "index")
                break

            case "basketline":
                BasketLine basketline = BasketLine.get(locinstanceid)
                permString << basketline.basket.user.company.id << ':' << basketline.basket.user.username << ':' << loccontrollerName << ':' << (locactionName ?: "index")
                break

            case "userpayment":
                Userpayment userpayment = Userpayment.get(locinstanceid)
                permString << userpayment.user.company.id << ':' << userpayment.user.username << ':' << loccontrollerName << ':' << (locactionName ?: "index")
                break

            default:
                false
        }
        println "permString: " + permString.toString()
        println "Permission: " + user.permissions.join('##')
        return SecurityUtils.subject.isPermitted(permString.toString())

    }
}
