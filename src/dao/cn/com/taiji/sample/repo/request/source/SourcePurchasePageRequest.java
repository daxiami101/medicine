/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import java.util.Calendar;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.dict.source.BuySell;
import cn.com.taiji.sample.entity.dict.source.MaterialStatus;
import cn.com.taiji.sample.entity.source.SourcePurchase;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourcePurchasePageRequest extends JpaPageableDataRequest<SourcePurchase>
{
	private String materialKind;//原料名称
	private String materialName;//*物料名称
	private String medicineCode;//药材编码
	private String province;//*产地
	private String level;//等级
	private String orderId;//到货单据号
	private String companyOrderId;//随货同行单据号
	private Calendar purchaseTime ;//*采收日期
	private String providerName;
	private String taskNo;//种植批号
	private MaterialStatus materialStatus;
	private BuySell buySell;//数据源
	public SourcePurchasePageRequest()
	{
		this.orderBy = "insertTime";
		this.desc =true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and materialKind like :materialKind", like(materialKind));
		hql.append(" and materialName=:materialName", materialName);
		hql.append(" and medicineCode=:medicineCode", medicineCode);
		hql.append(" and level=:level", level);
		hql.append(" and orderId=:orderId", orderId);
		hql.append(" and companyOrderId=:companyOrderId", companyOrderId);
		hql.append(" and providerName=:providerName", providerName);
		hql.append(" and taskNo=:taskNo", taskNo);
		hql.append(" and materialStatus=:materialStatus", materialStatus);
		hql.append(" and buySell=:buySell", buySell);
		return hql;
	}
	
	public BuySell getBuySell() {
		return buySell;
	}
	public void setBuySell(BuySell buySell) {
		this.buySell = buySell;
	}
	public MaterialStatus getMaterialStatus() {
		return materialStatus;
	}
	public void setMaterialStatus(MaterialStatus materialStatus) {
		this.materialStatus = materialStatus;
	}
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	public String getMaterialKind() {
		return materialKind;
	}
	public void setMaterialKind(String materialKind) {
		this.materialKind = materialKind;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMedicineCode() {
		return medicineCode;
	}
	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCompanyOrderId() {
		return companyOrderId;
	}
	public void setCompanyOrderId(String companyOrderId) {
		this.companyOrderId = companyOrderId;
	}
	public Calendar getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(Calendar purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
}
