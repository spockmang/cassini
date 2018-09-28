<?php

include './connection.php';
     
   $db = $m->project;
   $collection = $db->newtopology;
    $array1=array();
    $newarray=array();
    $newar=array();
    $cursor = $collection->find();
    if($cursor)
    {
        
     foreach ($cursor as $document) {
         $array1= $document["topology"][0]["node"];
          $j=0;
          $i=1;
      while ($j< sizeof($array1))
      {
         // if (substr('_abcdef', 0, 1) === '_')
          if(substr($array1[$j]["node-id"],0,1)=='h')
          {
             
              $newarray=$array1[$j]["host-tracker-service:addresses"][0];
              $newar[$i]=$newarray["mac"];
             $i++;
  
      }
          
      $j++;
      }
              
          }
           $newjson= json_encode($newar);
          echo $newjson;
      }
         //.$row['name'].$i
         
      
?>