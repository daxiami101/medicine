package cn.com.taiji.sample.entity.source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.dict.source.BuySell;
@Entity
@Table(name = "source_Store")
public class SourceStore extends AbstractInsertTimeEntity
{
	private String plantTaskId;//种植任务id
	private String harvestId;//采收id		
	private String storeCode ;//储存仓库代码 	
	private String storeCondition;//储存条件 	
	private String storeMethod;//贮藏方式 	
	private String measure;//存储措施
	private String taskNo;
	private BuySell buySell;//数据源
	private String medicineName;//药材名称
	private String processId;//加工id
	private String packageNo;//
	private String storeNo;//存储批号
	private String farmerId;//农户id
	private String seedId;//农户id
	private String processPackageId;//包装id
	@Column(name="process_Package_Id",nullable = false, length = 100)
	public String getProcessPackageId() {
		return processPackageId;
	}
	public void setProcessPackageId(String processPackageId) {
		this.processPackageId = processPackageId;
	}
	@Column(name="seed_Id",nullable = false, length = 100)
	public String getSeedId() {
		return seedId;
	}
	public void setSeedId(String seedId) {
		this.seedId = seedId;
	}
	@Column(name="farmer_Id",nullable = false, length = 100)
	public String getFarmerId() {
		return farmerId;
	}
	public void setFarmerId(String farmerId) {
		this.farmerId = farmerId;
	}
	@Column(name="store_No",nullable = false, length = 100)
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	@Column(name="package_No",nullable = false, length = 100)
	public String getPackageNo() {
		return packageNo;
	}
	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}
	@Column(name="process_Id",nullable = false, length = 100)
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	@Column(name="medicine_Name",nullable = false, length = 100)
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="buy_Sell",nullable = false, length = 100)
	public BuySell getBuySell() {
		return buySell;
	}
	public void setBuySell(BuySell buySell) {
		this.buySell = buySell;
	}
	@Column(name="task_No",nullable = false, length = 100)
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	@Column(name="plant_Task_Id",nullable = false, length = 100)
	public String getPlantTaskId() {
		return plantTaskId;
	}
	public void setPlantTaskId(String plantTaskId) {
		this.plantTaskId = plantTaskId;
	}
	@Column(name="harvest_Id",nullable = false, length = 100)
	public String getHarvestId() {
		return harvestId;
	}
	public void setHarvestId(String harvestId) {
		this.harvestId = harvestId;
	}
	@Column(name="store_Code",nullable = false, length = 100)
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	@Column(name="store_Condition",nullable = false, length = 100)
	public String getStoreCondition() {
		return storeCondition;
	}
	public void setStoreCondition(String storeCondition) {
		this.storeCondition = storeCondition;
	}
	@Column(name="store_Method",nullable = false, length = 100)
	public String getStoreMethod() {
		return storeMethod;
	}
	public void setStoreMethod(String storeMethod) {
		this.storeMethod = storeMethod;
	}
	@Column(name="measure",nullable = false, length = 100)
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}//养护措施
//	
	
	
}
