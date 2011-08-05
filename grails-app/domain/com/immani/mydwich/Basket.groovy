package com.immani.mydwich

class Basket implements Serializable{

    String remark
    Date orderDate
    Float totalprice = 0
    Integer totalnbofarticles = 0

    User user
    DeliveryAddress deliveryAddress
    Restaurant restaurant
    List basketLines
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

    Float getTotalprice(){
        basketLines.collect {it.price}.sum()
    }

    Integer getTotalnbofarticles(){
        basketLines.collect {it.quantity}.sum()
    }

    def addProduct(Product product, Integer quantity, List options, String comment) {
        BasketLine bl = new BasketLine(product: product, quantity: quantity, prodOptions: options, comment: comment )
        this.addToBasketLines(bl);
        return this
    }

    def removeBasketLine(BasketLine bl) {
        basketLines -= bl
    }

}
