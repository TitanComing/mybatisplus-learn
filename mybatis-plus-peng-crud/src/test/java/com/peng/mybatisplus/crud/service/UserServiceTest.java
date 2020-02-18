package com.peng.mybatisplus.crud.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.peng.mybatisplus.crud.entity.User;
import com.peng.mybatisplus.crud.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by peng on 2020/2/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void selectOneService() {
        User user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getId,  1));
        assertThat(user.getName()).isEqualTo("Jone");
    }

    @Test
    public void saveOrUpdateBatchService() {
        User user1 = new User();
        user1.setName("test1");
        user1.setAge(15);
        user1.setEmail("test1@qq.com");

        User user2 = new User();
        user2.setName("test2");
        user2.setAge(16);
        user2.setEmail("test2@qq.com");

        userService.saveOrUpdateBatch(Arrays.asList(user1,user2));
        assertThat(userService.getOne(Wrappers.<User>query().eq("name","test1")).getEmail()).isEqualTo("test1@qq.com");
    }

    @Test
    public void list() {
        List<User> userList = userService.list(Wrappers.<User>query().likeLeft("email","@baomidou.com"));
        userList.forEach(System.out::println);
        assertThat(userList.size()).isGreaterThan(0);
    }

    @Test
    public void pageService() {
        IPage<User> iPage = userService.page(new Page<>(1,3), Wrappers.<User>query().between("age",20,25));
        System.out.println(iPage.getRecords());
        assertThat(iPage.getRecords().size()).isGreaterThan(0);
    }

    @Test
    public void countService() {
        assertThat(userService.count(new QueryWrapper<User>().lambda().eq(User::getEmail, "test1@baomidou.com"))).isEqualTo(1);
    }

    @Test
    public void updateByIdService() {
        userService.updateById(new User().setId(2L).setEmail("test2haha@qq.com"));
        assertThat(userService.getById(2L).getEmail()).isEqualTo("test2haha@qq.com");
    }

    @Test
    public void removeService() {
        assertThat(userService.remove(new QueryWrapper<User>().lambda().eq(User::getId, 3L))).isTrue();
    }

    @Test
    public void removeByIdsService() {
        assertThat(userService.removeByIds(Arrays.asList(4L, 5L))).isTrue();
    }

}
