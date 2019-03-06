/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import java.util.Calendar;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.land.SourceWeed;

/**
 * 除草
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceWeedPageRequest extends JpaPageableDataRequest<SourceWeed>
{
	private String taskId;//种植任务
	private String seedId;//种子批号
	private String weedKind;//主要杂草种类
	private Calendar weedStartTime;//除草开始时间 	
	private Calendar weedEndTime ;//除草结束时间 
	private String weedMethod;//除草方式 	
	private String weedicide;//除草剂种类 	
	private Double num;//用药量/亩 	
	private String unit;//计量单位
	private String weedicideNo;//农药登记证号
	private String weedCom;//农药生产厂家 	
	private Calendar produceDate;//农药生产日期 	
	private Calendar purchaseDate;//农药采购日期
	private String taskNo;//种植批号
	private String medicineName;//药材名称
	public SourceWeedPageRequest()
	{
		this.orderBy = "insertTime";
		this.desc =true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and taskId like :taskId", like(taskId));
		hql.append(" and weedKind=:weedKind", weedKind);
		hql.append(" and weedMethod=:weedMethod", weedMethod);
		hql.append(" and weedicideNo=:weedicideNo", weedicideNo);
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
	public String getWeedKind() {
		return weedKind;
	}
	public void setWeedKind(String weedKind) {
		this.weedKind = weedKind;
	}
	public Calendar getWeedStartTime() {
		return weedStartTime;
	}
	public void setWeedStartTime(Calendar weedStartTime) {
		this.weedStartTime = weedStartTime;
	}
	public Calendar getWeedEndTime() {
		return weedEndTime;
	}
	public void setWeedEndTime(Calendar weedEndTime) {
		this.weedEndTime = weedEndTime;
	}
	public String getWeedMethod() {
		return weedMethod;
	}
	public void setWeedMethod(String weedMethod) {
		this.weedMethod = weedMethod;
	}
	public String getWeedicide() {
		return weedicide;
	}
	public void setWeedicide(String weedicide) {
		this.weedicide = weedicide;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getWeedicideNo() {
		return weedicideNo;
	}
	public void setWeedicideNo(String weedicideNo) {
		this.weedicideNo = weedicideNo;
	}
	public String getWeedCom() {
		return weedCom;
	}
	public void setWeedCom(String weedCom) {
		this.weedCom = weedCom;
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
