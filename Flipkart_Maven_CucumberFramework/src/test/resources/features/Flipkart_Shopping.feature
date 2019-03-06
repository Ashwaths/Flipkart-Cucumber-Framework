Feature: Flipkart online shopping
   
   Scenario: Verify the user is able to purchase Ethnic Wear on Flipkart website
    Given the user navigates to Flipkart page
    When the user selects Ethnic Wear from women's section redirects to product page
    And the user selects product filters from the page
	Then the user selects one of the ethnic wear from the filtered list
	And the user takes screenshot of the selected item    

   