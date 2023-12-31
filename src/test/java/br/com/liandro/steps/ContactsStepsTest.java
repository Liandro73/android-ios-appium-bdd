package br.com.liandro.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Platform;

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

    /**
     * Add new contact successfully
     */
    @Given("that I am on the Contacts homepage")
    public void thatIAmOnTheContactsHomepage() throws IOException {
        if (Platform.ANDROID.equals(pageObjectHelper.getPlatform())) {
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
        if (Platform.IOS.equals(pageObjectHelper.getPlatform())) {
            createContactsPageObject.clickOnButtonAddPhone();
        }
        createContactsPageObject.clickOnButtonSelectPhoneTypeAndSelectAType(phoneType);
        createContactsPageObject.fillFieldPhone(scenarioName, phone);
        if (Platform.IOS.equals(pageObjectHelper.getPlatform())) {
            createContactsPageObject.clickOnButtonAddEmail();
            createContactsPageObject.clickOnButtonSelectEmailTypeAndSelectAType(emailType);
            createContactsPageObject.fillFieldEmail(scenarioName, email);
        }
        if (Platform.ANDROID.equals(pageObjectHelper.getPlatform())) {
            createContactsPageObject.fillFieldEmail(scenarioName, email);
            createContactsPageObject.clickOnButtonSelectEmailTypeAndSelectAType(emailType);
        }
        if (Platform.IOS.equals(pageObjectHelper.getPlatform())) {
            createContactsPageObject.clickOnButtonAddAddress();
        }
        if (Platform.ANDROID.equals(pageObjectHelper.getPlatform())) {
            createContactsPageObject.clickOnButtonMoreFields();
        }
        createContactsPageObject.clickOnButtonSelectAddressTypeAndSelectAType(addressType);
        createContactsPageObject.fillFieldAddress(scenarioName, address, city, state, postalCode);
    }

    @When("I click on Save Contact button")
    public void iClickOnSaveContactButton() {
        createContactsPageObject.clickOnButtonSaveContact();
    }

    @Then("I should see the new contact added successfully")
    public void iShouldSeeTheNewContactAddedSuccessfully() {
        contactPageObject.checkLabelFullNameContactIsDisplayed(fullName);
    }

    /**
     * Remove a contact successfully
     */
    @Before(value = "@DeleteContact")
    public void addANewContactFullProcess() throws IOException {
        if (Platform.ANDROID.equals(pageObjectHelper.getPlatform())) {
            homeContactsPageObject.checkButtonSkipBackUpOptionIsVisibleAndClick(scenarioName);
        }
        homeContactsPageObject.checkSearchBarIsVisible(scenarioName);
        homeContactsPageObject.clickOnAddContactButton();
        createContactsPageObject.checkFieldFirstNameIsVisible(scenarioName);
        createContactsPageObject.fillFieldFirstName(scenarioName, firstName);
        createContactsPageObject.fillFieldLastName(scenarioName, lastName);
        createContactsPageObject.fillFieldCompany(scenarioName, company);
        if (Platform.IOS.equals(pageObjectHelper.getPlatform())) {
            createContactsPageObject.clickOnButtonAddPhone();
        }
        createContactsPageObject.clickOnButtonSelectPhoneTypeAndSelectAType("Mobile");
        createContactsPageObject.fillFieldPhone(scenarioName, phone);
        if (Platform.IOS.equals(pageObjectHelper.getPlatform())) {
            createContactsPageObject.clickOnButtonAddEmail();
            createContactsPageObject.clickOnButtonSelectEmailTypeAndSelectAType("Other");
            createContactsPageObject.fillFieldEmail(scenarioName, email);
        }
        if (Platform.ANDROID.equals(pageObjectHelper.getPlatform())) {
            createContactsPageObject.fillFieldEmail(scenarioName, email);
            createContactsPageObject.clickOnButtonSelectEmailTypeAndSelectAType("Other");
        }
        if (Platform.IOS.equals(pageObjectHelper.getPlatform())) {
            createContactsPageObject.clickOnButtonAddAddress();
        }
        if (Platform.ANDROID.equals(pageObjectHelper.getPlatform())) {
            createContactsPageObject.clickOnButtonMoreFields();
        }
        createContactsPageObject.clickOnButtonSelectAddressTypeAndSelectAType("Other");
        createContactsPageObject.fillFieldAddress(scenarioName, address, city, state, postalCode);
        createContactsPageObject.clickOnButtonSaveContact();
        contactPageObject.checkLabelFullNameContactIsDisplayed(fullName);
    }

    @Given("that I have a previously created contact")
    public void thatIHaveAPreviouslyCreatedContact() {
        contactPageObject.clickOnButtonBackContacts();
        homeContactsPageObject.checkThatContactIsInTheList(fullName);
    }

    @And("I select this contact")
    public void iSelectThisContact() {
        homeContactsPageObject.clickOnContactIsInTheList(fullName, email);
    }

    @And("I click on Edit Contact button")
    public void iClickOnEditContactButton() {
        contactPageObject.checkLabelFullNameContactIsDisplayed(fullName);
        contactPageObject.clickOnButtonEditContact();
    }

    @When("I click on Delete Contact button")
    public void iClickOnDeleteContactButton() {
        contactPageObject.clickOnButtonDeleteContact();
    }

    @And("I confirm the deletion of that contact")
    public void iConfirmTheDeletionOfThatContact() {
        contactPageObject.clickOnButtonConfirmDeleteContact();
    }

    @Then("I should see the confirmation of deletion of the contact")
    public void iShouldSeeTheConfirmationOfDeletionOfTheContact() {
        if (Platform.IOS.equals(pageObjectHelper.getPlatform())) {
            homeContactsPageObject.checkThatContactIsntInTheList(fullName);
        } else {
            homeContactsPageObject.checkToastContactDeletedIsVisible();
        }
    }

}
