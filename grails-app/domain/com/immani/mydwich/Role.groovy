package com.immani.mydwich

class Role implements Serializable {

    String name

    static belongsTo = [User]

    static hasMany = [users: User,
                      permissions: String]

    static constraints = {
        name(nullable: false, blank: false, unique: true)
    }

     String toString(){
	    return name
	}
}
