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
    protected void onCreate(Bundle savedInstanceState)
    {
        System.out.println("1");
        super.onCreate(savedInstanceState);
        System.out.println("2");
        Intent intent = getIntent();
        System.out.println("3");
        setContentView(R.layout.activity_settings);


    }
}
