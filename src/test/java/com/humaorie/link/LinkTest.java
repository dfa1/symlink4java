package com.humaorie.link;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkTest {

    private static final File SOURCE = new File("source");
    private static final File TARGET = new File("target");

    private void writeTo(File aFile, String content) throws IOException {
        FileWriter fileWriter = new FileWriter(aFile);
        fileWriter.write(content);
        fileWriter.close();
    }

    private String readFrom(File aFile) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(aFile));
        String content = fileReader.readLine();
        fileReader.close();
        return content;
    }

    @Before
    public void setUp() throws IOException {
        writeTo(SOURCE, "test");
    }

    @After
    public void tearDown() {
        SOURCE.deleteOnExit();
        TARGET.deleteOnExit();
    }

    @Test
    public void hardLink() {
        Link.create().hard(TARGET, SOURCE);
        Assert.assertTrue(TARGET.exists() && TARGET.isFile());
    }

    @Test
    public void content() throws Exception {
        Link.create().hard(TARGET, SOURCE);
        Assert.assertEquals("test", readFrom(TARGET));
    }

    @Test
    public void content2() throws Exception {
        Link.create().hard(TARGET, SOURCE);
        writeTo(SOURCE, "another test");
        String content = readFrom(TARGET);
        Assert.assertEquals("another test", content);
    }
}