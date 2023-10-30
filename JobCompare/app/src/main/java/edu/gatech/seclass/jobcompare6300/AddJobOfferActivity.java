package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class AddJobOfferActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private SQLHelper sqlHelper;
    private Cursor currentJob;
    private Cursor offerJob;

    private TextInputEditText offerTitle, offerCompany, offerCity, offerState, offerIndex;
    private TextInputEditText offerSalary, offerBonus, offerRetirement, offerRelocation, offerStock;
    private Button saveButton;

    private static final DecimalFormat decFormat = new DecimalFormat("0.00");

    Integer jobID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job_offer);

        offerTitle = (TextInputEditText) findViewById(R.id.offerTitle);
        offerCompany = (TextInputEditText) findViewById(R.id.offerCompany);
        offerCity = (TextInputEditText) findViewById(R.id.offerCity);
        offerState = (TextInputEditText) findViewById(R.id.offerState);
        offerIndex = (TextInputEditText) findViewById(R.id.offerIndex);
        offerSalary = (TextInputEditText) findViewById(R.id.offerSalary);
        offerBonus = (TextInputEditText) findViewById(R.id.offerBonus);
        offerRetirement = (TextInputEditText) findViewById(R.id.offerRetirement);
        offerRelocation = (TextInputEditText) findViewById(R.id.offerRelocation);
        offerStock = (TextInputEditText) findViewById(R.id.offerStock);

        //set OnFocusChangeListener on needed variables
        offerTitle.setOnFocusChangeListener(this);
        offerCompany.setOnFocusChangeListener(this);
        offerCity.setOnFocusChangeListener(this);
        offerState.setOnFocusChangeListener(this);
        offerIndex.setOnFocusChangeListener(this);
        offerSalary.setOnFocusChangeListener(this);
        offerBonus.setOnFocusChangeListener(this);
        offerRetirement.setOnFocusChangeListener(this);
        offerRelocation.setOnFocusChangeListener(this);
        offerStock.setOnFocusChangeListener(this);

        //buttons
        saveButton = findViewById(R.id.saveButtonID);

        sqlHelper = new SQLHelper(AddJobOfferActivity.this);

        //Get current job and get last offer
        currentJob = sqlHelper.getJob(1);
        offerJob = sqlHelper.getJob(0);

        //Instantiate dialog box for options
        AlertDialog.Builder builder = new AlertDialog.Builder(AddJobOfferActivity.this, android.R.style.Theme_Dialog);

        //Setup dialog buttons -- only 3 action buttons allowed
        builder.setNegativeButton("Return to Main", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(AddJobOfferActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setPositiveButton("Enter Another", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
                startActivity(getIntent());
            }
        });

        if (currentJob.moveToFirst()) {
            String currJobID = currentJob.getString(0);
            builder.setNeutralButton("Compare to Current", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    //Most Recent Job Offer
                    if (offerJob.moveToLast()) {
                        Intent intent = new Intent(AddJobOfferActivity.this, FinalTableActivity.class);

                        //Get last offer put in db
                        String offerJobID = offerJob.getString(0);

                        //set job id's for table
                        intent.putExtra("item1", currJobID);
                        intent.putExtra("item2", offerJobID);
                        startActivity(intent);
                    }
                }
            });
        }
        ;

        // Create Dialog object
        builder.create();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get data from all edit text fields.
                String title = offerTitle.getText().toString().trim();
                String company = offerCompany.getText().toString().trim();
                String city = offerCity.getText().toString().trim();
                String state = offerState.getText().toString().trim();
                String index = offerIndex.getText().toString().trim();
                String salary = offerSalary.getText().toString().trim();
                String bonus = offerBonus.getText().toString().trim();
                String retirement = offerRetirement.getText().toString().trim();
                String relocation = offerRelocation.getText().toString().trim();
                String stock = offerStock.getText().toString().trim();

                // validate if fields are empty or not.
                TextInputEditText[] textFields = {offerTitle, offerCompany, offerCity, offerState, offerIndex, offerSalary, offerBonus,
                        offerRetirement, offerRelocation, offerStock};
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
                        yrSalary, yrBonus, retire, reloc, stockAwd, 0);

                // after adding data display message.
                if (id == -1) {
                    Toast.makeText(AddJobOfferActivity.this, "Job offer NOT saved!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddJobOfferActivity.this, "Job offer saved!", Toast.LENGTH_SHORT).show();
                    builder.show();
                }
            }
        });
    }
    @Override
    public void onFocusChange(View view, boolean hasfocus) {
        try {
            if (!hasfocus) {
                switch (view.getId()) {
                    case R.id.offerTitle:
                        if (offerTitle.getText().toString().trim().isEmpty()) {
                            offerTitle.setError("Please Enter Title");
                        }
                        break;
                    case R.id.offerCompany:
                        if (offerCompany.getText().toString().trim().isEmpty()) {
                            offerCompany.setError("Please Enter Company");
                        }
                        break;
                    case R.id.offerCity:
                        if (offerCity.getText().toString().trim().isEmpty()) {
                            offerCity.setError("Please Enter City");
                        }
                        break;
                    case R.id.offerState:
                        if (offerState.getText().toString().trim().isEmpty()) {
                            offerState.setError("Please Enter State");
                        }
                        break;
                    case R.id.offerIndex:
                        if (offerIndex.getText().toString().trim().isEmpty()) {
                            offerIndex.setError("Please Enter Index");
                        }
                        break;

                    case R.id.offerSalary:
                        if (offerSalary.getText().toString().trim().isEmpty())
                            offerSalary.setError("Please Enter Salary");
                        else {
                            //Convert String to Double
                            double currSalary = Double.parseDouble(offerSalary.getText().toString());

                            //Round salary to 2 places to the right of the decimal (Currency)
                            offerSalary.setText(decFormat.format(currSalary));
                        }
                        break;
                    case R.id.offerBonus:
                        if (offerBonus.getText().toString().trim().isEmpty())
                            offerBonus.setError("Please Enter Bonus");
                        else {
                            //Convert String to Double
                            double currBonus = Double.parseDouble(offerBonus.getText().toString());

                            //Round to 2 places to the right of the decimal (Currency)
                            offerBonus.setText(decFormat.format(currBonus));
                        }
                        break;
                    case R.id.offerRetirement:
                        if (offerRetirement.getText().toString().trim().isEmpty()) {
                            offerRetirement.setError("Please Enter Retirement Benefits");
                        }
                        break;
                    case R.id.offerRelocation:
                        if (offerRelocation.getText().toString().trim().isEmpty())
                            offerRelocation.setError("Please Enter Relocation Stipend");
                        else {
                            //Convert String to Double
                            double currReloc = Double.parseDouble(offerRelocation.getText().toString());

                            //Round to 2 places to the right of the decimal (Currency)
                            offerRelocation.setText(decFormat.format(currReloc));
                        }
                        break;
                    case R.id.offerStock:
                        if (offerStock.getText().toString().trim().isEmpty())
                            offerStock.setError("Please Enter Stock Award");
                        break;
                }
            }
        } catch (Exception ex) {
            Toast.makeText(AddJobOfferActivity.this, "Error AddJobOfferActivity - onFocusChange: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void goMainMenu(View view) {
        if (view.getId() == R.id.cancelButtonID) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}