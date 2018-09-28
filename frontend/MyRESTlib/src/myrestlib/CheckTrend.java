/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrestlib;

import java.util.ArrayList;

/**
 *
 * @author M6000628
 */
public class CheckTrend {
    public static int mytrend(ArrayList ar)
    {
        int inc=0,dec=0;
        for(int i=0;i<ar.size();i++)
        
        {
            int k=(int)ar.get(i);
            int k1=0;
            if((i+1)<ar.size())
                k1=(int)ar.get(i+1);
            if(k1!=0)
            {
                if(k>=k1)
                    dec++;
                if(k<k1)
                    inc++;
                    
            }
            //if(k>ar.get(i+1))
        }
        if(inc==dec)
            return 0;
        else if(inc>dec)
            return 1;
        else
            return 2;


    }
    
}
