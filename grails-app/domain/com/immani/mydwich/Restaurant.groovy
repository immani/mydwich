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
       return query.addEntity(DeliveryAddress.class).list()
    }

      def retrieveRequestedPartnerships(){
       def currentSession = sessionFactory.currentSession
       Query query = currentSession.createSQLQuery("select partnership.* from partnership where restaurant_id = (?1) and isvalidated = false and originator='restaurant'");
       query.setParameterList("1", this);
       return query.addEntity(Partnership.class).list();
    }

    def retrieveWaitingPartnerships(){
       def currentSession = sessionFactory.currentSession
       Query query = currentSession.createSQLQuery("select partnership.* from partnership where restaurant_id = (?1) and isvalidated = false and originator='company'");
       query.setParameterList("1", this);
       return query.addEntity(Partnership.class).list();
    }

    def retrieveValidatedPartnerships(){
       def currentSession = sessionFactory.currentSession
       Query query = currentSession.createSQLQuery("select partnership.* from partnership where restaurant_id = (?1) and isvalidated = true");
       query.setParameterList("1", this);
       return query.addEntity(Partnership.class).list();
    }


	String toString(){ 
		return name
	}
}
