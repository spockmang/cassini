/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrestlib;

/**
 *
 * @author edasdn
 *
 * 
 * **/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class MyRESTlib {
private static CredentialsProvider provider;
private static HttpClient client;
public MyRESTlib()
{
   provider= new BasicCredentialsProvider();
             UsernamePasswordCredentials cred=new UsernamePasswordCredentials("admin","admin");
             provider.setCredentials(AuthScope.ANY, cred);

		 client = HttpClientBuilder.create()
  .setDefaultCredentialsProvider(provider)
  .build();  
}
public String myGetRequest(String myurl)
{
     String reply=null;
     try {
		HttpGet getRequest = new HttpGet(
			myurl);
		getRequest.addHeader("accept", "application/json");

		HttpResponse response = client.execute(getRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
                    
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
                       
                        
		}

		BufferedReader br = new BufferedReader(
                         new InputStreamReader((response.getEntity().getContent())));

		String output;
               
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
                        if(reply==null)
                        {
                            reply=output;
                        }
                        else
                        reply+=output+"\n";
		}

		//client.getConnectionManager().shutdown();

	  } catch (ClientProtocolException e) {

		e.printStackTrace();

	  } catch (IOException ex) {
        Logger.getLogger(MyRESTlib.class.getName()).log(Level.SEVERE, null, ex);
    }

return reply;
}
public int myPutRequest(String myurl)
{
    int res=0;
    try {
        
        HttpPut putRequest=new HttpPut(myurl);
        putRequest.addHeader("Content-Type","application/xml");
       // File flow=new File("/home/edasdn/putrequestxml/putflow.xml");
        InputStreamEntity input= new InputStreamEntity(new FileInputStream(new File("C:\\Users\\m6000628\\Documents\\temp.xml")),-1);
          putRequest.addHeader("Authorization","Basic YWRtaW46YWRtaW4=");
        input.setContentType("application/xml");
        input.setChunked(true);
        putRequest.setEntity(input);
        HttpResponse response = client.execute(putRequest);
        System.out.println(input+"\n");
         res=response.getStatusLine().getStatusCode();
        if (res==200 || res==201) {
               System.out.println("putrequest is successful\n");
		}
        else
        {
         throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode()+response.toString());   
        }
        
    } catch (UnsupportedEncodingException ex) {
        Logger.getLogger(MyRESTlib.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(MyRESTlib.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (RuntimeException ex)
    {
        System.out.println(ex);
        res=0;
        return res;
    }
    return res;
}
public int myDeleteRequest(String  myurl)
{int res=0;
int check=0;
try{
    
    HttpDelete deleteRequest=new HttpDelete(myurl);
    HttpResponse response=client.execute(deleteRequest);
    res=response.getStatusLine().getStatusCode();
    if(res==200)
    {
      System.out.println("deleterequest is successful\n");
       
      
		}   
    else
    {
     throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode()+response.toString());   
        }
    
}
catch (UnsupportedEncodingException ex) {
        Logger.getLogger(MyRESTlib.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(MyRESTlib.class.getName()).log(Level.SEVERE, null, ex);
    }
catch (RuntimeException ex)
{
    res=0;
    return res;
}
    
return res;
}
public int myPostRequest(String myurl)
{
  int res=0;
    try {
        
        HttpPost postRequest=new HttpPost(myurl);
        postRequest.addHeader("Content-Type","application/xml");
        File flow=new File("/home/edasdn/putrequestxml/putflow.xml");
        InputStreamEntity input= new InputStreamEntity(new FileInputStream(flow),-1);
          
        input.setContentType("application/xml");
        input.setChunked(true);
        postRequest.setEntity(input);
        HttpResponse response = client.execute(postRequest);
        System.out.println(input+"\n");
         res=response.getStatusLine().getStatusCode();
        if (res==204) {
               System.out.println("postrequest is successful\n");
		}
        else
        {
         throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode()+response.toString());   
        }
        
    } catch (UnsupportedEncodingException ex) {
        Logger.getLogger(MyRESTlib.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(MyRESTlib.class.getName()).log(Level.SEVERE, null, ex);
    }
    return res;   
}
 
}
    

