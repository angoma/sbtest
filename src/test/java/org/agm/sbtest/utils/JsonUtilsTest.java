package org.agm.sbtest.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.agm.sbtest.api.tasks.TaskRequest;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

public class JsonUtilsTest {

    @Test
    public void toJsonObjectTest1() {
        assertNull(JsonUtils.toJsonObject(null));
    }

    @Test
    public void toJsonObjectTest2() {
        assertEquals("{}", JsonUtils.toJsonObject(new Object()));
    }

    @Test
    public void toJsonObjectTest3() {
        TaskRequest request = new TaskRequest();
        request.setName("name");
        request.setContent("content");

        assertEquals("{\"name\":\"name\",\"content\":\"content\"}", JsonUtils.toJsonObject(request));
    }

    @Test
    public void toObjectTest1() {
        TaskRequest request = JsonUtils.toObject("{\"name\":\"name\",\"content\":\"content\"}",
                new TypeReference<TaskRequest>() {
                });
        assertEquals("name", request.getName());
        assertEquals("content", request.getContent());
    }

    @Test
    public void toObjectTest2() {
        assertNull(JsonUtils.toObject(null, new TypeReference<TaskRequest>() {
        }));
        assertNull(JsonUtils.toObject("", new TypeReference<TaskRequest>() {
        }));
    }
}
