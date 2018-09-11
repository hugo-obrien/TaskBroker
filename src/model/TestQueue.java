package model;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

public class TestQueue {

    private TestQueueElement first;

    public void add(Pair<LocalDateTime, Callable> pair) {
        TestQueueElement testQueueElement = new TestQueueElement(pair.getKey(), pair.getValue());
        if (first == null) {
            first = testQueueElement;
        } else {
            int compareResult = first.getDateTime().compareTo(testQueueElement.getDateTime());
            if (compareResult <= 0) {
                first.add(testQueueElement);
            } else {
                TestQueueElement oldFirst = this.first;
                first = testQueueElement;
                first.setNext(oldFirst);
            }
        }
    }

    public Pair<LocalDateTime, Callable> poll() {
        Pair<LocalDateTime, Callable> pair = new Pair<>(first.getDateTime(), first.getValue());
        first = first.getNext();
        return pair;
    }

    public TestQueue(TestQueueElement first) {
        this.first = first;
    }

    public TestQueue() {
        first = null;
    }

    class TestQueueElement {
        private LocalDateTime dateTime;
        private Callable value;
        private TestQueueElement next;

        void add(TestQueueElement addedElement) {
            if (next == null) {
                next = addedElement;
            } else {
                LocalDateTime addedDateTime = addedElement.getDateTime();
                int compareResult = dateTime.compareTo(addedDateTime);
                if (compareResult <= 0) {
                    next.add(addedElement);
                } else {
                    TestQueueElement oldNext = this.next;
                    next = addedElement;
                    next.setNext(oldNext);
                }
            }
        }

        LocalDateTime getDateTime() {
            return dateTime;
        }

        Callable getValue() {
            return value;
        }

        TestQueueElement getNext() {
            return next;
        }

        void setNext(TestQueueElement next) {
            this.next = next;
        }

        TestQueueElement(LocalDateTime key, Callable value) {
            this.dateTime = key;
            this.value = value;
            next = null;
        }
    }
}
