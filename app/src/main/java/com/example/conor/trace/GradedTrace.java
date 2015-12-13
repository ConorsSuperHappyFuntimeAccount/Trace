package com.example.conor.trace;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import java.util.UUID;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.content.Intent;

/**
 * Created by conor on 29/11/2015.
 */
public class GradedTrace extends AppCompatActivity implements View.OnClickListener
{
    private DrawingView drawView;
    private ImageButton currPaint,newBtn,submitBtn,saveBtn;
    private float  mediumBrush;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_graded);
        drawView = (DrawingView)findViewById(R.id.drawing);
        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_colors);

        currPaint = (ImageButton)paintLayout.getChildAt(0);
        currPaint.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.paint_pressed, null));

        mediumBrush = getResources().getInteger(R.integer.medium_size);

        drawView.setBrushSize(mediumBrush);

        newBtn = (ImageButton)findViewById(R.id.new_btn);
        newBtn.setOnClickListener(this);

        submitBtn = (ImageButton)findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(this);

        saveBtn = (ImageButton)findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void paintClicked(View view)
    {
        drawView.setErase(false);
        drawView.setBrushSize(drawView.getLastBrushSize());
        //use chosen color
        if(view!=currPaint)
        {
            //update color
            ImageButton imgView = (ImageButton) view;
            String color = view.getTag().toString();
            drawView.setColor(color);
            imgView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.paint_pressed, null));
            currPaint.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.paint,null));
            currPaint=(ImageButton)view;
        }

    }

    @Override
    public void onClick(View view)
    {
        //respond to clicks
        if(view.getId()==R.id.submit_btn)
        {
            //submit the current image to the server
            AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
            saveDialog.setTitle("Submit Drawing");
            saveDialog.setMessage("Are you sure you want to submit the current drawing?");
            saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //Save file
                    drawView.setDrawingCacheEnabled(true);
                    String imgSaved = MediaStore.Images.Media.insertImage(
                            getContentResolver(), drawView.getDrawingCache(),
                            ".bmp", "drawing");//was .png, but used .bmp for later purposes

                    if (imgSaved != null)
                    {
                        //double result = compareImg();
                        Toast savedToast = Toast.makeText(getApplicationContext(),
                                "Drawing has been submitted,check your device's gallery",
                                Toast.LENGTH_SHORT);
                        savedToast.show();
                    } else {
                        Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                "Sorry, your drawing couldn't be submitted", Toast.LENGTH_SHORT);
                        unsavedToast.show();
                    }
                    drawView.destroyDrawingCache();
                }
            });
            saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();
                }
            });
            saveDialog.show();
        }

        else if(view.getId()==R.id.new_btn)
        {
            //new button
            AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
            newDialog.setTitle("New drawing");
            newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
            newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    drawView.startNew();
                    dialog.dismiss();
                }
            });
            newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            newDialog.show();
        }

        else if(view.getId()==R.id.save_btn)
        {
            //Save current canvas as drawing
            AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
            saveDialog.setTitle("Save Drawing");
            saveDialog.setMessage("Are you sure you want to save the current drawing?");
            saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                    //Save file
                    drawView.setDrawingCacheEnabled(true);
                    String imgSaved = MediaStore.Images.Media.insertImage(
                            getContentResolver(),drawView.getDrawingCache(),
                            UUID.randomUUID().toString()+".bmp","drawing");//was .png, but used .bmp for later purposes

                    if(imgSaved!=null)
                    {
                        Toast savedToast = Toast.makeText(getApplicationContext(),
                                "Drawing has been saved,check your device's gallery",
                                Toast.LENGTH_SHORT);
                        savedToast.show();
                    }
                    else
                    {
                        Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                "Sorry, your drawing couldn't be saved",Toast.LENGTH_SHORT);
                        unsavedToast.show();
                    }
                    drawView.destroyDrawingCache();
                }
            });
            saveDialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();
                }
            });
            saveDialog.show();
        }

    }
    //new methods go here
    //public double compareImg() throws IOException {
        //URL url1 = new URL("http://rosettacode.org/mw/images/3/3c/Lenna50.jpg");
        //URL url2 = new URL("http://rosettacode.org/mw/images/b/b6/Lenna100.jpg");
        //img1 = ImageIO.read(url1);
        //img2 = ImageIO.read(url2);

      //  System.out.println("diff percent: " + ( 100.0));
    //}
}
