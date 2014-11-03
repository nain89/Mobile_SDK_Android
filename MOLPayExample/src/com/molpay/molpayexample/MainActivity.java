package com.molpay.molpayexample;

import java.util.Random;

import com.molpay.molpaylib.MOLPayActivity;
import com.molpay.molpaylib.settings.MerchantInfo;
 






import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	//private TextView result;
	public static final int REQUEST_CODE = 1; 
	Button pay;
	RelativeLayout result;
	TextView name;
	TextView mobile;
	TextView email;
	TextView desc;
	TextView trans;
	TextView amt;
	TextView stat;
	LinearLayout main;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 
		setContentView(R.layout.activity_main);
		main=(LinearLayout) findViewById(R.id.main);
		result=(RelativeLayout) findViewById(R.id.result);
	 
		
		
        name=(TextView) findViewById(R.id.nameTxt);
        mobile=(TextView) findViewById(R.id.mobileTxt);
        email=(TextView) findViewById(R.id.emailTxt);
        desc=(TextView) findViewById(R.id.descTxt);
        trans=(TextView) findViewById(R.id.transTxt);
        amt=(TextView) findViewById(R.id.amtTxt);
        stat=(TextView) findViewById(R.id.statTxt);
       
       
       
		
		pay=(Button) findViewById(R.id.pay);
		pay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
				b.putBoolean("debug",false);
				b.putBoolean("editable", true);
				 
				intent.putExtras(b);
				startActivityForResult(intent, REQUEST_CODE);
				
			}
		});
		

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
			main.setVisibility(View.GONE);
			result.setVisibility(View.VISIBLE);
			
			
			Bundle bundle = data.getExtras().getBundle("bundle");

			if (bundle != null) {
				String amount = bundle.getString(MerchantInfo.PAY_AMOUNT);
				String transaction_id = bundle.getString(MerchantInfo.TXN_ID);
				String transaction_status = bundle
						.getString(MerchantInfo.STATUS_CODE);
				String error_desc = bundle.getString(MerchantInfo.ERR_DESC);
				String bill_name=bundle.getString(MerchantInfo.BILL_NAME);
				String bill_email=bundle.getString(MerchantInfo.BILL_EMAIL);
				String bill_mobile=bundle.getString(MerchantInfo.BILL_MOBILE);
				String bill_desc=bundle.getString(MerchantInfo.BILL_DESC);

				name.setText(bill_name);
				email.setText(bill_email);
				mobile.setText(bill_mobile);
				desc.setText(bill_desc);
				trans.setText(transaction_id);
				amt.setText("MYR "+amount);
				
				
				
				
				//String Result = "the name is "+bill_name+"\nthe mobile is " + bill_mobile +"\nthe email is " + bill_email +"\nthe description is " + bill_desc +"\nthe amount is " + amount + "\nthe transaction id is " + transaction_id ;
                
				if(transaction_status.equals("00"))
				{
					stat.setText("Success");
				}
				else if(transaction_status.equals("11"))
				{
					stat.setText("Failed");
				}
				else if(transaction_status.equals("22"))
				{
					stat.setText("Pending");
				}
				
				//result.setText(""+Result);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 private boolean isNetworkAvailable() {
		    ConnectivityManager connectivityManager 
		          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
		}
}
