package com.lab.serverclassify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DownloadResultFileControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
    }

    @Test
    public void downloadFilePublic() throws Exception {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/downloadFile?version=1")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                    .andExpect(MockMvcResultMatchers.status().isOk());
//                    .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void uploadFile() throws Exception {
        String reslut = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/upload")
                .file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello world".getBytes())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(reslut);
    }
}
