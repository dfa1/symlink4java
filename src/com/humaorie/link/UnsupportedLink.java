package com.humaorie.link;

public class UnsupportedLink extends Link {

    private final String osName;

    public UnsupportedLink(String osName) {
        this.osName = osName;
    }

    @Override
    protected void hardLink(String target, String source) {
        String message = String.format("%s not supported yet", osName);
        throw new UnsupportedOperationException(message);
    }
}
