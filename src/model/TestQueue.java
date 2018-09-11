package model;

import java.util.PriorityQueue;

public class TestQueue extends PriorityQueue<TestElement> {

    @Override
    public boolean offer(TestElement element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TestElement poll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TestElement peek() {
        throw new UnsupportedOperationException();
    }
}
