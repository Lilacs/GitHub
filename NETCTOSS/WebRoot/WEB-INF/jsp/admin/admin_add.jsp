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
            //保存成功的提示消息
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
            $(function(){
            		$("#jumptosave").bind("click",function(){
            			//验证邮箱
            			b1 = $("#emailerr").checking6($("#email-c"),"邮箱格式错误,请检查!");
            			//验证密码
            			b2 = $("#pwderr").checking9($("#pwd1-c"),$("#pwd2-c"),$("#pwd2-err"),"密码输入不一致,请检查!")
           			//验证电话
           			b3 = $("#telephoneerr").checking4($("#telephone-c"),"电话格式错误,请检查!");
           			//验证登陆账号
           			b4 = $("#admin_codeerr").checking10($("#admin_code-c"),"4-15位,字母开头,错误请检查!")
          			//验证姓名
          			b5 = $("#nameerr").checking11($("#name-c"),"请输入真实姓名!");
          			//验证多选框至少选一个
          			b6 = checkBox();
          			if(b6 == false){
          				$("#numerr").text("至少选择一个!");
          			}
          			//验证密码为必须输入
          			b7 = false;
          			var pwd = $("#pwd2-c").val();
          			if(pwd.length < 5 ){
          				$("#pwd1-err").text("长度过短!请重新输入!");
          			}else{
						b7 = true;         			
          			}
          			if(b1 && b2 && b3 && b4 && b5 && b6 && b7){
          				$("#operate_create").submit();
          			}else{
          				alert("请检查输入项!");
          				showResultDiv(true);
               		 	window.setTimeout("showResultDiv(false);", 3000);
          			}
          		});            		
           	});
           	function checkBox(){
          		var flag = false;
          		//得到所有名字为**的标签
          		var objects = document.getElementsByName("num");
          		var j = 0;
          		var count = objects.length;
          		for(var i=0;i<objects.length;i++){
          			if(objects[i].checked == false){
          				j++;
          			}
          		}
          		if(j == count){
          			return flag;
          		}else{
          			flag = true;
          			return flag;
          		}
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
                <li><a href="adminall" class="admin_on"></a></li>
                <li><a href="fee_list" class="fee_off"></a></li>
                <li><a href="accall" class="account_off"></a></li>
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
            <div id="save_result_info" class="save_success">保存失败！</div>
            <s:form action="operate_create" method="post" cssClass="main_form" theme="simple" id="operate_create">
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <s:textfield name="adminInfo.name" id="name-c"></s:textfield>
                        <span class="required">*</span>
                        <div class="validate_msg_long" id="nameerr">真实姓名</div>
                    </div>
                    <div class="text_info clearfix"><span>管理员账号：</span></div>
                    <div class="input_info">
                        <s:textfield name="adminInfo.admin_code" id="admin_code-c"></s:textfield>
                        <span class="required">*</span>
                        <div class="validate_msg_long" id="admin_codeerr" >以字母开始最少4位</div>
                    </div>
                    <div class="text_info clearfix"><span>密码：</span></div>
                    <div class="input_info">
                       	<s:password name="adminInfo.password" id="pwd1-c"></s:password>
                        <span class="required">*</span>
                        <div class="validate_msg_long" id="pwd1-err"></div>
                    </div>
                    <div class="text_info clearfix"><span>重复密码：</span></div>
                    <div class="input_info">
                        	<s:password id="pwd2-c"></s:password>
                        <span class="required">*</span>
                        <div class="validate_msg_long" id="pwd2-err"></div>
                    </div>      
                    <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                        <s:textfield name="adminInfo.telephone" cssClass="width200" id="telephone-c"></s:textfield>
                        <span class="required">*</span>
                        <div class="validate_msg_medium" id="telephoneerr"></div>
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <s:textfield name="adminInfo.email" cssClass="width200" id="email-c"></s:textfield>
                        <span class="required">*</span>
                        <div class="validate_msg_medium" id="emailerr"></div>
                    </div>
                    <div class="text_info clearfix"><span>角色：</span></div>
                    <div class="input_info_high">
                        <div class="input_info_scroll">
                        <ul>
                            <s:checkboxlist  name="num" list="checkBoxList" listKey="id" listValue="name"  theme="verticalcheckbox"></s:checkboxlist>
 						</ul>
 					</div>
                        <span class="required">*</span>
                        <div class="validate_msg_tiny" id="numerr"></div>
                    </div>
                    <div class="button_info clearfix">
                        <input type="button" value="保存" class="btn_save" id="jumptosave" />
                        <input type="button" value="取消" class="btn_save" onclick="window.location='adminall'"/>
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
