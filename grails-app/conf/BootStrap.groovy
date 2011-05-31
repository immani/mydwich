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
            if (user.roles.contains("companyAdminRole")){
                Company company = user.company
                company.isvalidated = true
                user.isvalidated = true
                return [controller:'company', action:'show']
            }
            if (user.roles.contains("companyRole")){
                user.isvalidated = true
                return [controller:'user', action:'show']
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

        Role companyAdminRole = new Role(name:"companyadmin")
        companyAdminRole.save()
        Role companyRole = new Role(name: "company")
        Role restaurantAdminRole = new Role(name:"restaurantadmin")
        restaurantAdminRole.save()
        Role restaurantRole = new Role(name: "restaurant")

        //DeliveryAddresses
        DeliveryAddress pixwavre = new DeliveryAddress(
                name: "PIX-Wavre",
                address: "120J chaussée de Huy",
                zip: "1300",
                city: "Wavre",
                country: "Belgium"
        )

        DeliveryAddress pixgent = new DeliveryAddress(
                name: "PIX-Gent",
                address: "25 Derbystraat (Maaltecenter Blok C)",
                zip: "B-9051",
                city: "Gent",
                country: "Belgium"
        )

        DeliveryAddress immanibxl = new DeliveryAddress(
                name: "Immani Bruxelles",
                address: "314 Av couronne",
                zip: "1050",
                city: "Bruxelles",
                country: "Belgium"
        )
        def results = geocoderService.geocode(immanibxl.address, immanibxl.zip, immanibxl.city, immanibxl.country )
        immanibxl.lat = results.lat
        immanibxl.lng = results.lng

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

        //User creation

        // Creating a company user
        def companyuser = new User(
                username: "nicolas@immani.com",
                firstname: "Nicolas",
                lastname: "Germeau",
                title: "Mr",
                passwordHash: new Sha256Hash("nicolas").toHex(),
                company: immani,
                roles: [companyRole],
                language: 'nl');
        companyuser.addToPermissions("*:*")
        companyuser.save()
        if(companyuser.hasErrors()){
            println companyuser.errors
        }

        // Creating a restaurant user
        def restaurantuser = new User(
                username: "thierry@immani.com",
                firstname: "Thierry",
                lastname: "Soubestre",
                title: "Mr",
                passwordHash: new Sha256Hash("thierry").toHex(),
                restaurant: lepaindesoleil,
                roles: [restaurantRole],
                language: 'fr');
        restaurantuser.addToPermissions("*:*")
        restaurantuser.save()
        if(restaurantuser.hasErrors()){
            println restaurantuser.errors
        }

        //Product Category
        def sandwich = new ProductCategory(
                restaurant: lepaindesoleil, name_fr: "Sandwichs", name_en: "Sandwichs", name_nl: "Sandwichs"
        )
        sandwich.save()
        if(sandwich.hasErrors()){
            println sandwich.errors
        }

        def saladecat = new ProductCategory(
                restaurant: lepaindesoleil, name_fr: "Salade", name_en: "Salad", name_nl: "Salad"
        )
        saladecat.save()
        if(saladecat.hasErrors()){
            println saladecat.errors
        }

        def boisson = new ProductCategory(
                restaurant: lepaindesoleil, name_fr: "Boissons", name_en: "Drinks", name_nl: "Drinken"
        )
        boisson.save()
        if(boisson.hasErrors()){
            println boisson.errors
        }

        def pizza = new ProductCategory(
                restaurant: aduepassi, name_fr: "Pizza", name_en: "Pizza", name_nl: "Pizza"
        )
        pizza.save()
        if(pizza.hasErrors()){
            println pizza.errors
        }

        def dessert = new ProductCategory(
                restaurant: aduepassi, name_fr: "Dessert", name_en: "Dessert", name_nl: "Desserten"
        )
        dessert.save()
        if(dessert.hasErrors()){
            println dessert.errors
        }

        //Option Categories & Options
        def typedepain = new ProdOptionCategory(
                productCategories: [sandwich],
                restaurant: lepaindesoleil,
                name_fr: "type de pain",
                name_nl: "type broode",
                name_en: "bread type", type: "radio"
        )
        sandwich.addToProdOptionCategories(typedepain)
        lepaindesoleil.addToProdOptionCategories(typedepain)

        def painblanc = new ProdOption(name_fr: "Pain blanc", name_nl: "Witte broode", name_en: "White bread", price: 0.3)
        def paingris = new ProdOption(name_fr: "Pain gris", name_nl: "grijs broode", name_en: "Grey bread", price: 0.3)
        def fitness = new ProdOption(name_fr: "Fitness", name_nl: "Fitness", name_en: "Fitness", price: 0.3)
        typedepain.addToOptions(painblanc)
        typedepain.addToOptions(paingris)
        typedepain.addToOptions(fitness)


        typedepain.save()
        if(typedepain.hasErrors()){
            println typedepain.errors
        }


        def garniture = new ProdOptionCategory(
                productCategories: [sandwich],
                restaurant: lepaindesoleil,
                name_fr: "Garniture",
                name_nl: "Garniture_NL",
                name_en: "Garniture",
                type: "checkbox"
        )
        sandwich.addToProdOptionCategories(garniture)
        lepaindesoleil.addToProdOptionCategories(typedepain)
        //   aduepassi.addToProdOptionCategories(typedepain)

        def cornichon = new ProdOption(name_fr: "Cornichon", name_nl: "cornichon_nl", name_en: "pickles", price: 0.3)
        def salade = new ProdOption(name_fr: "Salade", name_nl: "Salad", name_en: "Salad", price: 0.3)
        def oeufdur = new ProdOption(name_fr: "Oeuf dur", name_nl: "Ei", name_en: "Hard boiled egg", price: 0.3)
        def anchois = new ProdOption(name_fr: "Anchois", name_nl: "anchois_nl", name_en: "Anchovies", price: 0.3)
        garniture.addToOptions(cornichon)
        garniture.addToOptions(salade)
        garniture.addToOptions(oeufdur)
        garniture.addToOptions(anchois)
        garniture.save()
        if(garniture.hasErrors()){
            println garniture.errors
        }

        def sauce = new ProdOptionCategory(
                productCategories: [sandwich],
                restaurant: lepaindesoleil,
                name_fr: "Sauce",
                name_nl: "Saus",
                name_en: "Sauce",
                type: "checkbox"
        )
        //   sandwich.addToProdOptionCategories(sauce)
        //   lepaindesoleil.addToProdOptionCategories(typedepain)
        def andalouse = new ProdOption(name_fr: "Andalouse", name_nl: "Andalous", name_en: "Andalouse", price: 0.3)
        def moutarde = new ProdOption(name_fr: "Moutarde", name_nl: "Mustard", name_en: "Mustard", price: 0.3)
        def blanche = new ProdOption(name_fr: "Blanche", name_nl: "Witte", name_en: "White", price: 0.3)
        sauce.addToOptions(andalouse)
        sauce.addToOptions(moutarde)
        sauce.addToOptions(blanche)
        sauce.save()
        if(sauce.hasErrors()){
            println sauce.errors
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

        //Products
        Product americain = new Product(
                restaurant: lepaindesoleil,
                productTags: [bio],
                productCategories: [sandwich],
                name_fr: "Sandwich Américain", name_nl: "Americain Sandwich", name_en: "American Sandwich",
                desc_fr: "Un Sandwich Américain...", desc_nl: "Ein Americain Sandwich...", desc_en: "An American Sandwich...",
                price: 4
        )
        americain.save()
        if(americain.hasErrors()){
            println americain.errors
        }

        Product pouletcurry = new Product(
                restaurant: lepaindesoleil,
                productTags: [bio],
                productCategories: [sandwich],
                name_fr: "Poulet Curry", name_nl: "Kip Curry", name_en: "Chicken Curry",
                desc_fr: "Un Poulet Curry...", desc_nl: 'Ein Kip Curry...', desc_en: "A Chicken Curry...",
                price: 4
        )
        pouletcurry.save()
        if(pouletcurry.hasErrors()){
            println pouletcurry.errors
        }

        Product club = new Product(
                restaurant: lepaindesoleil,
                productTags: [bio],
                productCategories: [sandwich],
                name_fr: "Club", name_nl: "Club", name_en: "Club",
                desc_fr: "Un Club...", desc_nl: 'Ein Club...', desc_en: "A Club...",
                price: 3.5
        )
        club.save()
        if(club.hasErrors()){
            println club.errors
        }

        Product coca = new Product(
                restaurant: lepaindesoleil,
                productCategories: [boisson],
                name_fr: "Coca", name_nl: "Coca", name_en: "Coca",
                desc_fr: "Un Coca...", desc_nl: 'Ein Coca...', desc_en: "A Coca...",
                price: 1
        )
        coca.save()
        if(coca.hasErrors()){
            println coca.errors
        }

        Product fanta = new Product(
                restaurant: lepaindesoleil,
                productCategories: [boisson],
                name_fr: "fanta", name_nl: "fanta", name_en: "fanta",
                desc_fr: "Un fanta...", desc_nl: 'Ein fanta...', desc_en: "A fanta...",
                price: 1
        )
        fanta.save()
        if(fanta.hasErrors()){
            println fanta.errors
        }

        Product cesar = new Product(
                restaurant: lepaindesoleil,
                productCategories: [boisson],
                name_fr: "cesar", name_nl: "cesar", name_en: "cesar",
                desc_fr: "Un cesar...", desc_nl: 'Ein cesar...', desc_en: "A cesar...",
                price: 1
        )
        cesar.save()
        if(cesar.hasErrors()){
            println cesar.errors
        }

    }


    def destroy = {

    }
}
