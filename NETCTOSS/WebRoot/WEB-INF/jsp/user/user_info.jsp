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
             	//验证电话
             	var b1 = false;
             	var telephone = $("#telephone-c").val();
             	if(telephone.length == 0 ){
             		b1 = true;
             	}else if(telephone.length > 0){
             		b1 = $("#telephoneerr").checking4($("#telephone-c"),"非法格式,请检查!");
             	}
             	//验证email;
             	var b2 = false;
             	var email = $("#email-c").val();
             	if(email.length == 0 ){
             		b2 = true;
             	}else if(email.length > 0){
             		b2 = $("#emailerr").checking6($("#email-c"),"非法格式,请检查!");
             	}
             	//验证姓名
             	var b3 = $("#nameerr").checking11($("#name-c"),"2-4位的中文姓名");
                if(b1 && b2 && b3){
                		alert("修改成功!");
                		$("#userinfoupdate").submit();
                }else{
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
                <li><a href="adminall" class="admin_off"></a></li>
                <li><a href="fee_list" class="fee_off"></a></li>
                <li><a href="accall" class="account_off"></a></li>
                <li><a href="serall" class="service_off"></a></li>
                <li><a href="#" class="bill_off"></a></li>
                <li><a href="#" class="report_off"></a></li>
                <li><a href="userinfotoview" class="information_on"></a></li>
                <li><a href="usermodipwd" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存失败！请检查是否输入格式!</div><!--保存失败，数据并发错误！-->
            <s:form action="userinfoupdate" method="post" cssClass="main_form" theme="simple" id="userinfoupdate">
                <input type="hidden" name="adminInfo.id" value="${adminInfo.id }" />
                <div class="text_info clearfix"><span>管理员账号：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="${adminInfo.admin_code }" /></div>
                <div class="text_info clearfix"><span>角色：</span></div>
                <div class="input_info">
                    <input type="text" readonly="readonly" class="readonly width400" value="${adminInfo.adminrole }" />
                </div>
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info">
                		<s:textfield name="adminInfo.name" id="name-c"></s:textfield>
                    <span class="required">*</span>
                    <div class="validate_msg_long " id="nameerr" style="color:red"></div>
                </div>
                <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info">
                		<s:textfield name="adminInfo.telephone" cssClass="width200" id="telephone-c"></s:textfield>
                    <div class="validate_msg_medium" id="telephoneerr" style="color:red"></div>
                </div>
                <div class="text_info clearfix"><span>Email：</span></div>
                <div class="input_info">
                    <s:textfield name="adminInfo.email" cssClass="width200" id="email-c"></s:textfield>
                    <div class="validate_msg_medium" id="emailerr" style="color:red"></div>
                </div>
                <div class="text_info clearfix"><span>创建时间：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="<s:date name="adminInfo.enrolldate" format="yyyy-MM-dd" />"/></div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="showResult();" />
                    <input type="button" value="取消" class="btn_save" />
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
