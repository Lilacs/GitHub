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
          	$(function(){
          		$("#jumptosave").bind("click",function(){
          			b1 = $("#nameerr").checking11($("#name-c"),"真实姓名");
          			b2 = $("#telephoneerr").checking4($("#telephone-c"),"非法格式,请检查!");
          			b3 = $("#emailerr").checking6($("#email-c"),"非法格式,请检查!");
          			b4 = checkBox();
          			if(b4 == false){
          				$("#numerr").text("至少选一个!");
          			}
          			if(b1 && b2 && b3 && b4){
          				$("#operate_update").submit();
          			}else{
          				showResultDiv(true);
                			window.setTimeout("showResultDiv(false);", 3000);
          			}
          		});
          	});
          	
        </script>
    </head>
    <body>
        	<%@include file="adminhead.jsp" %>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <div id="save_result_info" class="save_success">保存失败！</div>
            <s:form action="adminowillLoggertoupdate" method="post" cssClass="main_form" theme="simple" id="operate_update">
            			<s:hidden name="adminInfo.id"></s:hidden>
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <s:textfield name="adminInfo.name"></s:textfield>
                        <s:hidden name="id"></s:hidden>
                        <span class="required">*</span>
                        <div class="validate_msg_long " style="color:red"></div>
                    </div>
                    <div class="text_info clearfix"><span>管理员账号：</span></div>
                    <div class="input_info"><s:textfield name="adminInfo.adminCode" readonly="true"></s:textfield></div>
                    <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                        <s:textfield name="adminInfo.telephone"></s:textfield>
                        <span class="required">*</span>
                        <div class="validate_msg_long" style="color:red"></div>
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <s:textfield name="adminInfo.email" cssClass="width200"></s:textfield>
                        <span class="required">*</span>
                        <div class="validate_msg_medium" style="color:red" ></div>
                    </div>
                    <div class="text_info clearfix"><span>角色：</span></div>
                    <div class="input_info_high">
                        <div class="input_info_scroll">
                              <s:checkboxlist name="num" list="roleInfos" listKey="id" listValue="name"></s:checkboxlist>
                        </div>
                        <span class="required">*</span>
                        <div class="validate_msg_tiny" style="color:red" id="numerr"></div>
                    </div>
                    <div class="button_info clearfix">
                        <input type="submit" value="保存" class="btn_save" />
                        <input type="button" value="取消" class="btn_save" onclick="window.location='adminlist'"/>
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
