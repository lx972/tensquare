package cn.lx.tensquare.user.service.impl;
import cn.lx.tensquare.user.dao.AdminMapper;
import cn.lx.tensquare.user.pojo.Admin;
import cn.lx.tensquare.user.service.AdminService;
import cn.lx.tensquare.utils.IdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:Admin业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private IdWorker idWorker;


    /**
     * Admin条件+分页查询
     * @param admin 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Admin> findPage(Admin admin, int page, int size){
        //分页
        Page<Admin> page1 = new Page<>(page, size);
        //搜索条件构建
        QueryWrapper<Admin> queryWrapper = createWrapper(admin);
        //执行搜索
        Page<Admin> pageInfo = (Page<Admin>) adminMapper.selectPage(page1, queryWrapper);
        return pageInfo;
    }

    /**
     * Admin分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Admin> findPage(int page, int size){
        //静态分页
        Page<Admin> page1 = new Page<>(page, size);
        //分页查询
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        Page<Admin> pageInfo = (Page<Admin>) adminMapper.selectPage(page1, queryWrapper);
        return pageInfo;
    }

    /**
     * Admin条件查询
     * @param admin
     * @return
     */
    @Override
    public List<Admin> findList(Admin admin){
        //构建查询条件
        QueryWrapper<Admin> queryWrapper = createWrapper(admin);
        //根据构建的条件查询数据
        return adminMapper.selectList(queryWrapper);
    }


    /**
     * Admin构建查询对象
     * @param admin
     * @return
     */
    public QueryWrapper<Admin> createWrapper(Admin admin){
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        if(admin!=null){
            // ID
            if(!StringUtils.isEmpty(admin.getId())){
                    queryWrapper.eq("id",admin.getId());
            }
            // 登陆名称
            if(!StringUtils.isEmpty(admin.getLoginname())){
                    queryWrapper.eq("loginname","%"+admin.getLoginname()+"%");
            }
            // 密码
            if(!StringUtils.isEmpty(admin.getPassword())){
                    queryWrapper.eq("password",admin.getPassword());
            }
            // 状态
            if(!StringUtils.isEmpty(admin.getState())){
                    queryWrapper.eq("state",admin.getState());
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
        adminMapper.deleteById(id);
    }

    /**
     * 修改Admin
     * @param admin
     */
    @Override
    public void update(Admin admin){
        adminMapper.updateById(admin);
    }

    /**
     * 增加Admin
     * @param admin
     */
    @Override
    public void add(Admin admin){
        //设置主键值
        admin.setId(idWorker.nextId()+"");
        adminMapper.insert(admin);
    }

    /**
     * 根据ID查询Admin
     * @param id
     * @return
     */
    @Override
    public Admin findById(String id){
        return  adminMapper.selectById(id);
    }

    /**
     * 查询Admin全部数据
     * @return
     */
    @Override
    public List<Admin> findAll() {
        return adminMapper.selectList(null);
    }
}
