/**
 * 
 */
package com.epam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.epam.enums.AccountType;
import com.epam.exceptions.UserAccountNotFoundException;
import com.epam.models.Account;
import com.epam.utils.JpaUtil;

/**
 * The Class AccountDaoImpl.
 *
 * @author Avinash_Kamat
 */
@Component("accountDao")
public class AccountDaoImpl implements AccountDao {

  private EntityManager emManager;

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
  }
  
  /* (non-Javadoc)
   * @see com.epam.dao.AccountDao#updateAccount(com.epam.models.Account)
   */
  @Override
  public void updateAccount(Account account) {
    emManager.getTransaction().begin();
    emManager.merge(account);
    emManager.flush();
    boolean isManagedContext = emManager.contains(account);
    emManager.getTransaction().commit();
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
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.epam.dao.AccountDao#getAccountDetails(long)
   */
  @Override
  public Account getAccountDetails(long accountNumber) throws UserAccountNotFoundException {
    Account account = emManager.find(Account.class, accountNumber);
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

  @Override
  public List<Account> getAllAccounts() {
    emManager.clear();
    emManager.getTransaction().begin();
    //Query query = emManager.createQuery("SELECT e FROM Account e");
    //List<Account> accounts = query.getResultList();
    
    
    
    CriteriaBuilder cb = emManager.getCriteriaBuilder();

    CriteriaQuery<Account> cq = cb.createQuery(Account.class);
    Root<Account> from = cq.from(Account.class);

    cq.select(from);
    TypedQuery<Account> q = emManager.createQuery(cq);
    List<Account> allitems = q.getResultList();
    
    emManager.getTransaction().commit();
    
    return allitems;
  }

}
