package com.actitimeautomation.framework;

import Common.BaseClass;
import Common.PropertyHandling;
import Common.TestListeners;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DepartmentPage;
import pages.LoginPage;

import java.io.IOException;
@Listeners(TestListeners.class)
public class DepartmentTest extends BaseClass {
    WebDriver driver;

    PropertyHandling propertyHandling;

    DepartmentPage departmentPage;
    @BeforeClass
    public void setup() throws IOException, InterruptedException {
        propertyHandling = new PropertyHandling();
        String browser = propertyHandling.getProperty("browser");
       launchBrowser(browser);
        driver = super.driver;
        String actitimeUrl = propertyHandling.getProperty("actitimeUrl");
        driver.get(actitimeUrl);
        LoginPage loginPage = new LoginPage(driver);
        String username = propertyHandling.getProperty("username");
        String password = propertyHandling.getProperty("password");
        Thread.sleep(3000);
        loginPage.login(username, password);
        DepartmentPage departmentPage = new DepartmentPage(driver);
        this.departmentPage=departmentPage;
    }
    @DataProvider()
    public Object[][] getTestData(){
        Object[][]obj=new Object[][]
                {
                        {"Software Testing1"}
                };
        return obj;
    }
    @Test(priority = 1,dataProvider = "getTestData")
    public void createDepartment(Object deptName) throws InterruptedException {
        departmentPage.createDept(deptName.toString());
    }
}
