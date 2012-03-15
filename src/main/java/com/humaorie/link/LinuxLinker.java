package com.humaorie.link;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import java.io.File;
import java.io.IOException;

public class LinuxLinker implements Linker {

    private final C libc = (C) Native.loadLibrary("c", C.class);
    private final Pointer errno = NativeLibrary.getInstance("c").getGlobalVariableAddress("errno");

    private static interface C extends Library {

        /** see man 2 link */
        int link(String oldpath, String newpath);
    }

    @Override
    public void createHardLink(File target, File source) throws IOException {
        dbc.precondition(target != null, "target cannot be null");
        dbc.precondition(source != null, "source cannot be null");
        errno.setInt(0, 0);
        final int res = libc.link(source.getCanonicalPath(), target.getCanonicalPath());
        if (res != 0) {
            throw new IOException("link() failed: errno " + errno.getInt(0));
        }
    }
}
