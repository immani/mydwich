package com.immani.mydwich

import groovy.sql.Sql
import org.hibernate.Session
import org.hibernate.Query

class CompanyService {

    def dataSource
    static transactional = true

    def sessionFactory

   // TODO: All the queries here can be set in hibrnate named queries
   def searchnearbyrestaurant(int deliveryAddressid, float radius){
       Session currentSession = sessionFactory.currentSession;
       def restaurantList = currentSession.createSQLQuery("select restaurant.* from delivery_address, restaurant where delivery_address.id = ${deliveryAddressid}  and distance(delivery_address.lat, delivery_address.lng, restaurant.lat, restaurant.lng) <=  ${radius}").addEntity(Restaurant.class).list()
       return restaurantList
   }


   def searchdeliveryrestaurant(DeliveryAddress deliveryAddress){
       Session currentSession = sessionFactory.currentSession;
       Query query= currentSession.createSQLQuery("select restaurant.* from delivery_address, restaurant where delivery_address.id = ${deliveryAddress.id} and distance(delivery_address.lat, delivery_address.lng, restaurant.lat, restaurant.lng) <= restaurant.deliveryrange;").addEntity(Restaurant.class).list()
       return restaurantList
   }


    def searchdeliveryrestaurant(List<DeliveryAddress> deliveryAddresses){
        def currentSession = sessionFactory.currentSession

        Query query = currentSession.createSQLQuery("select restaurant.* from delivery_address, restaurant where delivery_address.id in (?1) and distance(delivery_address.lat, delivery_address.lng, restaurant.lat, restaurant.lng) <= restaurant.deliveryrange;");
        query.setParameterList("1", deliveryAddresses);

        def restaurantList = query.addEntity(Restaurant.class).list();
        return restaurantList;
    }


}
