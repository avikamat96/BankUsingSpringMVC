package com.epam.bankspring.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.epam.exceptions.UserInformationNotValidException;
import com.epam.models.User;
import com.epam.services.AccountValidatorService;

public class AccountValidatorServiceTest {
	
	private AccountValidatorService validator = new AccountValidatorService();
	
	@Test
	public void validatorTest() throws UserInformationNotValidException {
		User user = new User();
		user.setName("Avinash");
		user.setAge(16);
		boolean result = validator.validate(user);
		assertEquals(true, result);
	}
	
	@Test
	public void nameEmptyValidatorTest() {
		User user = new User();
		user.setName("");
		user.setAge(16);
		assertThrows(UserInformationNotValidException.class, ()->validator.validate(user));
	}
	
	@Test
	public void ageValidatorTest() {
		User user = new User();
		user.setName("Avinash");
		user.setAge(10);
		assertThrows(UserInformationNotValidException.class, ()->validator.validate(user));
	}

}
