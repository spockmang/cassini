<html>
<head>
<style>
    
#login {

  vertical-align: central;
  width: 200px;
  height: 150px;
    border-style: solid;
    border-width: 5px;
    border-color: #4CAF50;
    background-color: white;
    
}
body
{
background-color: white;
}
input[type=submit] {
    background-color:#4CAF50;
    color: white;
   
    cursor: pointer;
}

</style>
</head>
<body>
      <div id="banner" ><center><p style="color: #4CAF50;font-size: 50px;margin-top: 0%;position: static;margin-left: 5%">CASSINI</p>
            <p style="color: #4CAF50;font-size: 20px;margin-top: -3%;position: static;margin-left: 5%">A SDN BASED NETWORK AUTOMATION TOOL</p></center></div>
                  <center><div id="login">
<table>
<tr>
<center><br>PLEASE SIGN IN<br><br></center>
</tr>
<tr>
<form method="post" action="login.php">
<center><input type="text" name="u1" placeholder="USERNAME"><br><br>
<input type="password" name="p1" placeholder="PASSWORD"><br><br>

<center><input type="submit" name="SUBMIT" value="SUBMIT" onclick="update()"></center>
</center>
</form>
</tr>
</table>
                      </div></center>
    <?php


include('connection.php');
include('fetchandparse.php');



  //$collection->remove(array("sourceid"=>1,"destinationid"=>2));
   
	

   // echo '<table id="myTable"> <tr class="header"> <th style="width:25%;">hosts</th>
   // <th style="width:25%;">links</th></tr>';
  // $array=array();
 
    //$cursor = $collection->find();
    //if($cursor)
    //{
        //$i=0;
     //foreach ($cursor as $document) {
         //.$row['name'].$i
		// $temp1= $document["nodeid"];
		
       // echo '<tr>
   // <td>'.$document["nodeid"].'</td>


  //</tr>';
   //   // $i++;
   // }
   // echo '</table>';
        
    
    //}



 


//header("Refresh: 3;url='http://127.0.0.1/myproject/vamshitopo1.php'");







$x=100;

$y=100;
$name=1;
$falg=0;
 //$txt="{\"nodes\": [";
//$arr=new array();
$myfile=fopen("asdf.js","w");
$txt="var topologyData = {
    \"nodes\": [";
fwrite($myfile,$txt);
fclose($myfile);
   
    
   // $cursor =$collection->find(); 
    
    //if($cursor)
    //{
      //  $i=0;
     
        
    // put your code here

$myfile = fopen("asdf.js","a");


for($m=0;$m<sizeof($array);$m++)
{





$txt="{\"id\":".($m).",\"x\":".strval($x).",\"y\":".strval($y).",\"name\":"."\"".$array[$m]."\""."}"."\n";
 





if($falg!=0)
{
	$txt=",".$txt;
}

$x=$x+40;
$falg=$falg+1;
$y=$y+60;
$name=$name+1;
fwrite($myfile,$txt); 
	
}




//$txt="{\"id\":"+$row["destinationid"]+",\"x\":"+$x+",\"y\":"+$y+",\"name\":s"+$name+"}";
//$txt="{\"id\":".strval($row["destinationid"]).",\"x\":".strval($x).",\"y\":".strval($y).",\"name\":s".strval($name)."}";
//echo $txt;
//fwrite($myfile,$txt);
//$name=$name+1;
//$x=$x+50;

//$y=$y+50; 
//echo $txt;




$txt="],\"links\": [";
fwrite($myfile,$txt); 
$falg=0;


   // $cursor = $collection->find();
    //if($cursor)
    //{
        

   /*for($i=0;$i<$b;$i++) {
   $array($i)==source-id;
   source-id==$i;
   }
   for($c=0;$c<$b;$c++)
   {
   	$array($c)==destination-id;
	destination-id==$c;
   }
*/
//echo $document["sourceid"];
for($i=0;$i<sizeof($sourcearray);$i++)
{
	


$txt="{\"source\":".strval($sourcearray[$i]).",\"target\":".strval($destarray[$i])."}"."\n";
if($falg!=0)
{
	$txt=",".$txt;
}
$falg=$falg+1;
fwrite($myfile,$txt);
//echo $txt;
}

$j++;

//}

$txt="]};";
fwrite($myfile,$txt);

?>
</body>
</html>