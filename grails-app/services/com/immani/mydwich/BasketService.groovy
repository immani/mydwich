package com.immani.mydwich

class BasketService {

    static transactional = true

    /**
     * This method add a product and his options to a user basket, if no
     * basket exist it will create and save one.
     * @param user
     * @param basket
     * @param product
     * @param options
     * @return
     */
    def addProduct(User user,Basket basket,Product product, Integer quantity, Collection options) {

        log.debug "addproduct method started"
        if (!basket) {
            log.debug "creating a new basket"
            basket = new Basket(user:user)
            basket.totalprice = 0
            basket.totalnbofarticles = 0
        }else {
            log.debug "basket found merging with current hibernate session"
            basket.merge()
        }

        log.debug "adding a new basketline to the basket"
        float productprice = product.price*quantity
        basket.addToBasketLines(new BasketLine(product: product, quantity: quantity, prodOptions: options, comment: "test", price: productprice ));
        basket.totalprice += productprice
        basket.totalnbofarticles += quantity
        log.debug "saving the basket"
        //def errors = basket.save()

        return basket
    }
}
