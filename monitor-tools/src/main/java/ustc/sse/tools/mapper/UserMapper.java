package ustc.sse.tools.mapper;

import ustc.sse.tools.entity.UserEntity;

/**
 * 作者：陈志国
 * 时间：2017/11/26
 */
public interface UserMapper {
    public UserEntity queryUser(String username,String password) throws Exception;

    /**
     * 添加用户
     * @param userEntity
     * @return
     * @throws Exception
     */
    public int addUser(UserEntity userEntity) throws Exception;

    /**
     * 更新用户
     * @param entity
     * @return
     * @throws Exception
     */
    public int updateUserById(UserEntity entity) throws Exception;

    /**
     * 根据用户id删除用户
     * @param id
     * @return
     * @throws Exception
     */
    public int deleteUserById(String id) throws Exception;

}
