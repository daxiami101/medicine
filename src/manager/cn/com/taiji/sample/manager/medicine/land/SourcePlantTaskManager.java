package cn.com.taiji.sample.manager.medicine.land;

import javax.validation.Valid;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.SourcePlantTask;
import cn.com.taiji.sample.repo.request.source.SourcePlantTaskPageRequest;

/**
 * 种植任务
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourcePlantTaskManager
{
	public Pagination queryPage(SourcePlantTaskPageRequest req);

	public void save(@Valid SourcePlantTask pageModel);

	public SourcePlantTask findById(String id);

	public void delete(String id);

	public void update(@Valid SourcePlantTask user);

	public void createPlanTask(SourcePlantTaskPageRequest req);

	public void chooseFarmer(SourcePlantTaskPageRequest pageModel);


}
