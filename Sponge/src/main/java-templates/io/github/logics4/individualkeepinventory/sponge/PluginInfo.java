package io.github.logics4.individualkeepinventory.sponge;

public class PluginInfo {
    private PluginInfo() {}

    public static final String NAME = "${project.parent.name}";
    public static final String VERSION = "${project.parent.version}";
    public static final String DESCRIPTION = "${project.parent.description}";
    public static final String URL = "${project.parent.url}";
}
