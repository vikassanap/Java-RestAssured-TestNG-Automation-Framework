package com.project.qa.api.utils;


/**
 * @author : Vikas S.
 * @since : 05-06-2019, Wed
 **/

public class FileReaderManager {
    /**
     * Method to compute file path from resource folder
     *
     * @param filePath
     * @return file path
     */
    public static String getFilePath(String filePath) {
        return FileReaderManager.class.getClassLoader().getResource(filePath).getPath();
    }
}
