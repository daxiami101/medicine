<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
<!-- 	<td style="width: 60px"> -->
<%-- 		<input type="radio" name="ids" class="taiji_check_one" value='${vo.id }'/> --%>
<!-- 	</td> -->
	<td>${fn:escapeXml(vo.taskNo)}</td>
	<td>${fn:escapeXml(vo.medicineName)}</td>
	<td>${fn:escapeXml(vo.plantMethod)}</td>
	<td><fmt:formatDate value="${vo.startPlantTime.time}" pattern="yyyy-MM-dd"/></td>
	<td><fmt:formatDate value="${vo.endPlantTime.time}" pattern="yyyy-MM-dd"/></td>
	<td>${fn:escapeXml(vo.area)}</td>
	<td class="tdbtn">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/medicine/farmer/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			查看
			</a>
		</span>
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/land/growthRegulator/taskSelect/createTask/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			创建任务
			</a>
		</span>
	</td>
</tr>
