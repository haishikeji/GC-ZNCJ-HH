package com.pc.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pc.biz.domain.ShiduDO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 湿度记录
 * @author 品讯科技
 * @email zly@hnpinxun.cn
 * @date 2024-08
 */
@Mapper
public interface ShiduDao extends BaseMapper<ShiduDO> {

	ShiduDO get(String id);
	
	List<ShiduDO> list(Map<String, Object> map);

    BigDecimal getNow();
}
