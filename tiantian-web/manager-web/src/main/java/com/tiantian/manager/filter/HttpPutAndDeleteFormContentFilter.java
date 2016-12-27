package com.tiantian.manager.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.filter.HttpPutFormContentFilter;

public class HttpPutAndDeleteFormContentFilter extends HttpPutFormContentFilter {

    private final FormHttpMessageConverter formConverter = new AllEncompassingFormHttpMessageConverter();
    @Override
    public void setCharset(Charset charset) {
        super.setCharset(charset);
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
       /* if (("delete".equalsIgnoreCase(request.getMethod()) || "PATCH".equals(request.getMethod())) && isFormContentType(request)) {
            HttpInputMessage inputMessage = new ServletServerHttpRequest(request) {
                @Override
                public InputStream getBody() throws IOException {
                        return request.getInputStream();
                }
                MultiValueMap<String, String> formParameters = formConverter.read(null, inputMessage);
                HttpServletRequest wrapper = new HttpPutFormContentRequestWrapper(request, formParameters);
                filterChain.doFilter(wrapper, response);
        };
            
        }*/
            super.doFilterInternal(request, response, filterChain);
    }

    private boolean isFormContentType(HttpServletRequest request) {
        String contentType = request.getContentType();
        if (contentType != null) {
                try {
                        MediaType mediaType = MediaType.parseMediaType(contentType);
                        return (MediaType.APPLICATION_FORM_URLENCODED.includes(mediaType));
                }
                catch (IllegalArgumentException ex) {
                        return false;
                }
        }
        else {
                return false;
        }
}

}
