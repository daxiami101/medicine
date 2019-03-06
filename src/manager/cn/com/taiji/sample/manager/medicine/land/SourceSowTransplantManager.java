package cn.com.taiji.sample.manager.medicine.land;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.land.SourceSowTransplant;
import cn.com.taiji.sample.repo.request.source.SourcePlantTaskPageRequest;
import cn.com.taiji.sample.repo.request.source.SourceSowTransplantPageRequest;

/**
 * 播种移栽
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 * 播种移栽
 */
public interface SourceSowTransplantManager
{
	public Pagination queryPage(SourceSowTransplantPageRequest req);

	public SourceSowTransplant findById(String id);
	
	public void update(SourceSowTransplant user);

	public void save(@Valid SourceSowTransplant pageModel);

	public void delete(String id);

	public void chooseTask(SourcePlantTaskPageRequest pageModel, HttpServletRequest request);

	public void createTask(SourceSowTransplantPageRequest req, HttpServletRequest request);

	public List<SourceSowTransplant> listByTaskId(String plantTaskId);
}
