package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class FinalTableActivity extends AppCompatActivity {

    private SQLHelper sqlHelper;
    private Cursor job1Data, job2Data;
    private TextView j1Title, j1Company, j1City, j1State, j1Salary, j1Bonus, j1Retire, j1Relocate, j1RSU;
    private TextView j2Title, j2Company, j2City, j2State, j2Salary, j2Bonus, j2Retire, j2Relocate, j2RSU;

    private static final DecimalFormat decFormat = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_table);

        String selectedItemIndex1 = getIntent().getExtras().getString("item1",null);
        String selectedItemIndex2 = getIntent().getExtras().getString("item2",null);

        int sepPos1 = selectedItemIndex1.indexOf("]");
        int sepPos2 = selectedItemIndex2.indexOf("]");

        int jid1, jid2;
        if (selectedItemIndex1.matches("^\\[ID(\\d+)\\]\\s(.*)")) {
            jid1 = Integer.parseInt(selectedItemIndex1.substring(3, sepPos1));
            jid2 = Integer.parseInt(selectedItemIndex2.substring(3, sepPos2));
        } else {
            jid1 = Integer.parseInt(selectedItemIndex1);
            jid2 = Integer.parseInt(selectedItemIndex2);
        }

        sqlHelper = new SQLHelper(FinalTableActivity.this);

        // FIRST JOB
        job1Data = sqlHelper.getJobData(jid1);

        j1Title = findViewById(R.id.j1Title);
        j1Company = findViewById(R.id.j1Company);
        j1City = findViewById(R.id.j1City);
        j1State = findViewById(R.id.j1State);
        j1Salary = findViewById(R.id.j1Salary);
        j1Bonus = findViewById(R.id.j1Bonus);
        j1Retire = findViewById(R.id.j1Retire);
        j1Relocate = findViewById(R.id.j1Relocate);
        j1RSU = findViewById(R.id.j1RSU);

        job1Data.moveToFirst();
        if (job1Data.moveToFirst()) {
            String j1_title, j1_company, j1_city, j1_state, j1_salary, j1_bonus, j1_retire, j1_relocate, j1_rsu;
            j1_title = job1Data.getString(0);
            j1_company = job1Data.getString(1);
            j1_city = job1Data.getString(2);
            j1_state = job1Data.getString(3);
            j1_salary = job1Data.getString(4);
            j1_bonus = job1Data.getString(5);
            j1_retire = job1Data.getString(6);
            j1_relocate = job1Data.getString(7);
            j1_rsu = job1Data.getString(8);

            //Convert String to Double
            double j1_salary_tmp = Double.parseDouble(j1_salary);
            double j1_bonus_tmp = Double.parseDouble(j1_bonus);
            double j1_retire_tmp = Double.parseDouble(j1_retire);
            double j1_relocate_tmp = Double.parseDouble(j1_relocate);

            // display the values on table
            j1Title.setText(j1_title);
            j1Company.setText(j1_company);
            j1City.setText(j1_city);
            j1State.setText(j1_state);
            j1Salary.setText(decFormat.format(j1_salary_tmp));
            j1Bonus.setText(decFormat.format(j1_bonus_tmp));
            j1Retire.setText(decFormat.format(j1_retire_tmp));
            j1Relocate.setText(decFormat.format(j1_relocate_tmp));
            j1RSU.setText(j1_rsu);
        }
        job1Data.close();

        // SECOND JOB
        job2Data = sqlHelper.getJobData(jid2);

        j2Title = findViewById(R.id.j2Title);
        j2Company = findViewById(R.id.j2Company);
        j2City = findViewById(R.id.j2City);
        j2State = findViewById(R.id.j2State);
        j2Salary = findViewById(R.id.j2Salary);
        j2Bonus = findViewById(R.id.j2Bonus);
        j2Retire = findViewById(R.id.j2Retire);
        j2Relocate = findViewById(R.id.j2Relocate);
        j2RSU = findViewById(R.id.j2RSU);

        job2Data.moveToFirst();
        if (job2Data.moveToFirst()) {
            String j2_title, j2_company, j2_city, j2_state, j2_salary, j2_bonus, j2_retire, j2_relocate, j2_rsu;
            j2_title = job2Data.getString(0);
            j2_company = job2Data.getString(1);
            j2_city = job2Data.getString(2);
            j2_state = job2Data.getString(3);
            j2_salary = job2Data.getString(4);
            j2_bonus = job2Data.getString(5);
            j2_retire = job2Data.getString(6);
            j2_relocate = job2Data.getString(7);
            j2_rsu = job2Data.getString(8);

            //Convert String to Double
            double j2_salary_tmp = Double.parseDouble(j2_salary);
            double j2_bonus_tmp = Double.parseDouble(j2_bonus);
            double j2_retire_tmp = Double.parseDouble(j2_retire);
            double j2_relocate_tmp = Double.parseDouble(j2_relocate);

            // display the values on table
            j2Title.setText(j2_title);
            j2Company.setText(j2_company);
            j2City.setText(j2_city);
            j2State.setText(j2_state);
            j2Salary.setText(decFormat.format(j2_salary_tmp));
            j2Bonus.setText(decFormat.format(j2_bonus_tmp));
            j2Retire.setText(decFormat.format(j2_retire_tmp));
            j2Relocate.setText(decFormat.format(j2_relocate_tmp));
            j2RSU.setText(j2_rsu);
        }
        job2Data.close();
    }

    public void goMainMenu(View view) {
        if (view.getId() == R.id.mmButtonID) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void goCompare(View view) {
        if (view.getId() == R.id.caButtonID) {
            Intent intent = new Intent(this, CompareJobsActivity.class);
            startActivity(intent);
        }
    }
}