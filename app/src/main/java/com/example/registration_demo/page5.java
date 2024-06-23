package com.example.registration_demo;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class page5 extends AppCompatActivity {
    String adharNumber, firstName, middleName, lastName, contactNumber, altContactNumber, email,
            dateOfBirth, age, passportRadio, drivingLicenseRadio, voterIdRadio, nationality, gender,
            blood, maritalStatus, currentAddress, permanentAddress, currentLocation,
            preferredLocation, passportNumber, panNumber,worklink, smprofile, portfoliolink, myskills, languages;

    Uri passportCopyUri, adharCopyUri, panCardCopyUri, dlCopyUri, voterCopyUri, photoCopyUri, cvCopyUri;

    TextView com, join, certicom, textView,textView30;
    EditText course, Specialization, insti, perce, certiobt, autho;
    RadioGroup certigroup, fershergroup;
    private Map<Integer, Uri> selectedFileUris = new HashMap<>();
    private static final int REQUEST_CODE_EDCOM_COPY = 8;
    private static final int REQUEST_CODE_CERTI_COPY = 9;
    String my_course, my_Specialization, institution, edcomplition, Percentage, Certification, Certifications_Obtained, Authority, certi_Completion, join_date, fresher;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page5);

        com = findViewById(R.id.p5com);
        join = findViewById(R.id.p5joindate);
        certicom = findViewById(R.id.p5ceticomdate);
        course = findViewById(R.id.p5course);
        Specialization = findViewById(R.id.p5spe);
        insti = findViewById(R.id.p5insti);
        perce = findViewById(R.id.p5per);
        certiobt = findViewById(R.id.p5certiobt);
        autho = findViewById(R.id.p5certiauthoname);
        certigroup = findViewById(R.id.p5groupcetiradio);
        fershergroup = findViewById(R.id.p5groupfresherradio);
        textView = findViewById(R.id.textView);
        textView30 = findViewById(R.id.textView30);

        Intent intent = getIntent();

        if (intent != null) {
            // Extract file URIs
            passportCopyUri = intent.getParcelableExtra("passportCopyUri");
            adharCopyUri = intent.getParcelableExtra("adharCopyUri");
            panCardCopyUri = intent.getParcelableExtra("panCardCopyUri");
            dlCopyUri = intent.getParcelableExtra("dlCopyUri");
            voterCopyUri = intent.getParcelableExtra("voterCopyUri");
            photoCopyUri = intent.getParcelableExtra("photoCopyUri");
            cvCopyUri = intent.getParcelableExtra("cvCopyUri");

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
            worklink = intent.getStringExtra("WORK_LINK");
            smprofile = intent.getStringExtra("SOCIAL_MEDIA_PROFILE");
            portfoliolink = intent.getStringExtra("PORTFOLIO");
            myskills = intent.getStringExtra("SKILLS");
            languages = intent.getStringExtra("LANGUAGES");
            // Use the extracted data as needed
            // For example, you can set them in TextViews or use them in your logic
        }

    }

    public void edcomdatepic(View view) {
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dp = new DatePickerDialog(page5.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        com.setText(i2 + "/" + (i1 + 1) + "/" + i);
                    }
                }, mYear, mMonth, mDay
        );
        dp.show();
    }

    public void certidatepic(View view) {
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dp = new DatePickerDialog(page5.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        certicom.setText(i2 + "/" + (i1 + 1) + "/" + i);
                    }
                }, mYear, mMonth, mDay
        );
        dp.show();
    }

    public void joindatepic(View view) {
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dp = new DatePickerDialog(page5.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        join.setText(i2 + "/" + (i1 + 1) + "/" + i);
                    }
                }, mYear, mMonth, mDay
        );
        dp.show();
    }

    public void page5next(View view) {
        if (isEmpty(course) || isEmpty(Specialization) || isEmpty(insti) || isEmpty(perce) || isEmpty(
                certiobt) || isEmpty(autho)) {
            Toast.makeText(this, "Please fill out all required fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isTEmpty(com) || isTEmpty(join) || isTEmpty(certicom)) {
            Toast.makeText(this, "Please fill out all required fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate RadioGroups
        if (certigroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a gender.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (fershergroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a gender.", Toast.LENGTH_SHORT).show();
            return;
        }

        edcomplition = com.getText().toString();
        join_date = join.getText().toString();
        certi_Completion = certicom.getText().toString();
        my_course = course.getText().toString();
        my_Specialization = Specialization.getText().toString();
        institution = insti.getText().toString();
        Percentage = perce.getText().toString();
        Certifications_Obtained = certiobt.getText().toString();
        Authority = autho.getText().toString();
        Certification = getSelectedRadioButtonText(certigroup);
        fresher = getSelectedRadioButtonText(fershergroup);


        Intent intent = new Intent(page5.this, page6.class);

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
        intent.putExtra("photoCopyUri", photoCopyUri);
        intent.putExtra("cvCopyUri", cvCopyUri);
        intent.putExtra("EDUCATION_COMPLITION_DATE", edcomplition);
        intent.putExtra("JOIN_DATE", join_date);
        intent.putExtra("CERTIFICATE_COMPLETION_DATE", certi_Completion);
        intent.putExtra("COURSE", my_course);
        intent.putExtra("SPECIALIZATION", my_Specialization);
        intent.putExtra("INSTITUTION", institution);
        intent.putExtra("PERCENTAGE", Percentage);
        intent.putExtra("CERTIFICATE", Certification);
        intent.putExtra("CERTIFICATION_OBTAINED", Certifications_Obtained);
        intent.putExtra("AUTHORITY", Authority);
        intent.putExtra("FRESHER", fresher);
        intent.putExtra("Educatuin_completion_CopyUri", selectedFileUris.get(REQUEST_CODE_EDCOM_COPY));
        intent.putExtra("certification_proof_CopyUri", selectedFileUris.get(REQUEST_CODE_CERTI_COPY));

        startActivity(intent);

    }

    public void page5clear(View view) {
        fershergroup.clearCheck();
        certigroup.clearCheck();
        com.setText("");
        join.setText("");
        certicom.setText("");
        course.setText("");
        Specialization.setText("");
        insti.setText("");
        perce.setText("");
        certiobt.setText("");
        autho.setText("");

        my_course = "";
        my_Specialization = "";
        institution = "";
        edcomplition = "";
        Percentage = "";
        Certification = "";
        Certifications_Obtained = "";
        Authority = "";
        certi_Completion = "";
        join_date = "";
        fresher = "";

        Intent intent = new Intent(page5.this, MainActivity.class);
        startActivity(intent);


    }

    public void page5back(View view) {
        Intent intent = new Intent(page5.this, page4.class);
        startActivity(intent);
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    private boolean isTEmpty(TextView textView) {

        return textView.getText().toString().trim().length() == 0;
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

    public void chooseEdcomCopy(View view) {
        startFilePicker(REQUEST_CODE_EDCOM_COPY);
    }

    public void chooseCertiCopy(View view) {
        startFilePicker(REQUEST_CODE_CERTI_COPY);
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
                case REQUEST_CODE_EDCOM_COPY:
                    textView.setText(fileName);
                    break;
                case REQUEST_CODE_CERTI_COPY:
                    textView30.setText(fileName);
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

