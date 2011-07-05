package com.immani.mydwich

import java.security.MessageDigest
import sun.misc.BASE64Encoder

class UserPaymentService {

    static transactional = true

    String psid = "immanitest";
    String psidKey = "spritespritesprite8";
    String psidresponsekey = "spritespritesprite9"


    def encodeAsOgoneString(Userpayment userPayment) {
        String ogoneString = "";
        ogoneString += "ACCEPTURL=${userPayment.acceptedurl}${psidKey}"
        ogoneString += "AMOUNT=${(int)userPayment.amount*100}${psidKey}"
        ogoneString += "CURRENCY=${userPayment.currency}${psidKey}"
        ogoneString += "DECLINEURL=${userPayment.declinedurl}${psidKey}"
        ogoneString += "LANGUAGE=${userPayment.user.language}${psidKey}"
        ogoneString += "ORDERID=${userPayment.orderId}${psidKey}"
        ogoneString += "PSPID=${psid}${psidKey}"
        return ogoneString
    }

    def verifyUserPayement(params){
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

    def encodeAsSha1String(Userpayment userPayment){
       String ogoneString = this.encodeAsOgoneString(userPayment);
       return ogoneString.encodeAsSHA1();
    }


}
