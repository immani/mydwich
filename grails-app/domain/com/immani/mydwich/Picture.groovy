package com.immani.mydwich

class Picture {
    String filename
    String caption
    String description
    String url
    String thumburl
    String contentType
    byte[] file
    Restaurant restaurant
    Product product

    static belongsTo = [Restaurant, Product]

    static constraints = {
        product(nullable: true)
        url(nullable: true)
        thumburl(nullable: true)
        filename (blank:false)
        file (nullable:true, maxSize:1000000)
        contentType (inList:['image/jpeg', 'image/gif', 'image/png'])
    }
}
