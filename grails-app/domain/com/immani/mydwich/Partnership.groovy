package com.immani.mydwich

class Partnership {

     // Todo: how do we want to manage this relation for the delete ?
     Company company;
     Restaurant restaurant;

    static constraints = {
    }


    static Partnership link(Company company, Restaurant restaurant) {
		def partnership = Partnership.findByRestaurantAndCompany(restaurant, company)

        if (!partnership)
		{
			partnership = new Partnership()
			restaurant?.addToPartnerships(partnership)
			company?.addToPartnerships(partnership)
			partnership.save()
		}
		return partnership
	}


	static void unlink(Company company, Restaurant restaurant) {
		def partnership = Partnership.findByRestaurantAndCompany(restaurant, company)

        if (partnership)
		{
			restaurant?.removeFromPartnerships(partnership)
			company?.removeFromPartnerships(partnership)
			partnership.delete()
		}

	}
}
