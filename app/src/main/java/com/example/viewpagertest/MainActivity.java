package com.example.viewpagertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.viewpagertest.transform.AccordionTransformer;
import com.example.viewpagertest.transform.BackgroundToForegroundTransformer;
import com.example.viewpagertest.transform.CubeInTransformer;
import com.example.viewpagertest.transform.CubeOutTransformer;
import com.example.viewpagertest.transform.DefaultTransformer;
import com.example.viewpagertest.transform.DepthPageTransformer;
import com.example.viewpagertest.transform.DrawFromBackTransformer;
import com.example.viewpagertest.transform.FlipHorizontalTransformer;
import com.example.viewpagertest.transform.FlipVerticalTransformer;
import com.example.viewpagertest.transform.ForegroundToBackgroundTransformer;
import com.example.viewpagertest.transform.ParallaxPageTransformer;
import com.example.viewpagertest.transform.RotateDownTransformer;
import com.example.viewpagertest.transform.RotateUpTransformer;
import com.example.viewpagertest.transform.StackTransformer;
import com.example.viewpagertest.transform.TabletTransformer;
import com.example.viewpagertest.transform.ZoomInTransformer;
import com.example.viewpagertest.transform.ZoomOutSlideTransformer;
import com.example.viewpagertest.transform.ZoomOutTranformer;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private TestPagerAdapter adapter;

    private List<Fragment> fragments;
    private LayoutInflater inflater;

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_title);
        viewPager = findViewById(R.id.viewpager);
        radioGroup = findViewById(R.id.radio_group);

        fragments = new ArrayList<>();
        inflater = LayoutInflater.from(this);

        TestFragment testFragment = new TestFragment();
        TestFragment2 testFragment2 = new TestFragment2();
        TestFragment3 testFragment3 = new TestFragment3();
        TestFragment4 testFragment4 = new TestFragment4();
        TestFragment5 testFragment5 = new TestFragment5();
        TestFragment6 testFragment6 = new TestFragment6();

        fragments.add(testFragment);
        fragments.add(testFragment2);
        fragments.add(testFragment3);
        fragments.add(testFragment4);
        fragments.add(testFragment5);
        fragments.add(testFragment6);

        adapter = new TestPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        Log.i("LHD", "tabLayout.getTabCount() = " + tabLayout.getTabCount());
        for (int i = 0; i < fragments.size(); i++) {
            TabLayout.Tab tab = tabLayout.newTab();

            //方式1
//            View view = inflater.inflate(R.layout.view_tab, null);
//            TextView textView = view.findViewById(R.id.tv_tab);
//            textView.setText("tab" + i);
//
//            tab.setCustomView(view);

            //方式2
            View view = tab.setCustomView(R.layout.view_tab).getCustomView();
            TextView textView = view.findViewById(R.id.tv_tab);
            textView.setText("tab" + i);

            tabLayout.addTab(tab);
        }

        //查看setupWithViewPager源码发现，在ViewPager适配器不为空的情况下，最终会调用populateFromPagerAdapter()方法
        //这么写代码只能适应文字的选项卡样式，如果在Tab中有自定义的view，那么一旦调用谷歌官方的Android SDK中的：
        //tabLayout.setupWithViewPager(viewPager);
        //意味着放弃自定义view，TabLayout内部在添加Tab时候，会自动从ViewPager适配器PagerAdapter的getPageTitle()获得一个字符串然后作为一个TextView添加到TabLayout中，换句话说，开发者自定义的view将被删掉然后完全失效。
        //对于这个问题，谷歌Android官方在此（https://code.google.com/p/android/issues/detail?id=180667 ）给出了解释：
//        tabLayout.setupWithViewPager(viewPager);

        //方式1
//        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //方式2
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_accordion:
                        viewPager.setPageTransformer(false, new AccordionTransformer());
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.radio_bg_to_fg:
                        viewPager.setPageTransformer(false, new BackgroundToForegroundTransformer());
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.radio_cube_in:
                        viewPager.setPageTransformer(false, new CubeInTransformer());
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.radio_cube_out:
                        viewPager.setPageTransformer(false, new CubeOutTransformer());
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.radio_default:
                        viewPager.setPageTransformer(false, new DefaultTransformer());
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.radio_depth:
                        viewPager.setPageTransformer(false, new DepthPageTransformer());
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.radio_draw_from_back:
                        viewPager.setPageTransformer(false, new DrawFromBackTransformer());
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.radio_flip_horizon:
                        viewPager.setPageTransformer(false, new FlipHorizontalTransformer());
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.radio_flip_vertical:
                        viewPager.setPageTransformer(false, new FlipVerticalTransformer());
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.radio_1:
                        viewPager.setPageTransformer(false, new ForegroundToBackgroundTransformer());
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.radio_2:
                        viewPager.setPageTransformer(false, new ParallaxPageTransformer(R.id.img_bg));
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.radio_3:
                        viewPager.setPageTransformer(false, new RotateDownTransformer());
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.radio_4:
                        viewPager.setPageTransformer(false, new RotateUpTransformer());
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.radio_5:
                        viewPager.setPageTransformer(false, new StackTransformer());
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.radio_6:
                        viewPager.setPageTransformer(false, new TabletTransformer());
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.radio_7:
                        viewPager.setPageTransformer(false, new ZoomInTransformer());
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.radio_8:
                        viewPager.setPageTransformer(false, new ZoomOutSlideTransformer());
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.radio_9:
                        viewPager.setPageTransformer(false, new ZoomOutTranformer());
                        adapter.notifyDataSetChanged();
                        break;

                }
            }
        });
    }


}
