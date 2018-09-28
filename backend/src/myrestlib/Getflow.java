/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrestlib;

import com.mongodb.util.JSON;
import org.json.JSONObject;

/**
 *
 * @author M6000628
 */
public class Getflow {
   public static String getmyflow(JSONObject flow)
   {
       String tableid=flow.getString("tableid1");
       String flowid=flow.getString("flowid1");
       String switchid=flow.getString("switchid1");
       String myurl="http://172.16.52.74:8181/restconf/config/opendaylight-inventory:nodes/node/openflow:"+switchid+"/table/"+tableid+"/flow/"+flowid;
       String res=(new MyRESTlib()).myGetRequest(myurl);
       return res;
   }
    
}
