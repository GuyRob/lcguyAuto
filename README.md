# lcguyAuto

• Video that present the test run and the test report: https://www.youtube.com/watch?v=SNVeKzUOor8

• AWS Environment using S3 Static website: "templatemo_591_villa_agency" with adjustments for form submission.
  URL: http://lcguy.s3-website.eu-north-1.amazonaws.com/index.html

• Java Selenium framework with TestNG and Allure Report.

• Tests: 
 * P1_Homepage - testing: basic page load, banner navigation, youtube link and channel name, select best deal by category, open google map and compare with site location
 * P2_Navigation - Navigation between pages, selecting an item and compare if the correct item selected (The test is fail as expected, because different product is selected)
 * P3_Form - Filling the form section on homepage and contact us page, validating email format, and navigation to submitted page. 

Tests executaion for running all tests is from file testng.xml

• Tests Report: 
 * allure-results folder - contains all the logs with pass/fail results, and the screenshots.
 * allure-report folder - contains html and css to display the results
 * generateAllureReport.sh - script to generate the report and run local host web

• CI/CD Pipeline: .github\workflows\ci-cd-pipeline.yml with a setup for the project
