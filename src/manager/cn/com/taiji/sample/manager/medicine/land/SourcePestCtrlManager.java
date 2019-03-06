package cn.com.taiji.sample.manager.medicine.land;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.sample.entity.source.land.SourcePestCtrl;
import cn.com.taiji.sample.entity.source.land.SourceSeedHandle;
import cn.com.taiji.sample.repo.request.source.SourcePestCtrlPageRequest;

/**
 * 种植任务
 * @author admin02
 * 2018年9月2日 下午6:02:54
 * TODO
 */
public interface SourcePestCtrlManager
{
	public Pagination queryPage(SourcePestCtrlPageRequest req);

	public void delete(String id);

	public SourcePestCtrl findById(String id);

	public void createTask(SourcePestCtrl req, HttpServletRequest request);

	public List<SourcePestCtrl> listByTaskId(String plantTaskId);


}
