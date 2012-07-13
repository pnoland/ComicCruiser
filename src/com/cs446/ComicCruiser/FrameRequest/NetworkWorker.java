package com.cs446.ComicCruiser.FrameRequest;
import java.io.File;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import android.os.AsyncTask;

//Asynchronous threads that request frame data from the server
public class NetworkWorker extends AsyncTask<String, Integer, Boolean>{
	private static String serverUrl = "http://serverHostHame:80";
	private FrameData fd;
	
	public FrameData getFD(){
		return fd;
	}
	
	//Sets fd to be the FrameData contained within the given HttpResponse
	private void extractFrameData(HttpResponse response) {
		fd = new FrameData();
		//TO DO: Extract data from response
		//fd.setX(response.x)
		//fd.setY(response.y)
		//fd.setWidth(response.width)
		//fd.setHeight(response.height)
	}
	
	protected Boolean doInBackground(String... filePaths){
		try{
			HttpClient httpclient = new DefaultHttpClient();
			httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			
			HttpPost httppost = new HttpPost(serverUrl);
			File file = new File(filePaths[0]);
			
			MultipartEntity mpEntity = new MultipartEntity();
			mpEntity.addPart("file", new FileBody(file));
			mpEntity.addPart("fileSz", new StringBody(Long.toString(file.length())));
			
			httppost.setEntity(mpEntity);
			HttpResponse response = httpclient.execute(httppost);
			
			extractFrameData(response);
			
			return true;
		} catch(Exception e) {//TO DO: deal with specific exceptions?
			return false;
		}
	}
}
