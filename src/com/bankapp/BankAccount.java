package com.bankapp;

import java.util.concurrent.atomic.AtomicLong;

public class BankAccount {
	private static final AtomicLong NEXT_ID = new AtomicLong(1);
	
	private final long id;
	private String ownerName;
	private double balance;
	
	public BankAccount(String ownerName, double openingDeposit) {
		if (openingDeposit < 0) throw new IllegalArgumentException("Opening deposit cannot be negative");
		this.id = NEXT_ID.getAndIncrement();
		this.ownerName = ownerName;
		this.balance = openingDeposit;
	}
	
	public long getId() { return id; }
	public String getOwnerName() { return ownerName; }
	public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
	public double getBalance() { return balance; }
	
	public void deposit(double amount) {
		if (amount <= 0) throw new IllegalArgumentException("deposit amount must be greater than 0");
		balance += amount;
	}
	
	public void withdraw(double amount) {
		if (amount <= 0) throw new IllegalArgumentException("withdrawal must be greater than 0");
		if (amount > balance) throw new IllegalStateException("insufficient funds");
		balance -= amount;
	}
	
	@Override
	public String toString() {
		return "Account{id=" + id + ", owner='" + ownerName + "', balance=" + balance + "}";
	}
}
