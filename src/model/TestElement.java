package model;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

public class TestElement {

    private LocalDateTime localDateTime;
    private Callable callableElement;

    public TestElement(LocalDateTime localDateTime, Callable callableElement) {
        this.localDateTime = localDateTime;
        this.callableElement = callableElement;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Callable getCallableElement() {
        return callableElement;
    }
}
