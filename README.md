# Leanplum-PersistentContentDemo
Android Demo on how to use Leanplum Variables and App Inbox to create content cards

You can instrument Leanplum variables to feed promotional content to your app’s activity in the form of banners. You can A/B test these variables, segment them and roll out them to a gradually increasing portion of your user-base.

In the following example we will demonstrate how images can be added dynamically to a carousel view inside separate activities.

# Variables DEMO

## Variable setup

We will use a nested group variable i.e. persistent_content, which will allow us to create separate sub-groups that will carry the images for the carousel

main_content is the name of the subgroup of images which will be rendered in the main activity. The name is completely arbitrary and it will matter in the code implementation step.

second_content is the name of the subgroup of images which will be rendered in the secondary activity. 

## Code implementation

In this example, we are using a simple carousel library ( <a>https://github.com/sayyam/carouselview</a> )

Also the picasso library in order to more easily convert the image url into an Image: 


MainActivity.class

The context variable needs to be the same as the subgroup variable where the images are added.


public class MainActivity extends AppCompatActivity {

    //Constant for the context related to this activity
    final private String context = "main_content";

  ...

SecondaryActivity.class

The implementation follows the same steps as in MainActivity.class, the only difference is that we are setting the context to be the same as the name of the second subgroup variable:


import androidx.appcompat.app.AppCompatActivity;
public class SecondaryActivity extends AppCompatActivity {

    //Constant for the context related to this activity
    final private String context = "second_content_card";
    
    ...
