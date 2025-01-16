package com.pc.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pc.biz.domain.ProductTaskSchedulingDO;
import com.pc.biz.dto.ProductDTO;
import com.pc.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务排产表
 * @author 品讯科技
 * @email zly@hnpinxun.cn
 * @date 2024-08
 */
@Mapper
public interface ProductTaskSchedulingDao extends BaseMapper<ProductTaskSchedulingDO> {
	List<HashMap> listPage(@Param("pojo") Map params);

	List<ProductDTO> getList(Map<String, Object> params);

	HashMap<String, Object> getInfo(Map<String, Object> params);

	ProductDTO getDetail(String jhId);

    List<ProductTaskSchedulingDO> listReport(Query query);

    HashMap listReport4Count(Map<String, Object> query);

	int countReport(Query query);

    List<ProductTaskSchedulingDO> getReport(Map<String, Object> params);

	ProductTaskSchedulingDO listAllReport(Map<String, Object> params);

	List<ProductDTO> getQuantityList(Map<String, Object> params);
}
