<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td style="width: 60px">
		<input type="checkbox" name="ids" class="taiji_check_one" value='${vo.id }'/>
	</td>
	<td>${fn:escapeXml(vo.taskNo)}</td>
	<td>${fn:escapeXml(vo.medicineName)}</td>
	<td>${fn:escapeXml(vo.reproduceMaterial.value)}</td>
	<td>${fn:escapeXml(vo.handleMethod)}</td>
	<td>${fn:escapeXml(vo.handleNote)}</td>
	<td>
	<fmt:formatDate value="${vo.handleTime.time}" pattern="yyyy-MM-dd"/>
	</td>
	<td class="tdbtn">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/land/seedHandle/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			查看
			</a>
		</span>
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/land/seedHandle/edit/${vo.id}" class="taiji_modal_lg"  >
			编辑
			</a>
		</span>
	</td>
</tr>
