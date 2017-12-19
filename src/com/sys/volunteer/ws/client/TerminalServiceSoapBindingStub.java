/**
 * TerminalServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sys.volunteer.ws.client;

public class TerminalServiceSoapBindingStub extends org.apache.axis.client.Stub implements com.sys.volunteer.ws.client.TerminalService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[46];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
        _initOperationDesc5();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDirectAreaInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectSrvInfoRequest"), cn.epaylinks.www.TerminalDirectSrvInfoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectSrvInfoResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalDirectSrvInfoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDirectAreaInfoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("directOrderStateQuery");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectStateRequest"), cn.epaylinks.www.TerminalDirectStateRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectStateResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalDirectStateResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "directOrderStateQueryReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("directCharge");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectChargeRequest"), cn.epaylinks.www.TerminalDirectChargeRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectChargeResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalDirectChargeResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "directChargeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDirectSrvInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectSrvInfoRequest"), cn.epaylinks.www.TerminalDirectSrvInfoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectSrvInfoResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalDirectSrvInfoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDirectSrvInfoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalQuerySchemeInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "date"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Station"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ownerdepot"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "time"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "storeNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "terminalNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sign"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "ArrayOf_soapenc_string"));
        oper.setReturnClass(java.lang.String[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalQuerySchemeInfoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalGetPrice");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "date"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "no"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "stCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "depot"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ownerdepot"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "area"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "storeNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "terminalNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sign"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalGetPriceReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalNewOrder");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "date"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ticketnum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ticketPrice"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"), float.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Baf"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"), float.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "schemeInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "ArrayOf_soapenc_string"), java.lang.String[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "clientInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "ArrayOf_soapenc_string"), java.lang.String[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "storeNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "terminalNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sign"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalNewOrderReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalUpdateOrder");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "orderID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "storeNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "terminalNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "userAcc"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sign"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalUpdateOrderReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalCompleteOrder");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "orderID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "storeNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "terminalNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sign"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalCompleteOrderReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalDeleteOrder");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "orderID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "storeNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "terminalNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sign"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalDeleteOrderReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalGetOrderInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "OrderID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "storeNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "terminalNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sign"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "ArrayOf_soapenc_string"));
        oper.setReturnClass(java.lang.String[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalGetOrderInfoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalQueryCLAllTickets");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "storeNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "terminalNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "orderInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sign"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalQueryCLAllTicketResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalQueryCLAllTicketResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalQueryCLAllTicketsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalBuyCLTicket");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "storeNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "terminalNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "orderInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "priceAmount"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "oper_acc"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "payType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "payLinkNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sign"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalBuyCLTicketResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalBuyCLTicketResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalBuyCLTicketReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalCancelCLOrder");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "storeNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "terminalNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "orderInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sign"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalCancelCLTicketResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalCancelCLTicketResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalCancelCLOrderReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalDetainCLOrder");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "storeNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "terminalNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "orderInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sign"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDetainCLTicketResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalDetainCLTicketResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalDetainCLOrderReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getStoreBalance");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalStoreCreditRequest"), cn.epaylinks.www.TerminalStoreCreditRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalStoreCreditResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalStoreCreditResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getStoreBalanceReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getStoreCredit");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalStoreCreditRequest"), cn.epaylinks.www.TerminalStoreCreditRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalStoreCreditResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalStoreCreditResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getStoreCreditReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalAirOrderQueryNew");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirOrderQueryRequestNew"), cn.epaylinks.www.TerminalAirOrderQueryRequestNew.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirOrderQueryResponseNew"));
        oper.setReturnClass(cn.epaylinks.www.TerminalAirOrderQueryResponseNew.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalAirOrderQueryNewReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDownLoadCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownLoadRequest"), cn.epaylinks.www.TerminalDownLoadRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownLoadResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalDownLoadResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDownLoadCardReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalLogin");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "LoginRequest"), cn.epaylinks.www.LoginRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "LoginResponse"));
        oper.setReturnClass(cn.epaylinks.www.LoginResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalLoginReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTerminalCardType");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalGetCardTypeRequest"), cn.epaylinks.www.TerminalGetCardTypeRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalGetCardTypeResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalGetCardTypeResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getTerminalCardTypeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalGetTradingSeq");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalGetTradingSeqRequest"), cn.epaylinks.www.TerminalGetTradingSeqRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalGetTradingSeqResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalGetTradingSeqResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalGetTradingSeqReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDownLoadCard2");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownLoadRequest"), cn.epaylinks.www.TerminalDownLoadRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownLoad2Response"));
        oper.setReturnClass(cn.epaylinks.www.TerminalDownLoad2Response.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDownLoadCard2Return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalReturnCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalReturnRequest"), cn.epaylinks.www.TerminalReturnRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalReturnResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalReturnResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalReturnCardReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getReDownLoadCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalReDownCardRequest"), cn.epaylinks.www.TerminalReDownCardRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownLoadResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalDownLoadResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getReDownLoadCardReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBatchDownLoadCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownLoadRequest"), cn.epaylinks.www.TerminalDownLoadRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownLoadResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalDownLoadResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getBatchDownLoadCardReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalDownloadQuery");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownQueryRequest"), cn.epaylinks.www.TerminalDownQueryRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownQueryResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalDownQueryResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalDownloadQueryReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalDownloadStat");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownStatRequest"), cn.epaylinks.www.TerminalDownStatRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownStatResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalDownStatResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalDownloadStatReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalUploadDownLoadOK");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalUploadDownOKRequest"), cn.epaylinks.www.TerminalUploadDownOKRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalUploadDownOKResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalUploadDownOKResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalUploadDownLoadOKReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalBatchUploadPrintLoadOK");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalBatchUploadPrintOKRequest"), cn.epaylinks.www.TerminalBatchUploadPrintOKRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalBatchUploadPrintOKReponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalBatchUploadPrintOKReponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalBatchUploadPrintLoadOKReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalModifyPassword");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalModifyPasswordRequest"), cn.epaylinks.www.TerminalModifyPasswordRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalModifyPasswordResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalModifyPasswordResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalModifyPasswordReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTerminalMessage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalMessageRequest"), cn.epaylinks.www.TerminalMessageRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalMessageResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalMessageResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getTerminalMessageReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalAccountQuery");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAccountQueryRequest"), cn.epaylinks.www.TerminalAccountQueryRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAccountQueryResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalAccountQueryResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalAccountQueryReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalAirDeposit");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirDepositRequest"), cn.epaylinks.www.TerminalAirDepositRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirDepositResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalAirDepositResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalAirDepositReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[33] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalAirReversal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirReversalRequest"), cn.epaylinks.www.TerminalAirReversalRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirReversalResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalAirReversalResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalAirReversalReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[34] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalAirOrderQuery");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirOrderQueryRequest"), cn.epaylinks.www.TerminalAirOrderQueryRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirOrderQueryResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalAirOrderQueryResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalAirOrderQueryReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[35] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalAirDepositNew");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirDepositRequestNew"), cn.epaylinks.www.TerminalAirDepositRequestNew.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirDepositResponseNew"));
        oper.setReturnClass(cn.epaylinks.www.TerminalAirDepositResponseNew.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalAirDepositNewReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[36] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalAirDeposit3");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirDepositRequestNew"), cn.epaylinks.www.TerminalAirDepositRequestNew.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirDepositResponseNew"));
        oper.setReturnClass(cn.epaylinks.www.TerminalAirDepositResponseNew.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalAirDeposit3Return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[37] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalAirReversalNew");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirReversalRequestNew"), cn.epaylinks.www.TerminalAirReversalRequestNew.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirReversalResponseNew"));
        oper.setReturnClass(cn.epaylinks.www.TerminalAirReversalResponseNew.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalAirReversalNewReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[38] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("directQuery");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectQueryRequest"), cn.epaylinks.www.TerminalDirectQueryRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectQueryResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalDirectQueryResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "directQueryReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[39] = oper;

    }

    private static void _initOperationDesc5(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("terminalGetSalePrice");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalGetSalePriceRequest"), cn.epaylinks.www.TerminalGetSalePriceRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalGetSalePriceResponse"));
        oper.setReturnClass(cn.epaylinks.www.TerminalGetSalePriceResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "terminalGetSalePriceReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[40] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eproductBinding");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "requestXml"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "eproductBindingReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[41] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("checkCardStock");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "checkCardStockReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[42] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("rechargeTurnStoredQry");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://vo.ws.epaylinks.cn", "RechargeTurnStoredQryRequest"), cn.epaylinks.ws.vo.RechargeTurnStoredQryRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://vo.ws.epaylinks.cn", "RechargeTurnStoredQryResponse"));
        oper.setReturnClass(cn.epaylinks.ws.vo.RechargeTurnStoredQryResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "rechargeTurnStoredQryReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[43] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("rechargeTurnStored");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://vo.ws.epaylinks.cn", "RechargeTurnStoredRequest"), cn.epaylinks.ws.vo.RechargeTurnStoredRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://vo.ws.epaylinks.cn", "RechargeTurnStoredResponse"));
        oper.setReturnClass(cn.epaylinks.ws.vo.RechargeTurnStoredResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "rechargeTurnStoredReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[44] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("configMessage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "requestXml"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "configMessageReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[45] = oper;

    }

    public TerminalServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public TerminalServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public TerminalServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "ArrayOf_soapenc_string");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "ArrayOf_tns1_DirectOrderVo");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.DirectOrderVo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "DirectOrderVo");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "ArrayOf_tns1_TerminalAccountOperRec");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAccountOperRec[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAccountOperRec");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "ArrayOf_tns1_TerminalAirOrder");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAirOrder[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirOrder");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "ArrayOf_tns1_TerminalAirOrderNew");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAirOrderNew[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirOrderNew");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "ArrayOf_tns1_TerminalBatchUploadPrintOK");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalBatchUploadPrintOK[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalBatchUploadPrintOK");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "ArrayOf_tns1_TerminalCard");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalCard[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalCard");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "ArrayOf_tns1_TerminalCardType");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalCardType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalCardType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "ArrayOf_tns1_TerminalDownRecord");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDownRecord[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownRecord");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "ArrayOf_tns1_TerminalDownStat");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDownStat[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownStat");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "ArrayOf_tns1_TerminalPrintData");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalPrintData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalPrintData");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "ArrayOf_tns1_TerminalPrintFormat");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalPrintFormat[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalPrintFormat");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "ArrayOf_tns1_Ticket");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.Ticket[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "Ticket");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://vo.ws.epaylinks.cn", "RechargeTurnStoredQryRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.ws.vo.RechargeTurnStoredQryRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://vo.ws.epaylinks.cn", "RechargeTurnStoredQryResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.ws.vo.RechargeTurnStoredQryResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://vo.ws.epaylinks.cn", "RechargeTurnStoredRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.ws.vo.RechargeTurnStoredRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://vo.ws.epaylinks.cn", "RechargeTurnStoredResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.ws.vo.RechargeTurnStoredResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "DirectOrderVo");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.DirectOrderVo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "LoginRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.LoginRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "LoginResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.LoginResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAccountOperRec");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAccountOperRec.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAccountQueryRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAccountQueryRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAccountQueryResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAccountQueryResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirDepositRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAirDepositRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirDepositRequestNew");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAirDepositRequestNew.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirDepositResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAirDepositResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirDepositResponseNew");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAirDepositResponseNew.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirOrder");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAirOrder.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirOrderNew");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAirOrderNew.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirOrderQueryRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAirOrderQueryRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirOrderQueryRequestNew");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAirOrderQueryRequestNew.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirOrderQueryResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAirOrderQueryResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirOrderQueryResponseNew");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAirOrderQueryResponseNew.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirReversalRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAirReversalRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirReversalRequestNew");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAirReversalRequestNew.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirReversalResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAirReversalResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalAirReversalResponseNew");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalAirReversalResponseNew.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalBatchUploadPrintOK");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalBatchUploadPrintOK.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalBatchUploadPrintOKReponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalBatchUploadPrintOKReponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalBatchUploadPrintOKRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalBatchUploadPrintOKRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalBuyCLTicketResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalBuyCLTicketResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalCancelCLTicketResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalCancelCLTicketResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalCard");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalCard.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalCardType");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalCardType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDetainCLTicketResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDetainCLTicketResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectChargeRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDirectChargeRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectChargeResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDirectChargeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectQueryRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDirectQueryRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectQueryResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDirectQueryResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectSrvInfoRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDirectSrvInfoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectSrvInfoResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDirectSrvInfoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectStateRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDirectStateRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDirectStateResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDirectStateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownLoad2Response");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDownLoad2Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownLoadRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDownLoadRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownLoadResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDownLoadResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownQueryRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDownQueryRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownQueryResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDownQueryResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownRecord");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDownRecord.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownStat");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDownStat.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownStatRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDownStatRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalDownStatResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalDownStatResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalGetCardTypeRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalGetCardTypeRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalGetCardTypeResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalGetCardTypeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalGetSalePriceRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalGetSalePriceRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalGetSalePriceResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalGetSalePriceResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalGetTradingSeqRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalGetTradingSeqRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalGetTradingSeqResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalGetTradingSeqResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalMessageRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalMessageRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalMessageResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalMessageResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalModifyPasswordRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalModifyPasswordRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalModifyPasswordResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalModifyPasswordResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalPrintData");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalPrintData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalPrintFormat");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalPrintFormat.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalQueryCLAllTicketResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalQueryCLAllTicketResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalReDownCardRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalReDownCardRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalReturnRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalReturnRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalReturnResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalReturnResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalStoreCreditRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalStoreCreditRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalStoreCreditResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalStoreCreditResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalUploadDownOKRequest");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalUploadDownOKRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "TerminalUploadDownOKResponse");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.TerminalUploadDownOKResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epaylinks.cn", "Ticket");
            cachedSerQNames.add(qName);
            cls = cn.epaylinks.www.Ticket.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public cn.epaylinks.www.TerminalDirectSrvInfoResponse getDirectAreaInfo(cn.epaylinks.www.TerminalDirectSrvInfoRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "getDirectAreaInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalDirectSrvInfoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalDirectSrvInfoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalDirectSrvInfoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalDirectStateResponse directOrderStateQuery(cn.epaylinks.www.TerminalDirectStateRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "directOrderStateQuery"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalDirectStateResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalDirectStateResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalDirectStateResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalDirectChargeResponse directCharge(cn.epaylinks.www.TerminalDirectChargeRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "directCharge"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalDirectChargeResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalDirectChargeResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalDirectChargeResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalDirectSrvInfoResponse getDirectSrvInfo(cn.epaylinks.www.TerminalDirectSrvInfoRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "getDirectSrvInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalDirectSrvInfoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalDirectSrvInfoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalDirectSrvInfoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String[] terminalQuerySchemeInfo(java.lang.String date, java.lang.String station, java.lang.String ownerdepot, java.lang.String time, java.lang.String storeNo, java.lang.String terminalNo, java.lang.String sign) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalQuerySchemeInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {date, station, ownerdepot, time, storeNo, terminalNo, sign});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String terminalGetPrice(java.lang.String date, java.lang.String no, java.lang.String stCode, java.lang.String depot, java.lang.String ownerdepot, java.lang.String area, java.lang.String storeNo, java.lang.String terminalNo, java.lang.String sign) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalGetPrice"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {date, no, stCode, depot, ownerdepot, area, storeNo, terminalNo, sign});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String terminalNewOrder(java.lang.String date, int ticketnum, float ticketPrice, float baf, java.lang.String[] schemeInfo, java.lang.String[] clientInfo, java.lang.String storeNo, java.lang.String terminalNo, java.lang.String sign) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalNewOrder"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {date, new java.lang.Integer(ticketnum), new java.lang.Float(ticketPrice), new java.lang.Float(baf), schemeInfo, clientInfo, storeNo, terminalNo, sign});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String terminalUpdateOrder(java.lang.String orderID, java.lang.String storeNo, java.lang.String terminalNo, java.lang.String userAcc, java.lang.String sign) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalUpdateOrder"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {orderID, storeNo, terminalNo, userAcc, sign});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String terminalCompleteOrder(java.lang.String orderID, java.lang.String storeNo, java.lang.String terminalNo, java.lang.String sign) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalCompleteOrder"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {orderID, storeNo, terminalNo, sign});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String terminalDeleteOrder(java.lang.String orderID, java.lang.String storeNo, java.lang.String terminalNo, java.lang.String sign) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalDeleteOrder"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {orderID, storeNo, terminalNo, sign});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String[] terminalGetOrderInfo(java.lang.String orderID, java.lang.String storeNo, java.lang.String terminalNo, java.lang.String sign) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalGetOrderInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {orderID, storeNo, terminalNo, sign});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalQueryCLAllTicketResponse terminalQueryCLAllTickets(java.lang.String storeNo, java.lang.String terminalNo, java.lang.String orderInfo, java.lang.String sign) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalQueryCLAllTickets"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {storeNo, terminalNo, orderInfo, sign});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalQueryCLAllTicketResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalQueryCLAllTicketResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalQueryCLAllTicketResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalBuyCLTicketResponse terminalBuyCLTicket(java.lang.String storeNo, java.lang.String terminalNo, java.lang.String orderInfo, double priceAmount, java.lang.String oper_acc, java.lang.String payType, java.lang.String payLinkNo, java.lang.String sign) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalBuyCLTicket"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {storeNo, terminalNo, orderInfo, new java.lang.Double(priceAmount), oper_acc, payType, payLinkNo, sign});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalBuyCLTicketResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalBuyCLTicketResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalBuyCLTicketResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalCancelCLTicketResponse terminalCancelCLOrder(java.lang.String storeNo, java.lang.String terminalNo, java.lang.String orderInfo, java.lang.String sign) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalCancelCLOrder"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {storeNo, terminalNo, orderInfo, sign});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalCancelCLTicketResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalCancelCLTicketResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalCancelCLTicketResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalDetainCLTicketResponse terminalDetainCLOrder(java.lang.String storeNo, java.lang.String terminalNo, java.lang.String orderInfo, java.lang.String sign) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalDetainCLOrder"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {storeNo, terminalNo, orderInfo, sign});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalDetainCLTicketResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalDetainCLTicketResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalDetainCLTicketResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalStoreCreditResponse getStoreBalance(cn.epaylinks.www.TerminalStoreCreditRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "getStoreBalance"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalStoreCreditResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalStoreCreditResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalStoreCreditResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalStoreCreditResponse getStoreCredit(cn.epaylinks.www.TerminalStoreCreditRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "getStoreCredit"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalStoreCreditResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalStoreCreditResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalStoreCreditResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalAirOrderQueryResponseNew terminalAirOrderQueryNew(cn.epaylinks.www.TerminalAirOrderQueryRequestNew request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalAirOrderQueryNew"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalAirOrderQueryResponseNew) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalAirOrderQueryResponseNew) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalAirOrderQueryResponseNew.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalDownLoadResponse getDownLoadCard(cn.epaylinks.www.TerminalDownLoadRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "getDownLoadCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalDownLoadResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalDownLoadResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalDownLoadResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.LoginResponse terminalLogin(cn.epaylinks.www.LoginRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalLogin"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.LoginResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.LoginResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.LoginResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalGetCardTypeResponse getTerminalCardType(cn.epaylinks.www.TerminalGetCardTypeRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "getTerminalCardType"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalGetCardTypeResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalGetCardTypeResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalGetCardTypeResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalGetTradingSeqResponse terminalGetTradingSeq(cn.epaylinks.www.TerminalGetTradingSeqRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalGetTradingSeq"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalGetTradingSeqResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalGetTradingSeqResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalGetTradingSeqResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalDownLoad2Response getDownLoadCard2(cn.epaylinks.www.TerminalDownLoadRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "getDownLoadCard2"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalDownLoad2Response) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalDownLoad2Response) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalDownLoad2Response.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalReturnResponse terminalReturnCard(cn.epaylinks.www.TerminalReturnRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalReturnCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalReturnResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalReturnResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalReturnResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalDownLoadResponse getReDownLoadCard(cn.epaylinks.www.TerminalReDownCardRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "getReDownLoadCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalDownLoadResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalDownLoadResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalDownLoadResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalDownLoadResponse getBatchDownLoadCard(cn.epaylinks.www.TerminalDownLoadRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "getBatchDownLoadCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalDownLoadResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalDownLoadResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalDownLoadResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalDownQueryResponse terminalDownloadQuery(cn.epaylinks.www.TerminalDownQueryRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalDownloadQuery"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalDownQueryResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalDownQueryResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalDownQueryResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalDownStatResponse terminalDownloadStat(cn.epaylinks.www.TerminalDownStatRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalDownloadStat"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalDownStatResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalDownStatResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalDownStatResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalUploadDownOKResponse terminalUploadDownLoadOK(cn.epaylinks.www.TerminalUploadDownOKRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalUploadDownLoadOK"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalUploadDownOKResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalUploadDownOKResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalUploadDownOKResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalBatchUploadPrintOKReponse terminalBatchUploadPrintLoadOK(cn.epaylinks.www.TerminalBatchUploadPrintOKRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalBatchUploadPrintLoadOK"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalBatchUploadPrintOKReponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalBatchUploadPrintOKReponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalBatchUploadPrintOKReponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalModifyPasswordResponse terminalModifyPassword(cn.epaylinks.www.TerminalModifyPasswordRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalModifyPassword"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalModifyPasswordResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalModifyPasswordResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalModifyPasswordResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalMessageResponse getTerminalMessage(cn.epaylinks.www.TerminalMessageRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "getTerminalMessage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalMessageResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalMessageResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalMessageResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalAccountQueryResponse terminalAccountQuery(cn.epaylinks.www.TerminalAccountQueryRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalAccountQuery"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalAccountQueryResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalAccountQueryResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalAccountQueryResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalAirDepositResponse terminalAirDeposit(cn.epaylinks.www.TerminalAirDepositRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalAirDeposit"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalAirDepositResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalAirDepositResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalAirDepositResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalAirReversalResponse terminalAirReversal(cn.epaylinks.www.TerminalAirReversalRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[34]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalAirReversal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalAirReversalResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalAirReversalResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalAirReversalResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalAirOrderQueryResponse terminalAirOrderQuery(cn.epaylinks.www.TerminalAirOrderQueryRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[35]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalAirOrderQuery"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalAirOrderQueryResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalAirOrderQueryResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalAirOrderQueryResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalAirDepositResponseNew terminalAirDepositNew(cn.epaylinks.www.TerminalAirDepositRequestNew request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[36]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalAirDepositNew"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalAirDepositResponseNew) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalAirDepositResponseNew) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalAirDepositResponseNew.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalAirDepositResponseNew terminalAirDeposit3(cn.epaylinks.www.TerminalAirDepositRequestNew request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[37]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalAirDeposit3"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalAirDepositResponseNew) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalAirDepositResponseNew) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalAirDepositResponseNew.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalAirReversalResponseNew terminalAirReversalNew(cn.epaylinks.www.TerminalAirReversalRequestNew request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[38]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalAirReversalNew"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalAirReversalResponseNew) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalAirReversalResponseNew) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalAirReversalResponseNew.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalDirectQueryResponse directQuery(cn.epaylinks.www.TerminalDirectQueryRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[39]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "directQuery"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalDirectQueryResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalDirectQueryResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalDirectQueryResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.www.TerminalGetSalePriceResponse terminalGetSalePrice(cn.epaylinks.www.TerminalGetSalePriceRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[40]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "terminalGetSalePrice"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.www.TerminalGetSalePriceResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.www.TerminalGetSalePriceResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.www.TerminalGetSalePriceResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String eproductBinding(java.lang.String requestXml) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[41]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "eproductBinding"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {requestXml});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String checkCardStock(java.lang.String request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[42]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "checkCardStock"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.ws.vo.RechargeTurnStoredQryResponse rechargeTurnStoredQry(cn.epaylinks.ws.vo.RechargeTurnStoredQryRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[43]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "rechargeTurnStoredQry"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.ws.vo.RechargeTurnStoredQryResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.ws.vo.RechargeTurnStoredQryResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.ws.vo.RechargeTurnStoredQryResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.epaylinks.ws.vo.RechargeTurnStoredResponse rechargeTurnStored(cn.epaylinks.ws.vo.RechargeTurnStoredRequest req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[44]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "rechargeTurnStored"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.epaylinks.ws.vo.RechargeTurnStoredResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.epaylinks.ws.vo.RechargeTurnStoredResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.epaylinks.ws.vo.RechargeTurnStoredResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String configMessage(java.lang.String requestXml) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[45]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://service.ws.epaylinks.cn", "configMessage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {requestXml});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
