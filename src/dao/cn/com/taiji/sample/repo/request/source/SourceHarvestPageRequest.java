/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import java.time.LocalDateTime;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.SourceHarvest;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceHarvestPageRequest extends JpaPageableDataRequest<SourceHarvest>
{
	private String plantTaskId;//种植任务id
	private String harvestPart;//	采收部位 	
	private String harvestMedicine;//	采收药材 	
	private String harvestId;//	采收批号 	
	private LocalDateTime harvestTime;//	采收时间 	
	private String harvestMethod;//	采收方式 	
	private Double area ;//	采收面积(亩) 	
	private Double production ;//	采收产量
	private String harvestNo;//采收批号
	private String taskNo;//种植批号
	public SourceHarvestPageRequest()
	{
		this.orderBy = "insertTime";
		this.desc =true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and harvestMedicine like :harvestMedicine", like(harvestMedicine));
		hql.append(" and plantTaskId=:plantTaskId", plantTaskId);
		hql.append(" and harvestId=:harvestId", harvestId);
		hql.append(" and harvestMethod=:harvestMethod", harvestMethod);
		hql.append(" and harvestNo=:harvestNo", harvestNo);
		hql.append(" and taskNo=:taskNo", taskNo);
		return hql;
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
	public String getHarvestPart() {
		return harvestPart;
	}
	public void setHarvestPart(String harvestPart) {
		this.harvestPart = harvestPart;
	}
	public String getHarvestMedicine() {
		return harvestMedicine;
	}
	public void setHarvestMedicine(String harvestMedicine) {
		this.harvestMedicine = harvestMedicine;
	}
	public String getHarvestId() {
		return harvestId;
	}
	public void setHarvestId(String harvestId) {
		this.harvestId = harvestId;
	}
	public LocalDateTime getHarvestTime() {
		return harvestTime;
	}
	public void setHarvestTime(LocalDateTime harvestTime) {
		this.harvestTime = harvestTime;
	}
	public String getHarvestMethod() {
		return harvestMethod;
	}
	public void setHarvestMethod(String harvestMethod) {
		this.harvestMethod = harvestMethod;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public Double getProduction() {
		return production;
	}
	public void setProduction(Double production) {
		this.production = production;
	}
}
