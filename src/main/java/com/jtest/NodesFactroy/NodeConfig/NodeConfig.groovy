package com.jtest.NodesFactroy.NodeConfig

import com.google.gson.Gson

/**
 * Created by Administrator on 2016-04-02.
 */
class NodeConfig {
    def name;
    def type;

    def items = [:]

    def get(String key) {
        return items[key]
    }

    String toString() {
        new Gson().toJson(this);
    }
}
