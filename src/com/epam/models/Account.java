package com.epam.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.epam.enums.AccountType;

/**
 * The Class Account.
 *
 * @author Avinash_Kamat
 */
@Entity
@Table(name = "accounts")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "account_no")
  long accountNumber;
  
  @Column(name = "account_type")
  @Enumerated
  AccountType accountType;
  
  @Column(name = "account_balance")
  double accountBalance;
  
  @Column
  @CreationTimestamp
  private LocalDateTime createDateTime;

  @Column
  @UpdateTimestamp
  private LocalDateTime updateDateTime;
  
  @OneToOne(cascade = CascadeType.ALL)
  private User user;
  
  @JoinColumn(name = "accno")
  @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  private List<Transactions> transactions;

  /**
   * Instantiates a new account.
   */
  public Account() {
  }

  /**
   * Instantiates a new account.
   *
   * @param user the user
   */
  public Account(User user) {
    this.user = user;
  }

  /**
   * Gets the account number.
   *
   * @return the accountNumber
   */
  public long getAccountNumber() {
    return accountNumber;
  }

  /**
   * Sets the account number.
   *
   * @param accountNumber the accountNumber to set
   */
  public void setAccountNumber(long accountNumber) {
    this.accountNumber = accountNumber;
  }

  /**
   * Gets the account type.
   *
   * @return the accountType
   */
  public AccountType getAccountType() {
    return accountType;
  }

  /**
   * Sets the account type.
   *
   * @param accountType the accountType to set
   */
  public void setAccountType(AccountType accountType) {
    this.accountType = accountType;
  }

  /**
   * Gets the account balance.
   *
   * @return the accountBalance
   */
  public double getAccountBalance() {
    return accountBalance;
  }

  /**
   * Sets the account balance.
   *
   * @param accountBalance the accountBalance to set
   */
  public void setAccountBalance(Double accountBalance) {
    this.accountBalance = accountBalance;
  }

  /**
   * @return the createDateTime
   */
  public LocalDateTime getCreateDateTime() {
    return createDateTime;
  }

  /**
   * @param createDateTime the createDateTime to set
   */
  public void setCreateDateTime(LocalDateTime createDateTime) {
    this.createDateTime = createDateTime;
  }

  /**
   * @return the updateDateTime
   */
  public LocalDateTime getUpdateDateTime() {
    return updateDateTime;
  }

  /**
   * @param updateDateTime the updateDateTime to set
   */
  public void setUpdateDateTime(LocalDateTime updateDateTime) {
    this.updateDateTime = updateDateTime;
  }

  /**
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /**
   * @param user the user to set
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * @param accountBalance the accountBalance to set
   */
  public void setAccountBalance(double accountBalance) {
    this.accountBalance = accountBalance;
  }
  
  /**
   * @return the transactions
   */
  public List<Transactions> getTransactions() {
    return transactions;
  }

  /**
   * @param transactions the transactions to set
   */
  public void setTransactions(List<Transactions> transactions) {
    this.transactions = transactions;
  }

}
