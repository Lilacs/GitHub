<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="js/checking-1.0.js"></script>
        <script language="javascript" type="text/javascript">

        </script>
    </head>
    <body>
        <%@include file="feehead.jsp" %>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <div id="save_result_info" class="save_fail">保存失败，资费名称重复！</div>
            <s:form action="feeowillLoggertoadd" method="post" theme="simple" cssClass="mian_form">
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info">
                		<s:textfield name="cost.name" cssClass="width300"></s:textfield>
                    <span class="required">*</span>
                    <div class="validate_msg_short"></div>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                	
                <div class="input_info fee_type">
                		<s:radio name="cost.costtype" list="#{'1':'包月','2':'套餐','3':'计时' }"></s:radio>
                </div>
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                		<s:textfield name="cost.baseDuration"></s:textfield>
                    <span class="info">小时</span>
                    <span class="required">*</span>
                    <div id="base_duration_result"></div>
                </div>
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                		<s:textfield name="cost.baseCost"></s:textfield>
                    <span class="info">元</span>
                    <span class="required">*</span>
                    <div id="base_cost_result"></div>
                </div>
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                		<s:textfield name="cost.unitCost"></s:textfield>
                    <span class="info">元/小时</span>
                    <span class="required">*</span>
                    <div  id="unit_cost_result"></div>
                </div>
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <s:textarea name="cost.descr" cssClass="width300 height70" maxlength="100"></s:textarea>
                    <div  id="descr_result"></div>
                </div>                
                <div class="button_info clearfix">
                	<input type="submit" value="保存" class="btn_save"  id="save_checking"/>
                    <input type="button" value="取消" class="btn_save" onclick="window.location='fee_list'"/>
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
