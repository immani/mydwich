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
    Double balance = 0

    Company company
    Restaurant restaurant
    DeliveryAddress defaultda

    static belongsTo = [Company, Restaurant]

    static hasMany = [roles: Role,
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
        defaultda(nullable: true)

        //TODO: Check complexité du password en regex
    }

    String toString(){
        return username
    }



    public synchronized void deposit(double amount) {
        this.balance += amount;
    }


    public synchronized void withdraw(double amount) throws RuntimeException {

        if (amount > balance) {
            throw new RuntimeException("Negative balance");
        }
        balance -= amount;
    }

    // Block any direct set of the balance
    public void setBalance(Double balance){}


    def beforeInsert = {
        if(this.company != null){
            if(this.isadmin){
                this.addToPermissions("${this.company.domain}:*:company,deliveryaddress,picture,partnership:*")
                this.addToPermissions("${this.company.domain}:*:basket,basketline:list,show")
            }
            else{
                //Non admin user can view all company infos except , but not edit
                this.addToPermissions("${this.company.domain}:*:company,deliveraddress,picture,partnership,user:list,show,index")
            }
            //Company user can edit all infos for his user profile, basket, basketline
            //We put *:* at the end cause all the other Controllers are not limited by username so no need to restrict the permissions
            this.addToPermissions("${this.company.domain}:${this.username}:*:*")
        }
        else{
            //For the moment we do not define a restaurant admin, therefore everything which is linked to the restaurant can be accessed...
            this.addToPermissions("${this.restaurant.id}:*:*:*")
        }
    }


    def beforeUpdate = {
        beforeInsert()
    }
}
