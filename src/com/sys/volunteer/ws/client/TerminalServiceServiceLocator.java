/**
 * TerminalServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sys.volunteer.ws.client;

public class TerminalServiceServiceLocator extends org.apache.axis.client.Service implements com.sys.volunteer.ws.client.TerminalServiceService {

    public TerminalServiceServiceLocator() {
    }


    public TerminalServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TerminalServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TerminalService
    private java.lang.String TerminalService_address = "http://219.136.207.190:8081/webservice/services/TerminalService";

    public java.lang.String getTerminalServiceAddress() {
        return TerminalService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TerminalServiceWSDDServiceName = "TerminalService";

    public java.lang.String getTerminalServiceWSDDServiceName() {
        return TerminalServiceWSDDServiceName;
    }

    public void setTerminalServiceWSDDServiceName(java.lang.String name) {
        TerminalServiceWSDDServiceName = name;
    }

    public com.sys.volunteer.ws.client.TerminalService getTerminalService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TerminalService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTerminalService(endpoint);
    }

    public com.sys.volunteer.ws.client.TerminalService getTerminalService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub _stub = new com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getTerminalServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTerminalServiceEndpointAddress(java.lang.String address) {
        TerminalService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.sys.volunteer.ws.client.TerminalService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub _stub = new com.sys.volunteer.ws.client.TerminalServiceSoapBindingStub(new java.net.URL(TerminalService_address), this);
                _stub.setPortName(getTerminalServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("TerminalService".equals(inputPortName)) {
            return getTerminalService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "TerminalServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://219.136.207.190:8081/webservice/services/TerminalService", "TerminalService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TerminalService".equals(portName)) {
            setTerminalServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
