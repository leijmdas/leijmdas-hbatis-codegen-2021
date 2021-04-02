package testcase.groovy

import groovy.json.JsonSlurper
import groovy.transform.Field

/**
 * Created by Administrator on 2016-03-06.
 */
@Field def pmeDef = new Expando()

    class FriendHadnler {
        def scriptObject

        void friend(String name) {
            scriptObject.pmeDef.friend = name
        }
    }

    @Field def friendHandler = new FriendHadnler(scriptObject: this)
    void name(String name) {
        pmeDef.name = name
    }
    void pk(String[] pk) {
        pmeDef.pk = pk
    }

    void attr(LinkedHashMap attr) {
        pmeDef.attr = attr
    }

    void withFriend(Closure cl) {
        cl.setResolveStrategy(Closure.DELEGATE_FIRST)
        cl.setDelegate(friendHandler)
        cl.call()
    }

    def e(Closure cl) {
        cl.call()
        pk = pmeDef.pk.join(",")
        attrs = pmeDef.attr.toString().replace("[", "(").replace("]", ")")
        """define event ${pmeDef.name}${attrs}
partition key(${pk});"""
    }

    def msg(Closure cl) {
        cl.call()
        pk = pmeDef.pk.join(",")
        attrs = pmeDef.attr.toString().replace("[", "(").replace("]", ")")
        """msg ${pmeDef.name}${attrs}"""
    }

    println e {
        name 'E_SRC'
        pk 'ATTR_STRING', 'ATTR_LONG'
        attr ATTR_STRING: 'string', ATTR_LONG: 'long'

//    withFriend {
//        friend 'E_SRC'
//   }
    }
    n = "E_OUT"
    println msg {
        name n
        attr ATTR_STRING: 'ii', ATTR_LONG: '1333'

//    withFriend {
//        friend 'E_SRC'
//   }
    }
for ( i in 1..100)
{
    println i;
}
3.times{
    println 1
}

a=[1,2,3]
println a.join("|")
println a.findAll { it>1}
println a.max()
map=[1:"1",2:2]
map.each {println it}
aa=[1,2,3].collect{it*4}
print aa

int[]  array=[1,2,3]
println array
x=1
println x
def jsonSlurper = new JsonSlurper()
def object = jsonSlurper.parseText '''
    { "simple": 123,
      "fraction": 123.66,
      "exponential": 123e12
    }'''

assert object instanceof Map
assert object.simple.class == Integer
assert object.fraction.class == BigDecimal
assert object.exponential.class == BigDecimal
println object
println object.simple
def text = 'Dear "$firstname $lastname",\nSo nice to meet you in <% print city %>.\nSee you in ${month},\n${signed}'

def binding = ["firstname":"Sam", "lastname":"Pullara", "city":"San Francisco", "month":"December", "signed":"groovy-Dev"]

def engine = new groovy.text.SimpleTemplateEngine()
def template = engine.createTemplate(text).make(binding)

def result = 'Dear "Sam Pullara",\nSo nice to meet you in San Francisco.\nSee you in December,\ngroovy-Dev'

assert result == template.toString()
println template.toString()
