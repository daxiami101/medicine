package cn.com.taiji.sample.entity.source;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.dict.source.BuySell;
import cn.com.taiji.sample.entity.dict.source.MaterialStatus;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 采购管理
 */
@Entity
@Table(name = "source_purchase")
public class SourcePurchase extends AbstractInsertTimeEntity
{
		private String materialKind;//原料名称
		private String materialName;//*物料名称
		private String medicineCode;//药材编码
		private String province;//*产地
		private String city;//-
		private String county;//-
		private String level;//等级
		private String standard;//规格
		private String orderId;//到货单据号
		private String companyOrderId;//随货同行单据号
		private String unit;//计量单位
		private String purchaseNum;//采购数量(个)
		private Calendar purchaseTime ;//*采收日期
		private String providerName;//供货商
		private String purchaseNo;//采购批号
		private MaterialStatus materialStatus=MaterialStatus.PURCHASE;//物料状态
		private BuySell buySell;//数据源
		private String dataSource;//
		
		private String soldId;//销售id
		private String processId;//*加工批号
		private String harvestId;//采收id
		private String farmerId;//农户id
		private String seedId;//农户id
		private String processPackageId;//包装id
		private String plantTaskId;//种植任务idpn
		private String storeId;//存储id
		private String transportId;//运输id
		@Column(name="sold_Id",nullable = false, length = 100)
		public String getSoldId() {
			return soldId;
		}
		public void setSoldId(String soldId) {
			this.soldId = soldId;
		}
		@Column(name="process_Id",nullable = false, length = 100)
		public String getProcessId() {
			return processId;
		}
		public void setProcessId(String processId) {
			this.processId = processId;
		}
		@Column(name="harvest_Id",nullable = false, length = 100)
		public String getHarvestId() {
			return harvestId;
		}
		public void setHarvestId(String harvestId) {
			this.harvestId = harvestId;
		}
		@Column(name="farmer_Id",nullable = false, length = 100)
		public String getFarmerId() {
			return farmerId;
		}
		public void setFarmerId(String farmerId) {
			this.farmerId = farmerId;
		}
		@Column(name="seed_Id",nullable = false, length = 100)
		public String getSeedId() {
			return seedId;
		}
		public void setSeedId(String seedId) {
			this.seedId = seedId;
		}
		@Column(name="process_Package_Id",nullable = false, length = 100)
		public String getProcessPackageId() {
			return processPackageId;
		}
		public void setProcessPackageId(String processPackageId) {
			this.processPackageId = processPackageId;
		}
		@Column(name="plant_Task_Id",nullable = false, length = 100)
		public String getPlantTaskId() {
			return plantTaskId;
		}
		public void setPlantTaskId(String plantTaskId) {
			this.plantTaskId = plantTaskId;
		}
		@Column(name="store_Id",nullable = false, length = 100)
		public String getStoreId() {
			return storeId;
		}
		public void setStoreId(String storeId) {
			this.storeId = storeId;
		}
		@Column(name="transport_Id",nullable = false, length = 100)
		public String getTransportId() {
			return transportId;
		}
		public void setTransportId(String transportId) {
			this.transportId = transportId;
		}
		@Column(name="data_Source",nullable = false, length = 100)
		public String getDataSource() {
			return dataSource;
		}
		public void setDataSource(String dataSource) {
			this.dataSource = dataSource;
		}
		@Enumerated(EnumType.STRING)
		@Column(name="buy_Sell",nullable = false, length = 100)
		public BuySell getBuySell() {
			return buySell;
		}
		public void setBuySell(BuySell buySell) {
			this.buySell = buySell;
		}
		@Enumerated(EnumType.STRING)
		@Column(name="material_Status",nullable = false, length = 100)
		public MaterialStatus getMaterialStatus() {
			return materialStatus;
		}
		public void setMaterialStatus(MaterialStatus materialStatus) {
			this.materialStatus = materialStatus;
		}
		@Column(name="purchase_No",nullable = false, length = 100)
		public String getPurchaseNo() {
			return purchaseNo;
		}
		public void setPurchaseNo(String purchaseNo) {
			this.purchaseNo = purchaseNo;
		}
		@Column(name="material_Kind",nullable = false, length = 100)
		public String getMaterialKind() {
			return materialKind;
		}
		public void setMaterialKind(String materialKind) {
			this.materialKind = materialKind;
		}
		@Column(name="material_Name",nullable = false, length = 100)
		public String getMaterialName() {
			return materialName;
		}
		public void setMaterialName(String materialName) {
			this.materialName = materialName;
		}
		@Column(name="medicine_Code",nullable = false, length = 100)
		public String getMedicineCode() {
			return medicineCode;
		}
		public void setMedicineCode(String medicineCode) {
			this.medicineCode = medicineCode;
		}
		@Column(name="province",nullable = false, length = 100)
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		@Column(name="city",nullable = false, length = 100)
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		@Column(name="county",nullable = false, length = 100)
		public String getCounty() {
			return county;
		}
		public void setCounty(String county) {
			this.county = county;
		}
		@Column(name="level",nullable = false, length = 100)
		public String getLevel() {
			return level;
		}
		public void setLevel(String level) {
			this.level = level;
		}
		@Column(name="standard",nullable = false, length = 100)
		public String getStandard() {
			return standard;
		}
		public void setStandard(String standard) {
			this.standard = standard;
		}
		@Column(name="order_Id",nullable = false, length = 100)
		public String getOrderId() {
			return orderId;
		}
		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}
		@Column(name="company_Order_Id",nullable = false, length = 100)
		public String getCompanyOrderId() {
			return companyOrderId;
		}
		public void setCompanyOrderId(String companyOrderId) {
			this.companyOrderId = companyOrderId;
		}
		@Column(name="unit",nullable = false, length = 100)
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		@Column(name="purchase_Num",nullable = false, length = 100)
		public String getPurchaseNum() {
			return purchaseNum;
		}
		public void setPurchaseNum(String purchaseNum) {
			this.purchaseNum = purchaseNum;
		}
		@Column(name="purchase_Time",nullable = false, length = 100)
		public Calendar getPurchaseTime() {
			return purchaseTime;
		}
		public void setPurchaseTime(Calendar purchaseTime) {
			this.purchaseTime = purchaseTime;
		}
		@Column(name="provider_Name",nullable = false, length = 100)
		public String getProviderName() {
			return providerName;
		}
		public void setProviderName(String providerName) {
			this.providerName = providerName;
		}//供应商
}
