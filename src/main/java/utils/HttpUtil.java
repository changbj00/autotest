package utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpUtil {
    public static String post(String url, Map<String, String> parametersMap) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            Set<String> keyset = parametersMap.keySet();
            for (String key : keyset) {
                parameters.add(new BasicNameValuePair(key, parametersMap.get(key)));
            }
            post.addHeader("", "");
            post.setEntity(new UrlEncodedFormEntity(parameters));
            CloseableHttpResponse response = httpClient.execute(post);
            String result = EntityUtils.toString(response.getEntity());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String get(String url, Map<String,String> parametersMap) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            Set<String> keyset = parametersMap.keySet();
            for (String key : keyset) {
                parameters.add(new BasicNameValuePair(key, parametersMap.get(key)));
            }
            String paramsPart= URLEncodedUtils.format(parameters,"UTF-8");
            url+="?"+paramsPart;
            HttpGet get = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(get);
            String result = EntityUtils.toString(response.getEntity());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
