package com.immani.mydwich

class Company implements Serializable{

    String name
    String address
    String zip
    String city
    String country = "Belgium"
    String vat
    String phone
    String fax
    String domain
    Float lat
    Float lng
    Boolean isvalidated = false

    static hasMany = [users:User,
            deliveryAddresses:DeliveryAddress,
            partnerships:Partnership]

    static constraints = {
        name(nullable: false,blank: false)
        address(nullable: false,blank: false)
        zip(nullable: false,blank: false)
        city(nullable: false,blank: false)
        country(nullable: false,blank: false)
        vat(nullable: false,blank: false)
        phone(nullable: true, blank: true)
        fax(nullable: true, blank: true)
        domain(nullable:false, blank: false, unique: true)
        isvalidated(nullable: false)  //TODO:Regex pour valider domain name
        lat(nullable: true)
        lng(nullable: true)
    }

    String toString(){
        return name
    }
}
