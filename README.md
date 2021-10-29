# Leanplum-PersistentContentDemo
Android Demo on how to use Leanplum Variables and App Inbox to create persistent content

You can instrument Leanplum variables to feed promotional content to your app’s activity in the form of banners. You can A/B test these variables, segment them and roll out them to a gradually increasing portion of your user-base. Or you can choose App Inbox implementation to take advantage of the out-of-the-box Campaigns functionalities.

In the following example we will demonstrate how images can be added dynamically to a carousel view inside separate activities.

# App Inbox DEMO

With the Leanplum App Inbox you can also insert content inside specific app activities to inform your users about new features or daily promotions. As this content is set through the Leanplum dashboard, you  can distribute it to specific audiences, measure engagement and control the content by using the out of the box features provided.

While the content control and segmentation can be controlled directly via your Leanplum Dashboard OOTB, the implementation of the Leanpum App Inbox requires code change.

## Campaign Setup
Let’s start with the setup in Campaign Composer, where we can see some key things that are vital for the Persistent Content use case. 

In this specific example we have a carousel which is rendered on one of the activities and we have 3 separate campaigns, which will add an image into the carousel. All 3 campaigns will be built in the same fashion as the one in the screenshot below:

<img src="https://github.com/Leanplum/Leanplum-PersistentContentDemo/blob/master/appinbox_demo.png"/>

1. The most important part is the context variable’s value passed with a JSON object called Data along with the App Inbox Message, this can be any string and it will be used later in the code to decide on which activity to render the banner .

2. This is the variable which will hold the url to the image, it can be any string.

3. The context key, which will remain the same in all campaigns. It is vital that this key is the same in all campaigns, as the custom app logic which we will create it will use it to render the banner  to the appropriate activity.

4. The title, which you can add optionally to the banner  to further customize the  banner.

5. The subtitle, which you can add optionally to the banner .

We have instrumented it in a couple of use cases:

In the Main Activity we can add images into the carousel underneath the permanent content as separate images. All images in the carousel represent separate campaigns:

## Campaigns used:

Image 1 - https://dashboard-dot-leanplum.appspot.com/dashboard2/4540500222738432/composer/1642395027/actions/1635135071/edit

https://dashboard-dot-leanplum.appspot.com/dashboard2/4540500222738432/composer/1619395050/actions/1638715066/edit

https://dashboard-dot-leanplum.appspot.com/dashboard2/4540500222738432/composer/1638685043/actions/1648305109/edit

 

We can navigate on 2 other activities Bikes and Jerseys:

In Both we have similar use-cases. We have a Carousel, where this time, all images are coming from a single campaign. Underneath, there is a static banner.

## Campaigns Used:

## BIKES:

* <a>https://dashboard-dot-leanplum.appspot.com/dashboard2/4540500222738432/composer/1619365185/actions/1619355200/edit</a>

* <a>https://dashboard-dot-leanplum.appspot.com/dashboard2/4540500222738432/composer/1627565095/actions/1625855146/edit</a>

## JERSEYS

* <a>https://dashboard-dot-leanplum.appspot.com/dashboard2/4540500222738432/composer/1628695141/actions/1650055121/edit</a>

* <a>https://dashboard-dot-leanplum.appspot.com/dashboard2/4540500222738432/composer/1636795082/actions/1638795014/edit</a>

 

Code implementation - Android
In this example, we are using a simple carousel library (GitHub - sayyam/carouselview: A simple library to add carousel view in android app. )

Also the picasso library in order to more easily convert the image url into an Image: GitHub - square/picasso: A powerful image downloading and caching library for Android 


# Variables DEMO

## Variable setup

We will use a nested group variable i.e. persistent_content, which will allow us to create separate sub-groups that will carry the images for the carousel

main_content is the name of the subgroup of images which will be rendered in the main activity. The name is completely arbitrary and it will matter in the code implementation step.

second_content is the name of the subgroup of images which will be rendered in the secondary activity. 

<img src="https://github.com/Leanplum/Leanplum-PersistentContentDemo/blob/master/vars_demo.png"/>

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
    final private String context = "second_content";
    
    ...
