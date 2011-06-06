package com.immani.mydwich

class Restaurant implements Serializable{

    String name
	String address
	String zip
	String city
	String country
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
                      partnerships:Partnership]


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

    List listPatnershipsCompanies() {
		return partnerships.collect{it.company}
	}

    Partnership addPartnershipToCompany(Company company) {
		Partnership partnership = Partnership.link(company, this)
		return partnership
	}

	List removePartnershipFromCompany(Company company) {
		Partnership.unlink(company, this)
		return this.listPatnershipsCompanies()
	}

	String toString(){ 
		return name
	}
}
