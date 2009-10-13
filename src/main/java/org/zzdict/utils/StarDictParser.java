package org.zzdict.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class StarDictParser {

	/**
	 * folder that contains dict files
	 */
	private String dictFilesFolder;
	private StarDictInfoFileParser infoFileParser;
	private StarDictIndexFileParser indexFileParser;

	/**
	 * index file info to help access target word data
	 */
	private Map<String, DictDataInfo> indexFileInfo;

	/**
	 * 
	 * @param dictFilesFolder
	 * @throws FileNotFoundException
	 */
	public StarDictParser(String dictFilesFolder) throws FileNotFoundException {
		this.dictFilesFolder = dictFilesFolder;
		checkDictFilesFolderValid();
		initStarDictParser();
	}

	private void checkDictFilesFolderValid() throws FileNotFoundException {
		File f = new File(dictFilesFolder);
		if (!(f.exists() && f.isDirectory())) {
			throw new FileNotFoundException("Dict folder " + dictFilesFolder
					+ " could not be found!");
		}
	}

	private void initStarDictParser() throws FileNotFoundException {
		// init infoFileParser
		String infoFileName = "";
		this.infoFileParser = new StarDictInfoFileParser(infoFileName);

		// init indexFileParser
		String indexFileName = "";
		this.indexFileParser = new StarDictIndexFileParser(indexFileName);
	}

	private StarDictInfo getStarDictInfo() throws IOException,
			WrongPropertyException {
		return infoFileParser.parseStarDictInfo();
	}

	private void loadDictIndexInfoToMemory() {
		try {
			this.indexFileInfo = indexFileParser.parseIndexFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileFormatErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * get word data of given word
	 * 
	 * @param word
	 *            given word
	 * @return word data
	 */
	public WordDataItem getWordData(String word) {
		// TODO parse file to get word data
		/*
		 * {7}. The ".dict" file's format. The .dict file is a pure data
		 * sequence, as the offset and size of each word is recorded in the
		 * corresponding .idx file.
		 * 
		 * If the "sametypesequence" option is not used in the .ifo file, then
		 * the .dict file has fields in the following order: ==============
		 * word_1_data_1_type; // a single char identifying the data type
		 * word_1_data_1_data; // the data word_1_data_2_type;
		 * word_1_data_2_data; ...... // the number of data entries for each
		 * word is determined by // word_data_size in .idx file
		 * word_2_data_1_type; word_2_data_1_data; ...... ============== It's
		 * important to note that each field in each word indicates its own
		 * length, as described below. The number of possible fields per word is
		 * also not fixed, and is determined by simply reading data until you've
		 * read word_data_size bytes for that word.
		 * 
		 * 
		 * Suppose the "sametypesequence" option is used in the .idx file, and
		 * the option is set like this: sametypesequence=tm Then the .dict file
		 * will look like this: ============== word_1_data_1_data
		 * word_1_data_2_data word_2_data_1_data word_2_data_2_data ......
		 * ============== The first data entry for each word will have a
		 * terminating '\0', but the second entry will not have a terminating
		 * '\0'. The omissions of the type chars and of the last field's size
		 * information are the optimizations required by the "sametypesequence"
		 * option described above.
		 * 
		 * If "idxoffsetbits=64", the file size of the .dict file will be bigger
		 * than 4G. Because we often need to mmap this large file, and there is
		 * a 4G maximum virtual memory space limit in a process on the 32 bits
		 * computer, which will make we can get error, so "idxoffsetbits=64"
		 * dictionary can't be loaded in 32 bits machine in fact, StarDict will
		 * simply print a warning in this case when loading. 64-bits computers
		 * should haven't this limit.
		 * 
		 * Type identifiers ---------------- Here are the single-character type
		 * identifiers that may be used with the "sametypesequence" option in
		 * the .idx file, or may appear in the dict file itself if the
		 * "sametypesequence" option is not used.
		 * 
		 * Lower-case characters signify that a field's size is determined by a
		 * terminating '\0', while upper-case characters indicate that the data
		 * begins with a network byte-ordered guint32 that gives the length of
		 * the following data's size(NOT the whole size which is 4 bytes
		 * bigger).
		 * 
		 * For the type definition, @see WordDataType
		 */
		return null;
	}

}
