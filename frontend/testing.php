<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
<head>
<title>Bug UI</title>
</head>

<body>

<script>
    function myfunc()
    {
        var s = document.getElementsByName('selection')[0];
var text = s.options[s.selectedIndex].text;
document.write(text);
    //what goes here??
    }
    function myfunc1()
    {
        var s=document.getElementsByName('selection2')[0];
        var text=s.options[s.selectedIndex].text;
        document.write(text);
    }
</script>
<form>
    <select name = "selection">
        <option value = "select">Select</option>
        <option value = "inport">In-port</option>
        <option value = "source-mac">Source-Mac Address</option>
        <option value = "source-ip">Source IP-Address</option>
        <option value = "ether-type">Ether-type</option>
    </select>
    <input type = "button" onclick = "myfunc()" value = "Submit">
    
    
     <select name = "selection2">
        <option value = "select">Select</option>
        <option value = "outport">Out-port</option>
        <option value = "destination-mac">Destination-Mac Address</option>
        <option value = "destination-ip">Destination IP-Address</option>
        
    </select>
    <input type = "button" onclick = "myfunc1()" value = "Submit">
</form>
</body>
</html>