package testcase.study.groovy
/**
 * Created by Administrator on 2016-04-01.
 */
class Test_XMLDemo_Parser {
    static void main(args) {
        def xml = """
<response version-api="2.0">
    <value>
        <books>
            <book id="2">
                <title>Don Xijote</title>
                <author id="1">Manuel De Cervantes</author>
            </book>
        </books>
    </value>
</response>
"""

        def response = new XmlParser().parseText(xml)

/* Use the same syntax as groovy.xml.MarkupBuilder */
        response.value.books.book[0].replaceNode {
            book(id: "3") {
                title("To Kill a Mockingbird")
                author(id: "3", "Harper Lee")
            }
        }

        def newNode = response.value.books.book[0]

        assert newNode.name() == "book"
        assert newNode.@id == "3"
        assert newNode.title.text() == "To Kill a Mockingbird"
        assert newNode.author.text() == "Harper Lee"
        assert newNode.author.@id.first() == "3"
        def x = """
<cfg>
<db name="dbnode_ide"  type='oracle'>
<item name="url" value="2"/>
<item name="user" value="3"/>
<item name="pwd" value="4"/>
<item name="driver" value="5"/>
</db>
</cfg>"""

        xml = Test_XMLDemo_Parser.class.getResource("/conf/dbnode.xml").getText()
        response = new XmlParser().parseText(xml)
        for (dbnode in response.db) {
            assert dbnode.name() == "db"
            println dbnode.@name
            println dbnode.@type
            dbnode.each {
                println it.@name
                println it.@value
            }

        }

        '''        <cfg>
        <node name="camweb" url="http://127.0.0.1:1122/RTD">
        <item name="dsf" value='webproxy.dsfImpl'/>
        <item name="unit" value='webproxy.unitImpl'/>
        </node>
</cfg>'''

        def webproxy = [:]
        xml = Test_XMLDemo_Parser.class.getResource("/conf/webproxy.xml").getText()
        response = new XmlParser().parseText(xml)
        for (node in response.node) {
            assert node.name() == "node"

            println node.@name
            println node.@url
            webproxy.name = node.@name
            webproxy.url = node.@url

            node.each {
                println it.@name
                println it.@value
            }
            println webproxy
        }


    }




}
