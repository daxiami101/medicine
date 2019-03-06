<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td style="width: 60px">
		<input type="checkbox" name="ids" class="taiji_check_one" value='${vo.id }'/>
	</td>
	<td>${fn:escapeXml(vo.harvestNo)}</td>
	<td>${fn:escapeXml(vo.processNo)}</td>
	<td><fmt:formatDate value="${vo.processTime.time}" pattern="yyyy-MM-dd"/></td>
	<td>${fn:escapeXml(vo.processLevel)}</td>
	<td>${fn:escapeXml(vo.processMethod)}</td>
	<td>${fn:escapeXml(vo.preProcessQuality)}</td>
	<td>${fn:escapeXml(vo.nonMediQuality)}</td>
	<td>${fn:escapeXml(vo.postProcessQuality)}</td>
	<td class="tdbtn">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/process/processWork/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			查看
			</a>
		</span>
<!-- 		<span class="btn-ios-view" style="text-align:left;padding:5px"> -->
<%-- 			<a href="${rootUrl}app/process/processWork/edit/${vo.id}" class="taiji_modal_lg taiji_acl"  > --%>
<!-- 			编辑 -->
<!-- 			</a> -->
<!-- 		</span> -->
<!-- 		<span class="btn-ios-delete" style="text-align:left;padding:5px;"> -->
<%-- 			<a href="${rootUrl}app/process/processWork/delete/${vo.id}" class="taiji_remove {confirm_message:'确认要删除吗？'} taiji_acl"  > --%>
<!-- 			删除 -->
<!-- 		</a> -->
<!-- 	</span> -->
	</td>
</tr>
