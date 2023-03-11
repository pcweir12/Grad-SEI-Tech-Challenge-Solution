/**
 * 
 */
package anagram.checker;

import java.util.ArrayList;

/**
 * @author philip
 *
 */
public class AnagramCheckerApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User user;
		boolean flag;
		ArrayList<User> cache = new ArrayList<>();
		ArrayList<User> searchResults = new ArrayList<>();

		// Checking if file exists and if not then creating it
		AnagramUtilMethods.createStorageFile();

		// Load file contents into cache (array list)
		AnagramUtilMethods.populateCache(cache);

		// Asking user for their data entry
		user = AnagramUtilMethods.createUserEntry();

		/*
		 * Retrieving all previous entries by the user/username to enable checking of
		 * new request against the cache
		 */
		searchResults = AnagramUtilMethods.searchByUsername(cache, user.getUsername());

		/*
		 * Checking for duplicate entry. Method returns false if entry is unique and
		 * true if it has been entered by the user/username before.
		 */
		flag = AnagramUtilMethods.checkCacheForDuplicate(searchResults, user);

		// If flag is false then entry is unique and will be checked for an anagram
		if (!flag) {
			if (user.isAnagram()) {
				System.out.println("These words are anagrams!");
			} else {
				System.out.println("These words are not anagrams");
			}

			// Adding new unique entry to array list and file
			cache.add(user);
			AnagramUtilMethods.addToFileStorage(user);
		} else {
			/*
			 * Notifying user that they have already checked these words, and reminding them
			 * of the result.
			 */
			if (user.isAnagram()) {
				System.out.println("You have already checked these text values, they were anagrams. Please try again ");
			} else {
				System.out.println(
						"You have already checked these text values, they were not anagrams. Please try again ");
			}
		}

	}
}
