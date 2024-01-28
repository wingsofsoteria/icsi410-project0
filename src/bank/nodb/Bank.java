package bank.nodb;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Each {@code Bank} represents a bank.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 */
public class Bank {

	/**
	 * The name of this {@code Bank}.
	 */
	protected String name;

	/**
	 * The {@code Customer}s of this {@code Bank}.
	 */
	protected Map<String, Customer> customers = new TreeMap<String, Customer>();

	/**
	 * The {@code BankAccount}s managed by this {@code Bank}.
	 */
	protected Map<String, BankAccount> accounts = new TreeMap<String, BankAccount>();

	/**
	 * Constructs a {@code Bank} instance.
	 * 
	 * @param name
	 *            the name of the {@code Bank}
	 */
	public Bank(String name) {
		this.name = name;
	}

	/**
	 * Registers the specified {@code Customer}.
	 * 
	 * @param customer
	 *            a {@code Customer}
	 */
	public void register(Customer customer) {
		customers.put(customer.customerNumber(), customer);
	}

	/**
	 * Registers the specified {@code BankAccount}.
	 * 
	 * @param bankAccount
	 *            a {@code BankAccount}
	 */
	public void register(BankAccount bankAccount) {
		accounts.put(bankAccount.accountNumber(), bankAccount);
	}

	/**
	 * The main method of the {Bank} class.
	 * 
	 * @param args
	 *            the program arguments
	 * @throws Exception
	 *             if an error occurs
	 */
	public static void main(String[] args) throws Exception {
		var bank = new Bank("Sample");
		addData(bank, 10);

		System.out.println("customers:");
		bank.customers.values().stream().forEach(c -> System.out.println(c));
		System.out.println("");

		System.out.println("accounts:");
		bank.accounts.values().stream().forEach(a -> System.out.println(a));
		System.out.println("");

		System.out.println("accounts with balance > 10,000:");
		bank.queryBankAccounts(10000).forEach(a -> System.out.println(a));
		System.out.println("");

		System.out.println("ZIP code of the owner of account A10: " + bank.queryZipCode("A10"));
		System.out.println("ZIP code of the owner of account A11: " + bank.queryZipCode("A11"));
		System.out.println("ZIP code of the owner of account A15: " + bank.queryZipCode("A15"));
		System.out.println("");

		System.out.println("account number, ZIP code");
		bank.queryAccountNumberZipCode().forEach(e -> System.out.println(e.getKey() + ", " + e.getValue()));
		System.out.println("");

		System.out.println("sum of account balances: " + bank.queryTotalAccountBalance());
		System.out.println("");

		System.out.println("maximum of account balances: " + bank.queryMaximumAccountBalance());
		System.out.println("");

		System.out.println("ZIP code, number of customers");
		bank.queryZipCodeCustomers().forEach(e -> System.out.println(e.getKey() + ", " + e.getValue()));
		System.out.println("");

		System.out.println("customers with ZIP code 12222:");
		bank.queryCustomers(12222).forEach(c -> System.out.println(c));
		System.out.println("");

		System.out.println("customers with ZIP code 12225:");
		bank.queryCustomers(12225).forEach(c -> System.out.println(c));
		System.out.println("");

		System.out.println("ZIP code, number of accounts");
		bank.queryZipCodeAccounts().forEach(e -> System.out.println(e.getKey() + ", " + e.getValue()));
		System.out.println("");

		System.out.println("accounts with the maximum balance");
		bank.queryMaxBalanceBankAccounts().forEach(a -> System.out.println(a));
	}

	/**
	 * Returns a {@code Stream} of {@code BankAccount}s, among the {@code BankAccount}s of the specified {@code Bank},
	 * whose balance is greater than the specified amount.
	 * 
	 * @param amount
	 *            an amount
	 * @return a {@code Stream} of {@code BankAccount}s, among the {@code BankAccount}s of the specified {@code Bank},
	 *         whose balance is greater than the specified amount
	 */
	public Stream<BankAccount> queryBankAccounts(double amount) {
		return accounts.values().stream().filter(b -> b.balance() > amount);
	}

	/**
	 * Returns the sum of the balances of the {@code BankAccount}s managed by the specified {@code Bank}.
	 * 
	 * @return the sum of the balances of the {@code BankAccount}s managed by the specified {@code Bank}
	 */
	public Double queryTotalAccountBalance() {
		return accounts.values().stream().mapToDouble(a -> a.balance()).sum();
	}

	/**
	 * Returns the maximum of the balances of the {@code BankAccount}s managed by the specified {@code Bank}.
	 * 
	 * @return the maximum of the balances of the {@code BankAccount}s managed by the specified {@code Bank}
	 */
	public Double queryMaximumAccountBalance() {
		return accounts.values().stream().mapToDouble(a -> a.balance()).max().getAsDouble();
	}

	/**
	 * Returns the ZIP code of the owner of the specified {@code BankAccount}.
	 * 
	 * @param accountNumber
	 *            the account number of a {@code BankAccount}
	 * @return the ZIP code of the owner of the specified {@code BankAccount}
	 */
	public Integer queryZipCode(String accountNumber) {
		var account = accounts.get(accountNumber);
		if (account == null)
			return null;
		var customer = customers.get(account.customerNumber());
		return customer == null ? null : customer.zipCode();
	}

	/**
	 * Returns, for each {@code BankAccount}, the account number and the ZIP code of the owner of that
	 * {@code BankAccount}
	 * 
	 * @return a {@code Stream} of {@code Entry<String, Integer>} instances each representing the account number and the
	 *         ZIP code of the owner of a {@code BankAccount}
	 */
	public Stream<Map.Entry<String, Integer>> queryAccountNumberZipCode() {
		return accounts.values().stream()
				.map(a -> Map.entry(a.accountNumber(), customers.get(a.customerNumber()).zipCode()));
	}

	/**
	 * Finds the number of {@code Customer}s for each ZIP code.
	 * 
	 * @return a {@code Stream} of {@code Entry} instances each containing a ZIP code and the number of
	 *         {@code Customer}s
	 */
	public Stream<Entry<Integer, Long>> queryZipCodeCustomers() {
		var summary = customers.values().stream()
				.collect(Collectors.groupingBy(c -> c.zipCode(), Collectors.counting()));
		return summary.entrySet().stream();
	}

	/**
	 * Returns a {@code Stream} of {@code Customer}s whose ZIP code is equal to the specified ZIP code.
	 * 
	 * @param zipCode
	 *            a ZIP code
	 * @return a {@code Stream} of {@code Customer}s whose ZIP code is equal to the specified ZIP code
	 */
	public Stream<Customer> queryCustomers(int zipCode) {
		// TODO add some code here (it must not throw the UnsupportedOperationException)
		return this.customers.values().stream().filter(c -> c.zipCode == zipCode);
//		throw new UnsupportedOperationException();
	}

	/**
	 * Finds the number of {@code BankAccount}s for each ZIP code.
	 * 
	 * @return a {@code Stream} of {@code Entry} instances each containing a ZIP code and the number of
	 *         {@code BankAccount}s
	 */
	public Stream<Entry<Integer, Long>> queryZipCodeAccounts() {
		// TODO add some code here (it must not throw the UnsupportedOperationException)
		var summary = accounts.values().stream().collect(Collectors.groupingBy(a -> customers.get(a.customerNumber()).zipCode(), Collectors.counting()));
		return summary.entrySet().stream();
	}

	/**
	 * Finds the {@code BankAccount}s with the maximum balance (i.e., the {@code BankAccount}s whose balance is not
	 * smaller than any other {@code BankAccount}).
	 * 
	 * @return a {@code Stream} of the {@code BankAccount}s with the maximum balance
	 */
	public Stream<BankAccount> queryMaxBalanceBankAccounts() {
		// TODO add some code here (it must not throw the UnsupportedOperationException)
		return accounts.values().stream().filter(a->a.balance() >= queryMaximumAccountBalance());
	}

	/**
	 * Adds data to the specified {@code Bank}.
	 * 
	 * @param bank
	 *            a {@code Bank}
	 * @param customers
	 *            the number of {@code Customer}s of the {@code Bank}
	 */
	public static void addData(Bank bank, int customers) {
		BiConsumer<String, Integer> addCustomer = (s, i) -> bank.register(new Customer(s, i));
		BiConsumer<String, Map.Entry<String, Double>> addAccount = (s, e) -> bank
				.register(new BankAccount(s, e.getKey(), e.getValue()));
		addData(customers, addCustomer, addAccount);
	}

	/**
	 * Adds data related to customers and accounts of bank.
	 * 
	 * @param customers
	 *            the number of customers of the bank
	 * @param addCustomer
	 *            a {@code BiConsumer} for adding each customer
	 * @param addAccount
	 *            a {@code BiConsumer} for adding each account
	 */
	public static void addData(int customers, BiConsumer<String, Integer> addCustomer,
			BiConsumer<String, Map.Entry<String, Double>> addAccount) {
		double[] balances = { 1000.0, 10000.0, 100000.0 };
		int[] zipCodes = { 12222, 12223, 12224, 12225 };
		int digits = (int) Math.ceil(Math.log10(2 * customers));
		IntStream.range(0, customers).forEach(i -> {
			var customerID = String.format("C%0" + digits + "d", i);
			addCustomer.accept(customerID, zipCodes[i % zipCodes.length]);
			addAccount.accept(String.format("A%0" + digits + "d", 2 * i),
					Map.entry(customerID, balances[(2 * i) % balances.length]));
			addAccount.accept(String.format("A%0" + digits + "d", 2 * i + 1),
					Map.entry(customerID, balances[(2 * i + 1) % balances.length]));
			if (i == customers - 1)
				addAccount.accept(String.format("A%0" + digits + "d", 2 * i + 2),
						Map.entry(customerID, balances[(2 * i + 2) % balances.length]));
		});
	}

}
