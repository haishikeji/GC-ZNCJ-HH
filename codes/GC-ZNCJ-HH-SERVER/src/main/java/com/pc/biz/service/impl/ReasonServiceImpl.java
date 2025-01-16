package com.pc.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.pc.biz.dao.ReasonDao;
import com.pc.biz.domain.ReasonDO;
import com.pc.biz.service.ReasonService;



@Service
public class ReasonServiceImpl extends ServiceImpl<ReasonDao, ReasonDO> implements ReasonService {
	@Autowired
	private ReasonDao reasonDao;
	
	@Override
	public ReasonDO get(String id){
		return reasonDao.get(id);
	}
	
	@Override
	public List<ReasonDO> list(Map<String, Object> map){
		return reasonDao.list(map);
	}

	
}
