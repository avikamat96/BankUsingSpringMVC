/**
 * 
 */
package com.epam.services;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.epam.dao.AccountDao;
import com.epam.dao.AccountDaoImpl;
import com.epam.enums.AccountType;
import com.epam.exceptions.UserAccountNotFoundException;
import com.epam.models.Account;
import com.epam.models.User;

/**
 * The Class AccountService.
 *
 * @author Avinash_Kamat
 */
@Service
public class AccountService {
  
  AccountDao accountDao = new AccountDaoImpl();

  /**
   * Creates the account.
   *
   * @param user the user
   * @param accountType the account type
   * @return the account
   */
  public Account createAccount(User user, AccountType accountType) {
    Account account = new Account();
    account.setAccountType(accountType);
    account.setUser(user);
    account.setAccountBalance(0.00);
    accountDao.saveAccount(account);
    return account;
  }

  /**
   * Delete account.
   *
   * @param accountNumber the account number
   * @throws UserAccountNotFoundException the user account not found exception
   */
  public void deleteAccount(long accountNumber) throws UserAccountNotFoundException{
    accountDao.removeAccount(accountNumber);
  }
  
  /**
   * Update account.
   *
   * @param accountNumber the account number
   * @param account the account
   * @throws UserAccountNotFoundException the user account not found exception
   */
  public void updateAccount(Account account)
      throws UserAccountNotFoundException {
    accountDao.updateAccount(account);
  }
  
  /**
   * Gets the account details.
   *
   * @param accountNumber the account number
   * @return the account details
   * @throws UserAccountNotFoundException the user account not found exception
   */
  public Account getAccountDetails(long accountNumber) throws UserAccountNotFoundException {
       return accountDao.getAccountDetails(accountNumber);
  }
  
  public List<Account> getAllAccounts() {
    List<Account> accounts = accountDao.getAllAccounts();
    return accounts;
  }

}
