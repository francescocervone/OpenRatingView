# OpenRatingView
Android library to create and manage simple rating views with stars.

<img src="https://raw.githubusercontent.com/francescocervone/OpenRatingView/master/screen.png" alt="screenshot" width="50%" height="50%"/>
## Including in your project
* Create a `libraries` folder under your project root directory
* Copy my `openratingview` folder under your `libraries` folder
* Edit your `settings.gradle` in your main directory adding: 
```Gradle
include ':libraries:openratingview'
```
* Edit your `build.gradle` file adding:
```Gradle
dependencies {
    compile project(':libraries:openratingview')
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

    Copyright (c) 2015 Francesco Cervone

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

