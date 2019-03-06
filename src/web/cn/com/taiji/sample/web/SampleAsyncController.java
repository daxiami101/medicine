/*
 * Date: 2012-11-8
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.web;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.quartz.DBExclusiveTask;
import cn.com.taiji.common.model.AsyncNoteModel;
import cn.com.taiji.sample.manager.SampleAsyncTask;
import cn.com.taiji.sample.model.MyFinals;
import cn.com.taiji.sample.repo.request.system.SystemLogPageRequest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2012-11-8 上午11:12:20<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Controller
@RequestMapping("/sample/async")
public class SampleAsyncController extends MyAsyncController<String>
{
	@Autowired
	private SampleAsyncTask sampleTask;

	@Autowired
	public SampleAsyncController(DataSource ds, SampleAsyncTask task)
	{
		super(new DBExclusiveTask(task, MyFinals.CRON_TASK_TABLE, ds, TASK_PREFIX + "sample", 180));
	}

	@RequestMapping("/runTask")
	public void runAsyncTask(@Valid @ModelAttribute SystemLogPageRequest qm, HttpServletResponse response)
			throws IOException, ManagerException
	{
		int i = new Random().nextInt(10);
		if (i < 5) throw new ManagerException("这个错误信息要显示在页面上，不再调用进度URL");
		logger.debug(qm.toJson());
		if (isRunning())
		{
			responseJson(new AsyncNoteModel("任务正在执行").toJson(), response);
			return;
		}
		super.asyncDoTask();
		responseJson(new AsyncNoteModel("任务将在后台执行").toJson(), response);
	}

	@RequestMapping("/runTask/process")
	public void taskProcess(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		super.responseProcess(request, response, sampleTask.getMsg(), sampleTask.getPercent());
	}

	@Override
	protected String getSucessResult()
	{
		return sampleTask.getResult();
	}
}
