package com.example.registration_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText fname,mname,lname,contactno,altcontactno,email,age,nationality;
    TextView dob;
    RadioGroup groupgradio,groupmradio,groupbradio;
    String firstname,middlename,lastname,contno,altcontno,e_mail,dateofbirth,my_age,my_nationality,gender,maritalStatus,blood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fname = findViewById(R.id.fname);
        mname = findViewById(R.id.mname);
        lname = findViewById(R.id.lname);
        contactno = findViewById(R.id.cno);
        altcontactno = findViewById(R.id.acno);
        email = findViewById(R.id.email);
        dob = findViewById(R.id.dob);
        age = findViewById(R.id.age);
        groupgradio = findViewById(R.id.groupgradio);
        groupmradio = findViewById(R.id.groupmradio);
        nationality = findViewById(R.id.nc);
        groupbradio = findViewById(R.id.groupbradio);


    }
    public void datepic(View view) {
        Date d=new Date();
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dp=new DatePickerDialog(MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        dob.setText(i2+"/"+(i1+1)+"/"+i);
                    }
                },mYear,mMonth,mDay);
        dp.show();
    }

    public void next(View view) {

        if (isEmpty(fname) || isEmpty(lname) || isEmpty(contactno) || isEmpty(altcontactno) || isEmpty(email) || isEmpty(age) || isEmpty(nationality)) {
            Toast.makeText(this, "Please fill out all required fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isTEmpty(dob)){
            Toast.makeText(this, "Please fill out all required fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate RadioGroups
        if (groupgradio.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a gender.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (groupmradio.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a marital status.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (groupbradio.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a marital status.", Toast.LENGTH_SHORT).show();
            return;
        }

        // If validation passes, proceed to the next screen
        Toast.makeText(this, "All fields are valid! Navigating to the next screen.", Toast.LENGTH_SHORT).show();
        firstname = fname.getText().toString();
        middlename = mname.getText().toString();
        lastname = lname.getText().toString();
        contno = contactno.getText().toString();
        altcontno = altcontactno.getText().toString();
        e_mail = email.getText().toString();
        dateofbirth = dob.getText().toString();
        my_age = age.getText().toString();
        my_nationality = nationality.getText().toString();
        gender = getSelectedRadioButtonText(groupgradio);
        maritalStatus = getSelectedRadioButtonText(groupmradio);
        blood = getSelectedRadioButtonText(groupbradio);



        Intent intent = new Intent(MainActivity.this, page2.class);
        intent.putExtra("FIRST_NAME", firstname);
        intent.putExtra("MIDDLE_NAME", middlename);
        intent.putExtra("LAST_NAME", lastname);
        intent.putExtra("CONTACT_NUMBER", contno);
        intent.putExtra("ALT_CONTACT_NUMBER", altcontno);
        intent.putExtra("EMAIL", e_mail);
        intent.putExtra("DATE_OF_BIRTH", dateofbirth);
        intent.putExtra("AGE", my_age);
        intent.putExtra("NATIONALITY", my_nationality);
        intent.putExtra("GENDER", gender);
        intent.putExtra("BLOOD", blood);
        intent.putExtra("MARITAL_STATUS", maritalStatus);
        startActivity(intent);
    }

    // Helper method to check if an EditText field is empty
    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }
    private boolean isTEmpty(TextView dob) {

        return dob.getText().toString().trim().length() == 0;
    }
    private String getSelectedRadioButtonText(RadioGroup group) {
        int selectedId = group.getCheckedRadioButtonId();
        if (selectedId == -1) {
            return null; // No selection
        } else {
            RadioButton selectedRadioButton = findViewById(selectedId);
            return selectedRadioButton.getText().toString();
        }
    }

    public void clear(View view) {
        // Clear all EditText fields
        fname.setText("");
        mname.setText("");
        lname.setText("");
        contactno.setText("");
        altcontactno.setText("");
        email.setText("");
        dob.setText("");
        age.setText("");
        nationality.setText("");

        // Clear RadioGroups
        groupgradio.clearCheck();
        groupmradio.clearCheck();
        groupbradio.clearCheck();
        // Clear stored variables
        firstname = "";
        middlename = "";
        lastname = "";
        contno = "";
        altcontno = "";
        e_mail = "";
        dateofbirth = "";
        my_age = "";
        my_nationality = "";
        gender = "";
        maritalStatus = "";
        blood = "";

    }
}