package com.epam.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.epam.models.Account;
import com.epam.models.Transactions;
import com.epam.utils.JpaUtil;

public class TransactionDaoImpl implements TransactionDao{
  
  EntityManager emManager;
  
  public TransactionDaoImpl() {
    this.emManager = JpaUtil.getEntityManagerFactory().createEntityManager();
   }

  @Override
  public void saveTransaction(Transactions transaction) {
   // Account account = transaction.getAccount();
    emManager.getTransaction().begin();
   // emManager.merge(account);
    emManager.persist(transaction);
    emManager.getTransaction().commit();
    emManager.close();    
  }

  @Override
  public List<Transactions> getAllTransactions() {
    return null;
  }

}
