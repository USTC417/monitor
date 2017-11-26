package ustc.sse.tools.entity;

import java.util.Date;

/**
 * 客户机实体类
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/24
 * Time:上午8:14
 */
public class ClientEntity {
    private String clientId;

    private String clientSystem;

    private String clientName;

    private String clientIp;

    private String clientCpu;

    private float clientStorage;

    private float clientRam;

    private String clientLogPath;

    private Date createTime;

    private Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSystem() {
        return clientSystem;
    }

    public void setClientSystem(String clientSystem) {
        this.clientSystem = clientSystem;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientCpu() {
        return clientCpu;
    }

    public void setClientCpu(String clientCpu) {
        this.clientCpu = clientCpu;
    }

    public float getClientStorage() {
        return clientStorage;
    }

    public void setClientStorage(float clientStorage) {
        this.clientStorage = clientStorage;
    }

    public float getClientRam() {
        return clientRam;
    }

    public void setClientRam(float clientRam) {
        this.clientRam = clientRam;
    }

    public String getClientLogPath() {
        return clientLogPath;
    }

    public void setClientLogPath(String clientLogPath) {
        this.clientLogPath = clientLogPath;
    }


}
