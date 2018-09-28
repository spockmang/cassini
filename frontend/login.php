 <?php
    session_start();
    ?>
     <?php

 $username="root";
        $password="mysql";
        $server="127.0.0.1";
        $database="user";
        $con=  mysql_connect($server, $username, $password);
        mysql_select_db($database,$con);
        $uname=$_REQUEST['u1'];
        $pass=$_REQUEST['p1'];


        //$sql="SELECT * FROM userdet WHERE username='$uname' AND pwd= '$pass'";
      //$query= mysql_query($sql) ;
    //  if(mysql_num_rows($query))
        if($uname=="admin" && $pass=="admin")
      {
          $_SESSION['logged-in']=true;
          $_SESSION['u']=$uname;
          header("Location:main.php");
      }
      else
      {

          echo '<script>alert(invalid password )</script>';
          header("Location:index.php");
          // $_SESSION['logged-in']=false;
     //     $_SESSION['u']=$uname;
     //     header("Location:index_1.php");

      }
        mysql_close($con);


?>