/**
 * TerminalServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sys.volunteer.ws.client;

public interface TerminalServiceService extends javax.xml.rpc.Service {
    public java.lang.String getTerminalServiceAddress();

    public com.sys.volunteer.ws.client.TerminalService getTerminalService() throws javax.xml.rpc.ServiceException;

    public com.sys.volunteer.ws.client.TerminalService getTerminalService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
