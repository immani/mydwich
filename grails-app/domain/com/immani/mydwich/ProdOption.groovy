package com.immani.mydwich

class ProdOption {

    String name_fr
	String name_nl
	String name_en
	BigDecimal price
    ProdOptionCategory prodOptionCategory

    static belongsTo = [BasketLine, ProdOptionCategory]

    static hasMany = BasketLine

    static constraints = {
		name_fr(nullable: false, blank: false)
		name_nl(nullable: false, blank: false)
		name_en(nullable: false, blank: false)
		price(nullable:false, min:0.0, max:100.0)
    }

	String toString(){ 
		return name_fr
	}
}
