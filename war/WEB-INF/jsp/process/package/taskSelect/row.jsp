<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(vo.harvestNo)}</td>
	<td>${fn:escapeXml(vo.processNo)}</td>
	<td><fmt:formatDate value="${vo.processTime.time}" pattern="yyyy-MM-dd"/></td>
	<td>${fn:escapeXml(vo.processMethod)}</td>
	<td>${fn:escapeXml(vo.preProcessQuality)}</td>
	<td>${fn:escapeXml(vo.nonMediQuality)}</td>
	<td>${fn:escapeXml(vo.postProcessQuality)}</td>
	<td class="tdbtn">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/process/package/taskSelect/createTask/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			创建任务
			</a>
		</span>
	</td>
</tr>
