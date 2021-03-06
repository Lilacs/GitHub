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
        
        <%@include file="rolehead.jsp" %>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">           
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存失败!</div>
            
            <s:form action="roleowillLoggertoupdate" method="post" theme="simple" cssClass="main_form" id="roleupdate">
                <div class="text_info clearfix"><span>角色名称：</span></div>
                <div class="input_info">
                		<s:hidden name="roleInfo.id"></s:hidden>
                    <s:textfield name="roleInfo.name" cssClass="width200" id="role_name"></s:textfield>
                    <span class="required">*</span>
                    <div class="validate_msg_medium"></div>
                </div>   
                <div class="text_info clearfix"><span>设置权限：</span></div>
                <div class="input_info_high">
                    <div class="input_info_scroll">
                       <s:checkboxlist  name="num" list="abilitys" listKey="id" listValue="abilityType" theme="verticalcheckbox"></s:checkboxlist>
                    </div>
                    <span class="required">*</span>
                    <div class="validate_msg_tiny" id="numerr"></div>
                </div>
                <div class="button_info clearfix">
                    <input type="submit" value="保存" class="btn_save"/>
                    <input type="button" value="取消" class="btn_save" onclick="window.location='rolelist'"/>
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
