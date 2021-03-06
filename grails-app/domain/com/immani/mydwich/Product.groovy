package com.immani.mydwich

class Product implements Serializable {

    String name_fr
    String name_nl
    String name_en
    String desc_fr
    String desc_nl
    String desc_en
    BigDecimal price
    Restaurant restaurant
    Picture picture
    List productCategories

    static belongsTo = [Restaurant]

    static hasMany = [productTags:ProductTag, productCategories: ProductCategory]

    static constraints = {
        name_fr(nullable: false, blank: false)
        name_nl(nullable: false, blank: false)
        name_en(nullable: false, blank: false)
        desc_fr(nullable: true,  blank: true)
        desc_nl(nullable: true,  blank: true)
        desc_en(nullable: true,  blank: true)
        price(nullable:false, min:0.0, max:100.0)
        picture(nullable:true)
    }

    static searchable = [only: ['name_fr', 'name_nl', 'name_en', 'desc_fr', 'desc_nl', 'desc_en']]

    String toString(){
        return name_fr

    }
}
