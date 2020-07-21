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
		client.setPhone("639717");
		List<Product> list = new ArrayList<Product>();
		Product product = new Product(); 
		product.setName("zapatos");
		product.setDescription("zapatos verdes");
		product.setPrice(2.0);
		list.add(product); 
		client.setProducts(list);
		
		
		JSONObject jsonObject = new JSONObject(client); 
		try {
			//post
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
	        
	        //viendo si la respuesta es igual
	        if(!jsonObject.similar(jsonObjectString)) {
	        	throw new Exception("Response is not the same than the post, create client have errors "); 
	        }; 
	        System.out.println("Anadiendo producto");
	        //añadiendo procto
			List<Product> list2 = new ArrayList<Product>();
			Product product2 = new Product(); 
			product2.setName("camisa");
			product2.setDescription("camisa verde");
			product2.setPrice(2.0);
			list2.add(product2); 
			JSONObject productsJson = new JSONObject(list2); 
			
			client.getProducts().addAll(list2); 
			JSONObject prueba = new JSONObject(client); 

	        System.out.println("antes de llamada - respuesta: " +list2 );
			HttpPost requestPost2 = new HttpPost("http://localhost:90/client/"+client.getName());
			StringEntity params2 = new StringEntity(productsJson.toString());
			requestPost.addHeader("content-type", "application/json");
			requestPost.setEntity(params2);
			httpClient.execute(requestPost2); 
	        System.out.println("despues llamada de productos");

	        System.out.println("segunda llamada get");
			HttpGet requestGet2 = new HttpGet("http://localhost:90/client/"+client.getName());
			CloseableHttpResponse response1 =  httpClient.execute(requestGet2); 
	        StatusLine statusLine2 = response1.getStatusLine();
	        System.out.println(statusLine2.getStatusCode() + " " + statusLine2.getReasonPhrase());
	        String responseBody2 = EntityUtils.toString(response1.getEntity(), StandardCharsets.UTF_8);
	        System.out.println("Response body: " + responseBody2);
	        JSONObject jsonObjectString2 = new JSONObject(responseBody2);
	        
	        //viendo si la respuesta es igual
	        if(!prueba.similar(jsonObjectString2)) {
	        	throw new Exception("Response is not the same than the post, the adding clients have failled"); 
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