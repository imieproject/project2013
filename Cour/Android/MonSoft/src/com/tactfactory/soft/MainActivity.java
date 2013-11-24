package com.tactfactory.soft;

import java.util.ArrayList;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	public static final String EXTRA_VAR1 = "MaVar";
	public static final int REQUEST_1 = 5000;
	public static final int REQUEST_2 = 5001;
	
	Button button;
	Button button2;
	Button button3;
	//Button button2;
	
	private ArrayList<String> users = new ArrayList<String>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        
        button = (Button) this.findViewById(R.id.button1);
        button2 = (Button) this.findViewById(R.id.button2);
        button3 = (Button) this.findViewById(R.id.button3);

        button.setOnClickListener(this);
        //button.setOnClickListener(new MonListener());
        /*button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});*/
        
        //button2.setOnClickListener(this);
        button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				
				MainActivity.this.startActivityForResult(intent, REQUEST_1);
				
			}
		});
        
        button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				
				MainActivity.this.startActivityForResult(intent, REQUEST_2);
				
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    

	@Override
	public void onClick(View v) {
		
		Button btn = (Button) v;
		
		btn.getId();
		
        
		if (v == this.button) {
			Toast tt = Toast.makeText(this, "Hello", Toast.LENGTH_LONG);
			tt.show();
			
			User user = new User();
			user.setFirstname("Mickael");
			user.setLastname("Gaillard");
			
			//Intent intent = new Intent(this, SecondActivity.class);
			//intent.putExtra(EXTRA_VAR1, 10);
			//intent.putExtra("MonPote", user);
			
			Intent intent = new Intent(this, MaListActivity.class);
			
			this.startActivity(intent);
			//this.finish();
			
			//Intent intentUrl = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.tactfactory.com"));
			//this.startActivity(intentUrl);
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case REQUEST_1:
				Toast.makeText(this, "bt2", Toast.LENGTH_SHORT).show();
				break;
	
			default:
				break;
			}
		}
	}
	
	public void setUsers(ArrayList<String> usersList) {
		this.users = usersList;
	}
}
