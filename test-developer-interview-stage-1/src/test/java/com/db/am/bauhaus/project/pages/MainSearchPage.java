package com.db.am.bauhaus.project.pages;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by ongshir on 05/10/2016.
 */
@DefaultUrl("/")
public class MainSearchPage extends PageObject {

    String expectedIconHeader;

    @FindBy(css = "#search-query")
    WebElementFacade inputBox;

    @FindBy(css = ".btn.btn-orange.btn-append")
    WebElementFacade searchButton;

    @FindBy(css = ".h4.pb-xs-1-5")
    WebElementFacade topCategoriesHeaderElement;

    @FindBy(css = ".h1.conform-heading.display-inline")
    WebElementFacade allCategoriesHeader;

    @FindBy(css = ".vesta-hp-category-title.vesta-hp-curated-category.ml-xs-2.ml-sm-0.position-relative")
    WebElementFacade searchIconElement;

    @FindBy(css = ".text-smaller.text-link-secondary>span")
    WebElementFacade iconResultHeader;

    @FindBy(css = ".text-smaller.text-link-secondary>span")
    WebElementFacade primaryNavigation;

    @FindBy(css = ".float-left>h1")
    WebElementFacade dropDownProductNameElement;

    @FindBy(css = "#catnav-primary-link-10855")
    WebElementFacade primaryTabElement;

    @FindBy(css = "#side-nav-category-link-10856")
    WebElementFacade primaryDropDownElement;

    @FindBy(css = "#catnav-l3-10857")
    WebElementFacade secondaryDropDownElement;

    public  MainSearchPage(WebDriver d) {
        super(d);
    }

    private void executeScript(String s) {
    }

    public void searchFromDropDown()  {
        primaryTabElement.click();
        primaryDropDownElement.click();
        secondaryDropDownElement.click();
    }

    public void searchFromInputBox(String searchText) {
        inputBox.waitUntilPresent().sendKeys(searchText);
        searchButton.click();
    }

    public String getTopCategoriesHeader() {
        return topCategoriesHeaderElement.getText();
    }

    public String getAllCategoriesHeader() {
        return allCategoriesHeader.getText();

    }

    public String getIconHeader(){

        //This above method is simple way to find the icon using the json,
        // I referred the https://www.etsy.com/developers/documentation/getting_started/api_basics to get the details
        // There are no full details about API URI and headers , but we can use above method to find the list of icons etc

        RestAssured.baseURI = "https://openapi.etsy.com/v2/listings";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/jsonpath"); //I am not sure about the JSON PATH
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        return bodyAsString;

    }

    public String getDropDownResultHeader(){
        return dropDownProductNameElement.getText();
    }
}
