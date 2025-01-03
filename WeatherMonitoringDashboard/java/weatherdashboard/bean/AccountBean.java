package weatherdashboard.bean;

public class AccountBean {
	private String firstName = "";
	private String lastName = "";
	private String password = "";
	private String email = "";
	private String encryptPass = "";
	private String saltValue = "";
	public AccountBean(String firstName, String lastName, 
			String email, String password, String encryptpass, String saltvalue) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.encryptPass = encryptpass;
		this.saltValue = saltvalue;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEncryptPass() {
		return encryptPass;
	}
	public void setEncryptPass(String encryptPass) {
		this.encryptPass = encryptPass;
	}
	public String getSaltValue() {
		return saltValue;
	}
	public void setSaltvalue(String saltValue) {
		this.saltValue = saltValue;
	}
}
