<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(vo.packageNo)}</td>
	<td>${fn:escapeXml(vo.storeCode)}</td>
	<td>${fn:escapeXml(vo.storeCondition)}</td>
	<td>${fn:escapeXml(vo.storeMethod)}</td>
	<td>${fn:escapeXml(vo.measure)}</td>
	<td class="tdbtn">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/medicine/sales/taskSelect/createTask/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			创建任务
			</a>
		</span>
	</td>
</tr>
