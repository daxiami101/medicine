/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import java.time.LocalDateTime;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.dict.source.BuySell;
import cn.com.taiji.sample.entity.source.SourceSold;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceSoldPageRequest extends JpaPageableDataRequest<SourceSold>
{
	private String medicineName;//药材名称
	private String processId;//*加工批号
	private String medicineCode;//药材编码
	private String commerceDepartCode;//商务部品种代码
	private String clientName;//客户名称
	private LocalDateTime soldTime ;//*销售日期
	private String barcode;//企业条形码
	private String taiwanCode;//台湾地区代码
	private String customCode;//海关码
	private String companyOrderId;
	private String taskNo;//种植批号
	private BuySell buySell;//数据源
	public SourceSoldPageRequest()
	{
		this.orderBy = "insertTime";
		this.desc = true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and medicineName like :medicineName", like(medicineName));
		hql.append(" and processId=:processId", processId);
		hql.append(" and medicineCode=:medicineCode", medicineCode);
		hql.append(" and commerceDepartCode=:commerceDepartCode", commerceDepartCode);
		hql.append(" and clientName=:clientName", clientName);
		hql.append(" and barcode=:barcode", barcode);
		hql.append(" and taiwanCode=:taiwanCode", taiwanCode);
		hql.append(" and customCode=:customCode", customCode);
		hql.append(" and companyOrderId=:companyOrderId", companyOrderId);
		hql.append(" and taskNo=:taskNo", taskNo);
		hql.append(" and buySell=:buySell", buySell);
		return hql;
	}
	
	public BuySell getBuySell() {
		return buySell;
	}
	public void setBuySell(BuySell buySell) {
		this.buySell = buySell;
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
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getMedicineCode() {
		return medicineCode;
	}
	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}
	public String getCommerceDepartCode() {
		return commerceDepartCode;
	}
	public void setCommerceDepartCode(String commerceDepartCode) {
		this.commerceDepartCode = commerceDepartCode;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public LocalDateTime getSoldTime() {
		return soldTime;
	}
	public void setSoldTime(LocalDateTime soldTime) {
		this.soldTime = soldTime;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getTaiwanCode() {
		return taiwanCode;
	}
	public void setTaiwanCode(String taiwanCode) {
		this.taiwanCode = taiwanCode;
	}
	public String getCustomCode() {
		return customCode;
	}
	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}
	public String getCompanyOrderId() {
		return companyOrderId;
	}
	public void setCompanyOrderId(String companyOrderId) {
		this.companyOrderId = companyOrderId;
	}
}
