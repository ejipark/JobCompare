# Team 102 Design Description

**1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).**

The User enters the application and has four methods - setCurrentJob, addJobOffer, editComparisonSettings, and makeComparisons in order to meet the requirements. When first calling the setCurrentJob method, the user will be prompted to enter their details. Afterwards they will be allowed to edit the details of their current job. Job Offers don't have a requirement to edit therefore they can only add offers.

**2. When choosing to enter current job details, a user will: Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of...**

The CurrentJob class inherits from the Jobs class all the details that are required to enter for the job. A user can only have no job or one job so there is a 0 to 1 relationship between the user and the current job. A blank template will be generated for the first time it is used by the user, afterwards, users will be allowed to edit those details.

**3. When choosing to enter job offers, a user will...**

Similar to the current job, the JobOffers class inherits all the details from the Jobs class so that a user can input all of the information for the job offer. One key difference with JobOffers is that the class has a 0 to many relationship with the User as the User can have many job offers available to them. The user will have the ability to click a button to add the job offer returning to the main menu, compare the offer with the current job if a current job is inputted, or add the job offer and input the detials for another.

**4. When adjusting the comparison settings, the user can assign integer weights to: ...**

The user has access to the editComparisonSettings method in order to assign integer weights. By default, the application will have the integer weights set to one.

**5. When choosing to compare job offers, a user will: ...**

The user has access to the makeComparisons class in order to choose between two jobs. In order to have them ranked from best to worst in the list, each Job will have a weightedValue attribute that is calculated with the calculatedValue method in the Jobs class and is executed whenever the user wants to make comparisons. The comoparison is triggered once user selects two jobs for the selectedJobs attribute. There will be options for the user to perform another comparison, clearing the selectedJobs array, or return to the main menu.

**6. When ranking jobs, a job’s score is computed as the weighted sum of...**

The calculatedValue method of the Jobs class creates the weightedValue attribute (job score) based on the formula given. Since the weightedValue attribute will depend on the attributes settings on the ComparisonSettings class, the Jobs class will have a dependency to the ComparisonSettings class as it will not be able to accurately determine the value without the ComparisonSettings attributes. Jobs is also depending on the CostOfLivingIndex utility to provide a proper calculation.
