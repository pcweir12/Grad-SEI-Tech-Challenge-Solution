/**
 * 
 */
package anagram.checker;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author philip
 *
 */
class AnagramUtilMethodsTest {

	// Test data
	String usernameA, usernameB, usernameC, textValueA, textValueB, textValueC;

	ArrayList<User> mockCache, mockSearchResults;

	User user1, user2, user3, user4, user5, user6, user7;

	boolean flag;

	@BeforeEach
	void setUp() throws Exception {
		usernameA = "usernameA";
		usernameB = "usernameB";
		usernameC = "usernameC";
		textValueA = "textValueA";
		textValueB = "textValueB";
		textValueC = "textValueC";

		user1 = new User(usernameA, textValueA, textValueB);
		user2 = new User(usernameA, textValueB, textValueC);
		user3 = new User(usernameB, textValueC, textValueC);
		user4 = new User(usernameC, textValueA, textValueC);

		// duplicate entries
		user5 = new User(usernameA, textValueA, textValueB);
		user6 = new User(usernameA, textValueB, textValueA);
		user7 = new User(usernameA, textValueC, textValueA);

		// mockCache simulates the Anagram app cache array list that holds all previous
		// entries
		mockCache = new ArrayList<>();
		mockCache.add(user1);
		mockCache.add(user2);
		mockCache.add(user3);
		mockCache.add(user4);

		// mockSearchResults simulates the array list produced by the searchUsername
		// method.
		mockSearchResults = new ArrayList<>();
		mockSearchResults.add(user1);
		mockSearchResults.add(user2);

	}

	/**
	 * Testing search by username method, searching a mock cache to return an array
	 * of users (searchResults) with a specified username ("usernameA").
	 */
	@Test
	void testSearchByUsername() {
		ArrayList<User> searchResults = AnagramUtilMethods.searchByUsername(mockCache, usernameA);

		assertNotNull(searchResults);
		assertTrue((searchResults.contains(user1)) && (searchResults.contains(user2)));
		assertFalse((searchResults.contains(user3)) && (searchResults.contains(user4)));
		assertEquals(2, searchResults.size());
	}

	/**
	 * Testing check for duplicates method. Flags return true if the entry is a
	 * duplicate and false if the entry is unique.
	 */
	@Test
	void testCheckForDuplicates() {

		// Testing for duplicate entries (flag=true)
		// User5 is an exact duplicate of user1
		flag = AnagramUtilMethods.checkCacheForDuplicate(mockSearchResults, user5);
		assertTrue(flag);

		// User6 is a duplicate of user1, having the two text values switched
		flag = AnagramUtilMethods.checkCacheForDuplicate(mockSearchResults, user6);
		assertTrue(flag);

		// Testing for unique entry (flag=false)
		flag = AnagramUtilMethods.checkCacheForDuplicate(mockSearchResults, user7);
		assertFalse(flag);

	}

}
