package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private SQLHelper sqlHelper;

    private Button compareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compareButton = findViewById(R.id.compareJobsButtonID);

        sqlHelper = new SQLHelper(MainActivity.this);

        long jc = sqlHelper.getJobsCount();
        int jobCount = (int) jc;
        if (jobCount < 2) {
            compareButton.setClickable(false);
            compareButton.setAlpha(.5f);
        } else {
            compareButton.setClickable(true);
        }
    }

    public void goPage(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.setCurrentJobButtonID:
                intent = new Intent(this, SetCurrentJobActivity.class);
                startActivity(intent);
                break;
            case R.id.addJobOfferButtonID:
                intent = new Intent(this, AddJobOfferActivity.class);
                startActivity(intent);
                break;
            case R.id.settingsButtonID:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.compareJobsButtonID:
                intent = new Intent(this, CompareJobsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
