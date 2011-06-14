package com.immani.mydwich

class Partnership {

     // Todo: how do we want to manage this relation for the delete ?
     Company company;
     Restaurant restaurant;
     Boolean companyisvalidated;
     Boolean restaurantisvalidated;

    static constraints = {
    }


    static Partnership requestPartnership(Company company, Restaurant restaurant, boolean companyisvalidated, boolean restaurantisvalidated) {
		def partnership = Partnership.findByRestaurantAndCompany(restaurant, company)

        if (!partnership)
		{
			partnership = new Partnership();
            partnership.companyisvalidated = companyisvalidated;
            partnership.restaurantisvalidated = restaurantisvalidated;
			restaurant?.addToPartnerships(partnership)
			company?.addToPartnerships(partnership)
			partnership.save(flush:true)
		}
		return partnership
	}


	static void removePartnership(Company company, Restaurant restaurant) {
		def partnership = Partnership.findByRestaurantAndCompany(restaurant, company)

        if (partnership)
		{
			restaurant?.removeFromPartnerships(partnership)
			company?.removeFromPartnerships(partnership)
			partnership.delete()
		}
	}

    static void validatePartnership(Partnership partnership){
        partnership.companyisvalidated = true;
        partnership.restaurantisvalidated = true;
        partnership.save(flush:true);
    }

}
