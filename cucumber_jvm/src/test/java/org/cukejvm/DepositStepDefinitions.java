package org.cukejvm;

import static org.junit.Assert.assertTrue;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class DepositStepDefinitions {

	private Account account;

	@Given("^a User has no money in their account$")
	public void a_User_has_no_money_in_their_current_account() {
		User user = new User();
		account = new Account();
		user.setAccount(account);
		assertTrue("The balance is not zero.", account.getBalance() == 0L);
	}

	@When("^(\\d+) is deposited in to the account$")
	public void is_deposited_in_to_the_account(int amount) {
		account.deposit(amount);
	}

	@Then("^the balance should be (\\d+)$")
	public void the_balance_should_be(int expectedBalance) {
		int currentBalance = account.getBalance();
		assertTrue("The expected balance was 100, but actually was: "
				+ currentBalance, currentBalance == expectedBalance);
	}

	private class User {
		private Account account;

		public void setAccount(Account account) {
			this.account = account;
		}
	}

	private class Account {
		private int balance;

		public Account() {
			this.balance = 0;
		}

		public int getBalance() {
			return balance;
		}

		public void deposit(int amount) {
			this.balance += amount;
		}
	}

}