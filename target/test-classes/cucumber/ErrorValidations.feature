	@tag1
	Feature: Error Validation
		I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page

  @ErrorValidation
  Scenario Outline: Negative Test of Login Application
    Given Logged in with UserName <userName> and Password <userPassword>
    Then "Incorrect email or password." message is displayed on the Login Page

    Examples: 
      | userName  				 													| userPassword 				|
      | satisraja@gmail.com 							  	| Raja1234    	 					|