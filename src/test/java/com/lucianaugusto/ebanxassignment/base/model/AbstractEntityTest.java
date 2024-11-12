package com.lucianaugusto.ebanxassignment.base.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class AbstractEntityTest {

    @Test
    void getId() throws NoSuchFieldException, IllegalAccessException {
        Long id = 777L;
        DummyEntity dummyEntity = new DummyEntity();
        Field idField = dummyEntity.getClass().getSuperclass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(dummyEntity, id);

        Assertions.assertEquals(id, dummyEntity.getId());
    }

    private class DummyEntity extends AbstractEntity {
        // NTD
    }

}