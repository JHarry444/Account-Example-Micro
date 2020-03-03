package com.qa.account.gen.prize.util.exceptions;

public class AccountNotFoundException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1685741676167633805L;

	public AccountNotFoundException(String exception){
        super("Id supplied does not exist. Id: " + exception);
    }

}
