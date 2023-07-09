#language:en

  @Regressive
  Feature: : Contacts Management

    @Smoke @AddContact
    Scenario Outline: Add new contact successfully
      Given that I am on the Contacts homepage
      And I click on Add Contact button
      And I fill all the fields "<Phone type>" "<Email type>" "<Address type>"
      When I click on Save Contact button
      Then I should see the new contact added successfully

      Examples:
        | Phone type | Email type | Address type |
        | Mobile     | Other      | Other        |

    @Smoke @DeleteContact
    Scenario: Remove a contact successfully
      Given that I have a previously created contact
      And I select this contact
      And I click on Edit Contact button
      When I click on Delete Contact button
      And I confirm the deletion of that contact
      Then I should see the confirmation of deletion of the contact