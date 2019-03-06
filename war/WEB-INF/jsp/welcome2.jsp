<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="https://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>

<div data-role="page" id="pageone">
  <div data-role="header">
    <h1>本草真源--溯源信息</h1>
  </div>
  
  <div data-role="main" class="ui-content">
    
<div data-role="header">
    <h1>农户信息</h1>
  </div>
    <table data-role="table" class="ui-responsive">
      <thead>
        <tr>
          <th>农户代码</th>
          <th>农户类型</th>
          <th>种植药材名称</th>
          <th>种植合同号</th>
          <th>面积（亩）</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${fn:escapeXml(farmerModel.farmerNo) }</td>
          <td>${fn:escapeXml(farmerModel.farmerType.value) }</td>
          <td>${fn:escapeXml(farmerModel.medicineName) }</td>
          <td>${fn:escapeXml(farmerModel.contractNum) }</td>
          <td>${fn:escapeXml(farmerModel.area) }</td>
        </tr>
      </tbody>
    </table>
<!--     第二段 -->
<div data-role="header">
    <h1>种子种苗处理</h1>
  </div>
    <table data-role="table" class="ui-responsive">
      <thead>
        <tr>
          <th>繁殖材料</th>
          <th>处理方式</th>
          <th>处理说明</th>
          <th>处理时间</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${fn:escapeXml(seedHandleModel.reproduceMaterial.value) }</td>
          <td>${fn:escapeXml(seedHandleModel.handleMethod) }</td>
          <td>${fn:escapeXml(seedHandleModel.handleNote) }</td>
          <td><fmt:formatDate value="${seedHandleModel.handleTime.time}" pattern="yyyy-MM-dd"/></td>
        </tr>
      </tbody>
    </table>
<!--     段落 -->
<div data-role="header">
    <h1>播种/移栽</h1>
  </div>
    <table data-role="table" class="ui-responsive">
      <thead>
        <tr>
          <th>播种/移栽方式</th>
          <th>播种量(kg/亩)</th>
          <th>移栽量</th>
          <th>时间</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${fn:escapeXml(sowTransplantModel.method.value) }</td>
          <td>${fn:escapeXml(sowTransplantModel.seedNum) }</td>
          <td>${fn:escapeXml(sowTransplantModel.plantNum) }</td>
          <td><fmt:formatDate value="${sowTransplantModel.plantTime.time}" pattern="yyyy-MM-dd"/></td>
        </tr>
      </tbody>
    </table>
<!-- 段落 -->
<div data-role="header">
    <h1>灌溉</h1>
  </div>
    <table data-role="table" class="ui-responsive">
      <thead>
        <tr>
          <th>药材名称</th>
          <th>灌溉方式</th>
          <th>灌溉开始时间</th>
          <th>灌溉结束时间</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${fn:escapeXml(irrigationModel.medicineName) }</td>
          <td>${fn:escapeXml(irrigationModel.method) }</td>
          <td><fmt:formatDate value="${irrigationModel.irrigationStartTime.time}" pattern="yyyy-MM-dd"/></td>
          <td><fmt:formatDate value="${irrigationModel.irrigationEndTime.time}" pattern="yyyy-MM-dd"/></td>
        </tr>
      </tbody>
    </table>
<!-- 段落 -->
<div data-role="header">
    <h1>肥料</h1>
  </div>
    <table data-role="table" class="ui-responsive">
      <thead>
        <tr>
          <th>肥料种类</th>
          <th>施肥开始时间</th>
          <th>施肥结束时间</th>
          <th>施肥方式</th>
          <th>施肥量/亩</th>
          <th>计量单位</th>
          <th>登记证号</th>
          <th>生产厂家</th>
          <th>生产日期</th>
          <th>采购日期</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${fn:escapeXml(fertilizationModel.fertilitizationKind) }</td>
          <td><fmt:formatDate value="${fertilizationModel.fertStartTime.time}" pattern="yyyy-MM-dd"/></td>
          <td><fmt:formatDate value="${fertilizationModel.fertEndTime.time}" pattern="yyyy-MM-dd"/></td>
          <td>${fn:escapeXml(fertilizationModel.fertMethod) }</td>
          <td>${fn:escapeXml(fertilizationModel.fertNum) }</td>
          <td>${fn:escapeXml(fertilizationModel.unit) }</td>
          <td>${fn:escapeXml(fertilizationModel.enrollNo) }</td>
          <td>${fn:escapeXml(fertilizationModel.produceCom) }</td>
          <td><fmt:formatDate value="${fertilizationModel.produceDate.time}" pattern="yyyy-MM-dd"/></td>
          <td><fmt:formatDate value="${fertilizationModel.purchaseDate.time}" pattern="yyyy-MM-dd"/></td>
        </tr>
      </tbody>
    </table>
<!-- 段落 -->
<div data-role="header">
    <h1>除草</h1>
  </div>
    <table data-role="table" class="ui-responsive">
      <thead>
        <tr>
          <th>主要杂草种类</th>
          <th>种植药材</th>
          <th>除草方式</th>
          <th>除草开始时间</th>
          <th>除草结束时间</th>
          <th>除草剂种类</th>
          <th>用药量/亩</th>
          <th>计量单位</th>
          <th>农药登记证号</th>
          <th>农药生产厂家</th>
          <th>农药生产日期</th>
          <th>农药采购日期</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${fn:escapeXml(weedModel.weedKind) }</td>
          <td>${fn:escapeXml(weedModel.medicineName) }</td>
          <td>${fn:escapeXml(weedModel.weedMethod) }</td>
          <td><fmt:formatDate value="${weedModel.weedStartTime.time}" pattern="yyyy-MM-dd"/></td>
          <td><fmt:formatDate value="${weedModel.weedEndTime.time}" pattern="yyyy-MM-dd"/></td>
          <td>${fn:escapeXml(weedModel.weedicide) }</td>
          <td>${fn:escapeXml(weedModel.num) }</td>
          <td>${fn:escapeXml(weedModel.unit) }</td>
          <td>${fn:escapeXml(weedModel.weedicideNo) }</td>
          <td>${fn:escapeXml(weedModel.weedCom) }</td>
          <td><fmt:formatDate value="${weedModel.produceDate.time}" pattern="yyyy-MM-dd"/></td>
          <td><fmt:formatDate value="${weedModel.purchaseDate.time}" pattern="yyyy-MM-dd"/></td>
        </tr>
      </tbody>
    </table>
<!-- 段落 -->
<div data-role="header">
    <h1>生长调节剂</h1>
  </div>
    <table data-role="table" class="ui-responsive">
      <thead>
        <tr>
          <th>生长调节剂种类</th>
          <th>种植药材</th>
          <th>施用开始时间</th>
          <th>施用结束时间 </th>
          <th>施用方式 </th>
          <th>用药量/亩 </th>
          <th>计量单位 </th>
          <th>登记证号 </th>
          <th>生产厂家 </th>
          <th>生产日期</th>
          <th>采购日期</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${fn:escapeXml(growthRegulatorModel.regulatorKind) }</td>
          <td>${fn:escapeXml(growthRegulatorModel.medicineName) }</td>
          <td><fmt:formatDate value="${growthRegulatorModel.useStartTime.time}" pattern="yyyy-MM-dd"/></td>
          <td><fmt:formatDate value="${growthRegulatorModel.useEndTime.time}" pattern="yyyy-MM-dd"/></td>
          <td>${fn:escapeXml(growthRegulatorModel.useMethod) }</td>
          <td>${fn:escapeXml(growthRegulatorModel.num) }</td>
          <td>${fn:escapeXml(growthRegulatorModel.unit) }</td>
          <td>${fn:escapeXml(growthRegulatorModel.enrollNo) }</td>
          <td>${fn:escapeXml(growthRegulatorModel.company) }</td>
          <td><fmt:formatDate value="${growthRegulatorModel.produceDate.time}" pattern="yyyy-MM-dd"/></td>
          <td><fmt:formatDate value="${growthRegulatorModel.purchaseDate.time}" pattern="yyyy-MM-dd"/></td>
        </tr>
      </tbody>
    </table>
<!-- 段落 -->
<div data-role="header">
    <h1>病害防治</h1>
  </div>
    <table data-role="table" class="ui-responsive">
      <thead>
        <tr>
          <th>主要病害种类</th>
          <th>危害部位</th>
          <th>农药种类</th>
          <th>防治开始时间</th>
          <th>防治结束时间</th>
          <th>防治方式</th>
          <th>用药量/亩</th>
          <th>计量单位</th>
          <th>农药登记证号</th>
          <th>生产厂家</th>
          <th>生产日期</th>
          <th>采购日期</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${fn:escapeXml(diseaseCtrlModel.diseaseKind) }</td>
          <td>${fn:escapeXml(diseaseCtrlModel.diseasePalce) }</td>
          <td>${fn:escapeXml(diseaseCtrlModel.drugKind) }</td>
          <td><fmt:formatDate value="${diseaseCtrlModel.preventStartTime.time}" pattern="yyyy-MM-dd"/></td>
          <td><fmt:formatDate value="${diseaseCtrlModel.preventEndTime.time}" pattern="yyyy-MM-dd"/></td>
          <td>${fn:escapeXml(diseaseCtrlModel.preventMethod) }</td>
          <td>${fn:escapeXml(diseaseCtrlModel.num) }</td>
          <td>${fn:escapeXml(diseaseCtrlModel.unit) }</td>
          <td>${fn:escapeXml(diseaseCtrlModel.enrollNo) }</td>
          <td>${fn:escapeXml(diseaseCtrlModel.company) }</td>
          <td><fmt:formatDate value="${diseaseCtrlModel.produceDate.time}" pattern="yyyy-MM-dd"/></td>
          <td><fmt:formatDate value="${diseaseCtrlModel.purchaseDate.time}" pattern="yyyy-MM-dd"/></td>
        </tr>
      </tbody>
    </table>
<!-- 段落 -->
<div data-role="header">
    <h1>虫害防治</h1>
  </div>
    <table data-role="table" class="ui-responsive">
      <thead>
        <tr>
          <th>主要虫害种类</th>
          <th>危害部位</th>
          <th>防治开始时间</th>
          <th>防治结束时间</th>
          <th>防治方式</th>
          <th>用药量/亩</th>
          <th>计量单位</th>
          <th>农药种类</th>
          <th>农药采购人员</th>
          <th>登记证号</th>
          <th>生产厂家</th>
          <th>生产日期</th>
          <th>采购日期</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${fn:escapeXml(pestCtrlModel.pestKind) }</td>
          <td>${fn:escapeXml(pestCtrlModel.pestPlace) }</td>
          <td><javatime:format value="${pestCtrlModel.preventStartTime}" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
          <td><javatime:format value="${pestCtrlModel.preventEndTime}" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
          <td>${fn:escapeXml(pestCtrlModel.preventMethod) }</td>
          <td>${fn:escapeXml(pestCtrlModel.num) }</td>
          <td>${fn:escapeXml(pestCtrlModel.unit) }</td>
          <td>${fn:escapeXml(pestCtrlModel.pesticideKind) }</td>
          <td>${fn:escapeXml(pestCtrlModel.purchasePersonName) }</td>
          <td>${fn:escapeXml(pestCtrlModel.enrollNo) }</td>
          <td>${fn:escapeXml(pestCtrlModel.company) }</td>
          <td><javatime:format value="${pestCtrlModel.produceDate}" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
          <td><javatime:format value="${pestCtrlModel.purchaseDate}" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
        </tr>
      </tbody>
    </table>
<!-- 段落 -->
<div data-role="header">
    <h1>其他处理</h1>
  </div>
    <table data-role="table" class="ui-responsive">
      <thead>
        <tr>
          <th>处理种类</th>
          <th>处理开始时间</th>
          <th>处理结束时间</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${fn:escapeXml(otherModel.operation) }</td>
          <td><fmt:formatDate value="${otherModel.operationStartTime.time}" pattern="yyyy-MM-dd"/></td>
          <td><fmt:formatDate value="${otherModel.operationEndTime.time}" pattern="yyyy-MM-dd"/></td>
        </tr>
      </tbody>
    </table>
<!-- 段落 -->
<div data-role="header">
    <h1>种源信息</h1>
  </div>
    <table data-role="table" class="ui-responsive">
      <thead>
        <tr>
         <th>种子批号</th>
          <th>药材名称</th>
          <th>拉丁名</th>
          <th>繁殖材料</th>
          <th>繁殖方式</th>
          <th>繁殖地点</th>
          <th>购销方式</th>
        </tr>
      </thead>
      <tbody>
        <tr>
         <td>${fn:escapeXml(seedModel.seedNo) }</td>
          <td>${fn:escapeXml(seedModel.medicineId) }</td>
          <td>${fn:escapeXml(seedModel.latinName) }</td>
          <td>${fn:escapeXml(seedModel.reproduceMaterial.value) }</td>
          <td>${fn:escapeXml(seedModel.reproduceMethod.value) }</td>
          <td>${fn:escapeXml(seedModel.reproducePlace.value) }</td>
          <td>${fn:escapeXml(seedModel.buySell.value) }</td>
        </tr>
      </tbody>
    </table>

<!-- 第三段 -->
<div data-role="header">
    <h1>包装信息</h1>
  </div>
    <table data-role="table" class="ui-responsive">
      <thead>
        <tr>
         <th>包装时间 </th>
          <th>包装规格</th>
          <th>包装重量</th>
          <th>包装材料</th>
          <th>品名</th>
          <th>等级</th>
          <th>产地</th>
          <th>采收日期</th>
          <th>包装批次号</th>
          <th>生产单位</th>
          <th>药材名称</th>
          <th>采收批号</th>
          <th>加工批号</th>
        </tr>
      </thead>
      <tbody>
        <tr>
         <td><fmt:formatDate value="${processPackageModel.packageDate.time}" pattern="yyyy-MM-dd"/></td>
          <td>${fn:escapeXml(processPackageModel.standard) }</td>
          <td>${fn:escapeXml(processPackageModel.weight) }</td>
          <td>${fn:escapeXml(processPackageModel.packageMaterial) }</td>
          <td>${fn:escapeXml(processPackageModel.name) }</td>
          <td>${fn:escapeXml(processPackageModel.level) }</td>
          <td>${fn:escapeXml(processPackageModel.place) }</td>
          <td><fmt:formatDate value="${processPackageModel.harvestDate.time}" pattern="yyyy-MM-dd"/></td>
          <td>${fn:escapeXml(processPackageModel.packageNo) }</td>
          <td>${fn:escapeXml(processPackageModel.produceCom) }</td>
          <td>${fn:escapeXml(processPackageModel.medicineName) }</td>
          <td>${fn:escapeXml(processPackageModel.harvestNo) }</td>
          <td>${fn:escapeXml(processPackageModel.processNo) }</td>
        </tr>
      </tbody>
    </table>
<!-- 段落 -->
<div>
 <div data-role="header">
    <h1>加工信息</h1>
  </div>
    <table data-role="table" class="ui-responsive">
      <thead>
        <tr>
         <th>加工等级</th>
          <th>加工方法</th>
          <th>加工前重量(kg)</th>
          <th>非药用部位重量(kg)</th>
          <th>加工后总重量(kg)</th>
          <th>采收批号</th>
          <th>药材名称</th>
          <th>种植批号</th>
        </tr>
      </thead>
      <tbody>
        <tr>
         <td>${fn:escapeXml(processModel.processLevel) }</td>
          <td>${fn:escapeXml(processModel.processMethod) }</td>
          <td>${fn:escapeXml(processModel.preProcessQuality) }</td>
          <td>${fn:escapeXml(processModel.nonMediQuality) }</td>
          <td>${fn:escapeXml(processModel.postProcessQuality) }</td>
          <td>${fn:escapeXml(processModel.harvestNo) }</td>
          <td>${fn:escapeXml(processModel.medicineName) }</td>
          <td>${fn:escapeXml(processModel.taskNo) }</td>
        </tr>
      </tbody>
    </table>
<!-- 段落 -->
 <div data-role="header">
    <h1>采收信息</h1>
  </div>
    <table data-role="table" class="ui-responsive">
      <thead>
        <tr>
          <th>采收部位</th>
          <th>采收药材</th>
          <th>采收批号</th>
          <th>采收时间</th>
          <th>采收方式</th>
          <th>采收面积(亩)</th>
          <th>采收产量</th>
          <th>药材名称</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${fn:escapeXml(harvestModel.harvestPart) }</td>
          <td>${fn:escapeXml(harvestModel.harvestMedicine) }</td>
          <td>${fn:escapeXml(harvestModel.harvestNo) }</td>
          <td><fmt:formatDate value="${harvestModel.harvestTime.time}" pattern="yyyy-MM-dd"/></td>
          <td>${fn:escapeXml(harvestModel.harvestMethod) }</td>
          <td>${fn:escapeXml(harvestModel.area) }</td>
          <td>${fn:escapeXml(harvestModel.production) }</td>
          <td>${fn:escapeXml(harvestModel.medicineName) }</td>
        </tr>
      </tbody>
    </table>
<!--     段落 -->
 <div data-role="header">
    <h1>销售信息</h1>
  </div>
    <table data-role="table" class="ui-responsive">
      <thead>
        <tr>
          <th>储存仓库代码</th>
          <th>储存条件</th>
          <th>贮藏方式</th>
          <th>存储措施</th>
          <th>药材名称</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${fn:escapeXml(storeModel.storeCode) }</td>
          <td>${fn:escapeXml(storeModel.storeCondition) }</td>
          <td>${fn:escapeXml(storeModel.storeMethod) }</td>
          <td>${fn:escapeXml(storeModel.measure) }</td>
          <td>${fn:escapeXml(storeModel.medicineName) }</td>
        </tr>
      </tbody>
    </table>
<!--     段落 -->
     <div data-role="header">
    <h1>运输信息</h1>
  </div>
    <table data-role="table" class="ui-responsive">
      <thead>
        <tr>
          <th>药材名称</th>
          <th>运输方式</th>
          <th>安保措施</th>
          <th>安保合同号</th>
          <th>饮片编码</th>
          <th>随货单据号</th>
          <th>销售时间</th>
          <th>运输时间</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${fn:escapeXml(model.medicineName) }</td>
          <td>${fn:escapeXml(model.transMethod) }</td>
          <td>${fn:escapeXml(model.securityMeasure) }</td>
          <td>${fn:escapeXml(model.securityContractCode) }</td>
          <td>${fn:escapeXml(model.medicineCode) }</td>
          <td>${fn:escapeXml(model.companyOrderNo) }</td>
          <td><fmt:formatDate value="${model.purchaseDate.time}" pattern="yyyy-MM-dd"/></td>
          <td><fmt:formatDate value="${model.transportTime.time}" pattern="yyyy-MM-dd"/></td>
        </tr>
      </tbody>
    </table>
    
    </div>
  </div>
  <div data-role="footer">
    <h1>河南辅仁堂制药有限公司 <span> 制</span></h1>
  </div>
</div> 

</body>
</html>
