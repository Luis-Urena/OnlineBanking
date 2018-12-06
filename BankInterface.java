public interface BankInterface
{
    public boolean transfer(String toWhom,double amount);//create a document printout including

    public boolean billPay(String ToWhom,double Amount ); //Create a document printout like a reciept

    public boolean withdraw(double x, String y);

    public boolean deposit(double x,String y);

    public boolean validate(String username,String password);//Checks if username and password are correct



    public String toString();
}
