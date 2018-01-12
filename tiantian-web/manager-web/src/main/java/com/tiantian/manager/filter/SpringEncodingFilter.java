package com.tiantian.manager.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.CharacterEncodingFilter;
/**
 *自定义乱码处理过滤器(重写了spring自带的乱码过滤器)
 *增强了spring乱码处理的功能，，能对各种请求处理，包括，get，put，deletel，请求
 * @author schoolBoy
 *
 */
public class SpringEncodingFilter extends CharacterEncodingFilter {

    private String encoding;
    //重写编码格式
    @Override
    public void setEncoding(String encoding) {
        super.setEncoding(encoding);
        System.out.println("初始化、 、 、"+this.getClass().getName()+"encoding="+encoding);
        this.encoding=encoding;
    }
    //是否启用编码(如果设置为false，则不进行任何的编码设置)
    @Override
    public void setForceEncoding(boolean forceEncoding) {
        super.setForceEncoding(forceEncoding);
    }

    //对指定的请求编码
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        //如果是post的请求
        if(request.getMethod().equalsIgnoreCase("post")){
            super.doFilterInternal(request, response, filterChain);}
        else{
            HttpServletRequest myrequest = new SpringRequest(request,encoding);
            filterChain.doFilter(myrequest, response);
        }
    }
}

//自定义request对象
class SpringRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;

    private boolean hasEncode;

    private String encoding;

    public SpringRequest(HttpServletRequest request,String encoding) {
        super(request);// super必须写
        this.request = request;
        this.encoding=encoding;
    }

    // 对需要增强方法 进行覆盖
    @Override
    public Map getParameterMap() {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (!hasEncode) { // 确保get手动编码逻辑只运行一次
            for (String parameterName : parameterMap.keySet()) {
                String[] values = parameterMap.get(parameterName);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        try {
                            values[i] = new String(values[i]
                                    .getBytes("ISO-8859-1"), encoding);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            hasEncode = true;
        }
        return parameterMap;
    }

    @Override
    public String getParameter(String name) {
        Map<String, String[]> parameterMap = getParameterMap();
        String[] values = parameterMap.get(name);
        if (values == null) {
            return null;
        }
        return values[0]; // 取回参数的第一个值
    }

    @Override
    public String[] getParameterValues(String name) {
        Map<String, String[]> parameterMap = getParameterMap();
        String[] values = parameterMap.get(name);
        return values;
    }

}
