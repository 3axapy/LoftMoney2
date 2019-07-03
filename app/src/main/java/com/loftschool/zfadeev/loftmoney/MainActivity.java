package com.loftschool.zfadeev.loftmoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	public void onSaveInstanceState(final Bundle outState, final PersistableBundle outPersistentState) {
		super.onSaveInstanceState(outState, outPersistentState);
	}
	
	@Override
	protected void onRestoreInstanceState(final Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView helloWorldView = findViewById(R.id.hello_world);
		helloWorldView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				startActivity(new Intent(MainActivity.this, BudgetActivity.class));
			}
		});
		
		
		LoftApp loftApp = (LoftApp) getApplication();
		
		Api api = loftApp.getApi();
		
		String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
		
		Call<AuthResponse> authCall = api.auth(androidId);
		authCall.enqueue(new Callback<AuthResponse>() {
			
			@Override
			public void onResponse(
				final Call<AuthResponse> call, final Response<AuthResponse> response
			) {
				saveToken(response.body().getAuthToken());
			}
			
			@Override
			public void onFailure(final Call<AuthResponse> call, final Throwable t) {
				t.printStackTrace();
			}
		});
	}
	
	private void saveToken(final String token) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString("auth_token", token);
		editor.apply();
	}
}
