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

    def addProduct(Product product, Integer quantity, Collection options, String comment) {
        float productprice = product.price*quantity
        this.addToBasketLines(new BasketLine(product: product, quantity: quantity, prodOptions: options, comment: comment, price: productprice ));
        //basketLines.sort()
        this.totalprice += productprice
        this.totalnbofarticles += quantity
        return this
    }

}
