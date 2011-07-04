package com.immani.mydwich

class Partnership {

     // Todo: how do we want to manage this relation for the delete ?
     DeliveryAddress deliveryAddress
     Restaurant restaurant
     Boolean isvalidated
     String comment

    static constraints = {
        comment(nullable: true,blank: true)
    }


    static void removePartnership(Partnership partnership) {
        partnership.delete()
    }

    static void validatePartnership(Partnership partnership){
        partnership.isvalidated = true
        partnership.save(flush:true)
    }

    static Partnership requestPartnership(Restaurant restaurant, DeliveryAddress deliveryAddress) {
		def partnership = Partnership.findByRestaurantAndDeliveryAddress(restaurant, deliveryAddress)

        if (!partnership)
		{
			partnership = new Partnership()
            partnership.isvalidated = false
			restaurant?.addToPartnerships(partnership)
			deliveryAddress?.addToPartnerships(partnership)
			partnership.save(flush:true)
		}
		return partnership
	}

    /*
	static void removePartnership(DeliveryAddress deliveryAddress, Restaurant restaurant) {
		def partnership = Partnership.findByRestaurantAndDeliveryAddress(restaurant, deliveryAddress)

        if (partnership)
		{
			restaurant?.removeFromPartnerships(partnership)
			deliveryAddress?.removeFromPartnerships(partnership)
			partnership.delete()
		}
	}*/




}