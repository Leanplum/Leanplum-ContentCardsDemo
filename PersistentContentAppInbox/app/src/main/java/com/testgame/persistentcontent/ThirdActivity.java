package com.testgame.persistentcontent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumInbox;
import com.leanplum.LeanplumInboxMessage;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.testgame.contentcards.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
/**
 * Created by Daniel Bogdanov on 09/20/21.
 */

public class ThirdActivity extends AppCompatActivity {

    //LP Inbox
    private LeanplumInbox inbox;
    private List<String> messageIds;

    //contains images for the carousel
    private List<String> sampleImagesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //        Fetch inbox messages
        inbox = Leanplum.getInbox();
        List all = inbox.allMessages();
        List<LeanplumInboxMessage> myDataset = (List<LeanplumInboxMessage>) all;
        List<String> messages = new ArrayList<>();
        List<Date> timestamps = new ArrayList<>();
        messageIds = inbox.messagesIds();
        LinearLayout cl = (LinearLayout) findViewById(R.id.linear_layout);
        sampleImagesList = new ArrayList<>();




        for(String messageId : messageIds) {
            boolean show = false;
            boolean hasImage = false;

            LeanplumInboxMessage message = inbox.messageForId(messageId);
            CarouselView cv = null;
            //Set title and subtitle
            String title = message.getTitle();
            String subtitle = message.getSubtitle();
            String context = message.getData().optString("context");
            if(context.equals("LP_MAIN"))
            {
                //Check if there is an image attached
                String imageFilePath;
                if (message.getImageFilePath() != null) {
                    hasImage = true;
                    imageFilePath = message.getImageFilePath();
                } else {
                    imageFilePath = "";
                }

                Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);

                //Check if there is any additional data
                if (message.getData() != null) {
                    JSONObject data = message.getData();
                    Iterator<String> iter = data.keys();
                    cv = new CarouselView(this, null);

                    while (iter.hasNext()) {
                        String key = iter.next();

                        try {
                            Object value = data.get(key);

                            if ((value.toString()).contains("http")) setLinksList(value.toString());

                            //Check if it should render on this activity or another

                            if ((value.toString()).equals("LP_MAIN")) show = true;
                        } catch (JSONException e) {
                            // Something went wrong!
                        }
                    }
                }
                // Check if the content card should render
                if (show) {
                    //Check if the image contains a static Image and adds it to the view
                    if (hasImage) {

                        TextView titleView = new TextView(this);
                        titleView.setText(title);
                        titleView.setId(View.generateViewId());
                        titleView.setTextColor(Color.BLACK);
                        titleView.setTextSize(20);
                        titleView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        cl.addView(titleView);

                        TextView subtitleView = new TextView(this);
                        subtitleView.setText(subtitle);
                        subtitleView.setId(View.generateViewId());
                        subtitleView.setTextColor(Color.BLACK);
                        subtitleView.setTextSize(10);
                        subtitleView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        cl.addView(subtitleView);

                        TextView image = new TextView(this);
                        image.setBackground(new BitmapDrawable(getResources(), bitmap));
                        image.setId(View.generateViewId());
                        cl.addView(image);

                    } else {

                        TextView titleView = new TextView(this);
                        titleView.setText(title);
                        titleView.setId(View.generateViewId());
                        titleView.setTextColor(Color.BLACK);
                        titleView.setTextSize(20);
                        titleView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        cl.addView(titleView);

                        TextView subtitleView = new TextView(this);
                        subtitleView.setText(subtitle);
                        subtitleView.setId(View.generateViewId());
                        subtitleView.setTextColor(Color.BLACK);
                        subtitleView.setTextSize(10);
                        subtitleView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        cl.addView(subtitleView);


                        cv.setId(View.generateViewId());
                        cv.setMinimumWidth(200);
                        cv.setMinimumHeight(150);
                        cv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 500));
                        cv.setPageCount(sampleImagesList.size());
                        cv.setImageListener(imageListener);
                        cl.addView(cv);
//                    sampleImagesList.clear();
                    }

                }
            }
        }// end for
    } // end onCreate

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {

            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            Picasso.get().load(getLinkAt(position)).into(imageView);

        }
    };

    public void goToStore(View view)
    {
        Intent myIntent = new Intent(ThirdActivity.this, SecondaryActivity.class);
//        myIntent.putExtra("key", value); //Optional parameters
        ThirdActivity.this.startActivity(myIntent);
    }

    //getters & setters

    private void setLinksList(String link)
    {
        this.sampleImagesList.add(link);
    }
    private String getLinkAt(int position)
    {
        return this.sampleImagesList.get(position);
    }

}