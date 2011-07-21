package com.immani.mydwich

import org.hibernate.Session
import org.hibernate.Query

class DeliveryAddress implements Serializable{

    def sessionFactory

    String name
	String address
	String zip
	String city
	String country = "Belgium"
    Float lat
    Float lng

    static belongsTo = [company:Company]

    static hasMany = [partnerships:Partnership]

    static constraints = {
		name(nullable: false, blank: false)
		address(nullable: false, blank: false)
		zip(nullable: false, blank: false)
		city(nullable: false, blank: false)
		country(nullable: false, blank: false)
        lat(nullable: true)
        lng(nullable: true)
    }

    //TODO: Exclude partnerships already made  and external mapped query
    def retrieveNearbyRestaurants(){
       Session currentSession = sessionFactory.currentSession;
       Query query= currentSession.createSQLQuery("select restaurant.* from delivery_address, restaurant where delivery_address.id = (?1) and distance(delivery_address.lat, delivery_address.lng, restaurant.lat, restaurant.lng) <= restaurant.deliveryrange;")
       query.setParameterList("1", this);
       def restaurantList = query.addEntity(Restaurant.class).list()
       return restaurantList
   }


    // TODO: Not used or tested yet
    def retrieveNearbyRestaurants(float radius){
       Session currentSession = sessionFactory.currentSession;
       def restaurantList = currentSession.createSQLQuery("select restaurant.* from delivery_address, restaurant where delivery_address.id = (?1)  and distance(delivery_address.lat, delivery_address.lng, restaurant.lat, restaurant.lng) <=  (?2)").addEntity(Restaurant.class).list()
       query.setParameterList("1", this);
       query.setParameterList("2", radius);
       return restaurantList
   }

    String toString(){
		return "${name} - ${address} , ${zip} ${city}, ${country}"
	}

}