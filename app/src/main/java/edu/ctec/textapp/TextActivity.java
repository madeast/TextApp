package edu.ctec.textapp;

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


public class TextActivity extends AppCompatActivity
{
    private Button textingButton;
    private EditText numberText;
    private TextView textingView;
    private Spinner smsEdit;
    private byte[] hold;
    private String defaultNumber;
    private Button codyButton;
    private Button dadButton;
    private ArrayList<String> myLIST;
    private String Text;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        textingButton = (Button) findViewById(R.id.textingButton);
        textingView = (TextView) findViewById(R.id.textingView);
        numberText = (EditText) findViewById(R.id.nuberText);
        smsEdit = (Spinner) findViewById(R.id.smsEdit);
        codyButton = (Button) findViewById(R.id.codyButton);
        dadButton = (Button) findViewById(R.id.dadButton);
        myLIST = new ArrayList<String>();
        Text = "I forgot to select a message to send.";

        try
        {
            InputStream inputString = openFileInput("defaultNumber");
            if(inputString != null)
            {
                InputStreamReader inputStringReader = new InputStreamReader(inputString);
                BufferedReader bufferedStringReader = new BufferedReader(inputStringReader);
                String defaultDigit = "";
                StringBuilder stringBuilder = new StringBuilder();

                while((defaultDigit = bufferedStringReader.readLine()) != null)
                {
                    stringBuilder.append(defaultDigit);
                }

                inputString.close();
                numberText.setText(stringBuilder.toString());
            }
            //FileInputStream fos = openFileInput("defaultNumber");
            //int worked = fos.read(hold,0,9);
            //fos.close();
            //defaultNumber =
            //Toast.makeText(getApplicationContext(), worked, Toast.LENGTH_SHORT);

        }
        catch(FileNotFoundException e)
        {
            Toast.makeText(getApplicationContext(), "First Time opening this App!", Toast.LENGTH_SHORT);
            Log.e("Exception", "File Not Found" + e.toString());
        }
        catch(IOException e)
        {
            Log.e("Exception", "Cannot Open File" + e.toString());
        }

        loadArrayList();
        setupListeners();
    }

    private void setupListeners()
    {
        textingButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View currentView)
            {
                try
                {
                    phoneNumber = numberText.getText().toString();
                    saveRecentNumber(phoneNumber);
                    String message = textingView.getText().toString();
                    SmsManager.getDefault().sendTextMessage(phoneNumber, null, Text, null, null);

                    Toast.makeText(currentView.getContext(), "message was sent", Toast.LENGTH_LONG).show();
                }
                catch (Exception currentException)
                {
                    Toast.makeText(currentView.getContext(), "message was not sent", Toast.LENGTH_LONG).show();
                    Toast.makeText(currentView.getContext(), currentException.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        codyButton.setOnClickListener(new View.OnClickListener()
        {
                public void onClick(View currentView)
                {
                     numberText.setText("8018082905");
                }

        });

        dadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View currentView) {
                numberText.setText("8019493881");
            }
        });



        smsEdit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Text = smsEdit.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void loadArrayList()
    {
        myLIST.add("Hey how are you doing?");
        myLIST.add("Hey I need help!");
        myLIST.add("Whats up.");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myLIST);
        smsEdit.setAdapter(spinnerAdapter);
    }

    private void sendSMS(String phoneNumber, String messageContent)
    {
        SmsManager mySMSManager = SmsManager.getDefault();
        mySMSManager.sendTextMessage(phoneNumber, null, messageContent, null, null);
    }

    private void saveRecentNumber(String number)
    {
        try
        {
            OutputStreamWriter OSW = new OutputStreamWriter(openFileOutput("defaultNumber", Context.MODE_PRIVATE));
            OSW.write(phoneNumber);
            OSW.close();
        }
        catch(IOException e)
        {
            Log.e("Exception", "File Write failed:" + e.toString());
        }
    }

}




