package DataBaseTesting.DataBaseTesting;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {
	
	Connection con;
	Statement stmt;
	ResultSet rs;
	WebDriver driver = new ChromeDriver();
    String customerName;
    String customerLastName;
    String Email;
    String password;
    


    @BeforeTest
    public void mySetup() throws SQLException {
    	
    	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ClassicModels","root","1234");
    	driver.get("https://smartbuy-me.com/ar/account/register");
    	driver.manage().window().maximize();
    	
    }
    
    @Test(priority = 1 )
    public void InsertIntoDatabase() throws SQLException {
    	
    	String query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country, salesRepEmployeeNumber, creditLimit) VALUES (999, 'New Corp', 'Smith', 'John', '123456789', '123 Main St', 'Los Angeles', 'USA', 1370, 50000.00);";
        stmt = con.createStatement();
        int rowEffected = stmt.executeUpdate(query);  
        System.out.println("Rows inserted: " + rowEffected);
    	
    }
    
    @Test(priority = 2 )
    public void UpdateDatabase() throws SQLException {
    	
    	String query = "UPDATE customers SET creditLimit = 75000 WHERE customerNumber = 999";
        stmt = con.createStatement();
        int rowEffected = stmt.executeUpdate(query);  
        System.out.println("Rows updated: " + rowEffected);
    	
    }
    
    @Test(priority = 3 )
    public void ReadDatabase() throws SQLException {
    	
    	String query = "SELECT * FROM customers WHERE customerNumber = 103;";
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        
        while(rs.next()) {
        	
        	customerName = rs.getNString("contactFirstName");
        	System.out.println("Customer First Name: " + customerName);
        	
        	customerLastName=rs.getNString("contactLastName");
        	System.out.println("Customer last Name: " + customerLastName);
        	
        	Email = customerName+customerLastName+"@gmail.com";
        	System.out.println("Customer Email: " + Email);

        	password ="P@ssw0rd";
        	

        }
        
      WebElement FirstName =  driver.findElement(By.id("customer[first_name]"));
      FirstName.sendKeys(customerName);
      
      WebElement LastName =  driver.findElement(By.id("customer[last_name]"));
      LastName.sendKeys(customerLastName);
      
      WebElement theEmail = driver.findElement(By.id("customer[email]"));
      theEmail.sendKeys(Email);
      
      WebElement thePassword = driver.findElement(By.id("customer[password]"));
      thePassword.sendKeys(password);
      
    }
    
    @Test(priority = 2 )
    public void DeleteDatabase() throws SQLException {
    	
    	String query = "DELETE FROM customers WHERE customerNumber = 999;";
        stmt = con.createStatement();
        int rowEffected = stmt.executeUpdate(query);  
        System.out.println("Rows deleted: " + rowEffected);
    	
    }
  
    
}
