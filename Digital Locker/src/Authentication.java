import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

    public class Authentication {
	private static Scanner sc;
	private static Scanner input;
	private static Scanner lockerInput;
	private static PrintWriter output;
	private static PrintWriter lockerOutput;
	private static Users users;
	private static UserCredentials userCredentials;
		
    public static void main(String[] args)throws Exception {
		System.out.println("---Welcome---");
		initApp();
		welcomeScreen();
		signInOptions();
		System.out.println("---Thanks For Visiting---");
    }
	public static void signInOptions() {
		System.out.println("1.Registration ");
		System.out.println("2.Login ");
		System.out.println("Enter Your choice here: ");
		int option = sc.nextInt();
		switch(option) {
			case 1 : 
				registerUser();
				break;
			case 2 :
				loginUser();
				break;
			default :
				System.out.println("Invalid Choice!");
				System.out.println("select option 1 Or 2");
				break;
		}
		sc.close();
		input.close();
	}
	public static void lockerOptions(String inputUsername) {
		System.out.println("1.Fetch All Stored Credentials ");
		System.out.println("2.Enter Credentials ");
		int option = sc.nextInt();
		switch(option) {
			case 1 : 
				fetchCredentials(inputUsername);
				break;
			case 2 :
				storeCredentials(inputUsername);
				break;
			default :
				System.out.println("Invalid Choice!");
				System.out.println("select option 1 Or 2");
				break;
		}
		lockerInput.close();
	}
	public static void registerUser() {
		System.out.println("*************************************");
		System.out.println();
		System.out.println("   WELCOME TO REGISTRATION PAGE	 ");
		System.out.println();
		System.out.println("*************************************");
		
		System.out.println("Enter Username :");
		String username = sc.next();
		users.setUsername(username);
		
		System.out.println("Enter Password :");
		String password = sc.next();
		users.setPassword(password);
		
		output.println(users.getUsername());
		output.println(users.getPassword());
		
		System.out.println("User Registration Suscessful!");
		output.close();
	}
	public static void loginUser() {
		System.out.println("************************************");
		System.out.println();
		System.out.println("    WELCOME TO LOGIN PAGE	 ");
		System.out.println();
		System.out.println("************************************");
		System.out.println("Enter Username :");
		String inputUsername = sc.next();
		boolean found = false;
		while(input.hasNext() && !found) {
			if(input.next().equals(inputUsername)) {
				System.out.println("Enter Password :");
				String inpPassword = sc.next();
				if(input.next().equals(inpPassword)) {
					System.out.println("Successfully Login!");
					found = true;
					lockerOptions(inputUsername);
					break;
				}
			}
		}
		if(!found) {
			System.out.println("User Not Found!!");
			System.out.println("Login Failure!!");
			System.out.println("Try Again!!!!");
		}
	}
	public static void welcomeScreen() {
		System.out.println("***************************************");
		System.out.println();
		System.out.println("      WELCOME TO LOCKME.COM		");
		System.out.println("   YOUR PERSONAL DIGITAL LOCKER	");
		System.out.println();
		System.out.println("***************************************");
		
	}
	public static void storeCredentials(String loggedInUser) {
		System.out.println("**********************************************************");
		System.out.println();
		System.out.println(" WELCOME TO DIGITAL LOCKER STORE YOUR CREDENTIALS HERE ");
		System.out.println();
		System.out.println("**********************************************************");
		
		userCredentials.setLoggedInUser(loggedInUser);
		
		System.out.println("Enter Site Name :");
		String siteName = sc.next();
		userCredentials.setSiteName(siteName);
		
		System.out.println("Enter Username :");
		String username = sc.next();
		userCredentials.setUsername(username);
		
		System.out.println("Enter Password :");
		String password = sc.next();
		userCredentials.setPassword(password);
		
		lockerOutput.println(userCredentials.getLoggedInUser());
		lockerOutput.println(userCredentials.getSiteName());
		lockerOutput.println(userCredentials.getUsername());
		lockerOutput.println(userCredentials.getPassword());
		
		System.out.println("YOUR CREDENTIALS ARE STORED AND SECURED!");
		lockerOutput.close();		
	}
	public static void fetchCredentials(String inpUsername) {
		System.out.println("**************************************");
		System.out.println();
		System.out.println("   WELCOME TO DIGITAL LOCKER 	 ");
		System.out.println("       YOUR CREDS ARE 	 ");
		System.out.println();
		System.out.println("**************************************");
		System.out.println(inpUsername);
		
		while(lockerInput.hasNext()) {
        //System.out.println(lockerInput.hasNext());
			if(lockerInput.next().equals(inpUsername)) {
				System.out.println("Site Name: "+lockerInput.next());
				System.out.println("User Name: "+lockerInput.next());
				System.out.println("User Password: "+lockerInput.next());
			}
		}
	}
	public static void initApp() {

		File  dbFile = new File("database.txt");
		File  lockerFile = new File("locker-file.txt");
		
		try {
			input = new Scanner(dbFile);
			lockerInput = new Scanner(lockerFile);
			sc = new Scanner(System.in);	
			output = new PrintWriter( new FileWriter(dbFile,true));
			lockerOutput = new PrintWriter( new FileWriter(lockerFile,true));		
			users = new Users();
			userCredentials  = new UserCredentials();		
			
		} catch (IOException e) {
			System.out.println("File Not Found ");
			System.out.println("Create a text file first!!!");
		}
	}
}