package com.epam.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

@Controller
public class AccountController {

  @Autowired
  AccountService accountService;
  @Autowired
  AccountValidatorService validatorService;
  @Autowired
  TransactionService transactionService;

  @GetMapping("/createAccountPage")
  public String openCreateAccountPage() {
    return "createAccount";
  }

  @GetMapping("/updateAccount_AccountNumber")
  public String openUpdateAccountNumberPage() {
    return "updateAccount_AccountNumber";
  }

  @GetMapping("/deleteAccount_AccountNumber")
  public String openDeleteAccountPage() {
    return "deleteAccount_AccountNumber";
  }

  @GetMapping("/accountDetails_accountNumber")
  public String openViewAccountNumberPage() {
    return "accountDetails_accountNumber";
  }

  @GetMapping("/depositMoney")
  public String openDepositMoneyPage() {
    return "depositMoney";
  }

  @GetMapping("/withdrawMoney")
  public String openWithdrawMoneyPage() {
    return "withdrawMoney";
  }

  @PostMapping("/createaccount")
  public String createAccountProcess(HttpServletRequest request, Model model) throws UserInformationNotValidException {
    String userName = request.getParameter("userName");
    int userAge = Integer.parseInt(request.getParameter("userAge"));
    Gender userGender = request.getParameter("userGender").toLowerCase().equals("male") ? Gender.MALE : Gender.FEMALE;
    AccountType accountType = request.getParameter("accountType").toLowerCase().equals("savings") ? AccountType.SAVINGS
        : AccountType.CURRENT;
    User user = new User(userName, userAge, userGender);
    if (validatorService.validate(user)) {
      Account account = accountService.createAccount(user, accountType);
      model.addAttribute("account", account);
      return "showProfile";
    }

    return "";
  }

  @PostMapping("/updateAccountGetAccountNumber")
  public String updateAccountGetAccountNumber(HttpServletRequest request, Model model)
      throws UserAccountNotFoundException {
    long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
    Account account = accountService.getAccountDetails(accountNumber);
    model.addAttribute("account", account);
    return "updateAccount";
  }

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

  @PostMapping("/viewaccount")
  public String viewAccountProcess(HttpServletRequest request, Model model) throws UserAccountNotFoundException {
    long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
    Account account = accountService.getAccountDetails(accountNumber);
    model.addAttribute("account", account);
    return "showProfile";
  }

  @GetMapping("/listAllAccounts")
  public String viewAllAccountProcess(Model model) {
    List<Account> accountList = accountService.getAllAccounts();
    model.addAttribute("accountList", accountList);
    return "listAllAccounts";
  }

  @PostMapping("/deleteAccount")
  public String deleteAccountProcess(HttpServletRequest request, Model model) throws UserAccountNotFoundException {
    long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
    accountService.deleteAccount(accountNumber);
    return "dashboard";
  }

  @PostMapping("/depositMoney")
  public String depositMoneyProcess(HttpServletRequest request)
      throws NumberFormatException, UserAccountNotFoundException {
    long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
    Account account = accountService.getAccountDetails(accountNumber);
    if (account.getAccountType() != AccountType.DISABLED) {
      transactionService.depositMoney(accountNumber,
          Math.abs(Double.parseDouble(request.getParameter("depositAmount"))));
      return "dashboard";
    }
    return "";

  }

  @PostMapping("/withdrawMoney")
  public String withdrawMoneyProcess(HttpServletRequest request) throws NumberFormatException, UserAccountNotFoundException, InsufficientBalanceException {
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
