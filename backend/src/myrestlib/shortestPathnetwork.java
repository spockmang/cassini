/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrestlib;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author M6000628
 */
public class shortestPathnetwork {
    public static SimpleWeightedGraph<String, DefaultWeightedEdge> mynetwork;
//public static String hosts[];
public static ArrayList<String> hosts;
public  static void findhost()
{
    hosts=new ArrayList<String>();
        MyRESTlib mylib=new MyRESTlib();
        String myurl="http://172.16.52.74:8181/restconf/operational/network-topology:network-topology/topology/flow:1/";
        String res=mylib.myGetRequest(myurl);
        JSONObject myobj=new JSONObject(res);
        JSONArray topology=myobj.getJSONArray("topology");
        //JSONObject mytopo=topology.getJSONObject(0);
        JSONArray node=(topology.getJSONObject(0)).getJSONArray("node");
        JSONArray link=(topology.getJSONObject(0)).getJSONArray("link");
        int i;
        for(i=0;i<node.length();i++)
        {
            JSONObject temp=node.getJSONObject(i);
            String nodeid=temp.getString("node-id");
            if(nodeid.charAt(0)=='h')
            {
                hosts.add(nodeid);
            }
        }
}
//public static 
   public static void createnetwork()
   {
        mynetwork =
            new SimpleWeightedGraph<String, DefaultWeightedEdge>
            (DefaultWeightedEdge.class);
        if(hosts==null)
        hosts=new ArrayList<String>();
        else
            hosts.clear();;
        MyRESTlib mylib=new MyRESTlib();
        String myurl="http://172.16.52.74:8181/restconf/operational/network-topology:network-topology/topology/flow:1/";
        String res=mylib.myGetRequest(myurl);
        JSONObject myobj=new JSONObject(res);
        JSONArray topology=myobj.getJSONArray("topology");
        //JSONObject mytopo=topology.getJSONObject(0);
        JSONArray node=(topology.getJSONObject(0)).getJSONArray("node");
        JSONArray link=(topology.getJSONObject(0)).getJSONArray("link");
        int i;
        for(i=0;i<node.length();i++)
        {
            JSONObject temp=node.getJSONObject(i);
            String nodeid=temp.getString("node-id");
            if(nodeid.charAt(0)=='h')
            {
                hosts.add(nodeid);
            }
            mynetwork.addVertex(nodeid);
            if(nodeid.charAt(0)!='h')
            {
            JSONArray terminationpoint=temp.getJSONArray("termination-point");
            for(int j=0;j<terminationpoint.length();j++)
            {
                JSONObject temp1=terminationpoint.getJSONObject(j);
                String tpid=temp1.getString("tp-id");
                mynetwork.addVertex(tpid);
                DefaultWeightedEdge temp2=mynetwork.getEdge(nodeid,tpid);
            if(temp2==null)
            {
            DefaultWeightedEdge e1 = mynetwork.addEdge(nodeid,tpid); 
            mynetwork.setEdgeWeight(e1,0);
            }
                
            }
            }
            
        }
        for(i=0;i<link.length();i++)
        {
            JSONObject temp=link.getJSONObject(i);
             JSONObject dest=temp.getJSONObject("destination");
            String destid=dest.getString("dest-node");
            String desttp=dest.getString("dest-tp");
            JSONObject source=temp.getJSONObject("source");
            String sourceid=source.getString("source-node");
            String sourcetp=source.getString("source-tp");
            if(sourceid.charAt(0)!='h')
            {
            String portstats=GetPortStatistics.getstats(sourcetp,sourceid);
            JSONObject portst=new JSONObject(portstats);
            int trans=portst.getInt("transmitted");
            int recv=portst.getInt("recieved");
           /* try{
            sleep(30000);
            }
            catch (Exception e)
            {
                
            }
             String portstats1=GetPortStatistics.getstats(sourcetp,sourceid);
            JSONObject portst1=new JSONObject(portstats1);
            int trans1=portst1.getInt("transmitted");
            int recv1=portst1.getInt("recieved");*/
            int trafffic=(trans+recv)/2;
            int weight=(trafffic/50);
            if(weight==0)
                weight=1;
            DefaultWeightedEdge temp1=mynetwork.getEdge(sourcetp,desttp);
            if(temp1==null)
            {
            DefaultWeightedEdge e1 = mynetwork.addEdge(sourcetp,desttp); 
            mynetwork.setEdgeWeight(e1, weight);
            }
            }
          
            
        }
        
        
   }
    
    
}
