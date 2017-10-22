# OpenRatingView
Android library to create and manage simple rating views with stars.

<img src="screen.png" alt="screenshot" width="50%" height="50%"/>

## Including in your project

* Add in your root `build.gradle` at the end of repositories:
```Gradle
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
* Add the dependency:
```
dependencies {
    ...
    compile 'com.github.francescocervone:OpenRatingView:1.1.0'
    // You need to add Glide or Picasso dependency.
    compile 'com.squareup.picasso:picasso:2.5.2'
    compile 'com.github.bumptech.glide:glide:4.2.0'
}
```

## XML example

```xml
<com.francescocervone.openratingview.RatingView
        android:id="@+id/rating_view"
        app:orv_max_rating="5"
        app:orv_star_color="white"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
```

* `orv_max_rating` represents the number of stars you want to use (default 5)
* `orv_star_color` can be only "white" or "black" (default "white")

## OnStarClick example

You can catch the user click on stars:

```java
mRatingView.setOnStarClickListener(new RatingView.OnStarClickListener() {
    @Override
    public void onClick(int position) {
        Toast.makeText(getApplicationContext(), "Star " + position, Toast.LENGTH_SHORT).show();

    }
});
```

## License
    The MIT License (MIT)

    Copyright (c) 2016 Francesco Cervone

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.


