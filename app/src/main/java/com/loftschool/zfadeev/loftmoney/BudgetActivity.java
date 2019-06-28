package com.loftschool.zfadeev.loftmoney;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class BudgetActivity extends AppCompatActivity {
	
	private TabLayout mTabLayout;
	private ViewPager mViewPager;
	private BudgetViewPagerAdapter mViewPagerAdapter;
	
	@Override
	protected void onCreate(@Nullable final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_budget);
		
		mViewPagerAdapter = new BudgetViewPagerAdapter(getSupportFragmentManager());
		
		mTabLayout = findViewById(R.id.tab_layout);
		mViewPager = findViewById(R.id.view_pager);
		mViewPager.setAdapter(mViewPagerAdapter);
		
		mTabLayout.setupWithViewPager(mViewPager);
		mTabLayout.getTabAt(0).setText(R.string.outcome);
		mTabLayout.getTabAt(1).setText(R.string.income);
		
		mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_indictor_color));
	}
	
	static class BudgetViewPagerAdapter extends FragmentPagerAdapter {
		
		public BudgetViewPagerAdapter(final FragmentManager fm) {
			super(fm);
			
		}
		
		@Override
		public Fragment getItem(final int i) {
			switch (i) {
				case 0:
					return BudgetFragment.newInstance(R.color.dark_sky_blue);
				case 1:
					return BudgetFragment.newInstance(R.color.income_price_color);
					
			}
			return null;
		}
		
		@Override
		public int getCount() {
			return 2;
		}
	}
}