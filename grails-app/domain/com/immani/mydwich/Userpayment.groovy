package com.immani.mydwich

class Userpayment implements Serializable{


    Float amount
    String remark
    User user
    String currency;
    String acceptedurl;
    String declinedurl;


    static belongsTo = [User]

    static constraints = {
        user(nullable: false)
        remark(nullable: true, blank: true)
        amount(nullable: false)
        currency(nullable: false,blank:true)
        acceptedurl(nullable: true, blank:true)
        declinedurl(nullable: true, blank:true)
    }

    String toString(){
        return remark + ": " + amount
	}


}
