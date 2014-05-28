package com.platypus.cym;

import android.app.ListFragment;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.ListView;
import android.widget.TextView;

public class ContactListFrag extends ListFragment {

	//empty constructor
	public ContactListFrag() {}
	
	Uri mContactUri = ContactsContract.Contacts.CONTENT_URI;
	Uri mEmailUri = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
	
	final static String[] PROJECTION = 
			{Contacts._ID, Contacts.LOOKUP_KEY, Contacts.DISPLAY_NAME_PRIMARY, Contacts.LAST_TIME_CONTACTED, Contacts.HAS_PHONE_NUMBER};
	final static String SORT = Contacts.DISPLAY_NAME_PRIMARY + " ASC";
	
	final static String[] COLUMNS = {Contacts.DISPLAY_NAME_PRIMARY, Contacts.LAST_TIME_CONTACTED};
	final static int[] TO_IDS = {R.id.name_entry, R.id.time_entry};
	
	
	String SELECTION;
	Cursor mContactCur;
	SimpleCursorAdapter mAdapter;
	ContentResolver conResolver;
	
	//Format Contacts.LAST_TIME_CONTACTED to something readable
	ViewBinder lastContactedBind = new ViewBinder() {
		
		public boolean setViewValue(View view, Cursor cursor, int columnIndex){
			
			if (columnIndex == 3) {
				TextView tView = (TextView)view;
				long milli = cursor.getLong(columnIndex);
				
				if (milli == 0) {
					tView.setText("Never contacted");
				} else {
					//Show time since contact was last contacted in days
					long timeDiff = (System.currentTimeMillis()-milli)/DateUtils.DAY_IN_MILLIS; 
					
					if (timeDiff < 1) {
						tView.setText("Last contacted: Less than a day ago");
					} else if (timeDiff == 1){
						tView.setText("Last contacted: A day ago");
					}	else if (timeDiff >365) {
						tView.setText("Last contacted: Over a year ago");
					} else {
						String fDays = String.valueOf(timeDiff);
						tView.setText("Last contacted: " + fDays + " days ago");
					}
				}
				return true;
			}
			
			return false;
		}

	};
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
		 conResolver = getActivity().getContentResolver();
		 
	}
	
	public void updateList(String filter){
		
		//Change query based on selection from Action Bar
		if (filter == getString(R.string.contact_selection2)){
			SELECTION = Contacts.HAS_PHONE_NUMBER;
		} else {
			SELECTION = null;
		}

		mContactCur = conResolver.query(mContactUri, PROJECTION, SELECTION, null, SORT);
		
		mAdapter = 
				new SimpleCursorAdapter(getActivity(),R.layout.list_contact_detail,mContactCur,COLUMNS,TO_IDS,0);
		
		mAdapter.setViewBinder(lastContactedBind);
		setListAdapter(mAdapter);
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		
	}
	
}
