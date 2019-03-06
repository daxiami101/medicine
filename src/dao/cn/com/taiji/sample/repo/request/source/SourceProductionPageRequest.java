/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import java.time.LocalDateTime;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.SourceProduction;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceProductionPageRequest extends JpaPageableDataRequest<SourceProduction>
{
	private String materialName;//产品名称
	private String originalPlace;//产地
	private String productionId;//产品批号
	private String standard;//产品包装规格
	private LocalDateTime outTime ;//*出库日期
	private String storeMethod;
	
	public SourceProductionPageRequest()
	{
		this.orderBy = "insertTime";
		this.desc = true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and materialName like :materialName", like(materialName));
		hql.append(" and originalPlace=:originalPlace", originalPlace);
		hql.append(" and productionId=:productionId", productionId);
		hql.append(" and standard=:standard", standard);
		hql.append(" and storeMethod=:storeMethod", storeMethod);
		return hql;
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
	public String getProductionId() {
		return productionId;
	}
	public void setProductionId(String productionId) {
		this.productionId = productionId;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public LocalDateTime getOutTime() {
		return outTime;
	}
	public void setOutTime(LocalDateTime outTime) {
		this.outTime = outTime;
	}
	public String getStoreMethod() {
		return storeMethod;
	}
	public void setStoreMethod(String storeMethod) {
		this.storeMethod = storeMethod;
	}
	
}
