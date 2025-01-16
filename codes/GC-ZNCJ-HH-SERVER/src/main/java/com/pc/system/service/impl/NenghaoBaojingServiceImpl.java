package com.pc.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.pc.system.dao.NenghaoBaojingDao;
import com.pc.system.domain.NenghaoBaojingDO;
import com.pc.system.service.NenghaoBaojingService;



@Service
public class NenghaoBaojingServiceImpl implements NenghaoBaojingService {
	@Autowired
	private NenghaoBaojingDao nenghaoBaojingDao;
	
	@Override
	public NenghaoBaojingDO get(Integer id){
		return nenghaoBaojingDao.get(id);
	}
	
	@Override
	public List<NenghaoBaojingDO> list(Map<String, Object> map){
		return nenghaoBaojingDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return nenghaoBaojingDao.count(map);
	}
	
	@Override
	public int save(NenghaoBaojingDO nenghaoBaojing){
		return nenghaoBaojingDao.save(nenghaoBaojing);
	}
	
	@Override
	public int update(NenghaoBaojingDO nenghaoBaojing){
		return nenghaoBaojingDao.update(nenghaoBaojing);
	}
	
	@Override
	public int remove(Integer id){
		return nenghaoBaojingDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return nenghaoBaojingDao.batchRemove(ids);
	}
	
}
