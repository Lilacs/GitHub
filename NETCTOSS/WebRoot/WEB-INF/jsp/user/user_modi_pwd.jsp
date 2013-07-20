<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />        
    		<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="js/checking-1.0.js"></script>
    	<script type="text/javascript">
    		$(function(){
    			
    			$("#tosave").bind("click",function(){
    				
    				var oldpwd = $("#oldpwd").val();
    				var newpwd = $("#newpwd1").val();
    				var newpwd1 = $("#newpwd2").val();
    				b1 = false;
    				if(oldpwd.length > 5){
    					b1 = true;
    				}
    				b2 = $("#pwderr").checking9($("#newpwd1"),$("#newpwd2"),$("#pwderr"),"输入密码不一致,无法提交");
    				b3 = false;
    				if(newpwd.length > 5){
    					b3 = true;
    				}
    				if(b1 && b2 && b3){
    					$.post("userupdatepwd",{"oldpwd":oldpwd,"newpwd":newpwd},function(data){
    						if(data.flag){
    							alert(data.msg);
    							window.location= "usermodipwd";
    						}else{
    							alert(data.msg);
    						}
    					});
    				}else{
    					alert("检查输入!");
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
                <li><a href="adminall" class="admin_off"></a></li>
                <li><a href="fee_list" class="fee_off"></a></li>
                <li><a href="accall" class="account_off"></a></li>
                <li><a href="serall" class="service_off"></a></li>
                <li><a href="#" class="bill_off"></a></li>
                <li><a href="#" class="report_off"></a></li>
                <li><a href="userinfotoview" class="information_off"></a></li>
                <li><a href="usermodipwd" class="password_on"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <div id="main">      
            <!--保存操作后的提示信息：成功或者失败-->      
            <div id="save_result_info" class="save_success">保存成功！</div><!--保存失败，旧密码错误！-->
            <!-- <form action="usermodipwd" method="get" class="main_form" id="jumptonew"> -->
                <div class="text_info clearfix"><span>旧密码：</span></div>
                <div class="input_info">
                    <input type="password" class="width200"  id="oldpwd"/><span class="required">*</span>
                    <div class="validate_msg_medium">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>新密码：</span></div>
                <div class="input_info">
                    <input type="password"  class="width200" id="newpwd1"/><span class="required">*</span>
                    <div class="validate_msg_medium">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复新密码：</span></div>
                <div class="input_info">
                    <input type="password" class="width200"  id="newpwd2"/><span class="required">*</span>
                    <div class="validate_msg_medium" id="pwderr">两次新密码必须相同</div>
                </div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" id="tosave" />
                    <input type="button" value="取消" class="btn_save" onclick="window.location='index';" id="jumptonew"/>
                </div>
            <!-- </form>  --> 
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
