package com.pc.biz.domain;

import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 品讯科技
 * @email zly@hnpinxun.cn
 * @date 2024-08
 */
public class ScgwDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//生产岗位检查
	private String id;
	//对设备安全状态完好（0是    1否）
	private String sbStatue;
	//安全防护装置有效（0是    1否）
	private String fhStatue;
	//安全措施落实(0是      1否)
	private String aqcsStatue;
	//设备、工具是否符合安全规定（0是       1否）
	private String gjStatue;
	//作业场地以及物品的堆放是否符合安全规范（0是    1否）
	private String cdStatue;
	//个人防护用品、用具是否准备齐全（0是    1否）
	private String personStatue;
	//是否可靠（是/否）
	private String kkStatue;
	//创建人
	private Long createUserId;
	//创建时间
	private Date createTime;
	//更新人
	private Long updateUserId;
	//更新时间

	private Date updateTime;
	@Transient
	private String creatName;

    public String getCreatName() {
        return creatName;
    }

    public void setCreatName(String creatName) {
        this.creatName = creatName;
    }

    /**
	 * 设置：生产岗位检查
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：生产岗位检查
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：对设备安全状态完好（0是    1否）
	 */
	public void setSbStatue(String sbStatue) {
		this.sbStatue = sbStatue;
	}
	/**
	 * 获取：对设备安全状态完好（0是    1否）
	 */
	public String getSbStatue() {
		return sbStatue;
	}
	/**
	 * 设置：安全防护装置有效（0是    1否）
	 */
	public void setFhStatue(String fhStatue) {
		this.fhStatue = fhStatue;
	}
	/**
	 * 获取：安全防护装置有效（0是    1否）
	 */
	public String getFhStatue() {
		return fhStatue;
	}
	/**
	 * 设置：安全措施落实(0是      1否)
	 */
	public void setAqcsStatue(String aqcsStatue) {
		this.aqcsStatue = aqcsStatue;
	}
	/**
	 * 获取：安全措施落实(0是      1否)
	 */
	public String getAqcsStatue() {
		return aqcsStatue;
	}
	/**
	 * 设置：设备、工具是否符合安全规定（0是       1否）
	 */
	public void setGjStatue(String gjStatue) {
		this.gjStatue = gjStatue;
	}
	/**
	 * 获取：设备、工具是否符合安全规定（0是       1否）
	 */
	public String getGjStatue() {
		return gjStatue;
	}
	/**
	 * 设置：作业场地以及物品的堆放是否符合安全规范（0是    1否）
	 */
	public void setCdStatue(String cdStatue) {
		this.cdStatue = cdStatue;
	}
	/**
	 * 获取：作业场地以及物品的堆放是否符合安全规范（0是    1否）
	 */
	public String getCdStatue() {
		return cdStatue;
	}
	/**
	 * 设置：个人防护用品、用具是否准备齐全（0是    1否）
	 */
	public void setPersonStatue(String personStatue) {
		this.personStatue = personStatue;
	}
	/**
	 * 获取：个人防护用品、用具是否准备齐全（0是    1否）
	 */
	public String getPersonStatue() {
		return personStatue;
	}
	/**
	 * 设置：是否可靠（是/否）
	 */
	public void setKkStatue(String kkStatue) {
		this.kkStatue = kkStatue;
	}
	/**
	 * 获取：是否可靠（是/否）
	 */
	public String getKkStatue() {
		return kkStatue;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建人
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新人
	 */
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	/**
	 * 获取：更新人
	 */
	public Long getUpdateUserId() {
		return updateUserId;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
