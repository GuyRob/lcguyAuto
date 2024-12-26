# lcguyAuto

• Video that present the test run and the test report: https://www.youtube.com/watch?v=SNVeKzUOor8

• AWS Environment using S3 Static website: "templatemo_591_villa_agency" with adjustments for form submission.

  AWS URL: http://lcguy.s3-website.eu-north-1.amazonaws.com/index.html


• Java Selenium framework with TestNG and Allure Report.

• Tests: 10 Passes and 1 Fail
 * P1_Homepage - testing: basic page load, banner navigation, YouTube link and channel name, select the best deal by category, open Google map and compare with site location
 * P2_Navigation - Navigation between pages, selecting an item and comparing if the correct item was selected (The test failed as expected because a different product was selected)
 * P3_Form - Filling the form section on the homepage and contact us page, validate the email format, and navigate to the submitted page. 

Tests execution for running all tests is from file: testng.xml

• Tests Report: 
 * allure-results folder - contains all the logs with pass/fail results and the screenshots.
 * allure-report folder - contains HTML and CSS to display the results
 * generateAllureReport.sh - script to generate the report and run on local host web

• CI/CD Pipeline: .github\workflows\ci-cd-pipeline.yml with a setup for the project
