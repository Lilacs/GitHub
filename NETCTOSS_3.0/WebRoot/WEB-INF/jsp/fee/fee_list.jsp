<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
        <script language="javascript" type="text/javascript">
            //排序按钮的点击事件
            function sort(btnObj) {
                if (btnObj.className == "sort_desc")
                    btnObj.className = "sort_asc";
                else
                    btnObj.className = "sort_desc";
            }

            //启用
            function startFee(id) {
                var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
                if(r){
               		document.getElementById("operate_result_info").innerHTML = "启用成功!";	
               	 	document.getElementById("operate_result_info").style.display = "block";
               	 	window.location= 'feeowillLoggeropencost?id='.concat(id);
                }
            }
            //删除
            function deleteFee(id) {
                var r = window.confirm("确定要删除此资费吗？");
                if(r){ 
                	document.getElementById("operate_result_info").innerHTML = "删除成功!";
                	document.getElementById("operate_result_info").style.display = "block";
                	window.location= 'feeowillLoggertodelete?id='.concat(id);
                }  
            }
        </script>        
    </head>
    <body>
        <%@include file="feehead.jsp" %>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="" method="">
                <!--排序-->
                <div class="search_add">
                    <div>
                        <input type="button" value="月租" class="sort_asc" onclick="sort(this);" />
                        <input type="button" value="基费" class="sort_asc" onclick="sort(this);" />
                        <input type="button" value="时长" class="sort_asc" onclick="sort(this);" />
                    </div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='feeoaddview';" />
                </div> 
                <!--启用操作的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div>    
                <!--数据区域：用表格展示数据-->    
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>资费ID</th>
                            <th class="width100">资费名称</th>
                            <th>基本时长</th>
                            <th>基本费用</th>
                            <th>单位费用</th>
                            <th>创建时间</th>
                            <th>开通时间</th>
                            <th class="width50">状态</th>
                            <th class="width200"></th>
                        </tr>
                        <s:iterator value="costs" var="cost">        
                        <tr>
                            <td>${cost.id }</td>
                            <td><a href="feeotodetail?id=${cost.id}">${cost.name }</a></td>
                            <td>${cost.baseDuration } 小时</td>
                            <td>${cost.baseCost } 元</td>
                            <td>${cost.unitCost } 元/小时</td>
                            <td><s:date name="#cost.creattime" format="yyyy/MM/dd"/></td>
                            <td><s:date name="#cost.startime" format="yyyy/MM/dd"/></td>
                            <td>
                            	<s:if test="#cost.status == 1">开通</s:if>
                            	<s:elseif test="#cost.status == 0">暂停</s:elseif>
                            	<s:else>无状态</s:else>
                            </td>
                            <td>
                            	<s:if test="#cost.status != 1">                              
                                <input type="button" value="启用" class="btn_start" onclick="startFee(${cost.id})" />
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='feeotomodi?id=${cost.id}';" />
                                <input type="button" value="删除" class="btn_delete" onclick="deleteFee('${cost.id}')" />
                           	</s:if>  
                            </td>
                        </tr>
                        </s:iterator>
                    </table>
                    <p>业务说明：<br />
                    1、创建资费时，状态为暂停，记载创建时间；<br />
                    2、暂停状态下，可修改，可删除；<br />
                    3、开通后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br />
                    4、业务账号修改资费时，在下月底统一触发，修改其关联的资费ID（此触发动作由程序处理）
                    </p>
                </div>
                <!--分页-->
                <div id="pages">
                <s:if test="page > 1">
        	        <a href="feelist?page=${page-1}">上一页</a>
        	    </s:if>
        	    
        	    <s:iterator value="new int[page_count]" status="i">
                    <a href="feelist?page=${i.count}" class="current_page"><s:property value="#i.count"/></a>
                </s:iterator>
                
                <s:if test="page < page_count">
                    <a href="feelist?page=${page+1}">下一页</a>
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
