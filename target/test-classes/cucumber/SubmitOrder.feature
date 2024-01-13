	@tag1
	Feature: Purchase the order from Ecommerce	Website
		I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with UserName <userName> and Password <userPassword>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the Order Confirmation Page

    Examples: 
      | userName  				 													| userPassword 				|	productName		|
      | satishraja@gmail.com 								| Raja1234    	 					| zara coat 3				|
      | anshika@gmail.com 										| Iamking@000					|	adidas original		|