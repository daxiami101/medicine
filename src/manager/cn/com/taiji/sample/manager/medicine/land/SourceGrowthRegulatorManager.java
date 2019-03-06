package cn.com.taiji.sample.manager.medicine.land;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.land.SourceGrowthRegulator;
import cn.com.taiji.sample.entity.source.land.SourceSeedHandle;
import cn.com.taiji.sample.repo.request.source.SourceGrowthRegulatorPageRequest;

/**
 * 种植任务
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourceGrowthRegulatorManager
{
	public Pagination queryPage(SourceGrowthRegulatorPageRequest req);

	public void delete(String id);

	public SourceGrowthRegulator findById(String id);

	public void createTask(SourceGrowthRegulator req, HttpServletRequest request);

	public List<SourceGrowthRegulator> listByTaskId(String plantTaskId);


}
