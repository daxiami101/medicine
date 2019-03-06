package cn.com.taiji.sample.entity.source;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.dict.source.PlantMethod;
import cn.com.taiji.sample.entity.dict.source.ReproduceMaterial;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 种植任务
 */
@Entity
@Table(name = "source_plant_task")
public class SourcePlantTask extends AbstractInsertTimeEntity
{
	private String seedId;//种源id---关联SourceSeed
	private String taskNo;//种植批号	
	private String medicineName;//种植药材	
	private PlantMethod plantMethod;//种植方式	
	private Calendar startPlantTime;//种植起始时间	 
	private Calendar endPlantTime;//种植结束时间	 
	private Double area;//种植面积(亩)	
	private String originalPalce;//产地
	private String farmerId;//农户信息
	//种子种苗处理
	private ReproduceMaterial reproduceMaterial;//繁殖材料
	private String handleMethod;//处理方式
	private String handleNote;//处理说明
	private Calendar handleTime;//处理时间
	//播种移栽
	private Calendar transplantTime;//移栽时间
	private String transplantMethod;//播种/移栽方式
	private Double seedTotal;//播种量
	private Double transplateNum;//移栽量
	private String dataSource;//
	@Column(name="data_Source",nullable = false, length = 100)
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	@Column(name="seed_Id",nullable = false, length = 100)
	public String getSeedId() {
		return seedId;
	}
	public void setSeedId(String seedId) {
		this.seedId = seedId;
	}
	@Column(name="task_No",nullable = false, length = 100)
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	@Column(name="medicine_Name",nullable = false, length = 100)
	public String getMedicineName() {
		return medicineName;
	}
	
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="plant_Method",nullable = false, length = 100)
	public PlantMethod getPlantMethod() {
		return plantMethod;
	}
	public void setPlantMethod(PlantMethod plantMethod) {
		this.plantMethod = plantMethod;
	}
	@Column(name="start_Plant_Time",nullable = false, length = 100)
	public Calendar getStartPlantTime() {
		return startPlantTime;
	}
	public void setStartPlantTime(Calendar startPlantTime) {
		this.startPlantTime = startPlantTime;
	}
	@Column(name="end_Plant_Time",nullable = false, length = 100)
	public Calendar getEndPlantTime() {
		return endPlantTime;
	}
	public void setEndPlantTime(Calendar endPlantTime) {
		this.endPlantTime = endPlantTime;
	}
	@Column(name="area",nullable = false, length = 100)
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	@Column(name="original_Palce",nullable = false, length = 100)
	public String getOriginalPalce() {
		return originalPalce;
	}
	public void setOriginalPalce(String originalPalce) {
		this.originalPalce = originalPalce;
	}
	@Column(name="farmer_Id",nullable = false, length = 100)
	public String getFarmerId() {
		return farmerId;
	}
	public void setFarmerId(String farmerId) {
		this.farmerId = farmerId;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="reproduce_Material",nullable = false, length = 100)
	public ReproduceMaterial getReproduceMaterial() {
		return reproduceMaterial;
	}
	public void setReproduceMaterial(ReproduceMaterial reproduceMaterial) {
		this.reproduceMaterial = reproduceMaterial;
	}
	@Column(name="handle_Method",nullable = false, length = 100)
	public String getHandleMethod() {
		return handleMethod;
	}
	public void setHandleMethod(String handleMethod) {
		this.handleMethod = handleMethod;
	}
	@Column(name="handle_Note",nullable = false, length = 100)
	public String getHandleNote() {
		return handleNote;
	}
	public void setHandleNote(String handleNote) {
		this.handleNote = handleNote;
	}
	@Column(name="handle_Time",nullable = false, length = 100)
	public Calendar getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(Calendar handleTime) {
		this.handleTime = handleTime;
	}
	@Column(name="transplant_Time",nullable = false, length = 100)
	public Calendar getTransplantTime() {
		return transplantTime;
	}
	public void setTransplantTime(Calendar transplantTime) {
		this.transplantTime = transplantTime;
	}
	@Column(name="transplant_Method",nullable = false, length = 100)
	public String getTransplantMethod() {
		return transplantMethod;
	}
	public void setTransplantMethod(String transplantMethod) {
		this.transplantMethod = transplantMethod;
	}
	@Column(name="seed_Total",nullable = false, length = 100)
	public Double getSeedTotal() {
		return seedTotal;
	}
	public void setSeedTotal(Double seedTotal) {
		this.seedTotal = seedTotal;
	}
	@Column(name="transplate_Num",nullable = false, length = 100)
	public Double getTransplateNum() {
		return transplateNum;
	}
	public void setTransplateNum(Double transplateNum) {
		this.transplateNum = transplateNum;
	}
	
	
}
