package com.github.davidmoten.odata.client.generator;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class Imports {

    private final String simpleClassName;

    Imports(String simpleClassName) {
        this.simpleClassName = simpleClassName;
    }

    private final Map<String, String> map = new HashMap<>();

    public String add(Class<?> cls) {
        return add(cls.getName().replace("$", "."));
    }

    public String add(String className) {
        int i = className.lastIndexOf('.');
        String simpleName;
        if (i == -1) {
            simpleName = className;
        } else {
            simpleName = className.substring(i + 1, className.length());
        }
        if (simpleName.equals(simpleClassName)) {
            return className;
        } else {
            String c = map.get(simpleName);
            if (c == null) {
                map.put(simpleName, className);
                return simpleName;
            } else if (c.equals(className)) {
                return simpleName;
            } else {
                return className;
            }
        }
    }

    @Override
    public String toString() {
        String x = map.values().stream().sorted() //
                .filter(c -> !c.startsWith("java.lang.")) //
                .filter(c -> !c.equals("boolean")) //
                .filter(c -> !c.equals("short")) //
                .filter(c -> !c.equals("float")) //
                .filter(c -> !c.equals("double")) //
                .filter(c -> !c.equals("int")) //
                .filter(c -> !c.equals("byte")) //
                .map(c -> "import " + c + ";").collect(Collectors.joining("\n"));
        if (!x.isEmpty()) {
            x = x + "\n\n";
        }
        return x;
    }

}