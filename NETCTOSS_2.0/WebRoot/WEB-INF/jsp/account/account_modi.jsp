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
        <script language="javascript" type="text/javascript">
            //保存成功的提示信息
            function showResult() {
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }

            //显示修改密码的信息项
            function showPwd(chkObj) {
                if (chkObj.checked)
                    document.getElementById("divPwds").style.display = "block";
                else
                    document.getElementById("divPwds").style.display = "none";
            }
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
        <!--Logo区域开始-->
        <div id="header">
            <img src="images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <%@include file="accounthead.jsp" %>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">  
            <!--保存成功或者失败的提示消息-->          
            <div id="save_result_info" class="save_fail">保存失败，旧密码错误！</div>
            <s:form action="accountotoupdate" method="post" theme="simple" cssClass="main_form" id="accountmodi">
                    <!--必填项-->
                    <div class="text_info clearfix"><span>账务账号ID：</span></div>
                    <div class="input_info">
                      <s:textfield name="account.id" cssClass="readonly" readonly="true" id="id"></s:textfield>
                    </div>
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.realName" id="realName"></s:textfield>
                        <span class="required">*</span>
                        <div class="validate_msg_long"></div>
                    </div>
                    <div class="text_info clearfix"><span>身份证：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.idcardNo" readonly="true" cssClass="readonly" id="idcard-c"></s:textfield>
                    </div>
                    <div class="text_info clearfix"><span>登录账号：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.loginName" readonly="true" cssClass="readonly"></s:textfield>                    
                        <div class="change_pwd">
                            <input id="chkModiPwd" type="checkbox" onclick="showPwd(this);" />
                            <label for="chkModiPwd">修改密码</label>
                        </div>
                    </div>
                    <!--修改密码部分-->
                    <div id="divPwds">
                        <div class="text_info clearfix"><span>旧密码：</span></div>
                        <div class="input_info">
                        		<s:password id="oldpwd-c"></s:password>
                            <span class="required">*</span>
                            <div class="validate_msg_long" id="oldpwderr"></div>
                        </div>
                        <div class="text_info clearfix"><span>新密码：</span></div>
                        <div class="input_info">
                            <s:password name="account.loginPasswd" id="newpwd1"></s:password>
                            <span class="required">*</span>
                            <div class="validate_msg_long" id="newpwd1-c"></div>
                        </div>
                        <div class="text_info clearfix"><span>重复新密码：</span></div>
                        <div class="input_info">
                            <s:password id="newpwd2"></s:password>
                            <span class="required">*</span>
                            <div class="validate_msg_long" id="newpwd2-c"></div>
                        </div>  
                    </div>                   
                    <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.telephone" cssClass="width200" id="telephone"></s:textfield>
                        <span class="required">*</span>
                        <div class="validate_msg_medium" id="telephone-c"></div>
                    </div>
                    <div class="text_info clearfix"><span>生日：</span></div>
                    <div class="input_info">
                    	   <s:textfield id="birthdate" readonly="true"></s:textfield>
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                    	   <s:textfield name="account.email" cssClass="width200" id="email"></s:textfield>
                        <div class="validate_msg_medium" id="email-c"></div>
                    </div> 
                    <div class="text_info clearfix"><span>职业：</span></div>
                    <div class="input_info">
                       <s:select name="account.occupation" list="#{'干部':'干部','学生':'学生','技术人员':'技术人员','其他':'其他' }"></s:select>                
                    </div>
                    <div class="text_info clearfix"><span>性别：</span></div>
                    <div class="input_info fee_type">
                        <s:radio name="account.gender" list="#{'0':'男','1':'女' }" ></s:radio>
                    </div> 
                    <div class="text_info clearfix"><span>通信地址：</span></div>
                    <div class="input_info">
                    	   <s:textfield name="account.mailaddress" cssClass="width350" id="mailaddress" ></s:textfield>
                        <div class="validate_msg_tiny" id="mailaddress-c"></div>
                    </div> 
                    <div class="text_info clearfix"><span>邮编：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.zipcode" id="zipcode"></s:textfield>
                        <div class="validate_msg_long" id="zipcode-c"></div>
                    </div> 
                    <div class="text_info clearfix"><span>QQ：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.qq" id="qq"></s:textfield>
                        <div class="validate_msg_long" id="qq-c"></div>
                    </div>               
                    <!--操作按钮-->
                    <div class="button_info clearfix">
                        <input type="submit" value="保存" class="btn_save" />
                        <input type="button" value="取消" class="btn_save" onclick="window.location='accountlist'"/>
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
