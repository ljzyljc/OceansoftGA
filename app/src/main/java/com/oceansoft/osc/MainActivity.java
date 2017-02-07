package com.oceansoft.osc;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.oceansoft.osc.moudle.consult.view.ConsultFragment;
import com.oceansoft.osc.moudle.home.view.HomeFragment;
import com.oceansoft.osc.moudle.matters.view.MattersFragment;
import com.oceansoft.osc.moudle.user.view.UserFragment;
import com.oceansoft.osc.mvp.view.BaseActivity;
import com.oceansoft.osc.mvp.view.impl.AbsMvpActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener{
    private List<TabItem> tabItems;
    private FragmentTabHost fragmentTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabItemList();
        //绑定TabHost
        initTabView();
    }
    private void initTabView() {
        fragmentTabHost= (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
        fragmentTabHost.getTabWidget().setDividerDrawable(null);
        for(int i=0;i<tabItems.size();i++){
            TabItem tabItem=tabItems.get(i);
            TabHost.TabSpec tabSpec=fragmentTabHost.newTabSpec(tabItem.getTabTextRes()).setIndicator(tabItem.getTabIndicator());
            fragmentTabHost.addTab(tabSpec,tabItem.getFragmentClazz(),tabItem.getBundle());
            //设置背景
            fragmentTabHost.getTabWidget().getChildTabViewAt(i).setBackgroundColor(getResources().getColor(R.color.tabbar_bottom_bg));
            fragmentTabHost.setOnTabChangedListener(this);
            if (i==0){
                tabItem.setChecked(true);
            }

        }
    }

    private void initTabItemList() {
        tabItems=new ArrayList<>();
        this.tabItems.add(new TabItem(MainActivity.this,
                HomeFragment.class,
                R.drawable.icon_menu_index,
                R.drawable.icon_menu_index_a,
                R.string.home));
        this.tabItems.add(new TabItem(MainActivity.this,
                MattersFragment.class,
                R.drawable.icon_menu_center,
                R.drawable.icon_menu_center_a,
                R.string.matter));
        this.tabItems.add(new TabItem(MainActivity.this,
                ConsultFragment.class,
                R.drawable.icon_menu_matter_n,
                R.drawable.icon_menu_matter_p,
                R.string.consult));
        this.tabItems.add(new TabItem(MainActivity.this,
                UserFragment.class,
                R.drawable.icon_menu_personal_center,
                R.drawable.icon_menu_personal_center_a,
                R.string.user));
    }
    private int index=0;
    @Override
    public void onTabChanged(String tabId) {

        for (int i=0;i<tabItems.size();i++){
            TabItem tabItem=tabItems.get(i);
            if (tabItem.getTabTextRes().equals(tabId)){
                tabItem.setChecked(true);
                index=i;
            }else{
                tabItem.setChecked(false);
            }
        }
    }


}
