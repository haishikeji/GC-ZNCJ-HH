package com.pc.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 产品管理
 * @author 品讯科技
 * @email zly@hnpinxun.cn
 * @date 2024-08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProduceAdministrationDO implements Serializable {
	private static final long serialVersionUID = 1L;
	//主键
	private Integer id;

	//产品编码
	private String produceCode;

	//产品名称
	private String produceName;

	//规格
	private String productSpec;

	//单位
	private String productUnit;

	//SOP附件
	private String sopFiles;

	//SIP附件
	private String sipFiles;

}
