package com.immani.mydwich

class Account {

    User user
    double balance

    public synchronized void deposit(double amount) {
        balance += amount;
    }


    public synchronized void withdraw(double amount) throws RuntimeException {

        if (amount > balance) {
            throw new RuntimeException("Negative balance");
        }
        balance -= amount;
    }

    String toString(){
		return balance;
	}

    public void setBalance(Double balance){

    }
}
