package com.epam.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.epam.enums.AccountType;
import com.epam.enums.Gender;
import com.epam.exceptions.InsufficientBalanceException;
import com.epam.exceptions.UserAccountNotFoundException;
import com.epam.exceptions.UserInformationNotValidException;
import com.epam.models.Account;
import com.epam.models.User;
import com.epam.services.AccountService;
import com.epam.services.AccountValidatorService;
import com.epam.services.TransactionService;

/**
 * The Class AccountController.
 */
@Controller
public class AccountController {

  @Autowired
  private AccountService accountService;

  @Autowired
  private AccountValidatorService validatorService;

  @Autowired
  private TransactionService transactionService;

  /**
   * Creates the account process.
   *
   * @param request the request
   * @param model   the model
   * @return the string
   * @throws UserInformationNotValidException the user information not valid
   *                                          exception
   */
  @PostMapping("/createaccount")
  public String createAccountProcess(HttpServletRequest request, Model model) throws UserInformationNotValidException {
    String userName = request.getParameter("userName");
    int userAge = Integer.parseInt(request.getParameter("userAge"));
    Gender userGender = request.getParameter("userGender").equalsIgnoreCase("male") ? Gender.MALE : Gender.FEMALE;
    AccountType accountType = request.getParameter("accountType").equalsIgnoreCase("savings") ? AccountType.SAVINGS
        : AccountType.CURRENT;
    User user = new User(userName, userAge, userGender);
    String page = "";
    if (validatorService.validate(user)) {
      Account account = accountService.createAccount(user, accountType);
      model.addAttribute("account", account);
      page = "showProfile";

    }
    return page;
  }

  /**
   * Update account get account number.
   *
   * @param request the request
   * @param model   the model
   * @return the string
   * @throws UserAccountNotFoundException the user account not found exception
   */
  @PostMapping("/updateAccountGetAccountNumber")
  public String updateAccountGetAccountNumber(HttpServletRequest request, Model model)
      throws UserAccountNotFoundException {
    long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
    Account account = accountService.getAccountDetails(accountNumber);
    model.addAttribute("account", account);
    return "updateAccount";
  }

  /**
   * Update account process.
   *
   * @param request the request
   * @param model   the model
   * @return the string
   * @throws UserAccountNotFoundException the user account not found exception
   */
  @PostMapping("/updateaccount")
  public String updateAccountProcess(HttpServletRequest request, Model model) throws UserAccountNotFoundException {
    String userName = request.getParameter("updatedUserName");
    int userAge = Integer.parseInt(request.getParameter("updatedUserAge"));
    Gender userGender = Gender.valueOf(request.getParameter("updatedUserGender").toUpperCase());
    AccountType accountType = AccountType.valueOf(request.getParameter("updatedUserAccountType").toUpperCase());
    long accountNumber = Long.parseLong(request.getParameter("updatedUserAccountNumber"));
    Account account = accountService.getAccountDetails(accountNumber);
    User user = account.getUser();
    user.setName(userName);
    user.setAge(userAge);
    user.setGender(userGender);
    account.setUser(user);
    account.setAccountType(accountType);
    account.setAccountNumber(accountNumber);
    accountService.updateAccount(account);
    model.addAttribute("account", account);
    return "showProfile";
  }

  /**
   * View account process.
   *
   * @param request the request
   * @param model   the model
   * @return the string
   * @throws UserAccountNotFoundException the user account not found exception
   */
  @PostMapping("/viewaccount")
  public String viewAccountProcess(HttpServletRequest request, Model model) throws UserAccountNotFoundException {
    long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
    Account account = accountService.getAccountDetails(accountNumber);
    model.addAttribute("account", account);
    return "showProfile";
  }

  /**
   * View all account process.
   *
   * @param model the model
   * @return the string
   */
  @GetMapping("/listAllAccounts")
  public String viewAllAccountProcess(Model model) {
    List<Account> accountList = accountService.getAllAccounts();
    model.addAttribute("accountList", accountList);
    return "listAllAccounts";
  }

  /**
   * Delete account process.
   *
   * @param request the request
   * @return the string
   * @throws UserAccountNotFoundException the user account not found exception
   */
  @PostMapping("/deleteAccount")
  public String deleteAccountProcess(HttpServletRequest request) throws UserAccountNotFoundException {
    long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
    accountService.deleteAccount(accountNumber);
    return "dashboard";
  }

  /**
   * Deposit money process.
   *
   * @param request the request
   * @return the string
   * @throws UserAccountNotFoundException the user account not found exception
   */
  @PostMapping("/depositMoney")
  public String depositMoneyProcess(HttpServletRequest request) throws UserAccountNotFoundException {
    long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
    Account account = accountService.getAccountDetails(accountNumber);
    if (account.getAccountType() != AccountType.DISABLED) {
      transactionService.depositMoney(accountNumber,
          Math.abs(Double.parseDouble(request.getParameter("depositAmount"))));
      return "dashboard";
    }
    return "";
  }

  /**
   * Withdraw money process.
   *
   * @param request the request
   * @return the string
   * @throws UserAccountNotFoundException the user account not found exception
   * @throws InsufficientBalanceException the insufficient balance exception
   */
  @PostMapping("/withdrawMoney")
  public String withdrawMoneyProcess(HttpServletRequest request)
      throws UserAccountNotFoundException, InsufficientBalanceException {
    long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
    Account account = accountService.getAccountDetails(accountNumber);
    if (account.getAccountType() != AccountType.DISABLED) {
      transactionService.withdrawMoney(accountNumber,
          Math.abs(Double.parseDouble(request.getParameter("withdrawAmount"))));
      return "dashboard";
    }
    return "";
  }

}
