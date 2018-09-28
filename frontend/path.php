<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" href="sheet.css">
        <style>
        
     #banner
            {
                vertical-align: top;
                height: 100px;
                background-color: #fefefe;
                position: fixed;
                width: 100%;
                margin-top: 0%;
            }
            #set
            {
         
                float: left;
            }
            ul
            {
                 list-style-type: none;
            }
           
body {
    margin: 0;
}
li a.active {
    background-color: #4CAF50;
    color: white;
}
div
{
    align-self: right;
}
ul.a1 {
   
    list-style-type: none;
    padding: 0;
    width: 25%;
    background-color: #f1f1f1;
    position: fixed;
    height:100%;
    margin-top: 0%;
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



li a:hover:not(.active) {
    background-color: #555;
    color: white;
}
::-webkit-scrollbar {display:none;}
</style>
    </head>
    <body>
        <div id="banner"  ><center><p style="color: #4CAF50;font-size: 50px;margin-top: 0%;position: static;margin-left: 0%">CASSINI</p>
            <p style="color: #4CAF50;font-size: 20px;margin-top: -3%;position: static;margin-left: 0%">A SDN BASED NETWORK AUTOMATION TOOL</p></center></div>
            <ul id="sidepane" class="a1" style="margin-top: 0%">
    <li><a  href="main.php">TOPOLOGY</a></li>
  <li><a href="nodes.php">NODE INFORMATION</a></li>
  <li><a class="active" href="path.php">PATH CONFIGURATION</a></li>
  
   
</ul>
   
    
        <div id="set">
            <div id="div2"></div><hr>
           
            
    
 

        </div>
   
        <script>
    var element = document.getElementById('banner');
var positionInfo = element.getBoundingClientRect();
var height = positionInfo.height;
var width = positionInfo.width;
document.getElementById('sidepane').style.marginTop = height+"px";
document.getElementById('div2').style.marginTop=height+"px";
var element1=document.getElementById('sidepane');
var positionInfo1=element1.getBoundingClientRect();
var left=positionInfo1.width;
//document.getElementById('div1').style.Height=(window.innerHeight-height)+"px";
document.getElementById('div2').style.marginLeft=left+"px";

</script>
         <script>
      
   
      
         document.getElementById("div2").innerHTML='<object type="text/html" width="1000px" height="1100px" data="config.php" overflow/>';
    
         </script>
   
        <?php
        // put your code here
        ?>
    </body>
</html>
