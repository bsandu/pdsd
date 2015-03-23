package ro.pub.cs.systems.pdsd.lab04.contactsmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BasicDetailsFragment extends Fragment {
	
	OnClickListener myClickListener = new ButtonClickListener();
	
	private class ButtonClickListener implements Button.OnClickListener {

		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.button1){
				FragmentManager fragmentManager = getActivity().getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				AdditionalDetailsFragment additionalDetailsFragment = (AdditionalDetailsFragment)fragmentManager.findFragmentById(R.id.frameLayout2);
				if (additionalDetailsFragment == null) {
				  fragmentTransaction.add(R.id.frameLayout2, new AdditionalDetailsFragment());
				  ((Button)v).setText(getActivity().getResources().getString(R.string.hide_additional_details));
				  fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
				} else {
				  fragmentTransaction.remove(additionalDetailsFragment);
				  ((Button)v).setText(getActivity().getResources().getString(R.string.show_additional_details));
				  fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_EXIT_MASK);
				}
				fragmentTransaction.commit();
			}
			if(v.getId() == R.id.button3){
				String name = ((EditText)getActivity().findViewById(R.id.editText1)).getText().toString();
				String phone = ((EditText)getActivity().findViewById(R.id.editText2)).getText().toString();
				String email = ((EditText)getActivity().findViewById(R.id.editText3)).getText().toString();
				String address = ((EditText)getActivity().findViewById(R.id.editText4)).getText().toString();
				String jobTitle = null;
				String company = null;
				String website = null;
				String im = null;
				EditText JobTitle = (EditText)getActivity().findViewById(R.id.editText5);
				EditText Company = (EditText)getActivity().findViewById(R.id.editText6);
				EditText Website = (EditText)getActivity().findViewById(R.id.editText7);
				EditText Im = (EditText)getActivity().findViewById(R.id.editText8);
				
				
				
				Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
				intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
				if (name != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
				}
				if (phone != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
				}
				if (email != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
				}
				if (address != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
				}
				
				if(JobTitle != null)
				{
					jobTitle = JobTitle.getText().toString();
					company = Company.getText().toString();
					website = Website.getText().toString();
					im = Im.getText().toString();
				}
				
				if (jobTitle != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
				}
				if (company != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
				}
				ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
				if (website != null) {
				  ContentValues websiteRow = new ContentValues();
				  websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
				  websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
				  contactData.add(websiteRow);
				}
				if (im != null) {
				  ContentValues imRow = new ContentValues();
				  imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
				  imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
				  contactData.add(imRow);
				}
				intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
				//getActivity().startActivity(intent);
				getActivity().startActivityForResult(intent, 2014);
			}
			if(v.getId() == R.id.button2){
				getActivity().setResult(Activity.RESULT_CANCELED, new Intent());
				getActivity().finish();
			}
		}
		
	}
	
	@Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    Button b1 = (Button)getActivity().findViewById(R.id.button1);
	    b1.setOnClickListener(myClickListener);
	    Button b2 = (Button)getActivity().findViewById(R.id.button2);
	    b2.setOnClickListener(myClickListener);
	    Button b3 = (Button)getActivity().findViewById(R.id.button3);
	    b3.setOnClickListener(myClickListener);
	    Intent intent = getActivity().getIntent();
	    if (intent != null) {
	    	  String phone = intent.getStringExtra("ro.pub.cs.systems.pdsd.lab04.contactsmanager.PHONE_NUMBER_KEY");
	    	  if (phone != null) {
	    		  ((EditText)getActivity().findViewById(R.id.editText2)).setText(phone);
	    	  } else {
	    	    Activity activity = getActivity();
	    	    Toast.makeText(activity, activity.getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
	    	  }
	    	} 
	  }
	
	
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    return inflater.inflate(R.layout.fragment_basic_details, container, false);
	  }
}
