package org.wlyyy.itrs.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Page;
import org.wlyyy.itrs.domain.User;

import java.util.Date;
import java.util.Objects;

/**
 * 买票e
 *
 * @author wlyyy
 */
@Mapper
public interface UserRepository {

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Long id);

    @SelectProvider(type = UserQueryProvider.class, method = "select")
    Page<User> findByCondition(UserQuery queryObject);

    @Insert("insert into user(user_name, email, password, salt, sex, department_id, real_name, gmt_create, gmt_modify) values (" +
            "#{userName}, #{email}, #{password}, #{salt}, #{sex}, #{departmentId}, #{realName}, now(), now())")
    void insert(User user);

    // @Update("update user set user_name = #{userName}, password = #{password}, salt = #{salt}, sex = #{sex}, department_id = #{departmentId}, real_name = #{realName}, gmt_modify = now() where id = #{id}")
    @UpdateProvider(type = UserUpdateByIdProvider.class, method = "myMethod")
    int updateById(User user);

    class UserQuery {
        private Long id;
        private String userName;
        private String email;
        private Long departmentId;
        private String realName;
        private Date gmtCreateStart;
        private Date gmtCreateEnd;
        private Date gmtModifyStart;
        private Date gmtModifyEnd;

    }

    /**
     * Update动态SQL
     */
    class UserUpdateByIdProvider {

        public static String myMethod(User user) {
            return new UserUpdateByIdProvider().getUpdate(user);
        }


        boolean first = true;
        final StringBuilder builder = new StringBuilder();

        private void tryAppend(Object o, String forAppend) {
            if (Objects.nonNull(o) && !"".equals(o)) {
                if (!first) {
                    builder.append(", ");
                }
                first = false;
                builder.append(forAppend);
            }
        }

        String getUpdate(User user) {
            Objects.requireNonNull(user.getId(), "Cannot update when id is null");

            builder.append("update user set ");

            tryAppend(user.getUserName(), "user_name = #{userName}");
            tryAppend(user.getEmail(), "email = #{email}");
            tryAppend(user.getPassword(), "password = #{password}");
            tryAppend(user.getSalt(), "salt = #{salt}");
            tryAppend(user.getSex(), "sex = #{sex}");
            tryAppend(user.getDepartmentId(), "department_id = #{departmentId}");
            tryAppend(user.getRealName(), "real_name = #{realName}");

            // 不能全都是空
            if (first) {
                throw new IllegalArgumentException("One of [userName, email, password, salt, sex, departmentId, realName] should be not null");
            }

            builder.append(", gmt_modify = now() where id = #{id}");

            return builder.toString();
        }
    }

    class UserQueryProvider {
    }
}
