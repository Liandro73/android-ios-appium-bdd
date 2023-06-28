#language:en

  @Regressive @Contacts
  Feature: : Contacts Management

    @Smoke @AddContact
    Scenario Outline: Add new contact successfully
      Given that I am on the Contacts homepage
      And I click on Add Contact button
      And I fill all the fields "<First name>" "<Last name>" "<Company>" "<Phone>" "<Phone type>" "<Email>" "<Email type>" "<Address>" "<Address type>"
      When I click on Save Contact button
      Then I should see the new contact added successfully

      Examples:
        | First name | Last name | Company  | Phone         | Phone type | Email                   | Email type | Address                  | Address type |
        | Jonh       | Parker Jr | JPJ Bank | +351912345323 | Mobile     | jonh.parker@jpjbank.com | Work       | Avenue N. 301, Apart 120 | Other        |