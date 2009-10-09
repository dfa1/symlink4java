package com.humaorie.link;

import java.io.File;

public abstract class Link {

    public static Link create() {
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("windows")) {
            return new WindowsLink();
        } else if (osName.contains("linux")) {
            return new UnixLink();
        } else {
            return new UnsupportedLink(osName);
        }
    }

    protected abstract void hardLink(String target, String source);

    public Link hard(File target, File source) {
        if (!source.isFile()) {
            String message = String.format("'%s' must be a file", source);
            throw new UnsupportedOperationException(message);
        }

        hardLink(target.getAbsolutePath(), source.getAbsolutePath());
        return this;
    }

    public Link hard(String from, String to) {
        return hard(new File(from), new File(to));
    }
}
