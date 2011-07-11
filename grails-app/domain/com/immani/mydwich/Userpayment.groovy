package com.immani.mydwich

class Userpayment implements Serializable{

    Integer orderId = new Random().nextInt(100000);
    Float amount
    String remark
    User user
    String currency = "EUR";
    String paymentMethod
    String cardBrand
    String cardHolderName
    String ogonePaymentId
    String acceptance
    String status
    String ncerror
    String ipAddress

    transient String acceptedurl = "http://mydwich:8080/mydwich/userpayment/accepted";
    transient String declinedurl = "http://mydwich:8080/mydwich/userpayment/declined";

    static belongsTo = [User]

    static constraints = {
        user(nullable: false)
        remark(nullable: true, blank: true)
        amount(nullable: false, blank:false)
        currency(nullable: false,blank:true)
        paymentMethod(nullable: false,blank:false)
        cardBrand(nullable: false,blank:true)
        cardHolderName(nullable: false,blank:true)
        ogonePaymentId(nullable: false,blank:false)
        status(nullable: false,blank:false)
        ncerror(nullable: false,blank:false)
        ipAddress(nullable: false,blank:false)
    }
}
