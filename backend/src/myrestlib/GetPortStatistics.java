/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrestlib;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author M6000628
 */
public class GetPortStatistics {
  public static String getstats(String port,String sw)
  {
        MyRESTlib mylib=new MyRESTlib();
        String myurl="http://172.16.52.74:8181/restconf/operational/opendaylight-inventory:nodes/node/"+sw+"/node-connector/"+port;
        
       String res= mylib.myGetRequest(myurl);
        JSONObject myobj=new JSONObject(res);
        JSONArray myarray=myobj.getJSONArray("node-connector");
        int n=myarray.length();
        String portstats1=null;
        System.out.println(n);
        for(int i=0;i<n;i++)
        {
            JSONObject myobj1=myarray.getJSONObject(i);
            String id=myobj1.getString("id");
            System.out.println("id   : "+id);
            JSONObject state=myobj1.getJSONObject("flow-node-inventory:state");
            boolean portstate=state.getBoolean("live");
            boolean linkdown=state.getBoolean("link-down");
            System.out.println("live:   "+portstate);
            System.out.println("linkdown:   "+linkdown);
            JSONObject portstats=null;
            if(myobj1.has("opendaylight-port-statistics:flow-capable-node-connector-statistics"))
             portstats=myobj1.getJSONObject("opendaylight-port-statistics:flow-capable-node-connector-statistics");
            
            
            JSONObject packets=portstats.getJSONObject("packets");
            int transmit=packets.getInt("transmitted");
            int recieved=packets.getInt("received");
            int recievedrops=portstats.getInt("receive-drops");
            int transmitdrops=portstats.getInt("transmit-drops");
            
            System.out.println("packets tranmitted:    "+transmit);
            System.out.println("packets Recieved:     "+recieved);
            System.out.println("recieved-drops:    "+recievedrops+"\ntransmit-drops:    "+transmitdrops);
        
            JSONObject  temp=new JSONObject();
            temp.put("live",portstate);
            temp.put("linkdown",linkdown);
            temp.put("transmitted",transmit);
            temp.put("recieved",recieved);
            temp.put("recieved-drops",recievedrops);
            temp.put("transmit-drops",transmitdrops);
            portstats1=temp.toString();
            
            
            
            
            
            
        }
        return portstats1;
  }
    }
    

