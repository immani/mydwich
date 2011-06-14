package com.immani.mydwich

class DeliveryAddress implements Serializable{

    String name
	String address
	String zip
	String city
	String country = "Belgium"
    Float lat
    Float lng

   static belongsTo = [company:Company]

    static constraints = {
		name(nullable: false, blank: false)
		address(nullable: false, blank: false)
		zip(nullable: false, blank: false)
		city(nullable: false, blank: false)
		country(nullable: false, blank: false)
        lat(nullable: true)
        lng(nullable: true)
    }

    String toString(){
		return "${name} - ${address} , ${zip} ${city}, ${country}"
	}
}
