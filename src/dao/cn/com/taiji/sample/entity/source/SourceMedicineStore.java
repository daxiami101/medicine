package cn.com.taiji.sample.entity.source;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 药材入库
 */
@Entity
@Table(name = "source_Medicine_store")
public class SourceMedicineStore extends AbstractInsertTimeEntity
{
	private String materialName;//物料名称
	private String originalPlace;//产地
	private String materialId;//物料批号
	private String unit;//计量单位
	private String num;//数量(个)
	private String standard;//包装规格
	private String storeMethod;//贮藏方式
	private LocalDateTime storeTime ;
	
	private String soldId;//销售id
	private String processId;//*加工批号
	private String harvestId;//采收id
	private String farmerId;//农户id
	private String seedId;//农户id
	private String processPackageId;//包装id
	private String plantTaskId;//种植任务idpn
	private String storeId;//存储id
	private String transportId;//运输id
	private String purchaseId;//采购id
	
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
	@Column(name="material_Id",nullable = false, length = 100)
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	@Column(name="unit",nullable = false, length = 100)
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name="num",nullable = false, length = 100)
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	@Column(name="standard",nullable = false, length = 100)
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	@Column(name="store_Method",nullable = false, length = 100)
	public String getStoreMethod() {
		return storeMethod;
	}
	public void setStoreMethod(String storeMethod) {
		this.storeMethod = storeMethod;
	}
	@Column(name="store_Time",nullable = false, length = 100)
	public LocalDateTime getStoreTime() {
		return storeTime;
	}
	public void setStoreTime(LocalDateTime storeTime) {
		this.storeTime = storeTime;
	}//入库时间
	
	
}
