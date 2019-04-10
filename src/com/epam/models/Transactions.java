package com.epam.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.epam.enums.TransactionType;

@Entity
@Table(name = "transactions")
public class Transactions {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transaction_id")
  private int transactionId;
  
  @Column(name = "transaction_type")
  @Enumerated
  private TransactionType transactionType;
  
  @Column(name = "tranaction_amount")
  private double transactionAmount;
  
  @Column
  @CreationTimestamp
  private LocalDateTime createDateTime;

  @Column
  @UpdateTimestamp
  private LocalDateTime updateDateTime;
  
  /*
   * @ManyToOne(cascade = CascadeType.ALL ) // @JoinColumn(name =
   * "account_account_no") private Account account;
   */

  /**
   * @return the transactionId
   */
  public int getTransactionId() {
    return transactionId;
  }

  /**
   * @param transactionId the transactionId to set
   */
  public void setTransactionId(int transactionId) {
    this.transactionId = transactionId;
  }

  /**
   * @return the transactionType
   */
  public TransactionType getTransactionType() {
    return transactionType;
  }

  /**
   * @param transactionType the transactionType to set
   */
  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
  }

  /**
   * @return the transactionAmount
   */
  public double getTransactionAmount() {
    return transactionAmount;
  }

  /**
   * @param amount the transactionAmount to set
   */
  public void setTransactionAmount(double amount) {
    this.transactionAmount = amount;
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
   * @return the account
   */
//  public Account getAccount() {
//    return account;
//  }
//
//  /**
//   * @param account the account to set
//   */
//  public void setAccount(Account account) {
//    this.account = account;
//  }
  
  
  
}
