package cn.com.taiji.sample.manager.medicine.land;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.land.SourceIrrigation;
import cn.com.taiji.sample.entity.source.land.SourceSeedHandle;
import cn.com.taiji.sample.repo.request.source.SourceIrrigationPageRequest;

/**
 * 灌溉
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceIrrigationManager
{
	public Pagination queryPage(SourceIrrigationPageRequest req);

	public void delete(String id);

	public SourceIrrigation findById(String id);

	public void createTask(SourceIrrigationPageRequest req, HttpServletRequest request);

	public List<SourceIrrigation> listByTaskId(String plantTaskId);



}
