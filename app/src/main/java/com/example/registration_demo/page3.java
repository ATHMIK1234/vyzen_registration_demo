package com.example.registration_demo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class page3 extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST_CODE = 123;
    private String firstname, middlename, lastname, contno, altcontno, e_mail, dateofbirth, my_age,
            my_nationality, gender, blood, maritalStatus;
    private String caddress, paddress, clocation, plocation;
    String passno, adno, pno, passradio, dlradio, voterradio;
    RadioGroup passport, dl, voterid;
    EditText passportno, adharno, panno;
    TextView passportCopyFileName, adharcardCopyFileName, panCardFileName, dlCopyFileName,
            voterCopyFileName;
    private static final int REQUEST_CODE_PASSPORT_COPY = 1;
    private static final int REQUEST_CODE_ADHAR_COPY = 2;
    private static final int REQUEST_CODE_PAN_CARD_COPY = 3;
    private static final int REQUEST_CODE_DL_COPY = 4;
    private static final int REQUEST_CODE_VOTER_COPY = 5;
    private Map<Integer, Uri> selectedFileUris = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);


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
        caddress = intent.getStringExtra("CURRENT_ADDRESS");
        paddress = intent.getStringExtra("PERMANENT_ADDRESS");
        clocation = intent.getStringExtra("CURRENT_LOCATION");
        plocation = intent.getStringExtra("PREFERED_LOCATION");

        passport = findViewById(R.id.p3grouppassradio);
        dl = findViewById(R.id.p3groupdlradio);
        voterid = findViewById(R.id.p3groupvtidradio);
        passportno = findViewById(R.id.p3pass);
        adharno = findViewById(R.id.p3adhno);
        panno = findViewById(R.id.p3panno);
        passportCopyFileName = findViewById(R.id.passportCopyFileName);
        adharcardCopyFileName = findViewById(R.id.adharcardCopyFileName);
        panCardFileName = findViewById(R.id.panCardFileName);
        dlCopyFileName = findViewById(R.id.dlCopyFileName);
        voterCopyFileName = findViewById(R.id.voterCopyFileName);


    }

    public void page3back(View view) {
        Intent intent = new Intent(page3.this, page2.class);
        startActivity(intent);

    }

    public void page3next(View view) {
        if (isEmpty(adharno) || isEmpty(panno)) {
            Toast.makeText(this, "Please fill out all required fields.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (passport.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a gender.", Toast.LENGTH_SHORT).show();
            return;
        }

        passradio = getSelectedRadioButtonText(passport);
        dlradio = getSelectedRadioButtonText(dl);
        voterradio = getSelectedRadioButtonText(voterid);
        passno = passportno.getText().toString();
        adno = adharno.getText().toString();
        pno = panno.getText().toString();


        Intent intent = new Intent(page3.this, page4.class);
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
        intent.putExtra("PASSPORT", passradio);
        intent.putExtra("VOTER_ID", voterradio);
        intent.putExtra("DRIVING_LICENCE", dlradio);
        intent.putExtra("PASSPORT_NUMBER", passno);
        intent.putExtra("ADHAR_NUMBER", adno);
        intent.putExtra("PAN_NUMBER", pno);
        intent.putExtra("passportCopyUri", selectedFileUris.get(REQUEST_CODE_PASSPORT_COPY));
        intent.putExtra("adharCopyUri", selectedFileUris.get(REQUEST_CODE_ADHAR_COPY));
        intent.putExtra("panCardCopyUri", selectedFileUris.get(REQUEST_CODE_PAN_CARD_COPY));
        intent.putExtra("dlCopyUri", selectedFileUris.get(REQUEST_CODE_DL_COPY));
        intent.putExtra("voterCopyUri", selectedFileUris.get(REQUEST_CODE_VOTER_COPY));
        startActivity(intent);


    }

    public void page3clear(View view) {
        passport.clearCheck();
        dl.clearCheck();
        voterid.clearCheck();
        passportno.setText("");
        adharno.setText("");
        panno.setText("");

        passno = "";
        adno = "";
        pno = "";
        passradio = "";
        dlradio = "";
        voterradio = "";

        Intent intent = new Intent(page3.this, MainActivity.class);
        startActivity(intent);

    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
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

    public void choosePassportCopy(View view) {
        startFilePicker(REQUEST_CODE_PASSPORT_COPY);
    }

    public void chooseAdharCopy(View view) {
        startFilePicker(REQUEST_CODE_ADHAR_COPY);
    }

    public void choosePanCardCopy(View view) {
        startFilePicker(REQUEST_CODE_PAN_CARD_COPY);
    }

    public void chooseDlCopy(View view) {
        startFilePicker(REQUEST_CODE_DL_COPY);
    }

    public void chooseVoterCopy(View view) {
        startFilePicker(REQUEST_CODE_VOTER_COPY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedFileUri = data.getData();
            selectedFileUris.put(requestCode, selectedFileUri);

            // Display the selected file name in the corresponding TextView
            String fileName = getFileName(selectedFileUri);
            switch (requestCode) {
                case REQUEST_CODE_PASSPORT_COPY:
                    passportCopyFileName.setText(fileName);
                    break;
                case REQUEST_CODE_ADHAR_COPY:
                    adharcardCopyFileName.setText(fileName);
                    break;
                case REQUEST_CODE_PAN_CARD_COPY:
                    panCardFileName.setText(fileName);
                    break;
                case REQUEST_CODE_DL_COPY:
                    dlCopyFileName.setText(fileName);
                    break;
                case REQUEST_CODE_VOTER_COPY:
                    voterCopyFileName.setText(fileName);
                    break;
            }


        }
    }

    private void startFilePicker(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // Allow any file type to be selected
        startActivityForResult(intent, requestCode);
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
