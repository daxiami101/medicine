/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import java.util.Calendar;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.land.SourceGrowthRegulator;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceGrowthRegulatorPageRequest extends JpaPageableDataRequest<SourceGrowthRegulator>
{
	private String taskId;//种植任务
	private String seedId;//种子批号
	private String regulatorKind;//生长调节剂种类 
	private String useMethod;//施用方式 	
	private String enrollNo;//登记证号
	private String company;//生产厂家
	private Calendar produceDate;//生产日期
	private Calendar purchaseDate;//采购日期
	private String taskNo;//种植批号
	private String medicineName;//药材名称
	public SourceGrowthRegulatorPageRequest()
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
		hql.append(" and regulatorKind=:regulatorKind", regulatorKind);
		hql.append(" and useMethod=:useMethod", useMethod);
		hql.append(" and enrollNo=:enrollNo", enrollNo);
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
	public String getRegulatorKind() {
		return regulatorKind;
	}
	public void setRegulatorKind(String regulatorKind) {
		this.regulatorKind = regulatorKind;
	}
	public String getUseMethod() {
		return useMethod;
	}
	public void setUseMethod(String useMethod) {
		this.useMethod = useMethod;
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
	public Calendar getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(Calendar produceDate) {
		this.produceDate = produceDate;
	}
	public Calendar getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Calendar purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
}
