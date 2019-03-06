package cn.com.taiji.sample.entity.source;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 加工管理--包装
 * @author admin02
 * 2018年10月28日 下午9:34:33
 * TODO
 */
@Entity
@Table(name = "source_process_package")
public class SourceProcessPackage extends AbstractInsertTimeEntity
{
	private String plantTaskId;//种植任务id
	private String harvestId;//采收id
	private Calendar packageDate;//包装时间 	
	private String standard;//包装规格 	
	private Double weight;//包装重量 	
	private String packageMaterial;//包装材料 	
	private String name;//品名 	
	private String level;//等级 	
	private String place;//产地 	
	private Calendar harvestDate;//采收日期 	
	private String packageNo;//包装批次号 	
	private String produceCom;//生产单位
	private String taskNo;
	private String dataSource;//数据源
	private String medicineName;//药材名称
	private String harvestNo;//采收批号
	private String processNo;//加工批号
	private String farmerId;//农户id
	private String seedId;//农户id
	private String processId;//加工id
	@Column(name="process_Id",nullable = false, length = 100)
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
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
	@Column(name="process_No",nullable = false, length = 100)
	public String getProcessNo() {
		return processNo;
	}
	public void setProcessNo(String processNo) {
		this.processNo = processNo;
	}
	@Column(name="harvest_No",nullable = false, length = 100)
	public String getHarvestNo() {
		return harvestNo;
	}
	public void setHarvestNo(String harvestNo) {
		this.harvestNo = harvestNo;
	}
	@Column(name="data_Source",nullable = false, length = 100)
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	@Column(name="medicine_Name",nullable = false, length = 100)
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
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
	@Column(name="package_Date",nullable = false, length = 100)
	public Calendar getPackageDate() {
		return packageDate;
	}
	public void setPackageDate(Calendar packageDate) {
		this.packageDate = packageDate;
	}
	@Column(name="standard",nullable = false, length = 100)
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	@Column(name="weight",nullable = false, length = 100)
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	@Column(name="package_Material",nullable = false, length = 100)
	public String getPackageMaterial() {
		return packageMaterial;
	}
	public void setPackageMaterial(String packageMaterial) {
		this.packageMaterial = packageMaterial;
	}
	@Column(name="name",nullable = false, length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="level",nullable = false, length = 100)
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Column(name="place",nullable = false, length = 100)
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	@Column(name="harvest_Date",nullable = false, length = 100)
	public Calendar getHarvestDate() {
		return harvestDate;
	}
	public void setHarvestDate(Calendar harvestDate) {
		this.harvestDate = harvestDate;
	}
	@Column(name="package_No",nullable = false, length = 100)
	public String getPackageNo() {
		return packageNo;
	}
	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}
	@Column(name="produce_Com",nullable = false, length = 100)
	public String getProduceCom() {
		return produceCom;
	}
	public void setProduceCom(String produceCom) {
		this.produceCom = produceCom;
	}
}
