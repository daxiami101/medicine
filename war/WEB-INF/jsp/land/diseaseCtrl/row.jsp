<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td style="width: 60px">
		<input type="checkbox" name="ids" class="taiji_check_one" value='${vo.id }'/>
	</td>
	<td>${fn:escapeXml(vo.taskNo)}</td>
	<td>${fn:escapeXml(vo.medicineName)}</td>
	<td>${fn:escapeXml(vo.diseaseKind)}</td>
	<td>${fn:escapeXml(vo.diseasePalce)}</td>
	<td><fmt:formatDate value="${vo.preventStartTime.time}" pattern="yyyy-MM-dd"/></td>
	<td><fmt:formatDate value="${vo.preventEndTime.time}" pattern="yyyy-MM-dd"/></td>
	<td>${fn:escapeXml(vo.preventMethod)}</td>
	<td>${fn:escapeXml(vo.drugKind)}</td>
	<td>${fn:escapeXml(vo.num)}</td>
	<td class="tdbtn">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/land/diseaseCtrl/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			查看
			</a>
		</span>
<!-- 		<span class="btn-ios-view" style="text-align:left;padding:5px"> -->
<%-- 			<a href="${rootUrl}app/land/diseaseCtrl/edit/${vo.id}" class="taiji_modal_lg taiji_acl"  > --%>
<!-- 			编辑 -->
<!-- 			</a> -->
<!-- 		</span> -->
<!-- 		<span class="btn-ios-delete" style="text-align:left;padding:5px;"> -->
<%-- 			<a href="${rootUrl}app/land/diseaseCtrl/delete/${vo.id}" class="taiji_remove {confirm_message:'确认要删除吗？'} taiji_acl"  > --%>
<!-- 			删除 -->
<!-- 		</a> -->
<!-- 	</span> -->
	</td>
</tr>
