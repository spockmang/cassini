<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
           <link rel="stylesheet" href="sheet.css">
        <style>
            body {
    margin: 0;
}
            #banner
            {
                
                height: 50px;
                background-color: #fefefe;
                position: fixed;
                width: 100%;
                margin-top: 0%;
            }
            #set
            {
               
            }
            
        ul.a1 {
    list-style-type: none;
    
    padding: 0;
    width: 25%;
    background-color: #f1f1f1;
    position: fixed;
    height:100%;
    overflow: hidden;
}
li a:hover:not(.active) {
    background-color: #555;
    color: white;
}
li a {
    display: block;
    color: #000;
    padding: 8px 16px;
    text-decoration: none;
}

li a.active {
    background-color: #4CAF50;
    color: white;
}

#myTable {
  border-collapse: collapse;
  width: 100%;
  border: 1px solid #ddd;
  font-size: 18px;
  text-align: center;
  margin-top: -18%;
}

#myTable th, #myTable td {
  text-align: left;
  padding: 12px;
}
#myTable tr {
  border-bottom: 1px solid #ddd;
}

#myTable tr.header, #myTable tr:hover {
  background-color: #f1f1f1;
}
   td
   {
       width: 50%;
   }
   
   .modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
    position: relative;
    background-color: #fefefe;
    margin: auto;
    padding: 0;
    border: 1px solid #888;
    width: 80%;
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
  }

/* The Close Button */
.close,.close1 {
    color: white;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,.close:focus,.close1:hover,.close1:focus{
    color: #000;
    text-decoration: none;
    cursor: pointer;
}
input
{width: 100%;}
.i1
{
width: 25%;
}
.modal-header {
    padding: 2px 5px;
    background-color: #5cb85c;
    color: white;
}

.modal-body {padding: 2px 16px;}

.modal-footer {
    padding: 2px 5px;
    background-color: #5cb85c;
    color: white;
}
button
{
    height: 30px;
    width: 40%;
    background-color: #5cb85c;
    color: #fefefe;
   
}
.b2
{
    width: 70%;
    background-color: white;
    border: none;
    color: black;
    
}
body { margin:0px; }
      </style>
      <script src="jquery-3.2.1.min.js"></script>
 <script>
     
    function sendtophp(getmyJSON)
    {
       
        $.ajax({
        url: "deleteflow1.php",
        type: "POST",
        data: {ope:"delete1",payload:getmyJSON},
        success: function(data) {
            
           // data='\''+data+'\'';
           // alert(data);
            var obj=JSON.parse(data);
            //alert(obj);
    var myWindow = window.open("", "MsgWindow", "width=800,height=800");
   
   myWindow.document.write("<h1><center>PORT STATISTICS</center></h1><table id='myTable'><tr><td>live</td><td>"+obj.live+"</td></tr><tr><td>linkdown</td><td>"+obj["linkdown"]+"</td></tr><tr><td>Transmitted</td><td>"+obj["transmitted"]+"</td></tr><tr><td>Received</td><td>"+obj["recieved"]+"</td></tr><tr><td>received-drops</td><td>"+obj["recieved-drops"]+"</td></tr><tr><td>transmit-drops</td><td>"+obj["transmit-drops"]);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
     alert(errorThrown);
     alert(textStatus);
  }
       
       
    });
        
        }
     </script>
    </head>
    <body>
        <div id="banner" ><center><p style="color: #4CAF50;font-size: 50px;margin-top: 0%;position: static;margin-left: 0%">CASSINI</p>
            <p style="color: #4CAF50;font-size: 20px;margin-top: -3%;position: static;margin-left: 0%">A SDN BASED NETWORK AUTOMATION TOOL</p></center></div>
             <ul id="sidepane" class="a1" style="margin-top: 8%">
    <li><a  href="main.php">TOPOLOGY</a></li>
  <li><a href="nodes.php">NODE INFORMATION</a></li>
  <li><a class="active" href="path.php">PATH CONFIGURATION</a></li>
  
   
</ul>
        <?php
        $element= $_COOKIE['element'];
        
         echo '
<div id="div1" style="padding:1px 16px;margin-Left:25%;">
<div id="set" >
<h2>PORT INFORMATION</h2>
<table id="myTable"><tr class="header"><td> <center>Node  id</td><td><center>'.$element.'</center></td></tr>';
  ini_set('mongo.long_as_object', 1);
         $m = new MongoClient("mongodb://172.16.52.73:27017");
         $test=array();
   $db = $m->project;
   $collection = $db->newtopology;
    
    $cursor = $collection->find();
    if($cursor)
    {
        
     foreach ($cursor as $document) {
         $array1= $document["topology"][0]["node"];
          $j=0;
      while ($j< sizeof($array1))
      {
          if($element==$array1[$j]["node-id"])
          {
             
              $newarray=$array1[$j]["termination-point"];
            $k=0;
      while ($k< sizeof($newarray))
      {
          array_push($test,$newarray[$k]["tp-id"] );
          echo '<tr><td><center>Port'.($k+1).'</td><td><center><button class="b2" id="'.($k).'" onclick="get(this.id)">'.$newarray[$k]["tp-id"].'</td></tr>';
          $k++;
          echo '<br><br>';
      }
          }
      $j++;
        
              
          }
      }
         //.$row['name'].$i
         
    }

    echo '</div></div>';
    
?>
          <script>
  //  var element = document.getElementById('banner');
//var positionInfo = element.getBoundingClientRect();
//var height = positionInfo.height;
/*var width = positionInfo.width;
document.getElementById('sidepane').style.marginTop = (height)+"px";
document.getElementById('set').style.marginTop=height+"px";
var element1=document.getElementById('sidepane');
var positionInfo1=element1.getBoundingClientRect();
var left=positionInfo1.width;
document.getElementById('div1').style.maxWidth=window.innerWidth+"px";
document.getElementById('div1').style.Height=(window.innerHeight-height)+"px";*/


</script>
        <script>
            function get(clickedid)
{
    var complex1 = <?php echo json_encode($test); ?>;
var portid=complex1[clickedid];
var getmyobj={"portid":portid,"operation":"getstatsport"};
var getmyJSON = JSON.stringify(getmyobj);

sendtophp(getmyJSON);


}
            </script>
            
    </body>
</html>
