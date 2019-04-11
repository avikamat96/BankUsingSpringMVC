/**
* 
*/
package com.epam.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.enums.TransactionType;
import com.epam.exceptions.InsufficientBalanceException;
import com.epam.exceptions.UserAccountNotFoundException;
import com.epam.models.Account;
import com.epam.models.Transactions;

/**
 * The Class TransactionService.
 *
 * @author Avinash_Kamat
 */
@Service
public class TransactionService {

	@Autowired
	private AccountService accountService;

	/**
	 * Withdraw money.
	 *
	 * @param accountNumber
	 *            the account number
	 * @param amount
	 *            the amount
	 * @return the account
	 * @throws UserAccountNotFoundException
	 *             the user account not found exception
	 * @throws InsufficientBalanceException
	 *             the insufficient balance exception
	 */
	public boolean withdrawMoney(long accountNumber, double amount)
			throws UserAccountNotFoundException, InsufficientBalanceException {
		Transactions transaction = new Transactions();
		Account account = accountService.getAccountDetails(accountNumber);
		Double getCurrentBalance = account.getAccountBalance();
		if (getCurrentBalance - amount >= 0) {
			account.setAccountBalance(getCurrentBalance - amount);
			transaction.setTransactionType(TransactionType.WITHDRAW);
			transaction.setTransactionAmount(amount);
			List<Transactions> transactions = new ArrayList<>();
			transactions.add(transaction);
			account.setTransactions(transactions);
			return accountService.updateAccount(account);
		} else {
			throw new InsufficientBalanceException("Insufficient balance");
		}

	}

	/**
	 * Deposit money.
	 *
	 * @param accountNumber
	 *            the account number
	 * @param amount
	 *            the amount
	 * @return the account
	 * @throws UserAccountNotFoundException
	 *             the user account not found exception
	 */
	public boolean depositMoney(long accountNumber, double amount) throws UserAccountNotFoundException {
		Transactions transaction = new Transactions();
		Account account = accountService.getAccountDetails(accountNumber);
		account.setAccountBalance(account.getAccountBalance() + amount);
		transaction.setTransactionType(TransactionType.DEPOSIT);
		transaction.setTransactionAmount(amount);
		List<Transactions> transactions = new ArrayList<>();
		transactions.add(transaction);
		account.setTransactions(transactions);
		return accountService.updateAccount(account);
	}

}
