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
public class Flowstatistics {
     public static void main(String srgs[])
    {
        MyRESTlib mylib=new MyRESTlib();
        String myurl="http://172.16.52.74:8181/restconf/operational/opendaylight-inventory:nodes/node/openflow:1/table/0";
       String res= mylib.myGetRequest(myurl);
        JSONObject myobj=new JSONObject(res);
        JSONArray myarray=myobj.getJSONArray("flow-node-inventory:table");
        int n=myarray.length();
        int i;
        for(i=0;i<n;i++)
        {
            JSONObject myobj1=myarray.getJSONObject(i);
            int id=myobj1.getInt("id");
            System.out.println("id is   : "+id);
            JSONObject stats=myobj1.getJSONObject("opendaylight-flow-table-statistics:flow-table-statistics");
            int activeflows=stats.getInt("active-flows");
            int packetlookedup=stats.getInt("packets-looked-up");
            int packetmatched=stats.getInt("packets-matched");
            System.out.println("active-flows:  "+activeflows+"\npackets-looked-up:   "+packetlookedup+"\npackets-matched:    "+packetmatched);
            
        }
       
    }
    
}
