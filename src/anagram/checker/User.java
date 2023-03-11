/**
 * 
 */
package anagram.checker;

import java.util.Arrays;

/**
 * @author philip
 *
 */
public class User {

	// Instance variables
	private String username;
	private String textValue1;
	private String textValue2;
	private boolean isAnagram;

	// Constructors
	/**
	 * Default constructor with no arguments (args)
	 */
	public User() {

	}

	/**
	 * Constructor with arguments args
	 * 
	 * @param username
	 * @param userTextValue1
	 * @param userTextValue2
	 * @throws IllegalArgumentException
	 */
	public User(String username, String textValue1, String textValue2) throws IllegalArgumentException {
		this.setUsername(username);
		this.setTextValue1(textValue1);
		this.setTextValue2(textValue2);
		this.setIsAnagram();
	}

	// Methods (assuming that all values cannot include numbers or spaces including
	// the username)
	/**
	 * Method returns the username
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setting username
	 * 
	 * @param username
	 * @throws IllegalArgumentException
	 */
	public void setUsername(String username) throws IllegalArgumentException {
		char[] characters = username.toCharArray();
		for (Character c : characters) {
			if (Character.isDigit(c)) {
				throw new IllegalArgumentException("Username cannot contain numbers");
			} else if (username.contains(" ")) {
				throw new IllegalArgumentException("Username cannot contain spaces");
			} else {
				this.username = username;
			}
		}

	}

	/**
	 * Method returns the first text value to be checked
	 * 
	 * @return the textValue1
	 */
	public String getTextValue1() {
		return textValue1;
	}

	/**
	 * Setting first text value to be checked as an anagram
	 * 
	 * @param textValue1
	 * @throws IllegalArgumentException
	 */
	public void setTextValue1(String textValue1) throws IllegalArgumentException {
		char[] characters = textValue1.toCharArray();
		for (Character c : characters) {
			if (Character.isDigit(c)) {
				throw new IllegalArgumentException("Words cannot contain numbers");
			} else if (textValue1.contains(" ")) {
				throw new IllegalArgumentException("Words cannot contain spaces");
			} else {
				this.textValue1 = textValue1;
			}
		}

	}

	/**
	 * Method returns the second text value to be checked
	 * 
	 * @return the textValue2
	 */
	public String getTextValue2() {
		return textValue2;
	}

	/**
	 * Setting second text value to be checked as an anagram
	 * 
	 * @param textValue2
	 * @throws IllegalArgumentException
	 */
	public void setTextValue2(String textValue2) throws IllegalArgumentException {
		char[] characters = textValue2.toCharArray();
		for (Character c : characters) {
			if (Character.isDigit(c)) {
				throw new IllegalArgumentException("Words cannot contain numbers");
			} else if (textValue2.contains(" ")) {
				throw new IllegalArgumentException("Words cannot contain spaces");
			} else {
				this.textValue2 = textValue2;
			}
		}

	}

	/**
	 * Method returns boolean isAnagram value
	 * 
	 * @return the isAnagram
	 */
	public boolean isAnagram() {
		return isAnagram;
	}

	/**
	 * Setting the boolean value of isAnagram: true if anagram, false if not
	 * 
	 * @param isAnagram
	 */
	public void setIsAnagram() {
		this.isAnagram = checkForAnagrams();
	}

	/**
	 * Method checking for anagrams
	 * 
	 * @return the anagram
	 */
	private boolean checkForAnagrams() {
		boolean anagram;

		// converting all characters within the text value to lowercase
		String textValue1Lower = this.getTextValue1().toLowerCase();
		String textValue2Lower = this.getTextValue2().toLowerCase();

		// storing text values as char arrays
		char[] charArray1 = (textValue1Lower.toCharArray());
		char[] charArray2 = (textValue2Lower.toCharArray());

		// Sorting the char arrays
		Arrays.sort(charArray1);
		Arrays.sort(charArray2);

		if ((charArray1.length == charArray2.length) && (Arrays.equals(charArray1, charArray2))) {
			anagram = true;
		} else {
			anagram = false;
		}
		return anagram;
	}
}
