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
        <script language="javascript" type="text/javascript">
            function deleteRole(id) {
                var r = window.confirm("确定要删除此角色吗？");
                if(r){
                	document.getElementById("operate_result_info").style.display = "block";
                	window.location = 'role_delete?id='.concat(id);
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
            <form action="" method="">
                <!--查询-->
                <div class="search_add">
                    <input type="button" value="增加" class="btn_add" onclick="location.href='role_add';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div> <!--删除错误！该角色被使用，不能删除。-->
                <!--数据区域：用表格展示数据-->     
                <div id="data">                      
                    <table id="datalist">
                        <tr>                            
                            <th>角色 ID</th>
                            <th>角色名称</th>
                            <th class="width600">拥有的权限</th>
                            <th class="td_modi"></th>
                        </tr>   
                        <s:iterator value="list_role_info" var="info" status="i">
                        
                        <tr>
                            <td>${info.id}</td>
                            <td>${info.name }</td>
                            <td>
                            	${info.privilege}
                        	</td>
                            <td>
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='role_modi?id=${info.id}';"/>
                                <input type="button" value="删除" class="btn_delete" onclick="deleteRole('${info.id}')" />
                            </td>
                        </tr>
                       
                        </s:iterator>            
                    </table>
                </div> 
                <!--分页-->
               
                <div id="pages">
                 <s:if test="pageValue > 1">
                	 <a href="role_list?pageValue=${pageValue-1 }">上一页</a>
                 </s:if>
                 <s:if test="pageValue == 1">
                 	<a>上一页</a>
                 </s:if>
        	     <s:iterator value="new int[pageCount]" status="i">
        	     	<a href="role_list?pageValue=${i.count}" class="current_page">${i.count}</a>
        	     </s:iterator>
                 <s:if test="pageValue < pageCount">
                 	 <a href="role_list?pageValue=${pageValue+1 }">下一页</a>
                 </s:if>
                 <s:if test="pageValue == pageCount">
                 	<a>下一页</a>
                 </s:if>
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
