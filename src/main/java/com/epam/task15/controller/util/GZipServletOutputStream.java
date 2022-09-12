package com.epam.task15.controller.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

class GZipServletOutputStream extends ServletOutputStream {
    private final GZIPOutputStream gzipOutputStream;

    public GZipServletOutputStream(OutputStream output) throws IOException {
        gzipOutputStream = new GZIPOutputStream(output);
    }

    @Override
    public void close() throws IOException {
        gzipOutputStream.close();
    }

    @Override
    public void flush() throws IOException {
        gzipOutputStream.flush();
    }

    @Override
    public void write(byte[] b) throws IOException {
        gzipOutputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        gzipOutputStream.write(b, off, len);
    }

    @Override
    public void write(int b) throws IOException {
        gzipOutputStream.write(b);
    }

    @Override
    public boolean isReady() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        throw new UnsupportedOperationException();
    }
}
