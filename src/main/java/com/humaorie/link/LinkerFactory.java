package com.humaorie.link;

public class LinkerFactory {

    public static Linker create() {
        final String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("windows")) {
            return new WindowsLinker();
        } else if (osName.contains("linux")) {
            return new LinuxLinker();
        } else {
            throw new UnsupportedOperationException(String.format("unsupported os '%s'", osName));
        }
    }
}
