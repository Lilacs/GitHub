<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
    </head>
    <body>
        
        <%@include file="servicehead.jsp" %>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <form action="" method="" class="main_form">
                <!--必填项-->
                <div class="text_info clearfix"><span>业务账号ID：</span></div>
                <div class="input_info"><s:textfield name="service.id" readonly="true"></s:textfield></div>
                <div class="text_info clearfix"><span>账务账号ID：</span></div>
                <div class="input_info"><s:textfield name="service.account.id" readonly="true"></s:textfield></div>
                <div class="text_info clearfix"><span>客户姓名：</span></div>
                <div class="input_info"><s:textfield name="service.account.realName" readonly="true" cssClass="readonly"></s:textfield></div>
                <div class="text_info clearfix"><span>身份证号码：</span></div>
                <div class="input_info"><s:textfield name="service.account.idcardNo" readonly="true" cssClass="readonly"></s:textfield></div>
                <div class="text_info clearfix"><span>服务器 IP：</span></div>
                <div class="input_info"><s:textfield name="service.host.id" readonly="true" cssClass="readonly"></s:textfield></div>
                <div class="text_info clearfix"><span>OS 账号：</span></div>
                <div class="input_info"><s:textfield name="service.osUsername" readonly="true" cssClass="readonly"></s:textfield></div>
                <div class="text_info clearfix"><span>状态：</span></div>
                <div class="input_info">
                		<s:select name="service.status"  list="#{'0':'开通','1':'暂停','2':'删除' }" disabled="true"></s:select>    
                </div>
                <div class="text_info clearfix"><span>开通时间：</span></div>
                <div class="input_info"><input type="text" readonly class="readonly" value="<s:date name="service.createDate" format="yyyy-MM-dd"/>"/></div>
                <div class="text_info clearfix"><span>资费 ID：</span></div>
                <div class="input_info"><s:textfield name="service.cost.id" readonly="true" cssClass="readonly"></s:textfield></div>
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info"><s:textfield name="service.cost.name" cssClass="width200 readonly" readonly="true"></s:textfield></div>
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                		<s:textarea name="service.cost.descr" cssClass="width300 height70 readonly" readonly="true"></s:textarea>
                </div>  
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="button" value="返回" class="btn_save" onclick="location.href='servicelist';" />
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)加拿大达内IT培训集团公司 </span>
        </div>
    </body>
</html>
