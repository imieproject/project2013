package com.tactfactory.soft;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends Activity  {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_second);
        
        this.setResult(Activity.RESULT_CANCELED);
        
        int result = this.getIntent().getIntExtra(MainActivity.EXTRA_VAR1, -10);
        User resultUser =  (User) this.getIntent().getSerializableExtra("MonPote");
        
        DatabaseSQLite db = new DatabaseSQLite(this,"MaDB",null,1);
        
        // Inserting Users
        db.insertUser(new User("toto", "dodo"));       
        db.insertUser(new User("titi", "didi"));
        db.insertUser(new User("tata", "dada"));
        db.insertUser(new User("tutu", "dudu"));
         
        User toto = db.getUser(2);
        // Get all users from database
        ArrayList<User> users = db.getUsers();
        
        //LoadTask task = new LoadTask(this);
        //task.execute(new User("toto", "dodo"));
        
        UrlTask urlTask = new UrlTask(this);
        urlTask.execute();
        
        this.setResult(Activity.RESULT_OK);
    }
    
    
    private class LoadTask extends AsyncTask<User, Void, Boolean> {
    	private Context ctx;
    	private ProgressDialog progress;
    	
    	public LoadTask(Context ctx) {
			this.ctx = ctx;
		}
    	
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			//this.progress = new ProgressDialog(this.ctx);
			//this.progress.show();
		}

		@Override
		protected Boolean doInBackground(User... params) {
			Boolean result = false;
			
			for (User user : params) {
				DatabaseSQLite db = new DatabaseSQLite(this.ctx,"MaDB",null,1);
		        
		        // Inserting Users
		        if (db.insertUser(user) != -1){
		        	result = true;
		        };
			}
			
			return result;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			
			//progress.dismiss();
			
			String text = "KO";
			
			if (result) {
				text = "OK";
			}
			
			Toast.makeText(this.ctx, 
					text,
					Toast.LENGTH_SHORT).show();
		}
    }
    
    private class UrlTask extends AsyncTask<Void, Void, String> {
    	private Context ctx;
    	
    	public UrlTask(Context ctx) {
			this.ctx = ctx;
		}
    	
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		
		@Override
		protected String doInBackground(Void... params) {
			String result = null;

			HttpURLConnection urlConn = null;
			
			try {
				URL url = new URL("http://www.tactfactory.com/feed/");

				urlConn = (HttpURLConnection) url.openConnection();

				urlConn.setDoOutput(true);
				urlConn.setChunkedStreamingMode(0);

				InputStream is = new BufferedInputStream(
						urlConn.getInputStream());

				StringBuilder sb = new StringBuilder();
				BufferedReader r = new BufferedReader(
						new InputStreamReader(is), 1000);
				for (String line = r.readLine(); line != null; line = r
						.readLine()) {
					sb.append(line);
				}
				is.close();
				result = sb.toString();

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				urlConn.disconnect();
			}
			
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			String text = "KO";
			
			if (result != null) {
				Toast.makeText(this.ctx, 
						result,
						Toast.LENGTH_LONG).show();
			}
			
			//((MainActivity) this.ctx).setUsers(usersList);
		}
	}
}
