import java.util.Scanner;

public class Client
{
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private double checkingBalance;
    private double savingBalance;
    private String listOfTransactions ="";


    private static Scanner myScan = new Scanner(System.in);


    //Constructor
    public Client(String firstName, String lastName, String userName, String password,double balance, double savingBalance)
    {
        this.firstName=firstName;
        this.lastName=lastName;
        this.userName=userName;
        this.password=password;
        checkingBalance=balance;
        this.savingBalance=savingBalance;
    }

    //This will ask the user for the current password and checks if it matches the old password
    //if it matches then it will assign a new password and a user will enter
    public void changePassword()
    {
        boolean flag = true;
        while(flag)
        {
            System.out.println("Please enter current password.");
            String myCurrent = myScan.nextLine();
            if (password == myCurrent)
            {
                System.out.println("Please enter new password");
                password = myScan.nextLine();
                System.out.println("New password accepted.");
                break;
            }
            else
            {
                while(true)
                {
                    System.out.println("Incorrect password. Would you like to try again? Y/N");
                    String answer= myScan.nextLine();
                    if(answer.equals("Y") || answer.equals("y"))
                    {
                        break;
                    }
                    if(answer.equals("N") || answer.equals("n"))
                    {
                        flag=false;
                        break;
                    }
                    else
                        System.out.println("Invalid entry. Please try again.");
                }
            }
        }
    }

    public String getUserName()
    {
        return userName;
    }

    public String getPassword()
    {
        return password;
    }

    public double getCheckingBalance()
    {
        return checkingBalance;
    }

    public double getSavingBalance(){return savingBalance;}

    public void setCheckingBalance(double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }

    public void setSavingBalance(double savingBalance) {
        this.savingBalance = savingBalance;
    }

    public String getFirstName() {return firstName;}

    public String getLastName(){return lastName;}

    public void appendTransaction(String x)
    {
        listOfTransactions = listOfTransactions +" \n"+ x;
    }

    public String getListOfTransactions()
    {
        return listOfTransactions;
    }

    @Override
    public String toString()
    {
        return firstName+" "+lastName+"\n"+"Checking Account Balance: "+checkingBalance+"\n"+"Saving Account Balance: "+savingBalance;
    }
}
