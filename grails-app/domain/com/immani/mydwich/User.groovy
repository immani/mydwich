package com.immani.mydwich

class User implements Serializable {

    String username
    String passwordHash
    String sex
    String firstname
    String lastname
    String mobile
    String language
    Boolean isvalidated = false
    Boolean isadmin

    Company company
    Restaurant restaurant

    static belongsTo = [Company, Restaurant]

    static hasMany = [roles: Roles,
            permissions: String,
            baskets: Basket,
            userpayments: Userpayment]

    static mapping  = {
        permissions lazy: false
        company lazy: false
    }

    static constraints = {
        username(nullable: false, blank: false, unique: true, email: true,
                validator: {
                    val, obj ->
                    if(obj.properties['company']){
                        Integer pos = obj.properties['username'].indexOf('@')
                        String domain = obj.properties['username'].substring(pos + 1)
                        if(domain == obj.properties['company'].domain){
                            return true
                        }
                        else{
                            return ['user.company.email.notmatch', domain]
                        }
                    }
                    true
                })
        firstname(nullable: false, blank: false)
        lastname(nullable: false, blank: false)
        mobile(nullable: true, blank: true)
        language(nullable: false, blank: false, inList: ["fr", "nl", "en"])
        sex(nullable: false, blank: false, inList: ["Male", "Female"])
        company(nullable: true)
        restaurant(nullable:true)
        isvalidated(nullable: false)
        passwordHash(nullable: false, password: true)
        //TODO: Check complexité du password en regex
    }

    String toString(){
        return username
    }

    def beforeInsert = {
        if(this.company != null){
            if(this.isadmin){
                this.addToPermissions("${this.company.domain}:*:company,da,partnership:*")
            }
            else{
                //Non admin user can view all company infos except , but not edit
                this.addToPermissions("${this.company.domain}:*:company,da,partnership,user:list,show,index")
            }
            //Company user can edit all infos for his user profile
            this.addToPermissions("${this.company.domain}:${this.username}:*:*")
        }
        else{
            this.addToPermissions("${this.restaurant.id}:*:*:*")
        }
    }


    def beforeUpdate = {
        beforeInsert()
    }
}
