package com.github.groomon.gslib.configuration;

import org.apache.commons.lang.Validate;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A wrapper for implementations of {@link FileConfiguration}. It allows to use the same wrapper for several data formats and provides easier save and loading mechanics.
 * <p>
 * Undocumented methods are direct passes to the underlying {@link FileConfiguration}. Refer to its documentation for more information on this methods.
 *
 * @see FileConfiguration
 */
public class ConfigurationFile {

    //TODO logging
    //TODO javadoc

    private final FileConfiguration config;
    private File configFile;
    private String defaultFile;
    private boolean loadDefaultWhenFileMissing;
    private boolean saveOnDefaultLoad;
    private JavaPlugin plugin;

    public ConfigurationFile(@NotNull FileConfiguration implementation) {
        Validate.notNull(implementation, "The FileConfiguration implementation cannot be null");
        config = implementation;
        saveOnDefaultLoad = false;
        loadDefaultWhenFileMissing = true;
    }

    //TODO rename method?
    /**
     * Gets the underlying {@link FileConfiguration}.
     *
     * @return The wrapped FileConfiguration.
     */
    @NotNull
    public FileConfiguration getFileConfiguration() {
        return config;
    }

    @NotNull
    public ConfigurationFile saveOnDefaultLoad(boolean save) {
        saveOnDefaultLoad = save;
        return this;
    }

    @NotNull
    public ConfigurationFile loadDefaultWhenFileMissing(boolean load) {
        loadDefaultWhenFileMissing = load;
        return this;
    }

    @NotNull
    public ConfigurationFile setDefault(String file, JavaPlugin plugin) {
        Validate.notNull(file, "File cannot be null");
        Validate.notNull(plugin, "Plugin cannot be null");

        InputStream is = plugin.getResource(file);
        Validate.notNull(is, "File cannot be found");
        try {
            is.close();
        } catch (IOException e) {
            //TODO logging
            e.printStackTrace();
        }

        defaultFile = file;
        this.plugin = plugin;
        return this;
    }

    @NotNull
    public ConfigurationFile setFile(@NotNull File file) {
        Validate.notNull(file, "File cannot be null");
        configFile = file;
        return this;
    }

    @NotNull
    public ConfigurationFile setFile(@NotNull String file) {
        Validate.notNull(file, "File cannot be null");
        configFile = new File(file);
        return this;
    }

    @NotNull
    public ConfigurationFile load() throws IOException, InvalidConfigurationException {
        if(configFile == null) throw new IllegalStateException("Configuration file has not been set");
        if(configFile.exists()) {
            config.load(configFile);
        } else if(loadDefaultWhenFileMissing) {
            loadDefault();
            if(saveOnDefaultLoad) save();
        }
        return this;
    }

    @NotNull
    public ConfigurationFile load(@NotNull File file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        Validate.notNull(file, "File cannot be null");
        config.load(file);
        return this;
    }

    @NotNull
    public ConfigurationFile load(@NotNull String file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        Validate.notNull(file, "File cannot be null");
        config.load(file);
        return this;
    }

    @NotNull
    public ConfigurationFile loadFromString(@NotNull String contents) throws InvalidConfigurationException {
        Validate.notNull(contents, "Contents cannot be null");
        config.loadFromString(contents);
        return this;
    }

    @NotNull
    public ConfigurationFile loadDefault() throws IOException, InvalidConfigurationException {
        if(plugin == null) throw new IllegalStateException("Plugin has not been set");
        if(configFile == null) throw new IllegalStateException("Default file has not been set");
        InputStream is = plugin.getResource(defaultFile);
        if(is == null) throw new FileNotFoundException("Default file could not be found");
        config.load(new InputStreamReader(is));
        try {
            is.close();
        } catch (IOException e) {
            //TODO logging
            e.printStackTrace();
        }
        return this;
    }

    @NotNull
    public ConfigurationFile save() throws IOException {
        if(configFile == null) throw new IllegalStateException("Configuration file has not been set");
        config.save(configFile);
        return this;
    }

    @NotNull
    public ConfigurationFile save(File file) throws IOException {
        Validate.notNull(file, "File cannot be null");
        config.save(file);
        return this;
    }

    @NotNull
    public ConfigurationFile save(String file) throws IOException {
        Validate.notNull(file, "File cannot be null");
        config.save(file);
        return this;
    }

    @NotNull
    public String saveToString() {
        return config.saveToString();
    }



//    /**
//     * Saves this {@link ConfigurationFile} to the specified location.
//     * <p>
//     * If the file does not exist, it will be created. If already exists, it
//     * will be overwritten. If it cannot be overwritten or created, an
//     * exception will be thrown.
//     * <p>
//     * This method will save using the system default encoding, or possibly
//     * using UTF8.
//     * <p>
//     * The main save location will be set to the specified location.
//     *
//     * @param file File to save to.
//     * @throws IOException Thrown when the given file cannot be written to for
//     *     any reason.
//     * @throws IllegalArgumentException Thrown when file is null.
//     */
//    public void save(@NotNull File file) throws IOException {
//        Validate.notNull(file, "File cannot be null");
//        configFile = file;
//        config.save(configFile);
//    }
//
//    /**
//     * Saves this {@link ConfigurationFile} to the specified location.
//     * <p>
//     * If the file does not exist, it will be created. If already exists, it
//     * will be overwritten. If it cannot be overwritten or created, an
//     * exception will be thrown.
//     * <p>
//     * This method will save using the system default encoding, or possibly
//     * using UTF8.
//     * <p>
//     * The main save location will not be affected.
//     *
//     * @param file File to save to.
//     * @throws IOException Thrown when the given file cannot be written to for
//     *     any reason.
//     * @throws IllegalArgumentException Thrown when file is null.
//     */
//    public void saveCopy(@NotNull File file) throws IOException {
//        Validate.notNull(file, "File cannot be null");
//        config.save(file);
//    }
//
//    /**
//     * Saves this {@link ConfigurationFile} to the specified location.
//     * <p>
//     * If the file does not exist, it will be created. If already exists, it
//     * will be overwritten. If it cannot be overwritten or created, an
//     * exception will be thrown.
//     * <p>
//     * This method will save using the system default encoding, or possibly
//     * using UTF8.
//     * <p>
//     * The main save location will be set to the specified location.
//     *
//     * @param file File to save to.
//     * @throws IOException Thrown when the given file cannot be written to for
//     *     any reason.
//     * @throws IllegalArgumentException Thrown when file is null.
//     */
//    public void save(@NotNull String file) throws IOException {
//        Validate.notNull(file, "File cannot be null");
//        configFile = new File(file);
//        config.save(configFile);
//    }
//
//    /**
//     * Saves this {@link ConfigurationFile} to the specified location.
//     * <p>
//     * If the file does not exist, it will be created. If already exists, it
//     * will be overwritten. If it cannot be overwritten or created, an
//     * exception will be thrown.
//     * <p>
//     * This method will save using the system default encoding, or possibly
//     * using UTF8.
//     * <p>
//     * The main save location will not be affected.
//     *
//     * @param file File to save to.
//     * @throws IOException Thrown when the given file cannot be written to for
//     *     any reason.
//     * @throws IllegalArgumentException Thrown when file is null.
//     */
//    public void saveCopy(@NotNull String file) throws IOException {
//        Validate.notNull(file, "File cannot be null");
//        config.save(file);
//    }
//
//    /**
//     * Saves this {@link ConfigurationFile} to a string, and returns it.
//     * <p>
//     * The main save location will not be affected.
//     *
//     * @return String containing this configuration.
//     */
//    @NotNull
//    public String saveToString() {
//        return config.saveToString();
//    }
//
//    /**
//     * Loads this {@link ConfigurationFile} from the specified location.
//     * <p>
//     * All the values contained within this configuration will be removed,
//     * leaving only settings and defaults, and the new values will be loaded
//     * from the given file.
//     * <p>
//     * If the file cannot be loaded for any reason, an exception will be
//     * thrown.
//     *
//     * @param file File to load from.
//     * @throws FileNotFoundException Thrown when the given file cannot be
//     *     opened.
//     * @throws IOException Thrown when the given file cannot be read.
//     * @throws InvalidConfigurationException Thrown when the given file is not
//     *     a valid Configuration.
//     * @throws IllegalArgumentException Thrown when file is null.
//     */
//    public void load(@NotNull File file) throws FileNotFoundException, IOException, InvalidConfigurationException {
//        config.load(file);
//    }
//
//    /**
//     * Loads this {@link ConfigurationFile} from the specified reader.
//     * <p>
//     * All the values contained within this configuration will be removed,
//     * leaving only settings and defaults, and the new values will be loaded
//     * from the given stream.
//     *
//     * @param reader the reader to load from
//     * @throws IOException thrown when underlying reader throws an IOException
//     * @throws InvalidConfigurationException thrown when the reader does not
//     *      represent a valid Configuration
//     * @throws IllegalArgumentException thrown when reader is null
//     */
//    public void load(@NotNull Reader reader) throws IOException, InvalidConfigurationException {
//        config.load(reader);
//    }
//
//    /**
//     * Loads this {@link ConfigurationFile} from the specified location.
//     * <p>
//     * All the values contained within this configuration will be removed,
//     * leaving only settings and defaults, and the new values will be loaded
//     * from the given file.
//     * <p>
//     * If the file cannot be loaded for any reason, an exception will be
//     * thrown.
//     *
//     * @param file File to load from.
//     * @throws FileNotFoundException Thrown when the given file cannot be
//     *     opened.
//     * @throws IOException Thrown when the given file cannot be read.
//     * @throws InvalidConfigurationException Thrown when the given file is not
//     *     a valid Configuration.
//     * @throws IllegalArgumentException Thrown when file is null.
//     */
//    public void load(@NotNull String file) throws FileNotFoundException, IOException, InvalidConfigurationException {
//        config.load(file);
//    }
//
//    /**
//     * Loads this {@link ConfigurationFile} from the specified string, as
//     * opposed to from file.
//     * <p>
//     * All the values contained within this configuration will be removed,
//     * leaving only settings and defaults, and the new values will be loaded
//     * from the given string.
//     * <p>
//     * If the string is invalid in any way, an exception will be thrown.
//     *
//     * @param contents Contents of a Configuration to load.
//     * @throws InvalidConfigurationException Thrown if the specified string is
//     *     invalid.
//     * @throws IllegalArgumentException Thrown if contents is null.
//     */
//    public void loadFromString(@NotNull String contents) throws InvalidConfigurationException {
//        config.loadFromString(contents);
//    }



    // delegated methods

    @NotNull
    public FileConfigurationOptions options() {
        return config.options();
    }

    public void addDefault(@NotNull String path, @Nullable Object value) {
        config.addDefault(path, value);
    }

    public void addDefaults(@NotNull Map<String, Object> defaults) {
        config.addDefaults(defaults);
    }

    public void addDefaults(@NotNull Configuration defaults) {
        config.addDefaults(defaults);
    }

    public void setDefaults(@NotNull Configuration defaults) {
        config.setDefaults(defaults);
    }

    @Nullable
    public Configuration getDefaults() {
        return config.getDefaults();
    }

    @Nullable
    public ConfigurationSection getParent() {
        return config.getParent();
    }

    @NotNull
    public Set<String> getKeys(boolean deep) {
        return config.getKeys(deep);
    }

    @NotNull
    public Map<String, Object> getValues(boolean deep) {
        return config.getValues(deep);
    }

    public boolean contains(@NotNull String path) {
        return config.contains(path);
    }

    public boolean contains(@NotNull String path, boolean ignoreDefault) {
        return config.contains(path, ignoreDefault);
    }

    public boolean isSet(@NotNull String path) {
        return config.isSet(path);
    }

    @NotNull
    public String getCurrentPath() {
        return config.getCurrentPath();
    }

    @NotNull
    public String getName() {
        return config.getName();
    }

    @Nullable
    public Configuration getRoot() {
        return config.getRoot();
    }

    @Nullable
    public ConfigurationSection getDefaultSection() {
        return config.getDefaultSection();
    }

    public void set(@NotNull String path, @Nullable Object value) {
        config.set(path, value);
    }

    @Nullable
    public Object get(@NotNull String path) {
        return config.get(path);
    }

    @Nullable
    public Object get(@NotNull String path, @Nullable Object def) {
        return config.get(path, def);
    }

    @NotNull
    public ConfigurationSection createSection(@NotNull String path) {
        return config.createSection(path);
    }

    @NotNull
    public ConfigurationSection createSection(@NotNull String path, @NotNull Map<?, ?> map) {
        return config.createSection(path, map);
    }

    @Nullable
    public String getString(@NotNull String path) {
        return config.getString(path);
    }

    @Nullable
    public String getString(@NotNull String path, @Nullable String def) {
        return config.getString(path, def);
    }

    public boolean isString(@NotNull String path) {
        return config.isString(path);
    }

    public int getInt(@NotNull String path) {
        return config.getInt(path);
    }

    public int getInt(@NotNull String path, int def) {
        return config.getInt(path, def);
    }

    public boolean isInt(@NotNull String path) {
        return config.isInt(path);
    }

    public boolean getBoolean(@NotNull String path) {
        return config.getBoolean(path);
    }

    public boolean getBoolean(@NotNull String path, boolean def) {
        return config.getBoolean(path, def);
    }

    public boolean isBoolean(@NotNull String path) {
        return config.isBoolean(path);
    }

    public double getDouble(@NotNull String path) {
        return config.getDouble(path);
    }

    public double getDouble(@NotNull String path, double def) {
        return config.getDouble(path, def);
    }

    public boolean isDouble(@NotNull String path) {
        return config.isDouble(path);
    }

    public long getLong(@NotNull String path) {
        return config.getLong(path);
    }

    public long getLong(@NotNull String path, long def) {
        return config.getLong(path, def);
    }

    public boolean isLong(@NotNull String path) {
        return config.isLong(path);
    }

    @Nullable
    public List<?> getList(@NotNull String path) {
        return config.getList(path);
    }

    @Nullable
    public List<?> getList(@NotNull String path, @Nullable List<?> def) {
        return config.getList(path, def);
    }

    public boolean isList(@NotNull String path) {
        return config.isList(path);
    }

    @NotNull
    public List<String> getStringList(@NotNull String path) {
        return config.getStringList(path);
    }

    @NotNull
    public List<Integer> getIntegerList(@NotNull String path) {
        return config.getIntegerList(path);
    }

    @NotNull
    public List<Boolean> getBooleanList(@NotNull String path) {
        return config.getBooleanList(path);
    }

    @NotNull
    public List<Double> getDoubleList(@NotNull String path) {
        return config.getDoubleList(path);
    }

    @NotNull
    public List<Float> getFloatList(@NotNull String path) {
        return config.getFloatList(path);
    }

    @NotNull
    public List<Long> getLongList(@NotNull String path) {
        return config.getLongList(path);
    }

    @NotNull
    public List<Byte> getByteList(@NotNull String path) {
        return config.getByteList(path);
    }

    @NotNull
    public List<Character> getCharacterList(@NotNull String path) {
        return config.getCharacterList(path);
    }

    @NotNull
    public List<Short> getShortList(@NotNull String path) {
        return config.getShortList(path);
    }

    @NotNull
    public List<Map<?, ?>> getMapList(@NotNull String path) {
        return config.getMapList(path);
    }

    @Nullable
    public <T> T getObject(@NotNull String path, @NotNull Class<T> clazz) {
        return config.getObject(path, clazz);
    }

    @Nullable
    public <T> T getObject(@NotNull String path, @NotNull Class<T> clazz, @Nullable T def) {
        return config.getObject(path, clazz, def);
    }

    @Nullable
    public <T extends ConfigurationSerializable> T getSerializable(@NotNull String path, @NotNull Class<T> clazz) {
        return config.getSerializable(path, clazz);
    }

    @Nullable
    public <T extends ConfigurationSerializable> T getSerializable(@NotNull String path, @NotNull Class<T> clazz, @Nullable T def) {
        return config.getSerializable(path, clazz, def);
    }

    @Nullable
    public Vector getVector(@NotNull String path) {
        return config.getVector(path);
    }

    @Nullable
    public Vector getVector(@NotNull String path, @Nullable Vector def) {
        return config.getVector(path, def);
    }

    public boolean isVector(@NotNull String path) {
        return config.isVector(path);
    }

    @Nullable
    public OfflinePlayer getOfflinePlayer(@NotNull String path) {
        return config.getOfflinePlayer(path);
    }

    @Nullable
    public OfflinePlayer getOfflinePlayer(@NotNull String path, @Nullable OfflinePlayer def) {
        return config.getOfflinePlayer(path, def);
    }

    public boolean isOfflinePlayer(@NotNull String path) {
        return config.isOfflinePlayer(path);
    }

    @Nullable
    public ItemStack getItemStack(@NotNull String path) {
        return config.getItemStack(path);
    }

    @Nullable
    public ItemStack getItemStack(@NotNull String path, @Nullable ItemStack def) {
        return config.getItemStack(path, def);
    }

    public boolean isItemStack(@NotNull String path) {
        return config.isItemStack(path);
    }

    @Nullable
    public Color getColor(@NotNull String path) {
        return config.getColor(path);
    }

    @Nullable
    public Color getColor(@NotNull String path, @Nullable Color def) {
        return config.getColor(path, def);
    }

    public boolean isColor(@NotNull String path) {
        return config.isColor(path);
    }

    @Nullable
    public Location getLocation(@NotNull String path) {
        return config.getLocation(path);
    }

    @Nullable
    public Location getLocation(@NotNull String path, @Nullable Location def) {
        return config.getLocation(path, def);
    }

    public boolean isLocation(@NotNull String path) {
        return config.isLocation(path);
    }

    @Nullable
    public ConfigurationSection getConfigurationSection(@NotNull String path) {
        return config.getConfigurationSection(path);
    }

    public boolean isConfigurationSection(@NotNull String path) {
        return config.isConfigurationSection(path);
    }

    //TODO remove javadoc?
    /**
     * Creates a full path to the given {@link ConfigurationSection} from its
     * root {@link Configuration}.
     * <p>
     * You may use this method for any given {@link ConfigurationSection}, not
     * only {@link MemorySection}.
     *
     * @param section Section to create a path for.
     * @param key Name of the specified section.
     * @return Full path of the section from its root.
     */
    @NotNull
    public static String createPath(@NotNull ConfigurationSection section, @Nullable String key) {
        return MemorySection.createPath(section, key);
    }

    //TODO remove javadoc?
    /**
     * Creates a relative path to the given {@link ConfigurationSection} from
     * the given relative section.
     * <p>
     * You may use this method for any given {@link ConfigurationSection}, not
     * only {@link MemorySection}.
     *
     * @param section Section to create a path for.
     * @param key Name of the specified section.
     * @param relativeTo Section to create the path relative to.
     * @return Full path of the section from its root.
     */
    @NotNull
    public static String createPath(@NotNull ConfigurationSection section, @Nullable String key, @Nullable ConfigurationSection relativeTo) {
        return MemorySection.createPath(section, key, relativeTo);
    }

    //TODO new toString implementation
    @Override
    public String toString() {
        Configuration root = config.getRoot();
        return getClass().getSimpleName() +
                "[path='" +
                config.getCurrentPath() +
                "', root='" +
                (root == null ? null : root.getClass().getSimpleName()) +
                "']";
    }

}
