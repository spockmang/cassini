/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrestlib;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author M6000628
 */
public class Pathflow {
    public static String addpathflow(JSONObject flow)
    {
        try {
            // TODO code application logic here
            System.out.println("adding flow");
            String sourcemac=flow.getString("sourcemac");
            String destmac=flow.getString("destmac");
            String outport=flow.getString("out-port");
            //String sourcematcht=flow.getString("sourcematch-type");
            //String destmatcht=flow.getString("destinationmatch-type");
            //String sourcematch=flow.getString("source-match");
            //String destmatch=flow.getString("destination-match");
            String switchid=flow.getString("switchid");
            String ftableid=flow.getString("tableid");
            String flowid=flow.getString("flowid");
            String fpriority=flow.getString("priority");
            DocumentBuilderFactory  docFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder docbuilder=docFactory.newDocumentBuilder();
            Document doc=(Document) docbuilder.newDocument();
            Element rootelement=doc.createElement("flow");
            doc.appendChild(rootelement);
            
            Attr attr = doc.createAttribute("xmlns");
		attr.setValue("urn:opendaylight:flow:inventory");
		rootelement.setAttributeNode(attr);

            
            //creating priority element
            Element priority=doc.createElement("priority");
            priority.appendChild(doc.createTextNode(fpriority));
             rootelement.appendChild(priority);
             
             //creating match element 
             Element match=doc.createElement("match");
            
           /*  if(sourcematcht.contentEquals("Ether-type"))
             {
                  Element EthernetMatch=doc.createElement("Ethernet-match");
               Element EthernetType=doc.createElement("Ethernet-type");
              Element Type =doc.createElement("Type");
              Type.appendChild(doc.createTextNode(sourcematch));
              EthernetType.appendChild(Type);
             EthernetMatch.appendChild(EthernetType); 
              match.appendChild(EthernetMatch);
             }
             if(sourcematcht.contentEquals("Source-Mac Address"))
             {*/
           //      <ethernet-destination>
             //   <address>ff:ff:29:01:19:61</address>
            //</ethernet-destination>
            System.out.println("destmac="+destmac);
               System.out.println("sourcemac="+sourcemac);
            
                  Element EthernetMatch=doc.createElement("ethernet-match");
                        Element ethernetsource=doc.createElement("ethernet-destination");
                        Element address=doc.createElement("address");
                        address.appendChild(doc.createTextNode(destmac));
                        ethernetsource.appendChild(address);
                        EthernetMatch.appendChild(ethernetsource);
                          Element ethernetsource1=doc.createElement("ethernet-source");
                        Element address1=doc.createElement("address");
                        address1.appendChild(doc.createTextNode(sourcemac));
                        ethernetsource1.appendChild(address1);
                        EthernetMatch.appendChild(ethernetsource1);
                        
                         match.appendChild(EthernetMatch);
                        
            // }
            
             
            /*  Element ipdest=null;
              if(sourcematcht.contentEquals("In-port"))
              {
               ipdest=doc.createElement("in-port");
              ipdest.appendChild(doc.createTextNode(sourcematch));
               match.appendChild(ipdest);
              }
              else if(sourcematcht.contentEquals("Source IP-Address"))
                      {
                          ipdest=doc.createElement("ipv4-destination");
                           ipdest.appendChild(doc.createTextNode(sourcematch));
                            match.appendChild(ipdest);
                      }*/
             
              rootelement.appendChild(match);
              
              
              //creating id of flow
              Element id=doc.createElement("id");
              id.appendChild(doc.createTextNode(flowid));
              rootelement.appendChild(id);
              
              
              //creating tableid of flow
             Element tableid=doc.createElement("table_id");
             tableid.appendChild(doc.createTextNode(ftableid));
             rootelement.appendChild(tableid);

             
             
             //creating instructions
             Element instructions=doc.createElement("instructions");
             Element instruction=doc.createElement("instruction");
             Element order=doc.createElement("order");
             order.appendChild(doc.createTextNode("0"));
             instruction.appendChild(order);
             Element applyactions=doc.createElement("apply-actions");
             Element action=doc.createElement("action");
             Element order1=doc.createElement("order");
             order1.appendChild(doc.createTextNode("0"));
             action.appendChild(order1);
             // Element decnwttl=doc.createElement("dec-nw-ttl");
             Element outputaction=doc.createElement("output-action");
             Element outputconnector=doc.createElement("output-node-connector");
            /* if(destmatcht.contentEquals("Output-physicalport"))
             {
                 outputconnector.appendChild(doc.createTextNode(destmatch));
             }
              if(destmatcht.contentEquals("output-to-controller"))
             {
                 outputconnector.appendChild(doc.createTextNode("CONTROLLER"));
             }
               if(destmatcht.contentEquals("output-to-flood"))
             {
                 outputconnector.appendChild(doc.createTextNode("FLOOD"));
             }
                if(destmatcht.contentEquals("output-to-any"))
             {
                 outputconnector.appendChild(doc.createTextNode("ANY"));
             }*/
            outputconnector.appendChild(doc.createTextNode(outport));
               outputaction.appendChild(outputconnector);
               action.appendChild(outputaction);
              //action.appendChild(decnwttl);
              applyactions.appendChild(action);
              instruction.appendChild(applyactions);
              instructions.appendChild(instruction);
              rootelement.appendChild(instructions);
              
              
              //creating xml file
              TransformerFactory transformfactory=TransformerFactory.newInstance();
              Transformer transformer=transformfactory.newTransformer();
              DOMSource source=new DOMSource(doc);
              StreamResult result=new StreamResult(new File("C:\\Users\\m6000628\\Documents\\temp.xml"));
              transformer.transform(source, result);
              System.out.println("file created");
              String myurl="http://172.16.52.74:8181/restconf/config/opendaylight-inventory:nodes/node/"+switchid+"/table/"+ftableid+"/flow/"+flowid;
              MyRESTlib mylib=new MyRESTlib();
              System.out.println(myurl+"\n");
              int code=mylib.myPutRequest(myurl);
              if(code==200 || code==201)
              {
                  return "flow created with flow id:"+flowid;
              }
             
             
             
              
              
              
              
              
              
              
            
             
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Createflow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Createflow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Createflow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     return "some error occured:file is not properly formetted";
     }
        
        
        
        
    }
    

