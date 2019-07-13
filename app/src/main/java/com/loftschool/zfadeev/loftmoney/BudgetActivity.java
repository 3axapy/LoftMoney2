package com.loftschool.zfadeev.loftmoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static com.loftschool.zfadeev.loftmoney.BudgetFragment.REQUEST_CODE;

public class BudgetActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
	
	private Toolbar mToolbar;
	private TabLayout mTabLayout;
	private ViewPager mViewPager;
	private BudgetViewPagerAdapter mViewPagerAdapter;
	private FloatingActionButton mFloatingActionButton;
	
	@Override
	protected void onCreate(@Nullable final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_budget);
		
		mToolbar = findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);
		
		mViewPagerAdapter = new BudgetViewPagerAdapter(getSupportFragmentManager());
		
		mTabLayout = findViewById(R.id.tab_layout);
		mViewPager = findViewById(R.id.view_pager);
		mViewPager.setAdapter(mViewPagerAdapter);
		mViewPager.addOnPageChangeListener(this);
		
		mTabLayout.setupWithViewPager(mViewPager);
		mTabLayout.getTabAt(0).setText(R.string.outcome);
		mTabLayout.getTabAt(1).setText(R.string.income);
		mTabLayout.getTabAt(2).setText(R.string.balance);
		
		mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_indictor_color));
		
		mFloatingActionButton = findViewById(R.id.fab_add_screen);
		mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				FragmentManager fragmentManager = getSupportFragmentManager();
				for (Fragment fragment : fragmentManager.getFragments()) {
					if (fragment.getUserVisibleHint()) {
						fragment.startActivityForResult(new Intent(BudgetActivity.this, AddItemActivity.class), REQUEST_CODE);
					}
				}
				
			}
		});
		
		mViewPager.setCurrentItem(getPage());
	}
	
	@Override
	public void onActionModeStarted(final android.view.ActionMode mode) {
		super.onActionModeStarted(mode);
		mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_grey_blue));
		mTabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_grey_blue));
		mFloatingActionButton.hide();
	}
	
	@Override
	public void onActionModeFinished(final android.view.ActionMode mode) {
		super.onActionModeFinished(mode);
		mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
		mTabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
		mFloatingActionButton.show();
	}
	
	@Override
	public void onPageScrolled(final int i, final float v, final int i1) {
	
	}
	
	@Override
	public void onPageSelected(final int i) {
		if (i == 2) {
			mFloatingActionButton.hide();
		} else {
			mFloatingActionButton.show();
		}
		savePage(i);
	}
	
	private void savePage(final int page) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putInt("page", page);
		editor.apply();
	}
	
	private int getPage() {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		return sharedPreferences.getInt("page", 0);
	}
	
	@Override
	public void onPageScrollStateChanged(final int i) {
	
	}
	
	static class BudgetViewPagerAdapter extends FragmentPagerAdapter {
		
		public BudgetViewPagerAdapter(final FragmentManager fm) {
			super(fm);
			
		}
		
		@Override
		public Fragment getItem(final int i) {
			switch (i) {
				case 0:
					return BudgetFragment.newInstance(FragmentType.expense);
				case 1:
					return BudgetFragment.newInstance(FragmentType.income);
				case 2:
					return BalanceFragment.newInstance();
					
			}
			return null;
		}
		
		@Override
		public int getCount() {
			return 3;
		}
	}
}
