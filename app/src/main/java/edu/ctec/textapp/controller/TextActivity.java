package edu.ctec.textapp.controller;

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
import edu.ctec.textapp.model.AndroidSaveState;
import edu.ctec.textapp.R;


public class TextActivity extends AppCompatActivity
{
    //Declaration section.
    private AndroidSaveState saveState;
    private Button textingButton;
    private EditText numberText;
    private TextView textingView;
    private Spinner smsEdit;
    private byte[] hold;
    private String defaultNumber;
    private Button securityButton;
    private Button customizeButton;
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
        securityButton = (Button) findViewById(R.id.securityButton);
        customizeButton = (Button) findViewById(R.id.customizeButton);
        myLIST = new ArrayList<String>();
        Text = "I forgot to select a message to send.";

        try
        {
            //Allows app to remeber the last number that was used when the app closes.
            InputStream inputString = openFileInput("defaultNumber");
            if(inputString != null)
            {
                InputStreamReader inputStringReader = new InputStreamReader(inputString);
                BufferedReader bufferedStringReader = new BufferedReader(inputStringReader);
                String defaultDigit = "";
                StringBuilder stringBuilder = new StringBuilder();

                //Sets the number to the last used number.
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
        //Prevent errors if the app hasn't been used before.
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
        //Sends the actual text to the specified number.
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

        //Will set the text number to the number that is set to the button.
        securityButton.setOnClickListener(new View.OnClickListener()
        {
                public void onClick(View currentView)
                {
                     numberText.setText("8018082905");
                }

        });
        // Will allow the user to access customization screen.
        customizeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View currentView) {
                numberText.setText("8019493881");
            }
        });


        // When smsEdit text is selected show strings.
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
    //Loads all preset text fields.
    private void loadArrayList()
    {
        myLIST.add("Hey how are you doing?");
        myLIST.add("Hey I need help!");
        myLIST.add("Whats up.");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myLIST);
        smsEdit.setAdapter(spinnerAdapter);
    }
    // finds the phone number and sends the text.
    private void sendSMS(String phoneNumber, String messageContent)
    {
        SmsManager mySMSManager = SmsManager.getDefault();
        mySMSManager.sendTextMessage(phoneNumber, null, messageContent, null, null);
    }
    // Takes the most recent number and rembers it.
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




