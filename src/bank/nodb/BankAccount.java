package bank.nodb;

import java.util.LinkedHashMap;

/**
 * Each {@code BankAccount} represents a bank account.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class BankAccount {

	/**
	 * The account number of this {@code BankAccount}.
	 */
	protected String accountNumber;

	/**
	 * The customer number of the owner of this {@code BankAccount}.
	 */
	protected String customerNumber;

	/**
	 * The balance of this {@code BankAccount}
	 */
	protected double balance;

	/**
	 * Constructs a {@code BankAccount} instance.
	 * 
	 * @param accountNumber
	 *            the account number of the {@code BankAccount}
	 * @param customerNumber
	 *            the customer number of the owner of the {@code BankAccount}
	 * @param balance
	 *            the balance of the {@code BankAccount}
	 */
	public BankAccount(String accountNumber, String customerNumber, double balance) {
		this.accountNumber = accountNumber;
		this.customerNumber = customerNumber;
		this.balance = balance;
	}

	/**
	 * Returns the account number of this {@code BankAccount}.
	 * 
	 * @return the account number of this {@code BankAccount}
	 */
	public String accountNumber() {
		return accountNumber;
	}

	/**
	 * Returns the customer number of the owner of this {@code BankAccount}.
	 * 
	 * @return the customer number of the owner of this {@code BankAccount}
	 */
	public String customerNumber() {
		return customerNumber;
	}

	/**
	 * Returns the balance of this {@code BankAccount}.
	 * 
	 * @return the balance of this {@code BankAccount}
	 */
	public double balance() {
		return balance;
	}

	/**
	 * Returns a string representation of this {@code BankAccount}.
	 * 
	 * @return a string representation of this {@code BankAccount}
	 */
	public String toString() {
		var m = new LinkedHashMap<String, Object>();
		m.put("accountNumber", accountNumber);
		m.put("customerNumber", customerNumber);
		m.put("balance", balance);
		return m.toString();
	}

}
