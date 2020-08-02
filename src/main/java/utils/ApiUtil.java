package utils;

import auto.ApiInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ApiUtil {
        static Map<String,ApiInfo> apiInfoMap=new HashMap<String, ApiInfo>();
    static {
        ExcelData excelData=new ExcelData();
        Object[][] datas=excelData.getdata(0);
        for (Object[] data : datas) {
            ApiInfo apiInfo=new ApiInfo(data[0].toString(),data[1].toString(),data[2].toString(),data[3].toString());
            apiInfoMap.put(data[0].toString(),apiInfo);


        }
    }
    public static String getRequestType(String apiId) {
        return apiInfoMap.get(apiId).getType();
    }

    public static String getUrl(String apiId) {
        return apiInfoMap.get(apiId).getUrl();
    }

    public static void main(String[] args) {
        Set set=apiInfoMap.keySet();
        for (Object o : set) {
            System.out.println(o+"   "+apiInfoMap.get(o));
        }
    }
}
