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



    static hasMany = [users:User,
                      products:Product,
                      restaurantcategories:RestaurantCategory,
                      productsCategories: ProductCategory,
                      prodOptionCategories: ProdOptionCategory]


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
        desc_fr(nullable: false, blank: true)
        desc_nl(nullable: false, blank: true)
        desc_en(nullable: false, blank: true)
    }

    static searchable = [only: ['name', 'desc_fr', 'desc_nl', 'desc_en']]

	String toString(){ 
		return name
	}
}
