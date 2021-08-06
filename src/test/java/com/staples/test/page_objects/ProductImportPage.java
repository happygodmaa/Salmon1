package com.staples.test.page_objects;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.staples.test.framework.PageObject;
import com.staples.test.framework.helpers.PropertyReader;

public class ProductImportPage extends PageObject{
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductImportPage.class);
	
	By backoffice_loginName 								= By.id("LoginForm_Login");
	By backoffice_loginPassword 							= By.id("LoginForm_Password");
	By backoffice_organizationName 							= By.id("LoginForm_RegistrationDomain");
	By backoffice_loginButton 								= By.cssSelector("div.buttons>input.loginbutton");
	By backoffice_channelSelectForm 						= By.cssSelector("span.application-type-intershop_EnterpriseBackoffice.application-level-organization");
	By backofice_channelNames								= By.cssSelector("li.application-type-intershop_B2CBackoffice.application-level-channel >label.ui-corner-all>span");
	By backoofice_channelCatalog							= By.cssSelector("li>a[href *= 'ViewOverview-ChannelCatalog']");
	By backoffice_channelCatalog_productImport				= By.xpath("//li/a[contains(@href,'ViewOverview-ChannelCatalog')]/../ul/li/a[contains(@href,'ViewCatalogImpex_52-Status')]");
	By backoffice_channelCatalog_ProductImportUpload_button	= By.cssSelector("td.button>input[name='ImportFiles'][value='Upload']");
	By backoffice_productFileName							= By.cssSelector("tr>td:nth-child(2).table_detail.s");
	String _productFileName									= "_productFileName";
	String xpath_backoffice_productFile_CheckBox 			= "//tr/td[@class='table_detail s'][.='"+_productFileName+"']/../td[@class='e s center']";
	By productUpload_DeleteButton 							= By.cssSelector("td.button>input[name='ConfirmDeleteFiles']");
	By productUpload_confirmDeleteButton					= By.cssSelector("td.button>input[name='DeleteFiles']");
	By productUpload_clickOnChooseFileButton				= By.cssSelector("td>input.inputfield_en");
	By productUpload_uploadButton 							= By.cssSelector("td.button>input[name='Upload']");
	By backoffice_productImport_importButton 				= By.cssSelector("td>input.button[name='ProductImport']");
	String productImport_selectRadioButton					= "//tr/td[@class='table_detail w s']/label[.='"+_productFileName+"']/../../td[@class='s center']";
	By backoffice_productImport_nextButton					= By.cssSelector("td.button>input[name='ValidateFile']");
	By backoffice_productImport_validate_previousButton		= By.cssSelector("tr>td:nth-child(1)>table>tbody>tr>td.button>input.button");
	By backoffice_productImport_validate_nextButton			= By.cssSelector("tr>td:nth-child(2)>table>tbody>tr>td.button:nth-child(1)>input.button");
	By backoffice_productImportStatus_lastPage				= By.cssSelector("td.pagecursortxt>input.pagecursorbtn[value*='>>']");
	By backoffice_productImportStatus_refreshButton			= By.cssSelector("tr>td[align='left']>table>tbody>tr>td.button>input.button");
	By backoffice_productImport_Status						= By.cssSelector("tr>td.table_detail.s.top");
	
	
	public void loginToBackoffice() {
		enterBackofficeUserNameAndPassword();
		enterBackofficeOrganizationName();
		clickOnBackofficeLoginButton();
		verifyBackofficeHomePage();
	}
	
	private void enterBackofficeUserNameAndPassword() {
		if(isElementPresent(backoffice_loginName)) {
			waitForExpectedElement(backoffice_loginName).sendKeys(PropertyReader.getProp("backoffice.user.name"));
			if(isElementPresent(backoffice_loginPassword)) {
				waitForExpectedElement(backoffice_loginPassword).sendKeys(PropertyReader.getProp("backoffice.password"));
			}
			else {
				customAssertFailure(backoffice_loginPassword);
			}
		}
		else {
			customAssertFailure(backoffice_loginName);
		}
	}
	
	
	private void enterBackofficeOrganizationName() {
		if(isElementPresent(backoffice_organizationName)) {
			waitForExpectedElement(backoffice_organizationName).sendKeys(PropertyReader.getProp("backoffice.organizationName"));
		}
		else {
			customAssertFailure(backoffice_organizationName);
		}
	}
	
	private void clickOnBackofficeLoginButton() {
		if(isElementPresent(backoffice_loginButton)) {
			waitForExpectedElement(backoffice_loginButton).click();
		}
		else {
			customAssertFailure(backoffice_loginButton);
		}
	}
	
	private void verifyBackofficeHomePage() {
		if(isElementPresent(backoffice_channelSelectForm)) {
			Assert.assertEquals(waitForExpectedElement(backoffice_channelSelectForm).getText(), PropertyReader.getProp("backoffice.organizationName"));
		}
		else {
			customAssertFailure(backoffice_channelSelectForm);
		}
	}
	
	public void clickOnChannelSelectionForm() {
		if(isElementPresent(backoffice_channelSelectForm)) {
			waitForExpectedElement(backoffice_channelSelectForm).click();
		}
		else {
			customAssertFailure(backoffice_channelSelectForm);
		}
	}
	
	public void selectChannelFromBackOffice(String channelName) {
		if(isElementPresent(backofice_channelNames)) {
			clickOnChannelSelectionForm();
			List<WebElement> channelLists = presenceOfAllElementsLocatedBy(backofice_channelNames);
			clickOnSpecificElementUsingInputText(channelLists,channelName);
		}
		else {
			customAssertFailure(backofice_channelNames);
		}
	}
	
	public void navigateToChannelCatalog() {
		if(isElementPresent(backoofice_channelCatalog)) {
			actionToMouseOver(backoofice_channelCatalog);
		}
		else {
			customAssertFailure(backoofice_channelCatalog);
		}
	}
	
	public void clickOnProductImportExportButton() {
		if(isElementPresent(backoffice_channelCatalog_productImport)) {
			waitForExpectedElement(backoffice_channelCatalog_productImport).click();
		}
		else {
			customAssertFailure(backoffice_channelCatalog_productImport);
		}
	}
	
	public void clickOnProductFileUploadButton()
	{
		if(isElementPresent(backoffice_channelCatalog_ProductImportUpload_button)) {
			waitForExpectedElement(backoffice_channelCatalog_ProductImportUpload_button).click();
		}
		else {
			customAssertFailure(backoffice_channelCatalog_ProductImportUpload_button);
		}
	}
	
	public void verifyFileExistInBackoffice(String productFileName) {
		if(isElementPresent(backoffice_productFileName)) {
			List<WebElement> productFileLists = presenceOfAllElementsLocatedBy(backoffice_productFileName);
			boolean fileExistStatus = verifyPresenceOfElementsUsingListsText(productFileLists, productFileName);
			if(fileExistStatus) {
				By productFileCheckBox = By.xpath(xpath_backoffice_productFile_CheckBox.replace(_productFileName, productFileName));
				if(isElementPresent(productFileCheckBox)) {
					waitForExpectedElement(productFileCheckBox).click();
					if(isElementPresent(productUpload_DeleteButton)) {						
						waitForExpectedElement(productUpload_DeleteButton).click();
						
						if(isElementPresent(productUpload_confirmDeleteButton)) {						
							waitForExpectedElement(productUpload_confirmDeleteButton).click();
							
						}
						else {
							customAssertFailure(productUpload_confirmDeleteButton);
						}
					}
					else {
						customAssertFailure(productUpload_DeleteButton);
					}
				}
				else {
					customAssertFailure(backoffice_productFileName);
				}
			}
		}
		else {
			customAssertFailure(backoffice_productFileName);
		}
	}
	
	public void uploadProductImportFile(String productFileName) {
		String productFilePath = System.getProperty("user.dir")+PropertyReader.getProp("productImport.product.file.path");
		if(isElementPresent(productUpload_clickOnChooseFileButton)) {
			LOG.info("Product file path is: ["+productFilePath+productFileName+"]");
			if(waitForExpectedElement(productUpload_clickOnChooseFileButton).isEnabled()) {
				LocalFileDetector detector = new LocalFileDetector();
				File file = detector.getLocalFile(productFilePath+productFileName);
				((RemoteWebElement) waitForExpectedElement(productUpload_clickOnChooseFileButton)).setFileDetector(detector);
				waitForExpectedElement(productUpload_clickOnChooseFileButton).sendKeys(file.getAbsolutePath());
				if(isElementPresent(productUpload_uploadButton)) {
					waitForExpectedElement(productUpload_uploadButton).click();
				}
				else {
					customAssertFailure(productUpload_uploadButton);
				}
			}
		}
		else {
			customAssertFailure(productUpload_clickOnChooseFileButton);
		}
	}
	
	public void clickOnProductImportButton() {
		if(isElementPresent(backoffice_productImport_importButton)) {
			waitForExpectedElement(backoffice_productImport_importButton).click();
		}
		else {
			customAssertFailure(backoffice_productImport_importButton);
		}
	}
	
	public void selectProductFileForImport(String fileName) {
		By xpath_productFileNameRadioButton = By.xpath(productImport_selectRadioButton.replace(_productFileName, fileName));
		waitForExpectedElement(xpath_productFileNameRadioButton).click();
	}
	
	public void clickOnProductImportNextButton() {
		if(isElementPresent(backoffice_productImport_nextButton)) {
			waitForExpectedElement(backoffice_productImport_nextButton).click();
		}
		else {
			customAssertFailure(backoffice_productImport_nextButton);
		}
	}
	
	public void ClickOnProductImportValidateNextButton() {
		WebElement previousElement;
		if(isElementPresent(backoffice_productImport_validate_previousButton)) {
			for(int i = 0; i<5; i++) {
				previousElement = elementToBeClickable(backoffice_productImport_validate_previousButton);
				if(previousElement.isEnabled()) {
					if(isElementPresent(backoffice_productImport_validate_nextButton)) {
						waitForExpectedElement(backoffice_productImport_validate_nextButton).click();
						break;
					}
					else {
						customAssertFailure(backoffice_productImport_validate_nextButton);
					}
				}
			}
		}
		else {
			customAssertFailure(backoffice_productImport_validate_previousButton);
		}
	}
	
	public void clickOnProductImport_ImportButton() {
		if(isElementPresent(backoffice_productImport_validate_nextButton)) {
			waitForExpectedElement(backoffice_productImport_validate_nextButton).click();
		}
		else {
			customAssertFailure(backoffice_productImport_validate_nextButton);
		}
	}
	
	public void clickOnProductImportStatusLastPage() {
		if(isElementPresent(backoffice_productImportStatus_lastPage)) {
			waitForExpectedElement(backoffice_productImportStatus_lastPage).click();
		}
		else {
			customAssertFailure(backoffice_productImport_Status);
		}
	}
	
	public void clickOnProductImportRefreshButton() {
		if(isElementPresent(backoffice_productImportStatus_refreshButton)) {
			waitForExpectedElement(backoffice_productImportStatus_refreshButton).click();
		}
		else {
			customAssertFailure(backoffice_productImportStatus_refreshButton);
		}
	}
		
	
	public void verifyProductImportStatus(String expectedProductImportStatus) throws InterruptedException {
		String productImportStatus = null;
		List<WebElement> productImportLists = null;
		int productImportStatusSize = 0;
		if(isElementPresent(backoffice_productImport_Status)) {
				productImportLists = presenceOfAllElementsLocatedBy(backoffice_productImport_Status);
				productImportStatusSize = productImportLists.size();
				if(productImportStatusSize > 0) {
					for(int i = 0; i<= 5; i++) {
						productImportStatus = productImportLists.get(productImportStatusSize-1).getText();
						if(productImportStatus.equalsIgnoreCase(PropertyReader.getProp("backoffice.product.import.running.status"))) {
							Thread.sleep(8000);
						}
						else {
							break;
						}
						productImportLists = presenceOfAllElementsLocatedBy(backoffice_productImport_Status);
						productImportStatusSize = productImportLists.size();
					}
					assertEquals(productImportStatus,expectedProductImportStatus);
				}
			}
		else {
			customAssertFailure(backoffice_productImportStatus_lastPage);
		}
	}
}
