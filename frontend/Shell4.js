
(function (nx) {
    /**
     * define application
     */
    
    var Shell = nx.define(nx.ui.Application, {

 properties: {
            icon: {
                value: function() {
                    return function(vertex) {
                        var id = vertex.get("id");
                        var name=vertex.get("name");

                        if (name.slice(0,1)=="o" ) {
                            return 'switch'
                        } else {
                            return 'host'
                        }
                    }
                }
            }
        },
        methods: {
            start: function () {
               // host='<?php echo $host  ?>';
                //your application main entry
 //share(6);
//alert("hjhjhj");
                // initialize a topology
                var topo = new nx.graphic.Topology({
                    // set the topology view's with and height
                    width: 1080,
                    height: 1080,
                    
                  
                    // node config
                    nodeConfig: {
label: function(vertex) {
                            return vertex.get("name") + "abu";
                        },
                        iconType: '{#icon}',
                        // label display name from of node's model, could change to 'model.id' to show id
                        label: 'model.name'
                    },
                    // link config
                    linkConfig: {
                        // multiple link type is curve, could change to 'parallel' to use parallel link
                        linkType: 'curve'
                    },
nodeSetConfig: {
                        iconType: 'model.deice_type'
                    },
                    // show node's icon, could change to false to show dot
                    showIcon: true
                });

                //set data to topology
                topo.data(topologyData);
                //attach topology to document
                topo.attach(this);
            }
        }
    });

            
    /**
     * create application instance
     */
    var shell = new Shell();

    /**
     * invoke start method
     */
    shell.start();
})(nx);