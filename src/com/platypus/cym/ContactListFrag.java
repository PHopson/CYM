package com.platypus.cym;

import android.app.ListFragment;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.widget.SimpleCursorAdapter;


public class ContactListFrag extends ListFragment {

	//empty constructor
	public ContactListFrag() {}
	
	//Pull contacts with a phone number
	Uri mContactUri = ContactsContract.Contacts.CONTENT_URI;
	final static String[] PROJECTION = 
			{Contacts._ID, Contacts.LOOKUP_KEY, Contacts.DISPLAY_NAME_PRIMARY, Contacts.LAST_TIME_CONTACTED, Contacts.HAS_PHONE_NUMBER};
	
	/*final static String[] COLUMNS = 
		{Contacts.DISPLAY_NAME_PRIMARY, Contacts.LAST_TIME_CONTACTED, Contacts.HAS_PHONE_NUMBER};
	
	final static int[] TO_IDS = {R.id.name_entry, R.id.time_entry, R.id.phone_entry};*/
	String[] COLUMNS = {Contacts.DISPLAY_NAME_PRIMARY,Contacts.LAST_TIME_CONTACTED};
	int[] TO_IDS = {android.R.id.text1,android.R.id.text2};
	
	String SELECTION; 
	
	//adapters and cursor
	Cursor contactCur;
	SimpleCursorAdapter mAdapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		
		switch(getActivity().getActionBar().getSelectedNavigationIndex()) {
			
			case 1:
				SELECTION = Contacts.HAS_PHONE_NUMBER;
		}
		
		ContentResolver conResolver = getActivity().getContentResolver();
		//contactCur = conResolver.query(mContactUri, PROJECTION, SELECTION, null, Contacts.DISPLAY_NAME_PRIMARY + " ASC");
		contactCur = conResolver.query(mContactUri, PROJECTION, SELECTION, null, null);
		mAdapter = 
				new SimpleCursorAdapter(getActivity(),android.R.layout.simple_list_item_1,contactCur,COLUMNS,TO_IDS,0);
		
		setListAdapter(mAdapter);
	}
	
}
