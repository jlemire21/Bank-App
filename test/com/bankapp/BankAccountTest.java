package com.bankapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

	@Test
	void deposit_increases_balance() {
		BankAccount a = new BankAccount("Alice", 100.0);
		a.deposit(50.0);
		assertEquals(150.0, a.getBalance(), 1e-9);
	}
	
	@Test
	void deposit_non_positive_throws() {
		BankAccount a = new BankAccount("Bob", 0.0);
		assertThrows(IllegalArgumentException.class, () -> a.deposit(0.0));
		assertThrows(IllegalArgumentException.class, () -> a.deposit(-5.0));
	}
	
	@Test
	void withdraw_decreases_balance() {
		BankAccount a = new BankAccount("Cara", 200.0);
		a.withdraw(75.0);
		assertEquals(125.0, a.getBalance(), 1e-9);
	}
	
	@Test
	void withdraw_more_than_balance_throws() {
		BankAccount a = new BankAccount("Dan", 60.0);
		assertThrows(IllegalStateException.class, () -> a.withdraw(61.0));
	}
}
