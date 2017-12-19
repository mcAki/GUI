<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>
	
        代理商自助服务系统(Version2.0.02_8013_200)
        
</title>
    

    <link href="http://jiasu.tsource.cn/CDN/DIY/20121018V1/CSS/Classic/base.css?version=20120922" rel="stylesheet" type="text/css" />
    <link href="http://jiasu.tsource.cn/CDN/DIY/20121018V1/CSS/Classic/MainFrame.css?version=20120922" rel="stylesheet" type="text/css" />
    <link href="http://jiasu.tsource.cn/CDN/DIY/20121018V1/CSS/Classic/toolbar.css?version=20120922" rel="stylesheet" type="text/css" />
    
    <link href="http://jiasu.tsource.cn/CDN/DIY/20121018V1/CSS/Classic/jquery-ui-1.8.4.custom.css?version=20120922" rel="stylesheet" type="text/css" />
    
    <link href="http://jiasu.tsource.cn/CDN/DIY/20121018V1/CSS/Classic/jquery.cluetip.css?version=20120922" rel="stylesheet" type="text/css" />
        
    <script type="text/javascript" src="http://jiasu.tsource.cn/CDN/DIY/20121018V1/Scripts/jquery/jquery-1.4.2.min.js?version=20120922" language="javascript"></script>
    <script type="text/javascript" src="http://jiasu.tsource.cn/CDN/DIY/20121018V1/Scripts/jquery/jquery.cluetip.js?version=20120922" language="javascript"></script>
    <script type="text/javascript" src="http://jiasu.tsource.cn/CDN/DIY/20121018V1/Scripts/jquery/jquery.validate.min.js?version=20120922" language="javascript"></script>
    <script type="text/javascript" src="http://jiasu.tsource.cn/CDN/DIY/20121018V1/Scripts/jquery/ui/jquery.ui.core.js?version=20120922" language="javascript"></script>
    <script type="text/javascript" src="http://jiasu.tsource.cn/CDN/DIY/20121018V1/Scripts/jquery/ui/jquery.ui.widget.js?version=20120922" language="javascript"></script>
    <script type="text/javascript" src="http://jiasu.tsource.cn/CDN/DIY/20121018V1/Scripts/jquery/ui/jquery.ui.tabs.js?version=20120922" language="javascript"></script>
    <script type="text/javascript" src="http://jiasu.tsource.cn/CDN/DIY/20121018V1/Scripts/jquery/ui/jquery.ui.datepicker.js?version=20120922" language="javascript"></script>
    <script type="text/javascript" src="http://jiasu.tsource.cn/CDN/DIY/20121018V1/Scripts/jquery/ui/jquery.ui.datepicker-zh-CN.js?version=20120922" language="javascript"></script>
    <script type="text/javascript" src="http://jiasu.tsource.cn/CDN/DIY/20121018V1/Scripts/Anpworld/Anpworld.Tab.js?version=20120922" language="javascript"></script>
    <script type="text/javascript" src="http://jiasu.tsource.cn/CDN/DIY/20121018V1/Scripts/Anpworld/Anpworld.Tab.Dialog.js?version=20120922" language="javascript"></script>
    <script type="text/javascript" src="http://jiasu.tsource.cn/CDN/DIY/20121018V1/Scripts/Anpworld/CardX.js?version=20120922" language="javascript"></script>
    
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/Anpworld.Comm.js"></script>
    
    <style type="text/css">
        label. error
        {
            display: none;
        }
        input.text, input[type="text"], input[type="password"]
        {
            width: 240px;
            letter-spacing: 2px;
            text-align: left;
            border-width: 2px;
            border-color: #000000;
            border-style: none none solid none;
            font-size: 30px;
            font-weight: bold;
            line-height: 22px;
            color: Blue;
        }
        input[type="radio"]
        {
            text-align: left;
            font-size: large;
            font-weight: bold;
        }
        fieldset
        {
            margin: 1px;
            padding: 1px;
            border: 1px solid #CCC;
        }
        #printcontent
        {
            border: 0px;
            width: 95%;
            padding: 5px;
        }
        #advLayer1
        {
            position: absolute;
            right: 50px;
            top: 80px;
            width: 133px;
            height: 104px;
            z-index: 1;
        }
        #closeLayer1
        {
            position: absolute;
            right: 168px;
            top: 80px;
            width: 16px;
            height: 16px;
            z-index: 2;
        }
        a img
        {
            border: 0px;
        }
    </style>

    <script language="javascript" type="text/javascript">
        var UserNameLeft = '90';
        var UserNameTop = '-2';

        var CustomerNoLeft = '395';
        var CustomerNoTop = '20';

        var BillingcycleLeft = '20';
        var BillingcycleTop = '87';

        var FilldateLeft = '480';
        var FilldateTop = '40';

        var TotalpricelowerLeft = '500';
        var TotalpricelowerTop = '315';

        var TotalpriceupperLeft = '400';
        var TotalpriceupperTop = '255';

        var RemarkLeft = '60';
        var RemarkTop = '340';

        var BillinfodetailLeft = '60';
        var Billinfodetailtop = '190';

        var MyFontSize = 13; //'14';

        //add 2010-12-10
        var TotalPriceRealLowerTop = '335';
        var TotalPriceRealLowerLeft = '500';

        var TotalPriceRealUpperTop = '335';
        var TotalPriceRealUpperLeft = '50';

        var ClientBalanceLowerTop = '355';
        var ClientBalanceLowerLeft = '500';

        var CurrentMonthBillInfo = ""; //当前要打印月份返回的账单信息
        var BillTotalCnt = 0; //账单总的账期
        var BillCurrentIndex = 0; //当前打印的账期
        var CurrentMonthFees = 0; //当前打印月份的实收费用，如果小于0则余下都不打印

        //以下变量针对账单查询完成之后的打印功能
        var BillString = ""; //保存账单查询之后返回的信息
        var BillMonthArray = ""; //保存各个账期的月份值
        var CurrentBillString = ""; //保存当前要进行打印的月份的账单信息
        var CurrentBillFees = 0; //保存当前要进行打印的月份的账单费用总和
        var BillIndex = 0; //当前进行打印的索引
        var BillTotalCount = 0; //总的账期


        var IsAutoPrintStr = "";
        var AutoPrintTypeStr = "";
        var PrintFormatStr = "";

        var areaCode = '020';
        
        /*
        *关闭广告
        
        function closeAdv1() {
            $("#advLayer1").css("display", "none");
            $("#closeLayer1").css("display", "none");
        }*/
        var pw = window.parent.window;
        //跳转至充值付费卡确认页面
        function toSellCTVoucherCards() {
            pw.parent.addTabPage("电信充值付费卡销售", "/VoucherCards/CTVoucherCards/SellCTVoucherCards", 1);
        }
        $().ready(function() {
            try {
                document.getElementById("CustomerNumber").focus();
                $("input:text,input:password").addClass("txt");
            } catch (e) { };
            $("#fm_refill").validate({
                rules: {
                    CustomerNumber: { required: true }, //,digits: true, IsAreaCTNumber: [areaCode] 
                    VoucherType: { required: true }
                },
                messages: {
                    CustomerNumber: {
                        required: "客户号码为空"//,
                        //                        digits: "请输入数字号码",
                        //                        IsAreaCTNumber: "无效的中国电信客户号码"
                    },
                    VoucherType: {
                        required: "请选择一种充值缴费方式"
                    }
                },
                /* 验证通过后进行提交 */
                submitHandler: function(fm_refill) {
                    QueryBalance();
                }
            });

            $("#fm_voucher").validate({
                rules: {
                    VoucherMoney: { required: true, number: true, minlength: 1 },
                    ServicePassword: { required: true, digits: true, minlength: 1, maxlength: 6 }
                },
                messages: {
                    VoucherMoney: {
                        required: "充值金额为空,单位:元",
                        number: "充值金额必须是数字",
                        minlength: "充值金额格式不正确"
                    },
                    ServicePassword: {
                        required: "交易密码为空",
                        digits: "交易密码必须是数字",
                        minlength: "交易密码最少1位",
                        maxlength: "交易密码最多6位"
                    }
                },
                /* 验证通过后进行提交 */
                submitHandler: function(fm_voucher) {
                    RefillConfrim();
                }
            });

            $("#fm_send").validate({
                rules: {
                    sendNum: { required: true, digits: true, IsAreaCTNumber: [areaCode] }
                },
                messages: {
                    sendNum: {
                        required: "客户号码为空",
                        digits: "请输入数字号码",
                        IsAreaCTNumber: "无效的中国电信客户号码"
                    }
                },
                /* 验证通过后进行提交 */
                submitHandler: function(fm_send) {
                    sendSms();
                }
            });


        });


        var vTimerID;

        // 查询客户余额 开始
        function QueryBalance() {
            var Number = document.getElementById('CustomerNumber');
            var customerNumber = "";
            var trimNumber = Trim(Number.value);
            if ((trimNumber.substring(0, 1) == "1" && trimNumber.length == 11) || trimNumber.substring(0, 1) == "0") {
                customerNumber = trimNumber;
            } else {
                customerNumber = areaCode + trimNumber;
            }
            var rPort = document.getElementsByName("VoucherType");
            var typestr = "";
            for (i = 0; i < rPort.length; i++) {
                if (rPort[i].checked)
                    typestr = rPort[i].value;
            }

            vTimerID = setTimeout("DoQueryBalanceError()", 30000);

            Dialog.Running("<font color=red size=5>正在查询：<br/>" + FormatCustomerNumber(customerNumber) + " 的余额,<br/>请稍候.....</font>");
            var requestparam = "CustomerNumber=" + customerNumber + "&VoucherType=" + typestr + "&";
            //var requestparam = Anpworld.SerializeForm(document.forms["fm_refill"]);
            var call = Anpworld.AjaxCall3("/order/query.do", requestparam,
            QueryBalanceOver, DoQueryBalanceError);
        }

        function QueryBalanceOver(response) {
            clearTimeout(vTimerID);
            Dialog.Running.Close();
            parent.Restart();
            if (response != null) {
                var jasonText = eval(response);
                switch (jasonText.myretcode) {
                    case "100":
                        var temp = document.getElementById('step1');
                        temp.style.display = "none";

                        var obj = document.getElementById('step2');
                        obj.style.display = "";
                     
                        var Number = document.getElementById('CustomerNumber');
                        var customer = "";
                        var trimNumber = Trim(Number.value);
                        if ((trimNumber.substring(0, 1) == "1" && trimNumber.length == 11) || trimNumber.substring(0, 1) == "0") {
                            customer = trimNumber;
                        } else {
                            customer = areaCode + trimNumber;
                        }

                        var lblCallee = document.getElementById('lblCallee');
                        lblCallee.innerHTML = customer;

                        var lblpayFees = document.getElementById('lblpayFees');
                        lblpayFees.innerHTML = parseInt(jasonText.retresult) / 100.0 + '元';

                        var itemtype = "";
                        var vouchtype = document.getElementsByName("VoucherType");
                        for (i = 0; i < vouchtype.length; i++) {
                            if (vouchtype[i].checked) {
                                itemtype = vouchtype[i].value;
                            }
                        }

                        //保存客户号码及充值类型,方便账单套打使用
                        var txtCustomNumber = document.getElementById('txtCustomNumber');
                        var txtVoucherType = document.getElementById('txtVoucherType');
                        var txtVCType = document.getElementById('txtVCType');

                        txtCustomNumber.value = customer;
                        txtVoucherType.value = itemtype;
                        txtVCType.value = jasonText.vcType;

                        var lblVoucherType = document.getElementById('lblVoucherType');
                        if (itemtype == "000004") {
                            lblVoucherType.innerHTML = "综合缴费";
                        }
                        else {
                            lblVoucherType.innerHTML = "单一充值";
                        }

                        var lblNumType = document.getElementById('lblNumType');

                        var vcType = jasonText.vcType;
                        var accounttype = jasonText.accounttype;
                        var PaymentTypeStr = "";
                        switch (accounttype) {
                            case '1':
                                PaymentTypeStr = "中国电信－小灵通";
                                break;
                            case '2':
                                PaymentTypeStr = "中国电信－固话";
                                break;
                            case '3':
                                PaymentTypeStr = "中国电信－天翼";
                                break;
                            case '4':
                                PaymentTypeStr = "中国电信－宽带";
                                break;
                            default:
                                PaymentTypeStr = "中国电信－天翼";
                                break;
                        }
                        switch (vcType) {
                            case '1':
                                PaymentTypeStr += "/后付费";
                                break;
                            case '2':
                                PaymentTypeStr += "/预付费";
                                break;
                            case '0':
                                PaymentTypeStr += "/其他";
                            default:
                                PaymentTypeStr += "/预付费";
                                break;
                        }
                        lblNumType.innerHTML = PaymentTypeStr;
                        var voucherMoney = document.getElementById('VoucherMoney');
                        if (jasonText.vcType == "2" || jasonText.vcType == "4" || jasonText.vcType == "5" || jasonText.vcType == "0")		//预付费
                        { 
                            voucherMoney.value = "";
                            voucherMoney.focus();

                            var btn_show = document.getElementById('btn_show');
                            btn_show.style.display = "none";

                            document.getElementById("span").style.display = "none";
                        }
                        else if (jasonText.vcType == "1" || jasonText.vcType == "3")		//后付费
                        {
                            var vouchermoney = document.getElementById('VoucherMoney');
                            if (parseInt(jasonText.retresult) < 0) {
                                vouchermoney.value = parseInt(jasonText.retresult) / 100.0;
                                vouchermoney.value = Math.abs(vouchermoney.value);
                            }

                            vouchermoney.focus();

                            //设置了账单打印
                            if (jasonText.isbilltd == "1" && jasonText.isautoprint == "1") {
                                //不自动查询账单
                                if (jasonText.isautoquery == "0") {
                                    Dialog.AlertWith('<font size=4 color=red>如果充值缴费之后需要自动打印账单,请在充值缴费之前先点击查询账单按钮查询客户账单情况.</font>',
                                    function() { document.getElementById("btn_show").focus(); });
                                }
                                else { //自动查询账单
                                    startQueryBillData();
                                }
                            }
                            else {
                                if (jasonText.isautoquery == "1") {
                                    //自动查询账单
                                    startQueryBillData();
                                }
                            }

                            //保存打印配置信息
                            var myIsAutoPrint = document.getElementById("isAutoPrint");
                            var myAutoPrintType = document.getElementById("AutoPrintType");
                            var myPorts = document.getElementById("Ports");
                            var myRate = document.getElementById("Rate");
                            var printFormat = document.getElementById("printFormat");

                            myIsAutoPrint.value = jasonText.isautoprint;
                            myAutoPrintType.value = jasonText.printType;
                            printFormat.value = jasonText.printFormat;

                            myPorts.value = jasonText.printPort;
                            myRate.value = jasonText.printRate;
                        }
                        break;

                    default:
                        Dialog.AlertWith(jasonText.retresult,
                            function() { document.getElementById("CustomerNumber").focus(); });
                        break;
                }
            }
            else {
                Dialog.AlertWith('查询客户余额超时，请稍后再试.',
                function() { document.getElementById("CustomerNumber").focus(); });
            }
        }

        function DoQueryBalanceError() {
            clearTimeout(vTimerID);
            Dialog.Running.Close();
            Dialog.AlertWith("查询客户号码余额失败.",
            function() { document.getElementById("CustomerNumber").focus(); });
        }
        // 查询客户余额 结束

        function GoBack() {
            document.getElementById("VoucherMoney").focus();
            var VoucherMoney = document.getElementById('VoucherMoney');
            VoucherMoney.value = "";
            
            var div1 = document.getElementById('step1');
            var div2 = document.getElementById('step2');
            var obj = document.getElementById('CustomerNumber');
            clearBillData();
            div1.style.display = "";
            div2.style.display = "none";
            obj.focus();

            var btn_show = document.getElementById('btn_show');
            var print = document.getElementById('print');
            btn_show.style.display = "";
            print.style.display = "none";

            lbluserName.innerHTML = "";
        }

        function GoToBack() {
            window.location.href = "/CTProduct/Refill/ChinaTelePayment";
        }

        //账单查询  开始
        function startQueryBillData() {
            var Number = document.getElementById('CustomerNumber');
            var customerNumber = "";
            var trimNumber = Trim(Number.value);
            if ((trimNumber.substring(0, 1) == "1" && trimNumber.length == 11) || trimNumber.substring(0, 1) == "0") {
                customerNumber = trimNumber;
            } else {
                customerNumber = areaCode + trimNumber;
            }
            var rPort = document.getElementsByName("VoucherType");
            var typestr = "";
            for (i = 0; i < rPort.length; i++) {
                if (rPort[i].checked)
                    typestr = rPort[i].value;
            }
            Dialog.Running("<font color=red size=5>正在查询：<br/>" + FormatCustomerNumber(customerNumber) + " 的账单信息,<br/>请稍候.....</font>");
            requestparam = "CustomerNumber=" + customerNumber + "&VoucherType=" + typestr + "&BillMonth=0";
            var call = Anpworld.AjaxCall("/CTProduct/Refill/DoQueryBill", requestparam,
            QueryBillOver, DoQueryBillError);
        }

        function QueryBillOver(response) {
            var lblspan = document.getElementById('span');
            lblspan.style.display = "none";
            var TabBills = document.getElementById('TabBills');
            clearTimeout(vTimerID);
            Dialog.Running.Close();
            var strDashed = 'BORDER-BOTTOM:1px;BORDER-top:1px;BORDER-left:1px;BORDER-right:1px';
            var styleItemHeader = 'background-color:#E2ECFD;border:1px solid black;';

            var checkBillCount = 0; //用于判断是否无账单项数据
            if (response != null) {
                clearBillData();
                var jasonText = eval(response);
                if (jasonText.retcode == "0") {
                    var lbluserName = document.getElementById('lbluserName');
                    lbluserName.innerHTML = jasonText.username;
                    var itemCount = parseInt(jasonText.itemCount);
                    var billMonthIndex = 0;
                    var currBillMonthFee = 0.00;
                    if (itemCount == 0) {
                        var mytips = TabBills.insertRow(-1);
                        var tipscell = mytips.insertCell(-1);
                        tipscell.align = "center";
                        tipscell.innerHTML = "账单信息为空！请确认客户号码是否已出账。电信出账日为每月的4至6号。";
                        tipscell.style.cssText = "color: Red;border-style: groove none groove none; border-width: 1px;";
                    }
                    else {
                        //保存查询到的账单信息
                        BillString = response;

                        for (var i = 0; i < itemCount; i++) {
                            if (eval("jasonText.billItem" + i + ".item") == "0") //计费周期
                            {
                                if (billMonthIndex > 0) //小计上个计费周期总费用
                                {
                                    var xjr = TabBills.insertRow(-1);
                                    var xj = xjr.insertCell(-1);
                                    xj.align = "right";
                                    xj.innerHTML = "小计：";
                                    xj.style.cssText = "border-style: groove none groove none; border-width: 1px;";
                                    var tempnum = currBillMonthFee * 100;
                                    tempnum = Math.round(tempnum);
                                    xj = xjr.insertCell(-1);
                                    xj.align = "right";
                                    xj.innerHTML = tempnum / 100 + "元";
                                    xj.style.cssText = "border-style: groove none groove none; border-width: 1px;";
                                    xj = xjr.insertCell(-1);
                                    xj.align = "center";
                                    xj.innerHTML = " -- ";
                                    xj.style.cssText = "border-style: groove none groove none; border-width: 1px;";
                                    currBillMonthFee = 0.00;
                                }
                                if (eval("jasonText.billItem" + i + ".billid") != "0") {
                                    var billMonth = TabBills.insertRow(-1);
                                    billMonth.style.backgroundColor = "#E2ECFD";
                                    var billMonthCell = billMonth.insertCell(-1);
                                    billMonthCell.innerHTML = "计费周期：" + eval("jasonText.billItem" + i + ".number") + "至" + eval("jasonText.billItem" + i + ".itemfee");
                                    billMonthCell.style.cssText = "width:350px; color: #000000;border-style: groove none groove none; border-width: 1px;border-color: #000000;";
                                    billMonthCell = billMonth.insertCell(-1);
                                    billMonthCell.innerHTML = ".";
                                    billMonthCell.style.cssText = "width:250px; color: #000000;border-style: groove none groove none; border-width: 1px;border-color: #000000;";
                                    billMonthCell = billMonth.insertCell(-1);
                                    billMonthCell.innerHTML = ".";
                                    billMonthCell.style.cssText = "width:250px; color: #000000;border-style: groove none groove none; border-width: 1px;border-color: #000000;";

                                    var headRow = TabBills.insertRow(-1);
                                    var itemnameH = headRow.insertCell(-1);
                                    itemnameH.align = "center";
                                    itemnameH.innerHTML = "费用项目";
                                    itemnameH.style.cssText = styleItemHeader;
                                    var itemfeeH = headRow.insertCell(-1);
                                    itemfeeH.align = "right";
                                    itemfeeH.innerHTML = "金额(元)";
                                    itemfeeH.style.cssText = styleItemHeader;
                                    var numberH = headRow.insertCell(-1);
                                    numberH.align = "center";
                                    numberH.innerHTML = "设备号码";
                                    numberH.style.cssText = styleItemHeader;

                                    billMonthIndex = billMonthIndex + 1;
                                }
                                else {
                                    checkBillCount = checkBillCount + 1;
                                }
                            }
                            else {
                                if (eval("jasonText.billItem" + i + ".billid") != "0") {
                                    var newRow = TabBills.insertRow(-1);
                                    var itemnameCell = newRow.insertCell(-1);
                                    itemnameCell.align = "center";
                                    itemnameCell.innerHTML = eval("jasonText.billItem" + i + ".itemname");
                                    itemnameCell.style.cssText = strDashed;
                                    var itemfeeCell = newRow.insertCell(-1);
                                    itemfeeCell.align = "right";
                                    itemfeeCell.innerHTML = Math.round(parseFloat(eval("jasonText.billItem" + i + ".itemfee")) * 100) / 100;
                                    itemfeeCell.style.cssText = strDashed;
                                    var numberCell = newRow.insertCell(-1);
                                    numberCell.align = "center";
                                    numberCell.innerHTML = eval("jasonText.billItem" + i + ".number");
                                    numberCell.style.cssText = strDashed;

                                    currBillMonthFee = currBillMonthFee + parseFloat(eval("jasonText.billItem" + i + ".itemfee"));
                                }
                            }
                        }   //end for
                        if (billMonthIndex > 0) //小计上个计费周期总费用
                        {
                            var billMonth = TabBills.insertRow(-1);
                            var xj = billMonth.insertCell(-1);
                            xj.align = "right";
                            xj.innerHTML = "小计：";
                            xj.style.cssText = "border-style: groove none groove none; border-width: 1px;";
                            var tempnum = currBillMonthFee * 100;
                            tempnum = Math.round(tempnum);
                            xj = billMonth.insertCell(-1);
                            xj.align = "right";
                            xj.innerHTML = tempnum / 100 + "元";
                            xj.style.cssText = "border-style: groove none groove none; border-width: 1px;";
                            xj = billMonth.insertCell(-1);
                            xj.align = "center";
                            xj.innerHTML = " -- ";
                            xj.style.cssText = "border-style: groove none groove none; border-width: 1px;";
                            currBillMonthFee = 0.00;
                        }
                        if (checkBillCount != itemCount) {      //返回的空项是否和所有账单项一致，一致，则不显示合计，并提示账单项为空。
                            //合计
                            var newRow = TabBills.insertRow(-1);
                            var totalTipCell = newRow.insertCell(-1);
                            totalTipCell.align = "right";
                            totalTipCell.innerHTML = "合计：";
                            totalTipCell.style.cssText = styleItemHeader;

                            var totalPriceCell = newRow.insertCell(-1);
                            totalPriceCell.align = "right";
                            totalPriceCell.innerHTML = jasonText.fee;
                            totalPriceCell.style.cssText = styleItemHeader;

                            var EntryCell2 = newRow.insertCell(-1);
                            EntryCell2.innerHTML = "";
                            EntryCell2.style.cssText = styleItemHeader;

                            document.getElementById("print").style.display = "";
                            document.getElementById("btn_show").style.display = "none";
                        }
                        else {
                            var newRow = TabBills.insertRow(-1);
                            var totalTipCell = newRow.insertCell(-1);
                            totalTipCell.align = "right";
                            totalTipCell.innerHTML = "该用户无账单信息。";
                            document.getElementById("btn_show").style.display = "";
                        }
                    }
                }
                else if (jasonText.retcode == "1") {
                    Dialog.Alert(jasonText.retresult);
                    var mytips = TabBills.insertRow(-1);
                    var tipscell = mytips.insertCell(-1);
                    tipscell.align = "center";
                    tipscell.innerHTML = jasonText.retresult;
                    tipscell.style.cssText = "color: Red;border-style: groove none groove none; border-width: 1px;";

                    document.getElementById("btn_show").style.display = "";
                }
                else if (jasonText.retcode == "2") {
                Dialog.AlertWith("查询客户账单信息已超时!\n如客户号码是中国电信后付费，客户需查询账单的，请“取消返回”重新进行“查询余额”。\n否则可直接进行充值缴费。",
            function() { document.getElementById("VoucherMoney").focus(); });
                    var mytips = TabBills.insertRow(-1);
                    var tipscell = mytips.insertCell(-1);
                    tipscell.align = "center";
                    tipscell.innerHTML = "查询客户账单信息已超时! 如客户号码是中国电信后付费，客户需查询账单的，请“取消返回”重新进行“查询余额”。否则可直接进行充值缴费。";
                    tipscell.style.cssText = "color: Red;border-style: groove none groove none; border-width: 1px;";
                   
                    document.getElementById("btn_show").style.display = "";
                }
                else if (jasonText.retcode == "3") {
                    Dialog.AlertWith("<font size=4 color=\"red\">" + jasonText.retresult + "</font>",
                    function() { GoBack(); });
                    return;
                }
                else {
                    Dialog.AlertWith("查询客户账单信息错误!\n如客户号码是中国电信后付费，客户需查询账单的，请“取消返回”重新进行“查询余额”。\n否则可直接进行充值缴费。",
            function() { document.getElementById("VoucherMoney").focus(); });
                    var mytips = TabBills.insertRow(-1);
                    var tipscell = mytips.insertCell(-1);
                    tipscell.align = "center";
                    tipscell.innerHTML = "查询客户账单信息错误! 如客户号码是中国电信后付费，客户需查询账单的，请“取消返回”重新进行“查询余额”。 否则可直接进行充值缴费。";
                    tipscell.style.cssText = "color: Red;border-style: groove none groove none; border-width: 1px;";

                    document.getElementById("btn_show").style.display = "";
                }
                
            }
           // document.getElementById("VoucherMoney").focus();
        }

        function DoQueryBillError() {
            clearTimeout(vTimerID);
            Dialog.Running.Close();
            Dialog.AlertWith("客户账单查询超时，请稍后再试.",
            function() { document.getElementById("VoucherMoney").focus(); });
        }

        //清除账单数据行
        function clearBillData() {
            var TabBills = document.getElementById('TabBills');
            var TdLength = TabBills.rows.length;
            for (var i = TdLength - 1; i > 1; i--) {
                TabBills.deleteRow(i);
            }
        }
        //账单查询  结束

        //充值缴费操作  开始
        function RefillConfrim() {
            var Number = document.getElementById('CustomerNumber');
            var money = document.getElementById("VoucherMoney").value;
            var voucherType = document.getElementById('VoucherType1');
            var customerNumber = "";
            var trimNumber = Trim(Number.value);
            if ((trimNumber.substring(0, 1) == "1" && trimNumber.length == 11) || trimNumber.substring(0, 1) == "0") {
                customerNumber = trimNumber;
            } else {
                customerNumber = areaCode + trimNumber;
            }
            var desc = "";
            var isValueMsg = "";
            if (Number.value.substring(0, 1) == "1") {
                if (money > 30) {
                    isValueMsg = "<font style=\"color:red;  font-size: 18px; font-weight: bold;\">" + '<font style="color:blue;  font-size: 20px; font-weight: bold;">近期电信手机号码冲正受限，请仔细核对号码是否正确，避免造成损失<font> ' + "</font><br/>";  //读配置数据

                }
                else {
                    isValueMsg = "<font style=\"color:red;  font-size: 18px; font-weight: bold;\">" + '<font style="color:blue;  font-size: 20px; font-weight: bold;">30元以下充值面值无法冲正，请仔细核对号码是否正确，避免造成损失' + "</font><br/>";  //读配置数据
                }
            }
            if (Number.value.substring(0, 1) == "1" && voucherType.checked) {
                desc = "<font style=\"color:blue; font-size: 22px; font-weight: bold;\">天翼手机建议使用单一充值</font><br/>";
            }

            var headerMsg = "<font size=4>尊敬的代理商，您为：<br/><br/></font>";
            var numberMsg = "<font size=4>客户号码：<font style=\"color:red;  font-size: 28px; font-weight: bold;\">" + FormatCustomerNumber(customerNumber) + "</font></font><br/><br/>";
            var moneyMsg = "<font size=4>充值金额：<font style=\"color:red;  font-size: 28px; font-weight: bold;\">" + Trim(money) + "元</font></font><br/><br/>";
            var Tips = "<br/><font size=4>请您确认后，点击确定进行充值</font>";

            Dialog.ConfirmWith(headerMsg + numberMsg + moneyMsg + desc + isValueMsg + Tips,
                function () {
                    RefillSubmit(customerNumber);
                },
                function () { document.getElementById("btn_Refill").focus(); },
                500, 350);
        }

        //跳转至充值付费卡确认页面
        function toSellCTVoucherCardsConfirm(id, Money, SPwd) {
            var requestparam = "id=" + id + "&Money=" + Money + "&TradePassword=" + Anpworld.SerializePrame(SPwd);
            pw.parent.addTabPage("电信充值付费卡销售", "/VoucherCards/CTVoucherCards/SellCTVoucherCardsConfirm?" + requestparam, 1);
        }
        function RefillSubmit(customerNumber) {
            vTimerID = setTimeout("DoRefillError()", 30000);
            Dialog.Running("<font color=red size=5>正在为号码：<br/>" + FormatCustomerNumber(customerNumber) + " 的客户充值缴费,<br/>请稍候.....</font>");
            var requestparam = Anpworld.SerializeForm(document.forms["fm_voucher"]);
            var itemtype = "";
            var temp = document.getElementsByName("VoucherType");
            for (i = 0; i < temp.length; i++) {
                if (temp[i].checked) {
                    itemtype = temp[i].value;
                }
            }

            requestparam = requestparam + "&CustomerNumber=" + customerNumber + "&ItemType=" + itemtype;
            var call = Anpworld.AjaxCall("/CTProduct/Refill/ChinaTelecomRefill", requestparam,
                RefillOver, DoRefillError);
        }
        function RefillOver(response) {
            var lblCustNum = document.getElementById('lblCustNum');
            var beforemoney = document.getElementById('beforemoney');
            var lbltransmoney = document.getElementById('lbltransmoney');
            var lblcustblanace = document.getElementById('lblcustblanace');
            var lblpayFees = document.getElementById('lblpayFees');
            clearTimeout(vTimerID);
            Dialog.Running.Close();
            if (response != null) {
                var jasonText = eval(response);
                switch (jasonText.myretcode) {
                    case "100":
                        //刷新快捷助手
                        var pw = window.parent;
                        pw.document.getElementById("reopen").onclick();

                        var obj = document.getElementById('step3');
                        obj.style.display = "";

                        var identityIdTxt = document.getElementById('identityIdTxt');
                        identityIdTxt.value = jasonText.seqno;
                        var Number = document.getElementById('CustomerNumber');
                        var customerNumber = "";
                        var trimNumber = Trim(Number.value);
                        if ((trimNumber.substring(0, 1) == "1" && trimNumber.length == 11) || trimNumber.substring(0, 1) == "0") {
                            customerNumber = trimNumber;
                        } else {
                            customerNumber = areaCode + trimNumber;
                        }
                        lblCustNum.innerHTML = customerNumber;
                        beforemoney.innerHTML = lblpayFees.innerHTML;
                        lbltransmoney.innerHTML = document.getElementById('VoucherMoney').value + "元";
                        lblcustblanace.innerHTML = parseInt(jasonText.clientbalance) / 100.0 + "元";

                        var btn = document.getElementById('btn_GoBack');
                        btn.focus();

                        var temp = document.getElementById('step2');
                        temp.style.display = "none";

                        var requestparam = "FlowId=" + jasonText.seqno;

                        var isAutoPrint = document.getElementById("isAutoPrint").value;
                        var AutoPrintType = document.getElementById("AutoPrintType").value;
                        var isvctype = document.getElementById("txtVCType").value;
                        var printFormat = document.getElementById("printFormat").value;

                        //自动打印
                        if (isAutoPrint == "1") {
                            //串口打印并且是后付费类型则打印账单
                            if (AutoPrintType == "1" && (isvctype == "1" || isvctype == "3")) {
                                if (printFormat == "ck1") {
                                    AutoPrintBillByCK();
                                }
                                else if (printFormat == "ck2") {
                                    AutoPrintForCK2();    //获取串口某计费周期账单内容
                                }
                            }
                            else {
                                var call = Anpworld.AjaxCall("/CTProduct/Refill/GetPrintContentByFlowId", requestparam,
                                        GetPrintContentOver, GetPrintContentError);
                            }
                        }

                        break;

                    default:
                        Dialog.AlertWith(jasonText.retresult,
                            function() { document.getElementById("ServicePassword").focus(); });
                        break;
                }
            }
            else {
                Dialog.AlertWith('充值缴费超时，请稍后再试.',
                function() { document.getElementById("btn_Refill").focus(); });
            }
        }

        function DoRefillError() {
            clearTimeout(vTimerID);
            Dialog.Running.Close();
            Dialog.AlertWith("充值缴费失败，请稍后再试.",
            function() { document.getElementById("btn_Refill").focus(); });
        }
        //充值缴费操作  结束

        //打印凭证  开始
        function HandPrint() {
            var printFormat = document.getElementById("printFormat");
            BillCurrentIndex = 0;
            if (isSetPintConfig()) {
                var AutoPrintType = document.getElementById("AutoPrintType").value;
                var isvctype = document.getElementById("txtVCType").value;

                if (AutoPrintType != "") {
                    var identityIdTxt = document.getElementById('identityIdTxt');
                    var requestparam = "FlowId=" + identityIdTxt.value;

                    if (AutoPrintType == "1") {//串口打印
                        if (isvctype == "1" || isvctype == "3") {
                            if (printFormat.value == "ck1") {
                                AutoPrintBillByCK();
                            }
                            else if (printFormat.value == "ck2") {
                                AutoPrintForCK2();
                            }
                        }
                        else {
                            var call = Anpworld.AjaxCall("/CTProduct/Refill/GetPrintContentByFlowId", requestparam,
                                    GetPrintContentOver, GetPrintContentError);
                        }
                    }
                    else if (AutoPrintType == "2") {//系统打印
                        //AutoPrintForXT();
                        var call = Anpworld.AjaxCall("/CTProduct/Refill/GetPrintContentByFlowId", requestparam,
                                GetPrintContentOver, GetPrintContentError);
                    }
                    else if (AutoPrintType == "3") { //账单套打
                        if (isvctype == "0" || isvctype == "2" || isvctype == "4" || isvctype == "5") {
                            Dialog.Alert('无法为预付费的客户进行账单的打印,打印失败.');
                        }
                        else if (isvctype == "1" || isvctype == "3") {
                            if (printFormat.value == "xt5") {
                                AutoPrintFoPZ();
                            } else {
                                AutoPrintForTD();
                            }
                        }
                        else {
                            Dialog.Alert('无法确定客户的付费类型,打印失败.');
                        }
                    }
                    else {
                        Dialog.Alert('无法确定打印类型.');
                    }
                }
            }
            else {
                Dialog.Confirm('尊敬的代理商\n你还没配置打印信息，是否要配置？',
                    function() {
                        Dialog.Running("<font color=red size=5>正在配置打印信息，请稍候.....</font>");
                        document.getElementById("btnPrintForHand").disabled = true;

                        showModalDialog("/UserAccount/AccountPublic/AutoPrintConfig", "", "dialogWidth=600px;dialogHeight=600px");

                        SetPrintConfig();

                        var FlowId = document.getElementById('identityIdTxt').value;
                        var requestparam = "FlowId=" + FlowId;

                        var isvctype = document.getElementById("txtVCType").value;
                        var AutoPrintType = document.getElementById("AutoPrintType").value;

                        //串口打印并且是后付费类型则打印账单
                        if (AutoPrintType == "1" && (isvctype == "1" || isvctype == "3")) {
                            AutoPrintBillByCK();
                        }
                        else {//否则按照以前的打印方式
                            var call = Anpworld.AjaxCall("/CTProduct/Refill/GetPrintContentByFlowId", requestparam,
                                    GetPrintContentOver, GetPrintContentError);
                        }
                    },
                    function() { });
            }
        }

        function GetPrintContentOver(response) {
            Dialog.Running.Close();
            if (response != null && response != "") {
                var mylblPrintContent = document.getElementById("PrintContent");
                mylblPrintContent.value = response;
            }

            var isAutoPrint = document.getElementById("isAutoPrint").value;
            var AutoPrintType = document.getElementById("AutoPrintType").value;
            var isvctype = document.getElementById("txtVCType").value;
            var printFormat = document.getElementById("printFormat").value;

            if (AutoPrintType == "1") {//串口打印
                if (printFormat == "ck1") {
                    AutoPrintForCK();
                }
                else { AutoPrintForCK2(); }
            }
            else if (AutoPrintType == "2") {//系统打印
                AutoPrintForXT();
            }
            else if (AutoPrintType == "3") { //账单套打
                if (isvctype == "0" || isvctype == "2" || isvctype == "4" || isvctype == "5") {
                    Dialog.Alert('无法为预付费的客户进行账单的打印,打印失败.');
                }
                else if (isvctype == "1" || isvctype == "3") {
                    if (printFormat == "xt5") {
                        AutoPrintFoPZ();
                    } else {
                        AutoPrintForTD();
                    }
                }
                else {
                    Dialog.Alert('无法确定客户的付费类型,打印失败.');
                }
            }
            else {
                Dialog.Alert('无法确定打印类型.');
            }
            //}
        }

        function GetPrintContentError() {
            Dialog.Running.Close();
            Dialog.Alert("获取打印信息失败,请稍后再试.");
            document.getElementById("btnPrintForHand").disabled = false;
        }

        //获取保存后打印配置信息
        function SetPrintConfig() {
            var call = Anpworld.AjaxCall("/CTProduct/Refill/GetAutoPrintInfo", null,
                    GetAutoPrintInfoOver, GetAutoPrintInfoError);
        }

        function GetAutoPrintInfoOver(response) {
            var myIsAutoPrint = document.getElementById("isAutoPrint");
            var myAutoPrintType = document.getElementById("AutoPrintType");

            var myPorts = document.getElementById("Ports");
            var myRate = document.getElementById("Rate");
            var printFormat = document.getElementById("printFormat");

            if (response != null && response != "") {
                var jasonConfig = eval(response);
                myIsAutoPrint.value = jasonConfig.printIsAuto;
                myAutoPrintType.value = jasonConfig.printType;

                myPorts.value = jasonConfig.printPort;
                myRate.value = jasonConfig.printRate;
                printFormat.value = jasonConfig.printFormat;

                AutoPrintTypeStr = myAutoPrintType.value;
                PrintFormatStr = printFormat.value;
            }
            document.getElementById("btnPrintForHand").disabled = false;
        }

        function GetAutoPrintInfoError() {
        }

        function isSetPintConfig() {
            var isAutoPrint = document.getElementById("isAutoPrint").value;
            var AutoPrintType = document.getElementById("AutoPrintType").value;

            if (isAutoPrint == "" || AutoPrintType == "") {
                return false;
            }
            return true;
        }

        //系统打印
        function AutoPrintForXT() {
            var printText = document.getElementById("PrintContent");
            var lbl = document.getElementById("lblPrintContent");

            lbl.innerHTML = "<div style=\"font-size:20px;\">" + printText.value + "</div>";
            document.getElementById("title").style.display = "none";
            document.getElementById("step3").style.display = "none";
            window.print();
            document.getElementById("title").style.display = "";
            document.getElementById("step3").style.display = "";
            lbl.innerHTML = "";
        }

        //串口打印
        function AutoPrintForCK() {
            var MyTSObject = document.getElementById("TSObject");
            var versions = "";
            var rc = Anpworld.VerifyActiveXVersion(txvoucherx_ver);
            if (rc == -1) {
                Dialog.Alert("尊敬的代理商\n串口打印需要安装串口打印控件,请先安装控件.");
                return false;
            }
            else if (rc == 2) {
                Dialog.Alert("尊敬的代理商\n您的本地认证控件版本异常，可能存在问题，请重新下载控件安装.");
                return false;
            }
            else if (rc == 1) {
                Dialog.Alert("尊敬的代理商\n您之前安装的本地认证控件版本太旧,请下载安装新版本控件。");
                return false;
            }
            else {
                MyTSObject.innerHTML = Anpworld.SetActiveXObject();
            }

            //document.getElementById("step3").style.display = "none";
            var myPrintContentValue = document.getElementById("PrintContent").value;
            //var lbl = document.getElementById("lblPrintContent");
            //lbl.innerHTML = myPrintContentValue;

            while (myPrintContentValue.toLowerCase().indexOf("<br>") > -1) {
                myPrintContentValue = myPrintContentValue.toLowerCase().replace("<br>", "\n"); //\n为串口打印换行符
            }
            if (myPrintContentValue != "") {
                myPrintContentValue = myPrintContentValue + "\n" + " ";
            }
            var myPorts = document.getElementById("Ports").value;
            var myRate = document.getElementById("Rate").value;

            CkPrint(myPorts, myRate, myPrintContentValue);

            //document.getElementById("step3").style.display = "";
            //lbl.innerHTML = "";
        }

        //串口打印开始
        function CkPrint(Port, Rate, content) {
            OpenPrinter(Port, Rate);
            MyPrint(content);
            ClosePrinter();
        }

        //打开
        function OpenPrinter(Port, Rate) {
            try {
                var ret = TSVoucherX.OpenPrinter(Port, Rate);
            } catch (e) {
                Dialog.Alert("OpenPrinter错误: " + e.number);
                return false;
            }
            return true;
        }

        //打印
        function MyPrint(content) {
            try {
                var ret = TSVoucherX.print(content);
            } catch (e) {
                Dialog.Alert("MyPrint错误: " + e.number);
                return false;
            }
            return true;
        }

        //关闭
        function ClosePrinter() {
            try {
                var ret = TSVoucherX.ClosePrinter();

            } catch (e) {
                Dialog.Alert("ClosePrinter错误: " + e.number);
                return false;
            }
            return true;
        }

        //发送短信  开始
        function sendSms() {
            var lblCustNum = document.getElementById("lblCustNum");
            var callee = lblCustNum.innerHTML;

            var phonenumber = document.getElementById('sendNum');
            //var sendtime = GetCuttime();
            var transMoney = document.getElementById('VoucherMoney');
            Dialog.Running("<font color=red size=5>正在发送短信,<br/>请稍候.....</font>");
            var requestparam = "";
            requestparam = requestparam + "CustomerNumber=" + callee + "&SendNumber=" + phonenumber.value + "&VoucherMoney=" + transMoney.value;
            var call = Anpworld.AjaxCall("/CTProduct/Refill/SendSms", requestparam, SendSmsOver, DoSendSmsError);
        }

        function SendSmsOver(response) {
            Dialog.Running.Close();
            if (response == 1) {
                Dialog.AlertWith('请输入中国电信天翼手机号码或小灵通号码！其它运营商号码暂时无法接收短信.',
                            function() { document.getElementById("sendNum").focus(); });
            }
            else if (response == 2) {
                Dialog.AlertWith('请输入中国电信天翼手机号码或小灵通号码！其它运营商号码暂时无法接收短信.',
                            function() { document.getElementById("sendNum").focus(); });
            }
            else if (response == 3) {
                Dialog.AlertWith('发送充值缴费凭证短信失败.',
                            function() { document.getElementById("sendNum").focus(); });
            }
            else {
                Dialog.Alert('发送充值缴费凭证短信成功！');
                document.getElementById("btn_SendSms").disabled = true;
            }
        }

        function DoSendSmsError() {
            Dialog.Running.Close();
            Dialog.AlertWith("下发短信失败，请稍后再试.",
            function() { document.getElementById("sendNum").focus(); });
        }

        //发送短信  结束

        //账单自动套打 开始

        //账单系统打印
        function AutoPrintForXTTD() {
            //暂时 取消 余额小于0不打印的限制
            //            if (CurrentMonthFees < 0) {
            //                alert("第 " + (BillCurrentIndex + 1) + " 个账期的实收金额小于零，不进行发票的打印.");
            //            }
            //            else {

            var printText = document.getElementById("PrintContent");
            var lbl = document.getElementById("lblPrintContent");
            lbl.innerHTML = printText.value;
            document.getElementById("title").style.display = "none";
            document.getElementById("step3").style.display = "none";

            var WinPrint = window.open('', '', 'letf=10,top=10,width=680,toolbar=0,scrollbars=0,status=0,menubar=0');
            WinPrint.document.write(lbl.innerHTML);
            WinPrint.document.close();
            WinPrint.focus();
            WinPrint.print();
            WinPrint.close();

            document.getElementById("title").style.display = "";
            document.getElementById("step3").style.display = "";

            lbl.innerHTML = "";
            printText.value = "";
            //            }

            BillCurrentIndex = BillCurrentIndex + 1;  //下一个账期的序号
            if (BillCurrentIndex < BillTotalCnt) {
                Dialog.ConfirmWith("<font color=red size=4>第 " + BillCurrentIndex + " 页打印完毕，总共有" + BillTotalCnt + "页,是否继续打印第 " + (BillCurrentIndex + 1) + " 页?</font>",
                    function() {
                        CurrentMonthFees = 0;
                        CurrentMonthBillInfo = "";
                        AutoPrintForTD();
                    },
                    function() {
                        CurrentMonthFees = 0;
                        BillTotalCnt = 0;
                        BillCurrentIndex = 0;
                        CurrentMonthBillInfo = "";
                    },
                    400, 250);
            }
            else {
                BillCurrentIndex = 0;
                CurrentMonthFees = 0;
            }
        }

        function PrintContentForXT() {         //系统打印，只打印PrintContent的内容
            var printText = document.getElementById("PrintContent");

            var WinPrint = window.open('', '', 'letf=10,top=10,width=680,toolbar=0,scrollbars=0,status=0,menubar=0');
            WinPrint.document.write(printText.value);
            WinPrint.document.close();
            WinPrint.focus();
            WinPrint.print();
            WinPrint.close();

            printText.value = "";
        }

        //自动账单套打
        function AutoPrintForTD() {
            Dialog.Running("<font color=red size=4>系统正在处理中,请稍候.....</font>");
            var FlowId = document.getElementById('identityIdTxt').value;
            var requestparam = "flowid=" + FlowId + "&month=" + BillCurrentIndex;
            var call = Anpworld.AjaxCall("/CTProduct/Refill/GetCurrentMonthForAutoPrint", requestparam, QueryBillForPrintOver, DoQueryBillForPrintError);
        }

        //自动凭证打印
        function AutoPrintFoPZ() {
            Dialog.Running("<font color=red size=4>系统正在处理中,请稍候.....</font>");
            var FlowId = document.getElementById('identityIdTxt').value;
            var requestparam = "flowid=" + FlowId + "&month=" + BillCurrentIndex;
            var call = Anpworld.AjaxCall("/CTProduct/Refill/GetCurrentMonthForAutoPrint", requestparam, QueryBillForPZPrintOver, DoQueryBillForPrintError);
        }

        //串口查账单
        function AutoPrintForCK2() {
            Dialog.Running("<font color=red size=4>系统正在处理中,请稍候.....</font>");
            var FlowId = document.getElementById('identityIdTxt').value;
            var requestparam = "flowid=" + FlowId + "&month=" + BillCurrentIndex;
            var call = Anpworld.AjaxCall("/CTProduct/Refill/GetCurrentMonthForAutoPrint", requestparam, QueryBillForCK2PrintOver, DoQueryBillForPrintError);
        }

        //获取凭证打印格式
        function QueryBillForPZPrintOver(response) {
            Dialog.Running.Close();
            if (response != null || response != "") {
                CurrentMonthBillInfo = response;

                CreatePZHtml();         //凭证打印内容
                PrintContentForXT();    //打印凭证内容

                BillCurrentIndex = BillCurrentIndex + 1;  //下一个账期的序号

                if (BillCurrentIndex < BillTotalCnt) {
                    Dialog.ConfirmWith("<font color=red size=4>第 " + BillCurrentIndex + " 个账期打印完毕，总共有" + BillTotalCnt + " 个账期,是否继续打印第 " + (BillCurrentIndex + 1) + " 个账期?</font>",
                    function() {
                        CurrentMonthFees = 0;
                        AutoPrintFoPZ();
                    },
                    function() {
                        CurrentMonthFees = 0;
                        BillTotalCnt = 0;
                        BillCurrentIndex = 0;
                    },
                    400, 250);
                }
                else {
                    BillCurrentIndex = 0;
                    CurrentMonthFees = 0;
                }

            }
            else {
                Dialog.Alert("客户账单信息为空,无法进行账单的自动打印.");
            }
        }

        //获取串口打印格式
        function QueryBillForCK2PrintOver(response) {
            Dialog.Running.Close();
            if (response != null || response != "") {
                CurrentMonthBillInfo = response;

                CreateCKHtml();         //串口打印内容
                AutoPrintForCK();       //串口打印

                BillCurrentIndex = BillCurrentIndex + 1;  //下一个账期的序号

                if (BillCurrentIndex < BillTotalCnt) {
                    Dialog.ConfirmWith("<font color=red size=4>第 " + BillCurrentIndex + " 个账期打印完毕，总共有" + BillTotalCnt + " 个账期,是否继续打印第 " + (BillCurrentIndex + 1) + " 个账期?</font>",
                    function() {
                        CurrentMonthFees = 0;
                        AutoPrintForCK2();
                    },
                    function() {
                        CurrentMonthFees = 0;
                        BillTotalCnt = 0;
                        BillCurrentIndex = 0;
                    },
                    400, 250);
                }
                else {
                    BillCurrentIndex = 0;
                    CurrentMonthFees = 0;
                }

            }
            else {
                Dialog.Alert("客户账单信息为空,无法进行账单的自动打印.");
            }
        }

        function QueryBillForPrintOver(response) {
            Dialog.Running.Close();
            if (response != null || response != "") {
                CurrentMonthBillInfo = response;

                CreateHtml();
            }
            else {
                Dialog.Alert("客户账单信息为空,无法进行账单的自动打印.");
            }
        }

        function DoQueryBillForPrintError() {
            Dialog.Alert("客户账单信息查询超时,无法进行账单的自动打印.");
        }

        //创建凭证打印内容
        function CreatePZHtml() {
            var jasonText = eval(CurrentMonthBillInfo);
            var FlowId = document.getElementById('identityIdTxt').value;        //流水号
            if (jasonText.retcode == "100") {
                if (itemCount == 0) {
                    Dialog.Alert("客户账单信息为空.请确认客户号码是否已出账.电信出账日为每月的4至6号.");
                }
                else {
                    var p_username = jasonText.username;
                    var p_number = document.getElementById('txtCustomNumber').value;
                    var p_printCycle = "";

                    var vouchermoney = parseInt(jasonText.vouchermoney); //充值金额,单位:分
                    var billtotalfee = parseInt(jasonText.billtotalfee); //账单欠费总金额:分
                    var currentmonthssfee = parseInt(jasonText.currentmonthssfee); //该月实收金额:分
                    var currentmonthfee = parseInt(jasonText.currentmonthfee); //该月账单总费用:分
                    CurrentMonthFees = currentmonthssfee;
                    BillTotalCnt = jasonText.monthes; //总的账期数

                    var shishoufee = parseFloat(currentmonthssfee / 100.0).toFixed(2);  //实收                    
                    var clientbalace = (currentmonthssfee - currentmonthfee) / 100.0; //客户余额,单位:元

                    //填票时间
                    var nowDate = getNowTime();

                    //字体大小
                    var contentFontSize = "15px";

                    var itemCount = parseInt(jasonText.itemCount);
                    var billMonthIndex = 0;
                    var currBillMonthFee = 0.00;
                    var rowIndex = 0;
                    var ckBillInfo = "";            //账单信息
                    var totalFee = "";              //合计小写
                    var upperTotalFee = "";         //合计大写

                    //凭证打印的账单项（项目和金额）的六列
                    var cellContent1 = "&nbsp;";
                    var cellContent2 = "&nbsp;";
                    var cellContent3 = "&nbsp;";
                    var cellContent4 = "&nbsp;";
                    var cellContent5 = "&nbsp;";
                    var cellContent6 = "&nbsp;";

                    for (var i = 0; i < itemCount; i++) {       //for3
                        if (i == 10) {
                            break;
                        }

                        if (eval("jasonText.billItem" + i + ".item") == "0") //计费周期开始
                        {
                            //计费周期
                            p_printCycle = FormatDateTimeStr(eval("jasonText.billItem" + i + ".number")) + ":" + FormatDateTimeStr(eval("jasonText.billItem" + i + ".itemfee")) + "</div>";
                            billMonthIndex = billMonthIndex + 1;
                        }
                        else {//账单细项
                            if (rowIndex < 10) {
                                //欠费总金额大于实收,不显示账单细项
                                if (currentmonthfee > currentmonthssfee) {
                                    //第一二列
                                    cellContent1 = "&nbsp;";
                                    cellContent2 = "&nbsp;";
                                    currBillMonthFee = currBillMonthFee + parseFloat(eval("jasonText.billItem" + i + ".itemfee"));

                                    //第三四 及五六列
                                    var nextIndex = (i + 1) * 10 + i;
                                    for (var k = 0; k < 2; k++) {       //for1
                                        if (nextIndex < itemCount) {
                                            var oribillid = eval("jasonText.billItem" + i + ".billid");
                                            var curbillid = eval("jasonText.billItem" + nextIndex + ".billid");
                                            if (oribillid == curbillid) {
                                                cellContent3 = "&nbsp;";
                                                cellContent4 = "&nbsp;";
                                                currBillMonthFee = currBillMonthFee + parseFloat(eval("jasonText.billItem" + nextIndex + ".itemfee"));
                                            }
                                            else {
                                                cellContent3 = "&nbsp;";
                                                cellContent4 = "&nbsp;";
                                            }
                                        }
                                        else {
                                            cellContent5 = "&nbsp;";
                                            cellContent6 = "&nbsp;";
                                        }

                                        nextIndex = nextIndex + 10;
                                    } //end for1
                                }
                                else {
                                    //第一二列
                                    cellContent1 += eval("jasonText.billItem" + i + ".itemname");
                                    cellContent1 += "<br />&nbsp;";
                                    cellContent2 += Math.round(parseFloat(eval("jasonText.billItem" + i + ".itemfee")) * 100) / 100;
                                    cellContent2 += "<br />&nbsp;";
                                    currBillMonthFee = currBillMonthFee + parseFloat(eval("jasonText.billItem" + i + ".itemfee"));

                                    //第三四 及五六列
                                    var nextIndex = (i + 1) * 10 + i;
                                    for (var k = 0; k < 2; k++) {       //for2
                                        if (nextIndex < itemCount) {
                                            var oribillid = eval("jasonText.billItem" + i + ".billid");
                                            var curbillid = eval("jasonText.billItem" + nextIndex + ".billid");
                                            if (oribillid == curbillid) {
                                                cellContent3 += eval("jasonText.billItem" + nextIndex + ".itemname");
                                                cellContent3 += "<br />&nbsp;";
                                                cellContent4 += Math.round(parseFloat(eval("jasonText.billItem" + nextIndex + ".itemfee")) * 100) / 100;
                                                cellContent4 += "<br />&nbsp;";
                                                currBillMonthFee = currBillMonthFee + parseFloat(eval("jasonText.billItem" + nextIndex + ".itemfee"));
                                            }
                                            else {
                                                cellContent3 += "&nbsp;";
                                                cellContent4 += "&nbsp;";
                                            }
                                        }
                                        else {
                                            cellContent5 = "&nbsp;";
                                            cellContent6 = "&nbsp;";
                                        }
                                        nextIndex = nextIndex + 10;
                                    } //end for2
                                }
                            }
                            rowIndex++;
                        }
                    } //end for3                    

                    //小计最后一个计费周期总费用
                    if (billMonthIndex > 0) {
                        if (Math.round(parseFloat(shishoufee) * 100) / 100 > 0) {
                            upperTotalFee = Anpworld.ConvertCurrency(shishoufee);
                        }
                        else { upperTotalFee = "零元"; }
                    }

                    //凭证打印内容
                    var printText = "";
                    printText += "<html><head><title></title>";
                    printText += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">";
                    printText += "</head><body>";
                    //标题
                    printText += "<div style=\"text-align: center; font-size: 25px; font-weight: bold; width:90%\">";
                    printText += "<font face=\"楷体_GB2312\" color=\"#FF0000\">中国电信综合充值交费凭证</font></div>";
                    //客户信息
                    printText += "<div style=\"padding-left: 25px; font-size: " + contentFontSize + "; font-weight: bold;color: Red; margin-top: 15px; margin-bottom: 8px; width: 90%\">";

                    printText += "<table style=\"width: 100%;font-size: " + contentFontSize + ";\">";
                    printText += "<tr>";
                    printText += "<td style=\"width: 30%; color: Red; text-align:left;\">客户姓名：";
                    printText += "<span style=\"color: Black; font-weight: normal;\">" + p_username + "</span></td>";
                    printText += "<td style=\"width: 40%; color: Red;text-align:left;\">流水号：";
                    printText += "<span style=\"color: Black; font-weight: normal;\">" + FlowId + "</span></td></tr>";

                    printText += "<tr>";
                    printText += "<td style=\"width: 30%;  color: Red;text-align:left;\">客户号码：";
                    printText += "<span style=\"color: Black; font-weight: normal;\">" + p_number + "</span></td>";
                    printText += "<td style=\"width: 40%; font-size: " + contentFontSize + "; color: Red;text-align:left;\">计费周期：";
                    printText += "<span style=\"color: Black; font-weight: normal;\">" + p_printCycle + "</span></td></tr></table></div>";
                    //账单内容
                    printText += "<table width=\"90%\" border=\"1\" bordercolor=\"#FF0000\" style=\"text-align: left; line-height: 22px; margin-left: 10px;font-size: " + contentFontSize + "\" cellpadding=\"0\" cellspacing=\"0\">";
                    printText += "<tr>";
                    printText += "<td colspan=\"6\">";
                    printText += "<span style=\"color: #FF0000; ;\">&nbsp;&nbsp;缴费日期：</span>" + nowDate + "</td></tr>";
                    printText += "<tr>";
                    //项标题
                    for (var ii = 0; ii < 3; ii++) {
                        printText += "<td width=\"20%\" style=\"text-align: center; color: Red; font-weight: bold;\">项目</td>";
                        printText += "<td width=\"14%\" style=\"text-align: center; color: Red; font-weight: bold;\">金额(元)</td>";
                    }
                    printText += "</tr>";
                    //账单项内容
                    printText += "<tr>";
                    printText += "<td style=\"height: 200px; vertical-align:text-top;\">" + cellContent1 + "</td>";
                    printText += "<td style=\"height: 200px; vertical-align:text-top;\">" + cellContent2 + "</td>";
                    printText += "<td style=\"height: 200px; vertical-align:text-top;\">" + cellContent3 + "</td>";
                    printText += "<td style=\"height: 200px; vertical-align:text-top;\">" + cellContent4 + "</td>";
                    printText += "<td style=\"height: 200px; vertical-align:text-top;\">" + cellContent5 + "</td>";
                    printText += "<td style=\"height: 200px; vertical-align:text-top;\">" + cellContent6 + "</td>";
                    printText += "</tr>";

                    printText += "<tr><td colspan=\"6\" style=\"font-weight: bold; color: Red; height: 30px;vertical-align: top;\">&nbsp;</td></tr>";
                    //结算项
                    printText += "<tr style=\"border-top: 1px solid #000000;\">";
                    printText += "<td style=\"height: 30px; line-height: 30px; font-weight: normal;\" colspan=\"6\">";
                    printText += "<span style=\"padding-left: 10px;\">&nbsp;</span>明细合计：￥" + Math.round(parseFloat(currBillMonthFee) * 100) / 100 + "元";
                    printText += "<span style=\"padding-left:30px;\">&nbsp;&nbsp;&nbsp;</span>余额：￥" + clientbalace + "元</td></tr>";

                    printText += "<tr>";
                    printText += "<td colspan=\"6\" style=\"height: 30px; line-height: 30px; font-weight: normal;\">";
                    printText += "<span style=\"padding-left: 10px;\">&nbsp;</span>合计：￥" + shishoufee;
                    printText += "元(实收)<span style=\"padding-left: 30px;\">&nbsp;</span>人民币合计(大写)：" + upperTotalFee + "</td></tr>";
                    printText += "</table>";
                    //客户热线
                    printText += "<div style=\"width:90%; text-align:left;\">";
                    printText += "<span style=\"padding-left: 20px; font-size: 15px;\"><font face=\"楷体_GB2312\" color=\"#FF0000\">";
                    printText += "客户热线：10000</font></span><span style=\"font-size: 15px; color: #FF0000; padding-left: 280px;font-weight: bold;\">代收点：</span>";
                    printText += "</div>";
                    printText += "</body></html>";


                    if (printText.length > 0) {
                        var printTextObj = document.getElementById("PrintContent");
                        printTextObj.value = printText;
                    }
                }
            }
            else if (jasonText.retcode == "101") {
                Dialog.Alert(jasonText.retresult);
            }
            else if (jasonText.retcode == "101") {
                Dialog.Alert("查询客户账单信息已超时,无法进行账单的自动打印");
            }
            else {
                Dialog.Alert("查询客户账单信息错误,无法进行账单的自动打印");
            }
        }

        //创建串口打印内容
        function CreateCKHtml() {
            var jasonText = eval(CurrentMonthBillInfo);
            var FlowId = document.getElementById('identityIdTxt').value;        //流水号
            if (jasonText.retcode == "100") {
                if (itemCount == 0) {
                    Dialog.Alert("客户账单信息为空.请确认客户号码是否已出账.电信出账日为每月的4至6号.");
                }
                else {
                    var p_username = jasonText.username;
                    var p_number = document.getElementById('txtCustomNumber').value;
                    var p_printCycle = "";

                    var vouchermoney = parseInt(jasonText.vouchermoney); //充值金额,单位:分
                    var billtotalfee = parseInt(jasonText.billtotalfee); //账单欠费总金额:分
                    var currentmonthssfee = parseInt(jasonText.currentmonthssfee); //该月实收金额:分
                    var currentmonthfee = parseInt(jasonText.currentmonthfee); //该月账单总费用:分
                    CurrentMonthFees = currentmonthssfee;
                    BillTotalCnt = jasonText.monthes; //总的账期数

                    var shishoufee = parseFloat(currentmonthssfee / 100.0).toFixed(2);  //实收

                    //填票时间
                    var nowDate = getNowTime();

                    var itemCount = parseInt(jasonText.itemCount);
                    var billMonthIndex = 0;
                    var currBillMonthFee = 0.00;
                    var rowIndex = 0;
                    var ckBillInfo = "";            //账单信息
                    var totalFee = "";              //合计小写
                    var upperTotalFee = "";         //合计大写

                    for (var i = 0; i < itemCount; i++) {
                        if (i == 10) {
                            break;
                        }

                        if (eval("jasonText.billItem" + i + ".item") == "0") //计费周期开始
                        {
                            p_printCycle = FormatDateTimeStr(eval("jasonText.billItem" + i + ".number")) + ":" + FormatDateTimeStr(eval("jasonText.billItem" + i + ".itemfee"));
                            billMonthIndex = billMonthIndex + 1;
                        }
                        else {//账单细项
                            //欠费总金额大于实收,不显示账单细项
                            if (currentmonthfee > currentmonthssfee) {
                                ckBillInfo += "\n";
                                currBillMonthFee = currBillMonthFee + parseFloat(eval("jasonText.billItem" + i + ".itemfee"));
                            }
                            else {
                                ckBillInfo += " " + eval("jasonText.billItem" + i + ".itemname") + "        " + Math.round(parseFloat(eval("jasonText.billItem" + i + ".itemfee")) * 100) / 100 + "\n";
                                currBillMonthFee = currBillMonthFee + parseFloat(eval("jasonText.billItem" + i + ".itemfee"));
                            }
                            rowIndex++;
                        }
                    }
                    //小计最后一个计费周期总费用
                    if (billMonthIndex > 0) {
                        for (var j = rowIndex; j < 9; j++) {
                            ckBillInfo += "\n";
                        }

                        totalFee = Math.round(parseFloat(shishoufee) * 100) / 100 + "元";
                        if (Math.round(parseFloat(shishoufee) * 100) / 100 > 0) {
                            upperTotalFee = Anpworld.ConvertCurrency(shishoufee);
                        }
                        else { upperTotalFee = "零元"; }
                    }

                    var printText = "\n\n\n\n\n\n\n\n\n";
                    printText += "        " + nowDate + "\n\n";                   //填票时间
                    printText += "      " + p_username + "\n\n";                  //用户名
                    printText += "        " + p_number + "\n\n";                  //用户号码
                    printText += "      " + FlowId + "\n";                        //流水号
                    printText += "          12345678\n";                          //系统参考号
                    printText += "      " + p_printCycle + "\n\n\n";            //缴费月
                    printText += ckBillInfo;                                       //后付费账单项内容
                    printText += "        " + totalFee + "\n";                  //合计小写
                    printText += "                 " + upperTotalFee;             //合计大写
                    printText += "\n\n\n\n\n\n";
                    if (printText.length > 0) {
                        var printTextObj = document.getElementById("PrintContent");
                        printTextObj.value = printText;
                    }
                }
            }
            else if (jasonText.retcode == "101") {
                Dialog.Alert(jasonText.retresult);
            }
            else if (jasonText.retcode == "101") {
                Dialog.Alert("查询客户账单信息已超时,无法进行账单的自动打印");
            }
            else {
                Dialog.Alert("查询客户账单信息错误,无法进行账单的自动打印");
            }
        }

        function CreateHtml() {
            var printText = "";
            printText += "<html><head><title></title>";
            printText += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">";
            printText += "</head><body>";
            var jasonText = eval(CurrentMonthBillInfo);
            if (jasonText.retcode == "100") {
                var p_username = jasonText.username;
                var p_number = document.getElementById('txtCustomNumber').value;

                var vouchermoney = parseInt(jasonText.vouchermoney); //充值金额,单位:分
                var billtotalfee = parseInt(jasonText.billtotalfee); //账单欠费总金额:分
                var currentmonthssfee = parseInt(jasonText.currentmonthssfee); //该月实收金额:分
                var currentmonthfee = parseInt(jasonText.currentmonthfee); //该月账单总费用:分
                CurrentMonthFees = currentmonthssfee;

                BillTotalCnt = jasonText.monthes; //总的账期数

                //客户余额,单位:元
                var clientbalace = (currentmonthssfee - currentmonthfee) / 100.0;
                var shishoufee = parseFloat(currentmonthssfee / 100.0).toFixed(2);

                var today = new Date();
                var to_year = today.getFullYear();
                var to_month = today.getMonth() + 1;
                var to_day = today.getDate();

                var p_printdate = "" + to_year + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                p_printdate += (to_month > 9 ? to_month : "0" + to_month) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                p_printdate += to_day > 9 ? to_day : "0" + to_day;

                var itemCount = parseInt(jasonText.itemCount);
                var billMonthIndex = 0;
                var currBillMonthFee = 0.00;
                var rowIndex = 0;

                if (itemCount == 0) {
                    Dialog.Alert("客户账单信息为空.请确认客户号码是否已出账.电信出账日为每月的4至6号.");
                }
                else {
                    for (var i = 0; i < itemCount; i++) {
                        if (i == 10) {
                            break;
                        }

                        if (eval("jasonText.billItem" + i + ".item") == "0") //计费周期开始
                        {
                            printText += "<div style=\"font-size:" + MyFontSize + "px;font-family: 宋体;\">";

                            var temp_top = parseInt(Billinfodetailtop) - 115;
                            printText += "<div style=\"position:absolute; left:" + BillinfodetailLeft + "px; top:" + temp_top + "px;\">";       //账单套打的位置取了账单明细的位置

                            temp_top = parseInt(UserNameTop);
                            printText += "<div style=\"width:240px;position:absolute; left:" + UserNameLeft + "px; top:" + temp_top + "px;\">";
                            printText += p_username + "</div>";

                            temp_top = parseInt(CustomerNoTop);
                            printText += "<div style=\"width:150px;position:absolute; left:" + CustomerNoLeft + "px; top:" + temp_top + "px;\">";
                            printText += p_number + "</div>";

                            temp_top = parseInt(BillingcycleTop);
                            printText += "<div style=\"width:350px;position:absolute; left:" + BillingcycleLeft + "px; top:" + temp_top + "px;\">";
                            printText += FormatDateTimeStr(eval("jasonText.billItem" + i + ".number")) + ":" + FormatDateTimeStr(eval("jasonText.billItem" + i + ".itemfee")) + "</div>";

                            temp_top = parseInt(FilldateTop);
                            printText += "<div style=\"width:300px;position:absolute; left:" + FilldateLeft + "px; top:" + temp_top + "px;\">";
                            printText += p_printdate + "</div>";

                            temp_top = 120;
                            printText += "<table cellpadding=\"0\" cellspacing =\"0\" style=\"border-collapse:collapse; margin-left:25px; text-align :left; width:500px;border:solid 0px white; position:absolute; left:8px; top:" + temp_top + "px; font-size:" + MyFontSize + "px;\">";

                            billMonthIndex = billMonthIndex + 1;
                        }
                        else {//账单细项
                            if (rowIndex < 10) {
                                //欠费总金额大于实收,不显示账单细项
                                if (currentmonthfee > currentmonthssfee) {
                                    //第一二列
                                    printText += "<tr><td style=\"text-align:left;border:solid 0px white; width:20%;\">";
                                    printText += "&nbsp;&nbsp;";
                                    printText += "</td><td style=\"text-align:right; border:solid 0px white; width:14%;\">";
                                    printText += "&nbsp;&nbsp;";
                                    printText += "</td>";
                                    currBillMonthFee = currBillMonthFee + parseFloat(eval("jasonText.billItem" + i + ".itemfee"));

                                    //第三四 及五六列
                                    var nextIndex = (i + 1) * 10 + i;
                                    for (var k = 0; k < 2; k++) {
                                        if (nextIndex < itemCount) {
                                            var oribillid = eval("jasonText.billItem" + i + ".billid");
                                            var curbillid = eval("jasonText.billItem" + nextIndex + ".billid");
                                            if (oribillid == curbillid) {
                                                printText += "<td style=\"text-align:left;  border:solid 0px white;width:20%;\">";
                                                printText += "&nbsp;&nbsp;";
                                                printText += "</td><td style=\"text-align:right; border:solid 0px white;width:13%;\">";
                                                printText += "&nbsp;&nbsp;";
                                                printText += "</td>";
                                                currBillMonthFee = currBillMonthFee + parseFloat(eval("jasonText.billItem" + nextIndex + ".itemfee"));
                                            } //end if
                                            else {
                                                printText += "<td style=\"text-align:center; border:solid 0px white; width:20%;\">";
                                                printText += "&nbsp;&nbsp;";
                                                printText += "</td><td style=\"text-align:center; border:solid 0px white; width:13%;\">";
                                                printText += "&nbsp;";
                                                printText += "</td>";
                                            } //end else
                                        } //end if
                                        else {
                                            printText += "<td style=\"text-align:center; border:solid 0px white; width:20%;\">";
                                            printText += "&nbsp;&nbsp;";
                                            printText += "</td><td style=\"text-align:center; border:solid 0px white; width:13%;\">";
                                            printText += "&nbsp;";
                                            printText += "</td>";
                                        } //end else

                                        nextIndex = nextIndex + 10;
                                    } //end for
                                    printText += "</tr>";
                                }
                                else {
                                    //第一二列
                                    printText += "<tr><td style=\"text-align:left; border:solid 0px white; width:20%;\">";
                                    printText += eval("jasonText.billItem" + i + ".itemname");
                                    printText += "</td><td style=\"text-align:right; border:solid 0px white; width:14%;\">";
                                    printText += Math.round(parseFloat(eval("jasonText.billItem" + i + ".itemfee")) * 100) / 100;
                                    printText += "</td>";
                                    currBillMonthFee = currBillMonthFee + parseFloat(eval("jasonText.billItem" + i + ".itemfee"));

                                    //第三四 及五六列
                                    var nextIndex = (i + 1) * 10 + i;
                                    for (var k = 0; k < 2; k++) {
                                        if (nextIndex < itemCount) {
                                            var oribillid = eval("jasonText.billItem" + i + ".billid");
                                            var curbillid = eval("jasonText.billItem" + nextIndex + ".billid");
                                            if (oribillid == curbillid) {
                                                printText += "<td style=\"text-align:left;border:solid 0px white;width:20%;\">";
                                                printText += eval("jasonText.billItem" + nextIndex + ".itemname");
                                                printText += "</td><td style=\"text-align:right; border:solid 0px white;width:13%;\">";
                                                printText += Math.round(parseFloat(eval("jasonText.billItem" + nextIndex + ".itemfee")) * 100) / 100;
                                                printText += "</td>";
                                                currBillMonthFee = currBillMonthFee + parseFloat(eval("jasonText.billItem" + nextIndex + ".itemfee"));
                                            } //end if
                                            else {
                                                printText += "<td style=\"text-align:center; border:solid 0px white; width:20%;\">";
                                                printText += "&nbsp;&nbsp;";
                                                printText += "</td><td style=\"text-align:center; border:solid 0px white; width:13%;\">";
                                                printText += "&nbsp;";
                                                printText += "</td>";
                                            } //end else
                                        } //end if
                                        else {
                                            printText += "<td style=\"text-align:center; border:solid 0px white; width:20%;\">";
                                            printText += "&nbsp;&nbsp;";
                                            printText += "</td><td style=\"text-align:center; border:solid 0px white; width:13%;\">";
                                            printText += "&nbsp;";
                                            printText += "</td>";
                                        } //end else

                                        nextIndex = nextIndex + 10;
                                    } //end for
                                    printText += "</tr>";
                                }
                            } //end if
                            rowIndex++;
                        } //end else
                    } //end for

                    //小计最后一个计费周期总费用
                    if (billMonthIndex > 0) {
                        for (var j = rowIndex; j < 10; j++) {
                            printText += "<tr><td colspan=\"6\" style=\"border:solid 0px white;\">&nbsp;&nbsp;</td></tr>";
                        }

                        printText += "</table>";

                        printText += "<div style=\"width:180px;text-align:left;border:solid 0px white;position:absolute; left:" + TotalpricelowerLeft + "px; top:" + TotalpricelowerTop + "px;\">";
                        printText += "应收：￥" + Math.round(parseFloat(currBillMonthFee) * 100) / 100 + "元</div>";

                        printText += "<div style=\"width:350px;position:absolute;border:solid 0px white; left:" + TotalPriceRealUpperLeft + "px; top:" + TotalPriceRealUpperTop + "px;\">";
                        printText += "大写：" + Anpworld.ConvertCurrency(shishoufee) + "</div>";
                        printText += "<div style=\"width:180px;text-align:left;position:absolute;border:solid 0px white; left:" + TotalPriceRealLowerLeft + "px; top:" + TotalPriceRealLowerTop + "px;\">";
                        printText += "实收：￥" + shishoufee + "元</div>";

                        printText += "<div style=\"width:180px;text-align:left;position:absolute;border:solid 0px white; left:" + ClientBalanceLowerLeft + "px; top:" + ClientBalanceLowerTop + "px;\">";
                        printText += "余额：￥" + clientbalace + "元</div>";

                        printText += "<div style=\"width:100px;position:absolute;border:solid 0px white; left:" + RemarkLeft + "px; top:" + RemarkTop + "px;\"></div>";
                        printText += "</div></div></body></html>";
                    }
                }
            }
            else if (jasonText.retcode == "101") {
                Dialog.Alert(jasonText.retresult);
            }
            else if (jasonText.retcode == "101") {
                Dialog.Alert("查询客户账单信息已超时,无法进行账单的自动打印");
            }
            else {
                Dialog.Alert("查询客户账单信息错误,无法进行账单的自动打印");
            }

            if (printText.length > 0) {
                var printTextObj = document.getElementById("PrintContent");
                printTextObj.value = printText;
                AutoPrintForXTTD();
            }
        }

        function FormatDateTimeStr(str) {
            if (str == null || str == "") {
                return "";
            }
            str = str.toString();
            return str.substring(0, 4) + '-' + str.substring(4, 6) + '-' + str.substring(6, 8);
        }
        //账单自动套打 结束

        //账单串口打印开始
        function AutoPrintBillByCK() {
            Dialog.Running("<font color=red size=4>系统正在处理中,请稍候.....</font>");
            var FlowId = document.getElementById('identityIdTxt').value;
            var requestparam = "flowid=" + FlowId + "&month=" + BillCurrentIndex;
            var call = Anpworld.AjaxCall("/CTProduct/Refill/GetCurrentMonthForCKPrint", requestparam, QueryBillForCKPrintOver, DoQueryBillForCKPrintError);
        }

        function QueryBillForCKPrintOver(response) {
            Dialog.Running.Close();
            if (response != null || response != "") {
                var jasonText = eval(response);
                if (jasonText.retcode == "100") {
                    CurrentMonthFees = parseInt(jasonText.currentmonthssfee); //该月实收金额:分
                    BillTotalCnt = jasonText.monthes; //总的账期数

                    var mylblPrintContent = document.getElementById("PrintContent");
                    mylblPrintContent.value = jasonText.retresult;
                    AutoPrintForCKTD();
                }
                else if (jasonText.retcode == "101") {
                    Dialog.Alert(jasonText.retresult);
                }
                else {
                    Dialog.Alert("查询客户账单信息错误,无法进行账单的自动打印");
                }
            }
            else {
                Dialog.Alert("客户账单信息为空,无法进行账单的自动打印.");
            }
        }

        function DoQueryBillForCKPrintError() {
            Dialog.Running.Close();
            Dialog.Alert("客户账单信息查询超时,无法进行账单的自动打印.");
        }

        //账单串口打印
        function AutoPrintForCKTD() {
            //暂时 取消 余额小于0不打印的限制
            //            if (CurrentMonthFees < 0) {
            //                alert("第 " + (BillCurrentIndex + 1) + " 个账期的实收金额小于零，不进行打印操作.");
            //                return false;
            //            }
            //            else {
            AutoPrintForCK();
            //            }

            BillCurrentIndex = BillCurrentIndex + 1;  //下一个账期的序号
            if (BillCurrentIndex < BillTotalCnt) {
                Dialog.ConfirmWith("<font color=red size=4>第 " + BillCurrentIndex + " 个账期打印完毕，总共有" + BillTotalCnt + " 个账期,是否继续打印第 " + (BillCurrentIndex + 1) + " 个账期?</font>",
                    function() {
                        CurrentMonthFees = 0;
                        AutoPrintBillByCK();
                    },
                    function() {
                        CurrentMonthFees = 0;
                        BillTotalCnt = 0;
                        BillCurrentIndex = 0;
                    },
                    400, 250);
            }
            else {
                BillCurrentIndex = 0;
                CurrentMonthFees = 0;
            }
        }
        //账单串口打印结束

        //账单查询之后进行账单套打 开始
        function PrintBill() {
            if (BillString == "") {
                alert("账单内容为空,无法进行打印操作.");
                return false;
            }
            var jasonText = eval(BillString);
            if (jasonText.retcode == "0") {
                var itemCount = parseInt(jasonText.itemCount);
                if (itemCount == 0) {
                    alert("账单内容为空,无法进行打印操作.");
                    return false;
                }
                else {
                    GetAllMonthFromBillString(); //获取该次账单中所有的账期
                    GetCurrentBillIndex(); //获取本月的数据索引
                    CreateInnerText(); //创建本月账单的信息
                }
            }
            else {
                alert("账单内容为空,无法进行打印操作.");
                return false;
            }
        }

        function GetAllMonthFromBillString() {
            BillMonthArray = new Array();
            var jasonText = eval(BillString);
            var itemCount = parseInt(jasonText.itemCount);
            var tempbillid = "";
            for (var i = 0; i < itemCount; i++) {
                var temp = eval("jasonText.billItem" + i + ".billid");
                if (temp != tempbillid) {
                    tempbillid = temp;
                    BillMonthArray[BillTotalCount] = temp;
                    BillTotalCount++;
                }
            }
        }

        function GetCurrentBillIndex() {
            CurrentBillString = "";
            var jasonText = eval(BillString);
            var itemCount = parseInt(jasonText.itemCount);
            var tempbillid = BillMonthArray[BillIndex];

            for (var i = 0; i < itemCount; i++) {
                var temp = eval("jasonText.billItem" + i + ".billid");
                if (temp == tempbillid) {
                    CurrentBillString = CurrentBillString + i.toString() + ",";
                }
            }
        }

        //创建当前要打印的账单信息
        function CreateInnerText() {
            var printText = "";
            printText += "<html><head><title></title>";
            printText += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">";
            printText += "</head><body>";
            var jasonText = eval(BillString);

            var p_username = jasonText.username;
            var p_number = document.getElementById('txtCustomNumber').value;

            var today = new Date();
            var to_year = today.getFullYear();
            var to_month = today.getMonth() + 1;
            var to_day = today.getDate();
            var p_printdate = "" + to_year + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            p_printdate += (to_month > 9 ? to_month : "0" + to_month) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            p_printdate += to_day > 9 ? to_day : "0" + to_day;

            var array = CurrentBillString.split(',');
            var itemCount = array.length - 1;
            var rowIndex = 0;
            var billMonthIndex = 0;

            printText += "<div style=\"font-size:" + MyFontSize + "px;font-family: 宋体;\">";
            var temp_top = parseInt(Billinfodetailtop) - 115;
            printText += "<div style=\"position:absolute; left:" + BillinfodetailLeft + "px; top:" + temp_top + "px;\">";
            temp_top = parseInt(UserNameTop);
            printText += "<div style=\"width:240px;position:absolute; left:" + UserNameLeft + "px; top:" + temp_top + "px;\">";
            printText += p_username + "</div>";
            printText += "<div style=\"width:150px;position:absolute; left:" + CustomerNoLeft + "px; top:" + temp_top + "px;\">";
            printText += p_number + "</div>";temp_top = parseInt(FilldateTop);
            printText += "<div style=\"width:300px;position:absolute; left:" + FilldateLeft + "px; top:" + temp_top + "px;\">";
            printText += p_printdate + "</div>";
            temp_top = 120;
            printText += "<table cellpadding=\"0\" cellspacing =\"0\" style=\"border-collapse:collapse; margin-left:25px; text-align :left; width:500px;border:solid 0px white; position:absolute; left:8px; top:" + temp_top + "px; font-size:" + MyFontSize + "px;\">";

            //billMonthIndex = billMonthIndex + 1;

            var cycleFlag = 0;
            var cycleStr = "";
            var cycleStr1 = "";
            for (var i = 0; i < itemCount; i++) {
                if (rowIndex == 10) {
                    break;
                }

                if (eval("jasonText.billItem" + array[i] + ".item") == "0") //计费周期开始
                {
                    cycleFlag = 1;
                    temp_top = parseInt(BillingcycleTop); 
                    cycleStr="<div style=\"width:350px;position:absolute; left:" + BillingcycleLeft + "px; top:" + temp_top + "px;\">"
                    cycleStr += FormatDateTimeStr(eval("jasonText.billItem" + array[i] + ".number")) + ":" + FormatDateTimeStr(eval("jasonText.billItem" + array[i] + ".itemfee"))+"<div>" ;

                }
                else {
                    temp_top = parseInt(BillingcycleTop); 
                    cycleStr1 = "<div style=\"width:350px;position:absolute; left:" + BillingcycleLeft + "px; top:" + temp_top + "px;\">"
                    cycleStr1 += eval("jasonText.billItem" + 1 + ".billid")
                //账单细项
                    //第一二列
                    printText += "<tr><td style=\"text-align:left; border:solid 0px white; width:20%;\">";
                    printText += eval("jasonText.billItem" + array[i] + ".itemname");
                    printText += "</td><td style=\"text-align:right; border:solid 0px white; width:14%;\">";
                    printText += Math.round(parseFloat(eval("jasonText.billItem" + array[i] + ".itemfee")) * 100) / 100;
                    printText += "</td>";
                    CurrentBillFees = CurrentBillFees + parseFloat(eval("jasonText.billItem" + array[i] + ".itemfee"));

                    //第三四 及五六列
                    var nextIndex = (i + 1) * 10 + i;
                    for (var k = 0; k < 2; k++) {
                        if (nextIndex < itemCount) {
                            var oribillid = eval("jasonText.billItem" + array[i] + ".billid");
                            var curbillid = eval("jasonText.billItem" + nextIndex + ".billid");
                            if (oribillid == curbillid) {
                                printText += "<td style=\"text-align:left;border:solid 0px white;width:20%;\">";
                                printText += eval("jasonText.billItem" + nextIndex + ".itemname");
                                printText += "</td><td style=\"text-align:right; border:solid 0px white;width:13%;\">";
                                printText += Math.round(parseFloat(eval("jasonText.billItem" + nextIndex + ".itemfee")) * 100) / 100;
                                printText += "</td>";
                                CurrentBillFees = CurrentBillFees + parseFloat(eval("jasonText.billItem" + nextIndex + ".itemfee"));
                            } //end if
                            else {
                                printText += "<td style=\"text-align:center; border:solid 0px white; width:20%;\">";
                                printText += "&nbsp;&nbsp;";
                                printText += "</td><td style=\"text-align:center; border:solid 0px white; width:13%;\">";
                                printText += "&nbsp;";
                                printText += "</td>";
                            } //end else
                        } //end if
                        else {
                            printText += "<td style=\"text-align:center; border:solid 0px white; width:20%;\">";
                            printText += "&nbsp;&nbsp;";
                            printText += "</td><td style=\"text-align:center; border:solid 0px white; width:13%;\">";
                            printText += "&nbsp;";
                            printText += "</td>";
                        } //end else

                        nextIndex = nextIndex + 10;
                    } //end for
                    printText += "</tr>";
                }
                rowIndex++;
            } //end else


            //小计最后一个计费周期总费用
            //if (billMonthIndex > 0) {
                for (var j = rowIndex; j < 10; j++) {
                    printText += "<tr><td colspan=\"6\" style=\"border:solid 0px white;\">&nbsp;&nbsp;</td></tr>";
                }
                //var TotalPriceRealUpperTop2 = parseInt(TotalPriceRealUpperTop) + 6;
                printText += "</table>";

                printText += "<div style=\"width:180px;text-align:left;border:solid 0px white;position:absolute; left:" + TotalPriceRealUpperLeft + "px; top:" + TotalpricelowerTop + "px;\">";
                printText += "总计：￥" + Math.round(parseFloat(CurrentBillFees) * 100) / 100 + "元</div>";

                printText += "<div style=\"width:350px;position:absolute;border:solid 0px white; left:" + TotalPriceRealUpperLeft + "px; top:" + TotalPriceRealUpperTop + "px;\">";
                printText += "大写：" + Anpworld.ConvertCurrency(Math.round(parseFloat(CurrentBillFees) * 100) / 100) + "</div>";
                printText += "<div style=\"width:180px;text-align:left;position:absolute;border:solid 0px white; left:" + TotalPriceRealLowerLeft + "px; top:" + TotalPriceRealLowerTop + "px;\">";
                printText += "</div>";

                printText += "<div style=\"width:180px;text-align:left;position:absolute;border:solid 0px white; left:" + ClientBalanceLowerLeft + "px; top:" + ClientBalanceLowerTop + "px;\">";
                printText += "</div>";


                if (cycleFlag == 1) {
                    printText += cycleStr;
                }
                else { printText += cycleStr1; }

                printText += "<div style=\"width:100px;position:absolute;border:solid 0px white; left:" + RemarkLeft + "px; top:" + RemarkTop + "px;\"></div>";
                printText += "</div></div></body></html>";
            //}

            if (printText.length > 0) {
                var printTextObj = document.getElementById("PrintContent");
                printTextObj.value = printText;

                AutoPrintEveryBill();
            }
        }

        function AutoPrintEveryBill() {
            var printText = document.getElementById("PrintContent");
            var lbl = document.getElementById("lblPrintContent");
            lbl.innerHTML = printText.value;
            document.getElementById("title").style.display = "none";
            document.getElementById("step2").style.display = "none";

            //new method
            var WinPrint = window.open('', '', 'letf=10,top=10,width=680,toolbar=0,scrollbars=0,status=0,menubar=0');
            WinPrint.document.write(lbl.innerHTML);
            WinPrint.document.close();
            WinPrint.focus();
            WinPrint.print();
            WinPrint.close();
            //end method

            document.getElementById("title").style.display = "";
            document.getElementById("step2").style.display = "";
           

            lbl.innerHTML = "";
            printText.value = "";

            BillIndex = BillIndex + 1;  //下一个账期的序号
            if (BillIndex < BillTotalCount) {
                Dialog.ConfirmWith("<font color=red size=4>第 " + BillIndex + " 页打印完毕，总共有" + BillTotalCount + "页,是否继续打印第 " + (BillIndex + 1) + " 页?</font>",
                    function() {
                        CurrentBillFees = 0;
                        GetCurrentBillIndex();
                        CreateInnerText();
                    },
                    function() {
                        CurrentBillFees = 0;
                        BillTotalCount = 0;
                        BillIndex = 0;
                    },
                    400, 250);
            }
            else {
                BillIndex = 0;
                BillTotalCount = 0;
                CurrentBillFees = 0;
            }
        }

        //账单查询之后进行账单套打  结束

        //取得当前时间 
        function getNowTime() {
            var now = new Date();
            var year = now.getYear();
            var month = now.getMonth() + 1;
            var day = now.getDate();
            var hour = now.getHours();
            var minute = now.getMinutes();
            var second = now.getSeconds();
            var nowdate = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
            return nowdate;
        }  
    </script>


    <style type="text/css">
        body{font-size:12px;color: #000000;font-family: Arial, Helvetica, sans-serif, 宋体;margin: 0;padding: 0;height:100%; overflow:auto;}
    </style>
    <script language="javascript" type="text/javascript">
        $(function() {
            $.datepicker.setDefaults($.datepicker.regional['zh-CN']);
        });
    </script>
</head>
<body>
    
    <div id="title" class="tab_right_position">
        
<div class="ui-widget">
    <div class="ui-state-highlight ui-corner-all" style="margin-top: 0px; padding: 0 .7em;width:100%">
        <p>
            <span class="ui-icon ui-icon-home" style="float: left; margin-right: .3em; margin-top:.6em;"></span>
            <strong>当前页：
            【电信缴费】
            为电信固话、宽带、天翼手机、小灵通充值缴费。
            </strong>
        </p>
    </div>
</div>
    </div>
    <div id="step1">
        <div class="tab_right_position">
            
            <form id="fm_refill" action="" method="post">
            <table id="tab_table_style" style="width: 100%">
                <tr>
                    <td class="fontsize">
                        电信号码：
                    </td>
                    <td colspan="2">
                        <input id="CustomerNumber" name="CustomerNumber" type="text" maxlength="30" /><br />
                        未输入区号时,默认区号<b style="color: Red">020</b>,修改区号请<a href="#" onclick="parent.addTabPage('账户管理','/UserAccount/AccountPublic/AgentConfigIndex',1);"
                            style="color: Blue"><b>点击这里</b></a>
                    </td>
                </tr>
                <tr>
                    <td class="fontsize">
                        充值缴费方式：
                    </td>
                    <td colspan="2">
                        <input id="VoucherType1" name="VoucherType" type="radio" value="000004" onfocus="this.checked=true;" />综合缴费
                        <input id="VoucherType2" name="VoucherType" type="radio" value="000001" onfocus="this.checked=true;" />单一充值
                    </td>
                </tr>
                <tr style="height: 30px">
                    <td colspan="3" style="text-align: center;">
                        <input id="btn_Query" type="submit" value="查询余额" />
                    </td>
                </tr>
            </table>
            </form>
        </div>
        <div class="ui-widget" style="width: 97%">
            <div class="ui-corner-all" style="margin-top: 0px; padding: 0 .7em;">
                <p>
                    <span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
                    <strong>温馨提示：</strong>
                    <ul style="line-height: 20px; list-style-type: none; margin-left: 20px">
                        <li style="margin-left: 20px"><font color="#209c92">1、综合缴费：两个以上电信产品捆绑套餐用户使用综合缴费，如IP超市，我的E家，政企客户。
                            (<font color="red">不能单独为宽带缴费</font>，宽带缴费请选择[<a href="#" onclick="parent.addTabPage('电信交易','/CTProduct/Refill/BroadbandPayment',1);"><font
                                color="blue">宽带缴费</font></a>])</font> </li>
                        <li style="margin-left: 20px"><font color="#209c92">2、单一充值：电信的固定电话，小灵通，天翼手机等为本号码缴费使用单一充值。</font>
                            (<font color="red">不能为宽带缴费</font>) </li>
                        <li style="margin-left: 20px">注意：可以输入电信用户号码，包括电信固话、小灵通、天翼手机（180/189/133/153），宽带捆绑的固定电话，按查询余额继续。
                        </li>
                    </ul>
                </p>
            </div>
        </div>
        <div class="tab_right_position">
            
<div class="ui-widget">
    <div class="ui-state-highlight ui-corner-all" style="margin-top: 0px; padding: 0 .7em; width:100%">
        <p style=" width:100%">
            <span class="ui-icon ui-icon-circle-zoomin" style="float: left; margin-right: .3em; margin-top:.6em;"></span>
            <strong>
            【24小时内最近十笔电信的交易记录】
            </strong>
        </p>
    </div>
</div>
<style type="text/css">
td, th {
	border:1px solid #BCD8EC;
	padding:4px;
}
th {
	background:#F1FAFF; text-align:center;
}
</style>
<div id="divRecentTranList">
        <table border="1" style="line-height:20px; width:100%" id="tabRecentTranList">
            <tr style="height: 25px;">
                <th>序号1</th>
                <th>交易时间</th>
                <th>客户号码</th>
                <th>交易金额</th>
                <th>客户参考余额</th>
                <th>交易状态</th>              
                <th id="rollBackDisplay">冲正</th>
                <th>充值缴费类型</th>
                <th>凭证打印</th>
            </tr>
            
                <tr>
                <td colspan="9" align="center">
                 无记录   
                </td> 
                </tr>
                
        </table>
        <div style="width:100%; line-height:22px; margin:4px;">
            <script language="javascript" type="text/javascript">
function RecentTranListRefresh() {$.ajax({type:'post',url:'/UserAccount/Account/RecentTranList?pageIndex=1',success:function(data,status,xhr){$('#divRecentTranList').html(data);},beforeSend:Onloading});return false;; }</script>
        </div>
</div>

<script language="javascript" type="text/javascript">
    function Onloading() {
        $('#divRecentTranList').html('<img src="../../Content/images/loading.gif" />加载数据，请稍候......');
    }
    function DoRollBack(Calee, OriginalFee) {
        document.getElementById("VoucherMoney").value = OriginalFee / 100.00;
        document.getElementById("CustomerNumber").value = Calee;
        document.getElementById("ServicePassword").focus();
    }
    $().ready(function () {
        var rollBack = $("#VCButton").val();
        var tab = document.getElementById('tabRecentTranList');
        //var col = tab.cols[7];
        var rows = tab.rows.length
        var isShow = "none"
        if (rollBack == "冲  正")
            isShow = "";
        else
            isShow = "none";
        $("#rollBackDisplay").css("display", isShow);
        for (i = 1; i < rows; i++) {
            //var a = tab.rows[i].cells[6].childNodes[0].innerHTML;
            //alert(a);
            if (tab.rows[i].cells[6] != null) {
                tab.rows[i].cells[6].style.display = isShow;
            }
        }
    });
</script>

</div>
    </div>
    <div id="step2" style="display: none;">
        <!-- Start 充值缴费界面-->
        <div id="noPrintDiv1" class="tab_right_position">
            <form id="fm_voucher" action="" method="post">
            <fieldset>
                <table width="100%">
                    <tr>
                        <td class="fontsize">
                            充值缴费金额：
                        </td>
                        <td>
                            <input id="VoucherMoney" name="VoucherMoney" maxlength="10" type="text" value="" />
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="fontsize">
                            交易密码：
                        </td>
                        <td>
                            <input id="ServicePassword" name="ServicePassword" type="password" maxlength="8" />
                        </td>
                    </tr>
                    
                    <tr>
                        <td align="center" height="30" colspan="2">
                            <input type="submit" value="充值缴费" id="btn_Refill" />
                            <input type="button" value="取消返回" id="btn_Back" onclick="Javascript:GoBack();" />
                        </td>
                    </tr>
                </table>
            </fieldset>
            </form>
        </div>
        <!--End 充值缴费界面-->
        <!--Start 客户号码信息 -->
        <table cellspacing="0" cellpadding="2" style="border: 1px solid #000000; margin-bottom: 10px;"
            width="90%" align="center">
            <tr>
                <td colspan="2" bgcolor="#0968AC" align="left">
                    <span style="color: #FFFFFF">客户号码信息</span>
                </td>
            </tr>
            <tr>
                <td>
                    <font style="font-weight: bold">客户号码：</font>
                    <label id="lblCallee">
                    </label>
                </td>
                <td>
                    <font style="font-weight: bold">客户名称：</font>
                    <label id="lbluserName">
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <font style="font-weight: bold">号码类型：</font>
                    <label id="lblNumType">
                    </label>
                </td>
                <td>
                    <font style="font-weight: bold">交费类型：</font>
                    <label id="lblVoucherType">
                    </label>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <font style="font-weight: bold">账户金额：</font>
                    <label id="lblpayFees">
                    </label>
                </td>
            </tr>
        </table>
        <!--End 客户号码信息-->
        <!--Start 客户账单列表-->
        <table id="TabBills" cellspacing="0" cellpadding="2" style="border: 1px solid #000000;"
            width="90%" align="center">
            <tr style="border: 1px solid #000000;">
                <td colspan="2" bgcolor="#0968AC" align="left">
                    <span style="color: #FFFFFF; width: 25%;">客户账单列表&nbsp;&nbsp;</span> <span style="color: #ff0000;
                        width: 75%;" id="span">如果客户需要查看账单信息，请点击右边的账单查询按钮</span>
                </td>
                <td bgcolor="#0968AC" align="right">
                    <input style="color: #FFFFFF; background-color: Gray;" id="btn_show" value="账单查询"
                        type="button" onclick="javascript:startQueryBillData();" />
                    <input style="color: #FFFFFF; background-color: Gray; display: none;" id="print"
                        value="账单打印" type="button" onclick="javascript:PrintBill();" />
                </td>
            </tr>
            <tr style="border: 1px solid #000000;">
                <td colspan="3" bgcolor="#0968AC" align="left">
                    <span style="color: #FFFFFF">温馨提示：如果客户账单项过多（50项以上），只显示部分账单信息。如果账户金额与账单总计不符，请留意“违约金”项！</span>
                </td>
            </tr>
        </table>
        <!--End 客户账单列表-->
    </div>
    <!--Start 充值缴费结果-->
    <div id="step3" style="display: none;">
        <form id="fm_send" action="" method="post">
        <table cellspacing="3" cellpadding="3" width="97%" border="0" style="border: 1px solid #000000;"
            align="left">
            <tr style="border: 1px solid #000000;">
                <td bgcolor="#0968AC" align="left">
                    <b><span style="color: #FFFFFF">充值缴费结果</span></b>
                </td>
            </tr>
            <tr style="border: 1px solid #000000;">
                <td>
                    <input type="hidden" id="identityIdTxt" value="" />
                    <font color="red" size="3">您为客户号码：</font>
                    <label id="lblCustNum" style="font-size: 20; font-style: inherit; color: Red; text-decoration: underline;">
                    </label>
                    <font color="red" size="3">充值缴费成功！</font>
                </td>
            </tr>
            <tr style="border: 1px solid #000000;">
                <td>
                    充值缴费前客户号码余额：<b><label id="beforemoney"></label></b>
                </td>
            </tr>
            <tr style="border: 1px solid #000000;">
                <td>
                    充值缴费金额：<b><label id="lbltransmoney"></label></b>
                </td>
            </tr>
            <tr style="border: 1px solid #000000;">
                <td>
                    充值缴费后客户号码余额：<b><label id="lblcustblanace"></label></b>
                </td>
            </tr>
            <tr style="border: 1px solid #000000;">
                <td>
                    接收凭证的客户手机号码（可选）： <b>
                        <input class="normal" id="sendNum" name="sendNum" type="text" maxlength="11" value="" />
                        &nbsp;&nbsp;</b>
                    <input id="btn_SendSms" name="btn_SendSms" type="submit" value="发送短信" />
                    <input type="button" id="btnPrintForHand" onclick="javascript:HandPrint();" value="打印凭证" />
                    <input type="button" value="返  回" id="btn_GoBack" onclick="Javascript:GoToBack();" />
                </td>
            </tr>
            <tr style="border: 1px solid #000000;">
                <td>
                    <font color="red">(温馨提示：短信只能发送到中国电信天翼手机号码或小灵通号码)</font>
                </td>
            </tr>
        </table>
        </form>
    </div>
    <!--End 充值缴费结果-->
    <div id="AutoPrintDiv">
        <input id="PrintContent" type="hidden" value="" />
        <label id="lblPrintContent">
        </label>
        <!--0表示没有设置自动打印，1表示已经设置自动打印-->
        <input id="isAutoPrint" type="hidden" />
        <!--1表示串口打印，2表示系统打印-->
        <input id="AutoPrintType" type="hidden" />
        <!--打印格式：ck1,ck2,xt4 xt5 xt6-->
        <input id="printFormat" type="hidden" />
        <!--串口-->
        <input id="Ports" type="hidden" value="" />
        <!--波特率-->
        <input id="Rate" type="hidden" value="9600" />
        <!-- 客户号码 -->
        <input id="txtCustomNumber" type="hidden" value="" />
        <!-- 充值类型 000001 000004 -->
        <input id="txtVoucherType" type="hidden" value="" />
        <!-- 充值号码的付费类型,预付费(0 2 4 5)不打印账单(1 3) -->
        <input id="txtVCType" type="hidden" value="" />
    </div>
    <div id="TSObject">
    </div>

   
</body>
</html>