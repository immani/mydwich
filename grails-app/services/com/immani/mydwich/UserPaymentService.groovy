package com.immani.mydwich

import java.security.MessageDigest
import sun.misc.BASE64Encoder

class UserPaymentService {

    static transactional = true

    String psid = "immanitest";
    String psidKey = "spritespritesprite8";

    def initializeUserPayment(Userpayment userpayment){
      userpayment.currency = "EUR"
    }

    def encodeAsOgoneString(Userpayment userPayment) {
        String ogoneString = "";
        ogoneString += "AMOUNT=${(int)userPayment.amount*100}${psidKey}"
        ogoneString += "CURRENCY=${userPayment.currency}${psidKey}"
        ogoneString += "LANGUAGE=${userPayment.user.language}${psidKey}"
        ogoneString += "ORDERID=${userPayment.id}${psidKey}"
        ogoneString += "PSPID=${psid}${psidKey}"
        return ogoneString
    }

    def encodeAsSha1String(Userpayment userPayment){
       String ogoneString = this.encodeAsOgoneString(userPayment);
       return ogoneString.encodeAsSHA1();
    }
}
