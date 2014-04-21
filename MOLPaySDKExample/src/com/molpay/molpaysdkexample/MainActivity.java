package com.molpay.molpaysdkexample;

import java.util.Random;

import com.molpay.molpaylib.MOLPayActivity;
import com.molpay.molpaylib.settings.MerchantInfo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static final int REQUEST_CODE = 1; // Any number
	private TextView result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		result = (TextView) findViewById(R.id.txtv_result);
		

		Intent intent = new Intent(MainActivity.this,MOLPayActivity.class);
		Bundle b = new Bundle();
		b.putString("MerchantId", "MOLWallet");
		b.putString("AppName", "molwallet");
		b.putString("VerifyKey", "1a060ff52a818f665a57f9eca688e96d");
		b.putString("Username", "molpayapi");
		b.putString("Password", "M0LP@y@p1");

		Random r = new Random();
		int i1 = r.nextInt(500000 - 1) + 1;
		b.putString("OrderId", "HK" + String.valueOf(i1));

		b.putString("BillName", "MOLPay Demo");
		b.putString("BillDesc", "Purchase of 5 units of nasi lemak kits");
		b.putString("BillMobile", "55218438");
		b.putString("BillEmail", "demo@molpay.com");
		b.putString("Channel", "multi");
		b.putString("Currency", "MYR");
		b.putString("Country", "MY");
		b.putFloat("Amount", 1.1f);

		b.putString("BillName", "Tan Ah Kow");
		b.putString("BillDesc", "Purchase of 5 units of nasi lemak kits");
		b.putString("BillMobile", "+6012-2337889");
		b.putString("BillEmail", "ahkow_t@gmail.com");
		b.putString("Channel", "");
		b.putString("Currency", "MYR");
		b.putString("Country", "MY");
		b.putFloat("Amount", 1.6f);
		b.putInt("filter", 0);
		 
		intent.putExtras(b);
		startActivityForResult(intent, REQUEST_CODE);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {

			Bundle bundle = data.getExtras().getBundle("bundle");

			if (bundle != null) {
				String amount = bundle.getString(MerchantInfo.PAY_AMOUNT);
				String transaction_id = bundle.getString(MerchantInfo.TXN_ID);
				String transaction_status = bundle
						.getString(MerchantInfo.STATUS_CODE);
				String error_desc = bundle.getString(MerchantInfo.ERR_DESC);

				String Result = "the amount is " + amount + "\nthe transaction id is " + transaction_id +"\nthe error description is " + error_desc + "\nthe status is " + transaction_status;

				result.setText(""+Result);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
