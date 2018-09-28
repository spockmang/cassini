<!DOCTYPE html>
<html>
<head>
     <link rel="stylesheet" href="sheet.css">
<style>

 html
{
overflow-y: scroll;
overflow-x: hidden;
}
   #banner
            {
                vertical-align: top;
                height: 100px;
                background-color: #fefefe;
                position: fixed;
                width: 100%;
                margin-top: 0%;
            }
            body {
    margin: 0;
}
           
#myTable {
  border-collapse: collapse;
  
  border: 1px solid #ddd;
  font-size: 18px;
}

#myTable th, #myTable td {
  text-align: left;
  padding: 12px;
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
#myTable tr {
  border-bottom: 1px solid #ddd;
}

#myTable tr.header, #myTable tr:hover {
  background-color: #f1f1f1;
}
.s1
{
    border-style: none ;
    background-color: white;
    border-bottom: solid;
    border-color: #3e8e41;
}


.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    padding-left: 100px;
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
.close,.close1,.close2,.close3 {
    color: white;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,.close:focus,.close1:hover,.close1:focus,.close2:hover,.close2:focus ,.close3:hover,.close3:focus {
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
    width: 24%;
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
            alert(data);
             
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
     alert(errorThrown);
     alert(textStatus);
  }
       
       
    });
        
        }
        function gettophp(getmyJSON)
    {
        $.ajax({
        url: "deleteflow1.php",
        type: "POST",
        data: {ope:"delete1",payload:getmyJSON},
        success: function(data) {
          //  alert(data);
         out(data);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
     alert(errorThrown);
     alert(textStatus);
  }
       
       
    });
        
        }
        function out(data)
        {
             var output='<p>';
                    var str=data.split(",");
              var obj=JSON.stringify(data);
            for(var i=0;i<str.length;i++)
            {
                output+=str[i];
                output+='<br>';
            }
       
    var myWindow = window.open("", "MsgWindow", "width=800,height=800");
   
   
   myWindow.document.body.innerHTML=output;
        }
        
     </script>
</head>
<body>
    <div id="banner" style="margin-top: -5%"><center><p style="color: #4CAF50;font-size: 50px;margin-top: 0%;position: static;margin-left: 0%">CASSINI</p>
            <p style="color: #4CAF50;font-size: 20px;margin-top: -3%;position: static;margin-left: 0%">A SDN BASED NETWORK AUTOMATION TOOL</p></center></div>
 
            <ul id="sidepane" class="a1" style="margin-top: 100px">
    <li><a  href="main.php">TOPOLOGY</a></li>
    <li><a class="active" href="nodes.php" >NODE INFORMATION</a></li><li><a  href="path.php">PATH CONFIGURATION</a></li>
</ul>
<div id="set" >
<h2 style="margin-top: 5%">NODE INFORMATION</h2>
<?php
ini_set('mongo.long_as_object', 1);
    
   $m = new MongoClient("mongodb://172.16.52.73:27017");
   $db = $m->project;
   $collection = $db->newtopology;
    echo '<table id="myTable"> <tr class="header"> <th style="width:25%;">Node ID</th>
    <th style="width:25%;">Node Name</th><th style="width:25%;">Node Connectors</th></tr>';
   $array=array();
   
    $cursor = $collection->find();
    if($cursor)
    {
        $i=0;
     foreach ($cursor as $document) {
         $array1= $document["topology"][0]["node"];
         $j=0;
      while ($j< sizeof($array1))
      { echo '<tr><td><button class="b2" id="'.$i.'" onclick="port(this.id)">'.$array1[$j]["node-id"].'</td><td>none</td>';
      array_push($array, $array1[$j]["node-id"]);
      $newarray=$array1[$j]["termination-point"];
      $ele= sizeof($newarray);
      echo '<td>'.$ele.'</td></tr>';
      $j++;
       $i++;
        }
       
       
    }
    echo '</table><script>alert("Please click on node id to get port statistics");</script>';
        
    
    }
 else {
        echo '----';    
    }
    echo'
    

    
    <button id="myBtn">Add flow</button>

<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h1>Enter flow informantion</h1>
    </div>
    <div class="modal-body">
    <br><br>
    <input id="switchid" type="text" placeholder="Enter switch id"><br><br>
     <input id="tableid" type="text" placeholder="Enter table id"><br><br>
     <input id="flowid" type="text" placeholder="Enter flow id"><br><br>
      <select name = "selection">
        <option value = "select">Select</option>
        <option value = "inport">In-port</option>
        <option value = "source-mac">Source-Mac Address</option>
        <option value = "source-ip">Source IP-Address</option>
        <option value = "ether-type">Ether-type</option>
    </select>
     <input id="source" type="text" placeholder="Enter match details "><br><br>
      <select name = "selection2">
        <option value = "select">Select</option>
        <option value = "Outputphysicalport">Output-physicalport</option>
        <option value = "output-to-controller">output-to-controller</option>
        <option value = "output-to-flood">output-to-flood</option>
        <option value = "output-to-any">output-to-any</option>
        
    </select>
     <input id="destination" type="text" placeholder="(output - action)"><br><br>
     <input  id="priority" type="text" placeholder="Enter flow priority "><br><br>
    </div>
    <div class="modal-footer">
   <center> <button onclick="add()">ADD FLOW</button></center>
    </div>
  </div>

</div>

<script>
// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}


function add()
{
var switchid=document.getElementById("switchid").value;
var tableid=document.getElementById("tableid").value;
var flowid=document.getElementById("flowid").value;
var s = document.getElementsByName("selection")[0];
var text = s.options[s.selectedIndex].text;
 var s1=document.getElementsByName("selection2")[0];
        var text1=s1.options[s1.selectedIndex].text;
var source=document.getElementById("source").value;
var destination=document.getElementById("destination").value;
var priority=document.getElementById("priority").value;
if(switchid==""||tableid==""||flowid=="")
{
alert("Field can not be blank");
return false;
}
var addmyobj={"switchid":switchid,"tableid":tableid,"flowid":flowid,"sourcematch-type":text,"destinationmatch-type":text1,"source-match":source,"destination-match":destination,"priority":priority,"operation":"addflow"};
var addmyJSON = JSON.stringify(addmyobj);
modal.style.display = "none";
sendtophp(addmyJSON);
}
</script>



<button id="myBtn2">Get flow</button>

<div id="myModal2" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close1">&times;</span>
      <h1>Enter flow informantion</h1>
    </div>
    <div class="modal-body">
    <br><br>
    <input id="switchid1" type="text" placeholder="Enter switch id"><br><br>
     <input id="tableid1" type="text" placeholder="Enter table id"><br><br>
     <input id="flowid1"  type="text" placeholder="Enter flow id"><br><br>
    </div>
    <div class="modal-footer">
   <center> <button onclick="get()">GET FLOW</button></center>
    </div>
  </div>

</div>

<script>
// Get the modal
var modal2 = document.getElementById("myModal2");

// Get the button that opens the modal
var btn2 = document.getElementById("myBtn2");

// Get the <span> element that closes the modal
var span2 = document.getElementsByClassName("close1")[0];

// When the user clicks the button, open the modal 
btn2.onclick = function() {
    modal2.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span2.onclick = function() {
    modal2.style.display = "none";
}


function get()
{
var switchid1=document.getElementById("switchid1").value;
var tableid1=document.getElementById("tableid1").value;
var flowid1=document.getElementById("flowid1").value;
if(switchid1==""||tableid1==""||flowid1=="")
{
alert("Field can not be blank");
return false;
}
var getmyObj={"switchid1":switchid1,"tableid1":tableid1,"flowid1":flowid1,"operation":"getflow"};
var getmyJSON = JSON.stringify(getmyObj);
   modal2.style.display = "none";
gettophp(getmyJSON);
}
</script>

<button id="myBtn3">Delete flow</button>

<div id="myModal3" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close2">&times;</span>
      <h1>Enter flow informantion</h1>
    </div>
    <div class="modal-body">
    <br><br>
    <input id="switchid3" type="text" placeholder="Enter switch id"><br><br>
     <input id="tableid3" type="text" placeholder="Enter table id"><br><br>
     <input id="flowid3" type="text" placeholder="Enter flow id"><br><br>
    </div>
    <div class="modal-footer">
   <center> <button onclick="delete1()">DELETE FLOW</button></center>
    </div>
  </div>

</div>

<script>
// Get the modal
var modal3 = document.getElementById("myModal3");

// Get the button that opens the modal
var btn3 = document.getElementById("myBtn3");

// Get the <span> element that closes the modal
var span3 = document.getElementsByClassName("close2")[0];

// When the user clicks the button, open the modal 
btn3.onclick = function() {
    modal3.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span3.onclick = function() {
    modal3.style.display = "none";
}


function delete1()
{
var switchid3=document.getElementById("switchid3").value;
var tableid3=document.getElementById("tableid3").value;
var flowid3=document.getElementById("flowid3").value;
if(switchid3==""||tableid3==""||flowid3=="")
{
alert("Field can not be blank");
return false;
}
var getmyObj={"switchid1":switchid3,"tableid1":tableid3,"flowid1":flowid3,"operation":"del"};
var getmyJSON = JSON.stringify(getmyObj);
 modal3.style.display = "none";
  sendtophp(getmyJSON);

}
</script>




</script>
';
    echo '</div>';
   
    ?>
<script>
    var element = document.getElementById('banner');
var positionInfo = element.getBoundingClientRect();
var height = positionInfo.height;
var width = positionInfo.width;
document.getElementById('sidepane').style.marginTop = height-20+"px";
document.getElementById('set').style.marginTop=height-20+"px";
var element1=document.getElementById('sidepane');
var positionInfo1=element1.getBoundingClientRect();
var left=positionInfo1.width;
document.getElementById('set').style.width=window.innerWidth+"px";
document.getElementById('set').style.Height=(window.innerHeight-height)+"px";
document.getElementById('set').style.marginLeft=left+"px";

</script>
  <script>
            function port(clickedid)
            {
    
                var complex = <?php echo json_encode($array); ?>;
                var element=complex[clickedid];
            document.cookie = "element = " + element;
            window.location.assign("nodeconnect.php");
            
            
            }
  </script>
</body>
</html>
