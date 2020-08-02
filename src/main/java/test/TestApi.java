package test;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class TestApi {
    public static void main(String[] args) throws Exception {
        dopost();

    }

    public static void doget() throws IOException {
        CloseableHttpClient httpClient= HttpClients.createDefault();
        String url="https://www.baidu.com/";
        url+="?java";
        HttpGet get=new HttpGet(url);
        CloseableHttpResponse response=httpClient.execute(get);
        String result= EntityUtils.toString(response.getEntity());
        System.out.println(result);
    }
    public static void dopost() throws Exception {
        CloseableHttpClient httpClient=HttpClients.createDefault();
        String url="http://120.78.128.25:8080/futureloan/mvc/api/member/register";
        HttpPost post=new HttpPost(url);
        post.addHeader("","");
        List parameters=new ArrayList();
        parameters.add(new BasicNameValuePair("mobilephone","13555555555"));
        parameters.add(new BasicNameValuePair("pwd","123456"));
        post.setEntity(new UrlEncodedFormEntity(parameters));
        CloseableHttpResponse response = httpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
    }
}
