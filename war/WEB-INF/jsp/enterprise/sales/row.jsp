<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td style="width: 60px">
		<input type="checkbox" name="ids" class="taiji_check_one" value='${vo.id }'/>
	</td>
	<td>${fn:escapeXml(vo.medicineName)}</td>
	<td>${fn:escapeXml(vo.processId)}</td>
	<td>${fn:escapeXml(vo.medicineCode)}</td>
	<td>${fn:escapeXml(vo.commerceDepartCode)}</td>
	<td>${fn:escapeXml(vo.clientName)}</td>
	<td><javatime:format value="${vo.soldTime}" pattern="yyyy-MM-dd HH:mm:ss"  />	</td>
	<td>${fn:escapeXml(vo.soldNum)}</td>
	<td>${fn:escapeXml(vo.barcode)}</td>
	<td>${fn:escapeXml(vo.taiwanCode)}</td>
	<td>${fn:escapeXml(vo.customCode)}</td>
	<td>${fn:escapeXml(vo.companyOrderId)}</td>
	<td></td>
	<td class="tdbtn">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/medicine/sales/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			查看
			</a>
		</span>
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/medicine/sales/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			编辑
			</a>
		</span>
		<span class="btn-ios-delete" style="text-align:left;padding:5px;">
			<a href="${rootUrl}app/medicine/sales/delete/${vo.id}" class="taiji_remove {confirm_message:'确认要删除吗？'} taiji_acl"  >
			删除
		</a>
	</span>
	</td>
</tr>
