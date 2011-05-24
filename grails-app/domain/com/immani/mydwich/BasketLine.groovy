package com.immani.mydwich

class BasketLine implements Serializable {

    Integer quantity
    Float price
    String comment
    Product product

    static belongsTo = Basket

    static hasMany = [prodOptions:ProdOption]

    static constraints = {
        quantity(nullable:false, min:1, max:20)
        price(nullable: false)
        prodOptions(nullable:true)
        comment(nullable: true, blank: true)
    }

    String toString(){
		return product.toString();
	}
}