package cn.com.taiji.sample.manager.medicine.produce;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourceMedicineProduce;
import cn.com.taiji.sample.repo.request.source.SourceMedicineProducePageRequest;

/**
 * 种植任务
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceMedicineProduceManager
{
	public Pagination queryPage(SourceMedicineProducePageRequest req);

	public void delete(String id);

	public SourceMedicineProduce findById(String id);

	public void createTask(SourceMedicineProduce req, HttpServletRequest request);


}
