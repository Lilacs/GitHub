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
            //删除
            function deleteAccount() {
                var r = window.confirm("确定要删除此账务账号吗？\r\n删除后将不能恢复，且会删除其下属的所有业务账号。");
                document.getElementById("operate_result_info").style.display = "block";
            }
            //开通或暂停
            function setState(status,id) {
            		if(status == 0){
            			 var r = window.confirm("确定要暂停此账务账号吗？");
            			 if(r){
            			 	document.getElementById("operate_result_info").style.display = "block";
            			 	window.location = 'accountotopause?id='.concat(id);
            			 }
            		}
            		if(status == 1){
            			 var r = window.confirm("确定要开通此账务账号吗？");
            			 if(r){
            			 	document.getElementById("operate_result_info").style.display = "block";
            			 	window.location = 'accountotoopen?id='.concat(id);
            			 }
            			
            		}
                
            }
            function tonewpage(value){
            		var local = "accall?pageValue=".concat(value);
            		$("#accall-c").attr("action",local);
            		$("#accall-c").submit();
            }
            $(function(){
            		$("#search-c").bind("click",function(){
            			
            			var b1 = $("#check1").checking12($("#check1"),"");
            			var b2 = $("#check2").checking12($("#check2"),"");
            			var b3 = $("#check3").checking12($("#check3"),"");
            			if(b1 && b2 && b3){
            				$("#accall-c").submit();
            			}else{
            				alert("查询中不能包含(' \\ $ \" )等字符");
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
        <%@include file="accounthead.jsp" %>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
           	<s:form action="accountlist" method="post" theme="simple" id="accall-c">
                <!--查询-->
                <div class="search_add">                        
                    <div>身份证：
                    		<s:textfield name="idCard" cssClass="text_search" id="check1"></s:textfield></div>                            
                    <div>姓名：  
                    		<s:textfield name="realName" cssClass="width70 text_search" id="check2"></s:textfield></div>
                    <div>登录名 
                    		<s:textfield name="loginName" cssClass="text_search" id="check3"></s:textfield></div>
                    <div>
                        状态：
                        <s:select name="status" list="#{'4':'全部','0':'开通','1':'暂停','2':'删除' }" cssClass="select_search"></s:select>
                    </div>
                    <div><input type="submit" value="搜索" class="btn_search" id="search-c"/></div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='accountoaddview';" />
                </div>  
                <!--删除等的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="/images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功，且已删除其下属的业务账号！
                </div>   
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                    <tr>
                        <th>账号ID</th>
                        <th>姓名</th>
                        <th class="width150">身份证</th>
                        <th>登录名</th>
                        <th>状态</th>
                        <th class="width100">创建日期</th>
                        <th class="width150">上次登录时间</th>                                                        
                        <th class="width200"></th>
                    </tr>
                    <s:iterator value="list" var="account">
                    <tr>
                        <td>${account.id}</td>
                        <td><a href="javascript:" onclick="window.location='accountotodetail?id=${account.id }'">${account.realName }</a></td>
                        <td>${account.idcardNo }</td>
                        <td>${account.loginName }</td>
                        <td>
                        <s:if test="#account.status == 0">开通</s:if>
                        <s:if test="#account.status == 1">暂停</s:if>
                        <s:if test="#account.status == 2">删除</s:if>
                        </td>
                        <td><s:date name="#account.createDate" format="yyyy-MM-dd"/></td>
                        <td><s:date name="#account.lastLoginTime" format="yyyy-MM-dd"/></td>                            
                        <td class="td_modi">
                        	<s:if test="#account.status == 0">
                        		<input type="button" value="暂停" class="btn_start" onclick="setState('${account.status}','${account.id}') " />
                        	</s:if>
                        <s:if test="#account.status == 1">
                        		<input type="button" value="开通" class="btn_pause" onclick="setState('${account.status}','${account.id}');" />
                        	</s:if>
                        	<s:if test="#account.status == 2"></s:if>
                         <s:if test="#account.status != 2">
                            <input type="button" value="修改" class="btn_modify" onclick="location.href='accountotomodi?id=${account.id }';" />
                            <input type="button" value="删除" class="btn_delete" onclick="window.location='accountotodelete?id=${account.id }'" />
                         </s:if>  
                        </td>
                    </tr>       
                    </s:iterator>       
                </table>
                <p>业务说明：<br />
                1、创建则开通，记载创建时间；<br />
                2、暂停后，记载暂停时间；<br />
                3、重新开通后，删除暂停时间；<br />
                4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br />
                5、暂停账务账号，同时暂停下属的所有业务账号；<br />                
                6、暂停后重新开通账务账号，并不同时开启下属的所有业务账号，需要在业务账号管理中单独开启；<br />
                7、删除账务账号，同时删除下属的所有业务账号。</p>
                </div>                   
                <!--分页-->
                <div id="pages">
                <a href="accountlist?page=1">首页</a>
                <s:if test="page > 1">
        	        <a href="accountlist?page=${page-1}">上一页</a>
        	    		</s:if>
        	    
        	    		<s:iterator value="new int[pageCount]" status="i">
                    <a href="accountlist?page=${i.count}" class="current_page">${i.count }</a>
                </s:iterator>
                
                <s:if test="page < pageCount">
                    <a href="accountlist?page=${page+1}">下一页</a>
                </s:if>
                <a href="accountlist?page=${pageCount }">尾页</a>
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
