# Affirm Exam - Flickr Feed Viewer

A simple android app that shows a flickr feed based on a search parameter

Demo:



![enter image description here](https://github.com/neilbantoc/AffirmExam/raw/master/gifs/demo.gif)

to compile, don't forget to add these lines to your `gradle.properties` file

    android.useAndroidX=true
    android.enableJetifier=true
    FlickrApiKey="<your Flickr API key here>"

# Highlights

- used a modified version of MVP for the architecture
- `Kotlin` codebase
- endless scrolling
- immersive mode when scrolling down

# Libraries/Components used
 - Retrofit
 - Picasso
 - Databinding + Databinding Adapters
 - `RecyclerView`
 - `StaggeredGridLayoutManager`