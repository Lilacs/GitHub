<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
    		<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="js/checking-1.0.js"></script>
    		<script type="text/javascript">
    			$(function(){
            		var val = $("#idcard-c").val();
     				 if (15 == val.length) { //15位身份证号码
       					birthdayValue = val.charAt(6) + val.charAt(7);
    					    if (parseInt(birthdayValue) < 10) {
   					         birthdayValue = '20' + birthdayValue;
   				        }else {
            					birthdayValue = '19' + birthdayValue;
      				    }
        					birthdayValue = birthdayValue + '-' + val.charAt(8) + val.charAt(9) + '-' + val.charAt(10) + val.charAt(11);
     				 	$("#birthdate").attr("value", birthdayValue);
     				 }
     				 if (18 == val.length) { //18位身份证号码	 
       					 birthdayValue = val.charAt(6) + val.charAt(7) + val.charAt(8) + val.charAt(9) 
       					 				+ '-' + val.charAt(10) + val.charAt(11)
       					 			    + '-' + val.charAt(12) + val.charAt(13);
     					  $("#birthdate").attr("value", birthdayValue);
     				    
       				  }
           		});
    		</script>
    </head>
    <body>
       
        <%@include file="accounthead.jsp" %>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <form action="" method="" class="main_form">
                <!--必填项-->
                <div class="text_info clearfix"><span>账务账号ID：</span></div>
                <div class="input_info"><s:textfield name="account.id" readonly="true"></s:textfield></div>
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info"><s:textfield name="account.realName" readonly="true" cssClass="readonly"></s:textfield></div>
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                    <s:textfield name="account.idcardNo"  readonly="true" id="idcard-c"></s:textfield></div>
                <div class="text_info clearfix"><span>登录账号：</span></div>
                <div class="input_info">
                    <s:textfield name="account.loginName"  readonly="true"></s:textfield>
                </div>                   
                <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info">
                		<s:textfield name="account.telephone" readonly="true" cssClass="width200 readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>推荐人账务账号ID：</span></div>
                <div class="input_info"><s:textfield name="account.recommenderId"></s:textfield></div>
                <div class="text_info clearfix"><span>状态：</span></div>
                <div class="input_info">
                    <s:select name="account.status" list="#{'0':'开通','1':'暂停','3':'删除' }" disabled="true"></s:select>                 
                </div>                    
                <div class="text_info clearfix"><span>开通/暂停/删除时间：</span></div>
                <div class="input_info"><input type="text" value="<s:date name="account.createDate" format="yyyy-MM-dd"/>" readonly="readonly"></div>
                <div class="text_info clearfix"><span>上次登录时间：</span></div>
                <div class="input_info"><input type="text" value="<s:date name="account.lastLoginTime" format="yyyy-MM-dd"/>" readonly="readonly"></div>
                <div class="text_info clearfix"><span>上次登录IP：</span></div>
                <div class="input_info"><input type="text" readonly class="readonly" value="${account.lastLoginIp }" /></div>
                <!--可选项数据-->
                <div class="text_info clearfix"><span>生日：</span></div>
                <div class="input_info">
                    <input type="text" readonly class="readonly" id="birthdate"/>
                </div>
                <div class="text_info clearfix"><span>Email：</span></div>
                <div class="input_info">
                		<s:textfield name="account.email" readonly="true" cssClass="width350 readonly"></s:textfield>
                </div> 
                <div class="text_info clearfix"><span>职业：</span></div>
                <div class="input_info">
                    <s:select name="account.occupation" list="#{'干部':'干部','学生':'学生','技术人员':'技术人员','其他':'其他'}" disabled="true"></s:select>                      
                </div>
                <div class="text_info clearfix"><span>性别：</span></div>
                <div class="input_info fee_type">
                    <s:radio name="account.gender" list="#{'0':'男','1':'女' }" disabled="true"></s:radio>
                </div> 
                <div class="text_info clearfix"><span>通信地址：</span></div>
                <div class="input_info"><s:textfield name="account.mailaddress" cssClass="width350 readonly" readonly="true"></s:textfield></div> 
                <div class="text_info clearfix"><span>邮编：</span></div>
                <div class="input_info"><s:textfield name="account.zipcode" cssClass="readonly" readonly="true"></s:textfield></div> 
                <div class="text_info clearfix"><span>QQ：</span></div>
                <div class="input_info"><s:textfield name="account.qq" cssClass="readonly" readonly="true"></s:textfield></div>                
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="button" value="返回" class="btn_save" onclick="location.href='accountlist';" />
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
