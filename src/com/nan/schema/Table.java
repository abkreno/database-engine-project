package com.nan.schema;

import java.util.Hashtable;

import com.nan.schema.indices.DBLinearHashTable;

public class Table {
	private String tableName;
	private Hashtable<String, Page> tablePages;
	private Hashtable<String, DBLinearHashTable> tableIndices;
	private Hashtable<String, DBLinearHashTable> tableMultiIndices;

	/**
	 * Creates a Table and inizializes it's files (This constructor will only be
	 * called once a table is created for the first time)
	 * 
	 * @param strTableName
	 *            Table name (the folder of the table will be named as this
	 *            value)
	 * @param htblColNameType
	 *            Table Columns which will be stored in the metadata.csv file
	 * @param htblColNameRefs
	 *            Columns Refrences will be stored in the metadata.csv file
	 * @param strKeyColName
	 *            An Index will be generated automatically on this key and
	 *            stored on the indices file for this table
	 */
	public Table(String strTableName,
			Hashtable<String, String> htblColNameType,
			Hashtable<String, String> htblColNameRefs, String strKeyColName) {
	}

	/**
	 * This constructor is called to create a table that already exists in the
	 * data file
	 * 
	 * @param strTableName
	 */
	public Table(String strTableName) {

	}

}
