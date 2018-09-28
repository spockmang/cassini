/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrestlib;

import java.util.HashMap;
import java.util.Map;
import static myrestlib.shortestPathnetwork.hosts;
import static myrestlib.shortestPathnetwork.mynetwork;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author M6000628
 */
public class getInitialportStats {
    public static Map<String,Integer> pstat;
    public static void get()
    {
        pstat=new HashMap<>();
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
          
            if(nodeid.charAt(0)!='h')
            {
            JSONArray terminationpoint=temp.getJSONArray("termination-point");
            for(int j=0;j<terminationpoint.length();j++)
            {
                JSONObject temp1=terminationpoint.getJSONObject(j);
                String tpid=temp1.getString("tp-id");
               // mynetwork.addVertex(tpid);
               String portstats=GetPortStatistics.getstats(tpid,nodeid);
            JSONObject portst=new JSONObject(portstats);
            int trans=portst.getInt("transmitted");
            int recv=portst.getInt("recieved");
            int trafffic=(trans+recv)/2;
            pstat.put(tpid,trafffic);
                
            }
            }
            
        }
    }
    
}
