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
            function checkBox(){
          		var flag = false;
          		//得到所有名字为**的标签
          		var objects = document.getElementsByName("newability");
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
          	$(function(){
          		$("#role_name").bind("blur",function(){
          			var name =  $("#role_name").val();
            	 		$.post("checkrolenamemidoName",{"name":name},function(flag){
            	 			if(flag){
            	 				$("#nameerr").text("名称可用");
            	 			}else if(!flag){
            	 				$("#nameerr").text("名称重复");
            	 			}
            	 		});
          		});
          		$("#jumptosave").bind("click",function(){
          			//验证角色名称是否可用
          			var b1 = false;
            		 	if($("#nameerr").text() == "名称可用"){
            		 		b1 = true;
            		 	}
            		 	//验证角色名称是否合法
            		 	var b2 = $("#nameerr").checking13($("#role_name"),",请检查,2-10位中文名称");
          			//验证至少选择一个
          			var b3 = checkBox();
          			if(b3 == false){
          				$("#numerr").text("至少选择一个");
          			}
          			
          			if(b1 && b2 && b3){
          				$("#roleupdate").submit();
          			}else{
          				 showResultDiv(true);
            			  	 window.setTimeout("showResultDiv(false);", 3000);
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
                <li><a href="role_list" class="role_on"></a></li>
                <li><a href="adminall" class="admin_off"></a></li>
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
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存失败!</div>
            
            <s:form action="roleupdate" method="post" theme="simple" cssClass="main_form" id="roleupdate">
                <div class="text_info clearfix"><span>角色名称：</span></div>
                <div class="input_info">
                    <s:textfield name="name" cssClass="width200" id="role_name"></s:textfield>
                    <input type="hidden" name="oldname" value="${name}"/>
                    <span class="required">*</span>
                    <div class="validate_msg_medium" id="nameerr">点击检查</div>
                   
                </div>   
                <div class="text_info clearfix"><span>设置权限：</span></div>
                <div class="input_info_high">
                    <div class="input_info_scroll">
                       <s:checkboxlist  name="newability" list="checkList" listKey="id" listValue="name" theme="verticalcheckbox"></s:checkboxlist>
                    </div>
                    <span class="required">*</span>
                    <div class="validate_msg_tiny" id="numerr"></div>
                </div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" id="jumptosave" />
                    <input type="button" value="取消" class="btn_save" onclick="window.location='role_list'"/>
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
