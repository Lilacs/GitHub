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
       				  
       				  //验证表单信息
       				  $("#jumptosave").bind("click",function(){
       				  	//验证旧密码是否相同
						var b6 = true;
						var oldpwd = $("#oldpwd-c").val();
						var id = $("#id-c").val();
						if(oldpwd.length > 0){
							b6 = false;
							$.post("makerec_toEquals",{"oldpwd":oldpwd,"id":id},function(flag){
								if(flag){
									b6 = true;
								}else{
									 showResult();
								}
							});
						}
       				  	//验证姓名
       				  	var b1 = $("#nameerr").checking11($("#name-c"),"请检查,2-4位的中文姓名")
						//验证电话
						var b2 = $("#telephoneerr").checking4($("#telephone-c"),"格式错误,请输入正确的手机或者座机号码");
						//验证邮箱
						var b3 = false;
						var email = $("#email-c").val();
						if(email.length == 0){
							b3 = true;
						}else{
							b3 = $("#emailerr").checking6($("#email-c"),"格式错误,请输入正确的邮箱");
						}
						//验证邮编
						var b4 = false;
						var zipcode = $("#zipcode-c").val();
						if(zipcode.length == 0){
							b4 = true;
						}else{
							b4 = $("#zipcodeerr").checking7($("#zipcode-c"),"请检查,6位邮编")
						}
						//验证qq号
						var b5 = false;
						var qq = $("#qq-c").val();
						if(qq.length == 0){
							b5 = true;
						}else{
							b5 = $("#qqerr").checking8($("#qq-c"),"请检查您的QQ号码是否正确!")
						}
						
						
						//验证新密码为必须输入
						var b7 = false;
						var newpwd = $("#newpwd1-c").val();
						if(newpwd.length == 0 ){
							b7 = true;
						}else{
							if(newpwd.length < 6 )
								$("#newpwd1err").text("长度过短,最低6位");
							else
								b7 = true;
						}
						//验证两个密码是否一致
						var b8 = $("#newpwd2err").checking9($("#newpwd1-c"),$("#newpwd2-c"),$("#newpwd2err"),"两次密码不一致,请检查!");
						
						
						if(b1 && b2 && b3 && b4 && b5 && b6 && b7 && b8){
							$("#accountmodi").submit();
						}else{
							alert("请检查输入");
						}
						
       				  });
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
        <div id="navi">
            <ul id="menu">
                <li><a href="index" class="index_off"></a></li>
                <li><a href="role_list" class="role_off"></a></li>
                <li><a href="adminlist" class="admin_off"></a></li>
                <li><a href="fee_list" class="fee_off"></a></li>
                <li><a href="accall" class="account_on"></a></li>
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
            <!--保存成功或者失败的提示消息-->          
            <div id="save_result_info" class="save_fail">保存失败，旧密码错误！</div>
            <s:form action="accountmodi" method="post" theme="simple" cssClass="main_form" id="accountmodi">
                    <!--必填项-->
                    <div class="text_info clearfix"><span>账务账号ID：</span></div>
                    <div class="input_info">
                      <s:textfield name="account.id" cssClass="readonly" readonly="true" id="id-c"></s:textfield>
                    </div>
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.real_name" id="name-c"></s:textfield>
                        <span class="required">*</span>
                        <div class="validate_msg_long" id="nameerr"></div>
                    </div>
                    <div class="text_info clearfix"><span>身份证：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.idcard_no" readonly="true" cssClass="readonly" id="idcard-c"></s:textfield>
                    </div>
                    <div class="text_info clearfix"><span>登录账号：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.login_name" readonly="true" cssClass="readonly"></s:textfield>                    
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
                            <s:password name="account.login_passwd" id="newpwd1-c"></s:password>
                            <span class="required">*</span>
                            <div class="validate_msg_long" id="newpwd1err"></div>
                        </div>
                        <div class="text_info clearfix"><span>重复新密码：</span></div>
                        <div class="input_info">
                            <s:password id="newpwd2-c"></s:password>
                            <span class="required">*</span>
                            <div class="validate_msg_long" id="newpwd2err"></div>
                        </div>  
                    </div>                   
                    <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.telephone" cssClass="width200" id="telephone-c"></s:textfield>
                        <span class="required">*</span>
                        <div class="validate_msg_medium" id="telephoneerr"></div>
                    </div>
                    <div class="text_info clearfix"><span>生日：</span></div>
                    <div class="input_info">
                    	   <s:textfield id="birthdate" readonly="true"></s:textfield>
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                    	   <s:textfield name="account.email" cssClass="width200" id="email-c"></s:textfield>
                        <div class="validate_msg_medium" id="emailerr"></div>
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
                    	   <s:textfield name="account.mailaddress" cssClass="width350" id="mailaddress-c" ></s:textfield>
                        <div class="validate_msg_tiny" id="mailaddresserr"></div>
                    </div> 
                    <div class="text_info clearfix"><span>邮编：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.zipcode" id="zipcode-c"></s:textfield>
                        <div class="validate_msg_long" id="zipcodeerr"></div>
                    </div> 
                    <div class="text_info clearfix"><span>QQ：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.qq" id="qq-c"></s:textfield>
                        <div class="validate_msg_long" id="qqerr"></div>
                    </div>               
                    <!--操作按钮-->
                    <div class="button_info clearfix">
                        <input type="button" value="保存" class="btn_save" id="jumptosave" />
                        <input type="button" value="取消" class="btn_save" onclick="window.location='accall'"/>
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
