package com.ih2ome.common.test.pay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ecc.emp.data.KeyedCollection;
import com.sdb.payclient.bean.exception.*;

/**
 * ģ�⣨KH0001�����ʶ���״̬��ѯ
 *
 * @author: zhuning090
 */
public class DemokhPayment_PAY_API {
    public static void main(String[] args) throws CsiiException {
        com.sdb.payclient.core.PayclientInterfaceUtil util = new com.sdb.payclient.core.PayclientInterfaceUtil();
        com.ecc.emp.data.KeyedCollection input = new com.ecc.emp.data.KeyedCollection("input");
        com.ecc.emp.data.KeyedCollection output = new com.ecc.emp.data.KeyedCollection("output");

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = formatter.format(date);  //ʱ��
        String datetamp = timestamp.substring(0, 8);  //����

        input.put("masterId", "2000311146");  //�̻��ţ�ע������������Ҫ�滻���̻��Լ��������̻���
        input.put("orderId", "2000311146" + datetamp + getOrderId());  //�����ţ��ϸ����ظ�ʽ���̻���+8λ����YYYYMMDD+8λ��ˮ

        input.put("currency", "RMB");
        input.put("customerId", "6222980067337248");//�ͻ��ţ��̻�����ͻ���Ψһ��ʶ
        input.put("amount", "1.21");
        input.put("remark", "forGodness");
        input.put("objectName", "1111");
        input.put("paydate", timestamp);
        input.put("validtime", "0");
        input.put("payType", "05");//֧����ʽ��01B2B���أ�02B2C���أ�03������ݣ�04ƽ������ݣ�05΢��ɨ��
        //input.put("payCardType","00");//���ء�ƽ�������������֧�������ͣ�����֧����ʽ������
        //input.put("issInsCode","PAB");//���ء�ƽ�������������֧�������ͣ�����֧����ʽ������
        //input.put("dateTime","201706241530");//ƽ�������֧��������(ʹ��khPayment_PAY_API���ص�dateTime)������֧����ʽ������
        //input.put("bindId","20003111462017051155340531");//ƽ�������֧�������ͣ�����֧����ʽ������
        //input.put("OpenId","20003111462017051155340531");//�������֧�������ͣ�����֧����ʽ������
        //input.put("cardStr","20003111462017051155340531");//ƽ��������¿�֧�������ͣ�����֧����ʽ������
        String returnurl = "123";
        String NOTIFYURL = "123";
        input.put("returnurl", returnurl);
        input.put("NOTIFYURL", NOTIFYURL);
        try {
            KeyedCollection signData = util.getSignData(input);
            String origs = (String) signData.getDataValue("orig");
            String sign = (String) signData.getDataValue("sign");
            System.out.println("��ȡǩ��Դ����=========================");
            System.out.println(origs);
            System.out.println("��ȡǩ��������=========================");
            System.out.println(sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            output = util.execute(input, "khPayment_PAY_API"); //ִ�з��ͣ�KH0001�����ʶ���״̬��ѯ���󣬲����ؽ������
            Object orig = output.getDataValue("orig");
            Object sign = output.getDataValue("sign");
            System.out.println("���з��ص�ǩ��Դ����=====================");
            System.out.println(orig);
            System.out.println("���з��ص�ǩ��������======================");
            System.out.println(sign);
            String errorCode = (String) output.getDataValue("errorCode");
            String errorMsg = (String) output.getDataValue("errorMsg");

            System.out.println("---���ʶ���״̬��ѯ�����ϸ��Ϣ---" + output);

            if ((errorCode == null || errorCode.equals("")) && (errorMsg == null || errorMsg.equals(""))) {
                /**
                 * ��ϸ������Ϣ�뿴ƽ�����п���֧���ӿ��ĵ�
                 */
                System.out.println("---����״̬---" + output.getDataValue("status"));
            } else {
                System.out.println("---������---" + output.getDataValue("errorCode"));
                System.out.println("---����˵��---" + output.getDataValue("errorMsg"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //����8λ�����
    private static String getOrderId() {
        String orderId;
        java.util.Random r = new java.util.Random();
        while (true) {
            int i = r.nextInt(99999999);
            if (i < 0) i = -i;
            orderId = String.valueOf(i);
            System.out.println("---���������---" + orderId);
            if (orderId.length() < 8) {
                System.out.println("---λ������8λ---" + orderId);
                continue;
            }
            if (orderId.length() >= 8) {
                orderId = orderId.substring(0, 8);
                System.out.println("---����8λ��ˮ---" + orderId);
                break;
            }
        }
        return orderId;
    }
}
