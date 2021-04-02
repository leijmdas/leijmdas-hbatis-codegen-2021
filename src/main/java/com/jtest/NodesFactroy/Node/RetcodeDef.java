package com.jtest.NodesFactroy.Node;

public enum RetcodeDef {
	
	RetNosuite(-99001), RetNoTest(-99002), NoFoundFile(-99003), CheckNotEQ(-99004), GetDBHELP_ERR(-99004), ParamNoValue(-99005);
	int	retcode;
	
	RetcodeDef(int code) {
		this.retcode = code;
	}
}
