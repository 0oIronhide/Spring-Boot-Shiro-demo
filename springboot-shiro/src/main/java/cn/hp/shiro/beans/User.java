package cn.hp.shiro.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ironhide
 * @create 2020-04-25-22:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer userId;
    private String userNo;
    private String userName;
    private String userPassword;
    private Integer roleId;
    private Integer deptId;

}
