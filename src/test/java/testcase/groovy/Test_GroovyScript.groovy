package testcase.groovy

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.json.StreamingJsonBuilder

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

/**
 * Created by Administrator on 2016-03-08.
 */
class Test_GroovyScript {
    static void main(args) {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("groovy");

        Integer sum = (Integer) engine.eval("(1..10).sum()");
        assert 55 == sum;

        engine.put("first", "HELLO");
        engine.put("second", "world");
        String result = (String) engine.eval("first.toLowerCase()  + second.toUpperCase()");
        engine.put("v", 3333)
        def ret = engine.eval(" Math.abs( v) ")
        println ret
        println result


        StreamingJsonBuilder builder = new StreamingJsonBuilder(new StringWriter())
        builder.records {
            car {
                name 'HSV Maloo'
                make 'Holden'
                year 2006
                country 'Australia'
                record {
                    type 'speed'
                    description 'production pickup truck with speed of 271kph'
                }
            }
        }
        String json = JsonOutput.prettyPrint(builder.getWriter() .toString())
        println json
        def jsonSlurper = new JsonSlurper()
        def object = jsonSlurper.parseText(json)
        assert object instanceof Map
        println object


        println object.records.car
        builder.setWriter(new StringWriter())
        builder.jtest{
            name 'leijm',              'song'
        }
        println JsonOutput.prettyPrint(builder.getWriter().toString())
        object = jsonSlurper.parseText(JsonOutput.prettyPrint(builder.getWriter().toString()))
        println object.jtest.name

        //
        //

        def count = 0
    }
}
//http://www.groovy-lang.org/templating.html
