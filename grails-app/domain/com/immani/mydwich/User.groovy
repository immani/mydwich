package com.immani.mydwich

class User implements Serializable {

    String username
    String passwordHash
    String title
    String firstname
    String lastname
    String mobile
    String language
    Boolean isvalidated = false

    Company company
    Restaurant restaurant

    static belongsTo = [Company, Restaurant]

    static hasMany = [roles: Role,
                      permissions: String,
                      baskets: Basket,
                      userpayments: Userpayment]

    static mapping  = {
      permissions lazy: false
    }

    static constraints = {
        username(nullable: false, blank: false, unique: true, email: true)
        firstname(nullable: false, blank: false)
        lastname(nullable: false, blank: false)
        mobile(nullable: true, blank: true)
        language(nullable: false, blank: false)
        company(nullable: true)
        restaurant(nullable:true)
        isvalidated(nullable: false, blank: false)
    }

    String toString(){
	    return username
	}
}
