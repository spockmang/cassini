/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrestlib;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author M6000628
 */

public class DatabseConnection {
    public void printclass()
    {
        Class c = this.getClass();         

System.out.println("Package: "+c.getPackage()+"\nClass: "+c.getSimpleName()+"\nFull Identifier: "+c.getName());
    }
    public static void main(String args[]) throws UnknownHostException
    {
       
// MongoCredential credential = MongoCredential.createMongoCRCredential(userName, database, password);
        DBCollection Cnodeinfo=null;
        DBCollection Clinkinfo=null;
        MongoClient mongoClient = new MongoClient("172.16.52.73",27017);
        //MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://172.16.52.73:27017"));
       // MongoClient client=new MongoClient();
        System.out.println(mongoClient.getDatabaseNames());
        System.out.println(mongoClient.getConnectPoint());
       DB db= mongoClient.getDB("project");
       if(db.collectionExists("newtopology"))
       {
           Cnodeinfo=db.getCollection("newtopology");
           Cnodeinfo.drop();
           
        
       }
        Cnodeinfo=db.createCollection("newtopology",new BasicDBObject());
       MyRESTlib mylib=new MyRESTlib();
       String myurl="http://172.16.52.74:8181/restconf/operational/network-topology:network-topology/topology/flow:1/";
       String res=mylib.myGetRequest(myurl);
       DBObject dbObject = (DBObject) JSON
					.parse(res);
        Cnodeinfo.insert(dbObject);
       // Deleteflow del=new Deleteflow();
      //  printclass();
     
      
       /* JSONObject topo=new JSONObject(res);
        JSONArray nodearray=topo.getJSONArray("topology").getJSONObject(0).getJSONArray("node");
        JSONObject nodeinfo=new JSONObject();
        nodeinfo.put("node", nodearray);
        DBObject dbobj=(DBObject)JSON.parse(nodeinfo.toString());
        
        for(int i=0;i<nodearray.length();i++)
        {
            JSONObject temp=nodearray.getJSONObject(i);
            System.out.println(temp.getString("node-id"));
        }
        JSONArray linkarray=topo.getJSONArray("topology").getJSONObject(0).getJSONArray("link");
        JSONObject linkinfo=new JSONObject();
        linkinfo.put("link",linkarray);
        DBObject dbobj1=(DBObject)JSON.parse(linkinfo.toString());
         for(int i=0;i<linkarray.length();i++)
        {
            JSONObject temp=linkarray.getJSONObject(i);
            String destination=temp.getJSONObject("destination").getString("dest-node");
            String Source=temp.getJSONObject("source").getString("source-node");
            System.out.println("destination:   "+destination+"\nsource:   "+Source);
        }
         
      // DBObject dbObject = (DBObject) JSON
			//		.parse(res);
       Cnodeinfo.insert(dbobj);
       System.out.println("object inserted successfully");
       
      if(db.collectionExists("linkinfo"))
       {
           Clinkinfo=db.getCollection("linkinfo");
           Clinkinfo.drop();
           
        
       }
        Clinkinfo=db.createCollection("linkinfo",new BasicDBObject());
        Clinkinfo.insert(dbobj1);*/
          System.out.println("object inserted successfully");

       
        
    }
    
}
