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
import cn.com.taiji.sample.entity.source.SourceTransport;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceTransportPageRequest extends JpaPageableDataRequest<SourceTransport>
{
	private String medicineName;//药材名称
	private String processId;//产品批号
	private String transMethod;//*运输方式
	private String securityMeasure;//安保措施
	private String securityContractCode;//*安保合同号
	private String taskNo;//种植批号
	private LocalDateTime transportTime  ;
	private BuySell buySell;//数据源
	public SourceTransportPageRequest()
	{
		this.orderBy = "insertTime";
		this.desc =true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and medicineName like :medicineName", like(medicineName));
		hql.append(" and processId=:processId", processId);
		hql.append(" and transMethod=:transMethod", transMethod);
		hql.append(" and securityContractCode=:securityContractCode", securityContractCode);
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
	public String getTransMethod() {
		return transMethod;
	}
	public void setTransMethod(String transMethod) {
		this.transMethod = transMethod;
	}
	public String getSecurityMeasure() {
		return securityMeasure;
	}
	public void setSecurityMeasure(String securityMeasure) {
		this.securityMeasure = securityMeasure;
	}
	public String getSecurityContractCode() {
		return securityContractCode;
	}
	public void setSecurityContractCode(String securityContractCode) {
		this.securityContractCode = securityContractCode;
	}
	public LocalDateTime getTransportTime() {
		return transportTime;
	}
	public void setTransportTime(LocalDateTime transportTime) {
		this.transportTime = transportTime;
	}
}
