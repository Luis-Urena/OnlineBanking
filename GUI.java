import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.math.BigDecimal;

public class GUI extends Application
{
    private Stage window,errorWindow;
    private Scene menuScene,loginScene,depositScene,withdrawScene;
    private Bank bank = new Bank();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        window=primaryStage;
        window.setTitle("Bank Login");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);
        Label labelName = new Label("Username");
        GridPane.setConstraints(labelName,0,0);
        Label labelPass = new Label("Password");
        GridPane.setConstraints(labelPass,0,1);
        TextField textName = new TextField("");
        textName.setPromptText("Enter Username Here");
        GridPane.setConstraints(textName,1,0);
        TextField textPass = new TextField("");
        textPass.setPromptText("Enter Password Here");
        GridPane.setConstraints(textPass,1,1);
        Button loginButton = new Button("Login");
        loginButton.setOnAction(event ->
        {
            boolean check = bank.validate(textName.getText(),textPass.getText());
            if(check)
            {
                menu();
                textPass.clear();
            }
            else
            {
                error(1);
                textPass.clear();
            }

        });
        GridPane.setConstraints(loginButton,1,2);

        grid.getChildren().addAll(labelName,labelPass,textName,textPass,loginButton);
        loginScene = new Scene(grid,300,200);
        loginScene.getStylesheets().add("Custom.css");
        window.setScene(loginScene);
        window.show();



    }

    private void menu()
    {
        window.setTitle("Welcome! "+Bank.getLastAccessed().getFirstName()+" "+Bank.getLastAccessed().getLastName());
        GridPane menuGrid = new GridPane();
        menuGrid.setPadding(new Insets(10));
        menuGrid.setVgap(8);
        menuGrid.setHgap(10);
        //These are descriptive labels for the buttons
        //----------------------------------------------------------------------------------------------------------------------
        Label topDescription = new Label("Please select the one of the following.");
        GridPane.setConstraints(topDescription,0,0);
        Label withdrawLabel = new Label("This allows you to withdraw money from your Checking and Saving Account.");
        GridPane.setConstraints(withdrawLabel,1,2);
        Label depositLabel = new Label("This allows you to deposit money into your Checking and Saving Account.");
        GridPane.setConstraints(depositLabel,1,3);
        Label checkBalanceLabel = new Label("This allows you to check your balances on Checking and Saving Account.");
        GridPane.setConstraints(checkBalanceLabel,1,4);
        Label transferLabel = new Label("This allows you to transfer money between Accounts.");
        GridPane.setConstraints(transferLabel,1,5);
        Label billPayLabel = new Label("This allows you to use your Checking account to pay a Bill.");
        GridPane.setConstraints(billPayLabel,1,6);
        Label viewStatementLabel = new Label("This allows you to see all transactions done today.");
        GridPane.setConstraints(viewStatementLabel,1,7);


        //Menu Buttons Begin Here
        //------------------------------------------------------------------------------------------------------------
        Button withdrawButton = new Button("Withdraw");
        GridPane.setConstraints(withdrawButton,0,2);
        withdrawButton.setOnAction(event -> withdrawSelection());


        Button depositButton = new Button("Deposit");
        GridPane.setConstraints(depositButton,0,3);
        depositButton.setOnAction(event ->depositSelection());

        Button checkBalanceButton = new Button("Check Balance");
        GridPane.setConstraints(checkBalanceButton,0,4);
        checkBalanceButton.setOnAction(event ->checkBalanceSelection());

        Button transferButton = new Button("Transfer");
        GridPane.setConstraints(transferButton,0,5);
        transferButton.setOnAction(e ->transferSelection() );

        Button viewStatementButton = new Button("View Statement");
        GridPane.setConstraints(viewStatementButton,0,7);
        viewStatementButton.setOnAction(event -> viewStatementSelection());

        Button billPayButton = new Button("Bill Pay");
        GridPane.setConstraints(billPayButton,0,6);
        billPayButton.setOnAction(event -> billPaySelection());

        Button returnToLogin = new Button("Sign Out");
        returnToLogin.getStyleClass().add("button-red");
        GridPane.setConstraints(returnToLogin,2,8);
        returnToLogin.setOnAction(event ->
        {
            window.setScene(loginScene);
            window.setTitle("Bank Login");

        });

        menuGrid.getChildren().addAll(topDescription,withdrawButton,depositButton,checkBalanceButton,transferButton,viewStatementButton,billPayButton,returnToLogin,withdrawLabel,depositLabel,checkBalanceLabel,transferLabel,viewStatementLabel,billPayLabel);
        menuScene = new Scene(menuGrid,900,400);
        menuScene.getStylesheets().add("Custom.css");
        window.setScene(menuScene);
        window.show();
    }

    //this will trigger if there is a problem with any information entered that was not in the correct format
    private void error(int code)
    {
        errorWindow = new Stage();
        errorWindow.initModality(Modality.APPLICATION_MODAL);
        errorWindow.setTitle("ERROR ");
        GridPane errorGrid = new GridPane();
        errorGrid.setPadding(new Insets(10));
        errorGrid.setVgap(8);
        errorGrid.setHgap(10);
        Label label;

        switch(code)
        {
            case 1:
                label = new Label("The data you entered is incorrect.");
                break;
            case 2:
                label = new Label("Enter only positive numbers.");
                break;
            case 3:
                label= new Label("Please ensure to select a button.");
                break;
            case 4:
                label = new Label("Insufficient Funds");
                break;
            case 5:
                label = new Label("Not a real monetary value.");
                break;
            default:
                label = new Label("The data you entered is incorrect.");
        }
        GridPane.setConstraints(label,0,0);
        Button ok = new Button("OK");
        GridPane.setConstraints(ok,1,1);
        ok.setOnAction(e-> errorWindow.close());
        errorGrid.getChildren().addAll(label,ok);
        Scene scene = new Scene(errorGrid);
        scene.getStylesheets().add("Custom.css");
        errorWindow.setScene(scene);
        errorWindow.showAndWait();
    }


    //this will trigger when the deposit button is selected
    private void depositSelection()
    {
        GridPane depositGrid = new GridPane();
        depositGrid.setPadding(new Insets(10));
        depositGrid.setVgap(8);
        depositGrid.setHgap(10);

        Label prompt = new Label("Please enter amount to DEPOSIT and select either Checking or Savings.");
        GridPane.setConstraints(prompt,0,0);
        TextField promptText =new TextField("");
        promptText.setPromptText("Enter amount here using only numbers");
        GridPane.setConstraints(promptText,0,1);
        Button returnToMenu = new Button("Return to Menu");
        GridPane.setConstraints(returnToMenu,2,2);
        returnToMenu.setOnAction(e -> window.setScene(menuScene));
        RadioButton checkingRB = new RadioButton();
        checkingRB.setText("Checking Account");
        RadioButton savingRB = new RadioButton();
        savingRB.setText("Saving Account");
        final ToggleGroup group1 = new ToggleGroup();
        checkingRB.setToggleGroup(group1);
        checkingRB.setStyle("-fx-text-fill: #ffffff;");
        savingRB.setToggleGroup(group1);
        savingRB.setStyle("-fx-text-fill: #ffffff;");
        GridPane.setConstraints(checkingRB,1,0);
        GridPane.setConstraints(savingRB,1,1);
        Button enterValue = new Button("Enter");
        GridPane.setConstraints(enterValue,1,2);
        enterValue.setOnAction(e ->
        {
            if(isANumber(promptText))
            {
                if (savingRB.isSelected())
                {
                    if(bank.deposit(Double.parseDouble(promptText.getText()),"saving"))
                    {
                        transactionComplete();
                        promptText.clear();
                    }
                    else
                    {
                        error(4);
                        promptText.clear();
                    }
                }
                else if (checkingRB.isSelected())
                {
                    if(bank.deposit(Double.parseDouble(promptText.getText()),"checking"))
                    {
                        transactionComplete();
                        promptText.clear();
                    }
                    else
                    {
                        error(4);
                        promptText.clear();
                    }
                }
                else
                {
                    error(3);
                    promptText.clear();
                }
            }
        } );

        depositGrid.getChildren().addAll(prompt,promptText,savingRB,checkingRB,enterValue,returnToMenu);
        depositScene = new Scene(depositGrid);
        depositScene.getStylesheets().add("Custom.css");
        window.setScene(depositScene);
        window.show();
    }


    //check to see what is in text field is a number
    private boolean isANumber(TextField x)
    {
        try
        {
            double num = Double.parseDouble(x.getText());
            if(!(num>0))
            {
                error(2);
                x.clear();
                return false;
            }
            if((BigDecimal.valueOf(num).scale() > 2))
            {
                error(5);
                x.clear();
                return false;
            }

        }
        catch(NumberFormatException e)
        {
            error(1);
            x.clear();
            return false;
        }
        return true;
    }

    private void transactionComplete()
    {
        Stage TCWindow = new Stage();
        TCWindow.initModality(Modality.APPLICATION_MODAL);
        TCWindow.setTitle("Transaction Complete");
        GridPane TCGrid = new GridPane();
        TCGrid.setPadding(new Insets(10));
        TCGrid.setVgap(8);
        TCGrid.setHgap(10);
        Label label = new Label("Transaction has been processed and Saved.");
        GridPane.setConstraints(label,0,0);
        Button ok = new Button("OK");
        GridPane.setConstraints(ok,1,1);
        ok.setOnAction(e-> TCWindow.close());
        TCGrid.getChildren().addAll(label,ok);
        Scene scene = new Scene(TCGrid);
        scene.getStylesheets().add("Custom.css");
        TCWindow.setScene(scene);
        TCWindow.showAndWait();
    }

    private void withdrawSelection()
    {
        GridPane withdrawGrid = new GridPane();
        withdrawGrid.setPadding(new Insets(10));
        withdrawGrid.setVgap(8);
        withdrawGrid.setHgap(10);

        Label prompt = new Label("Please enter amount to WITHDRAW and select either Checking or Savings.");
        GridPane.setConstraints(prompt,0,0);
        TextField promptText =new TextField("");
        promptText.setPromptText("Enter amount here using only numbers.");
        GridPane.setConstraints(promptText,0,1);
        Button returnToMenu = new Button("Return to Menu");
        GridPane.setConstraints(returnToMenu,2,2);
        returnToMenu.setOnAction(e -> window.setScene(menuScene));
        RadioButton checkingRB = new RadioButton();
        checkingRB.setText("Checking Account");
        RadioButton savingRB = new RadioButton();
        savingRB.setText("Saving Account");
        final ToggleGroup group1 = new ToggleGroup();
        checkingRB.setToggleGroup(group1);
        checkingRB.setStyle("-fx-text-fill: #ffffff;");
        savingRB.setToggleGroup(group1);
        savingRB.setStyle("-fx-text-fill: #ffffff;");
        GridPane.setConstraints(checkingRB,1,0);
        GridPane.setConstraints(savingRB,1,1);
        Button enterValue = new Button("Enter");
        GridPane.setConstraints(enterValue,1,2);
        enterValue.setOnAction(e ->
        {
            if(isANumber(promptText))
            {
                if (savingRB.isSelected())
                {
                    if(bank.withdraw(Double.parseDouble(promptText.getText()),"saving"))
                    {
                        transactionComplete();
                        promptText.clear();
                    }
                    else
                    {
                        error(4);
                        promptText.clear();
                    }
                }
                else if (checkingRB.isSelected())
                {
                    if(bank.withdraw(Double.parseDouble(promptText.getText()),"checking"))
                    {
                        transactionComplete();
                        promptText.clear();
                    }
                    else
                    {
                        error(4);
                        promptText.clear();
                    }
                }
                else
                {
                    error(3);
                    promptText.clear();
                }
            }
        } );

        withdrawGrid.getChildren().addAll(prompt,promptText,savingRB,checkingRB,enterValue,returnToMenu);
        withdrawScene = new Scene(withdrawGrid);
        withdrawScene.getStylesheets().add("Custom.css");
        window.setScene(withdrawScene);
        window.show();
    }

    private void transferSelection()
    {
        GridPane withdrawGrid = new GridPane();
        withdrawGrid.setPadding(new Insets(10));
        withdrawGrid.setVgap(8);
        withdrawGrid.setHgap(10);

        RadioButton checkingRB = new RadioButton();
        checkingRB.setText("Checking Account");

        RadioButton savingRB = new RadioButton();
        savingRB.setText("Saving Account");
        final ToggleGroup group1 = new ToggleGroup();
        checkingRB.setToggleGroup(group1);
        checkingRB.setStyle("-fx-text-fill: #ffffff;");
        savingRB.setToggleGroup(group1);
        savingRB.setStyle("-fx-text-fill: #ffffff;");

        Label prompt = new Label("Transfer from:");
        Label transferTo = new Label();
        TextField amount = new TextField();
        amount.setPromptText("Enter amount in Integers only.");

        Button enter = new Button("Enter");
        enter.setOnAction(event ->
        {
            if(isANumber(amount))
            {
                if (savingRB.isSelected())
                {
                    if(bank.transfer("saving",Double.parseDouble(amount.getText())))
                    {
                        transactionComplete();
                        amount.clear();
                    }
                    else
                    {
                        error(4);
                        amount.clear();
                    }
                }
                else if (checkingRB.isSelected())
                {
                    if(bank.transfer("checking",Double.parseDouble(amount.getText())))
                    {
                        transactionComplete();
                        amount.clear();
                    }
                    else
                    {
                        error(4);
                        amount.clear();
                    }
                }
                else
                {
                    error(3);
                    amount.clear();
                }
            }
        });

        Button returnToMenu = new Button("Return to Menu");
        returnToMenu.setOnAction(event -> window.setScene(menuScene));

        checkingRB.setOnAction(event ->
        {
            GridPane wGrid = new GridPane();
            wGrid.setPadding(new Insets(10));
            wGrid.setVgap(8);
            wGrid.setHgap(10);
            transferTo.setText("Transfer to: Savings");
            GridPane.setConstraints(transferTo,0,3);
            GridPane.setConstraints(prompt,0,0);
            GridPane.setConstraints(checkingRB,0,1);
            GridPane.setConstraints(savingRB,1,1);
            GridPane.setConstraints(amount,0,2);
            GridPane.setConstraints(enter,0,4);
            GridPane.setConstraints(returnToMenu,1,4);
            wGrid.getChildren().addAll(prompt,savingRB,checkingRB,amount,enter,returnToMenu,transferTo);
            Scene s = new Scene(wGrid,350,200);
            s.getStylesheets().add("Custom.css");
            window.setScene(s);
            window.show();
        });

        savingRB.setOnAction(event ->
        {
            GridPane wGrid = new GridPane();
            wGrid.setPadding(new Insets(10));
            wGrid.setVgap(8);
            wGrid.setHgap(10);
            transferTo.setText("Transfer to: Checking");
            GridPane.setConstraints(transferTo,0,3);
            GridPane.setConstraints(prompt,0,0);
            GridPane.setConstraints(checkingRB,0,1);
            GridPane.setConstraints(savingRB,1,1);
            GridPane.setConstraints(amount,0,2);
            GridPane.setConstraints(enter,0,4);
            GridPane.setConstraints(returnToMenu,1,4);
            wGrid.getChildren().addAll(prompt,savingRB,checkingRB,amount,enter,returnToMenu,transferTo);
            Scene s = new Scene(wGrid,350,200);
            s.getStylesheets().add("Custom.css");
            window.setScene(s);
            window.show();
        });

        GridPane.setConstraints(prompt,0,0);
        GridPane.setConstraints(checkingRB,0,1);
        GridPane.setConstraints(savingRB,1,1);
        GridPane.setConstraints(amount,0,2);
        GridPane.setConstraints(enter,0,4);
        GridPane.setConstraints(returnToMenu,1,4);


        withdrawGrid.getChildren().addAll(prompt,savingRB,checkingRB,amount,enter,returnToMenu);
        Scene scene = new Scene(withdrawGrid,350,200);
        scene.getStylesheets().add("Custom.css");
        window.setScene(scene);
        window.show();

    }

    private void checkBalanceSelection()
    {
        Stage TCWindow = new Stage();
        TCWindow.initModality(Modality.APPLICATION_MODAL);
        TCWindow.setTitle("Account Balances!");
        GridPane TCGrid = new GridPane();
        TCGrid.setPadding(new Insets(10));
        TCGrid.setVgap(8);
        TCGrid.setHgap(10);
        Label label = new Label("Hello! "+Bank.getLastAccessed().getFirstName()+" "+Bank.getLastAccessed().getLastName()+"\nChecking Account Balance: "+Bank.getLastAccessed().getCheckingBalance()+" \nSaving Account Balance: "+Bank.getLastAccessed().getSavingBalance());
        GridPane.setConstraints(label,0,0);
        Button ok = new Button("OK");
        GridPane.setConstraints(ok,1,1);
        ok.setOnAction(e-> TCWindow.close());
        TCGrid.getChildren().addAll(label,ok);
        Scene scene = new Scene(TCGrid);
        scene.getStylesheets().add("Custom.css");
        TCWindow.setScene(scene);
        TCWindow.showAndWait();
    }

    private void viewStatementSelection()
    {
        Stage TCWindow = new Stage();
        TCWindow.initModality(Modality.APPLICATION_MODAL);
        TCWindow.setTitle("Today's Transactions");
        GridPane TCGrid = new GridPane();
        TCGrid.setPadding(new Insets(10));
        TCGrid.setVgap(8);
        TCGrid.setHgap(10);

        Label welcome = new Label("Welcome! These are your transactions for the day.");
        GridPane.setConstraints(welcome,0,0);

        Label label = new Label(Bank.getLastAccessed().getListOfTransactions());
        GridPane.setConstraints(label,0,1);
        Button ok = new Button("OK");
        GridPane.setConstraints(ok,0,2);
        ok.setOnAction(e-> TCWindow.close());
        TCGrid.getChildren().addAll(label,ok,welcome);
        Scene scene = new Scene(TCGrid,400,400);
        scene.getStylesheets().add("Custom.css");
        TCWindow.setScene(scene);
        TCWindow.showAndWait();
    }

    private void billPaySelection()
     {
        GridPane billGrid = new GridPane();
        billGrid.setPadding(new Insets(10));
        billGrid.setVgap(8);
        billGrid.setHgap(10);

        RadioButton Sears = new RadioButton();
        Sears.setText("Sears");
        RadioButton JCPenny = new RadioButton();
        JCPenny.setText("JCPenny");
        final ToggleGroup group1 = new ToggleGroup();
        Sears.setToggleGroup(group1);
        Sears.setStyle("-fx-text-fill: #ffffff;");
        JCPenny.setToggleGroup(group1);
        JCPenny.setStyle("-fx-text-fill: #ffffff;");

        RadioButton GameStop = new RadioButton();
        GameStop.setText("GameStop");
        RadioButton tMobile = new RadioButton();
        tMobile.setText("T-Mobile");
        GameStop.setToggleGroup(group1);
        GameStop.setStyle("-fx-text-fill: #ffffff;");
        tMobile.setToggleGroup(group1);
        tMobile.setStyle("-fx-text-fill: #ffffff;");

        RadioButton microsoft = new RadioButton();
        microsoft.setText("Microsoft");
        RadioButton Sony = new RadioButton();
        Sony.setText("Sony");
        microsoft.setToggleGroup(group1);
        microsoft.setStyle("-fx-text-fill: #ffffff;");
        Sony.setToggleGroup(group1);
        Sony.setStyle("-fx-text-fill: #ffffff;");

        RadioButton Disney = new RadioButton();
        Disney.setText("Disney");
        RadioButton Amazon = new RadioButton();
        Amazon.setText("Amazon");
        Disney.setToggleGroup(group1);
        Disney.setStyle("-fx-text-fill: #ffffff;");
        Amazon.setToggleGroup(group1);
        Amazon.setStyle("-fx-text-fill: #ffffff;");

        RadioButton Apple = new RadioButton();
        Apple.setText("Apple");
        RadioButton Walmart = new RadioButton();
        Walmart.setText("Wal-mart");
        Apple.setToggleGroup(group1);
        Apple.setStyle("-fx-text-fill: #ffffff;");
        Walmart.setToggleGroup(group1);
        Walmart.setStyle("-fx-text-fill: #ffffff;");

        TextField amount = new TextField();
        amount.setPromptText("Enter amount only in Integer value");
        Label prompt = new Label("Enter amount to be paid:");

        Button returnToMenu = new Button("Return to Menu");
        returnToMenu.setOnAction(event -> window.setScene(menuScene));

        Button enter = new Button("Enter");
        enter.setOnAction(event ->
        {
            if(isANumber(amount))
            {
                if (Sears.isSelected())
                {
                    if(bank.billPay("sears",Double.parseDouble(amount.getText())))
                    {
                        transactionComplete();
                        amount.clear();
                    }
                    else
                    {
                        error(4);
                        amount.clear();
                    }
                }
                else if (JCPenny.isSelected())
                {
                    if(bank.billPay("jcpenny",Double.parseDouble(amount.getText())))
                    {
                        transactionComplete();
                        amount.clear();
                    }
                    else
                    {
                        error(4);
                        amount.clear();
                    }
                }
                else if(GameStop.isSelected())
                {
                    if(bank.billPay("gamestop",Double.parseDouble(amount.getText())))
                    {
                        transactionComplete();
                        amount.clear();
                    }
                    else
                    {
                        error(4);
                        amount.clear();
                    }
                }
                else if(tMobile.isSelected())
                {
                    if(bank.billPay("tmobile",Double.parseDouble(amount.getText())))
                    {
                        transactionComplete();
                        amount.clear();
                    }
                    else
                    {
                        error(4);
                        amount.clear();
                    }
                }
                else if(microsoft.isSelected())
                {
                    if(bank.billPay("microsoft",Double.parseDouble(amount.getText())))
                    {
                        transactionComplete();
                        amount.clear();
                    }
                    else
                    {
                        error(4);
                        amount.clear();
                    }
                }
                else if(Sony.isSelected())
                {
                    if(bank.billPay("sony",Double.parseDouble(amount.getText())))
                    {
                        transactionComplete();
                        amount.clear();
                    }
                    else
                    {
                        error(4);
                        amount.clear();
                    }
                }
                else if(Disney.isSelected())
                {
                    if(bank.billPay("disney",Double.parseDouble(amount.getText())))
                    {
                        transactionComplete();
                        amount.clear();
                    }
                    else
                    {
                        error(4);
                        amount.clear();
                    }
                }
                else if(Amazon.isSelected())
                {
                    if(bank.billPay("amazon",Double.parseDouble(amount.getText())))
                    {
                        transactionComplete();
                        amount.clear();
                    }
                    else
                    {
                        error(4);
                        amount.clear();
                    }
                }
                else if(Apple.isSelected())
                {
                    if(bank.billPay("apple",Double.parseDouble(amount.getText())))
                    {
                        transactionComplete();
                        amount.clear();
                    }
                    else
                    {
                        error(4);
                        amount.clear();
                    }
                }
                else if(Walmart.isSelected())
                {
                    if(bank.billPay("walmart",Double.parseDouble(amount.getText())))
                    {
                        transactionComplete();
                        amount.clear();
                    }
                    else
                    {
                        error(4);
                        amount.clear();
                    }
                }
                else
                {
                    error(3);
                    amount.clear();
                }
            }
        });

        GridPane.setConstraints(prompt,0,0);
        GridPane.setConstraints(amount,0,1);
        GridPane.setConstraints(enter,0,2);
        GridPane.setConstraints(returnToMenu,0,3);
        GridPane.setConstraints(Sears,2,0);
        GridPane.setConstraints(JCPenny,2,1);
        GridPane.setConstraints(GameStop,2,2);
        GridPane.setConstraints(tMobile,2,3);
        GridPane.setConstraints(microsoft,2,4);
        GridPane.setConstraints(Sony,3,0);
        GridPane.setConstraints(Disney,3,1);
        GridPane.setConstraints(Amazon,3,2);
        GridPane.setConstraints(Apple,3,3);
        GridPane.setConstraints(Walmart,3,4);

        billGrid.getChildren().addAll(prompt,amount,enter,returnToMenu,Sears,JCPenny,GameStop,tMobile,microsoft,Sony,Disney,Amazon,Apple,Walmart);
        Scene scene = new Scene(billGrid,500,250 );
        scene.getStylesheets().add("Custom.css");
        window.setScene(scene);
        window.show();
    }
}
