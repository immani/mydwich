import com.immani.mydwich.*
import org.apache.shiro.crypto.hash.Sha256Hash
import grails.converters.deep.JSON

class BootStrap {
    def geocoderService
    def emailConfirmationService
    def init = { servletContext ->

        emailConfirmationService.onConfirmation = { email, uid ->
            log.info("User with id $uid has confirmed their email address $email")
            User user = User.get(uid)

            if (user.company != null) {
                if (user.isadmin) {
                    Company company = user.company
                    company.isvalidated = true
                    user.isvalidated = true
                    company.save()
                    user.save()
                    return [controller: 'company', action: 'show']
                }
                else {
                    user.isvalidated = true
                    user.save()
                    return [controller: 'user', action: 'show']


                }
            } else if (user.restaurant != null) {
                Restaurant restaurant = user.restaurant
                restaurant.isvalidated = true
                user.isvalidated = true
                restaurant.save()
                user.save()
                return [controller: 'restaurant', action: 'show']
            }

            // now do something…
            // Then return a map which will redirect the user to this destination

        }
        emailConfirmationService.onInvalid = { uid ->
            log.warn("User with id $uid failed to confirm email address after 30 days")
        }
        emailConfirmationService.onTimeout = { email, uid ->
            log.warn("User with id $uid failed to confirm email address after 30 days")
        }


        JSON.registerObjectMarshaller(Restaurant) {
            def returnArray = [:]
            returnArray['url'] = it.name.encodeAsURL()
            returnArray['name'] = it.name
            returnArray['address'] = it.address
            returnArray['zip'] = it.zip
            returnArray['city'] = it.city
            returnArray['country'] = it.country
            returnArray['lat'] = it.lat
            returnArray['lng'] = it.lng
            returnArray
        }

        JSON.registerObjectMarshaller(Company) {
            def returnArray = [:]
            returnArray['url'] = it.name.encodeAsURL()
            returnArray['name'] = it.name
            returnArray['address'] = it.address
            returnArray['zip'] = it.zip
            returnArray['city'] = it.city
            returnArray['country'] = it.country
            returnArray['lat'] = it.lat
            returnArray['lng'] = it.lng
            returnArray
        }

        JSON.registerObjectMarshaller(DeliveryAddress) {
            def returnArray = [:]
            returnArray['url'] = it.name.encodeAsURL()
            returnArray['name'] = it.name
            returnArray['address'] = it.address
            returnArray['zip'] = it.zip
            returnArray['city'] = it.city
            returnArray['country'] = it.country
            returnArray['lat'] = it.lat
            returnArray['lng'] = it.lng
            returnArray
        }
        /*
        Role companyAdminRole = new Role(name:"companyadmin")
        companyAdminRole.addToPermissions("*:*")
        companyAdminRole.save()
        Role companyRole = new Role(name: "company")
        //companyRole.addToPermissions("*:*")
        companyRole.save()
        Role restaurantAdminRole = new Role(name:"restaurantadmin")
        restaurantAdminRole.addToPermissions("*:*")
        restaurantAdminRole.save()
        Role restaurantRole = new Role(name: "restaurant")
        restaurantRole.addToPermissions("*:*")
        restaurantRole.save()
        */

        //DeliveryAddresses
        DeliveryAddress pixwavre = new DeliveryAddress(
                name: "PIX-Wavre",
                address: "120J chaussée de Huy",
                zip: "1300",
                city: "Wavre",
                country: "Belgium"
        )
        def results = geocoderService.geocode(pixwavre.address, pixwavre.zip, pixwavre.city, pixwavre.country )
        pixwavre.lat = results.lat
        pixwavre.lng = results.lng

        DeliveryAddress pixgent = new DeliveryAddress(
                name: "PIX-Gent",
                address: "25 Derbystraat (Maaltecenter Blok C)",
                zip: "B-9051",
                city: "Gent",
                country: "Belgium"
        )
        results = geocoderService.geocode(pixgent.address, pixgent.zip, pixgent.city, pixgent.country )
        pixgent.lat = results.lat
        pixgent.lng = results.lng

        DeliveryAddress immanibxl = new DeliveryAddress(
                name: "Immani Bruxelles",
                address: "314 Av couronne",
                zip: "1050",
                city: "Bruxelles",
                country: "Belgium"
        )
        results = geocoderService.geocode(immanibxl.address, immanibxl.zip, immanibxl.city, immanibxl.country )
        immanibxl.lat = results.lat
        immanibxl.lng = results.lng

        DeliveryAddress immaniwavre = new DeliveryAddress(
                name: "Immani Wavre",
                address: "100 Chaussée de Huy",
                zip: "1300",
                city: "Wavre",
                country: "Belgium"
        )
        results = geocoderService.geocode(immaniwavre.address, immaniwavre.zip, immaniwavre.city, immaniwavre.country )
        immaniwavre.lat = results.lat
        immaniwavre.lng = results.lng

        //Companies
        Company immani = new Company(
                name: "Immani",
                domain: "immani.com",
                address: "328 av de la Couronne",
                zip: "1050",
                city: "Ixelles",
                country: "Belgium",
                vat: "878.317.974",
                phone: "0123456789",
                fax: "0123456789",
        )
        immani.addToDeliveryAddresses(immanibxl)
        immani.addToDeliveryAddresses(immaniwavre)
        results = geocoderService.geocode(immani.address, immani.zip, immani.city, immani.country )
        immani.lat = results.lat
        immani.lng = results.lng
        immani.save();
        if(immani.hasErrors()){
            println immani.errors
        }

        Company pixelixir = new Company(
                name: "PIXELIXIR",
                domain: "pixelixir.com",
                address: "120J Chaussée de Huy",
                zip: "1300",
                city: "Wavre",
                country: "Belgium",
                vat: "888888888",
                phone: "010231781",
                fax: "010231788"
        )

        pixelixir.addToDeliveryAddresses(pixwavre)
        pixelixir.addToDeliveryAddresses(pixgent)
        results = geocoderService.geocode(pixelixir.address, pixelixir.zip, pixelixir.city, pixelixir.country )
        pixelixir.lat = results.lat
        pixelixir.lng = results.lng
        pixelixir.save()
        if(pixelixir.hasErrors()){
            println pixelixir.errors
        }

        //Restaurants Categories
        def sandwicherie = new RestaurantCategory(
                name_fr: "Sandwicherie",
                name_en: "Sandwichery",
                name_nl: "Sandwichery"
        )
        sandwicherie.save()
        if(sandwicherie.hasErrors()){
            println sandwicherie.errors
        }

        def pizzeria = new RestaurantCategory(
                name_fr: "Pizzeria",
                name_en: "Pizzeria",
                name_nl: "Pizzeria"
        )
        pizzeria.save()
        if(pizzeria.hasErrors()){
            println pizzeria.errors
        }

        //Restaurants
        def aduepassi = new Restaurant(
                restaurantcategories: [pizzeria, sandwicherie],
                name: "A Due Passi",
                address: "Chaussèe de Huy 18",
                zip: "1325",
                city: "Chaumont-Gistoux",
                country: "Belgium",
                vat: "878.317.974",
                phone: "0123456789",
                fax: "0123456789",
                deliveryrange:5.8,
                desc_fr: "restaurant italien",
                desc_nl: "restaurant italien nl",
                desc_en: "Italian restaurant"
        )
        results = geocoderService.geocode(aduepassi.address, aduepassi.zip, aduepassi.city, aduepassi.country )
        aduepassi.lat = results.lat
        aduepassi.lng = results.lng
        aduepassi.save()
        if(aduepassi.hasErrors()){
            println aduepassi.errors
        }

        def lepaindesoleil = new Restaurant(
                restaurantcategories: [sandwicherie],
                name: "le Pain de Soleil",
                address: "Avenue de la Couronne 372",
                zip: "1050",
                city: "Ixelles",
                country: "Belgium",
                vat: "888888888",
                phone: "010231781",
                fax: "010231788",
                desc_fr: "restaurant marocan",
                desc_nl: "restaurant marocan nl",
                desc_en: "marocan restaurant",
                deliveryrange:1.12
        )
        def results2 = geocoderService.geocode(lepaindesoleil.address, lepaindesoleil.zip, lepaindesoleil.city, lepaindesoleil.country )
        lepaindesoleil.lat = results2.lat
        lepaindesoleil.lng = results2.lng
        lepaindesoleil.save()
        if(lepaindesoleil.hasErrors()){
            println lepaindesoleil.errors
        }

        def rest = new Restaurant(restaurantcategories: [sandwicherie], name: "Kmie", address: "Avenue Adolphe Buyl 16", zip: "1050", city: "Ixelles", country: "Belgium", vat: "888888888",phone: "010231781",fax: "010231788",desc_fr: " fr", desc_nl: "nl",desc_en: " en", deliveryrange:4.4)
        results = geocoderService.geocode(rest.address, rest.zip, rest.city, rest.country )
        rest.lat = results.lat
        rest.lng = results.lng
        rest.save()
        if(rest.hasErrors()){
            println rest.errors
        }

        rest = new Restaurant(restaurantcategories: [sandwicherie], name: "Le Zest", address: "Fernand Cocqplein 22", zip: "1050", city: "Ixelles", country: "Belgium", vat: "888888888",phone: "010231781",fax: "010231788", desc_fr: " fr", desc_nl: "nl",desc_en: " en",deliveryrange:3.2)
        results = geocoderService.geocode(rest.address, rest.zip, rest.city, rest.country )
        rest.lat = results.lat
        rest.lng = results.lng
        rest.save()
        if(rest.hasErrors()){
            println rest.errors
        }

        rest = new Restaurant(restaurantcategories: [sandwicherie], name: "Chez Marie", address: "Rue Alphonse De Witte 40", zip: "1050", city: "Ixelles", country: "Belgium", vat: "888888888",phone: "010231781",fax: "010231788", desc_fr: " fr", desc_nl: "nl",desc_en: " en",deliveryrange:6.1)
        results = geocoderService.geocode(rest.address, rest.zip, rest.city, rest.country )
        rest.lat = results.lat
        rest.lng = results.lng
        rest.save()
        if(rest.hasErrors()){
            println rest.errors
        }

        rest = new Restaurant(restaurantcategories: [sandwicherie], name: "Rouge Tomate", address: "Avenue Louise 190", zip: "1050", city: "Ixelles", country: "Belgium", vat: "888888888",phone: "010231781",fax: "010231788", desc_fr: " fr", desc_nl: "nl",desc_en: " en",deliveryrange:40.7)
        results = geocoderService.geocode(rest.address, rest.zip, rest.city, rest.country )
        rest.lat = results.lat
        rest.lng = results.lng
        rest.save()
        if(rest.hasErrors()){
            println rest.errors
        }

        rest = new Restaurant(restaurantcategories: [sandwicherie], name: "Le Grain de Sel", address: "Chaussée de Vleurgat 9", zip: "1050", city: "Ixelles", country: "Belgium", vat: "888888888",phone: "010231781",fax: "010231788", desc_fr: " fr", desc_nl: "nl",desc_en: " en",deliveryrange:40.3)
        results = geocoderService.geocode(rest.address, rest.zip, rest.city, rest.country )
        rest.lat = results.lat
        rest.lng = results.lng
        rest.save()
        if(rest.hasErrors()){
            println rest.errors
        }

        //Partnership
        Partnership partnership = new Partnership(
                deliveryAddress: immanibxl,
                restaurant: lepaindesoleil,
                isvalidated: true,
                originator: "company",
                comment: "svp moi vouloir commander chez vous"
        )
        partnership.save()

        Partnership partnership2 = new Partnership(
                deliveryAddress: immaniwavre,
                restaurant: aduepassi,
                isvalidated: true,
                originator: "restaurant",
                comment: "venez manger chez nous :-)"
        )
        partnership2.save()

        //User creation
        // Creating a company user
        def companyadminuser = new User(
                username: "nicolas@immani.com",
                firstname: "Nicolas",
                lastname: "Germeau",
                sex:"Male",
                passwordHash: new Sha256Hash("nicolas").toHex(),
                company: immani,
                isadmin: true,
                language: 'nl',
                isvalidated: true);
        companyadminuser.save()
        if(companyadminuser.hasErrors()){
            println companyadminuser.errors
        }

        def companyuser = new User(
                username: "marie@immani.com",
                firstname: "Marie",
                lastname: "Deronchène",
                sex:"Female",
                passwordHash: new Sha256Hash("marie").toHex(),
                company: immani,
                isadmin: false,
                language: 'fr',
                isvalidated: true);
        companyuser.save()
        if(companyuser.hasErrors()){
            println companyuser.errors
        }

        def companyuser2 = new User(
                username: "thomas@immani.com",
                firstname: "Thomas",
                lastname: "Wellens",
                sex:"Male",
                passwordHash: new Sha256Hash("thomas").toHex(),
                company: immani,
                isadmin: false,
                language: 'fr',
                defaultda: immanibxl,
                isvalidated: true);
        companyuser2.save()
        if(companyuser2.hasErrors()){
            println companyuser2.errors
        }

        // Creating a restaurant user
        def restaurantadminuser = new User(
                username: "thierry@immani.com",
                firstname: "Thierry",
                lastname: "Soubestre",
                sex:"Male",
                passwordHash: new Sha256Hash("thierry").toHex(),
                restaurant: lepaindesoleil,
                isadmin: true,
                language: 'fr',
                defaultda: immanibxl,
                isvalidated: true);
        restaurantadminuser.save()
        if(restaurantadminuser.hasErrors()){
            println restaurantadminuser.errors
        }

        // Creating a restaurant user
        def restaurantuser = new User(
                username: "olivier@immani.com",
                firstname: "Olivier",
                lastname: "Soubestre",
                sex:"Male",
                passwordHash: new Sha256Hash("olivier").toHex(),
                restaurant: lepaindesoleil,
                isadmin: false,
                language: 'fr',
                isvalidated: true);
        restaurantuser.save()
        if(restaurantuser.hasErrors()){
            println restaurantuser.errors
        }


        //Options

        //ProductTags
        def bio = new ProductTag(name_fr: "Bio", name_nl: "Bio", name_en: "Bio")
        bio.save()
        if(bio.hasErrors()){
            println bio.errors
        }

        def sante = new ProductTag(name_fr: "Sante", name_nl: "Santeit", name_en: "Healthy")
        sante.save()
        if(sante.hasErrors()){
            println sante.errors
        }



    }


    def destroy = {

    }
}
