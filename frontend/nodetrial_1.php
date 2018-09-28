<?php


include('connection.php');
include('fetchandparse.php');



  //$collection->remove(array("sourceid"=>1,"destinationid"=>2));
   
	

   
    //}



 


//header("Refresh: 3;url='http://127.0.0.1/myproject/vamshitopo1.php'");




echo $host;


$x=100;

$y=100;
$name=1;
$falg=0;
 //$txt="{\"nodes\": [";
//$arr=new array();
$myfile=fopen("topology\\asd.js","w");
$txt="var topologyData = {
    \"nodes\": [";
fwrite($myfile,$txt);
fclose($myfile);
   
    
   // $cursor =$collection->find(); 
    
    //if($cursor)
    //{
      //  $i=0;
     
        
    // put your code here

$myfile = fopen("topology\\asd.js","a");
$xarray=array();
$yarray=array();
for($m=0;$m<$host;$m++)
{
	array_push($xarray,-1);
	array_push($yarray,-1);
}
$hoststring="";
for($m=$host;$m<sizeof($array);$m++)
{

$check=1;
while($check==1)
{
$x=rand(-400,700);
$check=0;
for($l=0;$l<sizeof($xarray);$l++)
{
	if($x==$xarray[$l]+40 || $x==$xarray[$l]-40)
	{
		$check=1;
	}
}
}
$check=1;
while($check==1)
{
$y=rand(-400,700);
$check=0;
for($l=0;$l<sizeof($yarray);$l++)
{
	if($y==$yarray[$l]+40 || $y==$yarray[$l]-40)
	{
		$check=1;
	}
}
}

$txt=",{\"id\":".($m).",\"x\":".strval($x).",\"y\":".strval($y).",\"name\":"."\"".$array[$m]."\""."}"."\n";
 $hoststring=$hoststring.$txt;

array_push($xarray,$x);
array_push($yarray,$y);
$x=$x+80;
$falg=$falg+1;
//$y=$y+60;
//$name=$name+1;

	
	
}

$x1=0;
$y1=0;
$te=0;
$falg=0;
for($m=0;$m<$host;$m++)
{
for($l=0;$l<sizeof($sourcearray);$l++)
{
	if($sourcearray[$l]==$m)
	{
	$te=$destarray[$l];
	break;	
	}
	elseif($destarray[$l]==$m)
	{
		$te=$sourcearray[$l];
		break;
	}
}
$x1=$xarray[$te];
$y1=$yarray[$te]+80;

//echo $array[$m];
//echo '</br>'$array[$m]'</br>';
$txt1="{\"id\":".($m).",\"x\":".strval($x1).",\"y\":".strval($y1).",\"name\":"."\"".$array[$m]."\""."}"."\n";
 





if($falg!=0)
{
	$txt1=",".$txt1;
}
//echo $txt1;
$x=$x+40;
$falg=$falg+1;
$y=$y+60;
$name=$name+1;
fwrite($myfile,$txt1); 
	
}
//echo $hoststring;
fwrite($myfile,$hoststring);




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







