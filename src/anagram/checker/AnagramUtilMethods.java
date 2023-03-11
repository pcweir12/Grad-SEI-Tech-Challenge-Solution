/**
 * 
 */
package anagram.checker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author philip
 *
 */
public class AnagramUtilMethods {

	/**
	 * Method checks if storage file exists and if not will create a file with the
	 * headings "Username, Text Value 1, Text Value 2, Is Anagram".
	 */
	public static void createStorageFile() {
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;
		File storageFile = new File("AnagramList.csv");

		// Checking if file exists and if not then creating it.
		if (!storageFile.exists()) {
			try {
				storageFile.createNewFile();

				// Creating headings for file
				fileWriter = new FileWriter(storageFile);
				bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write("Username, Text Value 1, Text Value 2, Is Anagram \n");

				// Closing resources
				bufferedWriter.close();

			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	/**
	 * Method populates cache (an array list) with the data stored within the long
	 * term storage file ("AnagramList.csv")
	 * 
	 * @param cache
	 */
	public static void populateCache(ArrayList<User> cache) {
		File storageFile = new File("AnagramList.csv");
		FileReader fileReader;
		BufferedReader bufferedReader;
		String userEntry;
		String[] userEntrySplit;

		try {
			fileReader = new FileReader(storageFile);
			bufferedReader = new BufferedReader(fileReader);

			// Reading past headings in the storage file
			bufferedReader.readLine();

			// Reading first line of data from the storage file
			userEntry = bufferedReader.readLine();

			// Processing file data
			while (userEntry != null) {
				/*
				 * Splitting one file entry into three String values and storing these in a
				 * String array
				 */
				userEntrySplit = userEntry.split(",");

				// Creating User object using data stored in String array
				User user = new User(userEntrySplit[0], userEntrySplit[1], userEntrySplit[2]);

				// Adding user object to cache
				cache.add(user);

				// Reading next line in file
				userEntry = bufferedReader.readLine();
			}

			// Closing resources
			bufferedReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/**
	 * Method asks the user for their data entry, it then creates and returns a user
	 * object containing this information.
	 * 
	 * @return
	 */
	public static User createUserEntry() {
		User user = new User();
		String userProceed = "no";
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to the Anagram Checker App!\n");
		System.out.println(
				"You will be asked to enter a username value and two text values which will be checked as anagrams of each other.");
		System.out.println("Values cannot contain spaces or numbers\n");

		do {
			try {

				// Ask user for data entry

				// Setting username
				System.out.println("Please type your username and press enter:");
				user.setUsername(scanner.nextLine());

				// Setting text value 1
				System.out.println("Please type your first word and press enter:");
				user.setTextValue1(scanner.nextLine());

				// Setting text value 2
				System.out.println("Please type your second word and press enter:");
				user.setTextValue2(scanner.nextLine());

				// Setting isAnagram
				user.setIsAnagram();

				// Setting text value 2
				System.out.println("Are you ready to proceed (type yes or y to continue): ");
				userProceed = scanner.nextLine();

			} catch (IllegalArgumentException e) {
				System.out.println("Illegal argument: " + e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("Input mismatch: " + e.getMessage());
			}
		} while ((!userProceed.equalsIgnoreCase("yes")) && (!userProceed.equalsIgnoreCase("y")));

		// closing resources
		scanner.close();

		// Return user object
		return user;
	}

	/**
	 * Method searches cache and returns an array containing the previous input data
	 * created by the current user/username.
	 * 
	 * This method makes the assumption that username is case sensitive.
	 * 
	 * @param cache
	 * @param username
	 * @return
	 */
	public static ArrayList<User> searchByUsername(ArrayList<User> cache, String username) {
		ArrayList<User> searchResults = new ArrayList<>();
		for (User user : cache) {
			if (user.getUsername().equals(username)) {
				searchResults.add(user);

			}
		}
		return searchResults;

	}

	/**
	 * Method checking cache to determine if a particular user/username has entered
	 * the two text value combination before. Method is based on the assumption that
	 * different users/usernames can enter the same text values combination.
	 * 
	 * Returns true if user entry is a duplicate and false if entry is unique.
	 * 
	 * @param searchResults
	 * @param user
	 */
	public static boolean checkCacheForDuplicate(ArrayList<User> searchResults, User user) {
		boolean previousEntry = false;
		for (User prevoiusUser : searchResults) {

			if ((user.getTextValue1().equalsIgnoreCase(prevoiusUser.getTextValue1())
					&& user.getTextValue2().equalsIgnoreCase(prevoiusUser.getTextValue2()))
					|| (user.getTextValue1().equalsIgnoreCase(prevoiusUser.getTextValue2())
							&& user.getTextValue2().equalsIgnoreCase(prevoiusUser.getTextValue1()))) {
				previousEntry = true;
				/*
				 * Making the assumption that text values with the exact character arrangement
				 * cannot be stored as a new entry(not case sensitive). Words containing the
				 * same characters as a previous entry, arranged in a different order will
				 * however be treated as a new entry.
				 */
			}
		}
		return previousEntry;
	}

	/**
	 * Method appends user entry to long term file storage ("AnagramList.csv").
	 * 
	 * @param user
	 */
	public static void addToFileStorage(User user) {
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;
		File file = new File("AnagramList.csv");

		try {
			fileWriter = new FileWriter(file, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(user.getUsername() + "," + user.getTextValue1() + "," + user.getTextValue2() + ","
					+ user.isAnagram() + "\n");

			// Closing resources
			bufferedWriter.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}