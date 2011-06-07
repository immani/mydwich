package com.immani.mydwich

class Picture {
    String filename
    String caption
    String description
    String url
    String contentType
    byte[] file





    static constraints = {
        filename (blank:false)
        file (nullable:true, maxSize:1000000)
        contentType (inList:['image/jpeg', 'image/gif'])
    }
}
