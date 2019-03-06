<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
<!-- 	<td style="width: 60px"> -->
<%-- 		<input type="checkbox" name="ids" class="taiji_check_one" value='${vo.id }'/> --%>
<!-- 	</td> -->
	<td>${fn:escapeXml(vo.taskNo)}</td>
	<td>${fn:escapeXml(vo.packageNo)}</td>
	<td>${fn:escapeXml(vo.medicineName)}</td>
	<td><fmt:formatDate value="${vo.packageDate.time}" pattern="yyyy-MM-dd"/></td>
	<td>${fn:escapeXml(vo.standard)}</td>
	<td>${fn:escapeXml(vo.weight)}</td>
	<td><fmt:formatDate value="${vo.harvestDate.time}" pattern="yyyy-MM-dd"/></td>
	<td>${fn:escapeXml(vo.packageNo)}</td>
	<td class="tdbtn">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/medicine/storage/taskSelect/createTask/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			创建任务
			</a>
		</span>
	</td>
</tr>
