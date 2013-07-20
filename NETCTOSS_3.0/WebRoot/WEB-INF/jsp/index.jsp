<%@page pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" /> 
        <style type="text/css">
          .a{	
			width: 200px;
			height: 150px;
			position: absolute;top:70%;left:80%;
			font-size: 40px;
			color: blue;
        	}
        </style>
    </head>
    <body class="index">
    	<marquee class="a">欢迎<s:property value="#session.user.adminCode"/>登陆</marquee>
        <!--导航区域开始-->
        <div id="index_navi">
            <ul id="menu">
                <li><a href="index" class="index_on"></a></li>
                <li><a href="rolelist" class="role_off"></a></li>
                <li><a href="adminlist" class="admin_off"></a></li>
                <li><a href="feelist" class="fee_off"></a></li>
                <li><a href="accountlist" class="account_off"></a></li>
                <li><a href="servicelist" class="service_off"></a></li>
                <li><a href="#" class="bill_off"></a></li>
                <li><a href="#" class="report_off"></a></li>
                <li><a href="usermodiview" class="information_off"></a></li>
                <li><a href="usermodiPWD" class="password_off"></a></li>
            </ul>
        </div>
    </body>
</html>
