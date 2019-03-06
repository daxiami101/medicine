/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import java.time.LocalDateTime;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.SourceSoldOut;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceSoldOutPageRequest extends JpaPageableDataRequest<SourceSoldOut>
{
	private String materialName;//产品名称
	private String originalPlace;//产地
	private String produceId;//产品批号
	private String standard;//产品包装规格
	private LocalDateTime soldTime ;//*销售日期
	private String storeMethod;//存储方式
	private String companyOrderId;//随货同行单据号
	private String customServiceName;
	
	public SourceSoldOutPageRequest()
	{
//		this.orderBy = "namePy";
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and materialName like :materialName", like(materialName));
		hql.append(" and originalPlace=:originalPlace", originalPlace);
		hql.append(" and produceId=:produceId", produceId);
		hql.append(" and standard=:standard", standard);
		hql.append(" and storeMethod=:storeMethod", storeMethod);
		hql.append(" and companyOrderId=:companyOrderId", companyOrderId);
		hql.append(" and customServiceName=:customServiceName", customServiceName);
		return hql;
	}
	
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public LocalDateTime getSoldTime() {
		return soldTime;
	}
	public void setSoldTime(LocalDateTime soldTime) {
		this.soldTime = soldTime;
	}
	public String getStoreMethod() {
		return storeMethod;
	}
	public void setStoreMethod(String storeMethod) {
		this.storeMethod = storeMethod;
	}
	public String getCustomServiceName() {
		return customServiceName;
	}
	public void setCustomServiceName(String customServiceName) {
		this.customServiceName = customServiceName;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getOriginalPlace() {
		return originalPlace;
	}
	public void setOriginalPlace(String originalPlace) {
		this.originalPlace = originalPlace;
	}
	public String getCompanyOrderId() {
		return companyOrderId;
	}
	public void setCompanyOrderId(String companyOrderId) {
		this.companyOrderId = companyOrderId;
	}
	public String getProduceId() {
		return produceId;
	}
	public void setProduceId(String produceId) {
		this.produceId = produceId;
	}
}
