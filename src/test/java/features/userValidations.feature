#Author: vinod.duddukuri@oracle.com

Feature: Validating User API's

	@AddUser @Regression
  Scenario Outline: Verify if User is being successfully added using AddUserAPI
    Given Add User Payload with "<firstname>" "<lastname>" "<subjectId>"
    When user calls "AddUserAPI" with "POST" http request
    Then the API call got success with status code 201
    And verify user id created maps to "<firstname>" using "GetUserAPI"

Examples:
   |firstname|lastname|subjectId|
   |munirathnam|singiri|3|
 #  |kumuda|srikantiah|4|
 
 @DeleteUser @Regression
 Scenario: Verify if Delete User functionality is working
    Given Delete User Payload
    When user calls "DeleteUserAPI" with "DELETE" http request
    Then the API call get success with status code 200
   