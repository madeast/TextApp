package edu.ctec.textapp;

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

public class TextActivity extends AppCompatActivity
{
    private Button textingButton;
    private EditText nuberText;
    private TextView textingView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        textingButton = (Button) findViewById(R.id.textingButton);
        textingView = (TextView) findViewById(R.id.textingView);
        nuberText = (EditText) findViewById(R.id.nuberText);

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
                    String phoneNumber = nuberText.getText().toString();
                    String message = textingView.getText().toString();
                    SmsManager.getDefault().sendTextMessage(phoneNumber, null, "Would you like to play a game?", null, null);

                    Toast.makeText(currentView.getContext(), "message was sent", Toast.LENGTH_LONG).show();
                }
                catch (Exception currentException)
                {
                    Toast.makeText(currentView.getContext(), "message was not sent", Toast.LENGTH_LONG).show();
                    Toast.makeText(currentView.getContext(), currentException.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void sendSMS(String phoneNumber, String messageContent)
    {
        SmsManager mySMSManager = SmsManager.getDefault();
        mySMSManager.sendTextMessage(phoneNumber, null, messageContent, null, null);
    }

}




