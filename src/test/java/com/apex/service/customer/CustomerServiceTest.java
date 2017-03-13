package com.apex.service.customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerServiceTest {
	 @Test
	  public void testwithGetexistingser() throws ClientProtocolException, IOException {
		   
		 String url="http://www.thomas-bayer.com//sqlrest/CUSTOMER/1";
		  
//		  create client
		  
		  HttpResponse response = SendgetRequest(url);
		  
//		 Assert status code
		int statuscode= response.getStatusLine().getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(statuscode, 200);
//		 Assert Status message
		String statusMessage= response.getStatusLine().getReasonPhrase();
		System.out.println(statusMessage);
		Assert.assertEquals(statusMessage, "OK");
//		 Assert Status data
		BufferedReader rd = new BufferedReader(
		        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line+"\n");
		}
		System.out.println(result.toString());
		String responseString=result.toString();
		Assert.assertTrue(responseString.contains("<FIRSTNAME>Susanne</FIRSTNAME>"));
//		 Assert correct tags
	  }
	 
	 
	 
	 @Test
	  public void testwithGetusernotfound() throws ClientProtocolException, IOException {
		 String url="http://www.thomas-bayer.com//sqlrest/CUSTOMER/1000"; 
		  HttpResponse response = SendgetRequest(url); 
//		  validate
		  
//		 Assert status code
		int statuscode= response.getStatusLine().getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(statuscode, 404);
//		 Assert Status message
		String statusMessage= response.getStatusLine().getReasonPhrase();
		System.out.println(statusMessage);
		Assert.assertEquals(statusMessage, "Not Found");
//		
	  }



	private HttpResponse SendgetRequest(String url ) throws IOException, ClientProtocolException {
		
		   
		  
//		  create client
		  
		  HttpClient client=HttpClientBuilder.create().build();
		  
//		  create the request method
		  HttpGet requestMsg=new HttpGet(url);
//		  send and receive the response
		 HttpResponse response= client.execute(requestMsg);
		return response;
	}
	  public void TestPOST(){
		  
		  
	  }
	 public void TestUPDATE(){
		  
	  }
	 public void TestDELETE(){
		  
	 }
	  
	  public void testwithnonexistingser() {
		  
	  }
	  public void testwith400errorcode() {
		  
	  }
	  public void testwith500errorcode () {
	  } 
	}