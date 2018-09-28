/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrestlib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author M6000628
 */
public class Sockettest {
    private static ServerSocket socket;

    private static Socket connection;
    private static String command       = new String();
    private static String responseStr   = "";

    private static int port = 1338;

    public static void main(String args[])  {
        System.out.println("Signal Server is running.");
  Update10 updatetopo = new Update10();
        Thread t = new Thread(updatetopo);
        t.start();
        try {
            sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sockettest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Update30 updatepath = new Update30();
        Thread t1 = new Thread(updatepath);
        t1.start();
        
        try  {
            socket = new ServerSocket(port);

            while (true)  {
                connection = socket.accept();

                InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
               // DataOutputStream response = new DataOutputStream(connection.getOutputStream());
                OutputStream out=connection.getOutputStream();
                //OutputStreamWriter outw=new OutputStreamWriter(out);
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                PrintStream ps=new PrintStream(out);
                BufferedReader input = new BufferedReader(inputStream);
                

                command = input.readLine();
                 System.out.println(command+"\n");
                JSONObject test=new JSONObject(command);
                String operation=test.getString("operation");
                if(operation.contentEquals("del"))
                {
                    responseStr=Deleteflow.deletemyflow(test);
                    responseStr=responseStr+"\r\n";
                    ps.write(responseStr.getBytes());
                    ps.flush();
                }
                if(operation.contentEquals("getstatsport"))
                {
                    responseStr=GetPortStatistics.getstats(test.getString("portid"),test.getString("portid").substring(0,test.getString("portid").length()-1));
                    responseStr=responseStr+"\r\n";
                    ps.write(responseStr.getBytes());
                    ps.flush();
                }
                if(operation.contentEquals("addflow"))
                {
                    responseStr=Createflow.Addflow(test);
                     responseStr=responseStr+"\r\n";
                    ps.write(responseStr.getBytes());
                    ps.flush();
                    
                }
                if(operation.contentEquals("getflow"))
                {
                    responseStr=Getflow.getmyflow(test);
                     responseStr=responseStr+"\r\n";
                     System.out.println(responseStr);
                    ps.write(responseStr.getBytes());
                    ps.flush();
                }
                if(operation.contentEquals("getpath"))
                {
                    String source=test.getString("source");
                    String dest=test.getString("destination");
                    source="host:"+source;
                    dest="host:"+dest;
                    if(ShortestPath.checkhostandpath(source, dest)==3)
                    {
                        List path=ShortestPath.getpath(source,dest);
                        ShortestPath.Storepath(path,source+"-"+dest);
                        String p=path.toString();
                        p=p+"\r\n";
                        System.out.println("path is this"+p);
                        ps.write(p.getBytes());
                        ps.flush();
                        
                    }
                }
                
                
//outw.write(responseStr);
//ps.write(responseStr.getBytes());
//ps.flush();


               // response.writeBytes(responseStr);
                //response.flush();
                //response.close();

               
            }
        } catch (IOException e)  {
            System.out.println("Fail!: " + e.toString());
        }

        System.out.println("Closing...");
    }
    
}
