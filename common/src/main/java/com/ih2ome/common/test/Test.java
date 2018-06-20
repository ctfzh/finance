package com.ih2ome.common.test;

import java.io.IOException;
import java.util.Map;

import com.pabank.sdk.PABankSDK;

//import com.pabank.sdk.entity.OpenCustAcctId;
public class Test {

    public static void main(String[] args) throws IOException {
        //JSON请求报文
        String req = "{\"AcctNo\":\"55423\",\"BankSeqNo\":\"2652685651566546\",\"BsnSeqNo\":\"545124541541613245\",\"BussTypeNo\":\"100157\",\"CnsmrSeqNo\":\"00062714985309527897\",\"CorpAgreementNo\":\"133565\",\"EndDate\":\"20170626\",\"RequestSeqNo\":\"78941216\",\"StartDate\":\"20170626\",\"TranStatus\":\"0\"}";
        //初始化配置
//        PABankSDK.init("D:\\mywork\\ih2ome\\SRC\\server\\finance\\common\\src\\main\\resources\\config\\config.properties");
        PABankSDK.init("config/config.properties");
//		PABankSDK.init("D:/软件/eclipse-jee-kepler-R-win321/eclipse-jee-kepler-R-win32/eclipse/workspace_test/Open-API1.1.3/conf/config.properties");
        //验证开发者
//		PABankSDK.getInstance().approveDev();
//   	服务调用，传入参数请求报文与服务ID
        Map<String, Object> returnMap = PABankSDK.getInstance().apiInter(req, "QuerySingleInterbankTran");
//		Map<String, Object> returnMap =PABankSDK.getInstance().apiInter(req,"QuerySingleInterbankTran","AAA");	
//      根据流水号新规则传入请求报文
//		Map<String, Object> returnMap =PABankSDK.getInstance().newApiInter(req,"QuerySingleInterbankTran");
//		Map<String, Object> returnMap =PABankSDK.getInstance().newApiInter(req,"QuerySingleInterbankTran","AAA");
//		System.err.println(returnMap.toString());

//		PABankSDK.getInstance().apiInter(c);

//		Map<String, Object> returnMap = PABankSDK.getInstance().apiInter(req);
//		Map<String, Object> returnMap = PABankSDK.getInstance().apiInter(req,"BBB");
//      根据流水号新规则传入请求报文
//		Map<String, Object> returnMap = PABankSDK.getInstance().newApiInter(req);
//	
//		CollPayBatchDealResult coll = new CollPayBatchDealResult();
//		coll.setFileType("0");
//		coll.setDate("20180131");
//		coll.setSerialNo("3");
//		coll.setCnsmrSeqNo("88888888");
//		coll.setCorpAgreementNo("Y000835312");
//		  OpenCustAcctId coll = new OpenCustAcctId();//会员子账户开立
//	      coll.setFunctionFlag("1");
//	      coll.setFundSummaryAcctNo("15000081084546");
//	      coll.setTranNetMemberCode("20180207");
//	      coll.setMemberProperty("00");
//	      coll.setUserNickname("开放平台");
//	      coll.setMobile("13750290453");
//	      coll.setEmail("88888@126.con");
//	      coll.setReservedMsg("测试");
//	      coll.setCnsmrSeqNo("88890001");
//	      coll.setMrchCode("3373");

////	
//		PABankSDK.getInstance().apiInter(c);
//		
//		Map<String, Object> returnMap = PABankSDK.getInstance().apiInter(coll);
//		Map<String, Object> returnMap = PABankSDK.getInstance().apiInter(coll,"BBB");
//      根据流水号新规则传入请求报文
//		Map<String, Object> returnMap = PABankSDK.getInstance().newApiInter(coll);
//		Map<String, Object> returnMap = PABankSDK.getInstance().newApiInter(coll,"BBB");

        System.err.println(returnMap);
    }

}
