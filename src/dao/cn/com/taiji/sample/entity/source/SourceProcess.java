package cn.com.taiji.sample.entity.source;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 加工管理--加工
 * @author admin02
 * 2018年10月28日 下午9:34:33
 * TODO
 */
@Entity
@Table(name = "source_process")
public class SourceProcess extends AbstractInsertTimeEntity
{
	private String plantTaskId;//种植任务id
	private String harvestId;//采收id
	private Calendar processTime; //加工日期 	
	private String processLevel; //加工等级 	
	private String processMethod;//加工方法 	
	private Double preProcessQuality; //加工前重量(kg) 	
	private Double nonMediQuality; //非药用部位重量(kg) 	
	private String postProcessQuality;//加工后总重量(kg)
	private String taskNo;//种植批号
	private String harvestNo;//采收批号
	private String processNo;//加工批号
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
	@Column(name="process_Time",nullable = false, length = 100)
	public Calendar getProcessTime() {
		return processTime;
	}
	public void setProcessTime(Calendar processTime) {
		this.processTime = processTime;
	}
	@Column(name="process_Level",nullable = false, length = 100)
	public String getProcessLevel() {
		return processLevel;
	}
	public void setProcessLevel(String processLevel) {
		this.processLevel = processLevel;
	}
	@Column(name="process_Method",nullable = false, length = 100)
	public String getProcessMethod() {
		return processMethod;
	}
	public void setProcessMethod(String processMethod) {
		this.processMethod = processMethod;
	}
	@Column(name="pre_Process_Quality",nullable = false, length = 100)
	public Double getPreProcessQuality() {
		return preProcessQuality;
	}
	public void setPreProcessQuality(Double preProcessQuality) {
		this.preProcessQuality = preProcessQuality;
	}
	@Column(name="non_Medi_Quality",nullable = false, length = 100)
	public Double getNonMediQuality() {
		return nonMediQuality;
	}
	public void setNonMediQuality(Double nonMediQuality) {
		this.nonMediQuality = nonMediQuality;
	}
	@Column(name="post_Process_Quality",nullable = false, length = 100)
	public String getPostProcessQuality() {
		return postProcessQuality;
	}
	public void setPostProcessQuality(String postProcessQuality) {
		this.postProcessQuality = postProcessQuality;
	}
	
}
