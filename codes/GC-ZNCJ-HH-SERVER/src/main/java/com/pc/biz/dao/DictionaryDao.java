package com.pc.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pc.biz.domain.DictionaryDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author 品讯科技
 * @email zly@hnpinxun.cn
 * @date 2024-08
 */
@Mapper
public interface DictionaryDao extends BaseMapper<DictionaryDO> {

	DictionaryDO get(String id);
	
	List<DictionaryDO> list(Map<String, Object> map);

}
