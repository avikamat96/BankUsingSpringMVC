/**
 * 
 */
package com.epam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epam.dao.AccountDao;
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

  @Autowired
  @Qualifier("accountDao")
  private AccountDao accountDao ;

  /**
   * Creates the account.
   *
   * @param user        the user
   * @param accountType the account type
   * @return the account
   */
  public Account createAccount(User user, AccountType accountType) {
    Account account = new Account();
    account.setAccountType(accountType);
    account.setUser(user);
    account.setAccountBalance(0.00);
    return accountDao.saveAccount(account); 
  }

  /**
   * Delete account.
   *
   * @param accountNumber the account number
   * @throws UserAccountNotFoundException the user account not found exception
   */
  public boolean deleteAccount(long accountNumber) throws UserAccountNotFoundException {
    Account account = accountDao.removeAccount(accountNumber);
    return (account.getAccountType() == AccountType.DISABLED);
  }

  /**
   * Update account.
   *
   * @param account the account
   * @throws UserAccountNotFoundException the user account not found exception
   */
  public boolean updateAccount(Account account) throws UserAccountNotFoundException {
    return accountDao.updateAccount(account); 
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

  /**
   * Gets the all accounts.
   *
   * @return the all accounts
   */
  public List<Account> getAllAccounts() {
    List<Account> accounts = accountDao.getAllAccounts();
    return accounts;
  }

}
