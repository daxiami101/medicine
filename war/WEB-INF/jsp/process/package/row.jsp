<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td style="width: 60px">
		<input type="checkbox" name="ids" class="taiji_check_one" value='${vo.id }'/>
	</td>
	<td>${fn:escapeXml(vo.taskNo)}</td>
	<td>${fn:escapeXml(vo.medicineName)}</td>
	<td><fmt:formatDate value="${vo.packageDate.time}" pattern="yyyy-MM-dd"/></td>
	<td>${fn:escapeXml(vo.standard)}</td>
	<td>${fn:escapeXml(vo.weight)}</td>
	<td>${fn:escapeXml(vo.packageMaterial)}</td>
	<td>${fn:escapeXml(vo.name)}</td>
	<td>${fn:escapeXml(vo.level)}</td>
	<td>${fn:escapeXml(vo.place)}</td>
	<td><fmt:formatDate value="${vo.harvestDate.time}" pattern="yyyy-MM-dd"/></td>
	<td>${fn:escapeXml(vo.packageNo)}</td>
	<td>${fn:escapeXml(vo.produceCom)}</td>
	<td class="tdbtn">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/process/package/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			查看
			</a>
		</span>
<!-- 		<span class="btn-ios-view" style="text-align:left;padding:5px"> -->
<%-- 			<a href="${rootUrl}app/ncp/exam/examOrderAccept/view/${vo.id}" class="taiji_modal_lg taiji_acl"  > --%>
<!-- 			编辑 -->
<!-- 			</a> -->
<!-- 		</span> -->
<!-- 		<span class="btn-ios-delete" style="text-align:left;padding:5px;"> -->
<%-- 			<a href="${rootUrl}app/process/package/delete/${vo.id}" class="taiji_remove {confirm_message:'确认要删除吗？'} taiji_acl"  > --%>
<!-- 			删除 -->
<!-- 		</a> -->
<!-- 	</span> -->
	</td>
</tr>
