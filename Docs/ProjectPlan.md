# Project Plan

**Author**: Team 102

## 1 Introduction

*Develop a single-user mobile android app that assists in comparing job offers.*

## 2 Process Description

### **<ins>Enter/Edit Current Job Details</ins>**

**Description:**
The user is presented with a current job interface where they can enter details about their current job. If a current job has already been entered the user will be presented with the details for this job. Once the user is finished entering current job details, they can save the current job or cancel to exit without saving.  Both cases result in the user returning to the main menu.

**Entrance Criteria:**
The user will be shown a form to **enter** or **edit** (if a current job has been previously entered) the following required fields for their **current job**: 
	
	Title
	Company
	Location (City and State)
	Cost of Living Index (based on location)
	Yearly Salary
	Yearly Bonus
	Retirement Benefits (% matched, given as int)
	Relocation Stipend
	Restricted Stock Unit Award (lump sum, vested over 4 yrs)
	
Once the required fields have been filled, the "Save" button allow the user to commit the transaction to the database and be saved for future use. 
A "Cancel" button will always be enabled which will return the user to the main menu without saving the fields to the database.

**Exit Criteria:**
Once the current job details have been entered and the user clicks "Save" the user will be presented with a "Current Job Saved!" alert.  If at any point the user decides to click the "Cancel" button, the transaction will not be commited to the database and the user will be re-directed to the main menu.  
	
### **<ins>Enter Job Offers</ins>**

**Description:**
The user is presented with an interface to enter all of the details of the job offer (same as current job). Once the job offer details have been entered, the user can choose to save or cancel. If the user chooses to save the offer they will be given the option to enter an additional job offer, return to the main menu, or compare the job offer to their current job (if there is a current job saved). If the user chooses to cancel after entering the job offer details, the details will not be saved to the database and the user will be redirected to the main menu.
	
**Entrance Criteria:** 
The user will be shown a form to enter the following fields for their **job offer** (same as current job): 
		
	Title
	Company
	Location (City and State)
	Cost of Living Index (based on location)
	Yearly Salary
	Yearly Bonus
	Retirement Benefits (% matched, given as int)
	Relocation Stipend
	Restricted Stock Unit Award (lump sum, vested over 4 yrs)
		
Once the required fields have been entered the "Save" button will be enabled (same as job offer) and the user will be able to commit the transaction to the database. Clicking the save button will clear out the entered fields enabling the user to enter another job offer.  Clicking the save button will also enable a "Return to Main" button, that returns the user to the main menu.  A "Compare Offer" button will be enabled once "Save" has been clicked if the user has previously entered a current job in the database. The "Cancel" button will always be enabled which will return the user to the main menu without saving the fields to the database (same action as for current job).

**Exit Criteria:**
Upon entering the required job offer fields the user will click the "Save" button and be presented with a "Save Successful" alert and the "Return to Main" and "Compare Offer" (if there is a current job) will be enabled. The "Cancel" button will not require a user alert, it will simply return the user to the main menu. Clicking the "Compare Offer" will redirect the user to a new form with a table comparing the offer to the current job.
 	
### **<ins>Edit Comparison Settings</ins>**

**Description:**
The main menu will have a "Edit Comparison Settings" button which upon clicking will redirect the user to an interface where they can edit the default integer weights that are assigned to the fields of the job offer and current job. The user will have the option to "Save" from this interface as long as each of the 5 fields has an integer and is not null. The user will also have the option to "Cancel" from this screen reverting the comparison settings back to their stored values.

**Entrance Criteria**
The user will be presented with a comparison settings form to enter values for the following fields (default values set to 1):

	Yearly Salary
	Yearly Bonus
	Retirement Benefits
	Relocation Stipend
	Restricted Stock Unit Award

The user will be required to enter integer values for all of the fields for the "Save" button to be enabled. The "Cancel" button will always be enabled, allowing the user to cancel the transaction and the comparison settings will remain set to their saved state.

**Exit Criteria:**
Upon entering values for all 5 of the comparison fields and clicking the "Save" button, the user will receive a "Settings Updated!" alert and automatically redirected back to the main menu. Clicking the cancel button will not generate an alert, but will simply redirect the user back to the main menu and keep the comparison settings in their prior state. 

### **<ins>Compare Jobs</ins>**

**Description:**
The main menu will have a "Compare Jobs" button enabled if there are at least 2 jobs in the database. Upon clicking the "Compare Jobs" button the user will be redirected to a table listing the currently entered jobs (offers and current), ranked from best to worst based on a weighted sum formula (using the job details and comparison weights). The table will have a selection column, allowing the user to select two jobs to compare. Selecting two jobs to compare will enable a "Compare Details" button that will redirect the user to a detailed comparison table. The user can then choose to click a "New Comparison" button and go back to the previous menu to select another two jobs or click on "Return to Main" to return the user to the main menu.

**Entrance Criteria**
The initial interface for the "Compare Jobs" will display two drop down menus pertaining to the current job and job offers save.  Additionally, a table that contains:
	
	Rank (desc order)
	Company Name
	Job Title
	Current Job (boolean)

At this menu the user will select two jobs to compare, making their selection using the "Select to Compare" column. Once two jobs are selected the "Compare Details" button will be enabled. The user can click the "Compare Details" button and will be taken to a new interface displaying a table with the following fields:
	
	Job Title
	Company
	Location
	Yearly salary adjusted for cost of living
	Yearly bonus adjusted for cost of living
	Retirement benefits
	Relocation stipend
	Restricted stock unit award

In this interface and in the previous interface (w/ the Job Rankings) the user will have the option to click "Return to Main", returning the user to the main menu. In the above "Compare Details" interface the user can also click a "New Comparison" button returning the user to the prior menu where they can make a new selection of jobs to compare.

**Exit Criteria:**
Clicking the "Compare Jobs" button from the main menu will redirect the user to a "Compare Jobs" table that shows the user a table with jobs ordered by ranking and allows the user to select two jobs to view comparison details. The user will also notice that the "Compare Details" button will be enabled once they select two jobs from the table.  Once the user clicks the "Compare Details" button they will be redirected to a more detailed page, listing the "Compare Detail" fields above. The activity is completed at this time, unless the user decides to make a new comparison. The new comparison can be triggered by clicking the "New Comparison" button as mentioned in the entrance criteria section.

## 3 Team

### <ins>Team Members</ins>

	Johnathan Baldera
	Eric Park
	Matt Long

### <ins>Team Roles</ins>

Role | Description
--- | ---
Project Manager | Responsible for managing the overall project. Ensure deliverables are delivered on time and the project meets requirements.
Developer | Design and code the application and perform unit testing to ensure system passes functional requirements.
Architect | Design the high-level architecture of the system, including UML structural and behavioral diagrams.
Tester | Perform integration, system, and regression testing and ensure system satisfies business requirements.
	
### <ins>Role Assignment</ins>

Name | Roles
--- | ---
Johnathan Baldera | Project Manager, Tester
Eric Park | Developer, Tester
Matt Long | Developer, Architect
