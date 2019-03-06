package cn.com.taiji.sample.entity.source.land;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.source.AbstractInsertTimeEntity;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 灌溉
 */
@Entity
@Table(name = "source_irrigation")
public class SourceIrrigation extends AbstractInsertTimeEntity
{
	private String taskId;//种植任务
	private String taskNo;//种植批号
	private String seedId;//种子批号
	private Calendar irrigationStartTime;//灌溉开始时间
	private Calendar irrigationEndTime;//灌溉结束时间
	private String method;//灌溉方式
	private String status;//审核状态
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
	@Column(name="irrigation_StartTime",nullable = false, length = 100)
	public Calendar getIrrigationStartTime() {
		return irrigationStartTime;
	}
	public void setIrrigationStartTime(Calendar irrigationStartTime) {
		this.irrigationStartTime = irrigationStartTime;
	}
	@Column(name="irrigation_EndTime",nullable = false, length = 100)
	public Calendar getIrrigationEndTime() {
		return irrigationEndTime;
	}
	public void setIrrigationEndTime(Calendar irrigationEndTime) {
		this.irrigationEndTime = irrigationEndTime;
	}
	@Column(name="method",nullable = false, length = 100)
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	@Column(name="status",nullable = false, length = 100)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="task_No",nullable = false, length = 100)
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	
}
