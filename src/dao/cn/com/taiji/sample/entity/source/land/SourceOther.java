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
 * 其他
 */
@Entity
@Table(name = "source_other")
public class SourceOther extends AbstractInsertTimeEntity
{
	private String taskId;//种植任务
	private String seedId;//种子批号
	private String operation;//操作 	
	private Calendar operationStartTime;//开始时间 
	private Calendar operationEndTime;//结束时间
	private String taskNo;//种植批号
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
	@Column(name="task_No",nullable = false, length = 100)
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
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
	@Column(name="operation",nullable = false, length = 100)
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	@Column(name="operation_Start_Time",nullable = false, length = 100)
	public Calendar getOperationStartTime() {
		return operationStartTime;
	}
	public void setOperationStartTime(Calendar operationStartTime) {
		this.operationStartTime = operationStartTime;
	}
	@Column(name="operation_End_Time",nullable = false, length = 100)
	public Calendar getOperationEndTime() {
		return operationEndTime;
	}
	public void setOperationEndTime(Calendar operationEndTime) {
		this.operationEndTime = operationEndTime;
	}
}
