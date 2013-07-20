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
        <script language="javascript" type="text/javascript">
            //显示角色详细信息
            function showDetail(flag, a) {
                var detailDiv = a.parentNode.getElementsByTagName("div")[0];
                if (flag) {
                    detailDiv.style.display = "block";
                }
                else
                    detailDiv.style.display = "none";
            }
            //重置密码
            function resetPwd() {
                alert("请至少选择一条数据！");
                //document.getElementById("operate_result_info").style.display = "block";
            }
            //删除
            function deleteAdmin(id) {
                var r = window.confirm("确定要删除此管理员吗？");
                if(r){
                 	document.getElementById("operate_result_info").style.display = "block";
                 	$("#onsubmitdelete").text("删除成功");
                	window.location='operate_delete?id='.concat(id);
                }
            }
            //全选
            function selectAdmins(inputObj) {
                var inputArray = document.getElementById("datalist").getElementsByTagName("input");
                for (var i = 1; i < inputArray.length; i++) {
                    if (inputArray[i].type == "checkbox") {
                        inputArray[i].checked = inputObj.checked;
                    }
                }
            }
           	function tonewpage(newpage){
           		var name = $("#searchname").val();
            	if(name.length > 0){
            			$("#searchform").attr("action","adminsearch?pageValue=".concat(newpage));
           			 	$("#searchform").submit();
           	 	}else{
           	 		$("#searchform").attr("action","adminall?pageValue=".concat(newpage));
           			 	$("#searchform").submit();
           	 	}
           	 	
        	}
            $(function(){
            	var name = $("#searchname").val();
            	if(name.length > 0){
            		var n1 = $("#upage").val();
            		var n2 = $("#npage").val();
            		var n3 =$("#pageCount").val();
            		$("#uppage").removeAttr("href");
            		$("#nextpage").removeAttr("href");
            		for(var i=1;i<=n3;i++){
            				$("#pagefor".concat(i)).removeAttr("href");
            		}
            		$("#uppage").bind("click",function(){
            			$("#searchform").attr("action","adminsearch?pageValue=".concat(n1));
            			$("#searchform").submit();
            		});
            		$("#nextpage").bind("click",function(){
            			$("#searchform").attr("action","adminsearch?pageValue=".concat(n2));
            			$("#searchform").submit();
            		});
            	}
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
            <s:form action="adminsearch" method="post" theme="simple" id="searchform">
                <!--查询-->
                <div class="search_add">
                    <div>
                        模块：
                        <s:select name="searchprivilege" list="#{'0':'全部','1':'角色管理','2':'管理员管理','3':'资费管理','4':'账务账号','5':'业务账号','6':'账单管理','7':'报表'}" cssClass="select_search"></s:select>
                        </div>
                    <div>角色：<s:textfield name="searchname" cssClass="text_search width200" id="searchname"></s:textfield></div>
                    <div><input type="submit" value="搜索" class="btn_search"/></div>
                    <input type="button" value="密码重置" class="btn_add" onclick="resetPwd();" />
                    <input type="button" value="增加" class="btn_add" onclick="location.href='operate_view';" />
                </div>
                <!--删除和密码重置的操作提示-->
                <div id="operate_result_info" class="operate_fail">
                    <img src="images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span id="onsubmitdelete">删除失败！数据并发错误。</span><!--密码重置失败！数据并发错误。-->
                </div> 
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th class="th_select_all">
                                <input type="checkbox" onclick="selectAdmins(this);" />
                                <span>全选</span>
                            </th>
                            <th>管理员ID</th>
                            <th>姓名</th>
                            <th>登录名</th>
                            <th>电话</th>
                            <th>电子邮件</th>
                            <th>授权日期</th>
                            <th class="width100">拥有角色</th>
                            <th></th>
                        </tr>        
                        <s:iterator value="adminInfo" var="info">           
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>${info.id }</td>
                            <td>${info.name }</td>
                            <td>${info.admin_code }</td>
                            <td>${info.telephone }</td>
                            <td>${info.email }</td>
                            <td><s:date name="#info.enrolldate" format="yyyy-MM-dd"/></td>
                            <td>
                                <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">${info.adminrole[0] }...</a>
                                <!--浮动的详细信息-->
                                <div class="detail_info">
                                   	${info.adminrole }
                                </div>
                            </td>
                            <td class="td_modi">
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='operate_modi?id=${info.id}';" />
                                <input type="button" value="删除" class="btn_delete" onclick="deleteAdmin('${info.id}');" />
                            </td>
                        </tr>
                        </s:iterator>
                    </table>
                </div>
                <!--分页-->
                <div id="pages">
                <s:if test="pageValue > 1">
                	 <a href="adminall?pageValue=${pageValue-1 }" id="uppage">上一页</a>
                 </s:if>
                 <s:if test="pageValue == 1">
                 	<a>上一页</a>
                 </s:if>
        	     <s:iterator value="new int[pageCount]" status="i">
        	     	<%-- <a href="adminall?pageValue=${i.count}" class="current_page" id="pagefor${i.count}">${i.count}</a> --%>
        	     	<a href="javascript:" onclick="tonewpage(${i.count})" class="current_page">${i.count}</a>
        	     	 
        	     </s:iterator>
                 <s:if test="pageValue < pageCount">
                 	 <a href="adminall?pageValue=${pageValue+1 }" id="nextpage">下一页</a>
                 </s:if>
                 <s:if test="pageValue == pageCount">
                 	<a>下一页</a>
                 </s:if>
        	    <input type="hidden" value="${pageValue-1}" id="upage"/>
        	    <input type="hidden" value="${pageValue+1}" id="npage"/>
        	    <input type="hidden" value="${pageCount}" id="pageCount"/>
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
