<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />       
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="images/logo.png" alt="logo" class="left"/>
            <a href="logout">[退出]</a>          
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
                <li><a href="index" class="index_off"></a></li>
                <li><a href="role_list.html" class="role_off"></a></li>
                <li><a href="adminall" class="admin_off"></a></li>
                <li><a href="fee_list" class="fee_on"></a></li>
                <li><a href="accall" class="account_off"></a></li>
                <li><a href="serall" class="service_off"></a></li>
                <li><a href="#" class="bill_off"></a></li>
                <li><a href="#" class="report_off"></a></li>
                <li><a href="userinfotoview" class="information_off"></a></li>
                <li><a href="usermodipwd" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
           	<s:form theme="simple">
                <div class="text_info clearfix"><span>资费ID：</span></div>
                <div class="input_info"><s:textfield name="cost.id" cssClass="readonly" readonly="true"></s:textfield></div>
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info"><s:textfield name="cost.name" cssClass="readonly" readonly="true"></s:textfield></div>
                <div class="text_info clearfix"><span>资费状态：</span></div>
                <div class="input_info">
                    <select class="readonly" disabled>
                    <s:if test="cost.status == 0"><option>暂停</option></s:if>
                    <s:elseif test="cost.status == 1"><option>开通</option></s:elseif>
                    <s:else><option>无状态</option></s:else>
                    </select>
                   <%--  <s:select name="cost.status" list="#{'0':'暂停','1':'开通' }" cssClass="readonly"></s:select>   --%>                     
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info fee_type">
                	
                    <input type="radio" name="radFeeType" <s:if test="cost.costtype == 1">checked="checked"</s:if> id="monthly" disabled="disabled" />
                    <label for="monthly">包月</label>
                    <input type="radio" name="radFeeType" <s:if test="cost.costtype == 2">checked="checked"</s:if> id="package" disabled="disabled" />
                    <label for="package">套餐</label>
                    <input type="radio" name="radFeeType" <s:if test="cost.costtype == 3">checked="checked"</s:if> id="timeBased" disabled="disabled" />
                    <label for="timeBased">计时</label>
                </div>
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                    <s:textfield name="cost.base_duration" cssClass="readonly" readonly="true"></s:textfield>
                    <span>小时</span>
                </div>
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                    <s:textfield name="cost.base_cost" cssClass="readonly" readonly="true"></s:textfield>
                    <span>元</span>
                </div>
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                    <s:textfield name="cost.unit_cost" cssClass="readonly" readonly="true"></s:textfield>
                    <span>元/小时</span>
                </div>
                <div class="text_info clearfix"><span>创建时间：</span></div>
                <div class="input_info"><input type="text"  class="readonly" readonly value="<s:date name="cost.creatime" format="yyyy/MM/dd"/>" /></div>      
                <div class="text_info clearfix"><span>启动时间：</span></div>
                <div class="input_info"><input type="text"  class="readonly" readonly value="<s:if test="cost.startime != null"><s:date name="cost.startime" format="yyyy-MM-dd"/></s:if>" /></div>      
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <s:textarea name="cost.descr" cssClass="width300 height70 readonly" readonly="true" ></s:textarea>
                </div>                    
                <div class="button_info clearfix">
                    <input type="button" value="返回" class="btn_save" onclick="location.href='fee_list';" />
                </div>
            </s:form> 
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)加拿大达内IT培训集团公司 </span>
        </div>
    </body>
</html>
