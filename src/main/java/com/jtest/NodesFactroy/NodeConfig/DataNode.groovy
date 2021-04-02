package com.jtest.NodesFactroy.NodeConfig

class DataNode extends NodeConfig{

    //def tableName
    Map<String, String> key_types = new LinkedHashMap<>()

    def items_data_filter() {
        def items_data=[:]
        items_data = items.clone()
        items_data.remove("name")
        items_data.remove("type")
        items_data
    }

    List<Map<String,String>> all_inner() {

        def items_data = items_data_filter();
        List<Map<String,String>> records = new ArrayList<>();
        int num=0;
        items_data.each {
            int n=it.getValue().split('\\|').size()
            if(n>num){
                num=n
            }
        }
        for(int i in 1..num) {
            records<<new LinkedHashMap<String,String>()
        }
        items_data.each {
            def record=it.getValue().split('\\|')

            for(int j in 1..num){
                def v=j<=record.size()?record.getAt(j-1):record.getAt(0)
                records.getAt(j-1).put(it.getKey(),v);
            }
        }

        return records;
    }

    List<String> all() {
        all_inner().collect {
            it.values().join("|")
        }
    }


    List<String> allInsert() {
        def ret = []
        all_inner().collect{
            r->r.collect {
                v->
                    if(key_types.get(v.getKey()).equals('STRING'))
                        "'"+v.getValue()+"'"
                    else if(key_types.get(v.getKey()).equals('DATE'))
                        "'"+v.getValue()+"'"

                    else
                        v.getValue()
            }.join(",")
        }.each {
            ret << "insert into $name(${key_types.keySet().join(',')}) values($it)"

        }

        ret
    }
}
