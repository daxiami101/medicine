<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td style="width: 60px">
		<input type="checkbox" name="ids" class="taiji_check_one" value='${vo.id }'/>
	</td>
	<td>${fn:escapeXml(vo.taskNo)}</td>
	<td>${fn:escapeXml(vo.medicineName)}</td>
	<td><fmt:formatDate value="${vo.irrigationStartTime.time}" pattern="yyyy-MM-dd"/></td>
	<td><fmt:formatDate value="${vo.irrigationEndTime.time}" pattern="yyyy-MM-dd"/></td>
	<td>${fn:escapeXml(vo.method)}</td>
	<td class="tdbtn">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/land/irrigation/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			查看
			</a>
		</span>
<!-- 		<span class="btn-ios-view" style="text-align:left;padding:5px"> -->
<%-- 			<a href="${rootUrl}app/land/irrigation/edit/${vo.id}" class="taiji_modal_lg taiji_acl"  > --%>
<!-- 			编辑 -->
<!-- 			</a> -->
<!-- 		</span> -->
<!-- 		<span class="btn-ios-delete" style="text-align:left;padding:5px;"> -->
<%-- 			<a href="${rootUrl}app/land/irrigation/delete/${vo.id}" class="taiji_remove {confirm_message:'确认要删除吗？'} taiji_acl"  > --%>
<!-- 			删除 -->
<!-- 		</a> -->
	</span>
	</td>
</tr>
