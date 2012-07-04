package com.cs446.ComicCruiser;
import java.io.File;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import android.os.AsyncTask;


public class NetworkWorker extends AsyncTask<String, Integer, Boolean>{
	private static String urlString = "http://serverHostHame:80";
	private static HttpClient httpclient = new DefaultHttpClient();
	
	protected Boolean doInBackground(String... filePaths){
		try{
			httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			
			HttpPost httppost = new HttpPost(urlString);
			File file = new File(filePaths[0]);
			
			//TO DO: Uncomment lines below once the proper jar files are added into the lib directory
			/*MultipartEntity mpEntity = new MultipartEntity();
			mpEntity.addPart("userfile", new FileBody(file));
			
			httppost.setEntity(mpEntity);
			HttpResponse respone = httpclient.execute(httppost);*/
			
			return true;
		} catch(Exception e) {//TO DO: deal with specific exceptions
			return false;
		}
	}
}
