package sms.edward.per.myapplication;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * description:
 * <p>
 * author:Edward
 * <p>
 * 2015/11/8
 */
public class three extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three);

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
        //只显示标题，有三个可选址SHOW_TITLE，SHOW_ICON，SHOW_ICON_AND_TITLE
        tabView.setIsShowIconTitle(TabView.SHOW_TITLE);
        //设置tab到顶部，只有TabView.TAB_LOCATION_TOP，TabView.TAB_LOCATION_BOTTOM可选
        tabView.setTabLocation(TabView.TAB_LOCATION_TOP);
        //设置tab整个栏的高度
        tabView.setTabHeight(30);
        //禁止ViewPager滚动，true表示可滑动，false表示不可滑动
        tabView.setIsViewPagerScrollable(false);
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
