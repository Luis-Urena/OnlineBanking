import java.text.DecimalFormat;

public class Bank implements BankInterface
{
    Client [] listOfClients = new Client[20];
    private static Client lastAccessed;
    private DecimalFormat myFormat = new DecimalFormat("##.00");

    public Bank()
    {
        listOfClients[0] = new Client("Vicente", "Romero", "admin", "admin",100,100);
        listOfClients[1] = new Client("Joe", "Smith", "Jsmith", "ghsg4",1235,200);
        listOfClients[2] = new Client("Jesus", "Christ", "JChrist", "sgmrk",34456,300);
        listOfClients[3] = new Client("Robert", "Jenkins", "RJenkins", "sgrmr",56543,400);
        listOfClients[4] = new Client("Google", "Docs", "Gdocs", "knjawuibef",2222,500);
        listOfClients[5] = new Client("Windows", "10", "W10", "Hethhtd",8883,600);
        listOfClients[6] = new Client("Mac", "kintosh", "MKintosh", "ghello123",445,700);
        listOfClients[7] = new Client("Leroy", "Jenkins", "LJenkins", "yllo123",66654,800);
        listOfClients[8] = new Client("Jacob", "Rodriguez", "JRodriguez", "hjfsello123",6532,900);
        listOfClients[9] = new Client("Link", "Unknown", "Zelda", "Hi",1213,150);
        listOfClients[10] = new Client("Roy", "lee", "Rlee", "Hessmke",3453,111);
        listOfClients[11] = new Client("Bruce", "Lee", "BadAss", "ehefma",243,345);
        listOfClients[12] = new Client("Abram", "Linkon", "President", "wefama",356,3546);
        listOfClients[13] = new Client("John", "Adams", "JAdams", "enor",8320,999);
        listOfClients[14] = new Client("Sugar", "Sweet", "Candy", "xvmsr",7283,285);
        listOfClients[15] = new Client("Pepe", "Romo", "SoccerIsLife", "sjh3e9854",2214,185);
        listOfClients[16] = new Client("Messi", "Lenardo", "Number1", "Hdmf346",5673,242);
        listOfClients[17] = new Client("Ronaldo", "Christiano", "SkillOverTalent", "Hfsgh",673,0);
        listOfClients[18] = new Client("Mario", "Bros", "MarioB", "NJhjfks",3745,50);
        listOfClients[19] = new Client("Lugi", "Bros", "LugiB", "msejsaka",267,77);
    }

    @Override
    public boolean transfer(String from,double amount)
    {
        if(from.equals("checking"))
        {
            if(amount < getLastAccessed().getCheckingBalance() || amount == getLastAccessed().getCheckingBalance())
            {
                getLastAccessed().setCheckingBalance(getLastAccessed().getCheckingBalance() - amount);
                getLastAccessed().setSavingBalance(getLastAccessed().getSavingBalance()+amount);
                 String view =myFormat.format(amount) + " was sent to the savings account. TRANSFER. ";
                lastAccessed.appendTransaction(view);
                return true;
            }
            return false;
        }

        if(from.equals("saving"))
        {
            if(amount < getLastAccessed().getSavingBalance() || amount == getLastAccessed().getSavingBalance())
            {
                getLastAccessed().setSavingBalance(getLastAccessed().getSavingBalance() - amount);
                getLastAccessed().setCheckingBalance(getLastAccessed().getCheckingBalance()+amount);
                String view = myFormat.format(amount) + " to checking account. TRANSFER. ";
                lastAccessed.appendTransaction(view);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean billPay(String myBills,double amount)
    {
        if(amount < getLastAccessed().getCheckingBalance() || amount == getLastAccessed().getCheckingBalance())
        {
            getLastAccessed().setCheckingBalance(getLastAccessed().getCheckingBalance() - amount);
            lastAccessed.appendTransaction( myFormat.format(amount)+" was used to pay "+ myBills +". BillPay");
            return true;
        }
        return false;
    }

    @Override
    public boolean withdraw(double amount, String y)
    {
        if(y.equals("saving"))
        {
            if(amount<getLastAccessed().getSavingBalance() || amount==getLastAccessed().getSavingBalance())
            {
                getLastAccessed().setSavingBalance(getLastAccessed().getSavingBalance()-amount);
                lastAccessed.appendTransaction(myFormat.format(amount) +" savings."+" WITHDRAW ");
                return true;
            }
            else
                return false;
        }
        else
        {
            if(amount<getLastAccessed().getCheckingBalance() || amount==getLastAccessed().getCheckingBalance())
            {
                getLastAccessed().setCheckingBalance(getLastAccessed().getCheckingBalance()-amount);
                lastAccessed.appendTransaction(myFormat.format(amount) +" checking"+" WITHDRAW ");
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    @Override
    public boolean deposit(double x,String y)
    {
        if(y.equals("saving"))
        {
            getLastAccessed().setSavingBalance(getLastAccessed().getSavingBalance() + x);
            lastAccessed.appendTransaction(myFormat.format(x) +" was deposited into savings. "+" DEPOSIT");
            return true;
        }
        else
        {
            getLastAccessed().setCheckingBalance(getLastAccessed().getCheckingBalance() + x);
            lastAccessed.appendTransaction(myFormat.format(x) +" was deposited into checking. "+" DEPOSIT");
            return true;
        }
    }

    @Override
    public boolean validate(String username,String password)
    {
        if(username.equals("") || password.equals(""))
            return false;

        for(int i=0;i<listOfClients.length;i++)
        {
            if(listOfClients[i].getUserName().equals(username) && listOfClients[i].getPassword().equals(password))
            {
                lastAccessed=listOfClients[i];
                return true;
            }

            if(listOfClients.length-1==i)
            {
                return false;
            }
        }
        return false;///this should never execute
    }

    public static Client getLastAccessed() {return lastAccessed;}
}
