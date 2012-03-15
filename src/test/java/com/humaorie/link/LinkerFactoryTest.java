package com.humaorie.link;

import org.junit.Test;

public class LinkerFactoryTest {
    
    @Test(expected=UnsupportedOperationException.class)
    public void throwsUnsupportedExceptionWhenOsIsNotSupported() {
        System.setProperty("os.name", "android");
        LinkerFactory.create();
    }
}
