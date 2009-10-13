package org.zzdict.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class StarDictDictFileParser {

	private String dictFileName;

	/**
	 * Constructor of StarDictDictFileParser
	 * 
	 * @param dictFileName
	 *            dict file name
	 */
	public StarDictDictFileParser(String dictFileName) {
		this.dictFileName = dictFileName;
	}

	/**
	 * query word data in dict file
	 * 
	 * @param word
	 *            the word
	 * @param dataInfo
	 *            dataInfo that contains wordDateItem type, position and length
	 *            information
	 * @return WordDataItem that contains word's explanation, sentences examples
	 *         etc
	 * @throws IOException
	 *             IO errors occur when reading the dict file
	 * @throws FileNotFoundException
	 */
	public WordDataItem getWordData(String word, DictDataInfo dataInfo)
			throws FileNotFoundException, IOException {
		if (isDictFileGzipped()) {
			GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(
					this.dictFileName));
		}
		return null;
	}

	private boolean isDictFileGzipped() {
		return dictFileName.endsWith(".dict.dz");
	}
}
