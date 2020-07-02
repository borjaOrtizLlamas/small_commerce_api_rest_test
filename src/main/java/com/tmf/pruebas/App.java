package com.tmf.pruebas;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		String json ="{\"name\":\"xe60693\",\"products\":[{\"name\": \"mueble\",\"precio\": \"21311\"},"
				+ "{\"name\": \"casa\",\"precio\": \"21311\"},{\"name\": \"pedro\",\"precio\": \"21311\"}]}"; 
		
		try {
			HttpPost requestPost = new HttpPost("springboot:8080/client");
			StringEntity params = new StringEntity(json);
			requestPost.addHeader("content-type", "application/json");
			requestPost.setEntity(params);
			httpClient.execute(requestPost);

			HttpGet requestGet = new HttpGet("springboot:8080/client");
			CloseableHttpResponse response =  httpClient.execute(requestGet); 
	        StatusLine statusLine = response.getStatusLine();
	        System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
	        String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
	        System.out.println("Response body: " + responseBody);
	        if(!json.toLowerCase().trim().equals(responseBody.toLowerCase().trim())) {
	        	throw new Exception("Response is not the same than the post"); 
	        }; 
	        
	        
		} catch (Exception ex) {
			throw ex;
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				throw e; 
			}
		}
	}
}
