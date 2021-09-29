package com.testgame.contentcards;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.leanplum.Var;
import com.leanplum.internal.FileManager;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
/**
 * Created by Daniel Bogdanov on 09/20/21.
 */

public class SecondaryActivity extends AppCompatActivity {

    //Constant for the context related to this activity
    final private String context = "second_content_card";

    // Variable declaration
    public Var<HashMap<String, HashMap<String,String>>> contentCards = Var.define("content_cards", new HashMap<String, HashMap<String,String>>());

    // HashMap which will contain only the images for this specific activity. In this case, they are recognized by context constant
    public HashMap<String, HashMap<String,String>> imageNames;

    //contains images for the carousel
    private List<String> sampleImagesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_secondary);
        LinearLayout cl = (LinearLayout) findViewById(R.id.linear_layout);
        sampleImagesList = new ArrayList<>();
        //init carousel
        CarouselView cv = new CarouselView(this, null);
        cv.setId(View.generateViewId());
        cv.setMinimumWidth(200);
        cv.setMinimumHeight(150);
        cv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 500));

        imageNames = contentCards.value();

        // Get all values for the given context

        for(String value : imageNames.get(context).values()) {
            if (value != null) {
                FileManager.DownloadFileResult result = FileManager.maybeDownloadFile(false, value, null, null, null);
                if (result != FileManager.DownloadFileResult.DOWNLOADING) {
//                    setImage(value);
                    // get the image Uris and add them to the carousel
                    sampleImagesList.add(getImageFileUri(value).toString());
                }
            }

            cv.setPageCount(sampleImagesList.size());
            cv.setImageListener(imageListener);
            cl.removeView(cv);
            cl.addView(cv);
        } // end for


    } // end onCreate

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {

            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            Picasso.get().load(getLinkAt(position)).into(imageView);

        }
    };

    private Uri getImageFileUri(String fileName) {
        java.io.File imgFile = new java.io.File(FileManager.fileValue(fileName));
        return Uri.fromFile(imgFile);
    }

    public void goToStore(View view) {
        Intent myIntent = new Intent(SecondaryActivity.this, SecondaryActivity.class);
//        myIntent.putExtra("key", value); //Optional parameters
        SecondaryActivity.this.startActivity(myIntent);
    }

    private String getLinkAt(int position)
    {
        return this.sampleImagesList.get(position);
    }

}