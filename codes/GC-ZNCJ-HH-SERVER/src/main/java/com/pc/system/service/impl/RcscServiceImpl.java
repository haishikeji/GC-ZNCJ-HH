package com.pc.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.pc.system.dao.RcscDao;
import com.pc.system.domain.RcscDO;
import com.pc.system.service.RcscService;



@Service
public class RcscServiceImpl implements RcscService {
	@Autowired
	private RcscDao rcscDao;
	
	@Override
	public RcscDO get(String id){
		return rcscDao.get(id);
	}
	
	@Override
	public List<RcscDO> list(Map<String, Object> map){
		return rcscDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return rcscDao.count(map);
	}
	
	@Override
	public int save(RcscDO rcsc){
		return rcscDao.save(rcsc);
	}
	
	@Override
	public int update(RcscDO rcsc){
		return rcscDao.update(rcsc);
	}
	
	@Override
	public int remove(String id){
		return rcscDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return rcscDao.batchRemove(ids);
	}
	
}
