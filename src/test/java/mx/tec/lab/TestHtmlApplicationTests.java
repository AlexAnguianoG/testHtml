package mx.tec.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

//@SpringBootTest
public class TestHtmlApplicationTests {
	private WebClient webClient;

	@BeforeEach
	public void init() throws Exception {
		webClient = new WebClient();
	}

	@AfterEach
	public void close() throws Exception {
		webClient.close();
	}

	//@Disabled
	@Test
	public void givenAClient_whenEnteringAutomationPractice_thenPageTitleIsCorrect() throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php");
		assertEquals("My Store", page.getTitleText());
	}
	
	//@Disabled
	@Test
	public void givenAClient_whenEnteringLoginCredentials_thenAccountPageIsDisplayed() throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
		emailField.setValueAttribute("alexag@alexag.com");
		HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
		passwordField.setValueAttribute("alexagalex");
		HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
		HtmlPage resultPage = submitButton.click();
		assertEquals("My account - My Store", resultPage.getTitleText());
	}
	
	//@Disabled
	@Test
	public void givenAClient_whenEnteringWrongLoginCredentials_thenAuthenticationPageIsDisplayed() throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
		emailField.setValueAttribute("alexag@alexag.com");
		HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
		passwordField.setValueAttribute("alexagalexag");
		HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
		HtmlPage resultPage = submitButton.click();
		assertEquals("Login - My Store", resultPage.getTitleText());
		//fail("Test not yet implemented!");
	}
	
	//@Disabled
	@Test
	public	void givenAClient_whenEnteringWrongLoginCredentials_thenErrorMessageIsDisplayed() throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
		emailField.setValueAttribute("alexag@alexag.com");
		HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
		passwordField.setValueAttribute("alexagalexag");
		HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
		HtmlPage resultPage = submitButton.click();
		HtmlListItem resultListItem = (HtmlListItem) resultPage.getFirstByXPath("//div[@class='alert alert-danger']/ol/li");
		assertEquals("Authentication failed.", resultListItem.getTextContent());
		//fail("Test not yet implemented!");
	}

	//@Disabled
	@Test
	public	void givenAClient_whenSearchingNotExistingProduct_thenNoResultsDisplayed() throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php");
		HtmlTextInput input = (HtmlTextInput) page.getElementById("search_query_top");
		input.setValueAttribute("alexag");
		HtmlButton submitButton = (HtmlButton) page.getElementByName("submit_search");
		HtmlPage resultPage = submitButton.click();
		HtmlParagraph resultParagraph = (HtmlParagraph) resultPage.getFirstByXPath("//p[@class='alert alert-warning']");
		assertEquals("No results were found for your search \"alexag\"",resultParagraph.getTextContent().trim());
		
	}
	
	//@Disabled
	@Test
	public	void givenAClient_whenSearchingEmptyString_thenPleaseEnterDisplayed() throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php");
		HtmlTextInput input = (HtmlTextInput) page.getElementById("search_query_top");
		input.setValueAttribute("");
		HtmlButton submitButton = (HtmlButton) page.getElementByName("submit_search");
		HtmlPage resultPage = submitButton.click();
		HtmlParagraph resultParagraph = (HtmlParagraph) resultPage.getFirstByXPath("//p[@class='alert alert-warning']");
		assertEquals("Please enter a search keyword", resultParagraph.getTextContent().trim());
		
		//fail("Test not yet implemented!");
	}

	//@Disabled
	@Test
	public	void givenAClient_whenSigningOut_thenAuthenticationPageIsDisplayed() throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
		emailField.setValueAttribute("alexag@alexag.com");
		HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
		passwordField.setValueAttribute("alexagalex");
		HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
		HtmlPage loginPage = submitButton.click();
		HtmlAnchor anchor = (HtmlAnchor) loginPage.getFirstByXPath("//a[@class='logout']");
		HtmlPage logoutPage = anchor.click();
		assertEquals("Login - My Store", logoutPage.getTitleText());
		//fail("Test not yet implemented!");
	}

}
