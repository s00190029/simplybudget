package edu.rdonoghue.simplybudget;
import android.app.Activity;
import edu.rdonoghue.simplybudget.MainActivity;

public class Category {
    // attributes
    String name;
    float balance;

    //methods
    public void updateBalance(float amtIn, boolean plusOrMinus){
        if (plusOrMinus == true){
            this.balance += amtIn;
        }
        else if (amtIn <= this.balance){
            this.balance -= amtIn;
        }
    }

    public void rename(String nameIn){
        this.name = nameIn;
    }

    public void resolveNegativeBalance(){
        if (this.balance < 0){
            this.balance += MainActivity.availableCash;
            MainActivity.setCash(0);
        }
    }

    // constructors
    public Category(){
        this.name = "unknown";
        this.balance = 0;
    }

    public Category(String nameIn){
        this.name = nameIn;
        this.balance = 0;
    }

    public Category(String nameIn, float balanceIn){
        this.name = nameIn;
        this.balance = balanceIn;
    }


    // get sets
    public String getName(){
        return name;
    }
    public void setName(String nameIn){
        this.name = nameIn;
    }

    public float getBalance(){
        return balance;
    }
    public void setBalance(float balanceIn){
        this.balance = balanceIn;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}

