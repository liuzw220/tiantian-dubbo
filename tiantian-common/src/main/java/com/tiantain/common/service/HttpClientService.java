package com.tiantain.common.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiantian.common.bean.HttpResult;

/**
 * 提供已经封装好了的 httpCilent的 请求类型
 * @author schoolBoy 刘志伟
 * @data 2015年5月18日 下午1:22:17
 */
@Service
public class HttpClientService {

    //注入HttpClient
    @Autowired(required=false)
    private CloseableHttpClient httpclient;
    //注入链接参数
    @Autowired(required=false)
    RequestConfig requestConfig;

    /**
     *  执行GET请求
     * @param url 请求的url
     * @return 请求的结果
     * @throws URISyntaxException 
     * @throws ClientProtocolException 
     * @throws IOException
     */
    public String doGet(String url) throws ClientProtocolException, IOException, URISyntaxException  {
        return doGet(url,null);
    }
    /**
     * 执行GET请求
     * @param url 请求的url
     * @param params 请求参数
     * @return 请求的结果
     * @throws ClientProtocolException
     * @throws IOException
     * @throws URISyntaxException
     */
    public String doGet(String url,Map<String, Object> params)
            throws ClientProtocolException, IOException, URISyntaxException {
        URIBuilder uri = new URIBuilder(url);
        if(params!=null&&params.size()>0){
            for (Entry<String, Object> entry : params.entrySet()) {
                uri.addParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        HttpGet httpGet=new HttpGet(uri.build());
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response=null;
        try {
            //执行请求
            response= httpclient.execute(httpGet);
            //返回200
            if(response.getStatusLine().getStatusCode()==200) 
                return EntityUtils.toString(response.getEntity(),"utf-8");
            else
                return null;
        } finally{
            //关闭响应
            response.close();
        }
    }

    /**
     * 执行没有参数的post请求
     * @param url 请求的url地址栏
     * @return 返回请求的结果
     * @throws ClientProtocolException
     * @throws IOException
     */
    public HttpResult doPost(String url) throws ClientProtocolException, IOException{
        return doPost(url,new HashMap<String, Object>());
    }


    /**
     * 提交带参数(表单)的post请求
     * @param url 请求的url地址栏
     * @param params 请求的参数
     * @return 返回请求的结果
     * @throws ClientProtocolException
     * @throws IOException
     */
    public HttpResult doPost(String url,Map<String, Object> params) throws ClientProtocolException, IOException{
        HttpPost httpPost=new HttpPost(url);
        httpPost.setConfig(this.requestConfig);
        //设置post参数
        if(params!=null&&params.size()>0){
            List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
            for(Entry<String, Object> entry :params.entrySet()){
                //添加表单参数
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造一个form表单式的实体
            UrlEncodedFormEntity form=new UrlEncodedFormEntity(parameters);
            httpPost.setEntity(form);
        }
        //设置头信息(伪装成浏览器)
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
        CloseableHttpResponse response=null;
        HttpResult httpResult= new HttpResult();
        try {
            //执行请求
            response= httpclient.execute(httpPost);
            //返回http状态
            httpResult.setCode(response.getStatusLine().getStatusCode());
            if(response.getStatusLine().getStatusCode()==200) 
                //如果是200则返回内容，否则返回http状态
                httpResult.setContent(EntityUtils.toString(response.getEntity(),"utf-8"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            httpResult.setCode(500);
        }finally{
            //关闭响应
            if(response!=null)
                response.close();
        }
        return httpResult;
    }
    /**
     * 提交带参数(json)的post请求
     * @param url
     * @param json
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public HttpResult doPost(String url, String json) throws ClientProtocolException, IOException {
        // 创建http GET请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(this.requestConfig);
        if (null != json) {
            // 构造字符串
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
        }
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpPost);
            HttpResult httpResult = new HttpResult();
            httpResult.setCode(response.getStatusLine().getStatusCode());
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                httpResult.setContent(EntityUtils.toString(response.getEntity(), "UTF-8"));
            }
            return httpResult;
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }
}
