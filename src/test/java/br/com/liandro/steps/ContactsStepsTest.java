package br.com.liandro.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class ContactsStepsTest extends BaseSteps {

    public ContactsStepsTest() {
    }

    @Given("that I am on the Contacts homepage")
    public void thatIAmOnTheContactsHomepage() throws IOException {
        if (onAndroid) {
            homeContactsPageObject.checkButtonSkipBackUpOptionIsVisibleAndClick(scenarioName);
        }
        homeContactsPageObject.checkSearchBarIsVisible(scenarioName);
    }

    @And("I click on Add Contact button")
    public void iClickOnAddContactButton() {
        homeContactsPageObject.clickOnAddContactButton();
    }

    @And("I fill all the fields {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void iFillAllTheFields(
            String firstName, String lastName, String company, String phone, String phoneType,
            String email, String emailType, String address, String city, String state, String postalCode, String addressType) throws IOException {
        createContactsPageObject.checkFieldFirstNameIsVisible(scenarioName);
        createContactsPageObject.fillFieldFirstName(scenarioName, firstName);
        createContactsPageObject.fillFieldLastName(scenarioName, lastName);
        createContactsPageObject.fillFieldCompany(scenarioName, company);
        if (oniOS) {
            createContactsPageObject.clickOnButtonAddPhone();
            createContactsPageObject.clickOnButtonSelectPhoneTypeAndSelectAType(phoneType);
        }
        createContactsPageObject.fillFieldPhone(scenarioName, phone);
        if (oniOS) {
            createContactsPageObject.clickOnButtonAddEmail();
            createContactsPageObject.clickOnButtonSelectEmailTypeAndSelectAType(emailType);
        }
        createContactsPageObject.fillFieldEmail(scenarioName, email);
        if (oniOS) {
            createContactsPageObject.clickOnButtonAddAddress();
            createContactsPageObject.clickOnButtonSelectAddressTypeAndSelectAType(addressType);
        }
        if (onAndroid) {
            createContactsPageObject.clickOnButtonMoreFields();
        }
        createContactsPageObject.fillFieldAddress(scenarioName, address, city, state, postalCode);
    }

    @When("I click on Save Contact button")
    public void iClickOnSaveContactButton() {
        createContactsPageObject.clickOnButtonSaveContact();
    }

    @Then("I should see the new contact added successfully")
    public void iShouldSeeTheNewContactAddedSuccessfully() {
        contactPageObject.clickOnButtonMoreFields();
    }

}
