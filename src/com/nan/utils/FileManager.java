package com.nan.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

import com.nan.schema.Column;
import com.nan.schema.Record;
import com.nan.schema.Schema;
import com.nan.schema.Table;

public class FileManager {
	protected static String userDirectory = System.getProperty("user.dir");
	protected static String tablesDirectory = userDirectory + "/data/tables/";
	private static File tempFile;

	public static Hashtable<String, Hashtable<String, Column>> readSchema()
			throws IOException {
		Hashtable<String, Hashtable<String, Column>> schema = new Hashtable<String, Hashtable<String, Column>>();
		File schemaFile = new File(userDirectory + "/data/metadata.csv");
		BufferedReader bf = new BufferedReader(new FileReader(schemaFile));
		String line;
		while ((line = bf.readLine()) != null) {
			String[] data = line.split(", ");
			String tableName = data[0];
			if (!schema.containsKey(tableName))
				schema.put(tableName, new Hashtable<String, Column>());
			Hashtable<String, Column> currTableColumns = schema.get(tableName);
			Column currColumn = new Column(data[1], data[2],
					data[3].equals("True"), data[4].equals("True"), data[5]);
			currTableColumns.put(data[1], currColumn);
		}
		bf.close();
		return schema;
	}

	public static Table readTable(String tableName) {
		return null;
	}

	public static void savePage(String tableName, int pageNumber,
			Hashtable<Integer, Record> pageRows) {
	}

	public static void createNewTable(String strTableName) {
		String folderPath = tablesDirectory + strTableName;
		createNewFolder(folderPath);
		createNewFolder(folderPath + "/pages");
		createNewFolder(folderPath + "/indices");
	}

	public static void createNewFolder(String path) {
		tempFile = new File(path);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
	}

	/**
	 * Deleting a directory recursively
	 * 
	 * @param path
	 *            the path of the directory
	 * @return
	 */
	public static boolean deleteFolder(String path) {
		File directory = new File(path);

		if (directory.exists()) {
			File[] files = directory.listFiles();
			if (null != files) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						deleteFolder(files[i].getPath());
					} else {
						files[i].delete();
					}
				}
			}
		}
		return (directory.delete());
	}

	public static void createNewFile(String path) {
		tempFile = new File(path);
		try {
			tempFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File[] getFiles(String path) {
		tempFile = new File(path);
		return tempFile.listFiles();
	}

	/**
	 * Writes the table info in the metadata.csv file
	 * 
	 * @param strTableName
	 * @param htblColNameType
	 * @param htblColNameRefs
	 * @param strKeyColName
	 * @throws IOException
	 */
	public static void writeSchema() throws IOException {
		String[] schemaCSV = Schema.getSchemaCSV();
		tempFile = new File(userDirectory + "/data/metadata.csv");
		BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
		for (String curr : schemaCSV) {
			bw.write(curr);
			bw.newLine();
		}
		bw.close();
	}
}
