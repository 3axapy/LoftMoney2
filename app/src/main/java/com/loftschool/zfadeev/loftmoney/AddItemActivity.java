package com.loftschool.zfadeev.loftmoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {
	
	private EditText titleEdit;
	private EditText priceEdit;
	private Button addButton;
	
	private String title;
	private String price;
	
	@Override
	protected void onCreate(@Nullable final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);
		
		titleEdit = findViewById(R.id.title_edittext);
		priceEdit = findViewById(R.id.price_edittext);
		addButton = findViewById(R.id.add_button);
		
		titleEdit.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
			
			}
			
			@Override
			public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
			
			}
			
			@Override
			public void afterTextChanged(final Editable s) {
				title = s.toString();
				changeButtonTextColor();
			}
		});
		
		priceEdit.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
			
			}
			
			@Override
			public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
			
			}
			
			@Override
			public void afterTextChanged(final Editable s) {
				price = s.toString();
				changeButtonTextColor();
			}
		});
		
		addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				setResult(Activity.RESULT_OK, new Intent().putExtra("name", title).putExtra("price", price));
				finish();
			}
		});
	}
	
	private void changeButtonTextColor() {
		if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(price)) {
			addButton.setTextColor(ContextCompat.getColor(this, R.color.add_button_text_color));
		} else {
			addButton.setTextColor(ContextCompat.getColor(this, R.color.add_button_color_inactive));
		}
	}
}