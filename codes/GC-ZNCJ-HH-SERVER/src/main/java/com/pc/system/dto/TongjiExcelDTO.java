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
public class TongjiExcelDTO implements Serializable {

	private static final long serialVersionUID = 7196406458069587972L;

	//年月
	@Excel(name = "年月")
	private String date;
	//总计划工时/机时
	@Excel(name = "总计划工时/机时")
	private String allPlan;
	//总计划人工
	@Excel(name = "总计划人工")
	private String planPerple;
	//生产日期
	@Excel(name = "总实际机时")
	private String allJiShi;
	//产出量
	@Excel(name = "总实际工时")
	private String allNow;
	//不良品盒数
	@Excel(name = "总实际人工")
	private String nowPeople;

}
