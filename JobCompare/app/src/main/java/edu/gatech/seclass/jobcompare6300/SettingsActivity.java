package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private SQLHelper sqlHelper;
    private Cursor res;

    // create variables for inputs in activity
    private TextInputEditText salaryWeight, bonusWeight, retirementWeight, relocationWeight, stockWeight;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        salaryWeight = findViewById(R.id.salaryWeight);
        bonusWeight = findViewById(R.id.bonusWeight);
        retirementWeight = findViewById(R.id.retirementWeight);
        relocationWeight = findViewById(R.id.relocationWeight);
        stockWeight = findViewById(R.id.stockWeight);
        saveButton = findViewById(R.id.saveButtonID);

        sqlHelper = new SQLHelper(SettingsActivity.this);

        res = sqlHelper.getSettingData();
        res.moveToFirst();
        if (res.moveToFirst()) {
            String salaryW, bonusW, retireW, relocateW, stockW;
            salaryW = res.getString(0);
            bonusW = res.getString(1);
            retireW = res.getString(2);
            relocateW = res.getString(3);
            stockW = res.getString(4);

            // display the saved setting on edit text box
            salaryWeight.setText(salaryW);
            bonusWeight.setText(bonusW);
            retirementWeight.setText(retireW);
            relocationWeight.setText(relocateW);
            stockWeight.setText(stockW);
        }

        // below line is to add on click listener for our save setting button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get data from all edit text fields.
                String salary = salaryWeight.getText().toString();
                String bonus = bonusWeight.getText().toString();
                String retire = retirementWeight.getText().toString();
                String relocate = relocationWeight.getText().toString();
                String stock = stockWeight.getText().toString();

                Boolean verifySalary = verifyInput(salary, salaryWeight);
                Boolean verifyBonus = verifyInput(bonus, bonusWeight);
                Boolean verifyRetire = verifyInput(retire, retirementWeight);
                Boolean verifyRelocate = verifyInput(relocate, relocationWeight);
                Boolean verifyStock = verifyInput(stock, stockWeight);

                if (verifySalary && verifyBonus && verifyRetire && verifyRelocate && verifyStock) {
                    Boolean checkUpdateSetting = sqlHelper.updateSetting(salary, bonus, retire, relocate, stock);
                    if (checkUpdateSetting == true) {
                        Toast.makeText(SettingsActivity.this, "Settings updated!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SettingsActivity.this, "System error - Please contact for help!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SettingsActivity.this, "Settings NOT updated! Check your input!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public Boolean verifyInput(String inputText, TextInputEditText inputBox) {
        if (inputText.isEmpty()) {
            inputBox.setError("It's empty!");
            return false;
        } else {
            int conv = Integer.parseInt(inputText);

            List<Integer> weights = Arrays.asList(1, 2, 3, 4, 5);
            if (!weights.contains(conv)) {
                inputBox.setError("Input integer from 1 to 5");
                return false;
            } else {
                return true;
            }
        }
    }

    public void goMainMenu(View view) {
        if (view.getId() == R.id.cancelButtonID) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}