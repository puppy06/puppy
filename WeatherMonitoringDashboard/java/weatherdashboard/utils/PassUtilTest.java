package weatherdashboard.utils;

public class PassUtilTest {

	/* Driver Code */
	public static void main(String[] args) {
		/* Plain text Password. */
		String password = "hank";

		/* generates the Salt value. It can be stored in a database. */
		String saltvalue = PassUtil.getSaltvalue(30);

		/* generates an encrypted password. It can be stored in a database. */
		String encryptedpassword = PassUtil.generateSecurePassword(password, saltvalue);

		/* Print out plain text password, encrypted password and salt value. */
		System.out.println("Plain text password = " + password);
		System.out.println("Secure password = " + encryptedpassword);
		System.out.println("Salt value = " + saltvalue);

		/* verify the original password and encrypted password */
		Boolean status = PassUtil.verifyUserPassword(password, encryptedpassword, saltvalue);
		if (status == true)
			System.out.println("Password Matched!!");
		else
			System.out.println("Password Mismatched");
	}
}