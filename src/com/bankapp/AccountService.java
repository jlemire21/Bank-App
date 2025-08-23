package com.bankapp;

import java.util.*;

public class AccountService {
	private final Map<Long, BankAccount> accounts = new HashMap<>();
	
	public BankAccount openAccount(String ownerName, double openingDeposit) {
		BankAccount acct = new BankAccount(ownerName, openingDeposit);
		accounts.put(acct.getId(), acct);
		return acct;
	}
	
	public Optional<BankAccount> findById(long id) {
		return Optional.ofNullable(accounts.get(id));
	}
	
	public List<BankAccount> listAccounts() {
		return new ArrayList<>(accounts.values());
	}
	
	public void deposit(long id, double amount) {
		BankAccount acct = accounts.get(id);
		if (acct == null) throw new NoSuchElementException("Account not found.");
		acct.deposit(amount);
	}
	
	public void withdraw(long id, double amount) {
		BankAccount acct = accounts.get(id);
		if (acct == null) throw new NoSuchElementException("Account not found.");
		acct.withdraw(amount);
	}
	
	public void transfer(long fromId, long toId, double amount) {
		BankAccount fromAcct = accounts.get(fromId);
		BankAccount toAcct = accounts.get(toId);
		if (fromAcct == null || toAcct == null) throw new NoSuchElementException("Account not found.");
		fromAcct.withdraw(amount);
		toAcct.deposit(amount);
	}
}
