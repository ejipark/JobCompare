package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

public class CompareJobsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private SQLHelper sqlHelper;
    private Cursor jobData, settingData;
    private Spinner spinner1, spinner2;
    private TableLayout tableLayout;
    private TextView rank, title, company, current;

    private Button compareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_jobs);

        compareButton = findViewById(R.id.compareButtonID);

        spinner1 = findViewById(R.id.selectJob1);
        spinner2 = findViewById(R.id.selectJob2);
        tableLayout = findViewById(R.id.tableLayout);

        sqlHelper = new SQLHelper(CompareJobsActivity.this);

        // LOAD SPINNER
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);

        List<String> jobs = sqlHelper.getAllJobsForSpinner();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, jobs);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter);

        // GET FRACTION FROM SETTINGS
        settingData = sqlHelper.getSettingData();
        settingData.moveToFirst();

        String salaryW, bonusW, retireW, relocateW, stockW;
        salaryW = settingData.getString(0);
        bonusW = settingData.getString(1);
        retireW = settingData.getString(2);
        relocateW = settingData.getString(3);
        stockW = settingData.getString(4);

        // convert weight string to integer
        Integer salaryWI = Integer.parseInt(salaryW);
        Integer bonusWI = Integer.parseInt(bonusW);
        Integer retireWI = Integer.parseInt(retireW);
        Integer relocateWI = Integer.parseInt(relocateW);
        Integer stockWI = Integer.parseInt(stockW);

        // get sum of all integers
        Integer totalWI = salaryWI + bonusWI + retireWI + relocateWI + stockWI;

        // get the actual weight
        float weightSalary = (float)salaryWI/totalWI;
        float weightBonus = (float)bonusWI/totalWI;
        float weightRetire = (float)retireWI/totalWI;
        float weightRelocate = (float)relocateWI/totalWI;
        float weightStock = (float)stockWI/totalWI;

        // TABLE QUERY
        jobData = sqlHelper.getAllJobsByScore(weightSalary, weightBonus, weightRetire, weightRelocate, weightStock);
        showJobData(jobData);

        compareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String jid1 = spinner1.getSelectedItem().toString();
                String jid2 = spinner2.getSelectedItem().toString();

                Intent intent = new Intent(v.getContext(), FinalTableActivity.class);
                intent.putExtra("item1", jid1);
                intent.putExtra("item2", jid2);
                startActivity(intent);
            }
        });
    }

    public void showJobData(Cursor jobData) {
        jobData.moveToFirst();
        int rk = 1;

        do {
            View tableRow = LayoutInflater.from(this).inflate(R.layout.table_item,null,false);

            rank = tableRow.findViewById(R.id.tableRank);
            title = tableRow.findViewById(R.id.tableTitle);
            company = tableRow.findViewById(R.id.tableCompany);
            current = tableRow.findViewById(R.id.tableCurrent);

            String rk_String = String.valueOf(rk);
            rank.setText(rk_String);
            title.setText(jobData.getString(0));
            company.setText(jobData.getString(1));
            String current_val;
            Integer current_pre = jobData.getInt(2);
            if (current_pre == 1) { current_val = "*";
            } else { current_val = ""; }
            current.setText(current_val);

            tableLayout.addView(tableRow);
            rk++;
        } while (jobData.moveToNext());
    }

    public void goMainMenu(View view) {
        if (view.getId() == R.id.cancelButtonID) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}