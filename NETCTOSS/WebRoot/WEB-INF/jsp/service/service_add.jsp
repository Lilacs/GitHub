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
        <script type="text/javascript" src="js/checking-1.0.js"></script>
        <script language="javascript" type="text/javascript">
            //保存成功的提示信息
            function showResult() {
               
                //验证密码输入是否一致
                var b1 = $("#pwderr").checking9($("#pwd1"),$("#pwd2"), $("#pwderr"),"密码输入不一致,请确认");
                //验证有没有财务id
                var b2 = false;
                var accid = $("#accountid-c").val();
                if(accid.length > 0){
                		b2 = true;
                }
                //验证账户信息
                b3 = false;
                var osname = $("#osname").val();
                if(osname.length > 5){
                		b3 = true;
                }
                if(b1 && b2 && b3) {
                		$("#save").submit();
                }else{
                		alert("请检查输入");
                		showResultDiv(true);
                		window.setTimeout("showResultDiv(false);", 3000);
                }
            }
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
    	<s:debug></s:debug>
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
                <li><a href="accall" class="account_off"></a></li>
                <li><a href="serall" class="service_on"></a></li>
                <li><a href="#" class="bill_off"></a></li>
                <li><a href="#" class="report_off"></a></li>
                <li><a href="userinfotoview" class="information_off"></a></li>
                <li><a href="usermodipwd" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <!--保存操作的提示信息-->
            <div id="save_result_info" class="save_fail">保存失败！192.168.0.23服务器上已经开通过 OS 账号 “mary”。</div>
            <s:form action="servicecreate" mothed="post" theme="simple" id="save">
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
                		<s:textfield name="service.account_id" id="accountid-c" readonly="ture"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info">
                		<s:select  name="service.cost_id" list="cost" listKey="id" listValue="name" ></s:select>         
                </div> 
                <div class="text_info clearfix"><span>服务器 IP：</span></div>
                <div class="input_info">
                    <s:select  name="service.unix_host" list="host" listKey="id" listValue="name" ></s:select> 
                    <span class="required">*</span>
                </div>                   
                <div class="text_info clearfix"><span>登录 OS 账号：</span></div>
                <div class="input_info">
                		<s:textfield name="service.os_username" maxLength="8" id="osname"></s:textfield>
                    <span class="required">*</span>
                    <div class="validate_msg_long">最低5位,8长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>密码：</span></div>
                <div class="input_info">
                		<s:password id="pwd1" name="service.login_passwd"></s:password>
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
                    <input type="button" value="保存" class="btn_save" onclick="showResult();" />
                    <input type="button" value="取消" class="btn_save" onclick="window.location='serall'"/>
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
