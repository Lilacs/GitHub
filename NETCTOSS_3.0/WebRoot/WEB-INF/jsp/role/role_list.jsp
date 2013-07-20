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
                	window.location = 'roleowillLoggertodelete?id='.concat(id);
                }
                
            }
        </script>
    </head>
    <body>
       
        <%@include file="rolehead.jsp" %>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="" method="">
                <!--查询-->
                <div class="search_add">
                    <input type="button" value="增加" class="btn_add" onclick="location.href='roleoaddview';" />
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
                        <s:iterator value="roleInfos" var="roleInfo">
                        <tr>
                            <td>${roleInfo.id}</td>
                            <td>${roleInfo.name }</td>
                            <td>
                            	 <s:iterator value="#roleInfo.abilitys" var="ability">
                            	 [${ability.abilityType }]
                            	 </s:iterator>   
                        	</td>
                            <td>
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='roleotomodi?id=${roleInfo.id}';"/>
                                <input type="button" value="删除" class="btn_delete" onclick="deleteRole('${roleInfo.id}')" />
                            </td>
                        </tr>
                       
                        </s:iterator>            
                    </table>
                </div> 
                <!--分页-->
               
                <div id="pages">
                <a href="rolelist?page=1">首页</a>
                <s:if test="page > 1">
        	        <a href="rolelist?page=${page-1}">上一页</a>
        	    		</s:if>
        	    
        	    		<s:iterator value="new int[pageCount]" status="i">
                    <a href="rolelist?page=${i.count}" class="current_page">${i.count }</a>
                </s:iterator>
                
                <s:if test="page < pageCount">
                    <a href="rolelist?page=${page+1}">下一页</a>
                </s:if>
                <a href="rolelist?page=${pageCount }">尾页</a>
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
