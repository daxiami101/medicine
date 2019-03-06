package cn.com.taiji.sample.entity.source;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "source_harvest")
public class SourceHarvest extends AbstractInsertTimeEntity
{
	private String plantTaskId;//种植任务id
	private String harvestPart;//	采收部位 	
	private String harvestMedicine;//	采收药材 	
	private String harvestNo;//	采收批号 	
	private Calendar harvestTime;//	采收时间 	
	private String harvestMethod;//	采收方式 	
	private Double area ;//	采收面积(亩) 	
	private Double production ;//	采收产量
	private String taskNo;
	private String medicineName;//药材名称
	private String farmerId;//农户id
	private String seedId;//农户id
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
	@Column(name="harvest_Part",nullable = false, length = 100)
	public String getHarvestPart() {
		return harvestPart;
	}
	public void setHarvestPart(String harvestPart) {
		this.harvestPart = harvestPart;
	}
	@Column(name="harvest_Medicine",nullable = false, length = 100)
	public String getHarvestMedicine() {
		return harvestMedicine;
	}
	public void setHarvestMedicine(String harvestMedicine) {
		this.harvestMedicine = harvestMedicine;
	}
	@Column(name="harvest_Time")
	public Calendar getHarvestTime() {
		return harvestTime;
	}
	public void setHarvestTime(Calendar harvestTime) {
		this.harvestTime = harvestTime;
	}
	@Column(name="harvest_Method",nullable = false, length = 100)
	public String getHarvestMethod() {
		return harvestMethod;
	}
	public void setHarvestMethod(String harvestMethod) {
		this.harvestMethod = harvestMethod;
	}
	@Column(name="area",nullable = false, length = 100)
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	@Column(name="production",nullable = false, length = 100)
	public Double getProduction() {
		return production;
	}
	public void setProduction(Double production) {
		this.production = production;
	}
	@Column(name="harvest_No",nullable = false, length = 100)
	public String getHarvestNo() {
		return harvestNo;
	}
	public void setHarvestNo(String harvestNo) {
		this.harvestNo = harvestNo;
	}
	
}
