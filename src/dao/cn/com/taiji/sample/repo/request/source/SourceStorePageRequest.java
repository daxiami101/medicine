/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.dict.source.BuySell;
import cn.com.taiji.sample.entity.source.SourceStore;

/**
 * 存储管理
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceStorePageRequest extends JpaPageableDataRequest<SourceStore>
{
	private String plantTaskId;//种植任务id
	private String harvestId;//加工id
	private String storeCode ;//储存仓库代码 	
	private String storeCondition;//储存条件 	
	private String storeMethod;//贮藏方式 	
	private String measure;
	private String taskNo;//种植批号
	private BuySell buySell;//数据源
	public SourceStorePageRequest()
	{
		this.orderBy = "insertTime";
		this.desc =true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and plantTaskId like :plantTaskId", like(plantTaskId));
		hql.append(" and harvestId=:harvestId", harvestId);
		hql.append(" and storeCode=:storeCode", storeCode);
		hql.append(" and storeCondition=:storeCondition", storeCondition);
		hql.append(" and storeMethod=:storeMethod", storeMethod);
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
	public String getPlantTaskId() {
		return plantTaskId;
	}
	public void setPlantTaskId(String plantTaskId) {
		this.plantTaskId = plantTaskId;
	}
	public String getHarvestId() {
		return harvestId;
	}
	public void setHarvestId(String harvestId) {
		this.harvestId = harvestId;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public String getStoreCondition() {
		return storeCondition;
	}
	public void setStoreCondition(String storeCondition) {
		this.storeCondition = storeCondition;
	}
	public String getStoreMethod() {
		return storeMethod;
	}
	public void setStoreMethod(String storeMethod) {
		this.storeMethod = storeMethod;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	
}
