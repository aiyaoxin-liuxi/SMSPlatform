<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1" />
	    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <link rel="stylesheet" type="text/css" href="${basePath}/jquery-easyui-1.5.2/themes/default/easyui.css">
	    <link rel="stylesheet" type="text/css" href="${basePath}/jquery-easyui-1.5.2/themes/icon.css">
	    <link rel="stylesheet" type="text/css" href="${basePath}/jquery-easyui-1.5.2/demo.css">
	    <script type="text/javascript" src="${basePath}/js/jquery-1.8.0.min.js"></script>
	    <script type="text/JavaScript" src="${basePath}/js/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript" src="${basePath}/js/baseJs.js"></script>
	    <script type="text/javascript" src="${basePath}/jquery-easyui-1.5.2/jquery.min.js"></script>
	    <script type="text/javascript" src="${basePath}/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
	</head>

	<body bgcolor='#FFFFFF' text='#000000' leftmargin='0' topmargin='0' marginwidth='0' marginheight='0'>
		<form action="dhb/walletPay" method="post" id="payForm">
			<input type="hidden" name="tranNo" id="tranNo" />
			<input type="hidden" name="merchId" id="merchId" />
			<input type="hidden" name="acctId" id="acctId" />
			<input type="hidden" name="money" id="money" />
			<input type="hidden" name="remark" id="remark" />
			<input type="hidden" name="synchUrl" id="synchUrl" />
			<input type="hidden" name="ticket" id="ticket" />
			<input type="hidden" name="sign" id="sign" />
		</form>
	</body>
</html>
