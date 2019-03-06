/*
 * Date: 2013年9月4日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.manager;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.sample.model.SessionTicket;

/**
 * 
 * @author Peream <br>
 *         Create Time：2013年9月4日 上午10:21:56<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class SessionTicketManagerImpl extends AbstractManager implements SessionTicketManager
{
	// 一个小时
	private static final long MAX_HOLD_TIME = 1000L * 60 * 60;
	// 如果app有多个(service 一个)，可以改成用memcache实现
	private Map<String, SessionTicket> tickets = Maps.newConcurrentMap();

	@Override
	public boolean isTicketValid(String ticketId)
	{
		SessionTicket ticket = tickets.get(ticketId);
		if (ticket == null) return false;
		ticket.setTime(System.currentTimeMillis());// 更新最后访问时间
		return true;
	}

	@Override
	public void removeTicket(String ticketId)
	{
		tickets.remove(ticketId);
	}

	@Override
	public void addTicket(String ticketId, String sessionId)
	{
		SessionTicket ticket = new SessionTicket();
		ticket.setSessionId(sessionId);
		ticket.setTicketId(ticketId);
		ticket.setTime(System.currentTimeMillis());
		tickets.put(ticketId, ticket);
	}

	@Override
	public void cleanOldTicket()
	{
		long now = System.currentTimeMillis();
		int count = 0;
		for (Entry<String, SessionTicket> en : tickets.entrySet())
		{
			if (now - en.getValue().getTime() > MAX_HOLD_TIME)
			{
				tickets.remove(en.getKey());
				count++;
			}
		}
		logger.info("删除{}个过期的ticketId,目前共有{}个ticketId", count, tickets.size());
	}
}
