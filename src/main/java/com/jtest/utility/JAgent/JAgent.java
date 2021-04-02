package com.jtest.utility.JAgent;

/**
 * Created by Administrator on 2017/7/1.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Map;

/**
 * 需要被管理的类（普通类）
 */
public class JAgent implements JAgentMBean,Serializable{
    transient static Map<String,IWorker> map_= Maps.newLinkedHashMap();

    JSONObject buildRet(){
        JSONObject j = JSON.parseObject("{}");
        j.put("retCode",0);
        j.put("retMsg","ok");

        return j ;
    }
    JSONObject checkParam(String p_json){
        JSONObject j = JSON.parseObject( p_json );
        JSONObject ret =buildRet( );

        if(j.get ("type")==null){
            ret.put("retCode",-2);
        }
        return ret ;
    }

    public StringBuilder execute(String p_json) {
        JSONObject j_ret = checkParam(p_json);
        if (p_json.isEmpty()) {
            System.out.println("{}");
            j_ret.put("retCode", -1);

        } else {

            JSONObject j = JSON.parseObject(p_json);
            System.out.println(j);
            String clsname = j.getString("type");
            try {
                Class<?> cls = Class.forName(clsname);
                IWorker worker = (IWorker) cls.newInstance();
                StringBuilder ret = worker.execute(p_json);
            } catch (InstantiationException e) {
                j_ret.put("retCode", -1);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                j_ret.put("retCode", -1);
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                j_ret.put("retCode", -1);
                e.printStackTrace();
            }
            j_ret.put("result", p_json);

        }
        return new StringBuilder(j_ret.toString());
    }

}
