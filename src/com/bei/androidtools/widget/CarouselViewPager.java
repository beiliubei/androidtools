package com.bei.androidtools.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bei.androidtools.R;
import com.bei.androidtools.adapter.ViewPagerAdapter;
import com.bei.androidtools.util.ViewUtil;

/**
 * CarouselViewPager use for page
 * 
 * @author benny
 * 
 */
public class CarouselViewPager extends RelativeLayout {
    private List<View> pageViews = new ArrayList<View>();
    private ViewPagerAdapter mPagerAdapter;
    private ImageView[] mPageContorlViews;
    private LinearLayout pageControlLinearLayout;
    private Context mContext;
    private int mCurPosition = 0;
    private int mHeight = 0;
    private int mWidth = 0;
    private int mSelectDrawable = 0;
    private int mUnSelectDrawable = 0;
    private OnClickListener mOnClickListener;
    private OnShowImageViewListener mOnShowImageViewListener;

    public CarouselViewPager(Context context) {
        super(context);
    }

    /**
     * 
     * @param context
     * @param attrs imageScale_width imageScale_height paddingBottom pageSelectDrawable pageUnSelectDrawable
     */
    public CarouselViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CarouselViewPager);
        mContext = context;
        ViewPager viewPager = new ViewPager(context);
        mPagerAdapter = new ViewPagerAdapter(pageViews);
        viewPager.setOnPageChangeListener(new GuidePageChangeListener());
        viewPager.setAdapter(mPagerAdapter);
        int width = context.getResources().getDisplayMetrics().widthPixels;
        mWidth = width;
        mHeight = width * ta.getInteger(R.styleable.CarouselViewPager_imageScale_height, 9)
                / ta.getInteger(R.styleable.CarouselViewPager_imageScale_width, 16);
        addView(viewPager);
        pageControlLinearLayout = new LinearLayout(mContext);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        pageControlLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        pageControlLinearLayout.setGravity(Gravity.CENTER);
        pageControlLinearLayout.setPadding(0, 0, 0,
                ViewUtil.getPixels(ta.getInteger(R.styleable.CarouselViewPager_paddingBottom, 10), mContext));
        addView(pageControlLinearLayout, layoutParams);
        initPageIndicator();
        mSelectDrawable = ta.getResourceId(R.styleable.CarouselViewPager_pageSelectDrawable, 0);
        mUnSelectDrawable = ta.getResourceId(R.styleable.CarouselViewPager_pageUnSelectDrawable, 0);
        ta.recycle();
    }

    private class GuidePageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0)
        {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2)
        {
        }

        @Override
        public void onPageSelected(int position)
        {
            if (pageControlLinearLayout != null) {
                mCurPosition = position;
                for (int i = 0; i < mPageContorlViews.length; i++) {
                    mPageContorlViews[position].setImageResource(mSelectDrawable);
                    if (position != i) {
                        mPageContorlViews[i].setImageResource(mUnSelectDrawable);
                    }
                }
            }
        }
    }

    private void initPageIndicator()
    {
        int length = pageViews.size();

        mPageContorlViews = new ImageView[length];

        ImageView pageIndicator = null;
        pageControlLinearLayout.removeAllViews();
        for (int i = 0; i < length; i++) {
            pageIndicator = new ImageView(mContext);
            pageIndicator.setPadding(4, 0, 4, 0);
            if (mCurPosition == i) {
                pageIndicator.setImageResource(mSelectDrawable);
            } else {
                pageIndicator.setImageResource(mUnSelectDrawable);
            }
            mPageContorlViews[i] = pageIndicator;
            pageControlLinearLayout.addView(pageIndicator);
        }
    }

    public void onDataChange(List<String> imagePath)
    {
        pageViews.clear();
        for (int i = 0; i < imagePath.size(); i++) {
            ImageView image = new ImageView(mContext);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            pageViews.add(image);
            mOnShowImageViewListener.onShowImageView(imagePath.get(i));
            image.setOnClickListener(mOnClickListener);
        }
        initPageIndicator();
        mPagerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        widthMeasureSpec = MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.AT_MOST);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setOnItemPageClickListener(OnClickListener mOnClickListener)
    {
        this.mOnClickListener = mOnClickListener;
    }

    public int getCurPosition()
    {
        return mCurPosition;
    }
    
    public interface OnShowImageViewListener{
        public void onShowImageView(String imagePath);
    }
}
