package ustc.sse.comand;
/*
返回client的内存信息
 */

import com.sun.management.*;
import org.json.JSONObject;

import java.lang.management.ManagementFactory;

public class MemoryCommandHandler extends SupperCommandHandler implements CommandHandler{
    long totalPhysicalMemory,freePhysicalMemorySize;
    Double compare;
    String str;
    public MemoryCommandHandler(String cmdId, String cmd, String data) {super(cmdId,cmd,data); }
    public Object handler()
    {
        JSONObject object=new JSONObject();
        JSONObject objectdata=new JSONObject();
        this.getmemery();
        objectdata.put("TPM",totalPhysicalMemory);
        objectdata.put("fpm",freePhysicalMemorySize);
        objectdata.put("compare",str);
        object.put("data",objectdata);
        return object;
    }
    public void getmemery()
    {
        String osname=System.getProperty("os.name");
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        totalPhysicalMemory=osmxb.getTotalPhysicalMemorySize();
        freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
        double usedphysicalmemory=totalPhysicalMemory-freePhysicalMemorySize;
        compare = (Double) (usedphysicalmemory/totalPhysicalMemory)*100;
        str = compare.doubleValue() + "%";
    }


}
