package com.loftschool.zfadeev.loftmoney;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.loftschool.zfadeev.loftmoney.MainActivity.AUTH_TOKEN;

public class BalanceFragment extends Fragment {
	
	private DiagramView mDiagramView;
	private TextView mTotalMoney;
	private TextView mExpenseMoney;
	private TextView mIncomeMoney;
	private Api mApi;
	
	public static BalanceFragment newInstance() {
		BalanceFragment fragment = new BalanceFragment();
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(
		@NonNull final LayoutInflater inflater,
		@Nullable final ViewGroup container,
		@Nullable final Bundle savedInstanceState
	) {
		View fragmentView = inflater.inflate(R.layout.fragment_balance, container, false);
		
		mDiagramView = fragmentView.findViewById(R.id.diagramView);
		mTotalMoney = fragmentView.findViewById(R.id.total_money);
		mExpenseMoney = fragmentView.findViewById(R.id.expense_money);
		mIncomeMoney = fragmentView.findViewById(R.id.income_money);
		
		return fragmentView;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mApi = ((LoftApp) getActivity().getApplication()).getApi();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		loadBalance();
		
	}
	
	private void loadBalance() {
		final String token = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(AUTH_TOKEN, "");
		Call<BalanceResponse> balanceResponseCall = mApi.getBalance(token);
		balanceResponseCall.enqueue(new Callback<BalanceResponse>() {
			@Override
			public void onResponse(
				final Call<BalanceResponse> call, final Response<BalanceResponse> response
			) {
				mTotalMoney.setText(getString(R.string.price_template, String.valueOf(response.body().getTotalIncome() - response.body().getTotalExpence())));
				mExpenseMoney.setText(getString(R.string.price_template, String.valueOf(response.body().getTotalExpence())));
				mIncomeMoney.setText(getString(R.string.price_template, String.valueOf(response.body().getTotalIncome())));
				
				mDiagramView.update(response.body().getTotalIncome(), response.body().getTotalExpence());
			}
			
			@Override
			public void onFailure(final Call<BalanceResponse> call, final Throwable t) {
			
			}
		});
	}
}
