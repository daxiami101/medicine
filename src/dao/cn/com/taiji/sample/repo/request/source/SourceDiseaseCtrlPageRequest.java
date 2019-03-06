/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.land.SourceDiseaseCtrl;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceDiseaseCtrlPageRequest extends JpaPageableDataRequest<SourceDiseaseCtrl>
{
	private String taskId;//种植任务
	private String seedId;//种子批号
	private String diseaseKind;//主要病害种类 	
	private String diseasePalce;//危害部位 	
	private String preventMethod;//防治方式 	
	private String drugKind;//农药种类 
	private String taskNo;//种植批号
	private String medicineName;//药材名称
	public SourceDiseaseCtrlPageRequest()
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
		hql.append(" and diseaseKind=:diseaseKind", diseaseKind);
		hql.append(" and diseasePalce=:diseasePalce", diseasePalce);
		hql.append(" and preventMethod=:preventMethod", preventMethod);
		hql.append(" and taskNo=:taskNo", taskNo);
		hql.append(" and medicineName=:medicineName", medicineName);
		return hql;
	}
	
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
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
	public String getDiseaseKind() {
		return diseaseKind;
	}
	public void setDiseaseKind(String diseaseKind) {
		this.diseaseKind = diseaseKind;
	}
	public String getDiseasePalce() {
		return diseasePalce;
	}
	public void setDiseasePalce(String diseasePalce) {
		this.diseasePalce = diseasePalce;
	}
	public String getPreventMethod() {
		return preventMethod;
	}
	public void setPreventMethod(String preventMethod) {
		this.preventMethod = preventMethod;
	}
	public String getDrugKind() {
		return drugKind;
	}
	public void setDrugKind(String drugKind) {
		this.drugKind = drugKind;
	}
	
}
