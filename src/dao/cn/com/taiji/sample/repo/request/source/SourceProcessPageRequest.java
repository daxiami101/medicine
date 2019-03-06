/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import java.util.Calendar;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.SourceProcess;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceProcessPageRequest extends JpaPageableDataRequest<SourceProcess>
{
	private String plantTaskId;//种植任务id
	private String harvestId;//采收id
	private Calendar processTime; //加工日期 	
	private String processLevel; //加工等级 	
	private String processMethod;//加工方法 	
	private String harvestNo;//采收批号
	private String taskNo;//种植批号
	private String processNo;//加工批号
	public SourceProcessPageRequest()
	{
		this.orderBy = "insertTime";
		this.desc = true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and plantTaskId like :plantTaskId", like(plantTaskId));
		hql.append(" and harvestId=:harvestId", harvestId);
		hql.append(" and processLevel=:processLevel", processLevel);
		hql.append(" and processMethod=:processMethod", processMethod);
		hql.append(" and harvestNo=:harvestNo", harvestNo);
		hql.append(" and taskNo=:taskNo", taskNo);
		hql.append(" and processNo=:processNo", processNo);
		return hql;
	}
	
	public String getProcessNo() {
		return processNo;
	}
	public void setProcessNo(String processNo) {
		this.processNo = processNo;
	}
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	public String getHarvestNo() {
		return harvestNo;
	}
	public void setHarvestNo(String harvestNo) {
		this.harvestNo = harvestNo;
	}
	public String getPlantTaskId() {
		return plantTaskId;
	}
	public void setPlantTaskId(String plantTaskId) {
		this.plantTaskId = plantTaskId;
	}
	public String getHarvestId() {
		return harvestId;
	}
	public void setHarvestId(String harvestId) {
		this.harvestId = harvestId;
	}
	public Calendar getProcessTime() {
		return processTime;
	}
	public void setProcessTime(Calendar processTime) {
		this.processTime = processTime;
	}
	public String getProcessLevel() {
		return processLevel;
	}
	public void setProcessLevel(String processLevel) {
		this.processLevel = processLevel;
	}
	public String getProcessMethod() {
		return processMethod;
	}
	public void setProcessMethod(String processMethod) {
		this.processMethod = processMethod;
	}
	
}
