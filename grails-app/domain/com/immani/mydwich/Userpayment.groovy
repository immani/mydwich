package com.immani.mydwich

class Userpayment implements Serializable{


    Float amount
    String remark
    User user
    String currency;


    static belongsTo = [User]

    static constraints = {
        user(nullable: false)
        remark(nullable: true, blank: true)
        amount(nullable: false)
        currency(nullable: false,blank:true)
    }

    String toString(){
        return remark + ": " + amount
	}


}
