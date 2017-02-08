package com.oceansoft.osc;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.oceansoft.osc.moudle.consult.view.ConsultFragment;
import com.oceansoft.osc.moudle.home.view.HomeFragment;
import com.oceansoft.osc.moudle.matters.view.MattersFragment;
import com.oceansoft.osc.moudle.user.view.UserFragment;
import com.oceansoft.osc.mvp.view.BaseActivity;
import com.oceansoft.osc.utils.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private HomeFragment mHomeFragment;
    private MattersFragment mMattersFragment;
    private ConsultFragment mConsultFragment;
    private UserFragment mUserFragment;

    private FrameLayout mFrameLayout;
    private RadioGroup mRadioGroup;

    private Drawable[] arrIconDrawable;
    private String[] arrBaseTitles;
    private RadioButton[] arrRadioButton;

    private Context mContext = this;

    private List<Fragment> totalList = new ArrayList<>();
    private FragmentManager mFragmentManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initDate();
        initTabs();

    }
    private void initView() {
        mFrameLayout = (FrameLayout) findViewById(R.id.fragment_main);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup_main);
    }
    private void initTabs() {

        //遍历button按钮，进行设置
        for (int i = 0; i < arrBaseTitles.length; i++) {
            arrRadioButton[i] = new RadioButton(mContext);
            //为button按钮设置标题
            arrRadioButton[i].setText(arrBaseTitles[i]);
            //设置文字为灰色
            arrRadioButton[i].setTextColor(Color.GRAY);
            //设置文字大小
            arrRadioButton[i].setTextSize(12);
            //设置button的默认图标为透明
            arrRadioButton[i].setButtonDrawable(android.R.color.transparent);
            //获取屏幕宽度
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            //把屏幕四等分设置按钮
            arrRadioButton[i].setWidth(screenWidth / 4);
            //按钮居中
            arrRadioButton[i].setGravity(Gravity.CENTER);
            //在RadioButton上方放置icon图标
            arrIconDrawable[i].setBounds(0, 0, 40, 40);
            arrRadioButton[i].setCompoundDrawables(null, arrIconDrawable[i], null, null);
            //设置icon图标与文字间的填充
            arrRadioButton[i].setCompoundDrawablePadding(5);
            //设置icon矢量图的颜色
            arrIconDrawable[i].setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            //为radiogroup填充按钮
            mRadioGroup.addView(arrRadioButton[i]);
        }
        final int tabColor = getResources().getColor(R.color.colorPrimary);
        //设置第一个tab的文字颜色和icon图标的颜色
        arrRadioButton[0].setTextColor(tabColor);
        arrIconDrawable[0].setColorFilter(tabColor, PorterDuff.Mode.SRC_IN);
        //为radioGroup设置监听器
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < arrBaseTitles.length; i++) {
                    //设置所有tab的文字颜色和icon图标的颜色为统一的灰色
                    arrRadioButton[i].setTextColor(Color.GRAY);
                    //为矢量图标填充颜色
                    arrIconDrawable[i].setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                    if (arrRadioButton[i].getId() == checkedId) {
                        //设置被勾选到的tab的文字颜色和icon颜色
                        arrRadioButton[i].setTextColor(tabColor);
                        arrIconDrawable[i].setColorFilter(tabColor, PorterDuff.Mode.SRC_IN);
                        FragmentHelper.switchFragment(mFragmentManager, totalList, i, R.id.fragment_main, 0, 0);
                    }

                }
            }
        });

    }

    private void initDate() {
        //获得Fragment管理器
        mFragmentManager = getSupportFragmentManager();
        arrBaseTitles = getResources().getStringArray(R.array.arrBaseTitles);
        //获取导航图标
        TypedArray typedArray = getResources().obtainTypedArray(R.array.arrIconDrawable);
        //遍历图标
        arrIconDrawable = new Drawable[arrBaseTitles.length];
        for (int i = 0; i < arrBaseTitles.length; i++) {
            arrIconDrawable[i] = typedArray.getDrawable(i);
        }
        //初始化Button按钮
        arrRadioButton = new RadioButton[arrBaseTitles.length];
        mHomeFragment = new HomeFragment();
        mMattersFragment = new MattersFragment();
        mConsultFragment = new ConsultFragment();
        mUserFragment = new UserFragment();
        totalList.add(mHomeFragment);
        totalList.add(mMattersFragment);
        totalList.add(mConsultFragment);
        totalList.add(mUserFragment);
        FragmentHelper.replaceFragment(mFragmentManager, totalList, 0, R.id.fragment_main, 0, 0);

    }

}
