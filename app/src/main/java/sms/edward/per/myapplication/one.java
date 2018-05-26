package sms.edward.per.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;


public class one extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one);

        //调起Tab的核心代码如下
        String[] tabTitle = {"1", "2", "3"};
        TabView tabView = (TabView) findViewById(R.id.tab_view);
        //设置tab标题
        tabView.setTabTitle(tabTitle);
        tabView.setFragments(this, getFragment(tabTitle));
    }

    public Fragment[] getFragment(String[] tabTitle) {
        Fragment[] fragments = new Fragment[tabTitle.length];
        for (int i = 0; i < tabTitle.length; i++) {
            Fragment fragment = new MyFragment();
            Bundle bundle = new Bundle();
            //设置参数，传递给Fragment页面
            bundle.putString("my", String.valueOf(i + 1));
            fragment.setArguments(bundle);
            fragments[i] = fragment;
        }
        return fragments;
    }
}
