package com.waelawada.learn.springboot;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.mockito.MockitoAnnotations;

/**
 * Created by waelawada on 2/28/15.
 */
public class MockitoRule extends TestWatcher {

    private final Object testObject;

    public MockitoRule(Object testObject) {
        this.testObject = testObject;
    }

    @Override
    protected void starting(Description description) {
        MockitoAnnotations.initMocks(testObject);
    }

}