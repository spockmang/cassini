<?php
if (isset($_POST['action']) && !empty($_POST['action'])) {
    $action = $_POST['action'];
    switch ($action) {
        case 'test' : echo "test";
            
            break;
        case 'blah' : echo "blah";
            break;
        // ...etc...
    }
}
?>

