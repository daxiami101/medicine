package cn.com.taiji.sample.entity.source.land;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.dict.source.PlantMethod;
import cn.com.taiji.sample.entity.source.AbstractInsertTimeEntity;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 播种移栽
 */
@Entity
@Table(name = "source_sow_Transplant")
public class SourceSowTransplant extends AbstractInsertTimeEntity
{
	private String taskId;//种植任务
	private String taskNo;//种植批号
	private String seedId;//种子批号
	private Calendar plantTime;//时间
	private PlantMethod method;//播种/移栽方式
	private Double seedNum;//播种量(kg/亩)
	private Double plantNum;//移栽量
	private String medicineName;//药材名称
	private String farmerId;//农户id
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
	@Column(name="task_Id",nullable = false, length = 100)
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	@Column(name="seed_Id",nullable = false, length = 100)
	public String getSeedId() {
		return seedId;
	}
	public void setSeedId(String seedId) {
		this.seedId = seedId;
	}
	@Column(name="plant_Time",nullable = false, length = 100)
	public Calendar getPlantTime() {
		return plantTime;
	}
	public void setPlantTime(Calendar plantTime) {
		this.plantTime = plantTime;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="method",nullable = false, length = 100)
	public PlantMethod getMethod() {
		return method;
	}
	public void setMethod(PlantMethod method) {
		this.method = method;
	}
	@Column(name="seed_Num",nullable = false, length = 100)
	public Double getSeedNum() {
		return seedNum;
	}
	public void setSeedNum(Double seedNum) {
		this.seedNum = seedNum;
	}
	@Column(name="plant_Num",nullable = false, length = 100)
	public Double getPlantNum() {
		return plantNum;
	}
	public void setPlantNum(Double plantNum) {
		this.plantNum = plantNum;
	}
	@Column(name="task_No",nullable = false, length = 100)
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	
}
