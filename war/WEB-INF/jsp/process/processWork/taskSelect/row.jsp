<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
<!-- 	<td style="width: 60px"> -->
<%-- 		<input type="checkbox" name="ids" class="taiji_check_one" value='${vo.id }'/> --%>
<!-- 	</td> -->
	<td>${fn:escapeXml(vo.harvestNo)}</td>
	<td>${fn:escapeXml(vo.harvestPart)}</td>
	<td>${fn:escapeXml(vo.harvestMedicine)}</td>
	<td><fmt:formatDate value="${vo.harvestTime.time}" pattern="yyyy-MM-dd"/></td>
	<td>${fn:escapeXml(vo.harvestMethod)}</td>
	<td>${fn:escapeXml(vo.area)}</td>
	<td>${fn:escapeXml(vo.production)}</td>
	<td class="tdbtn">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/medicine/farmer/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			查看
			</a>
		</span>
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/process/processWork/taskSelect/createTask/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			创建任务
			</a>
		</span>
	</td>
</tr>
