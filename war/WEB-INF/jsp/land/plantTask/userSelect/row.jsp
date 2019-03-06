<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
<!-- 	<td style="width: 60px"> -->
<%-- 		<input type="checkbox" name="ids" class="taiji_check_one" value='${vo.id }'/> --%>
<!-- 	</td> -->
	<td>${fn:escapeXml(vo.medicineId)}</td>
	<td>${fn:escapeXml(vo.latinName)}</td>
	<td>${fn:escapeXml(vo.reproduceMaterial.value)}</td>
	<td>${fn:escapeXml(vo.reproduceMethod.value)}</td>
	<td>${fn:escapeXml(vo.reproducePlace.value)}</td>
	<td>${fn:escapeXml(vo.buySell.value)}</td>
	<td class="tdbtn">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/medicine/seed/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			查看
			</a>
		</span>
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/land/plantTask/userSelect/createTask/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			创建种植任务
			</a>
		</span>
<!-- 		<span class="btn-ios-delete" style="text-align:left;padding:5px;"> -->
<%-- 			<a href="${rootUrl}app/medicine/seed/delete/${vo.id}" class="taiji_remove {confirm_message:'确认要删除吗？'} taiji_acl"  > --%>
<!-- 			删除 -->
<!-- 		</a> -->
	</span>
	</td>
</tr>
