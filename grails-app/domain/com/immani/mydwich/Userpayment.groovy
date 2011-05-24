package com.immani.mydwich

class Userpayment {
    String remark
    Date date
    Float amount
    User user
    String type

    static belongsTo = [User]

    static constraints = {
        user(nullable: false)
        date(nullable: false)
        remark(nullable: true, blank: true)
        amount(nullable: false)
        type(nullable: false, blank: false, inList:["Cash", "Visa", "Mastercard", "PingPing"])
    }

    String toString(){
        return date + " - " + remark + ": " + amount
	}
}
