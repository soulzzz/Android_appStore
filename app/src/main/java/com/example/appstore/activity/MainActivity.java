package com.example.appstore.activity;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.appstore.R;
import com.example.appstore.fragment.BaseFragment;
import com.example.appstore.util.LogUtil;
import com.example.appstore.util.MyFragmentPagerAdapter;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener
{
    public final static String TAG = "MainActivity";
    List<Fragment> fragments;
    Button btn1,btn2,btn3,btn4,btn5;
    BaseFragment recFragment,eduFragment,movieFragment,gameFragment,musicFragment;
    ImageView searchView;
    View lastFocusView;
    HashMap<View, Fragment> hashMap = new HashMap<>();
    ViewPager viewPage;
    List<Button> buttons = new ArrayList<>();
    MyFragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewPage.setAdapter(pagerAdapter);
        viewPage.setOffscreenPageLimit(1);
        viewPage.setCurrentItem(0);
        btn1.requestFocus();
        btn1.callOnClick();
    }

    private void initData() {
        fragments = new ArrayList<>();
        recFragment = new BaseFragment("RecommendFragment","推荐");
        eduFragment = new BaseFragment("EduFragment","教育");
        movieFragment = new BaseFragment("MovieFragment","电影");
        gameFragment = new BaseFragment("GameFragment","游戏");
        musicFragment = new BaseFragment("MusicFragment","音乐");
        fragments.add(recFragment);
        fragments.add(eduFragment);
        fragments.add(movieFragment);
        fragments.add(gameFragment);
        fragments.add(musicFragment);
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);
        buttons.add(btn5);
        hashMap.put(btn1, recFragment);
        hashMap.put(btn2, eduFragment);
        hashMap.put(btn3, movieFragment);
        hashMap.put(btn4, gameFragment);
        hashMap.put(btn5, musicFragment);

    }

    public void initView(){
        btn1 = findViewById(R.id.button_1);
        btn2 = findViewById(R.id.button_2);
        btn3 = findViewById(R.id.button_3);
        btn4 = findViewById(R.id.button_4);
        btn5 = findViewById(R.id.button_5);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        viewPage = findViewById(R.id.viewPage);
        searchView = findViewById(R.id.search);
        btn1.setOnFocusChangeListener(this);
        btn2.setOnFocusChangeListener(this);
        btn3.setOnFocusChangeListener(this);
        btn4.setOnFocusChangeListener(this);
        btn5.setOnFocusChangeListener(this);
        searchView.setOnFocusChangeListener(this);
        searchView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.d(TAG, "onPageSelected: lastFocusView"+lastFocusView.getId()+" buttons."+buttons.get(position).getId());
                if(lastFocusView!=null && lastFocusView instanceof Button) lastFocusView.setSelected(false);
                if(position <buttons.size()){
                    lastFocusView = buttons.get(position);
                    lastFocusView.setSelected(true);

                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public void ScaleDownView(View v2){

        if(v2!=null){
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(ObjectAnimator.ofFloat(v2, "scaleX", 1.15f, 1.0f)
                    , ObjectAnimator.ofFloat(v2, "scaleY", 1.15f, 1.0f));
            animatorSet.setDuration(100);
            animatorSet.start();
            animatorSet = null;
        }
    }
    public void ScaleUpView(View v1) {
        if (v1 != null) {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(ObjectAnimator.ofFloat(v1, "scaleX", 1.0f, 1.15f)
                    , ObjectAnimator.ofFloat(v1, "scaleY", 1.0f, 1.15f));
            animatorSet.setDuration(100);
            animatorSet.start();
            animatorSet = null;
//            v1.setScaleX(1.15f);
//            v1.setScaleY(1.15f);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        LogUtil.d(TAG,v+" "+hasFocus);
        if (hasFocus){ ;
           ScaleUpView((View) v.getParent());
           //清空 从item 滑上来时 lastFocusView的状态
           if(lastFocusView !=null)
           lastFocusView.setSelected(false);
            //滑动Button时切换 viewpage
            if(v instanceof Button){
                viewPage.setCurrentItem( buttons.indexOf(v));
            }
            ((ShimmerFrameLayout)v.getParent()).startShimmer();
            ((ShimmerFrameLayout)v.getParent()).showShimmer(true);
            ((ShimmerFrameLayout)v.getParent()).setZ(((ShimmerFrameLayout)v.getParent()).getZ()+1);
        }else {
           lastFocusView = v;
           ScaleDownView((View)lastFocusView.getParent());
            ((ShimmerFrameLayout)lastFocusView.getParent()).stopShimmer();
            ((ShimmerFrameLayout)lastFocusView.getParent()).hideShimmer();
            ((ShimmerFrameLayout)lastFocusView.getParent()).setZ(((ShimmerFrameLayout)lastFocusView.getParent()).getZ()-1);
           //从Button滑下来到Item的情况 需要把当前Button设为选中
           if (!(btn1.hasFocus() || btn2.hasFocus() ||btn3.hasFocus()||btn4.hasFocus()||btn5.hasFocus()||searchView.hasFocus() )){
               lastFocusView.setSelected(true);
           }
        }
    }

    @Override
    public void onClick(View v) {
        viewPage.setCurrentItem(fragments.indexOf(hashMap.get(v)));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}