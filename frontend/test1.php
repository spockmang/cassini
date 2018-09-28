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
        <style>
               html
          {
              overflow-x: hidden;
              overflow-y: hidden;
          }
            #myTable {
  border-collapse: collapse;
  width: 720px;
  border: 1px solid #ddd;
  font-size: 18px;
}


#myTable th {
  text-align: center;
  padding: 12px;
  background-color: #bababa;
}
#myTable tr {
  border-bottom: 1px solid #ddd;
  font-size: 16px;
}

#myTable tr:hover {
 background-color: #bababa;
}
        </style>
    </head>
    <body>
        <table id="myTable"><tr class="header"><th style="width: 20%;border-right: 1px solid #ddd;">SOURCE HOST-DESTINATION HOST</th><th style="width: 80%;">PATH</th></tr>
        <?php
        $paths=array();
        ini_set('mongo.long_as_object', 1);
        $m = new MongoClient("mongodb://172.16.52.73");
   $db = $m->project;
   $collection = $db->paths;
    
  
    $cursor = $collection->find();
    foreach ($cursor as $document) {
     
        $patharray=$document["pathbw"];
        $paths=$document["path"];
        $path= json_decode($paths,TRUE);
       
      for($i=0;$i<sizeof($patharray);$i++)
      {$t=sizeof($path);
          echo '<tr class="header"><td style="width: 20%;border-right: 1px solid #ddd;">'.$patharray.'</td><td style="width: 20%;">';
          for($j=0;$j<sizeof($path);$j++)
          {
              $sc="";
              $dc="";
       if($j>0)
       {
           $f=$j-1;
           list($weigh, $sourche, $destche) = explode(',', $path[$f]);
           $sourcheck= rtrim($sourche,'"');
           $destcheck= rtrim($destche,'"}');
           list($wc,$sc) = explode('":"', $sourcheck);
             list($ec,$dc) = explode('":"', $destcheck);
       }
           list($weight, $sour, $dest) = explode(',', $path[$j]);
           $source= rtrim($sour,'"');
           $destination= rtrim($dest,'"}');
       
            list($w,$s) = explode('":"', $source);
             list($e,$d) = explode('":"', $destination);
             
             if($j==0)
           echo $d.'-->'.$s;
else if(($s!=$sc)&&($s!=$dc))    
    echo ' --> '.$s;
else if(($d!=$sc)&&($d!=$dc))    
    echo ' --> '.$d;
else
{}
          }
           echo '</td></tr>';
      }
        }
        // put your code here
        ?>
    </body>
</html>
