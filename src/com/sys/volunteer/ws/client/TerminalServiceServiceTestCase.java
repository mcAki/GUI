/**
 * TerminalServiceServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sys.volunteer.ws.client;

public class TerminalServiceServiceTestCase extends junit.framework.TestCase {
    public TerminalServiceServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void testTerminalServiceWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalServiceAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1TerminalServiceGetDirectAreaInfo() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalDirectSrvInfoResponse value = null;
        value = binding.getDirectAreaInfo(new cn.epaylinks.www.TerminalDirectSrvInfoRequest());
        // TBD - validate results
    }

    public void test2TerminalServiceDirectOrderStateQuery() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalDirectStateResponse value = null;
        value = binding.directOrderStateQuery(new cn.epaylinks.www.TerminalDirectStateRequest());
        // TBD - validate results
    }

    public void test3TerminalServiceDirectCharge() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalDirectChargeResponse value = null;
        value = binding.directCharge(new cn.epaylinks.www.TerminalDirectChargeRequest());
        // TBD - validate results
    }

    public void test4TerminalServiceGetDirectSrvInfo() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalDirectSrvInfoResponse value = null;
        value = binding.getDirectSrvInfo(new cn.epaylinks.www.TerminalDirectSrvInfoRequest());
        // TBD - validate results
    }

    public void test5TerminalServiceTerminalQuerySchemeInfo() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String[] value = null;
        value = binding.terminalQuerySchemeInfo(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test6TerminalServiceTerminalGetPrice() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.terminalGetPrice(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test7TerminalServiceTerminalNewOrder() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.terminalNewOrder(new java.lang.String(), 0, 0, 0, new java.lang.String[0], new java.lang.String[0], new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test8TerminalServiceTerminalUpdateOrder() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.terminalUpdateOrder(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test9TerminalServiceTerminalCompleteOrder() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.terminalCompleteOrder(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test10TerminalServiceTerminalDeleteOrder() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.terminalDeleteOrder(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test11TerminalServiceTerminalGetOrderInfo() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String[] value = null;
        value = binding.terminalGetOrderInfo(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test12TerminalServiceTerminalQueryCLAllTickets() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalQueryCLAllTicketResponse value = null;
        value = binding.terminalQueryCLAllTickets(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test13TerminalServiceTerminalBuyCLTicket() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalBuyCLTicketResponse value = null;
        value = binding.terminalBuyCLTicket(new java.lang.String(), new java.lang.String(), new java.lang.String(), 0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test14TerminalServiceTerminalCancelCLOrder() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalCancelCLTicketResponse value = null;
        value = binding.terminalCancelCLOrder(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test15TerminalServiceTerminalDetainCLOrder() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalDetainCLTicketResponse value = null;
        value = binding.terminalDetainCLOrder(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test16TerminalServiceGetStoreBalance() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalStoreCreditResponse value = null;
        value = binding.getStoreBalance(new cn.epaylinks.www.TerminalStoreCreditRequest());
        // TBD - validate results
    }

    public void test17TerminalServiceGetStoreCredit() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalStoreCreditResponse value = null;
        value = binding.getStoreCredit(new cn.epaylinks.www.TerminalStoreCreditRequest());
        // TBD - validate results
    }

    public void test18TerminalServiceTerminalAirOrderQueryNew() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalAirOrderQueryResponseNew value = null;
        value = binding.terminalAirOrderQueryNew(new cn.epaylinks.www.TerminalAirOrderQueryRequestNew());
        // TBD - validate results
    }

    public void test19TerminalServiceGetDownLoadCard() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalDownLoadResponse value = null;
        value = binding.getDownLoadCard(new cn.epaylinks.www.TerminalDownLoadRequest());
        // TBD - validate results
    }

    public void test20TerminalServiceTerminalLogin() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.LoginResponse value = null;
        value = binding.terminalLogin(new cn.epaylinks.www.LoginRequest());
        // TBD - validate results
    }

    public void test21TerminalServiceGetTerminalCardType() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalGetCardTypeResponse value = null;
        value = binding.getTerminalCardType(new cn.epaylinks.www.TerminalGetCardTypeRequest());
        // TBD - validate results
    }

    public void test22TerminalServiceTerminalGetTradingSeq() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalGetTradingSeqResponse value = null;
        value = binding.terminalGetTradingSeq(new cn.epaylinks.www.TerminalGetTradingSeqRequest());
        // TBD - validate results
    }

    public void test23TerminalServiceGetDownLoadCard2() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalDownLoad2Response value = null;
        value = binding.getDownLoadCard2(new cn.epaylinks.www.TerminalDownLoadRequest());
        // TBD - validate results
    }

    public void test24TerminalServiceTerminalReturnCard() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalReturnResponse value = null;
        value = binding.terminalReturnCard(new cn.epaylinks.www.TerminalReturnRequest());
        // TBD - validate results
    }

    public void test25TerminalServiceGetReDownLoadCard() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalDownLoadResponse value = null;
        value = binding.getReDownLoadCard(new cn.epaylinks.www.TerminalReDownCardRequest());
        // TBD - validate results
    }

    public void test26TerminalServiceGetBatchDownLoadCard() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalDownLoadResponse value = null;
        value = binding.getBatchDownLoadCard(new cn.epaylinks.www.TerminalDownLoadRequest());
        // TBD - validate results
    }

    public void test27TerminalServiceTerminalDownloadQuery() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalDownQueryResponse value = null;
        value = binding.terminalDownloadQuery(new cn.epaylinks.www.TerminalDownQueryRequest());
        // TBD - validate results
    }

    public void test28TerminalServiceTerminalDownloadStat() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalDownStatResponse value = null;
        value = binding.terminalDownloadStat(new cn.epaylinks.www.TerminalDownStatRequest());
        // TBD - validate results
    }

    public void test29TerminalServiceTerminalUploadDownLoadOK() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalUploadDownOKResponse value = null;
        value = binding.terminalUploadDownLoadOK(new cn.epaylinks.www.TerminalUploadDownOKRequest());
        // TBD - validate results
    }

    public void test30TerminalServiceTerminalBatchUploadPrintLoadOK() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalBatchUploadPrintOKReponse value = null;
        value = binding.terminalBatchUploadPrintLoadOK(new cn.epaylinks.www.TerminalBatchUploadPrintOKRequest());
        // TBD - validate results
    }

    public void test31TerminalServiceTerminalModifyPassword() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalModifyPasswordResponse value = null;
        value = binding.terminalModifyPassword(new cn.epaylinks.www.TerminalModifyPasswordRequest());
        // TBD - validate results
    }

    public void test32TerminalServiceGetTerminalMessage() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalMessageResponse value = null;
        value = binding.getTerminalMessage(new cn.epaylinks.www.TerminalMessageRequest());
        // TBD - validate results
    }

    public void test33TerminalServiceTerminalAccountQuery() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalAccountQueryResponse value = null;
        value = binding.terminalAccountQuery(new cn.epaylinks.www.TerminalAccountQueryRequest());
        // TBD - validate results
    }

    public void test34TerminalServiceTerminalAirDeposit() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalAirDepositResponse value = null;
        value = binding.terminalAirDeposit(new cn.epaylinks.www.TerminalAirDepositRequest());
        // TBD - validate results
    }

    public void test35TerminalServiceTerminalAirReversal() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalAirReversalResponse value = null;
        value = binding.terminalAirReversal(new cn.epaylinks.www.TerminalAirReversalRequest());
        // TBD - validate results
    }

    public void test36TerminalServiceTerminalAirOrderQuery() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalAirOrderQueryResponse value = null;
        value = binding.terminalAirOrderQuery(new cn.epaylinks.www.TerminalAirOrderQueryRequest());
        // TBD - validate results
    }

    public void test37TerminalServiceTerminalAirDepositNew() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalAirDepositResponseNew value = null;
        value = binding.terminalAirDepositNew(new cn.epaylinks.www.TerminalAirDepositRequestNew());
        // TBD - validate results
    }

    public void test38TerminalServiceTerminalAirDeposit3() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalAirDepositResponseNew value = null;
        value = binding.terminalAirDeposit3(new cn.epaylinks.www.TerminalAirDepositRequestNew());
        // TBD - validate results
    }

    public void test39TerminalServiceTerminalAirReversalNew() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalAirReversalResponseNew value = null;
        value = binding.terminalAirReversalNew(new cn.epaylinks.www.TerminalAirReversalRequestNew());
        // TBD - validate results
    }

    public void test40TerminalServiceDirectQuery() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalDirectQueryResponse value = null;
        value = binding.directQuery(new cn.epaylinks.www.TerminalDirectQueryRequest());
        // TBD - validate results
    }

    public void test41TerminalServiceTerminalGetSalePrice() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.www.TerminalGetSalePriceResponse value = null;
        value = binding.terminalGetSalePrice(new cn.epaylinks.www.TerminalGetSalePriceRequest());
        // TBD - validate results
    }

    public void test42TerminalServiceEproductBinding() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.eproductBinding(new java.lang.String());
        // TBD - validate results
    }

    public void test43TerminalServiceCheckCardStock() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.checkCardStock(new java.lang.String());
        // TBD - validate results
    }

    public void test44TerminalServiceRechargeTurnStoredQry() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.ws.vo.RechargeTurnStoredQryResponse value = null;
        value = binding.rechargeTurnStoredQry(new cn.epaylinks.ws.vo.RechargeTurnStoredQryRequest());
        // TBD - validate results
    }

    public void test45TerminalServiceRechargeTurnStored() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        cn.epaylinks.ws.vo.RechargeTurnStoredResponse value = null;
        value = binding.rechargeTurnStored(new cn.epaylinks.ws.vo.RechargeTurnStoredRequest());
        // TBD - validate results
    }

    public void test46TerminalServiceConfigMessage() throws Exception {
        com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub binding;
        try {
            binding = (com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub)
                          new com.sys.volunteer.ws.client.TerminalServiceServiceLocator().getTerminalService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.configMessage(new java.lang.String());
        // TBD - validate results
    }

}
