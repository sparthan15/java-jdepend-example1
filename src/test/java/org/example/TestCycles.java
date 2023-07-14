package org.example;

import jdepend.framework.JDepend;
import jdepend.framework.JavaPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Logger;

public class TestCycles {
    private JDepend jdepend = new JDepend();
    private Collection<JavaPackage> packages;
    private Logger LOG = Logger.getLogger("TestCycles.class");

    @BeforeEach
    public void setUp() throws IOException {
        jdepend.addDirectory("target/classes");
        packages = jdepend.analyze();
    }

    @Test
    void cycleTest() {
        for (Object p : packages) {
            JavaPackage pack1 = (JavaPackage) p;
            Assertions.assertFalse(pack1.containsCycle(), pack1.getName() + " failed.");
            LOG.info(String.format("%s analyzed for cycles. OK", pack1.getName()));
        }
    }

    @Test
    void test() throws IOException {
        JDepend jdepend = new JDepend();
        jdepend.addDirectory("target/classes/org/example/controller");
        jdepend.addDirectory("target/classes/org/example/model");
        jdepend.addDirectory("target/classes/org/example/view");
        jdepend.analyze();
        Assertions.assertFalse(jdepend.containsCycles());
    }
}
