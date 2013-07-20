<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>���ڣ�NetCTOSS</title>
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
    				b2 = $("#pwderr").checking9($("#newpwd1"),$("#newpwd2"),$("#pwderr"),"�������벻һ��,�޷��ύ");
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
    					alert("�������!");
    				}
    				
    			});
    		});
    		
    	</script>
    </head>
    <body>
        <!--Logo����ʼ-->
        <div id="header">
            <img src="images/logo.png" alt="logo" class="left"/>
            <a href="#">[�˳�]</a>            
        </div>
        <!--Logo�������-->
        <!--��������ʼ-->
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
        <!--�����������-->
        <div id="main">      
            <!--������������ʾ��Ϣ���ɹ�����ʧ��-->      
            <div id="save_result_info" class="save_success">����ɹ���</div><!--����ʧ�ܣ����������-->
            <!-- <form action="usermodipwd" method="get" class="main_form" id="jumptonew"> -->
                <div class="text_info clearfix"><span>�����룺</span></div>
                <div class="input_info">
                    <input type="password" class="width200"  id="oldpwd"/><span class="required">*</span>
                    <div class="validate_msg_medium">30�������ڵ���ĸ�����ֺ��»��ߵ����</div>
                </div>
                <div class="text_info clearfix"><span>�����룺</span></div>
                <div class="input_info">
                    <input type="password"  class="width200" id="newpwd1"/><span class="required">*</span>
                    <div class="validate_msg_medium">30�������ڵ���ĸ�����ֺ��»��ߵ����</div>
                </div>
                <div class="text_info clearfix"><span>�ظ������룺</span></div>
                <div class="input_info">
                    <input type="password" class="width200"  id="newpwd2"/><span class="required">*</span>
                    <div class="validate_msg_medium" id="pwderr">���������������ͬ</div>
                </div>
                <div class="button_info clearfix">
                    <input type="button" value="����" class="btn_save" id="tosave" />
                    <input type="button" value="ȡ��" class="btn_save" onclick="window.location='index';" id="jumptonew"/>
                </div>
            <!-- </form>  --> 
        </div>
        <!--��Ҫ�������-->
        <div id="footer">
            <p>[Դ�Ա����ļ������������ʦ�ʣ�����ʵ����ҵ�����������õ�ʵս��Ŀ]</p>
            <p>��Ȩ����(C)���ô����IT��ѵ���Ź�˾ </p>
        </div>
    </body>
</html>
