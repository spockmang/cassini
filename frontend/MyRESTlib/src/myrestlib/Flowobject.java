/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrestlib;

/**
 *
 * @author M6000628
 */
public class Flowobject {
    String switchid;
    String flowid;
    String tableid;
    public Flowobject(String switchid,String flowid,String tableid)
    {
        this.switchid=switchid;
        this.flowid=flowid;
        this.tableid=tableid;
    }
    public String getflowid()
    {
        return flowid;
    }
    public String getswitchid()
    {
        return switchid;
    }
    public String gettableid()
    {
        return tableid;
    }
    
}
