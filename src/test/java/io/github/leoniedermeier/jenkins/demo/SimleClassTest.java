package io.github.leoniedermeier.jenkins.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SimleClassTest {

    @Test
    void testDoSomething() {
        SimleClass simleClass = new SimleClass();
        assertEquals("abX", simleClass.doSomething());
    }

}
