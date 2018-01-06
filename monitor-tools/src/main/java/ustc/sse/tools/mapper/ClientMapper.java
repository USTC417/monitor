package ustc.sse.tools.mapper;


import ustc.sse.tools.entity.ClientEntity;

import java.util.List;

/**
 * 客户机的数据库映射接口
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/24
 * Time:上午8:21
 */
public interface ClientMapper {

    /**
     * 添加一条客户机记录
     * @param entity
     */
    public void add(ClientEntity entity);

    /**
     * 更新客户机信息
     * @param status 修改之后的客户机状态
     * @param clientId 需要修改的客户机的id
     */
    public int update(int status, String clientId);

    /**
     * 删除一个客户机信息
     * @param clientId
     */
    public void delete(String clientId);

    /**
     * 查询一个客户机的信息
     * @param clientId
     */
    public ClientEntity queryInfo(String clientId);

    /**
     * 查询客户机列表
     * @return
     */
    public List<ClientEntity> queryClientList(String status);

    public int updateClientIp(String ip,String clientId);

    public int updateClientInfoByclientId(String clientName,String clientIp,String clientId);
}

