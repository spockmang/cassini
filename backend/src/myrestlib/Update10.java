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
import com.mongodb.util.JSON;
import static java.lang.Thread.sleep;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static myrestlib.Update30.swcount;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author M6000628
 */
public class Update10 implements Runnable {
DBCollection Cnodeinfo=null;
 MongoClient mongoClient;
  static boolean getstats=true;
    @Override
    public void run() {
       
        
        //To change body of generated methods, choose Tools | Templates.
         while(true)
         {
       
             try {
                 mongoClient = Connectiontomongodb.connect();
                 //MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://172.16.52.73:27017"));
                 // MongoClient client=new MongoClient();
                 //System.out.println(mongoClient.getDatabaseNames());
                 //System.out.println(mongoClient.getConnectPoint());
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
                 JSONObject myobj=new JSONObject(res);
                 JSONArray topology=myobj.getJSONArray("topology");
                 //JSONObject mytopo=topology.getJSONObject(0);
                 JSONArray node=(topology.getJSONObject(0)).getJSONArray("node");
                 JSONArray link=(topology.getJSONObject(0)).getJSONArray("link");
                 int nodecount=node.length();
                 if(swcount==0)
                 {
                      getInitialportStats.get();
                     swcount=nodecount;
                 }
                 else if(swcount<nodecount || swcount>nodecount)
                 {
                      getInitialportStats.get();
                     //reconfigure all the paths
                       shortestPathnetwork.createnetwork();
                     if(db.collectionExists("paths"))
                     {
                         
                         DBCollection paths=db.getCollection("paths");
                         DBCursor cursor = paths.find();
                         while(cursor.hasNext()) {
                             
                             DBObject obj=cursor.next();
                             JSONObject out=new JSONObject(JSON.serialize(obj));
                             String pathbw=out.getString("pathbw");
                             String[] sd=pathbw.split("-");
                             if(ShortestPath.checkhostandpath(sd[0],sd[1])==3)
                             {
                                 
                             List path=ShortestPath.getpath(sd[0],sd[1]);
                             ShortestPath.Storepath(path, pathbw);
                             } 
                             
                             //reconfigure the path for pathbw
                         }
                     }
                 }
                  
                 shortestPathnetwork.createnetwork();
                 
                 sleep(10000);
             } catch (InterruptedException ex) {
                 Logger.getLogger(Update10.class.getName()).log(Level.SEVERE, null, ex);
             }
        
    }
         
    }
    
    
}
