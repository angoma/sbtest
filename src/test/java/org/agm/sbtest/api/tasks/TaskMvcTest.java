package org.agm.sbtest.api.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.agm.sbtest.entities.Task;
import org.agm.sbtest.utils.JsonUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest()
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@AutoConfigureMockMvc
public class TaskMvcTest {

    @Autowired
    private MockMvc mvc;

    private static Long taskId;

    @Test
    public void test1NoTasksFound() throws Exception {
        MvcResult mvcResponse = mvc.perform(get("/tasks")).andExpect(status().isOk()).andReturn();

        List<Task> taskList = JsonUtils.toObject(mvcResponse.getResponse().getContentAsString(),
                new TypeReference<List<Task>>() {
                });

        assertEquals(0, taskList.size());
    }

    @Test
    public void test2TaskNotFound() throws Exception {
        mvc.perform(get("/tasks/{taskId}", 1)).andExpect(status().isNotFound());
    }

    @Test
    public void test3CreateTask() throws Exception {

        Map<String, String> request = new HashMap<>();
        request.put("name", "Task name");
        request.put("content", "Task content");

        MvcResult mvcResponse = mvc.perform(
                post("/tasks").contentType(MediaType.APPLICATION_JSON_VALUE).content(JsonUtils.toJsonObject(request)))
                .andExpect(status().isOk()).andReturn();

        Task task = JsonUtils.toObject(mvcResponse.getResponse().getContentAsString(), new TypeReference<Task>() {
        });

        log.info("response: {}", mvcResponse.getResponse().getContentAsString());
        log.info("task: {}", task);

        assertNotNull(task.getId());
        assertNotNull(task.getCreationDate());
        assertFalse(task.getFinished());
        assertNull(task.getFinishedDate());
        assertEquals("Task name", task.getName());
        assertEquals("Task content", task.getContent());

        taskId = task.getId();
    }

    @Test
    public void test4TaskFound() throws Exception {
        mvc.perform(get("/tasks/{taskId}", taskId)).andExpect(status().isOk());
    }

    @Test
    public void test5UpdateTask() throws Exception {

        Map<String, String> request = new HashMap<>();
        request.put("name", "Task name updated");
        request.put("content", "Task content updated");

        MvcResult mvcResponse = mvc.perform(put("/tasks/{taskId}", taskId).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JsonUtils.toJsonObject(request))).andExpect(status().isOk()).andReturn();

        Task task = JsonUtils.toObject(mvcResponse.getResponse().getContentAsString(), new TypeReference<Task>() {
        });

        assertNotNull(task.getId());
        assertNotNull(task.getCreationDate());
        assertFalse(task.getFinished());
        assertNull(task.getFinishedDate());
        assertEquals("Task name updated", task.getName());
        assertEquals("Task content updated", task.getContent());
    }

    @Test
    public void test6FinishTask() throws Exception {

        MvcResult mvcResponse = mvc.perform(post("/tasks/{taskId}/finish", taskId)).andExpect(status().isOk())
                .andReturn();

        Task task = JsonUtils.toObject(mvcResponse.getResponse().getContentAsString(), new TypeReference<Task>() {
        });

        assertNotNull(task.getId());
        assertNotNull(task.getCreationDate());
        assertTrue(task.getFinished());
        assertNotNull(task.getFinishedDate());
        assertEquals("Task name updated", task.getName());
        assertEquals("Task content updated", task.getContent());
    }

    @Test
    public void test7DeleteTask() throws Exception {

        mvc.perform(delete("/tasks/{taskId}", taskId)).andExpect(status().isNoContent());

        mvc.perform(delete("/tasks/{taskId}", taskId)).andExpect(status().isNotFound());

        mvc.perform(get("/tasks/{taskId}", taskId)).andExpect(status().isNotFound());
    }
}
