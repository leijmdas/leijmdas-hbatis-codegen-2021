package com.jtest.NodesFactroy.Inject

import com.jtest.NodesFactroy.Node.DBNode
import com.jtest.NodesFactroy.Node.JDbNode
import com.jtest.NodesFactroy.Node.JProxyNode
import com.jtest.NodesFactroy.NodeConfig.ConfigParser
import com.jtest.NodesFactroy.NodeConfig.DataNode
import com.jtest.annotation.JTestInject

import java.lang.reflect.Field

class NodesLoader implements INodesLoader {
    void load(Object owner) {
        for (Field f in owner.class.getDeclaredFields()) {
            f.setAccessible(true)

            if (f.isAnnotationPresent(Inject.class) && null == f.get(owner)
                    || f.isAnnotationPresent(JTestInject.class) && null == f.get(owner)) {
                Inject inj = f.getAnnotation(Inject.class)
                JTestInject jinj = f.getAnnotation(JTestInject.class)
                String filename = inj == null ? jinj.filename() : inj.filename();
                String value = inj == null ? jinj.value() : inj.value();


                if (DataNode.class.isAssignableFrom(f.getType())) {
                    loadConfig(f, owner, "/test/data/"+filename,  value)
                } else
                if (DBNode.class.isAssignableFrom(f.getType())) {
                    loadConfig(f, owner, "/test/conf/dbnode.xml",  value )
                }else
                if (JDbNode.class.isAssignableFrom(f.getType())) {
                    loadConfig(f, owner, "/test/conf/dbnode.xml", value)
                }
                else if (JProxyNode.class.isAssignableFrom(f.getType())) {
                    loadConfig(f, owner, "/test/conf/jproxy.xml",  value )
                }
                else
                {
                    loadConfig(f, owner, "/test/conf/"+filename,  value  )
                }


            }
        }
    }

    void load0(Object owner) {
        for (Field f in owner.class.getDeclaredFields()) {
            f.setAccessible(true)
            if (f.isAnnotationPresent(Inject.class) && null == f.get(owner)
                || f.isAnnotationPresent(JTestInject.class) && null == f.get(owner) ) {
                Inject inj = f.getAnnotation(Inject.class)
                if (DataNode.class.isAssignableFrom(f.getType())) {
                    loadConfig(f, owner, "/test/data/"+inj.filename(), inj.value())
                } else
                if (DBNode.class.isAssignableFrom(f.getType())) {
                    loadConfig(f, owner, "/test/conf/dbnode.xml", inj.value())
                }else
                if (JDbNode.class.isAssignableFrom(f.getType())) {
                    loadConfig(f, owner, "/test/conf/dbnode.xml", inj.value())
                }
                else if (JProxyNode.class.isAssignableFrom(f.getType())) {
                    loadConfig(f, owner, "/test/conf/jproxy.xml", inj.value())
                }
                else
                {
                    loadConfig(f, owner, "/test/conf/"+inj.filename(), inj.value())
                }


            }
        }
    }

    def loadConfig(Field f, Object owner, String filename, String key) {
        f.setAccessible(true);
        def config = ConfigParser.parseConfig(filename, key)
        f.set(owner, newObject(f, config))
    }

    def newObject (Field f,Map<String, Object> params)
    {
        def obj =f.getType().newInstance();
        for(String k in params.keySet())
        {
            for(Field ff in f.getType().getSuperclass().getDeclaredFields())
            {
                ff.accessible=true;
                if(ff.name==k)
                {
                    ff.set(obj,params.get(k))
                }
            }
            for(Field ff in f.getType().getDeclaredFields())
            {
                ff.accessible=true;
                if(ff.name==k)
                {
                    ff.set(obj,params.get(k))
                }
            }
        }
        obj
    }


}
