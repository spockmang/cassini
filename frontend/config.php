<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <style>
              html
          {
              overflow-x: hidden;
              overflow-y: hidden;
          }
            .division
            {
                float: left;
            }
            
            .dropbtn {
    background-color: #4CAF50;
    color: white;
    padding: 5px;
    font-size: 14px;
    border: none;
    cursor: pointer;
}

.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}
ul
            {
                 list-style-type: none;
            }
.dropdown-content a:hover {background-color: #888;}

.dropdown:hover .dropdown-content {
    display: block;
    
}

.dropdown:hover .dropbtn {
    background-color: #3e8e41;
}
#div3
{
        overflow: scroll;
      
    
}
#div4
{
    
    
}
clear
{
    float:left; 
clear: left;
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
        var str='<h3><p style="color:#4CAF50">Path between the Selected Source and destination is-:<br></p></h3>';
       var arr=data.split(",");
       for(var i=0;i<arr.length;i++)
       {
           var tmp1,arrch;
           if(i>0)
           {
                var tmp1=arr[i-1];
                tmp1=tmp1.substr(2);
           tmp1=tmp1.slice(0,-1);
           var arrch=tmp.split(" : ");
           }
           var tmp=arr[i];
           tmp=tmp.substr(2);
           tmp=tmp.slice(0,-1);
           var arr1=tmp.split(" : ");
          if(i==0)
          {
              str+="[("+arr1[1]+"-->"+arr1[0];
          }
          else if((arr1[1]!=arrch[0])&&(arr1[1]!=arrch[1]))
              {
              str+="-->"+arr1[1];
          }
          else if((arr1[0]!=arrch[0])&&(arr1[0]!=arrch[1]))
          {
              str+="-->"+arr1[0];

          }
          else{}
       }
         $('#div3').html(str);
         document.getElementById("div4").innerHTML='<object type="text/html" width="600px" data="test1.php"/>';
   
           // data='\''+data+'\'';
           // alert(data);
      //      var obj=JSON.parse(data);
            //alert(obj);
    //var myWindow = window.open("", "MsgWindow", "width=800,height=800");
   
  // myWindow.document.write("<h1><center>PORT STATISTICS</center></h1><table id='myTable'><tr><td>live</td><td>"+obj.live+"</td></tr><tr><td>linkdown</td><td>"+obj["linkdown"]+"</td></tr><tr><td>Transmitted</td><td>"+obj["transmitted"]+"</td></tr><tr><td>Received</td><td>"+obj["recieved"]+"</td></tr><tr><td>received-drops</td><td>"+obj["recieved-drops"]+"</td></tr><tr><td>transmit-drops</td><td>"+obj["transmit-drops"]);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
     alert(errorThrown);
     alert(textStatus);
  }
       
       
    });
        
        }
     </script>
    
 <script>
     var source="";
     var destination="";
    function sour()
    {
      
        $.ajax({
        url: "source.php",
        type: "POST",
       
        success: function(data) {
            
           // data='\''+data+'\'';
           
            var obj=JSON.parse(data);
             var count=0,i=1;
       var nul='<ul id="hit">';
   for(var prop in obj) {
      if (obj.hasOwnProperty(prop)) {
         ++count;
      }
   }
  
   for(;i<=count;i++)
    nul+="<li><a>"+obj[i]+"</a></li>";
nul+="</ul>";
            $('#d1').html(nul);
            $('#hit li').on('click', function() {
                source=$(this).find('a').html();
    $('#title').html($(this).find('a').html());
    });
          
            },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
     alert(errorThrown);
     alert(textStatus);
  }
       
       
    });
        
        }
         function dest()
    {
      
        $.ajax({
        url: "source.php",
        type: "POST",
       
        success: function(data) {
            
           // data='\''+data+'\'';
         
            var obj=JSON.parse(data);
             var count=0,i=1;
       var nul='<ul id="hitit">';
   for(var prop in obj) {
      if (obj.hasOwnProperty(prop)) {
         ++count;
      }
   }
  
   for(;i<=count;i++)
    nul+="<li><a>"+obj[i]+"</a></li>";
nul+="</ul>";
            $('#d2').html(nul);
            $('#hitit li').on('click', function() {
                destination=$(this).find('a').html();
    $('#titleit').html($(this).find('a').html());
    });
          
            },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
     alert(errorThrown);
     alert(textStatus);
  }
       
       
    });
        
        }
        function get()
        {
            if(source==destination)
            {
                alert("Source and destination cannot be the same");
            }
           
            else
            {
          var sendobj={"source":source,"destination":destination,"operation":"getpath"};
var sendjson = JSON.stringify(sendobj);
//alert(sendjson);
sendtophp(sendjson);
$('#title').html("Select Source");
$('#titleit').html("Select Destination");
            }
        }
        
     </script>
    </head>
    <body>
        <!DOCTYPE html>
<html>
    <body>
     
<?php include './connection.php';?>
        <div class="division">
           
        
            
            <b>ENTER DETAILS FOR PATH CONFIGURATION-:<br> <br>SELECT SOURCE:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div id="clear" class="dropdown"> <span id="title" class="dropbtn" >Select Source</span>
        
  <div id="d1" class="dropdown-content"></div>
        <script>sour();
            setInterval(sour,5000);</script></div>
            <br><br>
        
            <b>SELECT DESTINATION:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div class="dropdown"> <span id="titleit" class="dropbtn">Select Destination</span>
        
  <div id="d2" class="dropdown-content"></div>
        <script>dest();
            setInterval(dest,5000);</script></div>
            <br><br>   <button style="margin-left: 180px;"onclick="get()">Configure Path</button></center><div>
        </div> 
        <br>   <div id="div3" style="width:720px;border:dotted 2px #4CAF50 ;overflow: auto;"></div>
        <hr style="height: 2px;"> <br><b style="color: #4CAF50;"><center>CONFIGURED PATH DETAILS</center></b> <br><div id="div4" ></div>
</body>
</html>

<script>
    document.getElementById("div4").innerHTML='<object type="text/html" width="800px" height="2000px" data="test1.php"/>';
   
    </script>
    </body>
</html>
