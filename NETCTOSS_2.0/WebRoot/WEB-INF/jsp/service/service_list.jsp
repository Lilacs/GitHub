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
            //显示角色详细信息
            function showDetail(flag, a) {
                var detailDiv = a.parentNode.getElementsByTagName("div")[0];
                if (flag) {
                    detailDiv.style.display = "block";
                }
                else
                    detailDiv.style.display = "none";
            }
            //删除
            function deleteAccount(id) {
                var r = window.confirm("确定要删除此业务账号吗？删除后将不能恢复。");
                if(r){
                		document.getElementById("operate_result_info").style.display = "block";
                		window.location = 'serviceotodelete?id='.concat(id);
                }
                
            }
            //开通或暂停
            function setState1(id) {
                var r = window.confirm("确定要开通此业务账号吗？");
                if(r){
                		document.getElementById("operate_result_info").style.display = "block";
                		window.location = 'serviceopause?id='.concat(id);
                }
                
            }
            function setState2(id) {
                var r = window.confirm("确定要开通此业务账号吗？");
                if(r){
                		document.getElementById("operate_result_info").style.display = "block";
                		window.location = 'serviceoopen?id='.concat(id);
                }
                
            }
            
            function tonewpage(value){
            		var local = "serall?pageValue=".concat(value);
            		$("#serall-c").attr("action",local);
            		$("#serall-c").submit();
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
        <%@include file="servicehead.jsp" %>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <s:form action="servicelist" method="post" theme="simple" id="serall-c">
                <!--查询-->
                <div class="search_add">                        
                    <div>OS 账号：<s:textfield name="osUserName" cssClass="width100 text_search" id="check1"></s:textfield></div>                            
                    <div>服务器 IP：<s:textfield name="unixHost" cssClass="width100 text_search" id="check2"></s:textfield></div>
                    <div>身份证：<s:textfield name="idCardNo" maxLength="18" cssClass="text_search" id="check3"></s:textfield></div>
                    <div>状态：
                    		<s:select name="status" list="#{'3':'全部','0':'开通','1':'暂停','2':'删除'}" cssClass="select_search"></s:select>
                    </div>
                    <div><input type="submit" value="搜索" class="btn_search" id="search-c"/></div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='serviceoaddview';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div>   
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                    <tr>
                        <th class="width50">业务ID</th>
                        <th class="width70">账务账号ID</th>
                        <th class="width150">身份证</th>
                        <th class="width70">姓名</th>
                        <th>OS 账号</th>
                        <th class="width50">状态</th>
                        <th class="width100">服务器 IP</th>
                        <th class="width100">资费</th>                                                        
                        <th class="width200"></th>
                    </tr>
                    <s:iterator value="list" var="service">
                    <tr>
                        <td><a href="serviceotodetail?id=${service.id }" title="查看明细">${service.id }</a></td>
                        <td>${service.account.id }</td>
                        <td>${service.account.idcardNo }</td>
                        <td>${service.account.realName }</td>
                        <td>${service.osUsername }</td>
                        <td>
                        		<s:if test="#service.status == 0">开通</s:if>
                        		<s:if test="#service.status == 1">暂停</s:if>
                        		<s:if test="#service.status == 2">删除</s:if>
                        </td>
                        <td>${service.host.id }</td>
                        <td>
                            <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">${service.cost.name }</a>
                            <!--浮动的详细信息-->
                            <div class="detail_info">
                               	${service.cost.descr }
                            </div>
                        </td>                            
                        <td class="td_modi">
                        <s:if test="#service.status == 0">
                        			<input type="button" value="暂停" class="btn_start" onclick="setState1('${service.id }');" />
                        </s:if>
                        <s:if test="#service.status == 1">
                        			<input type="button" value="开通" class="btn_pause" onclick="setState2('${service.id }');" />
                        </s:if>
                        <s:if test="#service.status != 2 ">
                            <input type="button" value="修改" class="btn_modify" onclick="location.href='serviceotomodi?id=${service.id }';" />
                        </s:if>
                        <s:if test="#service.status != 2 ">
                            <input type="button" value="删除" class="btn_delete" onclick="deleteAccount('${service.id }');" />
                       	</s:if>
                        </td>
                    </tr>       
                    </s:iterator>                                               
                </table>                
                <p>业务说明：<br />
                1、创建即开通，记载创建时间；<br />
                2、暂停后，记载暂停时间；<br />
                3、重新开通后，删除暂停时间；<br />
                4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br />
                5、业务账号不设计修改密码功能，由用户自服务功能实现；<br />
                6、暂停和删除状态的账务账号下属的业务账号不能被开通。</p>
                </div>                    
                <!--分页-->
                <div id="pages">
                <a href="servicelist?page=1">首页</a>
                <s:if test="page > 1">
        	        <a href="servicelist?page=${page-1}">上一页</a>
        	    		</s:if>
        	    
        	    		<s:iterator value="new int[pageCount]" status="i">
                    <a href="servicelist?page=${i.count}" class="current_page">${i.count }</a>
                </s:iterator>
                
                <s:if test="page < pageCount">
                    <a href="servicelist?page=${page+1}">下一页</a>
                </s:if>
                <a href="servicelist?page=${pageCount }">尾页</a>
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
