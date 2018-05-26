package sms.edward.per.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class TabView extends RelativeLayout {
    //同时显示图标和标题
    public static final int SHOW_ICON_AND_TITLE = 30;
    //只显示图标
    public static final int SHOW_ICON = 31;
    //只显示标题
    public static final int SHOW_TITLE = 32;
    //tab放在底部
    public static final int TAB_LOCATION_BOTTOM = 1;
    //tab放在顶部
    public static final int TAB_LOCATION_TOP = 2;

    private RelativeLayout[] relativeLayouts;
    private ImageView[] imageViews;
    private TextView[] textViews;
    private Fragment[] fragments;
    private Context mContext;
    private ForbidSlideViewPager viewPager;
    private FragmentActivity fragmentActivity;
    //游标
    private View cursorImg;
    //标题
    private String[] tabTitle;
    //未点击图标
    private int[] selectTabImg;
    //已点击图标
    private int[] selectedTabImg;
    //标记旧的页面下标
    private int oldPageIndex = 0;
    //viewpager布局参数
    private LayoutParams viewPagerLayoutParams;
    //tab容器布局参数
    private LayoutParams lineTabContainerLayoutParams;
    //icon的布局参数
    private LayoutParams iconLayoutParams;
    //标题布局参数
    private LayoutParams textViewLayoutParams;
    //横线布局参数
    private LayoutParams horizontalLineLayoutParams;
    //默认字体大小为14dp
    private int textSize = 14;
    //设置图片的宽度和高度，默认为25dp
    private int imgWidth = 25, imgHeight = 25;
    //Tab的高度
    private int tabHeight = 60;
    //tab横线颜色，默认为浅蓝色
    private int tabTransverseLineColor = Color.parseColor("#ffbdc0c3");
    //tab横线高度，默认为1
    private int tabTransverseLineHeight = 1;
    //文本的颜色，默认为黑色
    private int selectTextViewColorId = Color.parseColor("#000000");
    //选中文本的颜色，默认为浅蓝色
    private int selectedTextViewColorId = Color.parseColor("#31b2f7");
    //是否显示Iicon或title，默认为两个都显示
    private int isShowIconTitle = 30;
    //tab未点击的背景颜色
    private int tabSelectBackgroundColorId = Color.parseColor("#e8ebee");
    //tab已点击的背景颜色
    private int tabSelectedBackgroundColorId = Color.parseColor("#EBF5FB");
    //图标与文本的间距，默认为5dp
    private int iconAndTextSpace = 5;
    //偏移量，默认为0
    private int offset = 0;
    //是否显示游标，默认为不显示
    private boolean isShowCursor = false;
    //ViewPager是否可滚动，默认为可滚动
    private boolean isViewPagerScrollable = true;
    //tab横线控件
    private View horizontalLine;
    //tab容器
    private LinearLayout lineTabContainer;
    //创建LinearLayout用来包裹图片和文本。
    private LinearLayout lineVerticalImgTxt;
    //tab的位置，默认为底部
    private int tabLocation = 1;


    //设置是否显示icon和标题
    public void setIsShowIconTitle(int isShowIconTitle) {
        this.isShowIconTitle = isShowIconTitle;
        //检查用户传入的值是否在指定的范围之内。如果不在指定范围，则默认初始值
        switch (isShowIconTitle) {
            case SHOW_ICON_AND_TITLE:
                break;
            case SHOW_ICON:
                break;
            case SHOW_TITLE:
                break;
            default:
                //默认为同时显示图标和标题
                this.isShowIconTitle = 30;
                break;
        }
    }

    //设置Tab位置
    public void setTabLocation(int tabLocation) {
        this.tabLocation = tabLocation;

    }

    //设置ViewPager滚动
    public void setIsViewPagerScrollable(boolean isViewPagerScrollable) {
        this.isViewPagerScrollable = isViewPagerScrollable;
    }

    //设置显示游标
    public void setIsShowCursor(boolean isShowCursor) {
        this.isShowCursor = isShowCursor;
    }

    //游标颜色
    private int cursorBackgroundColorId = Color.parseColor("#31b2f7");

    //设置游标颜色
    public void setCursorBackgroundColorId(int cursorBackgroundColorId) {
        this.cursorBackgroundColorId = cursorBackgroundColorId;
    }

    //设置间距
    public void setIconAndTextSpace(int iconAndTextSpace) {
        this.iconAndTextSpace = iconAndTextSpace;
    }

    //设置未点击的背景颜色
    public void setTabSelectBackgroundColorId(int tabSelectBackgroundColorId) {
        this.tabSelectBackgroundColorId = tabSelectBackgroundColorId;
    }

    //设置已点击的背景颜色
    public void setTabSelectedBackgroundColorId(int tabSelectedBackgroundColorId) {
        this.tabSelectedBackgroundColorId = tabSelectedBackgroundColorId;
    }

    //设置文本颜色
    public void setSelectTextViewColorId(int selectTextViewColorId) {
        this.selectTextViewColorId = selectTextViewColorId;
    }

    //设置选中文本颜色
    public void setSelectedTextViewColorId(int selectedTextViewColorId) {
        this.selectedTextViewColorId = selectedTextViewColorId;
    }

    //设置横线高度
    public void setTabTransverseLineHeight(int tabTransverseLineHeight) {
        this.tabTransverseLineHeight = tabTransverseLineHeight;
    }

    //设置横线颜色
    public void setTabTransverseLineColor(int tabTransverseLineColor) {
        this.tabTransverseLineColor = tabTransverseLineColor;
    }

    //设置tab的高度
    public void setTabHeight(int tabHeight) {
        this.tabHeight = tabHeight;
    }

    //设置图片宽度和高度
    public void setImgWidthAndHeight(int imgWidth, int imgHeight) {
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
    }

    //设置按下图标，必须实现
    public void setSelectedTabImg(int[] selectedTabImg) {
        this.selectedTabImg = selectedTabImg;
    }

    //设置原始图标，，必须实现
    public void setSelectTabImg(int[] selectTabImg) {
        this.selectTabImg = selectTabImg;
    }

    //设置标题，必须实现
    public void setTabTitle(String[] tabTitle) {
        this.tabTitle = tabTitle;
    }

    //设置页面，必须实现
    public void setFragments(FragmentActivity fragmentActivity, Fragment[] fragments) {
        this.fragments = fragments;
        this.fragmentActivity = fragmentActivity;
        //初始化布局
        initView();
        //初始化第一个Tab背景
        setImgAndFont(0, selectedTextViewColorId, tabSelectedBackgroundColorId, false);
    }

    //设置字体大小
    public void setTextSize(int textSize) {
        this.textSize = textSize;

        invalidate();
    }

    /**
     * 构造方法
     *
     * @param context
     */
    public TabView(Context context) {
        this(context, null);
    }

    /**
     * 构造方法
     *
     * @param context
     * @param attrs
     */
    public TabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 构造方法
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.TabView);


        textSize = typedArray.getDimensionPixelSize(R.styleable.TabView_tv_Tab_Title_Size, 14);
        tabHeight = typedArray.getDimensionPixelSize(R.styleable.TabView_tv_Tab_Height, 60);
        selectTextViewColorId = typedArray.getColor(R.styleable.TabView_tv_Tab_Select_TextView_Color, Color.parseColor("#000000"));
        selectedTextViewColorId = typedArray.getColor(R.styleable.TabView_tv_Tab_Selected_TextView_Color, Color.parseColor("#31b2f7"));
        tabLocation = typedArray.getInt(R.styleable.TabView_tv_Tab_Location, TAB_LOCATION_BOTTOM);
        //处理用户输入的数值，如果不在指定范围之内，则默认TAB_LOCATION_BOTTOM
        switch (tabLocation) {
            case TAB_LOCATION_BOTTOM:
                break;
            case TAB_LOCATION_TOP:
                break;
            default:
                tabLocation = TAB_LOCATION_BOTTOM;
                break;
        }
        isShowIconTitle = typedArray.getInt(R.styleable.TabView_tv_Tab_Show_Icon_Title, SHOW_ICON_AND_TITLE);
        //处理用户输入的数值，如果不在指定范围之内，默认SHOW_ICON_AND_TITLE
        switch (isShowIconTitle) {
            case SHOW_ICON_AND_TITLE:
                break;
            case SHOW_TITLE:
                break;
            case SHOW_ICON:
                break;
            default:
                tabLocation = SHOW_ICON_AND_TITLE;
                break;
        }
        //默认允许ViewPager滑动
        isViewPagerScrollable = typedArray.getBoolean(R.styleable.TabView_tv_ViewPage_Scrollable, true);
        isShowCursor = typedArray.getBoolean(R.styleable.TabView_tv_Tab_Show_Cursor, false);
        cursorBackgroundColorId = typedArray.getColor(R.styleable.TabView_tv_Tab_Show_Cursor_Color, Color.parseColor("#31b2f7"));
        tabSelectBackgroundColorId = typedArray.getColor(R.styleable.TabView_tv_Tab_Select_Background_Color, Color.parseColor("#e8ebee"));
        tabSelectedBackgroundColorId = typedArray.getColor(R.styleable.TabView_tv_Tab_Selected_Background_Color, Color.parseColor("#EBF5FB"));
        imgWidth = typedArray.getDimensionPixelSize(R.styleable.TabView_tv_Tab_Img_Width, 25);
        imgHeight = typedArray.getDimensionPixelSize(R.styleable.TabView_tv_Tab_Img_Height, 25);
        tabTransverseLineColor = typedArray.getColor(R.styleable.TabView_tv_Tab_Transverse_Line_Color, Color.parseColor("#ffbdc0c3"));
        tabTransverseLineHeight = typedArray.getDimensionPixelSize(R.styleable.TabView_tv_Tab_Transverse_Line_Height, 1);
        iconAndTextSpace = typedArray.getDimensionPixelSize(R.styleable.TabView_tv_Tab_Icon_Title_Space, 5);
        typedArray.recycle();
    }

    /**
     * 适配器
     */
    public class TabViewFragmentPagerAdapter extends FragmentPagerAdapter {
        private Fragment[] fragments;

        public TabViewFragmentPagerAdapter(FragmentManager fm, Fragment[] fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }


    /**
     * ViewPager监听器
     */
    public class ViewPagerOnPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //如果有显示游标就显示游标的移动动画
            if (isShowCursor) {
                Animation animation = new TranslateAnimation(oldPageIndex * offset, position * offset, 0, 0);
                animation.setFillAfter(true);
                animation.setDuration(100);
                cursorImg.setAnimation(animation);
            }
            changePage(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 设置ViewPager控件
     */
    public void setViewPagerWidget() {
        //设置FrameLayout控件
        viewPager = new ForbidSlideViewPager(mContext);
        //默认到第一页
        viewPager.setCurrentItem(0);
        //设置ViewPager是否可滚动
        viewPager.setIsScrollable(isViewPagerScrollable);

        viewPager.setAdapter(new TabViewFragmentPagerAdapter(fragmentActivity.getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(new ViewPagerOnPagerChangeListener());
        //设置ID为5
        viewPager.setId(Integer.valueOf(5));
        viewPagerLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        //将此控件放在横线上面。
//        viewPagerLayoutParams.addRule(RelativeLayout.ABOVE, 2);
        viewPager.setLayoutParams(viewPagerLayoutParams);
        addView(viewPager);
    }

    /**
     * 设置lineTabContainer控件
     */
    public void sheLineTabContainerWidget() {
        //lineTabContainer容器
        lineTabContainer = new LinearLayout(mContext);
        //水平布局
        lineTabContainer.setOrientation(LinearLayout.HORIZONTAL);
        //设置Tab容器的高度
        lineTabContainerLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, NumberValueChangeDp(tabHeight));
        //将这个控件的ID设置为1
        lineTabContainer.setId(Integer.valueOf(1));
        //将LinearLayout放置于父控件的底部
//        lineTabContainerLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lineTabContainer.setLayoutParams(lineTabContainerLayoutParams);

        addView(lineTabContainer);
    }


    /**
     * 初始化布局
     */
    public void initView() {
        //设置ViewPager控件
        setViewPagerWidget();
        //设置lineTabContainer控件
        sheLineTabContainerWidget();
        //设置横线
        setHorizontalLine();
        //创建游标
        createCursor();

        //初始化数组
        relativeLayouts = new RelativeLayout[tabTitle.length];
        imageViews = new ImageView[tabTitle.length];
        textViews = new TextView[tabTitle.length];
        //注意此处，设置每个RelativeLayout比重为1
        LinearLayout.LayoutParams tabRelaLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);

        //根据外面传进来的数据进行布局
        for (int i = 0; i < tabTitle.length; i++) {
            //设置RelativeLayout布局
            RelativeLayout rela = new RelativeLayout(mContext);
            rela.setOnClickListener(new ClickListener(i));
            //设置没有选中的背景颜色
            rela.setBackgroundColor(tabSelectBackgroundColorId);
            //设置内部控件垂直，水平布局(注意不能使用RelativeLayout.CENTER_VERTICAL)
            rela.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            lineTabContainer.addView(rela, tabRelaLayoutParams);

            lineVerticalImgTxt = new LinearLayout(mContext);
            lineVerticalImgTxt.setOrientation(LinearLayout.VERTICAL);
            //设置垂直方向布局。
            lineVerticalImgTxt.setGravity(Gravity.CENTER_HORIZONTAL);

            if (selectTabImg != null && selectTabImg.length > 0) {
                //设置图片
                ImageView imgViewIcon = new ImageView(mContext);
                //默认为不显示
                imgViewIcon.setVisibility(View.GONE);
                if (isShowIconTitle == SHOW_ICON_AND_TITLE) {
                    imgViewIcon.setVisibility(View.VISIBLE);
                } else if (isShowIconTitle == SHOW_ICON) {
                    imgViewIcon.setVisibility(View.VISIBLE);
                }
//                imgViewIcon.setVisibility(isHideIcon ? View.GONE : View.VISIBLE);
                imgViewIcon.setBackgroundResource(selectTabImg[i]);
                iconLayoutParams = new LayoutParams(NumberValueChangeDp(imgWidth), NumberValueChangeDp(imgHeight));

                //将图片添加到lineVerticalImgTxt
                lineVerticalImgTxt.addView(imgViewIcon, iconLayoutParams);
                imageViews[i] = imgViewIcon;
            }

            //设置tab标题
            TextView tabTitle = new TextView(mContext);
            //默认为不显示
            tabTitle.setVisibility(View.GONE);
            if (isShowIconTitle == SHOW_ICON_AND_TITLE) {
                tabTitle.setVisibility(View.VISIBLE);
            } else if (isShowIconTitle == SHOW_TITLE) {
                tabTitle.setVisibility(View.VISIBLE);
            }
            //判断tab标题是否应该隐藏
//            tabTitle.setVisibility(isHideText ? View.GONE : View.VISIBLE);
            tabTitle.setPadding(0, iconAndTextSpace, 0, 0);
            tabTitle.setText(this.tabTitle[i]);
            //设置字体大小
            tabTitle.setTextSize(textSize);
            //如果是第一个选项，则将这个选项的tab标题，设置为选中的颜色
            tabTitle.setTextColor(i == 0 ? selectedTextViewColorId : selectTextViewColorId);
            textViewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            tabTitle.setLayoutParams(textViewLayoutParams);
            lineVerticalImgTxt.addView(tabTitle);

            //设置布局参数
            LayoutParams tabRela = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            rela.addView(lineVerticalImgTxt, tabRela);

            relativeLayouts[i] = rela;
            textViews[i] = tabTitle;
        }

        //设置布局的排列顺序
        if (tabLocation == TAB_LOCATION_BOTTOM) {
            viewPagerLayoutParams.addRule(RelativeLayout.ABOVE, 2);
            lineTabContainerLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            horizontalLineLayoutParams.addRule(RelativeLayout.ABOVE, 1);
        } else if (tabLocation == TAB_LOCATION_TOP) {
            lineTabContainerLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            horizontalLineLayoutParams.addRule(RelativeLayout.BELOW, 1);
            viewPagerLayoutParams.addRule(RelativeLayout.BELOW, 2);
        } else {
            Log.e("----------->", "设置setTabLocation方法错误！只能是TAB_LOCATION_BOTTOM，TAB_LOCATION_TOP二选一");
        }
    }

    /**
     * 设置横线
     */
    public void setHorizontalLine() {
        //设置横线
        horizontalLine = new View(mContext);
        //设置Tab横线颜色
        horizontalLine.setBackgroundColor(tabTransverseLineColor);
        //设置Tab横线的高度
        horizontalLineLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, NumberValueChangeDp(tabTransverseLineHeight));
        horizontalLine.setLayoutParams(horizontalLineLayoutParams);
        //将这个横线的位置放的1控件的上面
//        horizontalLineLayoutParams.addRule(RelativeLayout.ABOVE, 1);
        //将这个控件ID设置为2
        horizontalLine.setId(Integer.valueOf(2));
        addView(horizontalLine);

    }

    /**
     * 创建游标
     */
    public void createCursor() {
        //是否显示游标
        if (isShowCursor) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            fragmentActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            //获取屏幕宽度
            int screenWidth = displayMetrics.widthPixels;
            //得到每个tab的宽度（屏幕宽度除以tab总数）
            offset = screenWidth / tabTitle.length;

            //设置下标
            cursorImg = new View(mContext);
            cursorImg.setBackgroundColor(cursorBackgroundColorId);
            LayoutParams cursorLayoutParams = new LayoutParams(offset, NumberValueChangeDp(tabTransverseLineHeight));
            cursorImg.setLayoutParams(cursorLayoutParams);

            if (tabLocation == TAB_LOCATION_BOTTOM) {
                cursorLayoutParams.addRule(RelativeLayout.BELOW, 5);
            } else if (tabLocation == TAB_LOCATION_TOP) {
                cursorLayoutParams.addRule(RelativeLayout.BELOW, 1);
            } else {
                Log.e("----------->", "设置setTabLocation方法错误！只能是TAB_LOCATION_BOTTOM，TAB_LOCATION_TOP二选一");
            }
            addView(cursorImg);
        }
    }

    /**
     * 点击事件
     */
    public class ClickListener implements OnClickListener {
        public int index;

        public ClickListener(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {
            changePage(index);
        }
    }

    /**
     * 跳转页面
     *
     * @param currentPage
     */
    public void changePage(int currentPage) {
        if (oldPageIndex != currentPage) {
            //跳转页面
            viewPager.setCurrentItem(currentPage);
            //将Tab设置为原来的颜色
            setImgAndFont(oldPageIndex, selectTextViewColorId, tabSelectBackgroundColorId, true);
            //设置新的Tab颜色
            setImgAndFont(currentPage, selectedTextViewColorId, tabSelectedBackgroundColorId, false);
            oldPageIndex = currentPage;
        }
    }

    /**
     * 设置底部图片和字体颜色
     *
     * @param index
     * @param textColorId     文本颜色
     * @param isPressStateImg 是否处于被点击状态
     */
    public void setImgAndFont(int index, int textColorId, int tabBackgroundColorId, boolean isPressStateImg) {
        if (isPressStateImg) {
            if (selectTabImg != null && selectTabImg.length > 0) {
                //设置下一个Tab的背景图片
                imageViews[index].setBackgroundResource(selectTabImg[index]);
            }
        } else {
            if ((selectTabImg != null && selectTabImg.length > 0) && (selectedTabImg != null && selectedTabImg.length > 0)) {
                //设置下一个Tab的背景图片
                imageViews[index].setBackgroundResource(selectedTabImg[index]);
            }
        }
        relativeLayouts[index].setBackgroundColor(tabBackgroundColorId);
        //设置下一个Tab的字体颜色
        textViews[index].setTextColor(textColorId);
    }

    /**
     * 将数值转换为相应的DP值
     *
     * @param value
     * @return
     */
    public int NumberValueChangeDp(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

    /**
     * 重写ViewPager，禁止ViewPager滑动
     */
    public class ForbidSlideViewPager extends ViewPager {
        //是否可滚动
        private boolean isScrollable = true;

        public ForbidSlideViewPager(Context context) {
            this(context, null);
        }

        public ForbidSlideViewPager(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public boolean onTouchEvent(MotionEvent ev) {
            if (!isScrollable) {
                return true;
            }
            return super.onTouchEvent(ev);
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            return super.onInterceptTouchEvent(ev);
        }

        //设置ViewPager是否可滑动
        public void setIsScrollable(boolean isScrollable) {
            this.isScrollable = isScrollable;
        }
    }
}
