package ro.pub.cs.systems.pdsd.lab03.phonedialer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneDialerActivity extends Activity {
	private class ButtonClickListener implements Button.OnClickListener {
		
		EditText edt = (EditText)findViewById(R.id.editText1);
	
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.imageButtonb){
				if(edt.getText().toString().length() != 0){
					edt.setText(edt.getText().toString().substring(0,
							edt.getText().toString().length()-1),
							TextView.BufferType.EDITABLE);
				}
			}
			else if(v.getId() == R.id.imageButtonc){
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:"+edt.getText().toString()));
				startActivity(intent);
			}
			else if(v.getId() == R.id.imageButtonh){
				finish();
			}
			else if(v.getId() == R.id.button10){
				if (edt.getText().toString().length() > 0) {
					  Intent intent = new Intent("ro.pub.cs.systems.pdsd.lab04.contactsmanager.intent.action.ContactsManagerActivity");
					  intent.putExtra("ro.pub.cs.systems.pdsd.lab04.contactsmanager.PHONE_NUMBER_KEY", edt.getText().toString());
					  startActivityForResult(intent, 2015);
					} 
				else {
					  Toast.makeText(getApplication(), getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
				}
			}
			else{
				Button x = (Button)findViewById(v.getId());
				edt.setText(edt.getText().toString() + x.getText().toString(), TextView.BufferType.EDITABLE);
			}
		}
		
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_dialer);
	
		ButtonClickListener buttonClickListener = new ButtonClickListener();
		
		Button b1 = (Button)findViewById(R.id.button1);
		b1.setOnClickListener(buttonClickListener);
		Button b2 = (Button)findViewById(R.id.button2);
		b2.setOnClickListener(buttonClickListener);
		Button b3 = (Button)findViewById(R.id.button3);
		b3.setOnClickListener(buttonClickListener);
		Button b4 = (Button)findViewById(R.id.button4);
		b4.setOnClickListener(buttonClickListener);
		Button b5 = (Button)findViewById(R.id.button5);
		b5.setOnClickListener(buttonClickListener);
		Button b6 = (Button)findViewById(R.id.button6);
		b6.setOnClickListener(buttonClickListener);
		Button b7 = (Button)findViewById(R.id.button7);
		b7.setOnClickListener(buttonClickListener);
		Button b8 = (Button)findViewById(R.id.button8);
		b8.setOnClickListener(buttonClickListener);
		Button b9 = (Button)findViewById(R.id.button9);
		b9.setOnClickListener(buttonClickListener);
		Button b0 = (Button)findViewById(R.id.button0);
		b0.setOnClickListener(buttonClickListener);
		Button bs = (Button)findViewById(R.id.buttons);
		bs.setOnClickListener(buttonClickListener);
		Button bd = (Button)findViewById(R.id.buttond);
		bd.setOnClickListener(buttonClickListener);
		Button b10 = (Button)findViewById(R.id.button10);
		b10.setOnClickListener(buttonClickListener);
		
		ImageButton bc = (ImageButton)findViewById(R.id.imageButtonc);
		bc.setOnClickListener(buttonClickListener);
		ImageButton bh = (ImageButton)findViewById(R.id.imageButtonh);
		bh.setOnClickListener(buttonClickListener);
		ImageButton bb = (ImageButton)findViewById(R.id.imageButtonb);
		bb.setOnClickListener(buttonClickListener);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.phone_dialer, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
