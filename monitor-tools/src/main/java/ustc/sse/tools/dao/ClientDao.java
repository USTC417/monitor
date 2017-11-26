package ustc.sse.tools.dao;

import org.apache.ibatis.session.SqlSession;
import ustc.sse.tools.entity.ClientEntity;
import ustc.sse.tools.mapper.ClientMapper;

/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/24
 * Time:上午8:47
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

    public ClientEntity queryEntity(String id){
        return mapper.queryInfo(id);
    }

}
