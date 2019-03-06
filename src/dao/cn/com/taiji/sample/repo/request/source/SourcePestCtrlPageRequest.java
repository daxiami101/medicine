/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.land.SourcePestCtrl;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourcePestCtrlPageRequest extends JpaPageableDataRequest<SourcePestCtrl>
{
	private String taskId;//种植任务
	private String seedId;//种子批号
	private String pestKind;//主要虫害种类 	
	private String pestPlace;//危害部位 	
	private String preventMethod;//防治方式 	
	private String enrollNo;//农药登记证号 	
	private String company;//农药生产厂家 	
	private String purchasePersonName;//农药采购	
	private String taskNo;//种植批号
	private String medicineName;//药材名称
	public SourcePestCtrlPageRequest()
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
		hql.append(" and pestKind=:pestKind", pestKind);
		hql.append(" and pestPlace=:pestPlace", pestPlace);
		hql.append(" and preventMethod=:preventMethod", preventMethod);
		hql.append(" and enrollNo=:enrollNo", enrollNo);
		hql.append(" and company=:company", company);
		hql.append(" and purchasePersonName=:purchasePersonName", purchasePersonName);
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
	public String getPestKind() {
		return pestKind;
	}
	public void setPestKind(String pestKind) {
		this.pestKind = pestKind;
	}
	public String getPestPlace() {
		return pestPlace;
	}
	public void setPestPlace(String pestPlace) {
		this.pestPlace = pestPlace;
	}
	public String getPreventMethod() {
		return preventMethod;
	}
	public void setPreventMethod(String preventMethod) {
		this.preventMethod = preventMethod;
	}
	public String getEnrollNo() {
		return enrollNo;
	}
	public void setEnrollNo(String enrollNo) {
		this.enrollNo = enrollNo;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPurchasePersonName() {
		return purchasePersonName;
	}
	public void setPurchasePersonName(String purchasePersonName) {
		this.purchasePersonName = purchasePersonName;
	}

}
