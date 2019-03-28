package com.itest.personal.utility;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginTestStepDefinition {

	WebDriver driver = null;
	

	@Given("^I am in personal utility page$")
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://192.168.0.102:8080/PersonalUtility/");
	}

	@Given("^I enter user as \"([^\"]*)\" and password as \"([^\"]*)\"$")
	public void i_enter_user_as_and_password_as(String arg1, String arg2) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNameId")));
		element.sendKeys(arg1);
		
		driver.findElement(By.id("passwordId")).sendKeys(arg2);
	}

	@When("^I click the login button$")
	public void i_click_the_login_button() throws Throwable {
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@Then("^The \"([^\"]*)\" tab should be visible$")
	public void the_settings_tab_should_be_visible(String arg0) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		String xpath="//a[contains(text(),'"+arg0+"')]";
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		boolean isSettingsVisible = element.isDisplayed();
		Assert.assertTrue(isSettingsVisible);
	}

	@After
	public void cleanUp() {
		driver.close();
	}

}
