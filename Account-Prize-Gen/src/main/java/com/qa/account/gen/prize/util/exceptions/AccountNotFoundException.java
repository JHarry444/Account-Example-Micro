package com.qa.account.gen.prize.util.exceptions;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String exception){
        super("Id supplied does not exist. Id: " + exception);
    }

}
