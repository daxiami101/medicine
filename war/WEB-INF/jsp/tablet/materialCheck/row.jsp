<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td style="width: 60px">
		<input type="checkbox" name="ids" class="taiji_check_one" value='${vo.id }'/>
	</td>
	<td>${fn:escapeXml(vo.materialKind)}</td>
	<td>${fn:escapeXml(vo.medicineCode)}</td>
	<td>${fn:escapeXml(vo.province)}</td>
	<td>${fn:escapeXml(vo.level)}</td>
	<td>${fn:escapeXml(vo.standard)}</td>
	<td>${fn:escapeXml(vo.orderId)}</td>
	<td>${fn:escapeXml(vo.unit)}</td>
	<td>${fn:escapeXml(vo.purchaseNum)}</td>
	<td>${fn:escapeXml(vo.companyOrderId)}</td>
	<td><fmt:formatDate value="${vo.purchaseTime.time}" pattern="yyyy-MM-dd"/></td>
	<td>${fn:escapeXml(vo.materialStatus.value)}</td>
	<td class="tdbtn">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/tablet/purchase/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			查看
			</a>
		</span>
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/tablet/purchase/edit/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			编辑
			</a>
		</span>
		<span class="btn-ios-delete" style="text-align:left;padding:5px;">
			<a href="${rootUrl}app/tablet/purchase/delete/${vo.id}" class="taiji_remove {confirm_message:'确认要删除吗？'} taiji_acl"  >
			删除
		</a>
	</span>
	</td>
</tr>
