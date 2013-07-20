<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" /> 
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript">
        	$(function(){
        		$("#check_login").bind("click",function(){
					var flag1 = $("#userisnull").checking($("#user"),"账号长度过低!");
					var flag2 = $("#pwdisnull").checking($("#pwd"),"请输入密码!");
					if(flag1 == true && flag2 == true){
						$("#login_checking").submit();
					};
				});
				
				$("#user").bind("blur",function(){
					$("#userisnull").checking($("#user"),"账号长度过低!");
				});
				
				$("#pwd").bind("blur",function(){
					$("#pwdisnull").checking($("#pwd"),"请重新输入密码,");
				});
				$("#changeimg").bind("click",function(){
					$("#changeimg").attr("src","checkcode?id=".concat(Math.random()));
					
				});
			});
			$.fn.checking=function(object,errormsg){
				var flag = false;
				var val = object.val();
				if(val == "" || val.length < 5){
					this.text(errormsg);
					return flag;
				}else{
					this.text("");
					flag = true;
					return flag;
				}
			};
        </script>
        <script type="text/javascript">
        	var num = 0;
			function checkuser(){
				var flag = false;
				var checkuser = document.getElementById("user").value;
				var checkpwd = document.getElementById("pwd").value;
				if(checkuser == "" || checkpwd == ""){
				num= num+1; 
				document.getElementById("someerror").innerHTML = "用户名或密码错误，请重试!第".concat(num).concat("次尝试");
					return false;
				}else{
					flag = true;
					return flag;
				}
			}
			
		

		</script>
    </head>
    <body class="index">
        <div class="login_box">
        <form action="check" method="post" id="login_checking">
            <table>
                <tr>
                    <td class="login_info">账号：</td>
                    <td colspan="2"><input name="user" type="text" class="width150" id="user"/></td>
                    <td class="login_error_info"><span class="required" id="userisnull"></span></td>
                </tr>
                <tr>
                    <td class="login_info">密码：</td>
                    <td colspan="2"><input name="pwd" type="password" class="width150" id="pwd"/></td>
                    <td><span class="required" id="pwdisnull"></span></td>
                </tr>
                <tr>
                    <td class="login_info">验证码：</td>
                    <td class="width70"><input name="checkcode" type="text" class="width70" /></td>
                    <td><img src="checkcode" alt="验证码" title="点击更换" id="changeimg"/></td>  
                    <td><span class="required">${usererror}</span></td>              
                </tr>            
                <tr>
                    <td></td>
                    <td class="login_button" colspan="2">
                    	 <a id="check_login"><img src="images/login_btn.png" /></a>
                    </td>    
                    <td><span class="required" id = "someerror"></span></td>                
                </tr>
            </table>
          </form>
        </div>
    </body>
</html>
