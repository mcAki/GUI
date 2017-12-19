/**
 * TerminalService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sys.volunteer.ws.client;

public interface TerminalService extends java.rmi.Remote {
    public cn.epaylinks.www.TerminalDirectSrvInfoResponse getDirectAreaInfo(cn.epaylinks.www.TerminalDirectSrvInfoRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalDirectStateResponse directOrderStateQuery(cn.epaylinks.www.TerminalDirectStateRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalDirectChargeResponse directCharge(cn.epaylinks.www.TerminalDirectChargeRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalDirectSrvInfoResponse getDirectSrvInfo(cn.epaylinks.www.TerminalDirectSrvInfoRequest request) throws java.rmi.RemoteException;
    public java.lang.String[] terminalQuerySchemeInfo(java.lang.String date, java.lang.String station, java.lang.String ownerdepot, java.lang.String time, java.lang.String storeNo, java.lang.String terminalNo, java.lang.String sign) throws java.rmi.RemoteException;
    public java.lang.String terminalGetPrice(java.lang.String date, java.lang.String no, java.lang.String stCode, java.lang.String depot, java.lang.String ownerdepot, java.lang.String area, java.lang.String storeNo, java.lang.String terminalNo, java.lang.String sign) throws java.rmi.RemoteException;
    public java.lang.String terminalNewOrder(java.lang.String date, int ticketnum, float ticketPrice, float baf, java.lang.String[] schemeInfo, java.lang.String[] clientInfo, java.lang.String storeNo, java.lang.String terminalNo, java.lang.String sign) throws java.rmi.RemoteException;
    public java.lang.String terminalUpdateOrder(java.lang.String orderID, java.lang.String storeNo, java.lang.String terminalNo, java.lang.String userAcc, java.lang.String sign) throws java.rmi.RemoteException;
    public java.lang.String terminalCompleteOrder(java.lang.String orderID, java.lang.String storeNo, java.lang.String terminalNo, java.lang.String sign) throws java.rmi.RemoteException;
    public java.lang.String terminalDeleteOrder(java.lang.String orderID, java.lang.String storeNo, java.lang.String terminalNo, java.lang.String sign) throws java.rmi.RemoteException;
    public java.lang.String[] terminalGetOrderInfo(java.lang.String orderID, java.lang.String storeNo, java.lang.String terminalNo, java.lang.String sign) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalQueryCLAllTicketResponse terminalQueryCLAllTickets(java.lang.String storeNo, java.lang.String terminalNo, java.lang.String orderInfo, java.lang.String sign) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalBuyCLTicketResponse terminalBuyCLTicket(java.lang.String storeNo, java.lang.String terminalNo, java.lang.String orderInfo, double priceAmount, java.lang.String oper_acc, java.lang.String payType, java.lang.String payLinkNo, java.lang.String sign) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalCancelCLTicketResponse terminalCancelCLOrder(java.lang.String storeNo, java.lang.String terminalNo, java.lang.String orderInfo, java.lang.String sign) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalDetainCLTicketResponse terminalDetainCLOrder(java.lang.String storeNo, java.lang.String terminalNo, java.lang.String orderInfo, java.lang.String sign) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalStoreCreditResponse getStoreBalance(cn.epaylinks.www.TerminalStoreCreditRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalStoreCreditResponse getStoreCredit(cn.epaylinks.www.TerminalStoreCreditRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalAirOrderQueryResponseNew terminalAirOrderQueryNew(cn.epaylinks.www.TerminalAirOrderQueryRequestNew request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalDownLoadResponse getDownLoadCard(cn.epaylinks.www.TerminalDownLoadRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.LoginResponse terminalLogin(cn.epaylinks.www.LoginRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalGetCardTypeResponse getTerminalCardType(cn.epaylinks.www.TerminalGetCardTypeRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalGetTradingSeqResponse terminalGetTradingSeq(cn.epaylinks.www.TerminalGetTradingSeqRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalDownLoad2Response getDownLoadCard2(cn.epaylinks.www.TerminalDownLoadRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalReturnResponse terminalReturnCard(cn.epaylinks.www.TerminalReturnRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalDownLoadResponse getReDownLoadCard(cn.epaylinks.www.TerminalReDownCardRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalDownLoadResponse getBatchDownLoadCard(cn.epaylinks.www.TerminalDownLoadRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalDownQueryResponse terminalDownloadQuery(cn.epaylinks.www.TerminalDownQueryRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalDownStatResponse terminalDownloadStat(cn.epaylinks.www.TerminalDownStatRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalUploadDownOKResponse terminalUploadDownLoadOK(cn.epaylinks.www.TerminalUploadDownOKRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalBatchUploadPrintOKReponse terminalBatchUploadPrintLoadOK(cn.epaylinks.www.TerminalBatchUploadPrintOKRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalModifyPasswordResponse terminalModifyPassword(cn.epaylinks.www.TerminalModifyPasswordRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalMessageResponse getTerminalMessage(cn.epaylinks.www.TerminalMessageRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalAccountQueryResponse terminalAccountQuery(cn.epaylinks.www.TerminalAccountQueryRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalAirDepositResponse terminalAirDeposit(cn.epaylinks.www.TerminalAirDepositRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalAirReversalResponse terminalAirReversal(cn.epaylinks.www.TerminalAirReversalRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalAirOrderQueryResponse terminalAirOrderQuery(cn.epaylinks.www.TerminalAirOrderQueryRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalAirDepositResponseNew terminalAirDepositNew(cn.epaylinks.www.TerminalAirDepositRequestNew request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalAirDepositResponseNew terminalAirDeposit3(cn.epaylinks.www.TerminalAirDepositRequestNew request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalAirReversalResponseNew terminalAirReversalNew(cn.epaylinks.www.TerminalAirReversalRequestNew request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalDirectQueryResponse directQuery(cn.epaylinks.www.TerminalDirectQueryRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.www.TerminalGetSalePriceResponse terminalGetSalePrice(cn.epaylinks.www.TerminalGetSalePriceRequest request) throws java.rmi.RemoteException;
    public java.lang.String eproductBinding(java.lang.String requestXml) throws java.rmi.RemoteException;
    public java.lang.String checkCardStock(java.lang.String request) throws java.rmi.RemoteException;
    public cn.epaylinks.ws.vo.RechargeTurnStoredQryResponse rechargeTurnStoredQry(cn.epaylinks.ws.vo.RechargeTurnStoredQryRequest request) throws java.rmi.RemoteException;
    public cn.epaylinks.ws.vo.RechargeTurnStoredResponse rechargeTurnStored(cn.epaylinks.ws.vo.RechargeTurnStoredRequest req) throws java.rmi.RemoteException;
    public java.lang.String configMessage(java.lang.String requestXml) throws java.rmi.RemoteException;
}
