package com.tmf.pruebas;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.tfm.dto.Client;
import com.tfm.dto.Product;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();


		Client client = new Client(); 
		client.setName("xe60693");
		List<Product> list = new ArrayList<Product>();
		Product product = new Product(); 
		product.setName("name");
		product.setDescription("name");
		product.setPrice("2.0");
		list.add(product); 
		client.setProducts(list);
		
		
		JSONObject jsonObject = new JSONObject(client); 
		try {
			HttpPost requestPost = new HttpPost("http://localhost:90/client");
			StringEntity params = new StringEntity(jsonObject.toString());
			requestPost.addHeader("content-type", "application/json");
			requestPost.setEntity(params);
			httpClient.execute(requestPost);

			HttpGet requestGet = new HttpGet("http://localhost:90/client/"+client.getName());
			CloseableHttpResponse response =  httpClient.execute(requestGet); 
	        StatusLine statusLine = response.getStatusLine();
	        System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
	        String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
	        System.out.println("Response body: " + responseBody);
	        JSONObject jsonObjectString = new JSONObject(responseBody);
	        if(!jsonObject.similar(jsonObjectString)) {
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