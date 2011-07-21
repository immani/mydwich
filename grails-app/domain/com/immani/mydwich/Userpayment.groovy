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

      // TODO: Externalize the password en login infos for both methods
      def encodeAsOgoneSHAS1tring() {
        String psid = "immanitest";
        String psidKey = "spritespritesprite8";
        String ogoneString = "";
        ogoneString += "ACCEPTURL=${this.acceptedurl}${psidKey}"
        ogoneString += "AMOUNT=${(int)this.amount*100}${psidKey}"
        ogoneString += "CURRENCY=${this.currency}${psidKey}"
        ogoneString += "DECLINEURL=${this.declinedurl}${psidKey}"
        ogoneString += "LANGUAGE=${this.user.language}${psidKey}"
        ogoneString += "ORDERID=${this.orderId}${psidKey}"
        ogoneString += "PSPID=${psid}${psidKey}"
        return ogoneString.encodeAsSHA1()
    }

    def static encodeAsOgoneSHAS1tring(params){
        String psidresponsekey = "spritespritesprite9"
        String ogoneString = "";
        ogoneString += "ACCEPTANCE=${params.ACCEPTANCE}${psidresponsekey}"
        ogoneString += "AMOUNT=${params.amount}${psidresponsekey}"
        ogoneString += "BRAND=${params.BRAND}${psidresponsekey}"
        if (params.CN){
             ogoneString += "CN=${params.CN}${psidresponsekey}"
        }
        ogoneString += "CURRENCY=${params.currency}${psidresponsekey}"
        ogoneString += "IP=${params.IP}${psidresponsekey}"
        ogoneString += "NCERROR=${params.NCERROR}${psidresponsekey}"
        ogoneString += "ORDERID=${params.orderID}${psidresponsekey}"
        ogoneString += "PAYID=${params.PAYID}${psidresponsekey}"
        ogoneString += "PM=${params.PM}${psidresponsekey}"
        ogoneString += "STATUS=${params.STATUS}${psidresponsekey}"
        return ogoneString.encodeAsSHA1().toUpperCase();
    }
}
