package com.epam.task15.controller.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Objects;

public class GZipServletResponseWrapper extends HttpServletResponseWrapper {

    private GZipServletOutputStream gzipOutputStream;
    private PrintWriter printWriter;

    public GZipServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public void close() throws IOException {
        if (Objects.nonNull(printWriter)) {
            printWriter.close();
        }

        if (Objects.nonNull(gzipOutputStream)) {
            gzipOutputStream.close();
        }
    }

    @Override
    public void flushBuffer() throws IOException {
        if (Objects.nonNull(printWriter)) {
            printWriter.flush();
        }

        if (Objects.nonNull(gzipOutputStream)) {
            gzipOutputStream.flush();
        }

        super.flushBuffer();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (Objects.nonNull(printWriter)) {
            throw new IllegalStateException("PrintWriter obtained already - cannot get OutputStream");
        }
        if (Objects.isNull(gzipOutputStream)) {
            gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
        }
        return gzipOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (Objects.isNull(printWriter) && Objects.nonNull(gzipOutputStream)) {
            throw new IllegalStateException("OutputStream obtained already - cannot get PrintWriter");
        }
        if (Objects.isNull(printWriter)) {
            gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
            printWriter = new PrintWriter(new OutputStreamWriter(gzipOutputStream, getResponse().getCharacterEncoding()));
        }
        return printWriter;
    }


    @Override
    public void setContentLength(int length) {
        // ignore, since content length of zipped content
        // does not match content length of unzipped content.
    }
}
