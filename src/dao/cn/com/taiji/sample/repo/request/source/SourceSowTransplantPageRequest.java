/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import java.util.Calendar;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.dict.source.PlantMethod;
import cn.com.taiji.sample.entity.source.land.SourceSowTransplant;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceSowTransplantPageRequest extends JpaPageableDataRequest<SourceSowTransplant>
{
	private String taskId;//种植任务
	private String seedId;//种子批号
	private Calendar plantTime;//时间
	private PlantMethod method;//播种/移栽方式
	private Double seedNum;//播种量(kg/亩)
	private Double plantNum;
	private String taskNo;//种植批号
	private String medicineName;//药材名称
	public SourceSowTransplantPageRequest()
	{
		this.orderBy = "insertTime";
		this.desc = true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and taskId like :taskId", like(taskId));
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
	public Calendar getPlantTime() {
		return plantTime;
	}
	public void setPlantTime(Calendar plantTime) {
		this.plantTime = plantTime;
	}
	public PlantMethod getMethod() {
		return method;
	}
	public void setMethod(PlantMethod method) {
		this.method = method;
	}
	public Double getSeedNum() {
		return seedNum;
	}
	public void setSeedNum(Double seedNum) {
		this.seedNum = seedNum;
	}
	public Double getPlantNum() {
		return plantNum;
	}
	public void setPlantNum(Double plantNum) {
		this.plantNum = plantNum;
	}
	
}
