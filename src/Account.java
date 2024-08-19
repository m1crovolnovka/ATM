import java.io.Serializable;

public class Account implements Serializable {
    private String login;
    private String password;
    private double balance =10000;
    private String miniStatement = "\0";
    private double depositBalance;
    public Account(){};

    public Account(String login){
        this.login = login;
        password = login;
    }
public double getBalance(){
    return balance;
}
    public Boolean is_password(String password){
        if(password.equals(this.password)){
            return true;
        }
        else
            return false;

    }
   // public void logIn(String password, )
public void withdrawAmont(double amount){
        balance-= amount;
}
public void addDepositBalance(double amount){
        depositBalance+=amount;
}
public void addStatement(String method, double amount){
        if(miniStatement.equals("\0"))
            miniStatement = amount +" "+ method;
        else
            miniStatement = miniStatement + '\n' +amount +" "+ method;
}
public String getMiniStatement(){
        return miniStatement;
}

}
