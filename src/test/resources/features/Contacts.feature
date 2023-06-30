#language:en

  @Regressive @Contacts
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
        | Work       | Work       | Other        |