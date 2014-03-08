package com.platypus.cym;

import android.app.ListFragment;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class PhoneListFrag extends ListFragment {

	//empty constructor
	public PhoneListFrag() {}
	
	//Pull contacts with a phone number
	Uri mContactUri = ContactsContract.Contacts.CONTENT_URI;
	final static String[] PROJECTION = 
			{Contacts._ID, Contacts.DISPLAY_NAME_PRIMARY, Contacts.LAST_TIME_CONTACTED, Contacts.HAS_PHONE_NUMBER};
	
	final static String[] COLUMNS = 
		{Contacts.DISPLAY_NAME_PRIMARY, Contacts.LAST_TIME_CONTACTED, Contacts.HAS_PHONE_NUMBER};
	
	final static String SELECTION = Contacts.HAS_PHONE_NUMBER;
	
	final static int[] TO_IDS = {R.id.name_entry, R.id.time_entry, R.id.phone_entry};
	
	//adapters and cursor
	Cursor contactCur;
	SimpleCursorAdapter mAdapter;
	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
		ContentResolver conResolver = getActivity().getContentResolver();
		contactCur = conResolver.query(mContactUri, PROJECTION, SELECTION, null, null);
		mAdapter = 
				new SimpleCursorAdapter(getActivity(),R.layout.phone_contact_detail,contactCur,COLUMNS,TO_IDS,0);
		
		setListAdapter(mAdapter);
	}
	
}
