package edu.rdonoghue.simplybudget;

public class category {

    // attributes
    int idIterate = 1;
    int id;
    String name;
    float balance;

    //methods
    public void updateBalance(float amtIn, boolean plusOrMinus){
        if (plusOrMinus == true){
            this.balance += amtIn;
        }
        else{
            this.balance -= amtIn;
        }
    }

    // constructors
    public category(){
        this.id = idIterate;
        idIterate = + 2;
        this.name = "unknown";
        this.balance = 0;
    }

    public category(String nameIn){
        this.name = name;
    }

}

