package com.immani.mydwich

class Picture {
    String filename
    String caption
    String description
    String contentType
    byte[] file
    Restaurant restaurant
    Product product


    static belongsTo = [Restaurant, Product]

    static constraints = {
        product(nullable: true)
        restaurant(nullable: true)
        filename (blank:false)
        file (nullable:true, maxSize:1000000)
        contentType (inList:['image/jpeg', 'image/gif', 'image/png'])
    }

    //TODO: Check if we can enrich the Domain
   /*
    def beforeDelete = {
    ApplicationContext applicationContext
        def restaurantname = this.restaurant ? this.restaurant.name.toString().toLowerCase().encodeAsURL() : this.product.restaurant.name.toString().toLowerCase().encodeAsURL()
        File imagefile
        File thumbimagefile
        if (this.restaurant){
            imagefile = applicationContext.getResource("/restimages/${restaurantname}/${filename}").getFile()
            thumbimagefile = applicationContext.getResource("/restimages/${restaurantname}/thumb_${filename}").getFile()
        }
        else{
            imagefile = applicationContext.getResource("/restimages/${restaurantname}/products/${filename}").getFile()
            thumbimagefile = applicationContext.getResource("/restimages/${restaurantname}/products/thumb_${filename}").getFile()
        }
        imagefile.delete()
        thumbimagefile.delete()
    }
    */
}
