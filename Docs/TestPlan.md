# Test Plan

**Author**: Team 102

## 1 Testing Strategy

### 1.1 Overall strategy

Our team will be using Unit tests as much as possible to test the functionality of tasks.  Since the application is small, Unit test should provide adeuquate coverage in most cases.  Individuals building out new functionality will be responsible for writing test for said functionality.  Integration and Systems tests will be made on a team level and be implemented based on discussions had as a team.  Before the end of the week, regression testing will be completed to ensure that the application is functioning within the boundary of tests before being submitted.

### 1.2 Test Selection

We will start the project by taking a systematic functional-testing approach.  This will allow us to create our test and consider our inputs before we start implementing functionality.  When writing tests, we will consider unique valid inputs to test cases intelligently for distint scenarios.  To accomplish this, we will take a partition testing approach for creating test cases that may cause issues.  This supports our process of Test Driven Development that we plan on taking to complete the project.

### 1.3 Adequacy Criterion

Test Driven Development will be the practice followed for developing the application.  Before working on a feature, it will be the developers responsibility to create test cases to handle all cases that may occur when the user is using the application.  If any member of a team identifies a bug while using the application, they should immediately notify the team via slack and create a test that replicates the issue.

### 1.4 Bug Tracking

Bugs and enhancements will be tracked using the Github Issues tool integrated in our Github repository. Upon insertion of an issue, any of our team members will be able to assign the issue to himself and work on updating the project.

### 1.5 Technology

JUnit will be the framework used for Unit Testing.  Espresso will be used for Integration and System testing as it is the recommended tool in Android's documentation. 

## 2 Test Cases

Purpose | Steps | Expected Result | Actual Result | P/F
--- | --- | --- | --- | ---
Main menu works correctly | Open the application and click on each option | User is lead to the correct page | When clicking on each page of the Main Menu, the correct page is opened | Pass
Cancel works correctly | Click on cancel in current job, add job offer, and adjust settings | User is returned to the main menu and nothing should be saved or updated | Cancel on each Activity page returns you back to the Main Menu as expected | Pass
Return to main menu works correctly | Click on return to main menu after saving a job offer or comparing two jobs | User is returned to the main menu | When clicking on "Return to Main Menu" in the modal after adding a job offer, the user is returned to the Main Menu | Pass
Save works correctly | Fill values and save for current job, job offer, and settings | User is returned to the main menu and data is stored in the database | When Pressing Save on the Current Job Offer, Add Job Offer, and Comparison Settings Activities, the data is saved to the database and is used to rank offers, and to display all opportunities when comparing | Pass
Save not allowed when input is missing | Try pressing the save button without inputting any values on any form | Error message on the first input box that has the missing value | When clicking on save button, the first missing input box has an alert notifying user to input values | Pass
Save not allowed when input is invalid on Settings | Try pressing the save button with invalid input values on settings page | Error message on the input box that has the invalid value | When clicking on save button, the input box with the invalid value has an alert notifying user that input value is invalid | Pass
Compare is disabled when there are less than 2 jobs | Try pressing the compare button with 0 or 1 job saved | User is unable to see the comparison page | When Job Table does not contain at least two jobs in its table via Add Job Offers or job offers and current job, the Compare button in the main menu is disabled. | Pass
Compare is enabled when there are 2 jobs saved | Try saving current job and one job offer or try saving two job offers and press the compare button in the main menu | User is able to select the option to compare jobs | When 2 or more jobs are added by Job Offer or one job is added with job offer and current job, the Compare button is enabled to allow for users to compare jobs. | Pass
Previous values are displayed after being saved | Fill and save current job or adjust settings and select option again | User is returned to the main menu and is able to see the values set on the previous save when entering the option again | When Current Job is added, the values will be displayed when returning to the page.  When Comparison Settings are modified, the same settings will reappear on the page. | Pass
Entering another job offer works after saving job offer | Fill and save job offer and select add another job offer | User is returned to a fresh add job offer screen | After clicking add another offer, the fields on the page are returned blank allowing for the user to input another offer. | Pass
Compare saved job offer to current job works correctly | Set and save current job, add and save job offer, and click on compare to current job option | User is shown a table that compares the saved job offer and current job | Current job offer is set and saved. When adding a job offer, the option to compare the job offer with the current job appears and directs the user directly to the comparison table with both offers displaying | Pass
Compare saved job offer to current job is disabled when there is no current job | Fill and save job offer and try clicking on compare to current job option | Compare button is disabled and user is unable to compare | When no current job is set, there is no option to compare it to the current job in the modal.  Only the option to add another offer or return to main menu exist | Pass
Compare works correctly | Add two jobs, click on compare option, select the two jobs, and click on compare button | User is shown a table that compares the two jobs selected | The user selects two jobs from page via a drop down menu.  When both jobs are selected, the compare button is clicked and the table displaying both jobs is displayed for comparison.  | Pass
Table order works correctly without adjusting settings | Add two jobs with different values and click on compare button | Table is in the correct order based on the job score calculated with the default weight values | When clicking on compare, the table displays the job offers in descending order based on the exisiting comparison settings of 1 in each category. | Pass
Table order works correctly after adjusting settings | Add two jobs with different values, adjust and save settings, and click on compare button | Table is in the correct order based on the job score calculated with the updated weight values | After the comparison settings are adjusted for a job, the table adjusts the order in which jobs are displayed according to the new settings correctly. | Pass
Able to make multiple comparisons | Add multiple jobs and try to make different comparisons | User can compare different set of jobs after making one and view the correct table | With multiple job offers added, the user can compare between all of the different job offers saved. No errors displayed when testing with a variety of Job Offers and Current Job. | Pass
Confirm that the "Compare to Current" button in the pop-up dialog (Job Offer Entry) is not visible if no current job is entered into database | Increment the database version by 1 to create an empty JobTable. Enter Job Offer Information and click "Save". | A pop-up dialog should appear with only 2 buttons: "Enter Another" and "Return to Main". | A pop-up dialog appears with only 2 buttons: "Enter Another" and "Return to Main" | Pass
