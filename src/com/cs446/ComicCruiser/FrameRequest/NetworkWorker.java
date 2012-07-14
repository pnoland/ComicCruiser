package com.cs446.ComicCruiser.FrameRequest;
import java.io.File;
import java.io.StringReader;

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
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.os.AsyncTask;
import java.util.List;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//Asynchronous threads that request frame data from the server
public class NetworkWorker extends AsyncTask<String, Integer, Boolean>{
	private static String serverUrl = "http://serverHostHame:80";
	private List<FrameData> fd;
	
	public List<FrameData> getFD(){
		return fd;
	}
	
	//Sets fd to be the FrameData contained within the given HttpResponse
	private void extractFrameData(HttpResponse response) {
		fd = new ArrayList<FrameData>();
		
		try{
			HttpEntity r_entity = response.getEntity();
	        String xmlString = EntityUtils.toString(r_entity);
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = factory.newDocumentBuilder();
	        InputSource inStream = new InputSource();
	        inStream.setCharacterStream(new StringReader(xmlString));
	        Document doc = db.parse(inStream);  

	        NodeList pageList = doc.getElementsByTagName("page");
	        for(int i = 0; i < pageList.getLength(); i++) {
	            //TO DO:
	        	//Iterate through the frameList for each page
	        	//Create FrameData objects and add them to fd
	        }
		} catch(Exception e) {
			//TO DO: Deal with specific exceptions?
		}
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
