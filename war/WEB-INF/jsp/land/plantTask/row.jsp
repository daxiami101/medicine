<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td style="width: 60px">
		<input type="checkbox" name="ids" class="taiji_check_one" value='${vo.id }'/>
	</td>
	<td>${fn:escapeXml(vo.taskNo)}</td>
	<td>${fn:escapeXml(vo.medicineName)}</td>
	<td>${fn:escapeXml(vo.plantMethod.value)}</td>
	<td>
	<fmt:formatDate value="${vo.startPlantTime.time}" pattern="yyyy-MM-dd"/>到
	<fmt:formatDate value="${vo.endPlantTime.time}" pattern="yyyy-MM-dd"/>
	</td>
	<td>${fn:escapeXml(vo.area)}</td>
	<td>${fn:escapeXml(vo.originalPalce)}</td>
	<td>
		<c:if test="${fn:length(vo.farmerId)<1}">
			<span class="btn-ios-view" style="text-align:left;padding:5px">
				<a href="${rootUrl}app/land/plantTask/farmerSelect/chooseFarmer/${vo.id}" class="taiji_modal_lg taiji_acl"  >
				配置农户
				</a>
			</span>
		</c:if>
		<c:if test="${fn:length(vo.farmerId)>=1}">
			<span class="btn-ios-view" style="text-align:left;padding:5px">
				已配置农户
			</span>
		</c:if>
	</td>
	<td class="tdbtn">
		<c:if test="${vo.farmerId!=null }">
		<span class="btn-ios-view" style="text-align:left;padding:5px">
			<a href="${rootUrl}app/land/plantTask/view/${vo.id}" class="taiji_modal_lg taiji_acl"  >
			查看
			</a>
		</span>
		</c:if>
		
		<span class="btn-ios-view" style="text-align:left;padding:5px;">
	</span>
<!-- 		<span class="btn-ios-view" style="text-align:left;padding:5px"> -->
<%-- 			<a href="${rootUrl}app/land/plantTask/view/${vo.id}" class="taiji_modal_lg taiji_acl"  > --%>
<!-- 			编辑 -->
<!-- 			</a> -->
<!-- 		</span> -->
		<span class="btn-ios-delete" style="text-align:left;padding:5px;">
			<a href="${rootUrl}app/land/plantTask/delete/${vo.id}" class="taiji_remove {confirm_message:'确认要删除吗？'} taiji_acl"  >
			删除
		</a>
	</span>
	</td>
</tr>
