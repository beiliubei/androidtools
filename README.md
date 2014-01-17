## androidtools
============

android develop tools


## How to use
#### In xml layout
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    	xmlns:carousel="http://schemas.android.com/apk/res/com.augmentum.op.hiker"
    	xmlns:tools="http://schemas.android.com/tools"
    	android:layout_width="match_parent"
    	android:layout_height="wrap_content"
    	android:orientation="vertical" >
    	<com.augmentum.op.hiker.ui.widget.CarouselViewPager
        	android:id="@+id/activity_picture"
        	android:layout_width="fill_parent"
        	android:layout_height="wrap_content"
        	carousel:imageScale_height="1"
        	carousel:imageScale_width="1"
        	carousel:paddingBottom="10"
       		carousel:pageSelectDrawable="@drawable/page_select"
        	carousel:pageUnSelectDrawable="@drawable/page_normal"/>
	</LinearLayout>
	
#### In java code
	init view:
	
	CarouselViewPager mViewPager = (CarouselViewPager)mContentLayout.findViewById(R.id.activity_picture);
    mViewPager.setOnItemPageClickListener(….);
    
    load data:
    mViewPager.onDataChange(imagePaths);
    
## Compile & Install
	mvn clean install
	mvn install:install-file -Dfile=target/androidtools-1.0.apklib -DgroupId=com.bei.androidtools -DartifactId=androidtools -Dversion=1.0 -Dpackaging=apklib