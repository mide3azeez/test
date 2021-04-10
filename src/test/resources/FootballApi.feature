Feature: As a user i want to be able to make calls to the foot ball API

  Background:
    * header x-rapidapi-key = xRapidApiKey
    * header x-rapidapi-host = xRapidApiHost
    * def teamInformationSchema = {"get":"#string","parameters":{"id":"#string"},"errors":[],"results":'#number',"paging":{"current":'#number',"total":'#number'},"response":[{"team":{"id":'#number',"name":"#string","country":"#string","founded":'#number',"national":'#boolean',"logo":"#string"},"venue":{"id":'#number',"name":"#string","address":"#string","city":"#string","capacity":'#number',"surface":"#string","image":"#string"}}]}

  @Football-Api
  Scenario: Get Team Information by ID validate response schema and response data
    Given url baseURL
    And path 'teams'
    And param id = 33
    When method get
    Then status 200
    And print response
    And match response == '#(^^teamInformationSchema)'
    And match response.response[0].team.id == 33
    And match response.response[0].team.name != ''
    And match response.response[0].team.country != ""
    And match response.response[0].team.founded != ""
    And match response.response[0].team.logo != ""

  @Football-Api
    Scenario: Get Coach by Team ID
      Given url baseURL
      And path 'coachs'
      And param team = 33
      When method get
      Then status 200
      And print response
      And match response.parameters.team == '33'
      And match response.response[0].firstname != ""

