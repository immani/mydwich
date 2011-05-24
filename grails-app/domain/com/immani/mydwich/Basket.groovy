package com.immani.mydwich

class Basket implements Serializable{

    String remark
    Date orderDate
    Float totalprice
    Integer totalnbofarticles

    User user
    DeliveryAddress deliveryAddress
    Restaurant restaurant

    static belongsTo = [User]

    static hasMany = [basketLines: BasketLine]

    static constraints = {
        user(nullable: false)
        deliveryAddress(nullable: false)
        orderDate(nullable: false)
        remark(nullable: true, blank: true)
    }

    String toString(){
		return basketLines.toListString();
	}

}
