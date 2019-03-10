package com.lab.serverclassify;


import org.junit.Test;
import sun.nio.ch.FileChannelImpl;

import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MappedByteBufferTest {

    /**
     * 内存映射 速度为0.6s ~ 1s
     *
     * @throws Exception
     */
    @Test
    public void mbbTest() throws Exception {
        Instant t1 = Instant.now();
        // 通过 RandomAccessFile 创建对应的文件操作类，第二个参数 rw 代表该操作类可对其做读写操作
        RandomAccessFile raf = new RandomAccessFile("1.txt", "rw");

        // 获取操作文件的通道
        FileChannel fc = raf.getChannel();

        byte[] bytes = "荣耀之于生死，救赎之于荣耀\n".getBytes();

        long GB = 1024 * 1024 * 1024L;

        long num = GB / bytes.length;

        long len = bytes.length * num;
        System.out.println(num + "  " + len);
        List<String> list = new ArrayList<>();
        list.add("wowowo");
        list.add("tatata");

        int count = 0;
        // 把文件映射到内存
        MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_WRITE, 0, GB);
        // 读写文件
//        for (int i = 0; i < num; i++) {
//            if (buffer.remaining() < bytes.length) {
//                count += buffer.position();
//                buffer.position(count);
//            }
//            buffer.put(bytes);
//        }
        buffer.put(bytes);
        Instant t2 = Instant.now();
        System.out.println("over");
        Method m = FileChannelImpl.class.getDeclaredMethod("unmap", MappedByteBuffer.class);
        m.setAccessible(true);
        m.invoke(FileChannelImpl.class, buffer);
        Instant t3 = Instant.now();
        System.out.println(Duration.between(t1, t2).toMillis() + "  " + Duration.between(t1, t3).toMillis());
    }

    /**
     * fileoutputstream 46777=47s
     *
     * @throws Exception
     */
    @Test
    public void byteFile() throws Exception {
        Instant t1 = Instant.now();
        FileOutputStream fos = new FileOutputStream("1.txt");

        byte[] bytes = "荣耀之于生死，救赎之于荣耀\n".getBytes();

        Long GB = 1024 * 1024 * 1024L;

        Long num = GB / bytes.length;

        Long len = bytes.length * num;

        for (int i = 0; i < num; i++) {
            fos.write(bytes);
        }
        Instant t2 = Instant.now();
        fos.close();
        Instant t3 = Instant.now();
        System.out.println(Duration.between(t1, t2).toMillis() + "  " + Duration.between(t1, t3).toMillis());

    }
}
