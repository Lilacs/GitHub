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
           
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }
            
           	$(function(){
           		$("#ajaxsave").bind("click", function(){
           			var id = $("#id").val();
           			var name = $("#name").val();
           			var telephone = $("#telephone").val();
           			var email = $("#email").val();
           			$.post("userupdate",{"id":id,"name":name,"telephone":telephone,"email":email},function(flag){
           				if(flag)
           					alert("修改成功!");
           				else
           					alert("修改失败,请重新确认!");
           			});
           		});
           	});
           	
        </script>
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
                <li><a href="rolelist" class="role_off"></a></li>
                <li><a href="adminlist" class="admin_off"></a></li>
                <li><a href="feelist" class="fee_off"></a></li>
                <li><a href="accountlist" class="account_off"></a></li>
                <li><a href="servicelist" class="service_off"></a></li>
                <li><a href="#" class="bill_off"></a></li>
                <li><a href="#" class="report_off"></a></li>
                <li><a href="usermodiview" class="information_on"></a></li>
                <li><a href="usermodiPWD" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存失败！请检查是否输入格式!</div><!--保存失败，数据并发错误！-->
            <s:form action="userupdate" method="post" cssClass="main_form" theme="simple" id="userinfoupdate">
                <input type="hidden" name="adminInfo.id" value="${adminInfo.id }" id = "id"/>
                <div class="text_info clearfix"><span>管理员账号：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="${adminInfo.adminCode }" /></div>
                <div class="text_info clearfix"><span>角色：</span></div>
                <div class="input_info">
                    <input type="text" readonly="readonly" class="readonly width400" value="<s:iterator value="adminInfo" var="roleInfos"><s:iterator value="roleInfos" var="roleInfo">[${roleInfo.name }]</s:iterator>
                </s:iterator>"/> 
                </div>
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info">
                		<s:textfield name="adminInfo.name" id="name"></s:textfield>
                    <span class="required">*</span>
                    <div class="validate_msg_long" style="color:red"></div>
                </div>
                <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info">
                		<s:textfield name="adminInfo.telephone" cssClass="width200" id="telephone"></s:textfield>
                    <div class="validate_msg_medium" style="color:red"></div>
                </div>
                <div class="text_info clearfix"><span>Email：</span></div>
                <div class="input_info">
                    <s:textfield name="adminInfo.email" cssClass="width200" id="email"></s:textfield>
                    <div class="validate_msg_medium"></div>
                </div>
                <div class="text_info clearfix"><span>创建时间：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="<s:date name="adminInfo.enrolldate" format="yyyy-MM-dd" />"/></div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" id="ajaxsave"/>
                    <input type="button" value="取消" class="btn_save" onclick="window.location='index'"/>
                </div>
               
            </s:form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>            
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
