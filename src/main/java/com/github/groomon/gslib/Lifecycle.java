package com.github.groomon.gslib;

public interface Lifecycle {

    boolean load();
    boolean unload();
    boolean reload();
}
