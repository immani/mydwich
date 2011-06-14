package com.immani.mydwich

class Restaurant implements Serializable{

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

    List listValidatedPatnershipsCompanies() {
		return Partnership.findAllByRestaurantAndCompanyisvalidated(this,true);
	}

    List listRequestedPartnershipsCompanies(){
        return Partnership.findAllByRestaurantAndCompanyisvalidated(this,false)
    }

    Partnership requestPartnershipToCompany(Company company) {
		Partnership partnership = Partnership.requestPartnership(company, this,false,true)
		return partnership
	}

	void removePartnershipFromCompany(Company company) {
		Partnership.removePartnership(company, this)
	}

	String toString(){ 
		return name
	}
}
