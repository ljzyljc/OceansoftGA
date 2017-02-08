package com.oceansoft.osc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.oceansoft.osc.mvp.view.BaseActivity;
import com.oceansoft.osc.widget.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TempCw on 2017/2/8.
 * 引导页
 */

public class GuideActivity extends BaseActivity {

    private List<Integer> imageList;
    private List<ImageView> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initImageList();
        initViewList();
        initView();
    }

    private void initImageList(){
        imageList = new ArrayList<>();
        imageList.add(R.drawable.guide_0);
        imageList.add(R.drawable.guide_1);
        imageList.add(R.drawable.guide_2);
        imageList.add(R.drawable.guide_3);
    }

    private void initViewList(){
        viewList = new ArrayList<>();
        for (int i = 0 ; i < imageList.size() ; i++){
            ImageView imageView = new ImageView(this);
            viewList.add(imageView);
        }
    }

    private void initView() {
        ViewPager viewpager = (ViewPager) findViewById(R.id.view_pager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        viewpager.setAdapter(new GuideViewPager());
        indicator.setViewPager(viewpager);
        viewpager.setCurrentItem(0);


        final Button tv_in = (Button) findViewById(R.id.tv_in);
        tv_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        });
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == imageList.size() - 1){
                    tv_in.setVisibility(View.VISIBLE);
                }else {
                    tv_in.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class GuideViewPager extends PagerAdapter {

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView(viewList.get(position));
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            ImageView imageView = viewList.get(position);
            imageView.setImageResource(imageList.get(position));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            return imageView;
        }

    }
}
