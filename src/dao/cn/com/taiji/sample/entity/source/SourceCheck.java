package cn.com.taiji.sample.entity.source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 种源管理
 */
@Entity
@Table(name = "source_check")
public class SourceCheck extends AbstractInsertTimeEntity
{
	private String materialName;//物料名称
	private String originalPlace;//产地
	private String companyOrderId;//货单单据号
	
	private String purchaseNo;//*物料批号
	private String unit;//计量单位
	private Double num;
	

	private String soldId;//销售id
	private String processId;//*加工批号
	private String harvestId;//采收id
	private String farmerId;//农户id
	private String seedId;//种子id
	private String processPackageId;//包装id
	private String plantTaskId;//种植任务idpn
	private String storeId;//存储id
	private String transportId;//运输id
	private String purchaseId;//*采购id
	
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
	@Column(name="purchase_Id",nullable = false, length = 100)
	public String getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	@Column(name="purchase_No",nullable = false, length = 100)
	public String getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	@Column(name="material_Name",nullable = false, length = 100)
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	@Column(name="original_Place",nullable = false, length = 100)
	public String getOriginalPlace() {
		return originalPlace;
	}
	public void setOriginalPlace(String originalPlace) {
		this.originalPlace = originalPlace;
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
	@Column(name="num",nullable = false, length = 100)
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}//数量(个)
}
