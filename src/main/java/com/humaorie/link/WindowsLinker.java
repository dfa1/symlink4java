package com.humaorie.link;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import java.io.File;
import java.io.IOException;

public class WindowsLinker implements Linker {

    private static final Kernel32 kernel32 = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);

    private static interface Kernel32 extends Library {
        // this function is available both on Vista and XP;
        // CreateSymbolicLink only on Vista

        int CreateHardLinkA(String dst, String src, Pointer mustBeNull);
    }

    @Override
    public void createHardLink(File target, File source) throws IOException {
        final Pointer mustBeNull = null;
        final int res = kernel32.CreateHardLinkA(source.getCanonicalPath(), target.getCanonicalPath(), mustBeNull);
        if (res == 0) {
            throw new IOException("CreateHardLinkA() failed");
        }
    }
}
