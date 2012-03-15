package com.humaorie.link;

import java.io.File;
import java.io.IOException;

public interface Linker {

    void createHardLink(File target, File source) throws IOException;
}
