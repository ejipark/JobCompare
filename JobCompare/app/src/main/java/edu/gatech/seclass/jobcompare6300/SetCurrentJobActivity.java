package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class SetCurrentJobActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private SQLHelper sqlHelper;
    private Cursor jobCursor;

    // create variables for inputs in activity
    private TextInputEditText currentTitle, currentCompany, currentCity, currentState, currentIndex;
    private TextInputEditText currentSalary, currentBonus, currentRetirement, currentRelocation, currentStock;
    private Button saveButton;

    private static final DecimalFormat decFormat = new DecimalFormat("0.00");

    Integer jobID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_set_current_job);

            //initialize variables
            currentTitle = findViewById(R.id.currentTitle);
            currentCompany = findViewById(R.id.currentCompany);
            currentCity = findViewById(R.id.currentCity);
            currentState = findViewById(R.id.currentState);
            currentIndex = findViewById(R.id.currentIndex);
            currentSalary = findViewById(R.id.currentSalary);
            currentBonus = findViewById(R.id.currentBonus);
            currentRetirement = findViewById(R.id.currentRetirement);
            currentRelocation = findViewById(R.id.currentRelocation);
            currentStock = findViewById(R.id.currentStock);

            //set OnFocusChangeListener on needed variables
            currentTitle.setOnFocusChangeListener(this);
            currentCompany.setOnFocusChangeListener(this);
            currentCity.setOnFocusChangeListener(this);
            currentState.setOnFocusChangeListener(this);
            currentIndex.setOnFocusChangeListener(this);
            currentSalary.setOnFocusChangeListener(this);
            currentBonus.setOnFocusChangeListener(this);
            currentRetirement.setOnFocusChangeListener(this);
            currentRelocation.setOnFocusChangeListener(this);
            currentStock.setOnFocusChangeListener(this);

            //buttons
            saveButton = findViewById(R.id.saveButtonID);

            //instance of SQLHelper created
            sqlHelper = new SQLHelper(SetCurrentJobActivity.this);
            jobCursor = sqlHelper.getJob(1);

            // load current job from db if it exists
            if (jobCursor.moveToFirst()) {
                //Get JobID for Current Job
                String jbIDStr = jobCursor.getString(0);
                jobID = Integer.valueOf(jbIDStr);

                String curTitle = jobCursor.getString(1);
                String curCompany = jobCursor.getString(2);
                String curCity = jobCursor.getString(3);
                String curState = jobCursor.getString(4);
                String curIndex = jobCursor.getString(5);
                String curSalary = jobCursor.getString(6);
                String curBonus = jobCursor.getString(7);
                String curRetire = jobCursor.getString(8);
                String curReloc = jobCursor.getString(9);
                String curStock = jobCursor.getString(10);

                //Convert String to Double
                double currSalary = Double.parseDouble(curSalary);
                double currBonus = Double.parseDouble(curBonus);
                double currRetire = Double.parseDouble(curRetire);
                double currReloc = Double.parseDouble(curReloc);

                //Set values
                currentTitle.setText(curTitle);
                currentCompany.setText(curCompany);
                currentCity.setText(curCity);
                currentState.setText(curState);
                currentIndex.setText(curIndex);
                currentSalary.setText(decFormat.format(currSalary));
                currentBonus.setText(decFormat.format(currBonus));
                currentRetirement.setText(decFormat.format(currRetire));
                currentRelocation.setText(decFormat.format(currReloc));
                currentStock.setText(curStock);
            }

            // below line is to add on click listener to save button
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // get data from all edit text fields.
                    String title = currentTitle.getText().toString().trim();
                    String company = currentCompany.getText().toString().trim();
                    String city = currentCity.getText().toString().trim();
                    String state = currentState.getText().toString().trim();
                    String index = currentIndex.getText().toString().trim();
                    String salary = currentSalary.getText().toString().trim();
                    String bonus = currentBonus.getText().toString().trim();
                    String retirement = currentRetirement.getText().toString().trim();
                    String relocation = currentRelocation.getText().toString().trim();
                    String stock = currentStock.getText().toString().trim();

                    // validate if fields are empty or not.
                    TextInputEditText[] textFields = {currentTitle, currentCompany, currentCity, currentState, currentIndex, currentSalary, currentBonus,
                            currentRetirement, currentRelocation, currentStock};
                    for (TextInputEditText input : textFields) {
                        if (input.getText().toString().trim().isEmpty()) {
                            input.setError("Please Enter a Value");
                            return;
                        }
                    }

                    //convert String to float or Integer
                    float colIndex = Float.parseFloat(index);
                    float yrSalary = Float.parseFloat(salary);
                    float yrBonus = Float.parseFloat(bonus);
                    float retire = Float.parseFloat(retirement);
                    float reloc = Float.parseFloat(relocation);
                    Integer stockAwd = Integer.valueOf(stock);

                    Long id = sqlHelper.insertJob(jobID, title, company, city, state, colIndex,
                            yrSalary, yrBonus, retire, reloc, stockAwd, 1);

                    if (id == -1) {
                        Toast.makeText(SetCurrentJobActivity.this, "Current job NOT saved!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SetCurrentJobActivity.this, "Current job saved!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            });
        } catch (Exception ex) {
            Toast.makeText(SetCurrentJobActivity.this, "Error SetCurrentJobActivity - onCreate: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasfocus) {
        try {
            if (!hasfocus) {
                switch (view.getId()) {
                    case R.id.currentTitle:
                        if (currentTitle.getText().toString().trim().isEmpty()) {
                            currentTitle.setError("Please Enter Title");
                        }

                        break;
                    case R.id.currentCompany:
                        if (currentCompany.getText().toString().trim().isEmpty()) {
                            currentCompany.setError("Please Enter Company");
                        }
                        break;
                    case R.id.currentCity:
                        if (currentCity.getText().toString().trim().isEmpty()) {
                            currentCity.setError("Please Enter City");
                        }
                        break;
                    case R.id.currentState:
                        if (currentState.getText().toString().trim().isEmpty()) {
                            currentState.setError("Please Enter State");
                        }
                        break;
                    case R.id.currentIndex:
                        if (currentIndex.getText().toString().trim().isEmpty()) {
                            currentIndex.setError("Please Enter Index");
                        }
                        break;

                    case R.id.currentSalary:
                        if (currentSalary.getText().toString().trim().isEmpty())
                            currentSalary.setError("Please Enter Salary");
                        else {
                            //Convert String to Double
                            double currSalary = Double.parseDouble(currentSalary.getText().toString());

                            //Round salary to 2 places to the right of the decimal (Currency)
                            currentSalary.setText(decFormat.format(currSalary));
                        }
                        break;
                    case R.id.currentBonus:
                        if (currentBonus.getText().toString().trim().isEmpty())
                            currentBonus.setError("Please Enter Bonus");
                        else {
                            //Convert String to Double
                            double currBonus = Double.parseDouble(currentBonus.getText().toString());

                            //Round to 2 places to the right of the decimal (Currency)
                            currentBonus.setText(decFormat.format(currBonus));
                        }
                        break;
                    case R.id.currentRetirement:
                        if (currentRetirement.getText().toString().trim().isEmpty()) {
                            currentRetirement.setError("Please Enter Retirement Benefits");
                        }
                        break;
                    case R.id.currentRelocation:
                        if (currentRelocation.getText().toString().trim().isEmpty())
                            currentRelocation.setError("Please Enter Relocation Stipend");
                        else {
                            //Convert String to Double
                            double currReloc = Double.parseDouble(currentRelocation.getText().toString());

                            //Round to 2 places to the right of the decimal (Currency)
                            currentRelocation.setText(decFormat.format(currReloc));
                        }
                        break;
                    case R.id.currentStock:
                        if (currentStock.getText().toString().trim().isEmpty())
                            currentStock.setError("Please Enter Stock Award");
                        break;
                }
            }
        } catch (Exception ex) {
            Toast.makeText(SetCurrentJobActivity.this, "Error SetCurrentJobActivity - onFocusChange: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void goMainMenu(View view) {
        if (view.getId() == R.id.cancelButtonID) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

}
