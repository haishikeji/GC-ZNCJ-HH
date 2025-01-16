package com.pc.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.pc.system.dao.BadHistoryDao;
import com.pc.system.domain.BadHistoryDO;
import com.pc.system.service.BadHistoryService;



@Service
public class BadHistoryServiceImpl extends ServiceImpl<BadHistoryDao, BadHistoryDO> implements BadHistoryService {
	@Autowired
	private BadHistoryDao badHistoryDao;
	
	@Override
	public BadHistoryDO get(String id){
		return badHistoryDao.get(id);
	}
	
	@Override
	public List<BadHistoryDO> list(Map<String, Object> map){
		return badHistoryDao.list(map);
	}

	
}
