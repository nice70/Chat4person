package com.sen.chat4person.ui;

import java.util.ArrayList;
import java.util.List;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sen.chat4person.R;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private ViewPager mViewPager;
	private ImageView mTabline;
	private TextView mChatTextView;
	private TextView mFriendTextView;
	private TextView mContactTextView;
	private int screenW_3;
	private List<Fragment> mFrags;
	private FragmentPagerAdapter mAdapter;
	private LinearLayout mchatLayout , mfriendlayout , mcontactlayout;
	private int currIndex = 0;
	private ChatFragment chatFragment;
	private FriendFragment friendFragment;
	private ContactFragment contactFragment;
	private List<TextView> tvs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initTabLine();
		initView();
	}

	private void initView() {
		mChatTextView = (TextView) findViewById(R.id.main_tv_chat);
		mFriendTextView = (TextView) findViewById(R.id.main_tv_friend);
		mContactTextView = (TextView) findViewById(R.id.main_tv_contact);
		mchatLayout = (LinearLayout) findViewById(R.id.ll_chat);
		mfriendlayout = (LinearLayout) findViewById(R.id.ll_friend);
		mcontactlayout = (LinearLayout) findViewById(R.id.ll_contact);
		mViewPager = (ViewPager) findViewById(R.id.mian_viewpager);
		
		mchatLayout.setOnClickListener(this);
		mfriendlayout.setOnClickListener(this);
		mcontactlayout.setOnClickListener(this);
		
		tvs = new ArrayList<TextView>();
		tvs.add(mChatTextView);
		tvs.add(mFriendTextView);
		tvs.add(mContactTextView);
		mFrags = new ArrayList<Fragment>();
		chatFragment = new ChatFragment();
		friendFragment = new FriendFragment();
		contactFragment = new ContactFragment();
		mFrags.add(chatFragment);
		mFrags.add(friendFragment);
		mFrags.add(contactFragment);
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public int getCount() {
				return mFrags.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				return mFrags.get(arg0);
			}
		};
		mViewPager.setAdapter(mAdapter);
		mViewPager.setCurrentItem(0);
		mViewPager.setOnPageChangeListener(new MOnPageChangeListener());
	}

	private void initTabLine() {
		mTabline = (ImageView) findViewById(R.id.main_iv_tabline);
		Display display = getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		screenW_3 = outMetrics.widthPixels / 3;
		LayoutParams lp = (LayoutParams) mTabline.getLayoutParams();
		lp.width = screenW_3;
		mTabline.setLayoutParams(lp);
	}

	class MOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = new TranslateAnimation(screenW_3 * currIndex, screenW_3
					* arg0, 0, 0);
			currIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(300);
			mTabline.startAnimation(animation);
			resetTextView();
			tvs.get(arg0).setTextColor(Color.parseColor("#48D1CC"));
		}

	}

	protected void resetTextView() {
		mChatTextView.setTextColor(Color.BLACK);
		mFriendTextView.setTextColor(Color.BLACK);
		mContactTextView.setTextColor(Color.BLACK);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mFrags = null;
		tvs = null;
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.ll_chat:
			mViewPager.setCurrentItem(0);
			break;
		case R.id.ll_friend:
			mViewPager.setCurrentItem(1);
			break;
		case R.id.ll_contact:
			mViewPager.setCurrentItem(2);
			break;
		}
	}

}
