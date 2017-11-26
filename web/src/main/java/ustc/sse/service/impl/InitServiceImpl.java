package ustc.sse.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ustc.sse.service.InitService;
import ustc.sse.tools.dao.ClientDao;
import ustc.sse.tools.entity.ClientEntity;
import ustc.sse.tools.util.Util;

import java.util.Date;

/**
 * 给客户机初始化服务的实现类
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/25
 * Time:下午12:27
 */
@Service
public class InitServiceImpl implements InitService{
    public String init(JSONObject data) {
        JSONObject object = new JSONObject();
        try {
            // 尝试获取所需的参数，若获取成功则返回UUID，否则返回错误信息
            //获取操作系统名称
            String system = data.getString("system");
            String cpu = data.getString("cpu");
            float ram = (float) data.getDouble("ram");
            float storage = (float) data.getDouble("storage");
            String uuid = Util.createId();
            object.put("status",200);
            object.put("error_msg","success");
            object.put("uuid",uuid);
            // 存储到数据库
            ClientEntity entity = new ClientEntity();
            entity.setClientSystem(system);
            entity.setStatus(1);
            entity.setClientCpu(cpu);
            entity.setClientSystem(system);
            entity.setClientRam(ram);
            entity.setCreateTime(new Date());
            entity.setClientStorage(storage);
            entity.setClientId(uuid);
            //entity = (ClientEntity) Util.parseJavaObject(data.toString(),ClientEntity.class);
            ClientDao dao = new ClientDao();
            dao.add(entity);
        }catch (Exception e){
            object.put("status","500");
            object.put("error_msg",e.toString());
        }

        return object.toString();
    }


}
