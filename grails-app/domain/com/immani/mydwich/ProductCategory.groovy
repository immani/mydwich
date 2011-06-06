package com.immani.mydwich

class ProductCategory {

    String name_fr
	String name_nl
	String name_en
    Integer catorder
    Restaurant restaurant

    static belongsTo = [Restaurant, Product]

    static hasMany = [products:Product,
                      prodOptionCategories: ProdOptionCategory]

	static constraints = {
		name_fr(nullable: false, blank: false)
		name_nl(nullable: false, blank: false)
		name_en(nullable: false, blank: false)
        catorder(nullable: false, size:1..100)
    }

    static mapping = {
        sort "catorder"
    }

    String toString(){
		return name_fr
	}
}
