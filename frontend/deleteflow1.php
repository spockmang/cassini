<?php


$PORT = 1338; //the port on which we are connecting to the "remote" machine
$HOST = "172.16.52.74"; //the ip of the remote machine (in this case it's the same machine)
$sock = socket_create(AF_INET, SOCK_STREAM, 0) //Creating a TCP socket
     or die("error: could not create socket\n");

$succ = socket_connect($sock, $HOST, $PORT) //Connecting to to server using that socket
    or die("error: could not connect to host\n");
//print_r("connected");


 if (isset($_POST['ope'])) {
$i=$_POST['ope'];
       switch($i)
       {
           case 'delete1':
               //echo $_POST['payload'];
               $send= socket_write($sock,$_POST['payload']."\n" , strlen($_POST['payload']."\n"));
               //echo "flowdeleted";
                $next = socket_read($sock,2048,PHP_NORMAL_READ);
                echo $next;
               break;
       }

        

    }

?>
