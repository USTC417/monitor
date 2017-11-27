package ustc.sse.tools.dao;

import org.apache.ibatis.executor.ReuseExecutor;
import org.apache.ibatis.session.SqlSession;
import ustc.sse.tools.entity.ClientEntity;
import ustc.sse.tools.entity.UserEntity;
import ustc.sse.tools.mapper.ClientMapper;
import ustc.sse.tools.mapper.UserMapper;

/**
 * 作者：陈志国
 * 时间：2017/11/26
 */
public class UserDao {

    private SqlSession session;

    private UserMapper mapper;
    public UserDao(){
        session = DbOperator.getSession();
        mapper = session.getMapper(UserMapper.class);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    public boolean addUser(UserEntity user){
        boolean result = false;
        try {
           result =  mapper.addUser(user) == 1?true:false;
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public boolean updateUserById(UserEntity user) {
        boolean result = false;
        try {
            result = mapper.updateUserById(user)==1?true:false;
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 根据用户id删除用户
     * @param id
     * @return
     */
    public boolean deleteUserById(String id){
        boolean result = false;
        try {
            result = mapper.deleteUserById(id)==1?true:false;
            session.commit();
        }catch (Exception e ){
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public UserEntity queryUserByNameandPwd(String username, String password)throws Exception{
        return mapper.queryUser(username,password);
    }

}
