package com.immani.mydwich

import org.hibernate.SessionFactory
import org.hibernate.LockMode

class UserpaymentController {

    def  sessionFactory

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def create = {
        User currentuser = session.user.merge()
        session.userpayment = session.userpayment == null ? new Userpayment(user: currentuser): session.userpayment;
        session.userpayment = new Userpayment(user: currentuser);
        render(view: "create", model: [userpaymentInstance: session.userpayment])
    }

    // TODO: Security check no set of the user
    def review = {
        session.userpayment.properties = params.properties
        String shaSign = session.userpayment.encodeAsOgoneSHAS1tring()
        render(view: "review",model: [userpaymentInstance: session.userpayment, psid: "immanitest",shasign: shaSign])
    }


    // TODO: This can be set in a before insert
    def accepted = {

        User user = session.user.merge()

        if (params.SHASIGN == Userpayment.encodeAsOgoneSHAS1tring(params)){
            Userpayment userpayment = session.userpayment;
            userpayment.acceptance = params.ACCEPTANCE
            userpayment.paymentMethod = params.PM
            userpayment.cardBrand = params.BRAND
            userpayment.cardHolderName = params.CN
            userpayment.ogonePaymentId = params.PAYID
            userpayment.status = params.STATUS
            userpayment.ncerror = params.NCERROR
            userpayment.ipAddress = params.IP
            userpayment.validate()
            user.userpayments.add(session.userpayment)
            user.deposit(userpayment.amount)
            session.user = user.save(flush:true)
            session.removeAttribute("userpayment")

        }else {
            session.removeAttribute("userpayment")
            throw new Exception("error in sha1")
        }

        redirect(action: "list")
    }

    def refused = {
      session.removeAttribute(userpayment)
    }

    def declined = {
      session.removeAttribute(userpayment)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userpaymentInstanceList: Userpayment.list(params), userpaymentInstanceTotal: Userpayment.count()]
    }


    def show = {
        def userpaymentInstance = Userpayment.get(params.id)
        if (!userpaymentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userpayment.label', default: 'Userpayment'), params.id])}"
            redirect(action: "list")
        }
        else {
            [userpaymentInstance: userpaymentInstance]
        }
    }

     /**
     * List of the payments of the current user
     */
    def listcompanyuserpayment = {
        User user = session.user.merge()
        Company company = user.company
        def users = company.users
        def userpaymentlist = users.userpayments
        render(view: "list", model: [userpaymentInstanceList: userpaymentlist, userpaymentInstanceTotal: userpaymentlist.size()])
    }

}