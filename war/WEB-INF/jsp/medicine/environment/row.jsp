<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td style="width: 60px">
		<input type="checkbox" name="ids" class="taiji_check_one" value='${vo.id }'/>
	</td>
	<td>${fn:escapeXml(vo.year)}</td>
	<td>${fn:escapeXml(vo.location)}</td>
	<td>${fn:escapeXml(vo.soilType)}</td>
	<td>${fn:escapeXml(vo.soilTexture.value)}</td>
	<td>${fn:escapeXml(vo.waterType)}</td>
	<td>${fn:escapeXml(vo.ph)}</td>
	<td>${fn:escapeXml(vo.annualPrecipitation)}</td>
	<td>${fn:escapeXml(vo.frostlessDay)}</td>
	<td>${fn:escapeXml(vo.averageTemperature)}</td>
	<td class="tdbtn">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/medicine/environment/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			查看
			</a>
		</span>
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/medicine/environment/edit/${vo.id}" class="taiji_modal taiji_acl"  >
			编辑
			</a>
		</span>
		<span class="btn-ios-delete" style="text-align:left;padding:5px;">
			<a href="${rootUrl}app/medicine/environment/delete/${vo.id}" class="taiji_remove {confirm_message:'确认要删除吗？'} taiji_acl"  >
			删除
		</a>
	</span>
	</td>
</tr>
