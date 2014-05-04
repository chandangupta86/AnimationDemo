package com.example.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class WebTask extends AsyncTask<String, Void, String> {
	ProgressDialog _mDialog;
	Context mContext;
	String mUrl;
	List<NameValuePair> _nameValuePair;
	public  WebTask(Context context,List<NameValuePair> nameValuePair,String urls ){
		mContext = context;
		_nameValuePair = nameValuePair;
		mUrl = urls;
	}
	public void onPreExecute() {
		//_mDialog = ProgressDialog.show(mActivity, "", "Loading");
	}

	public String doInBackground(String... params) {
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(mUrl);
		// Url Encoding the POST parameters
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(_nameValuePair));
		} catch (UnsupportedEncodingException e) {
			// writing error to Log
			e.printStackTrace();
		}

		// Making HTTP Request
		try {
			HttpResponse response = httpClient.execute(httpPost);
			// writing response to log
			String result = EntityUtils.toString(response.getEntity());
			Log.d("Http Response:",result);
			return result;
			
		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();

		}
		
		return null;
	}

	public void onPostExecute(String result) {
		Log.e("respose","respose data " + result  );
	}
	
}
