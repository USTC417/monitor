package ustc.sse.mapper;


import ustc.sse.tools.entity.ClientEntity;

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
     * @param entity 修改之后的客户机实体
     * @param clientId 需要修改的客户机的id
     */
    public void update(ClientEntity entity,String clientId);

    /**
     * 删除一个客户机信息
     * @param clientId
     */
    public void delete(String clientId);


}

