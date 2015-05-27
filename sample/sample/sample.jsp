<%@ page import="jp.co.opt.sample.SampleModel" %>
<%@ page import="java.util.*" %>
<table border="1">
<%
	java.util.List<jp.co.opt.sample.SampleModel> list =  (java.util.List<jp.co.opt.sample.SampleModel>)request.getAttribute("resultList");
	for(int i = 0; i < list.size(); i++){
%>
	<tr>
		<td><%= list.get(i).getGroupId() %></td>
		<td><%= list.get(i).getItemId() %></td>
		<td><%= list.get(i).getColumnValue() %></td>
	</tr>
<%
	}
%>
</table>
