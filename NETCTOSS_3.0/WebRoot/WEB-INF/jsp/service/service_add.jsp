<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script language="javascript" type="text/javascript">
         
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }
			$(function(){
				$("#find").bind("click",function(){
					var idCard = $("#checkCard").val();
               		$.post("makesergetAccId",{"idCard": idCard },function(data){
               			if(data.flag){
               				$("#accountid-c").attr("value",data.id);
               				$("#accountname-c").attr("value",data.loginName);
               			}else{
               				$("#idcarderr").text("无此身份证号或者此账号已删除，请重新录入。");
               			}         
                		});
				});
			});
            //自动查询账务账号
            function searchAccounts(txtObj) {
                //document.getElementById("a1").innerHTML = txtObj.value;
                
            }
        </script>
    </head>
    <body>
        
        <%@include file="servicehead.jsp" %>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <!--保存操作的提示信息-->
            <div id="save_result_info" class="save_fail">保存失败！192.168.0.23服务器上已经开通过 OS 账号 “mary”。</div>
            <s:form action="serviceowillLoggertoadd" mothed="post" theme="simple" id="save">
                <!--内容项-->
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                		<s:textfield cssClass="width180" id="checkCard"></s:textfield>
                    <input type="button" value="查询账务账号" class="btn_search_large" id="find"/>
                    <span class="required">*</span>
                    <div class="validate_msg_short" id="idcarderr"></div>
                </div>
                <div class="text_info clearfix"><span>账务账号：</span></div>
                <div class="input_info">
                    <input type="text"  id="accountname-c" readonly="true"/>
                </div>
                <div class="text_info clearfix"><span>账务id：</span></div>
                <div class="input_info">
                		<s:textfield name="service.account.id" id="accountid-c" readonly="ture"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info">
                		<s:select  name="service.cost.id" list="cost" listKey="id" listValue="name" ></s:select>         
                </div> 
                <div class="text_info clearfix"><span>服务器 IP：</span></div>
                <div class="input_info">
                    <s:select  name="service.host.id" list="host" listKey="id" listValue="name" ></s:select> 
                    <span class="required">*</span>
                </div>                   
                <div class="text_info clearfix"><span>登录 OS 账号：</span></div>
                <div class="input_info">
                		<s:textfield name="service.osUsername" maxLength="8" id="osname"></s:textfield>
                    <span class="required">*</span>
                    <div class="validate_msg_long">最低5位,8长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>密码：</span></div>
                <div class="input_info">
                		<s:password id="pwd1" name="service.loginPasswd"></s:password>
                    <span class="required">*</span>
                    <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复密码：</span></div>
                <div class="input_info">
                		<s:password id="pwd2"></s:password>
                    <span class="required">*</span>
                    <div class="validate_msg_long required" id="pwderr" ></div>
                </div>     
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="submit" value="保存" class="btn_save"  />
                    <input type="button" value="取消" class="btn_save" onclick="window.location='servicelist'"/>
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
