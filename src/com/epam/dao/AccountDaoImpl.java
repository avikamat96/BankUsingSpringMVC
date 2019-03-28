/**
 * 
 */
package com.epam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.epam.enums.AccountType;
import com.epam.exceptions.UserAccountNotFoundException;
import com.epam.models.Account;
import com.epam.utils.JpaUtil;

/**
 * The Class AccountDaoImpl.
 *
 * @author Avinash_Kamat
 */
public class AccountDaoImpl implements AccountDao {

  EntityManager emManager;

  public AccountDaoImpl() {
    this.emManager = JpaUtil.getEntityManagerFactory().createEntityManager();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.epam.dao.AccountDao#saveAccount(com.epam.models.Account)
   */
  @Override
  public void saveAccount(Account account) {
    emManager.getTransaction().begin();
    emManager.persist(account);
    emManager.getTransaction().commit();
    //emManager.close();
  }
  
  /* (non-Javadoc)
   * @see com.epam.dao.AccountDao#updateAccount(com.epam.models.Account)
   */
  @Override
  public void updateAccount(Account account) {
    emManager.getTransaction().begin();
    emManager.merge(account);
    emManager.getTransaction().commit();
    //emManager.close();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.epam.dao.AccountDao#removeAccount(com.epam.models.Account)
   */
  @Override
  public void removeAccount(long accountNumber) throws UserAccountNotFoundException {
    emManager.getTransaction().begin();
    Account account =emManager.find(Account.class,accountNumber);
    account.setAccountType(AccountType.DISABLED);
    emManager.getTransaction().commit();
    //emManager.close();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.epam.dao.AccountDao#getAccountDetails(long)
   */
  @Override
  public Account getAccountDetails(long accountNumber) throws UserAccountNotFoundException {
  //  emManager.getTransaction().begin();
    Account account = emManager.find(Account.class, accountNumber);
    //emManager.getTransaction().commit();
   // emManager.close();
    if(account==null) {
      throw new UserAccountNotFoundException("Account number does not exist");
    }
    return account;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.epam.dao.AccountDao#getAllAccounts()
   */
  @SuppressWarnings("unchecked")
  @Override
  public List<Account> getAllAccounts() {
   // emManager.getTransaction().begin();
    Query query = emManager.createQuery("SELECT e FROM Account e");
    List<Account> accounts = query.getResultList();
  //  emManager.getTransaction().commit();
   // emManager.close();
    return accounts;
  }

}
