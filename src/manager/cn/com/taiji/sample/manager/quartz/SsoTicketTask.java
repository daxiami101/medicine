/*
 * Date: 2013年9月4日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.manager.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.sample.config.manager.TaskInfo;
import cn.com.taiji.sample.manager.SessionTicketManager;

/**
 * 
 * @author Peream <br>
 *         Create Time：2013年9月4日 上午10:52:40<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class SsoTicketTask extends AbstractCronTask
{
	@Autowired
	private SessionTicketManager ticketManager;

	public SsoTicketTask()
	{
		super(TaskInfo.SSO_TICKET);
	}

	@Override
	public void run()
	{
		ticketManager.cleanOldTicket();
	}

}
