<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td style="width: 60px">
		<input type="checkbox" name="ids" class="taiji_check_one" value='${vo.id }'/>
	</td>
	<td>${fn:escapeXml(vo.materialName)}</td>
	<td>${fn:escapeXml(vo.originalPlace)}</td>
	<td>${fn:escapeXml(vo.medicineNo)}</td>
	<td>${fn:escapeXml(vo.standard)}</td>
	<td>${fn:escapeXml(vo.leftNum)}</td>
	<td>${fn:escapeXml(vo.executeStandard)}</td>
	<td>${fn:escapeXml(vo.productionName)}</td>
	<td>${fn:escapeXml(vo.medicineCode)}</td>
	<td>${fn:escapeXml(vo.manufactureMethod)}</td>
	<td>${fn:escapeXml(vo.processStandard)}</td>
	<td>${fn:escapeXml(vo.unit)}</td>
	<td>${fn:escapeXml(vo.produceId)}</td>
	<td>${fn:escapeXml(vo.amount)}</td>
	<td><javatime:format value="${vo.produceTime}" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
	<td>${fn:escapeXml(vo.productionNum)}</td>
	<td class="tdbtn">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/tablet/produce/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			查看
			</a>
		</span>
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/tablet/produce/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			编辑
			</a>
		</span>
		<span class="btn-ios-delete" style="text-align:left;padding:5px;">
			<a href="${rootUrl}app/tablet/produce/delete/${vo.id}" class="taiji_remove {confirm_message:'确认要删除吗？'} taiji_acl"  >
			删除
		</a>
	</span>
	</td>
</tr>
