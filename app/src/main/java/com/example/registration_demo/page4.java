package com.example.registration_demo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class page4 extends AppCompatActivity {

    String worklink, smprofile, portfoliolink, myskills, languages;
    EditText wlink, sm, portfolio, skill, lang;
    TextView photoCopyFileName, cvCopyFileName;
    Uri passportCopyUri, adharCopyUri, panCardCopyUri, dlCopyUri, voterCopyUri;
    private Map<Integer, Uri> selectedFileUris = new HashMap<>();
    private static final int REQUEST_CODE_PHOTO_COPY = 6;
    private static final int REQUEST_CODE_CV_COPY = 7;
    String adharNumber, firstName, middleName, lastName, contactNumber, altContactNumber, email,
            dateOfBirth, age, passportRadio, drivingLicenseRadio, voterIdRadio, nationality, gender,
            blood, maritalStatus, currentAddress, permanentAddress, currentLocation,
            preferredLocation, passportNumber, panNumber;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);

        photoCopyFileName = findViewById(R.id.photoCopyFileName);
        cvCopyFileName = findViewById(R.id.cvCopyFileName);
        wlink = findViewById(R.id.p4wl);
        sm = findViewById(R.id.p4sm);
        portfolio = findViewById(R.id.p4po);
        skill = findViewById(R.id.p4sk);
        lang = findViewById(R.id.p4lang);

        Intent intent = getIntent();

        if (intent != null) {
            // Extract file URIs
            passportCopyUri = intent.getParcelableExtra("passportCopyUri");
            adharCopyUri = intent.getParcelableExtra("adharCopyUri");
            panCardCopyUri = intent.getParcelableExtra("panCardCopyUri");
            dlCopyUri = intent.getParcelableExtra("dlCopyUri");
            voterCopyUri = intent.getParcelableExtra("voterCopyUri");

            // Extract other data
            adharNumber = intent.getStringExtra("ADHAR_NUMBER");
            firstName = intent.getStringExtra("FIRST_NAME");
            middleName = intent.getStringExtra("MIDDLE_NAME");
            lastName = intent.getStringExtra("LAST_NAME");
            contactNumber = intent.getStringExtra("CONTACT_NUMBER");
            altContactNumber = intent.getStringExtra("ALT_CONTACT_NUMBER");
            email = intent.getStringExtra("EMAIL");
            dateOfBirth = intent.getStringExtra("DATE_OF_BIRTH");
            age = intent.getStringExtra("AGE");
            nationality = intent.getStringExtra("NATIONALITY");
            gender = intent.getStringExtra("GENDER");
            blood = intent.getStringExtra("BLOOD");
            maritalStatus = intent.getStringExtra("MARITAL_STATUS");
            currentAddress = intent.getStringExtra("CURRENT_ADDRESS");
            permanentAddress = intent.getStringExtra("PERMANENT_ADDRESS");
            currentLocation = intent.getStringExtra("CURRENT_LOCATION");
            preferredLocation = intent.getStringExtra("PREFERRED_LOCATION");
            passportNumber = intent.getStringExtra("PASSPORT_NUMBER");
            panNumber = intent.getStringExtra("PAN_NUMBER");
            passportRadio = intent.getStringExtra("PASSPORT");
            drivingLicenseRadio = intent.getStringExtra("DRIVING_LICENCE");
            voterIdRadio = intent.getStringExtra("VOTER_ID");
            // Use the extracted data as needed
            // For example, you can set them in TextViews or use them in your logic
        }


    }

    public void page4back(View view) {
        Intent intent = new Intent(page4.this, page3.class);
        startActivity(intent);
    }

    public void page4next(View view) {
        if (isEmpty(wlink) || isEmpty(sm) || isEmpty(portfolio) || isEmpty(skill) || isEmpty(lang)) {
            Toast.makeText(this, "Please fill out all required fields.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (isTEmpty(photoCopyFileName) || isTEmpty(cvCopyFileName)){
            Toast.makeText(this, "Please fill out all required fields.", Toast.LENGTH_SHORT).show();
            return;
        }


        worklink = wlink.getText().toString();
        smprofile = sm.getText().toString();
        portfoliolink = portfolio.getText().toString();
        myskills = skill.getText().toString();
        languages = lang.getText().toString();


        Intent intent = new Intent(page4.this, page5.class);

        // Add the extracted data to the new intent
        intent.putExtra("passportCopyUri", passportCopyUri);
        intent.putExtra("adharCopyUri", adharCopyUri);
        intent.putExtra("panCardCopyUri", panCardCopyUri);
        intent.putExtra("dlCopyUri", dlCopyUri);
        intent.putExtra("voterCopyUri", voterCopyUri);
        intent.putExtra("ADHAR_NUMBER", adharNumber);
        intent.putExtra("FIRST_NAME", firstName);
        intent.putExtra("MIDDLE_NAME", middleName);
        intent.putExtra("LAST_NAME", lastName);
        intent.putExtra("CONTACT_NUMBER", contactNumber);
        intent.putExtra("ALT_CONTACT_NUMBER", altContactNumber);
        intent.putExtra("EMAIL", email);
        intent.putExtra("DATE_OF_BIRTH", dateOfBirth);
        intent.putExtra("AGE", age);
        intent.putExtra("NATIONALITY", nationality);
        intent.putExtra("GENDER", gender);
        intent.putExtra("BLOOD", blood);
        intent.putExtra("MARITAL_STATUS", maritalStatus);
        intent.putExtra("CURRENT_ADDRESS", currentAddress);
        intent.putExtra("PERMANENT_ADDRESS", permanentAddress);
        intent.putExtra("CURRENT_LOCATION", currentLocation);
        intent.putExtra("PREFERRED_LOCATION", preferredLocation);
        intent.putExtra("PASSPORT_NUMBER", passportNumber);
        intent.putExtra("PAN_NUMBER", panNumber);
        intent.putExtra("PASSPORT", passportRadio);
        intent.putExtra("DRIVING_LICENCE", drivingLicenseRadio);
        intent.putExtra("VOTER_ID", voterIdRadio);
        intent.putExtra("WORK_LINK", worklink);
        intent.putExtra("SOCIAL_MEDIA_PROFILE", smprofile);
        intent.putExtra("PORTFOLIO", portfoliolink);
        intent.putExtra("SKILLS", myskills);
        intent.putExtra("LANGUAGES", languages);
        intent.putExtra("photoCopyUri", selectedFileUris.get(REQUEST_CODE_PHOTO_COPY));
        intent.putExtra("cvCopyUri", selectedFileUris.get(REQUEST_CODE_CV_COPY));
        startActivity(intent);

    }

    public void page4clear(View view) {
        wlink.setText("");
        sm.setText("");
        portfolio.setText("");
        skill.setText("");
        lang.setText("");

        worklink = "";
        smprofile = "";
        portfoliolink = "";
        myskills = "";
        languages = "";

        Intent intent = new Intent(page4.this, MainActivity.class);
        startActivity(intent);

    }


    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }
    private boolean isTEmpty(TextView textView) {
        return textView.getText().toString().trim().length() == 0;
    }

    public void choosePhotoCopy(View view) {
        startFilePicker(REQUEST_CODE_PHOTO_COPY);

    }

    public void chooseCvCopy(View view) {
        startFilePicker(REQUEST_CODE_CV_COPY);
    }

    private void startFilePicker(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // Allow any file type to be selected
        startActivityForResult(intent, requestCode);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedFileUri = data.getData();
            selectedFileUris.put(requestCode, selectedFileUri);

            // Display the selected file name in the corresponding TextView
            String fileName = getFileName(selectedFileUri);
            switch (requestCode) {
                case REQUEST_CODE_PHOTO_COPY:
                    photoCopyFileName.setText(fileName);
                    break;
                case REQUEST_CODE_CV_COPY:
                    cvCopyFileName.setText(fileName);
                    break;
            }
        }
    }
    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (nameIndex >= 0) {
                        result = cursor.getString(nameIndex);
                    }
                }
            }
        }

        return result;
    }
}

