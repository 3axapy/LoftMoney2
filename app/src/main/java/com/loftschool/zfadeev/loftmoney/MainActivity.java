package com.loftschool.zfadeev.loftmoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
	
	public static final String AUTH_TOKEN = "auth_token";
	private Api mApi;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (!TextUtils.isEmpty(getToken())) {
			startBudgetActivity();
			return;
		}
		
		LoftApp loftApp = (LoftApp) getApplication();
		
		mApi = loftApp.getApi();
		
		Button enterButton = findViewById(R.id.enter_button);
		enterButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				callAuth();
			}
		});
	}
	
	private void callAuth() {
		String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
		
		Call<AuthResponse> authCall = mApi.auth(androidId);
		authCall.enqueue(new Callback<AuthResponse>() {
			
			@Override
			public void onResponse(
				final Call<AuthResponse> call, final Response<AuthResponse> response
			) {
				saveToken(response.body().getAuthToken());
				startBudgetActivity();
			}
			
			@Override
			public void onFailure(final Call<AuthResponse> call, final Throwable t) {

				t.printStackTrace();
			}
		});
	}
	
	private void startBudgetActivity() {
		startActivity(new Intent(MainActivity.this, BudgetActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
		finish();
		overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
	}
	
	private void saveToken(final String token) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(AUTH_TOKEN, token);
		editor.apply();
	}
	
	private String getToken() {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
		return sharedPreferences.getString(AUTH_TOKEN, "");
	}
}
