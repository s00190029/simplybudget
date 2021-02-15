package edu.rdonoghue.simplybudget;
import android.app.Activity;
import edu.rdonoghue.simplybudget.MainActivity;

public class Category {
    // attributes
    static int idIterate =0;
   private long id; // primary key
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
        this.id = idIterate;
        idIterate++;
        this.name = "unknown";
        this.balance = 0;
    }

    public Category(String nameIn){
        this.id = idIterate;
        idIterate += 2;
        this.name = nameIn;
        this.balance = 0;
    }

    public Category(String nameIn, float balanceIn){
        this.id = idIterate;
        idIterate += 2;
        this.name = nameIn;
        this.balance = balanceIn;
    }

    // for db
    public Category(int idIn, String nameIn, float balanceIn){
        this.id = idIn;
        this.name = nameIn;
        this.balance = balanceIn;
    }

    // get sets
    public long getId(){
        return this.id;
    }
    public void setId(long idIn){
        this.id = idIn;
    }

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

}

