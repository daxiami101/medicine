<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(vo.materialName)}</td>
	<td>${fn:escapeXml(vo.originalPlace)}</td>
	<td>${fn:escapeXml(vo.productionId)}</td>
	<td><fmt:formatDate value="${vo.outTime.time}" pattern="yyyy-MM-dd"/></td>
	<td>${fn:escapeXml(vo.standard)}</td>
	<td>${fn:escapeXml(vo.unit)}</td>
	<td>${fn:escapeXml(vo.outNum)}</td>
	<td>${fn:escapeXml(vo.leftNum)}</td>
	<td><fmt:formatDate value="${vo.outTime.time}" pattern="yyyy-MM-dd"/></td>
	<td>${fn:escapeXml(vo.storeMethod)}</td>
	<td class="tdbtn">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/ncp/exam/examOrderAccept/view/${model.id}" class="taiji_modal_lg taiji_acl"  >
			查看
			</a>
		</span>
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/tablet/saleOut/taskSelect/createTask/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			创建任务
			</a>
		</span>
	</td>
</tr>
