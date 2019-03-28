package com.epam.dao;

import java.util.List;

import com.epam.models.Account;
import com.epam.models.Transactions;

public interface TransactionDao {

  void saveTransaction(Transactions transaction);
  
  List<Transactions> getAllTransactions();
}
