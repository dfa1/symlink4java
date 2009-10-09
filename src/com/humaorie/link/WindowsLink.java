package com.humaorie.link;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

class WindowsLink extends Link {

    private static final Kernel32 kernel32 =
            (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);

    private static interface Kernel32 extends Library {
        // this function is available both on Vista and XP;
        // CreateSymbolicLink only on Vista

        public int CreateHardLinkA(String dst, String src, Pointer mustBeNull);
    }

    @Override
    protected void hardLink(String from, String to) {
        // third parameter must be null
        int res = kernel32.CreateHardLinkA(from, to, null);
//        if (res == 0) {
//            throw new RuntimeException(new IOException("CreateHardLinkA() failed"));
//        }
    }
}

