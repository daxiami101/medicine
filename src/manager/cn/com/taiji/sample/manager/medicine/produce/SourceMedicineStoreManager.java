package cn.com.taiji.sample.manager.medicine.produce;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceMedicineStore;
import cn.com.taiji.sample.repo.request.source.SourceMedicineStorePageRequest;

/**
 * 种植任务
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceMedicineStoreManager
{
	public Pagination queryPage(SourceMedicineStorePageRequest req);

	public SourceMedicineStore findById(String id);

	public void delete(String id);

	public void createTask(SourceMedicineStore req, HttpServletRequest request);


}
