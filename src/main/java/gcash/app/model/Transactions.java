package gcash.app.model;

enum TransactionTypes {
    CASH_IN,
    RECEIVE,
    SEND
}

public class Transactions {
    double amount;
    String phoneNumber = Users.getPhoneNumber();
    

}


