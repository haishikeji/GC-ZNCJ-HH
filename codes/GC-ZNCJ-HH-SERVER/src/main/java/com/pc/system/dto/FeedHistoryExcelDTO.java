package com.pc.system.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;


/**
 * 工作量统计
 * 
 * @author 品讯科技
 * @email zly@hnpinxun.cn
 * @date 2024-08
 */
@Data
public class FeedHistoryExcelDTO implements Serializable {

	private static final long serialVersionUID = 7196406458069587972L;

		//投料日期
		@Excel(name = "投料日期")
		private String date;
		//工单编号
		@Excel(name = "工单编号")
		private String orderNumber;
		//设备编号
		@Excel(name = "设备编号")
		private String sbNumber;
		//设备名称
		@Excel(name = "设备名称")
		private String sbName;
		//物料型号
		@Excel(name = "物料型号")
		private String wlType;
		//物料名称
		@Excel(name = "物料名称")
		private String wlName;
		//投入量
		@Excel(name = "投入量")
		private String wlNumber;
		//物料型号
		@Excel(name = "物料型号")
		private String wlType2;
		//物料名称
		@Excel(name = "物料名称")
		private String wlName2;
		//投入量2
		@Excel(name = "投入量")
		private String wlNumber2;
	

}
