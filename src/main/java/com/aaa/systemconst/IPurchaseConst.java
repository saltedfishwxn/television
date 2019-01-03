package com.aaa.systemconst;

public interface IPurchaseConst {
	
	//采购单状态记录
	
	Integer COMMIT_STATE=2;//采购单提交状态
	
	Integer	CHEXK_PASS=1;	//审核通过
	
	Integer	CHEXK_REJECT=3; //审核驳回
	
	Integer SEND_STATE=4; //货物发送中
	
	Integer RECEIVE_STATE=5;//已收货
	
}
