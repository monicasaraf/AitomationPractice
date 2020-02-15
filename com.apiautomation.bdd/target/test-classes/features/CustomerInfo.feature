Feature: Verifying Customer Info Query

Scenario: Verify Custome Info Query Response
Given user is authenticated and generated jwt
When user hits customer info query
Then API response is status code is 200
And Validate Response
