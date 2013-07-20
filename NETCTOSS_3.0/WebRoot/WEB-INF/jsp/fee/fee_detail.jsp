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
        <%@include file="feehead.jsp" %>
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
                    <s:if test="cost.status == 0"><option>开通</option></s:if>
                    <s:if test="cost.status == 1"><option>暂停</option></s:if>
                    <s:if test="cost.status == 2"><option>删除</option></s:if>
                    </select>
                   <%--  <s:select name="cost.status" list="#{'0':'暂停','1':'开通' }" cssClass="readonly"></s:select>   --%>                     
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info fee_type">
                	
                    <s:radio name="cost.costtype" list="#{'1':'包月','2':'套餐','3':'计时' }" disabled="true" cssClass="readonly"></s:radio>
                </div>
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                    <s:textfield name="cost.baseDuration" cssClass="readonly" readonly="true"></s:textfield>
                    <span>小时</span>
                </div>
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                    <s:textfield name="cost.baseCost" cssClass="readonly" readonly="true"></s:textfield>
                    <span>元</span>
                </div>
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                    <s:textfield name="cost.unitCost" cssClass="readonly" readonly="true"></s:textfield>
                    <span>元/小时</span>
                </div>
                <div class="text_info clearfix"><span>创建时间：</span></div>
                <div class="input_info"><input type="text"  class="readonly" readonly value="<s:date name="cost.creattime" format="yyyy/MM/dd"/>" /></div>      
                <div class="text_info clearfix"><span>启动时间：</span></div>
                <div class="input_info"><input type="text"  class="readonly" readonly value="<s:if test="cost.startime != null"><s:date name="cost.startime" format="yyyy-MM-dd"/></s:if>" /></div>      
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <s:textarea name="cost.descr" cssClass="width300 height70 readonly" readonly="true" ></s:textarea>
                </div>                    
                <div class="button_info clearfix">
                    <input type="button" value="返回" class="btn_save" onclick="location.href='feelist';" />
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
