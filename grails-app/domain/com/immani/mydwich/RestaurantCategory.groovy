package com.immani.mydwich

class RestaurantCategory implements Serializable{

    String name_fr
	String name_nl
	String name_en

    static belongsTo = [Restaurant]

    static hasMany = [restaurants:Restaurant]

    static constraints = {
		name_fr(nullable: false, blank: false)
		name_nl(nullable: false, blank: false)
		name_en(nullable: false, blank: false)
    }

	String toString(){
		return name_fr
	}
}