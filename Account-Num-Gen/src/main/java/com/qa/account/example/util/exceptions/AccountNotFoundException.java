package com.qa.account.example.util.exceptions;

public class AccountNotFoundException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8146471539628485259L;

	public AccountNotFoundException(String exception){
        super("Id supplied does not exist. Id: " + exception);
    }

}
