package cn.hp.shiro.mapper;

import cn.hp.shiro.beans.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.Set;

/**
 * @author Ironhide
 * @create 2020-04-25-22:36
 */
@Repository
public interface UserMapper extends Mapper<User> {

    @Select("select * from user where user_name = #{userName}")
    User login(String userName);

    @Select("select permission_name from permission p\n" +
            "LEFT JOIN role_permission rp ON p.permission_id = rp.permission_id\n" +
            "WHERE rp.role_id = #{roleId}")
    Set<String> getUserPermission(Integer roleId);

}
