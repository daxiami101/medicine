<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td style="width: 60px">
		<input type="checkbox" name="ids" class="taiji_check_one" value='${vo.id }'/>
	</td>
	<td>${fn:escapeXml(vo.materialName)}</td>
	<td>${fn:escapeXml(vo.originalPlace)}</td>
	<td>${fn:escapeXml(vo.productionNo)}</td>
	<td>${fn:escapeXml(vo.standard)}</td>
	<td>${fn:escapeXml(vo.leftNum)}</td>
	<td>${fn:escapeXml(vo.unit)}</td>
	<td>${fn:escapeXml(vo.soldNum)}</td>
	<td><fmt:formatDate value="${vo.soldTime.time}" pattern="yyyy-MM-dd"/></td>
	<td>${fn:escapeXml(vo.storeMethod)}</td>
	<td>${fn:escapeXml(vo.companyOrderId)}</td>
	<td>${fn:escapeXml(vo.customServiceName)}</td>
	<td>${fn:escapeXml(vo.returnStatus)}</td>
	<td class="tdbtn"  style="width:190px">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/tablet/saleOut/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			查看
			</a>
		</span>
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/tablet/saleOut/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			编辑
			</a>
		</span>
		<span class="btn-ios-delete" style="text-align:left;padding:5px;">
			<a href="${rootUrl}app/tablet/saleOut/delete/${vo.id}" class="taiji_remove {confirm_message:'确认要删除吗？'} taiji_acl"  >
			删除
		</a>
	</span>
	<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/tablet/saleOut/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
		二维码
			</a>
		</span>
	</td>
</tr>
