package com.immani.mydwich

class BasketLine implements Serializable {

    Integer quantity
    Float price
    String comment
    Product product
    Basket basket

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

    public getPrice(){
        //TODO: Integrate into Constructor
        if(prodOptions.size()>0){
            this.price = quantity * product.price + prodOptions?.price.collect{it*quantity}.sum()
        }
        else{
            this.price = quantity * product.price
        }

        return this.price
    }

}