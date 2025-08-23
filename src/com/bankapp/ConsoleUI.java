package com.bankapp;

import java.util.*;

public class ConsoleUI {
	private final AccountService service = new AccountService();
	private final Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		new ConsoleUI().run();
	}
	
	private void run() {
		System.out.println("=== Bank App ===");
		boolean running = true;
		while(running) {
			printMenu();
			String choice = in.nextLine().trim();
			try {
				switch (choice) {
				case "1" -> openAccount();
				case "2" -> deposit();
				case "3" -> withdraw();
				case "4" -> transfer();
				case "5" -> listAccounts();
				case "6" -> showAccount();
				case "0" -> running = false;
				default -> System.out.println("Unknown Choice");
				}
			} catch (IllegalArgumentException | NoSuchElementException | IllegalStateException e) {
				System.out.println("Error: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("Unexpected error: " + e);
			}
		}
		System.out.println("Goodbye!");
	}
	
	private void printMenu() {
		System.out.println("""
				----------------------
				1) Open Account
				2) Deposit
				3) Withdraw
				4) Transfer
				5) List Accounts
				6) Show Account
				0) Exit
				Choose: """);
	}
	
	private void openAccount() {
		System.out.print("Owner name: ");
		String owner = in.nextLine().trim();
		System.out.print("Opening deposit: ");
		double deposit = Double.parseDouble(in.nextLine().trim());
		BankAccount acct = service.openAccount(owner, deposit);
		System.out.println("Created: " + acct);
	}
	
	private void deposit() {
		long id = promptId("Account ID: ");
		double amount = promptAmount("Amount to deposit: ");
		service.deposit(id, amount);
		System.out.println("Deposited. New balance: " + service.findById(id).get().getBalance());
	}
	
	private void withdraw() {
		long id = promptId("Account ID: ");
		double amount = promptAmount("Amount to withdraw: ");
		service.withdraw(id, amount);
		System.out.println("Withdrawn. New balance: " + service.findById(id).get().getBalance());
	}
	
	private void transfer() {
		long from = promptId("From account ID: ");
		long to = promptId("To account ID: ");
		double amount = promptAmount("Amount to transfer: ");
		service.transfer(from, to, amount);
		System.out.println("Transfer complete.");
	}
	
	private void listAccounts() {
		List<BankAccount> all = service.listAccounts();
		if (all.isEmpty()) {
			System.out.println("No accounts yet.");
		} else {
			all.forEach(System.out::println);
		}
	}
	
	private void showAccount() {
		long id = promptId("Account ID: ");
		Optional<BankAccount> acct = service.findById(id);
		System.out.println(acct.map(Object::toString).orElse("Not found"));
	}
	
	private long promptId(String label) {
		System.out.print(label);
		return Long.parseLong(in.nextLine().trim());
	}
	
	private double promptAmount(String label) {
		System.out.print(label);
		double amt = Double.parseDouble(in.nextLine().trim());
		if (amt <= 0) throw new IllegalArgumentException("Amount must be greater than 0");
		return amt;
	}
}
