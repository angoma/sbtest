package org.agm.sbtest.exceptions;

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7495996137320294185L;

    public EntityNotFoundException(String type, Object id) {
        super("Entity " + type + " " + id + " not found");
    }

}
