package com.springboot.provider.common.utils;

import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertyUtils {
    private static final PropertiesPropertySourceLoader propertiesPropertySourceLoader = new PropertiesPropertySourceLoader();
    private static final YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();

    public static List<Object> load(String key, String fileName) throws IOException {
        Resource resource = new ClassPathResource(fileName);
        if (fileName.endsWith(".properties")){
            return propertiesPropertySourceLoader.load(key, resource).stream().map(s -> s.getProperty(key)).filter(Objects::nonNull).collect(Collectors.toList());
        } else if (fileName.endsWith(".yml")){
            return yamlPropertySourceLoader.load(key, resource).stream().map(s -> s.getProperty(key)).filter(Objects::nonNull).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        InputStream inputStream;
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            inputStream = classLoader.getResourceAsStream(fileName);
            if (inputStream != null) {
                properties.load(inputStream);
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static Properties loadAbsolutePathProperties(String filePath) {
        Properties properties = new Properties();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        File file = new File(filePath);

        try {
            if (file.exists()) {
                inputStream = new FileInputStream(file);
                inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            }
            if (inputStream != null) {
                properties.load(inputStreamReader);
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void main(String[] args) throws IOException {
//        PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();
//        Resource resource = new ClassPathResource("application.properties");
//        try {
//            String path = (String) loader.load("server.port", resource).get(0).getProperty("module.package.path");
//            System.out.println(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println(PropertyUtils.load("server.port","application.properties"));

    }


}
