package com.rjkx.sk.system.utils;

import java.io.File;

public class FileUtil {
	public static String getExtensiona(File f) {
		return (f != null) ? getExtension(f.getName()) : "";
	}

	public static String getExtension(String filename) {
		String ext = getExtension(filename, "");
		if (ext.length() > 0) {
			ext = "." + ext;
		}
		return ext;
	}

	public static String getExtension(String filename, String defExt) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > -1) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1);
			}
		}
		return defExt;
	}

	public static String trimExtension(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i > -1) && (i < (filename.length()))) {
				return filename.substring(0, i);
			}
		}
		return filename;
	}
}
