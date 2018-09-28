<!DOCTYPE html>
<html>
<head>

<style>
    html
{
overflow-y: scroll;
overflow-x: hidden;
}
 body {
    margin: 0;
}
   
           
body {
    margin: 0;
}
li a.active {
    background-color: #4CAF50;
    color: white;
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
tr {
  border-bottom: 1px solid #4CAF50 ;
}

li a {
    display: block;
    color: #000;
    padding: 8px 16px;
    text-decoration: none;
}


button.b1
{
background-color: #4CAF50;
color: white;
height: 40px;
}
button.b2
{
background-color: #4CAF50;
color: white;
height: 40px;
margin:0px;

}
li a:hover:not(.active) {
    background-color: #555;
    color: white;
}
table.t1
{
 border-style: solid;
    border-width: 2px;
    border-color: #4CAF50 ;
width: 1200px ;
}

</style>
 <script src="jquery-3.2.1.min.js"></script>
 <script src="sharing1.js"></script>
<script>
    function myshare(te)
    {
        alert(te);
        test=te;
        console.log(test);
    }
    function run()
    {
        $.ajax({
        url: "reloadtopo1.php",
        type: "POST",
       
        success: function(data) {
            
           // data='\''+data+'\'';
           //alert(data);
           myshare(data);
           alert(data);
          
            },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
     alert(errorThrown);
     alert(textStatus);
  }
       
       
    });
    //alert("kkklklg");
    
   
         document.getElementById("divit").innerHTML='<object type="text/html" width="1080px" height="1080px"  data="topo.php"/>';
       }
 </script>
 


</head>
<body>
    
    <div id="banner" ><center><p style="color: #4CAF50;font-size: 50px;margin-top: 0%;position: static;margin-left: 0%">CASSINI</p>
            <p style="color: #4CAF50;font-size: 20px;margin-top: -3%;position: static;margin-left: 0%">A SDN BASED NETWORK AUTOMATION TOOL</p></center></div>
    
            <ul id="sidepane" class="a1">
    <li><a class="active" href="main.php">TOPOLOGY</a></li>
  <li><a href="nodes.php">NODE INFORMATION</a></li>
  <li><a  href="path.php">PATH CONFIGURATION</a></li>
  
   
</ul>
         <div id="divit" style="margin-left:25%;padding:1px 16px;height:1000px;">
                
        <script>run();
            </script>
    </div>
       
    <script>
   var element = document.getElementById('banner');
var positionInfo = element.getBoundingClientRect();
var height = positionInfo.height;
var width = positionInfo.width;
//document.getElementById('sidepane').style.marginTop = height+"px";
//document.getElementById('div1').style.marginTop=height+"px";
var element1=document.getElementById('sidepane');
var positionInfo1=element1.getBoundingClientRect();
var left=positionInfo1.width;
//document.getElementById('div1').style.width=window.innerWidth+"px";
//document.getElementById('div1').style.Height=window.innerHeight+"px";
//document.getElementById('div1').style.marginLeft=left+"px";

    
    
</script>
</body>
</html>
