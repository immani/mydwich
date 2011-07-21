package com.immani.mydwich

import org.hibernate.Query

class Company implements Serializable{

    def sessionFactory

    String name
    String address
    String zip
    String city
    String country = "Belgium"
    String vat
    String phone
    String fax
    String domain
    Float lat
    Float lng
    Boolean isvalidated = false

    static hasMany = [users:User,
            deliveryAddresses:DeliveryAddress]

    static constraints = {
        name(nullable: false,blank: false)
        address(nullable: false,blank: false)
        zip(nullable: false,blank: false)
        city(nullable: false,blank: false)
        country(nullable: false,blank: false)
        vat(nullable: false,blank: false)
        phone(nullable: true, blank: true)
        fax(nullable: true, blank: true)
        domain(nullable:false, blank: false, unique: true)
        isvalidated(nullable: false)  //TODO:Regex pour valider domain name
        lat(nullable: true)
        lng(nullable: true)
    }

    String toString(){
        return name
    }

    //TODO: Exclude partnerships already made
    def retrieveNearbyRestaurants(){
        def currentSession = sessionFactory.currentSession
        Query query = currentSession.createSQLQuery("select restaurant.* from delivery_address, restaurant where delivery_address.id in (?1) and distance(delivery_address.lat, delivery_address.lng, restaurant.lat, restaurant.lng) <= restaurant.deliveryrange;");
        query.setParameterList("1", this.deliveryAddresses);
        return query.addEntity(Restaurant.class).list();
    }

    def retrieveRequestedPartnerships(){
       def currentSession = sessionFactory.currentSession
       Query query = currentSession.createSQLQuery("select partnership.* from partnership where delivery_address_id in (?1) and isvalidated = false and originator='company'");
       query.setParameterList("1", deliveryAddresses);
       return query.addEntity(Partnership.class).list();
    }

    def retrieveWaitingPartnerships(){
       def currentSession = sessionFactory.currentSession
       Query query = currentSession.createSQLQuery("select partnership.* from partnership where delivery_address_id in (?1) and isvalidated = false and originator='restaurant'");
       query.setParameterList("1", deliveryAddresses);
       return query.addEntity(Partnership.class).list();
    }

    def retrieveValidatedPartnerships(){
       def currentSession = sessionFactory.currentSession
       Query query = currentSession.createSQLQuery("select partnership.* from partnership where delivery_address_id in (?1) and isvalidated = true");
       query.setParameterList("1", deliveryAddresses);
       return query.addEntity(Partnership.class).list();
    }



}
