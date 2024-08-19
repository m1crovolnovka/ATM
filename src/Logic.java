import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Logic {
    private Scanner in = new Scanner(System.in);
    public void logic() {
        HashMap<String, Account> accaunts = readFile();
        Account user = logIn(accaunts);
        menu(user);
        writeFile(accaunts);
    }
    public void menu(Account user){
        try {
            while (true) {
                System.out.println("1. Проверить баланс\n2. Снять наличные\n3. Пополнить депозит\n4. Просмотреть историю\n5. Выход");
                int numSwitch;
                numSwitch = in.nextInt();
                switch (numSwitch) {
                    case 1:
                        viewBalance(user);
                        break;
                    case 2:
                        withdrawAmount(user);
                        break;
                    case 3:
                        depositAmount(user);
                        break;
                    case 4:
                        viewMiniStatement(user);
                        break;
                    case 5:
                        return;
                }
            }
        }
            catch(Exception ex){
                System.out.println("Неверный ввод");
                menu(user);
        }
    }
    public Account logIn(HashMap<String, Account> accaunts) {
        try {
            String login;
            String password;
            System.out.print("Впишите логин: ");
            login = in.nextLine();
            System.out.print("Впишите пароль: ");
            password = in.nextLine();
            Account user = accaunts.get(login);
            if (user != null && user.is_password(password)) {
                System.out.println("Вы вошли");
                return user;
            }
            else
                throw new UncorrectPasswordException("Неверный логин или пароль");
        }
        catch(UncorrectPasswordException ex){
            System.out.println(ex.getMessage());
            System.out.println();
           return logIn(accaunts);
        }
    }
    public void viewBalance(Account user){
        System.out.println("Ваш баланс: " + user.getBalance());
    }
    public void withdrawAmount(Account user){
        try {
            double amount;
            System.out.println("Какую сумму хотите снять: ");
            amount = in.nextDouble();
            if (amount > user.getBalance()) {
                System.out.println("Такой суммы нет на счете");
                return;
            } else {
                System.out.println("Вы сняли сумму: " + amount);
                user.withdrawAmont(amount);
                System.out.println("Остаток на счете: " + user.getBalance());
                user.addStatement("снятие наличных", amount);
            }
        }
        catch(Exception ex){
            in.nextLine();
            System.out.println(ex.getMessage());
        }

    }
    public void depositAmount(Account user){
        try {
            double amount;
            System.out.println("Какую сумму хотите положить: ");
            amount = in.nextDouble();
            if (amount > user.getBalance()) {
                System.out.println("Такой суммы нет на счете");
                return;
            } else {
                System.out.println("Вы положили сумму: " + amount);
                user.withdrawAmont(amount);
                user.addDepositBalance(amount);
                System.out.println("Остаток на счете: " + user.getBalance());
                user.addStatement("пополнение депозита", amount);
            }
        }
        catch(Exception ex){
            in.nextLine();
            System.out.println(ex.getMessage());
        }
    }
    public void viewMiniStatement(Account user){
        System.out.println(user.getMiniStatement());
    }

    public void writeFile(HashMap<String, Account> accounts) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.dat")))
        {
            oos.writeObject(accounts);
            oos.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public HashMap<String, Account> readFile() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.dat")))
        {
            return (HashMap<String, Account>)ois.readObject();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }


}
