<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
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

			$(function(){
				//给提交加入验证功能
				$("#save_checking").bind("click",function(){
            		var flag = false;
            		var n1 = $("#neme_result").checking($("#check_name"),"50长度的字母、数字、汉字和下划线的组合",0);
            		var n2 = $("#descr_result").checking($("#check_descr"),"100长度的字母、数字、汉字和下划线的组合",0);
            		var n3 = $("#base_duration_result").checking2($("#check_base_duration"),"1-600之间的整数");
            		var n4 = $("#base_cost_result").checking3($("#check_base_cost"),"0-99999.99之间的数值");
            		var n5 = $("#unit_cost_result").checking3($("#check_unit_cost"),"0-99999.99之间的数值");
					if(n1 && n2 && n3 && n4 && n5){
						$("#submit_cost").submit();
					}else{
						$.post("check_add_cost",{"name":name},function(f){
							if(!f){
								alert("无法提交,请检查表单的输入,注意提示!")
							}						
						});
						return flag;
					};
				});
				//给文本输入框加载onblur事件
				$("#check_name").bind("blur",function(){
					$("#neme_result").checking($("#check_name"),"50长度的字母、数字、汉字和下划线的组合",3);
				});
				$("#check_base_duration").bind("blur",function(){
					$("#base_duration_result").checking2($("#check_base_duration"),"1-600之间的整数");
				});
				$("#check_base_cost").bind("blur",function(){
					$("#base_cost_result").checking3($("#check_base_cost"),"0-99999.99之间的数值");
				});
				$("#check_unit_cost").bind("blur",function(){
					$("#unit_cost_result").checking3($("#check_unit_cost"),"0-99999.99之间的数值");
				});
				$("#check_descr").bind("blur",function(){
					$("#descr_result").checking($("#check_descr"),"100长度的字母、数字、汉字和下划线的组合",0);
				});
				//异步验证资费名称是否存在
				$("#check_name").blur(function(){
					var name = $("#check_name").val();
					if(name.length > 3){
						//Ajax功能post方法的使用方式
						$.post("check_add_cost",{"name":name},function(f){
							if(f){
								$("#neme_result").text("名称可用");
							}else if(!f){
								$("#neme_result").text("名称重复");
							}						
						});
					}
				});
				//给单选按钮增加change事件,贴近业务逻辑
				$("#monthly").bind("change",function(){
					$("#check_base_duration").val("0");
					$("#check_unit_cost").val("0.0");
					$("#check_base_cost").val("");
					
					$("#check_base_duration").attr("readonly","readonly");
					$("#check_base_duration").attr("class","checkedread");
					$("#check_unit_cost").attr("readonly","readonly");
					$("#check_unit_cost").attr("class","checkedread");
					$("#check_base_cost").removeAttr("readonly");
					$("#check_base_cost").removeAttr("class");
				});
				$("#package").bind("change",function(){
					$("#check_base_duration").val("");
					$("#check_unit_cost").val("");
					$("#check_base_cost").val("");
				
					$("#check_base_duration").removeAttr("readonly");
					$("#check_base_duration").removeAttr("class");
					$("#check_base_cost").removeAttr("readonly");
					$("#check_base_cost").removeAttr("class");
					$("#check_unit_cost").removeAttr("readonly");
					$("#check_unit_cost").removeAttr("class");
				});
				$("#timeBased").bind("change",function(){
					$("#check_base_duration").val("0");
					$("#check_unit_cost").val("0.05");
					$("#check_base_cost").val("0");
					
					$("#check_base_duration").attr("readonly","readonly");
					$("#check_base_duration").attr("class","checkedread");
					$("#check_unit_cost").removeAttr("readonly");
					$("#check_unit_cost").removeAttr("class");
					$("#check_base_cost").attr("readonly","readonly");
					$("#check_base_cost").attr("class","checkedread");
					
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
                <li><a href="adminall" class="admin_off"></a></li>
                <li><a href="fee_list" class="fee_on"></a></li>
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
            <div id="save_result_info" class="save_fail">保存失败，资费名称重复！</div>
            <form action="create_cost" method="post" class="main_form" id="submit_cost">
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info">
                    <input type="text" class="width300" name="name" id="check_name"/>
                    <span class="required">*</span>
                    <div class="validate_msg_short" id="neme_result"></div>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                	
                <div class="input_info fee_type">
                    <input type="radio" name="costtype" id="monthly" onclick="feeTypeChange(1);" value="1" />
                    <label for="monthly">包月</label>
                    <input type="radio" name="costtype" checked="checked" id="package" onclick="feeTypeChange(2);" value="2"/>
                    <label for="package">套餐</label>
                    <input type="radio" name="costtype" id="timeBased" onclick="feeTypeChange(3);" value="3"/>
                    <label for="timeBased">计时</label>
                </div>
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                    <input type="text" value="" name="base_duration" id="check_base_duration"/>
                    <span class="info">小时</span>
                    <span class="required">*</span>
                    <div id="base_duration_result"></div>
                </div>
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                    <input type="text" value=""  name="base_cost" id="check_base_cost"/>
                    <span class="info">元</span>
                    <span class="required">*</span>
                    <div id="base_cost_result"></div>
                </div>
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                    <input type="text" value="" name="unit_cost" id="check_unit_cost"/>
                    <span class="info">元/小时</span>
                    <span class="required">*</span>
                    <div  id="unit_cost_result"></div>
                </div>
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <s:textarea name="descr" cssClass="width300 height70" maxlength="100" id="check_descr"></s:textarea>
                    <div  id="descr_result"></div>
                </div>                
                <div class="button_info clearfix">
                	<input type="button" value="保存" class="btn_save"  id="save_checking"/>
                    <input type="button" value="取消" class="btn_save" onclick="window.location='fee_list'"/>
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)加拿大达内IT培训集团公司 </span>
        </div>
    </body>
</html>
