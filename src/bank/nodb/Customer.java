package bank.nodb;

import java.util.LinkedHashMap;

/**
 * Each {@code Customer} represents a customer.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class Customer {

	/**
	 * The customer number of this {@code Customer}.
	 */
	protected String customerNumber;

	/**
	 * The ZIP code of this {@code Customer}
	 */
	protected int zipCode;

	/**
	 * Constructs a {@code Customer}.
	 * 
	 * @param customerNumber
	 *            the customer number of the {@code Customer}
	 * @param zipCode
	 *            the Customer of the {@code Customer}
	 */
	public Customer(String customerNumber, int zipCode) {
		this.customerNumber = customerNumber;
		this.zipCode = zipCode;
	}

	/**
	 * Returns the customer number of this {@code Customer}.
	 * 
	 * @return the customer number of this {@code Customer}
	 */
	public String customerNumber() {
		return customerNumber;
	}

	/**
	 * Returns the ZIP code of this {@code Customer}.
	 * 
	 * @return the ZIP code of this {@code Customer}
	 */
	public int zipCode() {
		return zipCode;
	}

	/**
	 * Returns a string representation of this {@code Customer}.
	 * 
	 * @return a string representation of this {@code Customer}
	 */
	public String toString() {
		var m = new LinkedHashMap<String, Object>();
		m.put("customerNumber", customerNumber);
		m.put("zipCode", zipCode);
		return m.toString();
	}

}
