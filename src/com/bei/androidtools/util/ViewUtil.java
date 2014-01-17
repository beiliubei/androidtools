package com.bei.androidtools.util;

import java.util.Map;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;

public class ViewUtil {
	@SuppressLint("NewApi")
	public static void setAlpha(View view, float alpha) {
		if (Build.VERSION.SDK_INT < 11) {
			final AlphaAnimation animation = new AlphaAnimation(alpha, alpha);
			animation.setDuration(0);
			animation.setFillAfter(true);
			view.startAnimation(animation);
		} else {
			view.setAlpha(alpha);
		}
	}

	public static void hideKeyBoard(Activity activity) {
		((InputMethodManager) activity
				.getSystemService(Activity.INPUT_METHOD_SERVICE))
				.hideSoftInputFromWindow(activity.getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}

	public static int getPixels(int dipValue, Context context) {
		Resources r = context.getResources();
		int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dipValue, r.getDisplayMetrics());
		return px;
	}

	/**
	 * start new activity without bundle
	 * @param context
	 * @param newActivity
	 * @param finish
	 */
	public static void navigateActivity(Activity context, Class<?> newActivity, boolean finish ,int flags) {
		Intent i = new Intent(context, newActivity);
		if (flags > 0) {
			i.setFlags(flags);
		}
		context.startActivity(i);
		if (finish) {
			context.finish();
		}
	}
	
	/**
	 * start new activity without bundle,flags and finish self
	 * @param context
	 * @param newActivity
	 */
	public static void navigateActivity(Activity context, Class<?> newActivity){
		navigateActivity(context, newActivity, true, 0);
	}
	
	/**
	 * start new activity with bundle
	 * @param context
	 * @param newActivity
	 * @param extraList
	 */
	public static void navigateActivity(Activity context, Class<?> newActivity,
			Map<String, Object> extraList, boolean finish) {
		Intent i = new Intent(context, newActivity);
		Bundle mBundle = new Bundle();
		Set<String> keys = extraList.keySet();
		for (String Key : keys) {
			mBundle.putParcelable(Key, (Parcelable) extraList.get(Key));
		}
		i.putExtras(mBundle);
		context.startActivity(i);
		if (finish) {
			context.finish();
		}
	}
	
	/**
	 * start new activity with bundle finish self
	 * @param context
	 * @param newActivity
	 * @param extraList
	 */
	public static void navigateActivity(Activity context, Class<?> newActivity,
			Map<String, Object> extraList){
		navigateActivity(context, newActivity, extraList, true);
	}
	
	public static int getActionBarHeight(Context context) {
		TypedValue tv = new TypedValue();
		int actionBarHeight = 0;
		if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
		{
		    actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,context.getResources().getDisplayMetrics());
		}
		return actionBarHeight;
	}
	
	public static int getStatusBarHeight(Context context) { 
	      int result = 0;
	      int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
	      if (resourceId > 0) {
	          result = context.getResources().getDimensionPixelSize(resourceId);
	      } 
	      return result;
	} 
}
