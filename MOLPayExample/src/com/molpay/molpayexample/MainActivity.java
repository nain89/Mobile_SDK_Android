package com.molpay.molpayexample;

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
	private TextView result;
	public static final int REQUEST_CODE = 1; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		result = (TextView) findViewById(R.id.txtv_result);
		
		Intent intent = new Intent(MainActivity.this,MOLPayActivity.class);
		Bundle b = new Bundle();
		b.putString("MerchantId", "MOLPay_merchant_ID");
		b.putString("AppName", "merchant_App_name");
		b.putString("VerifyKey", "1axz05ff2a818f665a57f9eca6bBe966");
		b.putString("Username", "api_user_merchantA");
		b.putString("Password", "api_pass_merchantA");

		Random r = new Random();
		int i1 = r.nextInt(500000 - 1) + 1;
		b.putString("OrderId", "GPAA" + String.valueOf(i1));

		b.putString("BillName", "Buyer Name");
		b.putString("BillDesc", "Purchase of 5 pcs of survivor kits");
		b.putString("BillMobile", "01811667788");
		b.putString("BillEmail", "user@email.com");
		b.putString("Channel", "multi");
		b.putString("Currency", "MYR");
		b.putString("Country", "MY");
		b.putFloat("Amount", 1.1f);
		b.putBoolean("debug", false);//Make this true to enable debugging
		 
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
                String appCode=bundle.getString(MerchantInfo.APP_CODE);
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
