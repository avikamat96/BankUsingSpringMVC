/**
 * 
 */
package com.epam.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.epam.exceptions.UserAccountNotFoundException;
import com.epam.models.Account;

/**
 * The Interface AccountDao.
 *
 * @author Avinash_Kamat
 */
@Component
public interface AccountDao {

  /**
   * Creates the account.
   *
   * @param account the account
   */
  Account saveAccount(Account account);

  /**
   * Delete account.
   *
   * @param account the account
   * @return true, if successful
   * @throws UserAccountNotFoundException the user account not found exception
   */
  Account removeAccount(long accountNumber) throws UserAccountNotFoundException;
  
  
  /**
   * Update account.
   *
   * @param account the account
 * @return 
   */
  boolean updateAccount(Account account);

  /**
   * Gets the account details.
   *
   * @param accountNumber the account number
   * @return the account details
   * @throws UserAccountNotFoundException the user account not found exception
   */
  Account getAccountDetails(long accountNumber) throws UserAccountNotFoundException;

  /**
   * Gets the all accounts.
   *
   * @return the all accounts
   */
  List<Account> getAllAccounts();

}
