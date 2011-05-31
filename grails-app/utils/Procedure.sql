
         CREATE FUNCTION distance (companylat double,companylng double,restaurantlat double,restaurantlng double)
             RETURNS DOUBLE DETERMINISTIC
                BEGIN

                declare philat double;
                declare k1 double;
                declare k2 double;
                declare deltalat double;
                declare deltalng double;
                declare result double;

                set philat = (companylat + restaurantlat)/2;
                set k1 = 111.13209 - (0.56605 * cos(2*philat)) + (0.00120 * cos(4*philat));
                set k2 = (111.41513 * cos(philat)) - (0.09455 * cos(3*philat)) + (0.00012 * cos(5*philat));
                set deltalat =  companylat - restaurantlat;
                set deltalng = companylng - restaurantlng;

                set result = sqrt(pow(k1*deltalat,2) + pow(k2*deltalng,2));

                return result;
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




