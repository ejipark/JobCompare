package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {

    // Constant variables for database
    private static final String DATABASE_NAME = "JobCompare";    // Database Name
    private static final String JOB_TABLE_NAME = "JobTable";   // Job Table Name
    private static final String SETTING_TABLE_NAME = "SettingTable";   // Setting Table Name
    private static final int DATABASE_Version = 4;    // DB Version

    // Job Table Columns
    private static final String JOB_ID = "_JobId";     // Primary Key (_JobId)
    private static final String TITLE = "Title";
    private static final String COMPANY = "Company";
    private static final String CITY = "City";
    private static final String STATE = "State";
    private static final String COL = "CostOfLiving";
    private static final String YEAR_SALARY = "YearSalary";
    private static final String YEAR_BONUS = "YearBonus";
    private static final String RETIREMENT = "Retirement";
    private static final String RELOCATION = "Relocation";
    private static final String STOCK_AWARD = "StockAward";
    private static final String CURRENT_JOB = "CurrentJob";

    // Setting Table Columns
    private static final String SALARY_WEIGHT = "SalaryWeight";
    private static final String BONUS_WEIGHT = "BonusWeight";
    private static final String RETIREMENT_WEIGHT = "RetirementWeight";
    private static final String RELOCATION_WEIGHT = "RelocationWeight";
    private static final String STOCK_WEIGHT = "StockWeight";

    // creating constructor for database handler.
    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
    }

    // Method for creating database
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            // SQL Query to create job table w/ Columns & Data Types
            String jobTableCreate = "CREATE TABLE " + JOB_TABLE_NAME + " ("
                    + JOB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + TITLE + " VARCHAR(255),"
                    + COMPANY + " VARCHAR(225),"
                    + CITY + " VARCHAR(255),"
                    + STATE + " VARCHAR(225),"
                    + COL + " FLOAT,"
                    + YEAR_SALARY + " FLOAT,"
                    + YEAR_BONUS + " FLOAT,"
                    + RETIREMENT + " FLOAT,"
                    + RELOCATION + " FLOAT,"
                    + STOCK_AWARD + " INTEGER,"
                    + CURRENT_JOB + " INTEGER)";

            // SQL Query to create setting table w/ Columns & Data Types
            String settingTableCreate = "CREATE TABLE " + SETTING_TABLE_NAME + " ("
                    + SALARY_WEIGHT + " VARCHAR(1),"
                    + BONUS_WEIGHT + " VARCHAR(1),"
                    + RETIREMENT_WEIGHT + " VARCHAR(1),"
                    + RELOCATION_WEIGHT + " VARCHAR(1),"
                    + STOCK_WEIGHT + " VARCHAR(1))";

            // insert initial record to the setting table
            String settingTableInsert = "INSERT INTO " + SETTING_TABLE_NAME + " VALUES ('1','1','1','1','1')";

            // Exec SQL method from above to create tables
            db.execSQL(jobTableCreate);
            db.execSQL(settingTableCreate);
            db.execSQL(settingTableInsert);
        } catch (Exception ex) {
            System.out.println("SQLHelper-OnCreate() exception: " + ex.getMessage());
        }
    }

    public long getJobsCount() {
        long count = 0L;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            count = DatabaseUtils.queryNumEntries(db, JOB_TABLE_NAME);
            db.close();
            return count;
        } catch (Exception ex) {
            System.out.println("SQLHelper-getJobsCount() exception: " + ex.getMessage());
        }
        return count;
    }

    public long insertJob(Integer jobID, String title, String company, String city, String state, Float costLiving,
                          Float salary, Float bonus, Float retirement, Float relocation,
                          Integer stock, Integer current) {

        long id = 0L;
        try {
            // create variable for sql lite db and call writable method
            SQLiteDatabase db = this.getWritableDatabase();

            // variable for content values
            ContentValues contentVal = new ContentValues();

            // pass values with column key and value
            contentVal.put(TITLE, title);
            contentVal.put(COMPANY, company);
            contentVal.put(CITY, city);
            contentVal.put(STATE, state);
            contentVal.put(COL, costLiving);
            contentVal.put(YEAR_SALARY, salary);
            contentVal.put(YEAR_BONUS, bonus);
            contentVal.put(RETIREMENT, retirement);
            contentVal.put(RELOCATION, relocation);
            contentVal.put(STOCK_AWARD, stock);
            contentVal.put(CURRENT_JOB, current);

            // if jobID is present, perform db update, otherwise insert new record
            if (jobID > 0) {
                id = db.update(JOB_TABLE_NAME, contentVal, "_JobId=?", new String[]{jobID.toString()});
            } else {
                id = db.insert(JOB_TABLE_NAME, null, contentVal);
            }
            return id;

        } catch (Exception ex) {
            System.out.println("SQLHelper-insertJob() exception: " + ex.getMessage());
            return id;
        }
    }

    public Cursor getJob(Integer current) {
        SQLiteDatabase db = this.getWritableDatabase();
        String currentJob = String.valueOf(current);

        //Execute query and store result in cursor to iterate over
        //Below query can be modified with SQL commands: Group By, Order, Where..etc
        Cursor cur = db.rawQuery("SELECT * FROM " + JOB_TABLE_NAME + " WHERE " + CURRENT_JOB + " ='" + currentJob + "'", null);
        return cur;
    }

    public Cursor getSettingData() {
        // create variable for sql lite db and call writable method
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + SETTING_TABLE_NAME, null);
        return res;
    }

    public Boolean updateSetting(String salary, String bonus, String retire, String relocate, String stock) {
        // create variable for sql lite db and call writable method
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentVal = new ContentValues();

        // pass values with column key and value
        contentVal.put(SALARY_WEIGHT, salary);
        contentVal.put(BONUS_WEIGHT, bonus);
        contentVal.put(RETIREMENT_WEIGHT, retire);
        contentVal.put(RELOCATION_WEIGHT, relocate);
        contentVal.put(STOCK_WEIGHT, stock);

        long result = db.update(SETTING_TABLE_NAME, contentVal, null, null);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<String> getAllJobsForSpinner() {
        List<String> gigs = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + JOB_ID + "," + COMPANY + "," + TITLE + "," + CURRENT_JOB +
                " FROM " + JOB_TABLE_NAME + " ORDER BY " + COMPANY + " ASC", null);
        if (cursor.moveToFirst()) {
            do {
                String current;
                Integer current_pre = cursor.getInt(3);
                if (current_pre == 1) {
                    current = " *";
                } else {
                    current = "";
                }

                String option = "[ID" + cursor.getString(0) + "] " + cursor.getString(1) + " - " + cursor.getString(2) + current;
                gigs.add(option);
            } while (cursor.moveToNext());
        }
        return gigs;
    }

    public Cursor getAllJobsByScore(float saw, float bow, float rtw, float rlw, float stw) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + COMPANY + "," + TITLE + "," + CURRENT_JOB + "," +
                "((" + saw + "*(" + YEAR_SALARY + "*100/" + COL + "))+(" +
                bow + "*(" + YEAR_BONUS + "*100/" + COL + "))+(" +
                rtw + "*" + YEAR_SALARY + "*" + RETIREMENT + "/100)+(" + rlw + "*" + RELOCATION + ")+(" +
                stw + "*" + STOCK_AWARD + "/4)) as score" +
                " FROM " + JOB_TABLE_NAME +
                " ORDER BY score DESC", null);

        return res;
    }

    public Cursor getJobData(int jid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + TITLE + "," + COMPANY + "," + CITY + "," + STATE + ",(" +
                YEAR_SALARY + "*100/" + COL + "),(" +
                YEAR_BONUS + "*100/" + COL + ")," + RETIREMENT + "," + RELOCATION + "," + STOCK_AWARD +
                " FROM " + JOB_TABLE_NAME +
                " WHERE " + JOB_ID + "==" + jid, null);
        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // call method to check if table exists
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + JOB_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + SETTING_TABLE_NAME);
            onCreate(db);
        }
    }
}

