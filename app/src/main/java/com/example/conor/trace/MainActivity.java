package com.example.conor.trace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Free Draw button button */
    public void launchFreeMode(View view)
    {
        Intent intent = new Intent(this, TraceFunction.class);
        startActivity(intent);
    }

    public void launchSettings(View view)
    {
        Intent intent2 = new Intent(this, Settings.class);
        startActivity(intent2);
    }

    public void launchGradedTrace(View view)
    {
        Intent intent3 = new Intent(this , GradedTrace.class);
        startActivity(intent3);
    }
}
