# Project Review 1

In terms of your second question, accessing the movie from the adapter is the preferred way to handle data and it is done well by you. :+1:

## Code Review

####  PopularMovies/app/src/main/java/com/scheffknecht/daniel/popularmovies/activities/MovieDetailActivity.java

:bell: Since from your codes, I can see that you are a really advanced student, in order to learn more, you could also check a package called "butterknife". In the future, you can find and automatically cast the corresponding view in your layout easily. This will save you a lot of time. :smiley:

Supp:

http://jakewharton.github.io/butterknife/

Also, a video tutorial:

https://www.youtube.com/watch?v=1A4LY8gUEDs

- - -

Here, to learn more, you can also try to use error and placeholder in Picasso here to avoid crashing down due to empty string values or null values. Before the error placeholder is shown, your request will be retried three times. sample codes (from Picasso documentation):

```java
Picasso.with(context)
    .load(url)
    .placeholder(R.drawable.user_placeholder)
    .error(R.drawable.user_placeholder_error)
    .into(imageView);
```

The quality of the data from this movie database is really good. However, when you want to use Picasso to show image fetched from other places (for example, Spotify), there is much higher chance for you to get Picasso an empty string value or null value. This is just for your information. :)

#### PopularMovies/app/build.gradle 

Yes great! You have your minSdkVersion value set to 15, this implementation can make your app able to run on most of the Android devices (96%). In the meantime, your app can gain a lot of latest powerful features from Android 4.X. Great job here!

Supp: https://developer.android.com/about/dashboards/index.html?utm_source=suzunone

- - -

This suggestion is related to the question you have asked about where to put API key

In general, a standard way to put API key into our app is by utilizing app/build.gradle file. You could put API key like this:

```gradle
apply plugin: 'com.android.application'

android {
    compileSdkVersion 25
    buildToolsVersion "25.0.2"
    defaultConfig {
        applicationId "com.example.devlovepreet.popularmovies2"
        minSdkVersion 15
        targetSdkVersion 25
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
        buildConfigField("String","API_KEY",'\"API Here\"')
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    dataBinding.enabled = true;
}
```

#### PopularMovies/app/src/main/java/com/scheffknecht/daniel/popularmovies/models/Movie.java

Awesome job implementing Parcelable! By doing so, you can optimize your app to save dynamic data/state efficiently. Well done! :clap:

#### PopularMovies/app/src/main/res/layout/activity_movie_detail.xml

I do think your app is really good but there is one critical issue concerning about it's UI. When your app is in its landscape orientation, your app can not show movie plot properly (there is no way for your users to view all of its contents). In order to fix this issue, you can consider to wrapb all the movie detail widgets within a ScrollView so that your users could scroll up and down.