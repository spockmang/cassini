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
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgrapht.EdgeFactory;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.json.JSONObject;

/**
 *
 * @author M6000628
 */
public class ShortestPath {
    public static int checkhostandpath(String h1,String h2)
    {
       
       
         String mh1=h1;
         String mh2=h2;
         System.out.println(mh1+"\n"+mh2);
         int s=0,d=0;
         int i;
         System.out.println(shortestPathnetwork.hosts);
         for(i=0;i<shortestPathnetwork.hosts.size();i++)
         {
             String temp=shortestPathnetwork.hosts.get(i);
             if(temp.contentEquals(mh1))
             {
                 s=1;
             }
             if(temp.contentEquals(mh2))
             {
                 d=1;
             }
         }
         if(s==0 && d==0)
         {
           System.out.println("Source and destination are not found in network\n");   
           return 0;
         }
         else if(s==0 && d==1)
         {
             System.out.println("Source is not found in network\n");
             return 1;
         }
         else if(s==1 && d==0)
         {
            return 2;    
         }
         else 
             return 3;
        

        
    }
    public static List getpath(String source,String destination)
    {
        List path=null;
        if(shortestPathnetwork.mynetwork.containsVertex(source) && shortestPathnetwork.mynetwork.containsVertex(destination))
      path =DijkstraShortestPath.findPathBetween(shortestPathnetwork.mynetwork,source,destination);
      return path;
    }
    public static void Storepath(List path,String pathbw)
    {
        List paths1=new ArrayList();
        try {
            
            int sum=0;
            for( int i=0;i<path.size();i++)
            {
                if(path.get(i) instanceof DefaultWeightedEdge)
                {
                    //System.out.println(path.get(0)+"hjhjhjh");
                    DefaultWeightedEdge temp=(DefaultWeightedEdge) path.get(i);
                    sum+=shortestPathnetwork.mynetwork.getEdgeWeight(temp);
                    System.out.println(shortestPathnetwork.mynetwork.getEdgeWeight(temp));
                    Gson gs=new Gson();
                    String te=gs.toJson(temp);
                    //path.remove(i);
                    paths1.add(i, te);
                    
                }
            }
            System.out.println(sum);
            MongoClient mongoClient = new MongoClient("172.16.52.73",27017);
            Gson gson=new Gson();
            String path1=gson.toJson(paths1);
            JSONObject obj=new JSONObject();
            String sd=pathbw;
            obj.put("pathbw",sd);
            obj.put("path", path1);
            obj.put("pathsum",sum);
            DB project=mongoClient.getDB("project");
            DBCollection paths;
            if(!project.collectionExists("paths"))
            {
                paths=project.createCollection("paths",new BasicDBObject());
            }
            else
                paths=project.getCollection("paths");
            DBObject dbobj=(DBObject)JSON.parse(obj.toString());
            if(paths.findOne(new BasicDBObject().append("pathbw", sd))!=null)
            {
                System.out.println("one path  found");
                paths.remove(new BasicDBObject().append("pathbw", sd));
                ConfigurePath.deletepath(pathbw);
            }
            paths.insert(dbobj);
            ConfigurePath.SetPath(path, pathbw);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ShortestPath.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }
    
}
