
-- --------------------------------------------------------------------------------
-- Routine DDL
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `distance`(companylat double,companylng double,restaurantlat double,restaurantlng double) RETURNS double
    DETERMINISTIC
BEGIN

  declare R double;
  declare dLat double;
  declare dLon double;
  declare a double;
  declare c double;
  declare d double;

  Set R = 6371;
  Set dlat= Radians(companylat-restaurantlat);
  Set dLon = Radians(companylng-restaurantlng);
  Set a = sin(dLat/2) * sin(dLat/2) + cos(Radians(companylat)) * cos(RAdians(restaurantlat)) * sin(dLon/2) * sin(dLon/2);
  Set c = 2 * atan2(sqrt(a), sqrt(1-a));
  Set d = R * c;
  return d;

END


        CREATE PROCEDURE searchnearbyrestaurant(IN deliveryaddressid int, IN radius int)
            BEGIN
              select restaurant.* from delivery_address, restaurant
              where delivery_address.id = deliveryaddressid
              and distance(delivery_address.lat, delivery_address.lng, restaurant.lat, restaurant.lng) <= radius;
            END

        CREATE PROCEDURE searchdeliveryrestaurant(IN deliveryaddressid int)
          BEGIN
            select restaurant.* from delivery_address, restaurant
            where delivery_address.id = deliveryaddressid
            and distance(delivery_address.lat, delivery_address.lng, restaurant.lat, restaurant.lng) <= restaurant.deliveryrange;
          END








