package edu.ctec.textapp.model;

import android.app.Application;

/**
 * Created by emad6932 on 11/20/15.
 */
public class AndroidSaveState extends Application
{
    //Will act as the go between the main screen and the actual texting app.
    private String personalNumber;
    private String personalName;

    public AndroidSaveState()
    {
        this.personalNumber = ""

    }

}
