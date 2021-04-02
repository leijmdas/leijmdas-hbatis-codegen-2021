package com.jtest.NodesFactroy.NodeConfig


/**
 * Created by ljm on 2012-04-01.
 */
class ConfigParser {

    static Map<String, Object> parseConfig(String filename, String key) {
        def find = false;
        def config = [:]
        def key_types=[:]

        println ConfigParser.class.getResource(filename)

        def p =  ConfigParser.class.getResource(filename).getFile()
        if(! new File(p).exists()){
            println  "$filename does not exist!"
            return new LinkedHashMap<String, Object>();
        }
        def path_xml = ConfigParser.class.getResource(filename).getText()

        def response = new XmlParser().parseText(path_xml)
        for (node in response.node) {
            assert node.name() == "node"

            config.name = node.@name
            config.type = node.@type
            node.each {
                config.put(it.@name, it.@value)
                key_types.put(it.@name,it.@type)
            }
            println config
            if(key==node.@name)
            {
                find=true
                break
            }
        }
        if(!find)
        {
            System.err.println("no found cfg key is :"+key)
        }

        def w=config.clone()

        config.put("items",w)
        config.put("key_types",key_types)
        config
    }


}
