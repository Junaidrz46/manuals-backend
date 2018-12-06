package se.agilecourse.test.users;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.google.gson.reflect.TypeToken;
import se.agilecourse.SpringBootApp;
import se.agilecourse.model.User;
import se.agilecourse.test.utils.TestUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
@AutoConfigureMockMvc
public class UsersTests {

    Logger logger = LoggerFactory.getLogger(UsersTests.class);

    @Autowired
    private MockMvc mvc;

    @Test
    public void createCompanyRepresentative() throws Exception {
        String uri = "/rest/users/saveCompanyRepresentative";
        User user = new User();
        user.setFirstname("FName");
        user.setLastName("LName");
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        user.setCompanyId("5c07ceb31c1f1ff2ce6e8a72");
        String inputJson = TestUtils.objectToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .header("Origin","*")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        User returnUser = TestUtils.jsonToObject(content,User.class);
        assertEquals(user.getFirstname(), "FName");
    }

    @Test
    public void findUserByRole() throws Exception {
        String uri = "/rest/users/findUsersByRole";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .header("Origin","*")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("role","representative")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        logger.info(content);

        ObjectMapper mapper = new ObjectMapper();
        List<User> listUser = mapper.readValue(content, new TypeReference<List<User>>(){});
        logger.info("List Size : "+listUser.size());
        assertEquals(listUser.get(0).getRole(), "representative");
    }


}
