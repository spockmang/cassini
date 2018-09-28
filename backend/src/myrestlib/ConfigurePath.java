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

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.StringUtils;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.json.JSONObject;

/**
 *
 * @author M6000628
 */
public class ConfigurePath {
    public static int flowid=10;
public static void SetPath(List path,String pathbw)
{
    System.out.println("setting the path");
   int i;
   String[] sd=pathbw.split("-");
   String s1=sd[0].substring(5);
   String d1=sd[1].substring(5);
   System.out.println(s1+"   "+d1);
   System.out.println(path.size());
   List fdelpath=new ArrayList();
   int k;
   for(i=0;i<path.size();i++)
   {k=i;
       DefaultWeightedEdge e=(DefaultWeightedEdge)path.get(i);
       String source=shortestPathnetwork.mynetwork.getEdgeSource(e);
       String dest=shortestPathnetwork.mynetwork.getEdgeTarget(e);
       System.out.println(source+"     "+dest);
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
       
       if(s==2)
       {
           if(d==1)
           {
               DefaultWeightedEdge e1=(DefaultWeightedEdge)path.get(i+1);
               String port=shortestPathnetwork.mynetwork.getEdgeTarget(e1);
               int c=0;
               for(int j=0;j<port.length();j++)
       {
           if(port.charAt(j)==':')
               c++;
       }
               if(c==1)
                   port=shortestPathnetwork.mynetwork.getEdgeSource(e1);
                  String temp="";
        for( i=port.length()-1;port.charAt(i)!=':';i--)
        {
            
        }
        i++;
        while(i<port.length())
        {
            temp+=port.charAt(i);
            i++;
        }
               JSONObject config=new JSONObject();
               config.put("out-port",temp);
               config.put("sourcemac",s1);
               config.put("destmac",d1);
               config.put("switchid",dest);
               config.put("tableid","0");
               config.put("flowid",Integer.toString(flowid));
               config.put("priority",0);
               String res=Pathflow.addpathflow(config);
               System.out.print("jjkkjkjd"+res);
               Flowobject flow=new Flowobject(dest,Integer.toString(flowid),"0");
               Gson gs=new Gson();
               String fl=gs.toJson(flow);
               fdelpath.add(fl);
               
               
               
           }
       }
       else if(s==1)
       {
           if(d==2)
           {
               System.out.println("creating flow jjkdskjdkj");
               DefaultWeightedEdge e1=(DefaultWeightedEdge)path.get(i+1);
               String port=shortestPathnetwork.mynetwork.getEdgeTarget(e1);
                 int c=0;
               for(int j=0;j<port.length();j++)
       {
           if(port.charAt(j)==':')
               c++;
       }
               if(c==1)
                   port=shortestPathnetwork.mynetwork.getEdgeSource(e1);
               
                  String temp="";
        for( i=port.length()-1;port.charAt(i)!=':';i--)
        {
            
        }
        i++;
        while(i<port.length())
        {
            temp+=port.charAt(i);
            i++;
        }
        //System.out.println();
               JSONObject config=new JSONObject();
               config.put("out-port",temp);
               config.put("sourcemac",s1);
               config.put("destmac",d1);
               config.put("switchid",source);
               config.put("tableid","0");
               config.put("flowid",Integer.toString(flowid));
               config.put("priority","0");
               String res=Pathflow.addpathflow(config);
               System.out.print("jjkkjkjd"+res);
               Flowobject flow=new Flowobject(source,Integer.toString(flowid),"0");
                Gson gs=new Gson();
               String fl=gs.toJson(flow);
               fdelpath.add(fl);
               
               
           }
       }
       i=k;
       
   }
       
   
   
    MongoClient cli=Connectiontomongodb.connect();
    DB project=cli.getDB("project");
    DBCollection delpath;
    if(!project.collectionExists("fdelpath"))
    {
        delpath=project.createCollection("fdelpath",new BasicDBObject());
    }
    else
        delpath=project.getCollection("fdelpath");
      JSONObject obj=new JSONObject();
           String fdel=(new Gson()).toJson(fdelpath);
            obj.put("pathbw",pathbw);
            obj.put("path", fdel);
                DBObject dbobj=(DBObject)JSON.parse(obj.toString());
                delpath.insert(dbobj);
                flowid++;
    
}
public static void deletepath(String pathbw)
{
    System.out.println("delete path is called\n");
   MongoClient cli=Connectiontomongodb.connect();
    DB project=cli.getDB("project");
    DBCollection delpath=null;
    if(project.collectionExists("fdelpath"))
    {
      delpath=project.getCollection("fdelpath");   
    }
  else
    {System.out.println("can not delete path");
        return;
    }
       
        DBObject path = delpath.findOne(new BasicDBObject().append("pathbw",pathbw));
        System.out.println(path);
        if(path!=null)
        {
            List fdelpath=(new Gson()).fromJson(path.get("path").toString(),java.util.List.class);
            //System.out.println(fdelpath);
            for(int i=0;i<fdelpath.size();i++)
            {
                Flowobject temp=(new Gson()).fromJson(fdelpath.get(i).toString(),Flowobject.class);
               JSONObject te=new JSONObject();
               te.put("switchid1",temp.getswitchid());
               te.put("tableid1",temp.gettableid());
               te.put("flowid1",temp.getflowid());
               String res=Deleteflow.deletemyflow(te);
               System.out.println(res);
            }
            delpath.remove(path);
            
        }
        
    
}

    
}
