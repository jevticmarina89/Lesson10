package exercises;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ValidationOfDataTest {
	
	ValidationOfData validation = new ValidationOfData();
	
	@Test
	void testPhoneNumberIsValid() throws Exception {
		assertTrue(validation.validPhoneNumber("0100-555-666"));
		assertTrue(validation.validPhoneNumber("0100 555666"));
		assertFalse(validation.validPhoneNumber("0100555666"));
		assertFalse(validation.validPhoneNumber("0100v555n666"));
	}
	
	@Test
	void testEmailIsValid() throws Exception {
		assertTrue(validation.validEmailAddress("someone@email.com"));
		assertTrue(validation.validEmailAddress("someone89@email.com"));
		assertFalse(validation.validEmailAddress("someoneemail.com"));
		assertFalse(validation.validEmailAddress("some(*one)@email.com"));
	}

}
