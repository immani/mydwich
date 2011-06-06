package com.immani.mydwich
import grails.converters.JSON
import org.apache.shiro.authz.AuthorizationException
import org.hibernate.collection.PersistentSet

class CompanyController {
    def geocoderService
    def companyService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        render(view: "index")
    }
    /**
     * @return returns the current user
     */
    private User currentuser(){
        return session.user.merge()
    }

    /**
     * Checks if a companyuser has to right to perform an action toward a company; ex: show, edit, update
     * @param companyInstance
     * @return
     */
    private boolean hasrights(Company companyInstance){
        if(currentuser().company == companyInstance){
            return true
        }
        throw new AuthorizationException("User is not allowed to perform that operation")
    }


     /**
     * Show the company of the current user
     */
    def show = {
        User user = session.user.merge()
        Company companyInstance = user.company
        if (!companyInstance) {
            flash.message = "User doesn't belong to a Company"
            render(view: "/info")
        }
        else {
            render(view: "show", model: [companyInstance: companyInstance])
        }
    }

    /**
     * Edit the company of the current user
     */
    def edit = {
        User user = session.user.merge()
        Company companyInstance = user.company
        if (!companyInstance) {
            flash.message = "User doesn't belong to a Company"
            render(view: "/info")
        }
        else {
            render(view: "edit", model: [companyInstance: companyInstance])
        }
    }

    /**
     * updates the company of the current user
     */
    def update = {
        def companyInstance = Company.get(params.id)
        if (companyInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (companyInstance.version > version) {
                    companyInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'company.label', default: 'Company')] as Object[], "Another user has updated this Company while you were editing")
                    render(view: "edit", model: [companyInstance: companyInstance])
                    return
                }
            }
            companyInstance.properties = params
            if (companyInstance.isDirty('address') || companyInstance.isDirty('zip') || companyInstance.isDirty('city') || companyInstance.isDirty('country') ){
                def results = geocoderService.geocode(params.address, params.zip, params.city, params.country )
                companyInstance.properties = params + results
            }
            if (!companyInstance.hasErrors() && companyInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'company.label', default: 'Company'), companyInstance.id])}"
                redirect(action: "show", id: companyInstance.id)
            }
            else {
                render(view: "edit", model: [companyInstance: companyInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'company.label', default: 'Company'), params.id])}"
            redirect(action: "list")
        }
    }


   /* def retrieverestaurantswithinrange = {
        //TODO: Limit info Returned
        User user = session.user.merge()
        Company companyInstance = user.company
        params.delta = Math.min(params.delta ? params.float('delta') : 0.02, 0.05)
        //Float Delta = params.delta
        Float CompanyLat = companyInstance.lat
        Float CompanyLng = companyInstance.lng

        def c = Restaurant.createCriteria()
        def restaurantlist= c.list {
            between('lat', (CompanyLat - params.delta).toFloat(), (CompanyLat + params.delta).toFloat())
            between('lng', (CompanyLng - params.delta).toFloat(), (CompanyLng + params.delta).toFloat())
            //            between("lng", CompanyLng + Delta, CompanyLng - Delta  )
        }
        render restaurantlist as JSON
    }  */

      def retrieverestaurantswithinrange = {
        User user = session.user.merge()
        Company companyInstance = user.company
        List deliveryAddresses= companyInstance.deliveryAddresses.asList()
        def restaurantList = companyService.searchdeliveryrestaurant(deliveryAddresses)
        render restaurantList as JSON
    }

     def searchdeliveryrestaurant = {
        companyService.searchdeliveryrestaurant(1)
     }

}
