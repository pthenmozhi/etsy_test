package com.db.am.bauhaus.project.steplib;

import com.db.am.bauhaus.project.pages.MainSearchPage;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by ongshir on 05/10/2016.
 */
public class SearchUser extends ScenarioSteps {

    MainSearchPage mainSearchPage;

    String searchText = "craft";
    String dropDownProduct = "Hats & Caps";
    String searchIcon = "HOME & LIVING ";


    @Step
    public void search_from_input_box() {
        mainSearchPage.searchFromInputBox(searchText);
    }

    @Step
    public void search_from_drop_down(){
            mainSearchPage.searchFromDropDown();
    }
    @Step
    public void verify_result_for_top_categories() {
        assertThat(mainSearchPage.getTopCategoriesHeader(), containsString(searchText));
    }

    @Step
    public void verify_result_for_all_categories() {
        assertThat(mainSearchPage.getAllCategoriesHeader(), containsString(searchText));
    }

    @Step
    public void verify_result_for_Icon_Header(){
        assertThat(mainSearchPage.getIconHeader(), containsString(searchIcon));
    }

    @Step
    public void verify_search_result_for_drop_down_header(){
        assertThat(mainSearchPage.getDropDownResultHeader(),containsString(dropDownProduct));
    }
}
