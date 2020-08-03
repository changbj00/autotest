package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ApiUtil;
import utils.ExcelData;
import utils.HttpUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegisterTest {
    @Test(dataProvider = "testdata")
    public void test1(String caseId,String apiId,String requestData,String responseData){
        String url= ApiUtil.getUrl(apiId);
        Map parameters= JSON.parseObject(requestData);
        String type=ApiUtil.getRequestType(apiId);
        String result;
        if ("post".equalsIgnoreCase(type)){
            result= HttpUtil.post(url,parameters);
        }else {
            result= HttpUtil.post(url,parameters);
        }
        System.out.println(result);

    }
    @DataProvider(name = "testdata")
    public Object[][] testdata(){
        ExcelData excelData=new ExcelData();
        return excelData.getdata(1);
    }
}
