package br.com.liandro.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class HomeContactsStepsTest extends BaseSteps {

    public HomeContactsStepsTest() {
    }

    @Given("that I am on the Contacts homepage")
    public void thatIAmOnTheContactsHomepage() throws IOException {
        homeContactsPageObject.checkSearchBarIsVisible(scenarioName);
    }

    @And("I click on Add Contact button")
    public void iClickOnAddContactButton() throws IOException {
        homeContactsPageObject.clickOnAddContactButton(scenarioName);
    }

    @And("I fill all the fields {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void iFillAllTheFields(
            String firstName, String lastName, String company, String phone, String phoneType,
            String email, String emailType, String address, String addressType) {

    }

    @When("I click on Save Contact button")
    public void iClickOnSaveContactButton() {

    }

    @Then("I should see the new contact added successfully")
    public void iShouldSeeTheNewContactAddedSuccessfully() {

    }

}
