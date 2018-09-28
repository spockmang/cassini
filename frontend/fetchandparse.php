<?php
		ini_set('mongo.long_as_object', 1);
        $m = new MongoClient("mongodb://172.16.52.73:27017");
   $db = $m->project;
   $collection = $db->newtopology;
  
    $cursor = $collection->find();
    foreach ($cursor as $document) {
      //  print_r($document);
      
      $array1= $document["topology"][0]["node"];
	 $array2=$document["topology"][0]["link"];
	  $array =  array();
	  $sourcearray= array();
	  $destarray= array();
	  $host=0;
      $j=0;
      while ($j< sizeof($array1))
      { //echo '<br><br>';
      //print_r($array1[$j]["node-id"]);
      //echo '<br><br>';
	  if($array1[$j]["node-id"][0]=='h')
	  {
     array_push($array,($array1[$j]["node-id"]));
	 $host++;
	 }
	 
      $j++;
        }
		$k=sizeof($array1);
		//print_r($k);
    } // put your code here
	$j=0;
	while ($j< sizeof($array1))
      { //echo '<br><br>';
     // print_r($array1[$j]["node-id"]);
      //echo '<br><br>';
	  if($array1[$j]["node-id"][0]!='h')
	  {
     array_push($array,($array1[$j]["node-id"]));
	 }
	 
      $j++;
        }
		
	$j=0;
	while($j<(sizeof($array2)))
	{
	$tempdest=$array2[$j]["destination"]["dest-node"];
	$tempsource=$array2[$j]["source"]["source-node"];
	$i=0;
	$tempi=0;
	$tempj=0;
	while($i<sizeof($array))
	{
	if(strcmp($array[$i],$tempdest)==0)
	{
	//array_push($destarray,$i);
	$tempi=$i;
	break;
	}
	$i++;
	}
	$i=0;
	while($i<sizeof($array))
	{
	
	if(strcmp($array[$i],$tempsource)==0)
	{
	//array_push($sourcearray,$i);
	$tempj=$i;
	break;	
	}
	
		$i++;
		}
		$check=0;
		//echo "</br></br>".$tempi;
		//echo "</br></br>".$tempj;
		$k=0;
		while($k<sizeof($destarray))
		{
			if($tempj==$destarray[$k])
			{
				if($sourcearray[$k]==$tempi)
				{
					$check=1;
					break;
				}
				
			}
			$k++;
		}
		if($check==0)
		{
			array_push($destarray,$tempi);
					array_push($sourcearray,$tempj);
		}
	
		//print_r($array2[$j]["link-id"]);
		//echo "</br></br>destination-id</br>";
		//print_r($array2[$j]["destination"]["dest-node"]);
		//echo "</br></br>source-id</br>";
		//print_r($array2[$j]["source"]["source-node"]);
		
		$j++;
		}
	//echo "</br>".$sourcearray[0];
	//echo "</br>".$destarray[0];
	//echo "</br></br>".sizeof($array2);
	$b=sizeof($array2);
        ?>
