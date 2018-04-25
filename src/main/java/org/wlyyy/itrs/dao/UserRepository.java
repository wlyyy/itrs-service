package org.wlyyy.itrs.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wlyyy.common.utils.StringTemplateUtils.St;
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
    User findByCondition(@Param("user") UserQuery queryObject, Pageable page);

    /**
     * 新建用户，忽略id、gmtCreate、gmtModify字段
     * @param user 用户
     */
    @Insert("insert into user(user_name, email, password, salt, sex, department_id, real_name, gmt_create, gmt_modify) values (" +
            "#{userName}, #{email}, #{password}, #{salt}, #{sex}, #{departmentId}, #{realName}, now(), now())")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Long.class)
    void insert(User user);

    // @Update("update user set user_name = #{userName}, password = #{password}, salt = #{salt}, sex = #{sex}, department_id = #{departmentId}, real_name = #{realName}, gmt_modify = now() where id = #{id}")
    @UpdateProvider(type = UserUpdateByIdProvider.class, method = "myMethod")
    int updateById(User user);

    /**
     * 用户查询类
     */
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

        public Long getId() {
            return id;
        }

        public UserQuery setId(Long id) {
            this.id = id;
            return this;
        }

        public String getUserName() {
            return userName;
        }

        public UserQuery setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public UserQuery setEmail(String email) {
            this.email = email;
            return this;
        }

        public Long getDepartmentId() {
            return departmentId;
        }

        public UserQuery setDepartmentId(Long departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public String getRealName() {
            return realName;
        }

        public UserQuery setRealName(String realName) {
            this.realName = realName;
            return this;
        }

        public Date getGmtCreateStart() {
            return gmtCreateStart;
        }

        public UserQuery setGmtCreateStart(Date gmtCreateStart) {
            this.gmtCreateStart = gmtCreateStart;
            return this;
        }

        public Date getGmtCreateEnd() {
            return gmtCreateEnd;
        }

        public UserQuery setGmtCreateEnd(Date gmtCreateEnd) {
            this.gmtCreateEnd = gmtCreateEnd;
            return this;
        }

        public Date getGmtModifyStart() {
            return gmtModifyStart;
        }

        public UserQuery setGmtModifyStart(Date gmtModifyStart) {
            this.gmtModifyStart = gmtModifyStart;
            return this;
        }

        public Date getGmtModifyEnd() {
            return gmtModifyEnd;
        }

        public UserQuery setGmtModifyEnd(Date gmtModifyEnd) {
            this.gmtModifyEnd = gmtModifyEnd;
            return this;
        }
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

    /**
     * 动态查询
     */
    class UserQueryProvider {
        public static String select(@Param("user") UserQuery user, Pageable page) {
            return new UserQueryProvider().getSelect(user, page);
        }

        static String DELIMITER = " and ";

        boolean first = true;
        final StringBuilder builder = new StringBuilder();

        private void tryAppend(Object o, String forAppend) {
            if (Objects.nonNull(o) && !"".equals(o)) {
                if (!first) {
                    builder.append(DELIMITER);
                }
                first = false;
                builder.append(forAppend);
            }
        }

        private String getSelect(UserQuery user, Pageable page) {
            if (user == null) {
                return St.r("select {} from user {}",
                        "id, user_name, email, sex, department_id, real_name, gmt_create, gmt_modify",
                        getPage(page)
                );
            }
            builder.append("select id, user_name, email, sex, department_id, real_name, gmt_create, gmt_modify from user where ");

            String userName = "concat('%', #{user.userName}, '%')";
            String realName = "concat('%', #{user.realName}, '%')";

            tryAppend(user.getId(), "id = #{id}");
            tryAppend(user.getUserName(), "user_name like " + userName);
            tryAppend(user.getEmail(), "email = #{user.email}");
            tryAppend(user.getDepartmentId(), "department_id = #{user.departmentId}");
            tryAppend(user.getRealName(), "real_name like " + realName);
            tryAppend(user.getGmtCreateStart(), "gmt_create >= #{user.gmtCreateStart}");
            tryAppend(user.getGmtCreateEnd(), "gmt_create <= #{user.gmtCreateEnd}");
            tryAppend(user.getGmtModifyStart(), "gmt_modify >= #{user.gmtModifyStart}");
            tryAppend(user.getGmtModifyEnd(), "gmt_modify <= #{user.gmtModifyEnd}");

            // 不能全都是空
            if (first) {
                throw new IllegalArgumentException("One of query condition should be not null");
            }

            builder.append(" ").append(getPage(page));

            return builder.toString();
        }

        private String getPage(Pageable page) {
            return St.r("limit {}, {}", page.getOffset(), page.getPageSize());
        }
    }

}
