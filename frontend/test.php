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
    </head>
    <body>
        <?php
        $m = new MongoClient();
   $db = $m->project;
   $collection = $db->topology;
  
    $cursor = $collection->find();
    foreach ($cursor as $document) {
      //  print_r($document);
      
      $array1= $document["topology"][0]["node"];
      $j=0;
      while ($j< sizeof($array1))
      { echo '<br><br>';
      print_r($array1[$j]["node-id"]);
      echo '<br><br>';
      $newarray=$array1[$j]["termination-point"];
      echo '<br><br>';
      $k=0;
      while ($k< sizeof($newarray))
      {
          print_r($newarray[$k]["tp-id"]);
          $k++;
          echo '<br><br>';
      }
      $j++;
        }
    } // put your code here
        ?>
    </body>
</html>
