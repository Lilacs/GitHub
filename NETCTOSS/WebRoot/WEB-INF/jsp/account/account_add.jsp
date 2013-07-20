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
            	var flag = false;
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
                //验证身份证
                var b1 = $("#idcard-c").checking5($("#idcard"),"身份证格式错误,请重新填写!");
                //生成生日信息
                if(b1 = true){
     				var val = $("#idcard").val();
     				 if (15 == val.length) { //15位身份证号码
       					 birthdayValue = val.charAt(6) + val.charAt(7);
    					    if (parseInt(birthdayValue) < 10) {
   					         birthdayValue = '20' + birthdayValue;
   				        }else {
            					birthdayValue = '19' + birthdayValue;
      				    }
        					birthdayValue = birthdayValue + '-' + val.charAt(8) + val.charAt(9) + '-' + val.charAt(10) + val.charAt(11);
     				 	var f1 = RQcheck(birthdayValue);
     				 	if(f1){
     				 		$("#birthdate").attr("value", birthdayValue);
     				 	}else{
     				 		b1 = false;
     				 		$("#birthdate-c").text("请检查填写身份证信息是否有误");
     				 	}
     				 	
     				 }
     				  if (18 == val.length) { //18位身份证号码
       					 birthdayValue = val.charAt(6) + val.charAt(7) + val.charAt(8) + val.charAt(9) 
       					 				+ '-' + val.charAt(10) + val.charAt(11)
       					 			    + '-' + val.charAt(12) + val.charAt(13);
       					 var f1 = RQcheck(birthdayValue);
     				 	 if(f1){
     				 		$("#birthdate").attr("value", birthdayValue);
     				 	 }else{
     				 		b1 = false;
     				 		$("#birthdate-c").text("请检查填写身份证信息是否有误");
     				 	 }
       				  }
     				}	 
                //验证email
                var b2 = true;
                var email = $("#email").val();
                if(email.length > 0){
                	var b2 = $("#email-c").checking6($("#email"),"邮箱格式错误,正确格式****@126.com");
                }
                //验证电话
                var b3 = $("#telephone-c").checking4($("#telephone"),"非法格式,请输入正确的手机或者座机号码");
                //验证两次密码输入
                var b4 = $(this).checking9($("#pwd1"),$("#pwd2"),$("#pwd2-c"),"两次密码不一致,请重新填写");
                //验证登陆账号
                var b5 = $("#login_name-c").checking10($("#login_name"),"错误!字母开头,5-16个字母、数字和下划线的组合");
                //验证qq
                var b6 = true;
                var qq = $("#qq").val();
                if(qq.length > 0){
                	var b6 = $("#qq-c").checking8($("#qq"),"非法格式");
                }
                //验证密码为必须填写
                var b7 = true;
                var pwd1 = $("#pwd1").val();
      			if(pwd1.length == 0 || pwd1 == ""){
      				$("#pwd1-c").text("***密码为必须填写");
      				b7 = false;
      			};
      			//验证推荐人身份证号
     			var b8 = false;
     			var recommender_id = $("#recommender_id").val();
     			if(recommender_id.length  > 0 || recommender_id == "" || recommender_id == null){
     				b8 = true;
     			};
     			//验证用户姓名
     			var b9 = $("#real_name-c").checking11($("#real_name"),"非法输入");
     			
                if(b1 && b2 && b3 && b4 && b5 && b6 && b7 && b8 && b9){
               	 	$("#accountappend").submit();
               	 	flag = true;
               	 	return flag;
                }else{
              	  	alert("请检查输入!");
              	  	return flag;
                };
            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }

            //显示选填的信息项
            function showOptionalInfo(imgObj) {
                var div = document.getElementById("optionalInfo");
                if (div.className == "hide") {
                    div.className = "show";
                    imgObj.src = "images/hide.png";
                }
                else {
                    div.className = "hide";
                    imgObj.src = "images/show.png";
                }
            }
            //验证身份证是否合法
            function RQcheck(RQ) {
            		var date = RQ;
            		var result = date.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
  	 	        if (result == null)
          	      return false;
            		var d = new Date(result[1], result[3] - 1, result[4]);
            		return (d.getFullYear() == result[1] && (d.getMonth() + 1) == result[3] && d.getDate() == result[4]);

       	    }
            $(function(){
            		$("#recommender_cardno").bind("blur",function(){
            			var idCard = $("#recommender_cardno").val();
           	 		if(idCard.length >0){
           	 			$.post("makerec_toExists",{"idCard":idCard},function(flag){
            					if(flag){
            						$.post("makerec_makeIdForAdd",{"idCard":idCard},function(id){
            							$("#recommender_id").attr("value",id);
            							$("#rec_cardno").text("推荐人可用");
            						});
            					}else{
            						$("#rec_cardno").text("没有此推荐人的任何信息,如没有推荐人请置空");
            					}
           				 });
           			}
           		});
           		$("#idcard").bind("blur",function(){
           			var val = $("#idcard").val();
     				 if (15 == val.length) { //15位身份证号码
       					 birthdayValue = val.charAt(6) + val.charAt(7);
    					    if (parseInt(birthdayValue) < 10) {
   					         birthdayValue = '20' + birthdayValue;
   				        }else {
            					birthdayValue = '19' + birthdayValue;
      				    }
        					birthdayValue = birthdayValue + '-' + val.charAt(8) + val.charAt(9) + '-' + val.charAt(10) + val.charAt(11);
     				 	var f1 = RQcheck(birthdayValue);
     				 	if(f1){
     				 		$("#birthdate").attr("value", birthdayValue);
     				 	}else{
     				 		b1 = false;
     				 		$("#birthdate-c").text("请检查填写身份证信息是否有误");
     				 	}
     				 }
     				  if (18 == val.length) { //18位身份证号码
       					 birthdayValue = val.charAt(6) + val.charAt(7) + val.charAt(8) + val.charAt(9) 
       					 				+ '-' + val.charAt(10) + val.charAt(11)
       					 			    + '-' + val.charAt(12) + val.charAt(13);
       					 var f1 = RQcheck(birthdayValue);
     				 	 if(f1){
     				 		$("#birthdate").attr("value", birthdayValue);
     				     }else{
     				 		b1 = false;
     				 		$("#birthdate-c").text("请检查填写身份证信息是否有误");
     				 	 }
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
            <div id="save_result_info" class="save_fail">保存失败，该身份证已经开通过账务账号！</div>
            <s:form action="accountappend" method="post" cssClass="main_form" theme="simple">
                <!--必填项-->
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info">
                    <s:textfield name="account.real_name" id="real_name"></s:textfield>
                    <span class="required" id="real_name-c">*</span>
                    <div class="validate_msg_long"></div>
                </div>
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                    <s:textfield name="account.idcard_no" id="idcard"></s:textfield>
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="idcard-c"></div>
                </div>
                <div class="text_info clearfix"><span>登录账号：</span></div>
                <div class="input_info">
                    <s:textfield name="account.login_name" id="login_name"></s:textfield>
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="login_name-c"></div>
                </div>
                <div class="text_info clearfix"><span>密码：</span></div>
                <div class="input_info">
                    <s:password id="pwd1"></s:password>
                    <span class="required" id="pwd1-c">*</span>
                    <div class="validate_msg_long"></div>
                </div>
                <div class="text_info clearfix"><span>重复密码：</span></div>
                <div class="input_info">
                    <s:password id="pwd2" name="account.login_passwd"></s:password>
                    <span class="required" id="pwd2-c">*</span>
                    <div class="validate_msg_long"></div>
                </div>     
                <div class="text_info clearfix"><span>性别：</span></div>
                    <div class="input_info fee_type">
                    	<s:radio name="account.gender" list="#{'0':'男','1':'女'}"></s:radio>
                </div> 
                <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info">
                    <s:textfield name="account.telephone" cssClass="width200" id="telephone"></s:textfield>
                    <span class="required">*</span>
                    <div class="validate_msg_medium" id="telephone-c"></div>
                </div>                
                <!--可选项-->
                <div class="text_info clearfix"><span>可选项：</span></div>
                <div class="input_info">
                    <img src="images/show.png" alt="展开" onclick="showOptionalInfo(this);" />
                </div>
                <div id="optionalInfo" class="hide">
                    <div class="text_info clearfix"><span>推荐人身份证号码：</span></div>
                    <div class="input_info">
                        <input type="text" id="recommender_cardno"/>
                        <s:hidden name="account.recommender_id" id="recommender_id"></s:hidden>
                        <div class="validate_msg_long" id="rec_cardno">如没有请置空,一经填写无法更改!</div>
                    </div>
                    <div class="text_info clearfix"><span>生日：</span></div>
                    <div class="input_info">
                    	<s:textfield name="birthdate" readonly="true" cssClass="readonly" id="birthdate"></s:textfield>
                    <div class="validate_msg_tiny" id="birthdate-c"></div>
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.email" id="email"></s:textfield>
                        <div class="validate_msg_tiny" id="email-c"></div>
                    </div> 
                    <div class="text_info clearfix"><span>职业：</span></div>
                    <div class="input_info">
                    	<s:select name="account.occupation" list="#{'干部':'干部','学生':'学生','技术人员':'技术人员','其他':'其他'}"></s:select>                     
                    </div>
                    
                    <div class="text_info clearfix"><span>通信地址：</span></div>
                    <div class="input_info">
                         <s:textfield name="account.mailaddress" cssClass="width350"></s:textfield>
                        <div class="validate_msg_tiny"></div>
                    </div> 
                    <div class="text_info clearfix"><span>邮编：</span></div>
                    <div class="input_info">
                         <s:textfield name="account.zipcode"></s:textfield>
                        <div class="validate_msg_long"></div>
                    </div> 
                    <div class="text_info clearfix"><span>QQ：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.qq" id="qq"></s:textfield>
                        <div class="validate_msg_long" id="qq-c"></div>
                    </div>                
                </div>
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="showResult();" />
                    <input type="button" value="取消" class="btn_save" onclick="window.location='accall';"/>
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
