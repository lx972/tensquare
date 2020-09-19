package cn.lx.tensquare.user.service.impl;
import cn.lx.tensquare.user.dao.UserMapper;
import cn.lx.tensquare.user.pojo.User;
import cn.lx.tensquare.user.service.UserService;
import cn.lx.tensquare.utils.IdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:User业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IdWorker idWorker;


    /**
     * User条件+分页查询
     * @param user 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<User> findPage(User user, int page, int size){
        //分页
        Page<User> page1 = new Page<>(page, size);
        //搜索条件构建
        QueryWrapper<User> queryWrapper = createWrapper(user);
        //执行搜索
        Page<User> pageInfo = (Page<User>) userMapper.selectPage(page1, queryWrapper);
        return pageInfo;
    }

    /**
     * User分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<User> findPage(int page, int size){
        //静态分页
        Page<User> page1 = new Page<>(page, size);
        //分页查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Page<User> pageInfo = (Page<User>) userMapper.selectPage(page1, queryWrapper);
        return pageInfo;
    }

    /**
     * User条件查询
     * @param user
     * @return
     */
    @Override
    public List<User> findList(User user){
        //构建查询条件
        QueryWrapper<User> queryWrapper = createWrapper(user);
        //根据构建的条件查询数据
        return userMapper.selectList(queryWrapper);
    }


    /**
     * User构建查询对象
     * @param user
     * @return
     */
    public QueryWrapper<User> createWrapper(User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(user!=null){
            // ID
            if(!StringUtils.isEmpty(user.getId())){
                    queryWrapper.eq("id",user.getId());
            }
            // 手机号码
            if(!StringUtils.isEmpty(user.getMobile())){
                    queryWrapper.eq("mobile",user.getMobile());
            }
            // 密码
            if(!StringUtils.isEmpty(user.getPassword())){
                    queryWrapper.eq("password",user.getPassword());
            }
            // 昵称
            if(!StringUtils.isEmpty(user.getNickname())){
                    queryWrapper.eq("nickname","%"+user.getNickname()+"%");
            }
            // 性别
            if(!StringUtils.isEmpty(user.getSex())){
                    queryWrapper.eq("sex",user.getSex());
            }
            // 出生年月日
            if(!StringUtils.isEmpty(user.getBirthday())){
                    queryWrapper.eq("birthday",user.getBirthday());
            }
            // 头像
            if(!StringUtils.isEmpty(user.getAvatar())){
                    queryWrapper.eq("avatar",user.getAvatar());
            }
            // E-Mail
            if(!StringUtils.isEmpty(user.getEmail())){
                    queryWrapper.eq("email",user.getEmail());
            }
            // 注册日期
            if(!StringUtils.isEmpty(user.getRegdate())){
                    queryWrapper.eq("regdate",user.getRegdate());
            }
            // 修改日期
            if(!StringUtils.isEmpty(user.getUpdatedate())){
                    queryWrapper.eq("updatedate",user.getUpdatedate());
            }
            // 最后登陆日期
            if(!StringUtils.isEmpty(user.getLastdate())){
                    queryWrapper.eq("lastdate",user.getLastdate());
            }
            // 在线时长（分钟）
            if(!StringUtils.isEmpty(user.getOnline())){
                    queryWrapper.eq("online",user.getOnline());
            }
            // 兴趣
            if(!StringUtils.isEmpty(user.getInterest())){
                    queryWrapper.eq("interest",user.getInterest());
            }
            // 个性
            if(!StringUtils.isEmpty(user.getPersonality())){
                    queryWrapper.eq("personality",user.getPersonality());
            }
            // 粉丝数
            if(!StringUtils.isEmpty(user.getFanscount())){
                    queryWrapper.eq("fanscount",user.getFanscount());
            }
            // 关注数
            if(!StringUtils.isEmpty(user.getFollowcount())){
                    queryWrapper.eq("followcount",user.getFollowcount());
            }
        }
        return queryWrapper;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id){
        userMapper.deleteById(id);
    }

    /**
     * 修改User
     * @param user
     */
    @Override
    public void update(User user){
        userMapper.updateById(user);
    }

    /**
     * 增加User
     * @param user
     */
    @Override
    public void add(User user){
        //设置主键值
        user.setId(idWorker.nextId()+"");
        userMapper.insert(user);
    }

    /**
     * 根据ID查询User
     * @param id
     * @return
     */
    @Override
    public User findById(String id){
        return  userMapper.selectById(id);
    }

    /**
     * 查询User全部数据
     * @return
     */
    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }
}
