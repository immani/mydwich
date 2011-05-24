package com.immani.mydwich

class ProdOptionCategory {

    String name_fr
	String name_nl
	String name_en
    String type
    Restaurant restaurant

    static belongsTo = [Restaurant, ProductCategory]

    static hasMany = [productCategories: ProductCategory,
                      options: ProdOption]

    static constraints = {
		name_fr(nullable: false, blank: false)
		name_nl(nullable: false, blank: false)
		name_en(nullable: false, blank: false)
        type(inList:["radio", "checkbox"])
    }

    String toString(){
		return name_fr
	}
}
