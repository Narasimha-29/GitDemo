
@tag
Feature: Purchase Order from the Eccomerce Website
I want to use this template for my feature file

Background:
Given I landed on Ecommerce Page


@tag
Scenario Outline: Positive test of submitting the Order
Given Logged in with username <name> and password <password>
When I add product <ProductName> to Cart 
And Checkout <ProductName> and submit the order
Then "THANKYOU FOR THE ORDER." message should display from ConfirmationPage


Examples:
|name               | password     | ProductName |
|y19ec1367@gmail.com| Narasimha@123| ZARA COAT 3 |


 