package nl.rutgerkok.pokkit.startup;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * Class loader that only loads classes from the given URLs, and doesn't fall
 * back on the global class loader except for when the class is not found. This
 * allows the given URLs to override literally any class.
 *
 * <p>
 * Keep in mind: findClass - always from disk, loadClass: checks in cache first.
 */
final class UrlFirstClassLoader extends URLClassLoader {

    private final Map<String, Class<?>> loaded = new HashMap<>();

    public UrlFirstClassLoader(URL[] urls, ClassLoader fallback) {
        super(urls, fallback);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (name.startsWith(getClass().getPackage() + ".")) {
            // Class in same package, load using global class loader
            throw new ClassNotFoundException(name);
        }

        // Load from cache
        Class<?> cached = loaded.get(name);
        if (cached != null) {
            if (resolve) {
                resolveClass(cached);
            }
            return cached;
        }

        try {
            // Load from JAR file
            Class<?> loadedFromJar = this.findClass(name);
            loaded.put(name, loadedFromJar);
            if (resolve) {
                resolveClass(loadedFromJar);
            }
            return loadedFromJar;
        } catch (ClassNotFoundException e) {
            // Fall back on global class loader for Java standard library
            Class<?> fromGlobal = super.loadClass(name, resolve);
            loaded.put(name, fromGlobal);
            if (resolve) {
                resolveClass(fromGlobal);
            }
            return fromGlobal;
        }
    }
}