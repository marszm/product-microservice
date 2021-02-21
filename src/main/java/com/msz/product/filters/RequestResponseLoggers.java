package com.msz.product.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.TeeOutputStream;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

@Slf4j
@Component
@Order(1)
public class RequestResponseLoggers implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        MyCustomHttpRequestWrapper myCustomHttpRequestWrapper = new MyCustomHttpRequestWrapper((HttpServletRequest) servletRequest);
        log.info("request uri: " + myCustomHttpRequestWrapper.getRequestURI());
        log.info("request method: " + myCustomHttpRequestWrapper.getMethod());
        log.info("request body: " + new String(myCustomHttpRequestWrapper.getByteArray()));

        MyCustomHttpResponseWrapper myCustomHttpResponseWrapper = new MyCustomHttpResponseWrapper((HttpServletResponse) servletResponse);

        filterChain.doFilter(myCustomHttpRequestWrapper, myCustomHttpResponseWrapper);

        log.info("response status: " + myCustomHttpResponseWrapper.getStatus());
        log.info("response body: " + myCustomHttpResponseWrapper.getByteArrayOutputStream().toString());

    }

    private static class MyCustomHttpRequestWrapper extends HttpServletRequestWrapper {

        private final byte[] byteArray;

        public MyCustomHttpRequestWrapper(HttpServletRequest request) {
            super(request);
            try {
                byteArray = IOUtils.toByteArray((request.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException("issue while reading request satream");
            }
        }

        @Override
        public ServletInputStream getInputStream() {
            return new MyDelegatingServletInputStream(new ByteArrayInputStream(byteArray));
        }

        public byte[] getByteArray() {
            return byteArray;
        }
    }

    private static class MyCustomHttpResponseWrapper extends HttpServletResponseWrapper {

        private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        private final PrintStream printStream = new PrintStream(byteArrayOutputStream);

        public MyCustomHttpResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new MyDelegatingServletOutputStream(new TeeOutputStream(super.getOutputStream(), printStream));
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriter(new TeeOutputStream(super.getOutputStream(), printStream));
        }

        public ByteArrayOutputStream getByteArrayOutputStream() {
            return byteArrayOutputStream;
        }
    }
}
