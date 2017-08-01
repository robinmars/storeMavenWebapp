package com.weixin.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目名称： SmsMonitorPlate
 * 类名称： MonitorSmsDetail
 * 类描述： 监控项表附属表的实体类
 * 创建人： 钟启辉
 * 创建时间： 2017/4/25 11:13
 *
 * @version V1.0.0.T.1
 * ----------------------------------------------------
 */
public class MonitorSmsDetail implements Serializable {

    /**
     * 主键
     */
    private int sn;

    /**
     * 监控项表主键sn
     */
    private int monitorSmsSn;

    /**
     * 监控节点，多个监控节点逗号隔开
     */
    private String serversSn;

    /**
     * 监控命令
     */
    private String monitorCommand;

    /**
     * 异常出现次数
     */
    private int appearNum;

    /**
     * 下发错误码
     */
    private String errCode;

    /**
     * 状态报告错误码
     */
    private String reportErrCode;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 监控通道代码
     */
    private String tdCode;

    /**
     * 业务代码
     */
    private String ywCode;

    /**
     * 发送量阀值
     */
    private int sendLimit;

    /**
     * 发送率
     */
    private int sendRate;

    /**
     * 成功率
     */
    private int successRate;

    /**
     * 失败率
     */
    private int failRate;

    /**
     * 未知率
     */
    private int unknowRate;

    /**
     * 5s内返回率
     */
    private int reportFiveRate;

    /**
     * 10s内返回率
     */
    private int reportTenRate;

    /**
     * 60s内返回率
     */
    private int reportSixtyRate;

    /**
     * 采集频率
     */
    private int gateFrequency;

    /**
     * 插入时间
     */
    private Date inDate;

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public int getMonitorSmsSn() {
        return monitorSmsSn;
    }

    public void setMonitorSmsSn(int monitorSmsSn) {
        this.monitorSmsSn = monitorSmsSn;
    }

    public String getServersSn() {
        return serversSn;
    }

    public void setServersSn(String serversSn) {
        this.serversSn = serversSn;
    }

    public String getMonitorCommand() {
        return monitorCommand;
    }

    public void setMonitorCommand(String monitorCommand) {
        this.monitorCommand = monitorCommand;
    }

    public int getAppearNum() {
        return appearNum;
    }

    public void setAppearNum(int appearNum) {
        this.appearNum = appearNum;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getReportErrCode() {
        return reportErrCode;
    }

    public void setReportErrCode(String reportErrCode) {
        this.reportErrCode = reportErrCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTdCode() {
        return tdCode;
    }

    public void setTdCode(String tdCode) {
        this.tdCode = tdCode;
    }

    public String getYwCode() {
        return ywCode;
    }

    public void setYwCode(String ywCode) {
        this.ywCode = ywCode;
    }

    public int getSendLimit() {
        return sendLimit;
    }

    public void setSendLimit(int sendLimit) {
        this.sendLimit = sendLimit;
    }

    public int getSendRate() {
        return sendRate;
    }

    public void setSendRate(int sendRate) {
        this.sendRate = sendRate;
    }

    public int getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(int successRate) {
        this.successRate = successRate;
    }

    public int getFailRate() {
        return failRate;
    }

    public void setFailRate(int failRate) {
        this.failRate = failRate;
    }

    public int getUnknowRate() {
        return unknowRate;
    }

    public void setUnknowRate(int unknowRate) {
        this.unknowRate = unknowRate;
    }

    public int getReportFiveRate() {
        return reportFiveRate;
    }

    public void setReportFiveRate(int reportFiveRate) {
        this.reportFiveRate = reportFiveRate;
    }

    public int getReportTenRate() {
        return reportTenRate;
    }

    public void setReportTenRate(int reportTenRate) {
        this.reportTenRate = reportTenRate;
    }

    public int getReportSixtyRate() {
        return reportSixtyRate;
    }

    public void setReportSixtyRate(int reportSixtyRate) {
        this.reportSixtyRate = reportSixtyRate;
    }

    public int getGateFrequency() {
        return gateFrequency;
    }

    public void setGateFrequency(int gateFrequency) {
        this.gateFrequency = gateFrequency;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }
}


