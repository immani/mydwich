package com.immani.mydwich

import org.hibernate.Session
import org.hibernate.Query

class Restaurant implements Serializable{

    def sessionFactory

    String name
	String address
	String zip
	String city
	String country = "Belgium"
	String vat
	String phone
	String fax
	String desc_fr
	String desc_nl
	String desc_en
    Float lat
    Float lng
    Float deliveryrange


    static hasMany = [users:User,
                      products:Product,
                      restaurantcategories:RestaurantCategory,
                      productsCategories: ProductCategory,
                      prodOptionCategories: ProdOptionCategory,
                      partnerships:Partnership,
                      pictures: Picture]

    static constraints = {
		name(nullable: false, blank: false)
		address(nullable: false, blank: false)
		zip(nullable: false, blank: false)
		city(nullable: false, blank: false)
		country(nullable: false, blank: false)
		vat(nullable: false, blank: false)
        phone(nullable: true, blank: true)
        fax(nullable: true, blank: true)
        lat(nullable: true)
        lng(nullable: true)
   }

     def retrieveNearbyDeliveryAddresses = {
       Session currentSession = sessionFactory.currentSession;
       Query query= currentSession.createSQLQuery("select delivery_address.* from delivery_address, restaurant where restaurant.id = :restaurantid and distance(delivery_address.lat, delivery_address.lng, restaurant.lat, restaurant.lng) <= restaurant.deliveryrange;")
       query.setParameter("restaurantid",this.id)
       query.addEntity(DeliveryAddress.class).list()
    }

      def retrieveRequestedPartnerships = {
       def currentSession = sessionFactory.currentSession
       Query query = currentSession.createSQLQuery("select partnership.* from partnership where restaurant_id = (?1) and isvalidated = false and originator='restaurant'");
       query.setParameterList("1", this);
       query.addEntity(Partnership.class).list();
    }

    def retrieveWaitingPartnerships = {
       def currentSession = sessionFactory.currentSession
       Query query = currentSession.createSQLQuery("select partnership.* from partnership where restaurant_id = (?1) and isvalidated = false and originator='company'");
       query.setParameterList("1", this);
       query.addEntity(Partnership.class).list();
    }

    def retrieveValidatedPartnerships = {
       def currentSession = sessionFactory.currentSession
       Query query = currentSession.createSQLQuery("select partnership.* from partnership where restaurant_id = (?1) and isvalidated = true");
       query.setParameterList("1", this);
       query.addEntity(Partnership.class).list();
    }


	String toString(){ 
		return name
	}


    def beforeInsert = {

        ProductCategory sandwich = new ProductCategory(name_fr: "Sandwichs", name_en: "Sandwichs en", name_nl: "Sandwichs nl",catorder:1);
        this.addToProductsCategories(sandwich)

        ProductCategory tartine = new ProductCategory(name_fr: "Tartines", name_en: "Tartines en", name_nl: "Tartines nl",catorder:2)
        this.addToProductsCategories(tartine)

        ProductCategory salade = new ProductCategory(name_fr: "Salades", name_en: "Salads en", name_nl: "Salads nl",catorder:3)
        this.addToProductsCategories(salade)

        ProductCategory platchaud = new ProductCategory(name_fr: "plats chaud", name_en: "Plats chaud en", name_nl: "Plats chaud nl",catorder:4)
        this.addToProductsCategories(platchaud)

        ProductCategory platfroid = new ProductCategory(name_fr: "plats froid", name_en: "Plats froid en", name_nl: "Plats froid nl",catorder:5)
        this.addToProductsCategories(platfroid)

        ProductCategory boisson = new ProductCategory(name_fr: "boisson", name_en: "boisson en", name_nl: "boisson nl",catorder:6)
        this.addToProductsCategories(boisson)

        ProductCategory dessert = new ProductCategory(name_fr: "Dessert", name_en: "Dessert", name_nl: "Desserten",catorder:7)
        this.addToProductsCategories(dessert)

        ProdOptionCategory garniture = new ProdOptionCategory(name_fr: "Garniture", name_en: "Garniture_en", name_nl: "Garniture_nl",type: "checkbox");
        this.addToProdOptionCategories(garniture)
        sandwich.addToProdOptionCategories(garniture)
        tartine.addToProdOptionCategories(garniture)

        ProdOptionCategory typedepain = new ProdOptionCategory(name_fr: "Type de pain", name_en: "Type de pain en", name_nl: "Type de pain nl",type: "checkbox");
        this.addToProdOptionCategories(typedepain)
        sandwich.addToProdOptionCategories(typedepain)
        tartine.addToProdOptionCategories(typedepain)

        ProdOptionCategory sauce = new ProdOptionCategory(name_fr: "Sauce", name_en: "Sauce en", name_nl: "sauce nl",type: "checkbox");
        this.addToProdOptionCategories(sauce)
        sandwich.addToProdOptionCategories(sauce)
        salade.addToProdOptionCategories(sauce)

        ProdOption cornichon = new ProdOption(name_fr: "Cornichon",name_en: "Pickles", name_nl: "Augurkje",price: 0.3);
        garniture.addToOptions(cornichon)

        ProdOption saladeoption = new ProdOption(name_fr: "Salade", name_nl: "Salad", name_en: "Salad", price: 0.3)
        garniture.addToOptions(saladeoption)

        ProdOption oeufdur = new ProdOption(name_fr: "Oeuf dur", name_nl: "Ei", name_en: "Hard boiled egg", price: 0.4)
        garniture.addToOptions(oeufdur)

        ProdOption anchois = new ProdOption(name_fr: "Anchois", name_nl: "anchois_nl", name_en: "Anchovies", price: 0.5)
        garniture.addToOptions(anchois)

        ProdOption painblanc = new ProdOption(name_fr: "Pain blanc", name_nl: "Witte broode", name_en: "White bread", price: 0.0)
        typedepain.addToOptions(painblanc)

        ProdOption paingris = new ProdOption(name_fr: "Pain gris", name_nl: "grijs broode", name_en: "Grey bread", price: 0.2)
        typedepain.addToOptions(paingris)

        ProdOption fitness = new ProdOption(name_fr: "Fitness", name_nl: "Fitness", name_en: "Fitness", price: 0.3)
        typedepain.addToOptions(fitness)

        ProdOption andalouse = new ProdOption(name_fr: "Andalouse", name_en: "Andalouse", name_nl: "Andalous", price: 0.2)
        sauce.addToOptions(andalouse)

        ProdOption moutarde = new ProdOption(name_fr: "Moutarde", name_en: "Mustard", name_nl: "Mustard", price: 0.2)
        sauce.addToOptions(moutarde)

        ProdOption blanche = new ProdOption(name_fr: "Blanche", name_en: "White", name_nl: "Witte", price: 0.2)
        sauce.addToOptions(blanche)

        // TODO: Remove this on the production environment (only for testing)

        Product americain = new Product(name_fr: "Sandwich Américain", name_nl: "Americain Sandwich", name_en: "American Sandwich", desc_fr: "Un Sandwich Américain...", desc_nl: "Ein Americain Sandwich...", desc_en: "An American Sandwich...",price: 4)
        this.addToProducts(americain)
        americain.addToProductCategories(sandwich)

        Product pouletcurry = new Product(name_fr: "Poulet Curry", name_nl: "Kip Curry", name_en: "Chicken Curry",desc_fr: "Un Poulet Curry...", desc_nl: 'Ein Kip Curry...', desc_en: "A Chicken Curry...",price: 4)
        this.addToProducts(pouletcurry)
        pouletcurry.addToProductCategories(sandwich)


        Product club = new Product(name_fr: "Club", name_nl: "Club", name_en: "Club", desc_fr: "Un Club...", desc_nl: 'Ein Club...', desc_en: "A Club...",price: 3.5)
        this.addToProducts(club)
        club.addToProductCategories(sandwich)

        Product coca = new Product(name_fr: "Coca", name_nl: "Coca", name_en: "Coca",desc_fr: "Un Coca...", desc_nl: 'Ein Coca...', desc_en: "A Coca...",price: 1)
        this.addToProducts(coca)
        coca.addToProductCategories(boisson)

        Product fanta = new Product(name_fr: "fanta", name_nl: "fanta", name_en: "fanta", desc_fr: "Un fanta...", desc_nl: 'Ein fanta...', desc_en: "A fanta...",price: 1)
        this.addToProducts(fanta)
        fanta.addToProductCategories(boisson)

        Product cesar = new Product(name_fr: "cesar", name_nl: "cesar", name_en: "cesar",desc_fr: "Un cesar...", desc_nl: 'Ein cesar...', desc_en: "A cesar...",price: 1)
        this.addToProducts(cesar)
        cesar.addToProductCategories(salade)
    }
}
