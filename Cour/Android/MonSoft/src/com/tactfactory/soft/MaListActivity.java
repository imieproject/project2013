package com.tactfactory.soft;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MaListActivity extends FragmentActivity {
	
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_list);
		
		this.lv = (ListView)this.findViewById(R.id.list);
		
		DatabaseSQLite db = new DatabaseSQLite(this,"MaDB",null,1);
		
		Cursor curs = db.getAllUsersCursor();
		MonUserCursorAdapter adapter = new MonUserCursorAdapter(this, curs);
		
		this.lv.setAdapter(adapter);
	}
	
	
	public class MonUserCursorAdapter extends CursorAdapter {

		public MonUserCursorAdapter(Context context, Cursor c) {
			super(context, c);
		}
		
		@Override
		public View newView(Context ctx, Cursor cursor, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			View view = inflater.inflate(R.layout.row_item, parent,false);
			
			return view;
		}
		
		@Override
		public void bindView(View view, Context ctx, Cursor cursor) {
			TextView tv1 = (TextView) view.findViewById(R.id.text1);
			TextView tv2 = (TextView) view.findViewById(R.id.text2);
			
			tv1.setText(cursor.getString(1));
			tv2.setText(cursor.getString(2));
		}
	}
	
	public void toto () {
		String[] url = {"http://google.fr"};
		String titi = "";
	}

	/**
	 * @param url
	 * @param titi
	 */
	private void mafunct(String[] url, String titi) {
		String tutu = "tutu";
		for (String string : url) {
			titi += string;
		}
	}
}
