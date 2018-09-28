<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <script src="jquery-3.2.1.min.js"></script>
        <script>
            function check()
            {
                $.ajax({ url: 'testphp.php',
         data: {action: 'test'},
         type: 'post',
         success: function(output) {
                      alert(output);
                  }
});
            }
            
            
       
    
        </script>
    </head>
    <body>
        
        <button onclick="check()">jhghjhd</button>
    </body>
</html>