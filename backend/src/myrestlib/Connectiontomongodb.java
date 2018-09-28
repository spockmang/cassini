/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrestlib;

import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M6000628
 */
public class Connectiontomongodb {
    public static MongoClient conn;
    public Connectiontomongodb()
    {
        try {
            conn=new MongoClient("172.16.52.146",27017);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Connectiontomongodb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static MongoClient connect()
    {
        if(conn==null)
        {
            try {
                conn=new MongoClient("172.16.52.146",27017);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Connectiontomongodb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }
    
}
