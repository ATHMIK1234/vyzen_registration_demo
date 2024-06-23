package com.example.registration_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class page2 extends AppCompatActivity {

    private String firstname, middlename, lastname, contno, altcontno, e_mail, dateofbirth, my_age, my_nationality, gender, blood, maritalStatus;
    String caddress,paddress,clocation,plocation;
    EditText curaddress,peraddress,curloc,perloc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        Intent intent = getIntent();
        firstname = intent.getStringExtra("FIRST_NAME");
        middlename = intent.getStringExtra("MIDDLE_NAME");
        lastname = intent.getStringExtra("LAST_NAME");
        contno = intent.getStringExtra("CONTACT_NUMBER");
        altcontno = intent.getStringExtra("ALT_CONTACT_NUMBER");
        e_mail = intent.getStringExtra("EMAIL");
        dateofbirth = intent.getStringExtra("DATE_OF_BIRTH");
        my_age = intent.getStringExtra("AGE");
        my_nationality = intent.getStringExtra("NATIONALITY");
        gender = intent.getStringExtra("GENDER");
        blood = intent.getStringExtra("BLOOD");
        maritalStatus = intent.getStringExtra("MARITAL_STATUS");

        curaddress = findViewById(R.id.p2cadress);
        peraddress = findViewById(R.id.p2padress);
        curloc = findViewById(R.id.p2clocation);
        perloc = findViewById(R.id.p2plocation);



    }

    public void page2next(View view) {

        if (isEmpty(curaddress) || isEmpty(peraddress) || isEmpty(curloc) || isEmpty(perloc)) {
            Toast.makeText(this, "Please fill out all required fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        caddress = curaddress.getText().toString();
        paddress = peraddress.getText().toString();
        clocation = curloc.getText().toString();
        plocation = perloc.getText().toString();


        Intent intent = new Intent(page2.this, page3.class);
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
        intent.putExtra("CURRENT_ADDRESS", caddress);
        intent.putExtra("PERMANENT_ADDRESS", paddress);
        intent.putExtra("CURRENT_LOCATION", clocation);
        intent.putExtra("PREFERED_LOCATION", plocation);
        startActivity(intent);


    }


    // Helper method to check if an EditText field is empty
    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    public void page2back(View view) {
        Intent intent = new Intent(page2.this, MainActivity.class);
        startActivity(intent);
    }

    public void page2clear(View view) {
        curaddress.setText("");
        peraddress.setText("");
        curloc.setText("");
        perloc.setText("");

        caddress = "";
        paddress = "";
        clocation = "";
        plocation = "";

        Intent intent = new Intent(page2.this, MainActivity.class);
        startActivity(intent);
    }
}