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
        isvalidated(nullable: false, blank: false)
        ///^([a-z0-9]([-a-z0-9]*[a-z0-9])?\\.)+((a[cdefgilmnoqrstuwxz]|aero|arpa)|(b[abdefghijmnorstvwyz]|biz)|(c[acdfghiklmnorsuvxyz]|cat|com|coop)|d[ejkmoz]|(e[ceghrstu]|edu)|f[ijkmor]|(g[abdefghilmnpqrstuwy]|gov)|h[kmnrtu]|(i[delmnoqrst]|info|int)|(j[emop]|jobs)|k[eghimnprwyz]|l[abcikrstuvy]|(m[acdghklmnopqrstuvwxyz]|mil|mobi|museum)|(n[acefgilopruz]|name|net)|(om|org)|(p[aefghklmnrstwy]|pro)|qa|r[eouw]|s[abcdeghijklmnortvyz]|(t[cdfghjklmnoprtvwz]|travel)|u[agkmsyz]|v[aceginu]|w[fs]|y[etu]|z[amw])$/i
        lat(nullable: true)
        lng(nullable: true)
    }

    List listValidatedPatnerships() {
        return Partnership.findAllByCompanyAndRestaurantisvalidated(this,true);
    }

    List listRequestedPartnerships(){
        return Partnership.findAllByCompanyAndRestaurantisvalidated(this,false);
    }

    Partnership requestPartnership(Restaurant restaurant) {
        Partnership partnership = Partnership.requestPartnership(this,restaurant,true,false)
        return partnership
    }

    void removePartnership(Restaurant restaurant) {
        Partnership.removePartnership(this, restaurant)
    }

    String toString(){
        return name
    }
}
