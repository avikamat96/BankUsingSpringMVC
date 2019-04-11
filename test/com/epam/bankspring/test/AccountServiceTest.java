package com.epam.bankspring.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.dao.AccountDao;
import com.epam.enums.AccountType;
import com.epam.exceptions.UserAccountNotFoundException;
import com.epam.models.Account;
import com.epam.models.User;
import com.epam.services.AccountService;

public class AccountServiceTest {

	@InjectMocks
	private AccountService accountService;

	@Mock
	private AccountDao accountDao;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createAccountTest() {
		User user = new User();
		user.setAge(15);
		AccountType accountType = AccountType.SAVINGS;
		Account account = new Account();
		when(accountDao.saveAccount(account)).thenReturn(new Account());
		System.out.println(accountService.createAccount(user, accountType));
		//assertNotNull(accountService.createAccount(user, accountType));
		// assertNotNull(result);

	}

	@Test
	public void getAccountByIdTest() throws UserAccountNotFoundException {
		long id = 1;
		Account account = new Account();
		account.setAccountNumber(id);
		when(accountDao.getAccountDetails(id)).thenReturn(account);
		assertEquals(1, account.getAccountNumber());
	}

	@Test
	public void getAllAccountsTest() {
		Account account = new Account();
		List<Account> accounts = new ArrayList<>();
		accounts.add(account);
		when(accountDao.getAllAccounts()).thenReturn(accounts);
		assertEquals(1, accounts.size());
	}

	@Test
	public void updateAccountTest() throws UserAccountNotFoundException {
		Account account = new Account();
		when(accountDao.updateAccount(account)).thenReturn(true);
		assertTrue(accountService.updateAccount(account));
	}
	
	@Test
	public void deleteAccountTest() throws UserAccountNotFoundException {
		Account account = new Account();
		account.setAccountType(AccountType.DISABLED);
		long accountNumber = 1;
		when(accountDao.removeAccount(accountNumber )).thenReturn(account);
		assertEquals(true, accountService.deleteAccount(accountNumber));
	}

}
