package com.epam.bankspring.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import com.epam.dao.AccountDaoImpl;
import com.epam.exceptions.InsufficientBalanceException;
import com.epam.exceptions.UserAccountNotFoundException;
import com.epam.models.Account;
import com.epam.services.AccountService;
import com.epam.services.TransactionService;

class TransactionServiceTest {

	@InjectMocks
	private TransactionService transactionService;
	
	@Mock
	private AccountService accountService;
	
	@Mock
	private AccountDaoImpl accountDao;
	
	
	 @BeforeEach
	  public void setUp() throws Exception {
	      MockitoAnnotations.initMocks(this);
	  }
	
	@Test
	void depositMoneyTest() throws UserAccountNotFoundException {
		Account account = new Account();
		long accountNumber = 1;
		account.setAccountNumber(accountNumber);
		when(accountService.getAccountDetails(accountNumber)).thenReturn(account);
		when(accountService.updateAccount(account)).thenReturn(true);
		assertEquals(true, transactionService.depositMoney(accountNumber, 100));
	}
	
	@Test
	void depositMoneyExceptionTest() throws UserAccountNotFoundException {
		Account account = new Account();
		long accountNumber = 1;
		account.setAccountNumber(accountNumber);
		when(accountService.getAccountDetails(accountNumber)).thenThrow(UserAccountNotFoundException.class);
		when(accountService.updateAccount(account)).thenReturn(true);
		assertThrows(UserAccountNotFoundException.class, ()->transactionService.depositMoney(accountNumber, 100));
	}
	
	@Test
	void withdrawMoneyTest() throws UserAccountNotFoundException, InsufficientBalanceException {
		Account account = new Account();
		long accountNumber = 1;
		account.setAccountBalance(200);
		account.setAccountNumber(accountNumber);
		when(accountService.getAccountDetails(accountNumber)).thenReturn(account);
		when(accountService.updateAccount(account)).thenReturn(true);
		assertEquals(true, transactionService.withdrawMoney(accountNumber, 100));
	}
	
	@Test
	void withdrawMoneyUserNotFoundTest() throws UserAccountNotFoundException, InsufficientBalanceException {
		Account account = new Account();
		long accountNumber = 1;
		account.setAccountBalance(200);
		account.setAccountNumber(accountNumber);
		when(accountService.getAccountDetails(accountNumber)).thenThrow(UserAccountNotFoundException.class);
		when(accountService.updateAccount(account)).thenReturn(true);
		assertThrows(UserAccountNotFoundException.class, ()->transactionService.withdrawMoney(accountNumber, 100));
	}
	
	@Test
	void withdrawMoneyInsuffiecientBalanceTest() throws UserAccountNotFoundException {
		Account account = new Account();
		long accountNumber = 1;
		account.setAccountBalance(200);
		account.setAccountNumber(accountNumber);
		when(accountService.getAccountDetails(accountNumber)).thenReturn(account);
		when(accountService.updateAccount(account)).thenReturn(true);
		assertThrows(InsufficientBalanceException.class, ()->transactionService.withdrawMoney(accountNumber, 300));
	}
	

}
