package edu.rdonoghue.simplybudget;
import android.app.Activity;
import edu.rdonoghue.simplybudget.MainActivity;

public class category {
    // attributes
    static int idIterate =0;
    int id;
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

    public void setBalance(float balanceIn){
        this.balance = balanceIn;
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
    public category(){
        this.id = idIterate;
        idIterate++;
        this.name = "unknown";
        this.balance = 0;
    }

    public category(String nameIn){
        this.id = idIterate;
        idIterate += 2;
        this.name = nameIn;
        this.balance = 0;
    }

    public category(String nameIn, float balanceIn){
        this.id = idIterate;
        idIterate += 2;
        this.name = nameIn;
        this.balance = balanceIn;
    }

}

