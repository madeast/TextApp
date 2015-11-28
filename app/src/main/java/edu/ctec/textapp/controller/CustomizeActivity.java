package edu.ctec.textapp.controller;

/**
 * Created by emad6932 on 11/20/15.
 */

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import android.widget.Spinner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import edu.ctec.textapp.R;
import edu.ctec.textapp.model.AndroidSaveState;

public class CustomizeActivity extends AppCompatActivity
{
    private AndroidSaveState saveState;
    private EditText phoneText;
    private EditText nameText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);
        saveState = (AndroidSaveState) getApplication();
        saveButton = (Button) findViewById(R.id.saveButton);
        phoneText = (EditText) findViewById(R.id.phoneText);
        nameText = (EditText) findViewById(R.id.nameText);

        setupListeners();
    }

    private void setupListeners()
    {
        //Will save numbers to the customizable button to allow personal numbers.
        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View buttonView)
            {
                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }

}
