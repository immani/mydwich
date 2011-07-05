package com.immani.mydwich

class Userpayment implements Serializable{

    Integer orderId = new Random().nextInt(100000);
    Float amount
    String remark
    User user
    String currency = "EUR";
    transient String acceptedurl = "http://mydwich:8080/mydwich/userpayment/accepted";
    transient String declinedurl = "http://mydwich:8080/mydwich/userpayment/declined";


    static belongsTo = [User]

    static constraints = {
        user(nullable: false)
        remark(nullable: true, blank: true)
        amount(nullable: false)
        currency(nullable: false,blank:true)
        acceptedurl(nullable: true, blank:true)
        declinedurl(nullable: true, blank:true)
    }
}
