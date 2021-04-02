package com.jtest.NodesFactroy.Node

import com.google.gson.Gson

/**
 * Created by Administrator on 2016-04-02.
 */
class JProxyNode {
  String name;
  String url;
  String type;

  String toString() {
    return new Gson().toJson(this);
  }
}
