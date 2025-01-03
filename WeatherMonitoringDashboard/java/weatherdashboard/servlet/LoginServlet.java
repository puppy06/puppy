package weatherdashboard.servlet;

import java.io.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import weatherdashboard.utils.PassUtil;
import weatherdashboard.bean.AccountBean;

@jakarta.servlet.annotation.WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String emailAddress = req.getParameter("email");
		String password = req.getParameter("password");
		try {
			if(isUserExists(emailAddress, password)) {
				//pw.append("<br><br> "
				//		+"Login Successful"
				//		+ "<br><br>");
				req.getRequestDispatcher("charts.jsp").forward(req, res);
			}else {
				pw.append("<br><br> "
						+"Login unsuccessful"
						+ "<br><br>");
			}
		} catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private boolean isUserExists(String username, String password) throws CsvValidationException {
		String tomcatBase = System.getProperty("catalina.base");
		String filePath = String.format("%s/webapps/weatherdashboard/doc/useraccount/",tomcatBase);
		String fileName = "usersignup.csv";
		try {
			//FileWriter outputFile = new FileWriter(file, true);
			CSVReader writer = new CSVReader(new FileReader(filePath+fileName));
			String[] nextRecord;  
			int index = 0;
	        // we are going to read data line by line 
	        while ((nextRecord = writer.readNext()) != null) { 
	             if(index>0&&nextRecord[0].equals(username))
	            	 return PassUtil.verifyUserPassword(password, nextRecord[3], nextRecord[4]);
	             index++;
	        }
	        return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
