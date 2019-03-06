/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import java.util.Calendar;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.land.SourceIrrigation;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceIrrigationPageRequest extends JpaPageableDataRequest<SourceIrrigation>
{
	private String taskId;//种植任务
	private String seedId;//种子批号
	private Calendar irrigationStartTime;//灌溉开始时间
	private Calendar irrigationEndTime;//灌溉结束时间
	private String method;//灌溉方式
	private String status;//审核状态
	private String taskNo;
	private String medicineName;//药材名称
	public SourceIrrigationPageRequest()
	{
		this.orderBy = "insertTime";
		this.desc =true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and taskId like :taskId", like(taskId));
		hql.append(" and seedId=:seedId", seedId);
		hql.append(" and method=:method", method);
		hql.append(" and taskNo=:taskNo", taskNo);
		hql.append(" and medicineName=:medicineName", medicineName);
		return hql;
	}
	
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getSeedId() {
		return seedId;
	}
	public void setSeedId(String seedId) {
		this.seedId = seedId;
	}
	public Calendar getIrrigationStartTime() {
		return irrigationStartTime;
	}
	public void setIrrigationStartTime(Calendar irrigationStartTime) {
		this.irrigationStartTime = irrigationStartTime;
	}
	public Calendar getIrrigationEndTime() {
		return irrigationEndTime;
	}
	public void setIrrigationEndTime(Calendar irrigationEndTime) {
		this.irrigationEndTime = irrigationEndTime;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
