package bank.nodb;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * {@code UnitTests} tests the implementations in the {@code bank} package.
 * 
 * @author Jeong-Hyon Hwang (jhh@cs.albany.edu)
 * 
 */
public class UnitTests {

	/**
	 * Tests the Task 1 implementation.
	 * 
	 * @throws Exception
	 *             if an error occurs
	 */
	@Test
	public void task1() throws Exception {
		var bank = sampleBank(10);
		assertEquals(
				"[{customerNumber=C00, zipCode=12222}, {customerNumber=C04, zipCode=12222}, {customerNumber=C08, zipCode=12222}]",
				bank.queryCustomers(12222).toList().toString());
		assertEquals(
				"[{customerNumber=C01, zipCode=12223}, {customerNumber=C05, zipCode=12223}, {customerNumber=C09, zipCode=12223}]",
				bank.queryCustomers(12223).toList().toString());
		assertEquals("[{customerNumber=C03, zipCode=12225}, {customerNumber=C07, zipCode=12225}]",
				bank.queryCustomers(12225).toList().toString());
		bank = sampleBank(20);
		assertEquals(5, bank.queryCustomers(12222).count());
		assertEquals(5, bank.queryCustomers(12223).count());
		assertEquals(5, bank.queryCustomers(12224).count());
		assertEquals(0, bank.queryCustomers(12226).count());
	}

	/**
	 * Tests the Task 2 implementation.
	 * 
	 * @throws Exception
	 *             if an error occurs
	 */
	@Test
	public void task2() throws Exception {
		var bank = sampleBank(10);
		assertEquals("[12224=4, 12225=4, 12222=6, 12223=7]", bank.queryZipCodeAccounts().toList().toString());
		bank = sampleBank(20);
		assertEquals("[12224=10, 12225=11, 12222=10, 12223=10]", bank.queryZipCodeAccounts().toList().toString());
		bank = sampleBank(100);
		assertEquals("[12224=50, 12225=51, 12222=50, 12223=50]", bank.queryZipCodeAccounts().toList().toString());
	}

	/**
	 * Tests the Task 3 implementation.
	 * 
	 * @throws Exception
	 *             if an error occurs
	 */
	@Test
	public void task3() throws Exception {
		var bank = sampleBank(10);
		assertEquals("[A02, A05, A08, A11, A14, A17, A20]",
				bank.queryMaxBalanceBankAccounts().map(a -> a.accountNumber()).toList().toString());
		bank = sampleBank(15);
		assertEquals("[A02, A05, A08, A11, A14, A17, A20, A23, A26, A29]",
				bank.queryMaxBalanceBankAccounts().map(a -> a.accountNumber()).toList().toString());
		bank = sampleBank(20);
		assertEquals("[A02, A05, A08, A11, A14, A17, A20, A23, A26, A29, A32, A35, A38]",
				bank.queryMaxBalanceBankAccounts().map(a -> a.accountNumber()).toList().toString());
	}
	/**
	 * Constructs a sample {@code Bank}.
	 * 
	 * @param customers
	 *            the number of {@code Customer}s of the {@code Bank}
	 * @return a sample {@code Bank}
	 */
	bank.nodb.Bank sampleBank(int customers) {
		var b = new bank.nodb.Bank("Sample Bank");
		bank.nodb.Bank.addData(b, customers);
		return b;
	}

}
