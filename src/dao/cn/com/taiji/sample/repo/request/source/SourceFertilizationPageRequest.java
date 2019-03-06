/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import java.util.Calendar;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.land.SourceFertilization;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceFertilizationPageRequest extends JpaPageableDataRequest<SourceFertilization>
{
	private String taskId;//种植任务
	private String seedId;//种子批号
	private String fertilitizationKind;//肥料种类</th>
	private Calendar fertStartTime;//<th>施肥开始时间 </th>
	private Calendar fertEndTime;//<th >施肥结束时间 </th>
	private String fertMethod;//<th>施肥方式 </th>
	private String enrollNo;//<th>登记证号 </th>
	private String produceCom;//<th>生产厂家  </th>
	private String taskNo;//种植批号
	private String medicineName;//药材名称
	public SourceFertilizationPageRequest()
	{
		this.orderBy = "insertTime";
		this.desc = true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and taskId like :taskId", like(taskId));
		hql.append(" and seedId=:seedId", seedId);
		hql.append(" and fertilitizationKind=:fertilitizationKind", fertilitizationKind);
		hql.append(" and fertMethod=:fertMethod", fertMethod);
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
	public String getFertilitizationKind() {
		return fertilitizationKind;
	}
	public void setFertilitizationKind(String fertilitizationKind) {
		this.fertilitizationKind = fertilitizationKind;
	}
	public Calendar getFertStartTime() {
		return fertStartTime;
	}
	public void setFertStartTime(Calendar fertStartTime) {
		this.fertStartTime = fertStartTime;
	}
	public Calendar getFertEndTime() {
		return fertEndTime;
	}
	public void setFertEndTime(Calendar fertEndTime) {
		this.fertEndTime = fertEndTime;
	}
	public String getFertMethod() {
		return fertMethod;
	}
	public void setFertMethod(String fertMethod) {
		this.fertMethod = fertMethod;
	}
	public String getEnrollNo() {
		return enrollNo;
	}
	public void setEnrollNo(String enrollNo) {
		this.enrollNo = enrollNo;
	}
	public String getProduceCom() {
		return produceCom;
	}
	public void setProduceCom(String produceCom) {
		this.produceCom = produceCom;
	}
	
}
