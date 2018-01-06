package ustc.sse.tools.dao;

import org.apache.ibatis.session.SqlSession;
import ustc.sse.tools.entity.ClientEntity;
import ustc.sse.tools.mapper.ClientMapper;

import java.util.List;

/** 客户端操作dao
 * 作者：陈志国
 * 时间：2017/11/26
 */
public class ClientDao {

    private SqlSession session;

    private ClientMapper mapper;
    public ClientDao(){
        session = DbOperator.getSession();
        mapper = session.getMapper(ClientMapper.class);
    }
    public void add(ClientEntity entity){

        mapper.add(entity);
        session.commit();
    }

    /**
     * 根据客户端的id查询客户端的详细信息
     * @param id
     * @return
     */
    public ClientEntity queryEntity(String id){
        return mapper.queryInfo(id);
    }

    /**
     * 查询客户端列表
     * @param status
     * @return
     */
    public List<ClientEntity> queryClientList(String status){ return mapper.queryClientList(status);}


    /**
     * 更新客户端的状态
     * @param status
     * @param clientId
     * @return
     */
    public int updateClient(int status,String clientId){
        int s = mapper.update(status, clientId);
        session.commit();
        return s;
    }
    public int updateClientIp(String ip,String clientId){
        int s = mapper.updateClientIp(ip, clientId);
        session.commit();
        return s;
    }

    public int updateClientInfoByclientId(String clientName,String clientIp,String clientId){
        int result = mapper.updateClientInfoByclientId(clientName,clientIp,clientId);
        session.commit();
        return result;
    }
}
