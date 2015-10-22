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

public class TextActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        findViewById(R.id.textingButton).setOnClickListener(this);
    }

    public void onClick(View v)
    {
        String phoneNumber = ((EditText)
                findViewById(R.id.nuberText)).getText().toString();
        try
        {
            SmsManager.getDefault().sendTextMessage(phoneNumber, null, "Hello SMS!", null, null);
        }
        catch (Exception e)
        {
            AlertDialog.Builder alertDialogBuilder = new

                    AlertDialog.Builder(this);
            AlertDialog dialog = alertDialogBuilder.create();

            dialog.setMessage(e.getMessage());

            dialog.show();
        }
    }
}


