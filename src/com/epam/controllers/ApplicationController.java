package com.epam.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The Class ApplicationController.
 */
@Controller
public class ApplicationController {

  /**
   * Index controller.
   *
   * @return the string
   */
  @GetMapping("/")
  public String indexController() {
    return "login";
  }

  /**
   * Show dashboard.
   *
   * @return the string
   */
  @GetMapping("/dashboard")
  public String showDashboard() {
    return "dashboard";
  }

  /**
   * Logout.
   *
   * @return the string
   */
  @GetMapping("/logout")
  public String logout(HttpServletRequest request) {
    request.getSession().invalidate();
    return "login";
  }

  /**
   * Do post.
   *
   * @param employeeId       the employee id
   * @param employeePassword the employee password
   * @return the string
   */
  @PostMapping("/auth")
  public String doPost(@RequestParam("employeeid") String employeeId,
      @RequestParam("accountpassword") String employeePassword, HttpServletRequest request) {
    if (employeeId.equals("1111") && employeePassword.equals("1111")) {
      request.getSession().setAttribute("isUserLoggedIn",true);
      return "dashboard";
    }
    return "login";
  }

  /**
   * Open create account page.
   *
   * @return the string
   */
  @GetMapping("/createAccountPage")
  public String openCreateAccountPage() {
    return "createAccount";
  }

  /**
   * Open update account number page.
   *
   * @return the string
   */
  @GetMapping("/updateAccount_AccountNumber")
  public String openUpdateAccountNumberPage() {
    return "updateAccount_AccountNumber";
  }

  /**
   * Open delete account page.
   *
   * @return the string
   */
  @GetMapping("/deleteAccount_AccountNumber")
  public String openDeleteAccountPage() {
    return "deleteAccount_AccountNumber";
  }

  /**
   * Open view account number page.
   *
   * @return the string
   */
  @GetMapping("/accountDetails_accountNumber")
  public String openViewAccountNumberPage() {
    return "accountDetails_accountNumber";
  }

  /**
   * Open deposit money page.
   *
   * @return the string
   */
  @GetMapping("/depositMoney")
  public String openDepositMoneyPage() {
    return "depositMoney";
  }

  /**
   * Open withdraw money page.
   *
   * @return the string
   */
  @GetMapping("/withdrawMoney")
  public String openWithdrawMoneyPage() {
    return "withdrawMoney";
  }

}
