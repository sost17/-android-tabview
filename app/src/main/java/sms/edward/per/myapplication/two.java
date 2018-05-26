package sms.edward.per.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * description:
 * <p>
 * author:Edward
 * <p>
 * 2015/11/8
 */
public class two extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two);

        int[] selectedTabImg = {R.mipmap.aty_main_home, R.mipmap.aty_main_centre, R.mipmap.ic_search_blue};
        int[] selectTabImg = {R.mipmap.aty_main_home_c, R.mipmap.aty_main_centre_c, R.mipmap.ic_search_black};
        String[] tabTitle = {"首页", "我", "搜索"};
        TabView tabView = (TabView) findViewById(R.id.tab_view);
        //设置tab标题
        tabView.setTabTitle(tabTitle);
        //设置没有被选中的图标状态
        tabView.setSelectTabImg(selectTabImg);
        //设置被选中的图标状态
        tabView.setSelectedTabImg(selectedTabImg);
        //此处的setFragments必须最后才调用，否则上面的设置都无效
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
