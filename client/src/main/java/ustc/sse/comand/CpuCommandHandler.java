package ustc.sse.comand;

import org.json.JSONObject;

import java.io.*;
import java.util.Scanner;

public class CpuCommandHandler extends SupperCommandHandler implements CommandHandler {
    private static final int CPUTIME = 500;
    private static final int PERCENT = 100;
    private static final int FAULTLENGTH = 10;

    public CpuCommandHandler(String cmdId, String cmd, String data) {
          super(cmdId, cmd, data);
      }

      public Object handler() {
          JSONObject object=new JSONObject();
          JSONObject dataobject=new JSONObject();
          String s1=null;
          try {
              s1=getCpuInfo();
          } catch (IOException e) {
              e.printStackTrace();
          }
          String s2=getMotherboardSN();
          String s3=getCpuRatioForWindows();
          dataobject.put("cpu_Ratio",s3);
          dataobject.put("cpu_Serial",s1);
          dataobject.put("motherBoard",s2);
          object.put("data",dataobject);
          return object;
      }
      /*
      CPU序列号
       */
    private static String getCpuInfo() throws IOException {
        Process process = Runtime.getRuntime().exec(
                new String[] { "wmic", "cpu", "get", "ProcessorId" });
        process.getOutputStream().close();
        Scanner sc = new Scanner(process.getInputStream());
        String property = sc.next();
        String serial = sc.next();
        return serial;
    }

 /*
 获取主板型号
  */
    public static String getMotherboardSN() {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_BaseBoard\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.SerialNumber \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p
                    .getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.trim();
    }
    /*
    cpu使用率
     */
    public static String getCpuRatioForWindows() {

        try {
            String procCmd = System.getenv("windir")
                    + "\\system32\\wbem\\wmic.exe process get Caption,CommandLine,KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
            // 取进程信息
            long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
            Thread.sleep(CPUTIME);
            long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));

            if (c0 != null && c1 != null) {
                long idletime = c1[0] - c0[0];
                long busytime = c1[1] - c0[1];
                return   Double.valueOf(PERCENT * (busytime) * 1.0 / (busytime + idletime)).intValue() + "%";
            } else {
                return   0 + "%";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return  0 + "%";
        }
    }

    private static long[] readCpu(final Process proc) {
        long[] retn = new long[2];
        try {
            proc.getOutputStream().close();
            InputStreamReader ir = new InputStreamReader(proc.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line = input.readLine();
            if (line == null || line.length() < FAULTLENGTH) {
                return null;

            }

            int capidx = line.indexOf("Caption");
            int cmdidx = line.indexOf("CommandLine");
            int rocidx = line.indexOf("ReadOperationCount");
            int umtidx = line.indexOf("UserModeTime");
            int kmtidx = line.indexOf("KernelModeTime");
            int wocidx = line.indexOf("WriteOperationCount");
            long idletime = 0;
            long kneltime = 0;
            long usertime = 0;
            while ((line = input.readLine()) != null) {
                if (line.length() < wocidx) {

                    continue;

                }

                String caption = Bytes.substring(line, capidx, cmdidx - 1).trim();
                String cmd = Bytes.substring(line, cmdidx, kmtidx - 1).trim();
                if (cmd.indexOf("wmic.exe") >= 0) {
                    continue;
                }
                String s1 = Bytes.substring(line, kmtidx, rocidx - 1).trim();
                String s2 = Bytes.substring(line, umtidx, wocidx - 1).trim();
                if (caption.equals("System Idle Process") || caption.equals("System")) {

                    if (s1.length() > 0)

                        idletime += Long.valueOf(s1).longValue();
                    if (s2.length() > 0)
                        idletime += Long.valueOf(s2).longValue();
                    continue;
                }
                if (s1.length() > 0)

                    kneltime += Long.valueOf(s1).longValue();
                if (s2.length() > 0)
                    usertime += Long.valueOf(s2).longValue();
            }
            retn[0] = idletime;
            retn[1] = kneltime + usertime;
            return retn;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                proc.getInputStream().close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;

    }
    static class Bytes {
        public static String substring(String src, int start_idx, int end_idx) {
            byte[] b = src.getBytes();
            String tgt = "";
            for (int i = start_idx; i <= end_idx; i++) {
                tgt += (char) b[i];
            }

            return tgt;
        }
    }


}
