package com.humaorie.link;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class LinuxLinkerTest {

    @Test(expected = IllegalArgumentException.class)
    public void targetCannotBeNull() throws IOException {
        final LinuxLinker linuxLinker = new LinuxLinker();
        linuxLinker.createHardLink(null, new File("."));
    }

    @Test(expected = IllegalArgumentException.class)
    public void sourceCannotBeNull() throws IOException {
        final LinuxLinker linuxLinker = new LinuxLinker();
        linuxLinker.createHardLink(new File("."), null);
    }
    
    @Test
    public void canCreateLink() throws IOException {
        final LinuxLinker linuxLinker = new LinuxLinker();
        final File source = File.createTempFile("source", null);
        final File target = new File("/tmp/target");
        source.deleteOnExit();
        target.deleteOnExit();
        linuxLinker.createHardLink(target, source);
        Assert.assertEquals(true, target.exists());
    }
}
