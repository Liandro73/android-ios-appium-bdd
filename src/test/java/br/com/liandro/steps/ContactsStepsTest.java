package br.com.liandro.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class ContactsStepsTest extends BaseSteps {

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    public String fullName = String.format("%s %s", firstName, lastName);
    String company = faker.company().name();
    String phone = faker.phoneNumber().phoneNumber();
    String address = faker.address().streetAddress();
    String city = faker.address().city();
    String state = faker.address().stateAbbr();
    String postalCode = faker.address().zipCode();
    String email = String.format(
            "%s@%s.com",
            fullName.replace(" ", "").toLowerCase(),
            company.replace(" ", "").replace(",", "").toLowerCase());

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

    @And("I fill all the fields {string} {string} {string}")
    public void iFillAllTheFields(
            String phoneType, String emailType, String addressType) throws IOException {
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
        contactPageObject.clickOnButtonMoreFields(fullName);
    }

}
