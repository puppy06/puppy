package weatherdashboard.servlet;
import java.io.*;

import com.opencsv.CSVWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import weatherdashboard.utils.PassUtil;
import weatherdashboard.bean.AccountBean;

@jakarta.servlet.annotation.WebServlet("/CreateAccount") // This annotattion means this servlet class is get HttpRequest from 'CreateAccount' action
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String userName = req.getParameter("firstName")+" "+req.getParameter("lastName");
		String emailAddress = req.getParameter("email");
		String password = req.getParameter("password");
		String passwordConfirm = req.getParameter("password");
		if(password.equals(passwordConfirm)) {
			String saltValue = PassUtil.getSaltvalue(30);
			String encryptedPassword = encryptPassword(password, saltValue);
			AccountBean bean = new AccountBean(firstName,lastName,emailAddress,password, encryptedPassword, saltValue);
			if(saveSignUpCSVFile(bean)) {
				pw.append("<br><br> Dear "
				+userName+": "
				+"Your account has been created successfully! "
				+ "<br><br>"	
				+ "Your email is "
				+ emailAddress
				+ "<br><br>"
				+ "<a href=\"login.jsp\">Please Go to Login</a>");
			}else {
				pw.append("<br><br> Dear "
					+userName+": "
					+"Your password has not been saved "
					+ "<br><br>"	
					+ "<a href=\register.jsp\">Please input again</a>");
			}
		}else {
			pw.append("<br><br> Dear "
			+userName+": "
			+ "<br><br>"
			+ "Your password "
			+ "is not consistent "
			+ "<br><br>"
			+ "<a href=\"register.jsp\">Please input again</a>");
		}
	}
	private boolean saveSignUpCSVFile(AccountBean bean) {
		String tomcatBase = System.getProperty("catalina.base");
		String filePath = String.format("%s/webapps/weatherdashboard/doc/useraccount/",tomcatBase);
		String fileName = "usersignup.csv";
		File dir = new File(filePath);
		boolean isEmpty = false;
		if(!dir.isDirectory()) {
			dir.mkdir();
		}
		File file = new File(filePath+fileName);
		if(!file.exists())
			isEmpty = true;
		try {
			//FileWriter outputFile = new FileWriter(file, true);
			CSVWriter writer = new CSVWriter(new FileWriter(filePath+fileName, true));
			if(isEmpty)
				writer.writeNext(new String[] {"Email","First Name","Last Name","Password","Encrypted Value"});
			writer.writeNext(new String[] {bean.getEmail(),bean.getFirstName(),bean.getLastName(),bean.getEncryptPass(),bean.getSaltValue()});
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private String encryptPassword(String pass, String saltValue) {
		String encryptedPassword = PassUtil.generateSecurePassword(pass, saltValue);
		return encryptedPassword;
	}
}
