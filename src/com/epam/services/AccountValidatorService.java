package com.epam.services;


import org.springframework.stereotype.Service;

import com.epam.exceptions.UserInformationNotValidException;
import com.epam.models.User;

/**
 * The Class AccountValidatorService.
 *
 * @author Avinash_Kamat
 */
@Service
public class AccountValidatorService {

  /**
   * Validate.
   *
   * @param user the user
   * @return true, if successful
   * @throws UserInformationNotValidException the user information not valid
   *                                          exception
   */
  public boolean validate(User user) throws UserInformationNotValidException {
    boolean isInformationValid = true;
    if (user.getName().isEmpty() || user.getAge() < 15) {
      throw new UserInformationNotValidException("User information is not valid");
    }
    return isInformationValid;
  }

} 
