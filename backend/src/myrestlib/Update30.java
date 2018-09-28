/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrestlib;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import static java.lang.Thread.sleep;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author M6000628
 */
public class Update30 implements Runnable {
   
    static int  swcount=0;
    static int T=0; 
     Map<String,ArrayList> mymap;
       MongoClient mongoClient;

    @Override
    public void run() {
        
          mymap=new HashMap<>();
        mongoClient = Connectiontomongodb.connect();
         
      
        //MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://172.16.52.73:27017"));
        // MongoClient client=new MongoClient();
        while(true)
        {
       // System.out.println(mongoClient.getDatabaseNames());
       // System.out.println(mongoClient.getConnectPoint());
        DB db= mongoClient.getDB("project");
      
      
        if(db.collectionExists("paths"))
        {
            try {
                DBCollection paths=db.getCollection("paths");
                DBCursor cursor = paths.find();
                while(cursor.hasNext()) {
                    
                    DBObject obj=cursor.next();
                    JSONObject out=new JSONObject(JSON.serialize(obj));
                    String pathbw=out.getString("pathbw");
                    String[] sd=pathbw.split("-");
                    int check=0;
                    int check1=0;
                     if(shortestPathnetwork.hosts==null)
                         shortestPathnetwork.findhost();
                    for(int i=0;i<shortestPathnetwork.hosts.size();i++)
                    {
                        if(sd[0].contentEquals(shortestPathnetwork.hosts.get(i)))
                        {
                            check=1;
                        }
                    }
                      for(int i=0;i<shortestPathnetwork.hosts.size();i++)
                    {
                        if(sd[1].contentEquals(shortestPathnetwork.hosts.get(i)))
                        {
                            check1=1;
                        }
                    }
                    
                    if(check==0 || check1==0)
                    {
                    paths.remove(obj);
                    mymap.remove(pathbw);
                    continue;
                    }
                    
                    
                    int sum=out.getInt("pathsum");
                   // List path=(List)out.get("path");
                   // Employee emp = (new Gson()).fromJson(dbobj.toString(), Employee.class);
                  // JSONArray path1=(JSONArray)out.get("path");
                   System.out.println(out.get("path"));
                   List path=(new Gson()).fromJson(out.get("path").toString(),java.util.List.class);
                    int pathsum=0;
                    for(int i=0;i<path.size();i++)
                    {
                       System.out.println(path.get(i).toString());
                        //JSONObject e=new JSONObject(path.get(i).toString());
                       DefaultWeightedEdge e=(new Gson()).fromJson(path.get(i).toString(),org.jgrapht.graph.DefaultWeightedEdge.class);
                     //  System.out.println("kjhjkkjkjs"+shortestPathnetwork.mynetwork.toString());
                     //System.out.println(e);
                     if(shortestPathnetwork.mynetwork==null)
                         shortestPathnetwork.createnetwork();
                       String source= shortestPathnetwork.mynetwork.getEdgeSource(e);
                       if(source==null)
                           System.out.println("jhghjhdsjhdd");
                        String dest=shortestPathnetwork.mynetwork.getEdgeTarget(e);
                       // String source=e.getString("source");
                        //String dest=e.getString("target");
                        //System.out.println(source+dest);
                         int s=0,d=0;
       for(int j=0;j<source.length();j++)
       {
           if(source.charAt(j)==':')
               s++;
       }
       for(int j=0;j<dest.length();j++)
       {
           if(dest.charAt(j)==':')
               d++;
       }
                        if(s==2 && d==2)
                        {
                            String portstats=GetPortStatistics.getstats(source,source.substring(0, source.length() - 2));
                            JSONObject portst=new JSONObject(portstats);
                            int trans=portst.getInt("transmitted");
                            int recv=portst.getInt("recieved");
                            System.out.println("intitial porstats are"+getInitialportStats.pstat.get(source));
                            int trafffic=((trans+recv)/2)-(getInitialportStats.pstat.get(source));
                            getInitialportStats.pstat.remove(source);
                            getInitialportStats.pstat.put(source,trafffic);
                            int weight=(trafffic/50);
                            pathsum+=weight;
                            
                        }
                        
                        
                    }
                    if(mymap.containsKey(pathbw))
                    {
                        ArrayList al= mymap.get(pathbw);
                        al.add(pathsum);
                    }
                    else
                    {
                        ArrayList al=new ArrayList();
                        al.add(pathsum);
                        mymap.put(pathbw, al);
                    }
                    
                    
                    
                }
                sleep(30000);
                T=T+30;
                if(T%120==0)
                {
                    //analysis of trend;
                    
                    Iterator it=mymap.entrySet().iterator();
                    while(it.hasNext())
                    {
                        Map.Entry pair=(Map.Entry)it.next();
                        int trend=CheckTrend.mytrend((ArrayList)pair.getValue());
                      
                        if(trend==1)
                        {
                            //reconfigure the path
                            if(shortestPathnetwork.mynetwork==null)
                                 shortestPathnetwork.createnetwork();
                            System.out.println("......................................................reconfiguring the path ..................................................");
                            String pathbw=(String)pair.getKey();
                             String[] sd=pathbw.split("-");
                              DBCursor cursor1 = paths.find();
                while(cursor1.hasNext()) {
                    
                    DBObject obj=cursor1.next();
                    JSONObject out=new JSONObject(JSON.serialize(obj));
                    String pathbw1=out.getString("pathbw");
                    String[] sd1=pathbw1.split("-");
                    if(sd1[0].contentEquals(sd[0]) && sd1[1].contentEquals(sd[1]))
                    {
                           
                     List path1=(new Gson()).fromJson(out.get("path").toString(),java.util.List.class);  
                      for(int i=0;i<path1.size();i++)
                    {
                       System.out.println(path1.get(i).toString());
                      
                     //  System.out.println("kjhjkkjkjs"+shortestPathnetwork.mynetwork.toString());
                    }
                       List path=ShortestPath.getpath(sd[0],sd[1]);
                       for(int i=0;i<path.size();i++)
                    {
                       System.out.println(path.get(i).toString());
                        //JSONObject e=new JSONObject(path.get(i).toString());
                      DefaultWeightedEdge e1=(DefaultWeightedEdge)path.get(i);
                       shortestPathnetwork.mynetwork.setEdgeWeight(e1, 1000000.0);
                     //  System.out.println("kjhjkkjkjs"+shortestPathnetwork.mynetwork.toString());
                    }
                         List path2=ShortestPath.getpath(sd[0],sd[1]);
                   ShortestPath.Storepath(path2, pathbw1);
                }
                            
                            //configure the path between pathw
                        }
                        //String 
                    }
                    mymap.clear();
                    
                }
            
            
            
            }
            }catch (InterruptedException ex) {
                Logger.getLogger(Update30.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
        }
        
         //To change body of generated methods, choose Tools | Templates.
    }
    
}
