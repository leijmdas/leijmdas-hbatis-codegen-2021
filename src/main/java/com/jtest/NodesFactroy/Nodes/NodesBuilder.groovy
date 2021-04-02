package com.jtest.NodesFactroy.Nodes

import com.jtest.NodesFactroy.Inject.NodesLoader

/**
 * Created by Administrator on 2012-04-02.
 */

class NodesBuilder {
    NodesBuilder() {
          new NodesLoader().load(this)

    }
}