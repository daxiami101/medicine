package cn.com.taiji.sample.entity.source.land;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.dict.source.ReproduceMaterial;
import cn.com.taiji.sample.entity.source.AbstractInsertTimeEntity;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 种子种苗处理
 */
@Entity
@Table(name = "source_seed_handle")
public class SourceSeedHandle extends AbstractInsertTimeEntity
{
	private String taskId;//种植任务
	private String seedId;//种子批号id
	private ReproduceMaterial reproduceMaterial;//繁殖材料
	private String handleMethod;//处理方式
	private String handleNote;//处理说明
	private Calendar handleTime;//处理时间
	private String taskNo;//种植批号
	private String medicineName;//药材名称
	private String farmerId;//农户信息id
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
	
	
	
	
}
