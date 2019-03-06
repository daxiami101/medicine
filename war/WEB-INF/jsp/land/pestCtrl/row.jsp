<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td style="width: 60px">
		<input type="checkbox" name="ids" class="taiji_check_one" value='${vo.id }'/>
	</td>
	<td>${fn:escapeXml(vo.taskNo)}</td>
	<td>${fn:escapeXml(vo.medicineName)}</td>
	<td>${fn:escapeXml(vo.pestKind)}</td>
	<td>${fn:escapeXml(vo.pestPlace)}</td>
	<td><javatime:format value="${vo.preventStartTime}" pattern="yyyy-MM-dd HH:mm:ss"  />	</td>
	<td><javatime:format value="${vo.preventEndTime}" pattern="yyyy-MM-dd HH:mm:ss"  />	</td>
	<td>${fn:escapeXml(vo.preventMethod)}</td>
	<td>${fn:escapeXml(vo.pesticideKind)}</td>
	<td>${fn:escapeXml(vo.num)}</td>
	<td>${fn:escapeXml(vo.purchasePersonName)}</td>
	<td class="tdbtn">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/land/pestCtrl/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			查看
			</a>
		</span>
<!-- 		<span class="btn-ios-view" style="text-align:left;padding:5px"> -->
<%-- 			<a href="${rootUrl}app/land/pestCtrl/edit/${vo.id}" class="taiji_modal_lg taiji_acl"  > --%>
<!-- 			编辑 -->
<!-- 			</a> -->
<!-- 		</span> -->
<!-- 		<span class="btn-ios-delete" style="text-align:left;padding:5px;"> -->
<%-- 			<a href="${rootUrl}app/land/pestCtrl/delete/${vo.id}" class="taiji_remove {confirm_message:'确认要删除吗？'} taiji_acl"  > --%>
<!-- 			删除 -->
<!-- 		</a> -->
<!-- 	</span> -->
	</td>
</tr>
