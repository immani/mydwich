package com.immani.mydwich

class ProductTag {

    String name_fr
	String name_nl
	String name_en

    static belongsTo = Product
    static hasMany = [products: Product]

    static constraints = {
        name_fr(nullable: false, blank: false)
		name_nl(nullable: false, blank: false)
		name_en(nullable: false, blank: false)
    }

    String toString(){
		return "${name_fr}"
	}
}
