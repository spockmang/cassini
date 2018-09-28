<?php
include('connection.php');
include('fetchandparse.php');
echo $host;
$x=100;
$y=100;
$name=1;
$falg=0;
$myfile=fopen("topodata.js","w");
$txt="var topologyData = {
    \"nodes\": [";
fwrite($myfile,$txt);
fclose($myfile);
$myfile = fopen("topodata.js","a");
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
$x=rand(-700,0);
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
$y=rand(-2000,-1500);
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
$txt1="{\"id\":".($m).",\"x\":".strval($x1).",\"y\":".strval($y1).",\"name\":"."\"".$array[$m]."\""."}"."\n";
if($falg!=0)
{
	$txt1=",".$txt1;
}
$x=$x+40;
$falg=$falg+1;
$y=$y+60;
$name=$name+1;
fwrite($myfile,$txt1); 
	
}
fwrite($myfile,$hoststring);
$txt="],\"links\": [";
fwrite($myfile,$txt); 
$falg=0;
for($i=0;$i<sizeof($sourcearray);$i++)
{
$txt="{\"source\":".strval($sourcearray[$i]).",\"target\":".strval($destarray[$i])."}"."\n";
if($falg!=0)
{
	$txt=",".$txt;
}
$falg=$falg+1;
fwrite($myfile,$txt);
}
$j++;
$txt="]};";
fwrite($myfile,$txt);








