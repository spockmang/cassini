/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrestlib;

import org.json.JSONObject;

/**
 *
 * @author M6000628
 */
public class Deleteflow {
    
  public static String deletemyflow(JSONObject flow)
  {String res=null;
      String switchid=flow.getString("switchid1");
      if(switchid.charAt(0)!='o')
      switchid="openflow:"+switchid;
      String tableid=flow.getString("tableid1");
      String flowid=flow.getString("flowid1");
       MyRESTlib mylib=new MyRESTlib();
        String myurl="http://172.16.52.74:8181/restconf/config/opendaylight-inventory:nodes/node/"+switchid+"/table/"+tableid+"/flow/"+flowid;
       int delflowid= mylib.myDeleteRequest(myurl);
       if(delflowid==0)
       {
         res="error in while deleteing possible causes can be wrong flowid or switch id or tableid";  
       }
       else if(delflowid==200)
        res="flow deleted id: "+flowid;
       return res;
  }
    
}
