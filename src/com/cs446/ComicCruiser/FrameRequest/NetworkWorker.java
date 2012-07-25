package com.cs446.ComicCruiser.FrameRequest;
import java.io.File;
import java.io.StringReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.cs446.ComicCruiser.ComicRepository.Issue;
import android.os.AsyncTask;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//Asynchronous threads that request frame data from the server
public class NetworkWorker extends AsyncTask<Issue, Integer, Boolean>{
	private static String serverUrl = "http://mecdanna.servequake.com";
	
	protected Boolean doInBackground(Issue... issues){
		try{
			HttpClient httpclient = new DefaultHttpClient();
			httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			
			HttpPost httppost = new HttpPost(serverUrl);
			File file = new File(issues[0].getFilepath());
			
			MultipartEntity mpEntity = new MultipartEntity();
			mpEntity.addPart("file", new FileBody(file));
			mpEntity.addPart("fileSz", new StringBody(Long.toString(file.length())));
			
			httppost.setEntity(mpEntity);
			HttpResponse response = httpclient.execute(httppost);
			
			boolean result = addFrameDataToIssue(response, issues[0]);
			
			return result;
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
    protected void onPostExecute(Boolean result) {
        if (result == true) {
        	//TO DO: We succeeded
        } else {
        	//TO DO: We failed
        }
    }
	
	//Sets fd to be the FrameData contained within the given HttpResponse
	private boolean addFrameDataToIssue(HttpResponse response, Issue issue) {
		ArrayList<FrameData> fd = new ArrayList<FrameData>();
		
		try{
			//Put the response into an XML parser
			Document doc = convertResponseToXMLDoc(response);

	        //Iterate through the pages in the comic
	        NodeList pageList = doc.getElementsByTagName("page");
	        for(int i = 0; i < pageList.getLength(); i++) {
	        	Element pageElement = (Element) pageList.item(i);
	        	
	        	//Iterate through the frames on the page
		        NodeList frameList = pageElement.getElementsByTagName("frame");
		        for(int j = 0; j < frameList.getLength(); j++) {
		        	
		        	Node frameNode = frameList.item(j);
		        	if(frameNode.getNodeType() == Node.ELEMENT_NODE){
		        		Element frameElement = (Element)frameNode;
		        		FrameData frameObj = new FrameData();
		        		
		        		//Set the current frame's x value
		        		frameObj.setX(getIntFromFrameElement(frameElement, "x"));
		        		//Set the current frame's y value
		        		frameObj.setY(getIntFromFrameElement(frameElement, "y"));
		        		//Set the current frame's width value
		        		frameObj.setWidth(getIntFromFrameElement(frameElement, "w"));
		        		//Set the current frame's height value
		        		frameObj.setHeight(getIntFromFrameElement(frameElement, "h"));
		        		//Set the current frame's page number
		        		frameObj.setPageNum(i);
		        		//Add the current frame to the frame list
		        		fd.add(frameObj);
		        	}
		        }
	        }
	        //Set the issue's frame data
	        issue.setFrameData(fd);
	        return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	private Document convertResponseToXMLDoc(HttpResponse response) {
		try{
			HttpEntity r_entity = response.getEntity();
	        String xmlString = EntityUtils.toString(r_entity);
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = factory.newDocumentBuilder();
	        InputSource inStream = new InputSource();
	        inStream.setCharacterStream(new StringReader(xmlString));
	        Document doc = db.parse(inStream);
	        return doc;
		} catch(Exception e){
			return null;
		}
	}
	
	private int getIntFromFrameElement(Element frameElement, String attribute){
		NodeList xList = frameElement.getElementsByTagName(attribute);
		Element xElement = (Element)xList.item(0);
		NodeList textXList = xElement.getChildNodes();
		String x = ((Node)textXList.item(0)).getNodeValue();
		return Integer.parseInt(x);
	}
}
