package com.example.conor.trace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by conor on 26/11/2015.
 */
public class Settings extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_settings);
    }

    public void email()
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "trace@tracer.com" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        intent.putExtra(Intent.EXTRA_TEXT, "mail body");
        startActivity(Intent.createChooser(intent, ""));
    }

        public void launchAboutUs(View view)
        {
            System.out.println("fuck");
            Intent intent = new Intent(this, AboutUs.class);
            System.out.println("fuck");
            startActivity(intent);
        }

        public void launchAboutTrace(View view)
        {
            Intent intent2 = new Intent(this, AboutTrace.class);
            startActivity(intent2);
        }

        public void launchLogin(View view)
        {
            Intent intent3 = new Intent(this , Login.class);
            startActivity(intent3);
        }
        public void launchHelp(View view)
        {
            Intent intent = new Intent(this, Help.class);
            startActivity(intent);
        }
}
