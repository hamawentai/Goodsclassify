package com.lab.serverclassify.utils;


import com.lab.serverclassify.pojo.bo.ResultFileBo;
import com.lab.serverclassify.pojo.bo.ResultFileRowBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sun.nio.ch.FileChannelImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * @author weixun
 */
@Service
@Slf4j
public class CreateResultFileUtil {

    public void mppCreateFile(ResultFileBo resultFileBo, String fileName) {
        Instant t1 = Instant.now();
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(fileName, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert raf != null;
        FileChannel fc = raf.getChannel();

        byte[] tab = "\t".getBytes();
        byte[] line = "\n".getBytes();
        List<ResultFileRowBO> rowBOS = resultFileBo.getRows();
        long len = (tab.length + line.length) * rowBOS.size() + resultFileBo.getLen();
        MappedByteBuffer buffer = null;
        try {
            buffer = fc.map(FileChannel.MapMode.READ_WRITE, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (ResultFileRowBO bo : rowBOS) {
            assert buffer != null;
            buffer.put(bo.getLabel());
            buffer.put(tab);
            buffer.put(bo.getDescribe());
            buffer.put(line);
        }
        Instant t2 = Instant.now();
        log.info("file size  " + len / 1024 + "kb");
        try {
            Method m = null;
            m = FileChannelImpl.class.getDeclaredMethod("unmap", MappedByteBuffer.class);
            m.setAccessible(true);
            m.invoke(FileChannelImpl.class, buffer);
            fc.close();
            raf.close();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | IOException e) {
            e.printStackTrace();
        }
        Instant t3 = Instant.now();
        log.info("create file & close resource time consuming " + Duration.between(t1, t2).toMillis() + "  " + Duration.between(t1, t3).toMillis());
    }
}
