package com.example.registration_demo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class page6 extends AppCompatActivity {
    RadioGroup backradio, drugradio, casesradio, aknowradio;
    EditText crimdet;
    private FirebaseStorage storage;
    private DatabaseReference database;
    String adharNumber, firstName, middleName, lastName, contactNumber, altContactNumber, email,
            dateOfBirth, age, passportRadio, drivingLicenseRadio, voterIdRadio, nationality, gender,
            blood, maritalStatus, currentAddress, permanentAddress, currentLocation,
            preferredLocation, passportNumber, panNumber,worklink, smprofile, portfoliolink, myskills,
            languages,my_course, my_Specialization, institution, edcomplition, Percentage, Certification,
            Certifications_Obtained, Authority, certi_Completion, join_date, fresher;

    Uri passportCopyUri, adharCopyUri, panCardCopyUri, dlCopyUri, voterCopyUri, photoCopyUri, cvCopyUri, educomCopyUri, certiproofCopyUri;
    private Map<Integer, Uri> selectedFileUris = new HashMap<>();
    String crimdetailsValue,backgroundValue,drugtestValue,criminalcasesValue,acknowledgeValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        setContentView(R.layout.activity_page6);

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance().getReference("users");

        // Retrieve the intent values


        crimdet = findViewById(R.id.p6crimdet);
        backradio = findViewById(R.id.p6groupbackradio);
        drugradio = findViewById(R.id.p6groupdrugradio);
        casesradio = findViewById(R.id.p6groupcrimradio);
        aknowradio = findViewById(R.id.p6groupaknowradio);


    }

    public void page6back(View view) {
        Intent intent = new Intent(page6.this, page5.class);
        startActivity(intent);
    }

    public void page6submit(View view) {

        if (isEmpty(crimdet)) {
            Toast.makeText(this, "Please fill out all required fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (backradio.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a gender.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (drugradio.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a gender.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (casesradio.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a gender.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (aknowradio.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a gender.", Toast.LENGTH_SHORT).show();
            return;
        }

        crimdetailsValue = crimdet.getText().toString();
        backgroundValue = getSelectedRadioButtonText(backradio);
        drugtestValue = getSelectedRadioButtonText(drugradio);
        criminalcasesValue= getSelectedRadioButtonText(casesradio);
        acknowledgeValue = getSelectedRadioButtonText(aknowradio);

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
            educomCopyUri = intent.getParcelableExtra("Educatuin_completion_CopyUri");
            certiproofCopyUri = intent.getParcelableExtra("certification_proof_CopyUri");


            // Upload each file to Firebase Storage and insert data into Firebase Realtime Database
            uploadAndInsertData(passportCopyUri, "passportCopy", intent);
            uploadAndInsertData(adharCopyUri, "adharCopy", intent);
            uploadAndInsertData(panCardCopyUri, "panCardCopy", intent);
            uploadAndInsertData(dlCopyUri, "dlCopy", intent);
            uploadAndInsertData(voterCopyUri, "voterCopy", intent);
            uploadAndInsertData(photoCopyUri, "Photo", intent);
            uploadAndInsertData(cvCopyUri, "cvCopy", intent);
            uploadAndInsertData(educomCopyUri, "Education_complition_Copy", intent);
            uploadAndInsertData(certiproofCopyUri, "Certificate_proof_Copy", intent);

        }

        if (intent != null) {
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
            edcomplition = intent.getStringExtra("EDUCATION_COMPLITION_DATE");
            my_course = intent.getStringExtra("COURSE");
            my_Specialization = intent.getStringExtra("SPECIALIZATION");
            Percentage = intent.getStringExtra("PERCENTAGE");
            Certification = intent.getStringExtra("CERTIFICATE");
            Certifications_Obtained = intent.getStringExtra("CERTIFICATION_OBTAINED");
            Authority = intent.getStringExtra("AUTHORITY");
            certi_Completion = intent.getStringExtra("CERTIFICATE_COMPLETION_DATE");
            join_date = intent.getStringExtra("JOIN_DATE");


            // Insert data into Realtime Database
            insertDataIntoRealtimeDatabase(
                    adharNumber,
                    firstName,
                    middleName,
                    lastName,
                    contactNumber,
                    altContactNumber,
                    email,
                    dateOfBirth,
                    age,
                    nationality,
                    gender,
                    blood,
                    maritalStatus,
                    currentAddress,
                    permanentAddress,
                    currentLocation,
                    preferredLocation,
                    passportNumber,
                    panNumber,
                    passportRadio,
                    drivingLicenseRadio,
                    voterIdRadio,
                    worklink,
                    smprofile,
                    portfoliolink,
                    myskills,
                    languages,
                    edcomplition,
                    join_date,
                    certi_Completion,
                    my_course,
                    my_Specialization,
                    institution,
                    Percentage,
                    Certifications_Obtained,
                    Authority,
                    Certification,
                    fresher,
                    crimdetailsValue,
                    backgroundValue,
                    drugtestValue,
                    criminalcasesValue,
                    acknowledgeValue); // Include new fields


        }
        else {
            Log.e("Page6Activity", "Failed to generate user ID");
        }

        Intent resultintent = new Intent(page6.this, result.class);
        startActivity(resultintent);
    }
    private void insertDataIntoRealtimeDatabase(
            String adharNumber, String firstName,
            String middleName, String lastName, String contactNumber,
            String altContactNumber, String email, String dateOfBirth,
            String age, String nationality, String gender, String blood,
            String maritalStatus, String currentAddress, String permanentAddress,
            String currentLocation, String preferredLocation, String passportNumber,
            String panNumber, String passportRadio, String drivingLicenseRadio,
            String voterIdRadio, String workLink, String socialMediaProfile,
            String portfolioLink, String skills, String languages,
            String educationCompletionDate, String joinDate,
            String certificationCompletionDate, String course,
            String specialization, String institution, String percentage,
            String certificationsObtained, String authority, String certification,
            String fresher, String crimdetailsValue, String backgroundValue,
            String drugtestValue, String criminalcasesValue,
            String acknowledgeValue
    ) {
        // Example code to insert data into Realtime Database
        String userId = database.push().getKey();

        Map<String, Object> userData = new HashMap<>();
        userData.put("AdharNumber", adharNumber);
        userData.put("FirstName", firstName);
        userData.put("MiddleName", middleName);
        userData.put("LastName", lastName);
        userData.put("ContactNumber", contactNumber);
        userData.put("AltContactNumber", altContactNumber);
        userData.put("Email", email);
        userData.put("DateOfBirth", dateOfBirth);
        userData.put("Age", age);
        userData.put("Nationality", nationality);
        userData.put("Gender", gender);
        userData.put("Blood", blood);
        userData.put("MaritalStatus", maritalStatus);
        userData.put("CurrentAddress", currentAddress);
        userData.put("PermanentAddress", permanentAddress);
        userData.put("CurrentLocation", currentLocation);
        userData.put("PreferredLocation", preferredLocation);
        userData.put("PassportNumber", passportNumber);
        userData.put("PanCardNumber", panNumber);
        userData.put("PassportRadio", passportRadio);
        userData.put("DrivingLicenseRadio", drivingLicenseRadio);
        userData.put("VoterIdRadio", voterIdRadio);
        userData.put("WorkLink", workLink);
        userData.put("SocialMediaProfile", socialMediaProfile);
        userData.put("PortfolioLink", portfolioLink);
        userData.put("Skills", skills);
        userData.put("Languages", languages);
        userData.put("EducationCompletionDate", educationCompletionDate);
        userData.put("JoinDate", joinDate);
        userData.put("CertificationCompletionDate", certificationCompletionDate);
        userData.put("Course", course);
        userData.put("Specialization", specialization);
        userData.put("Institution", institution);
        userData.put("Percentage", percentage);
        userData.put("CertificationsObtained", certificationsObtained);
        userData.put("Authority", authority);
        userData.put("CertificationRadio", certification);
        userData.put("Fresher", fresher);
        userData.put("CrimdetailsValue", crimdetailsValue);
        userData.put("BackgroundValue", backgroundValue);
        userData.put("DrugtestValue", drugtestValue);
        userData.put("CriminalcasesValue", criminalcasesValue);
        userData.put("AcknowledgeValue", acknowledgeValue);


        database.child(userId).setValue(userData).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Data inserted successfully
                Log.d("Page6Activity", "Data inserted successfully");
                // Handle success as needed (e.g., update UI, etc.)
            } else {
                // Failed to insert data
                Log.e("Page6Activity", "Failed to insert data", task.getException());
                // Handle failure as needed (e.g., display error message, retry, etc.)
            }
        });}


    private void uploadAndInsertData(Uri fileUri, String fileType, Intent intent) {
        if (fileUri != null) {
            // Upload file to Firebase Storage
            uploadFileToFirebase(fileUri, fileType);
        }
    }

    private void uploadFileToFirebase(Uri fileUri, String fileType) {
        // Example code to upload file to Firebase Storage
        String fileName = getFileNameFromUri(fileUri);
        StorageReference storageRef = storage.getReference().child("uploads/" + fileName);

        UploadTask uploadTask = storageRef.putFile(fileUri);
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            // File uploaded successfully
            Log.d("Page6Activity", "File uploaded: " + fileName);
            // Handle success as needed (e.g., update UI, etc.)
        }).addOnFailureListener(exception -> {
            // Handle unsuccessful uploads
            Log.e("Page6Activity", "Failed to upload file " + fileName, exception);
            // Handle failure as needed (e.g., display error message, retry, etc.)
        });
    }

    @SuppressLint("Range")
    private String getFileNameFromUri(Uri uri) {
        // Example method to get file name from URI
        String fileName = "";
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
            cursor.close();
        }
        return fileName;
    }

    private boolean isEmpty (EditText editText){
        return editText.getText().toString().trim().length() == 0;
    }
    private String getSelectedRadioButtonText (RadioGroup group){
        int selectedId = group.getCheckedRadioButtonId();
        if (selectedId == -1) {
            return null; // No selection
        } else {
            RadioButton selectedRadioButton = findViewById(selectedId);
            return selectedRadioButton.getText().toString();
        }
    }

    public void page6clear(View view) {
        backradio.clearCheck();
        drugradio.clearCheck();
        casesradio.clearCheck();
        aknowradio.clearCheck();
        crimdet.setText("");

        crimdetailsValue = "";
        backgroundValue = "";
        drugtestValue = "";
        criminalcasesValue = "";
        acknowledgeValue = "";

        Intent intent = new Intent(page6.this, MainActivity.class);
        startActivity(intent);
    }
}