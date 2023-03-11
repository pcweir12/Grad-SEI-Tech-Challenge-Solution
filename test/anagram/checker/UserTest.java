package anagram.checker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

	// Test data
	User user;
	String usernameValid, usernameInvalidNumb, usernameInvalidSpace, textValue1Valid, textValue1InvalidNumb,
			textValue1InvalidSpace, textValue2Valid, textValue2InvalidNumb, textValue2InvalidSpace, isAnagramTextValue1,
			isAnagramTextValue2, isAnagramTextValue3;

	@BeforeEach
	void setUp() throws Exception {
		user = new User();
		usernameValid = "validUsername";
		usernameInvalidNumb = "invalidUsername1";
		usernameInvalidSpace = "invalid username";
		textValue1Valid = "validTextValueOne";
		textValue1InvalidNumb = "invalidTextValue1";
		textValue1InvalidSpace = "invalid text value one";
		textValue2Valid = "validTextValueTwo";
		textValue2InvalidNumb = "invalidTextValue2";
		textValue2InvalidSpace = "invalid text value two";
		isAnagramTextValue1 = "abcd";
		isAnagramTextValue2 = "dcba";
		isAnagramTextValue3 = "adcd";

	}

	/**
	 * Testing default constructor with no args
	 */
	@Test
	void testDefaultConstructor() {
		assertNotNull(user);
	}

	/**
	 * Testing valid username
	 */
	@Test
	void testGetSetUsernameValid() {
		user.setUsername(usernameValid);
		assertEquals(usernameValid, user.getUsername());
	}

	/**
	 * Testing invalid usernames with spaces and numbers
	 */
	@Test
	void testGetSetUsernameExceptionExpected() {
		// Invalid username with number
		assertThrows(IllegalArgumentException.class, () -> {
			user.setUsername(usernameInvalidNumb);
		});
		// Invalid username with space
		assertThrows(IllegalArgumentException.class, () -> {
			user.setUsername(usernameInvalidSpace);
		});
	}

	/**
	 * Testing valid text value 1
	 */
	@Test
	void testGetSetTextValue1Valid() {
		user.setTextValue1(textValue1Valid);
		assertEquals(textValue1Valid, user.getTextValue1());
	}

	/**
	 * Testing invalid text value 1, with numbers and spaces
	 */
	@Test
	void testGetSetTextValue1ExceptionExpected() {
		// Invalid text value with number
		assertThrows(IllegalArgumentException.class, () -> {
			user.setTextValue1(textValue1InvalidNumb);
		});
		// Invalid text value with space
		assertThrows(IllegalArgumentException.class, () -> {
			user.setTextValue1(textValue1InvalidSpace);
		});
	}

	/**
	 * Testing valid text value 2
	 */
	@Test
	void testGetSetTextValue2Valid() {
		user.setTextValue2(textValue2Valid);
		assertEquals(textValue2Valid, user.getTextValue2());
	}

	/**
	 * Testing invalid text value 2, with numbers and spaces
	 */
	@Test
	void testGetSetTextValue2ExceptionExpected() {
		// Invalid text value with number
		assertThrows(IllegalArgumentException.class, () -> {
			user.setTextValue2(textValue2InvalidNumb);
		});
		// Invalid text value with space
		assertThrows(IllegalArgumentException.class, () -> {
			user.setTextValue2(textValue2InvalidSpace);
		});
	}

	/**
	 * Testing true is anagram
	 */
	@Test
	void testGetSetIsAnagramTrue() {
		user.setTextValue1(isAnagramTextValue1);
		user.setTextValue2(isAnagramTextValue2);
		user.setIsAnagram();
		assertTrue(user.isAnagram());
	}

	/**
	 * Testing false is anagram
	 */
	@Test
	void testGetSetIsAnagramFalse() {
		user.setTextValue1(isAnagramTextValue1);
		user.setTextValue2(isAnagramTextValue3);
		user.setIsAnagram();
		assertFalse(user.isAnagram());

	}

	/**
	 * Testing constructor with valid args
	 */
	@Test
	void testConstructorWithArgsValid() {

		// Input with values that are anagrams i.e isAnagram=true
		User user2 = new User(usernameValid, isAnagramTextValue1, isAnagramTextValue2);
		// Checking username
		assertEquals(usernameValid, user2.getUsername());
		// Checking text value 1
		assertEquals(isAnagramTextValue1, user2.getTextValue1());
		// Checking text value 2
		assertEquals(isAnagramTextValue2, user2.getTextValue2());
		// Checking is anagram returns true
		assertTrue(user2.isAnagram());

		// Input with values that are not anagrams i.e isAnagram=false
		User user3 = new User(usernameValid, isAnagramTextValue1, isAnagramTextValue3);
		// Checking username
		assertEquals(usernameValid, user3.getUsername());
		// Checking text value 1
		assertEquals(isAnagramTextValue1, user3.getTextValue1());
		// Checking text value 2
		assertEquals(isAnagramTextValue3, user3.getTextValue2());
		// Checking is anagram returns false
		assertFalse(user3.isAnagram());

	}

	/**
	 * Testing constructor with invalid args, exception expected
	 */
	@Test
	void testConstructorWithArgsExceptionExcpected() {

		// Testing invalid username with number
		assertThrows(IllegalArgumentException.class, () -> {
			new User(usernameInvalidNumb, textValue1Valid, textValue2Valid);
		});
		// Testing invalid username with space
		assertThrows(IllegalArgumentException.class, () -> {
			new User(usernameInvalidSpace, textValue1Valid, textValue2Valid);
		});
		// Testing invalid text value 1 with number
		assertThrows(IllegalArgumentException.class, () -> {
			new User(usernameValid, textValue1InvalidNumb, textValue2Valid);
		});
		// Testing invalid text value 1 with space
		assertThrows(IllegalArgumentException.class, () -> {
			new User(usernameValid, textValue1InvalidSpace, textValue2Valid);
		});
		// Testing invalid text value 2 with number
		assertThrows(IllegalArgumentException.class, () -> {
			new User(usernameValid, textValue1Valid, textValue2InvalidNumb);
		});
		// Testing invalid text value 2 with space
		assertThrows(IllegalArgumentException.class, () -> {
			new User(usernameValid, textValue1Valid, textValue2InvalidSpace);
		});

	}
}
