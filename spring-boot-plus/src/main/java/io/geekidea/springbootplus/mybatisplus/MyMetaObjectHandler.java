package io.geekidea.springbootplus.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import io.geekidea.springbootplus.shiro.util.JwtTokenUtil;
import io.geekidea.springbootplus.shiro.util.JwtUtil;
import io.geekidea.springbootplus.shiro.util.LoginUtil;
import io.geekidea.springbootplus.shiro.vo.LoginSysUserRedisVo;
import io.geekidea.springbootplus.system.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {



    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("创建自动填充");
        // 获取user对象
        Long userId = LoginUtil.getUserId();
        this.strictInsertFill(metaObject,"createId",Long.class,userId);
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
//        this.fillStrategy(metaObject, "createTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug请升级到之后的版本如`3.3.1.8-SNAPSHOT`)
        /* 上面选其一使用,下面的已过时(注意 strictInsertFill 有多个方法,详细查看源码) */
        //this.setFieldValByName("operator", "Jerry", metaObject);
        //this.setInsertFieldValByName("operator", "Jerry", metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新自动填充");
        // 获取user对象
        Long userId = LoginUtil.getUserId();
        this.strictInsertFill(metaObject,"updateId",LoginSysUserRedisVo.class,userId);
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
//        this.fillStrategy(metaObject, "updateTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug请升级到之后的版本如`3.3.1.8-SNAPSHOT`)
        /* 上面选其一使用,下面的已过时(注意 strictUpdateFill 有多个方法,详细查看源码) */
        //this.setFieldValByName("operator", "Tom", metaObject);
        //this.setUpdateFieldValByName("operator", "Tom", metaObject);
    }
}