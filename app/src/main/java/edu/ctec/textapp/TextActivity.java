package edu.ctec.textapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Button;
import android.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TextActivity extends AppCompatActivity
{
    private Button textingButton;
    private EditText nuberText;
    private TextView textingView;
    private EditText smsEdit;
    private byte[] hold;
    private int defaultNumber;
    private Button emerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        textingButton = (Button) findViewById(R.id.textingButton);
        textingView = (TextView) findViewById(R.id.textingView);
        nuberText = (EditText) findViewById(R.id.nuberText);
        smsEdit = (EditText) findViewById(R.id.smsEdit);
        emerButton = (Button) findViewById(R.id.emerButton);

        try
        {
            FileInputStream fos = openFileInput("defaultNumber");
            int worked = fos.read(hold,0,9);
            fos.close();
            //defaultNumber =
            Toast.makeText(getApplicationContext(), worked, Toast.LENGTH_SHORT);

        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "First Time opening this App!", Toast.LENGTH_SHORT);
        }

        setupListeners();
    }

    private void setupListeners()
    {
        textingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View currentView) {
                try {
                    String phoneNumber = nuberText.getText().toString();
                    String message = textingView.getText().toString();
                    String Text = smsEdit.getText().toString();
                    SmsManager.getDefault().sendTextMessage(phoneNumber, null, Text, null, null);

                    Toast.makeText(currentView.getContext(), "message was sent", Toast.LENGTH_LONG).show();
                } catch (Exception currentException) {
                    Toast.makeText(currentView.getContext(), "message was not sent", Toast.LENGTH_LONG).show();
                    Toast.makeText(currentView.getContext(), currentException.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        emerButton.setOnClickListener((c));

    }
    private void sendSMS(String phoneNumber, String messageContent)
    {
        SmsManager mySMSManager = SmsManager.getDefault();
        mySMSManager.sendTextMessage(phoneNumber, null, messageContent, null, null);
    }

    private void saveRecentNumber(int number)
    {
        if(convertInt(number))
        {
            try
            {
                FileOutputStream fos = openFileOutput("defaultNumber", Context.MODE_PRIVATE);
                fos.write(String.valueOf(number).getBytes());
                fos.close();

            }
            catch(Exception e)
            {
                Toast.makeText(getApplicationContext(), "Number Was not Saved", Toast.LENGTH_LONG).show();            }

        }

    }

    private boolean convertInt(int number)
    {
        boolean isString = false;
        try
        {
            String.valueOf(number);
            isString = true;
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "Error number Not Converted to string", Toast.LENGTH_SHORT);
        }
        return isString;
    }

}




