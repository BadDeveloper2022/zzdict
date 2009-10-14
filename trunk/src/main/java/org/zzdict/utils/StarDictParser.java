package org.zzdict.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StarDictParser {

	/**
	 * folder that contains dict files
	 */
	private String dictFilesFolder;

	/**
	 * index file info to help access target word data
	 */
	private Map<String, DictDataInfo> indexFileInfo;

	/**
	 * dict infos
	 */
	private StarDictInfo dictInfo;

	private StarDictInfoFileParser infoFileParser;
	private StarDictIndexFileParser indexFileParser;
	private StarDictDictFileParser dictFileParser;


	/**
	 * 
	 * @param dictFilesFolder
	 * @throws WrongPropertyException 
	 * @throws IOException 
	 */
	public StarDictParser(String dictFilesFolder) throws IOException, WrongPropertyException {
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

	private void initStarDictParser() throws IOException, WrongPropertyException {
		// init infoFileParser
		String infoFileName = FileUtils.getNameOfFileWithSuffixInDir(dictFilesFolder,".ifo");
		this.infoFileParser = new StarDictInfoFileParser(infoFileName);
		this.dictInfo = this.infoFileParser.parseStarDictInfo();

		// init indexFileParser
		String indexFileName = FileUtils.getNameOfFileWithSuffixInDir(dictFilesFolder,".idx");
		if (indexFileName == null){
			indexFileName = FileUtils.getNameOfFileWithSuffixInDir(dictFilesFolder,".idx.gz");
		}
		this.indexFileParser = new StarDictIndexFileParser(indexFileName);
		loadDictIndexInfoToMemory();
		
		// init dictFileParser
		String dictFileName = FileUtils.getNameOfFileWithSuffixInDir(dictFilesFolder,".dict");
		if (dictFileName == null){
			dictFileName = FileUtils.getNameOfFileWithSuffixInDir(dictFilesFolder,".dict.dz");
		}		
		this.dictFileParser = new StarDictDictFileParser(dictFileName);
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
	 * get word datas of given word
	 * 
	 * @param word
	 *            given word
	 * @return list of word data
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public List<WordDataItem> getWordDatas(String word) throws FileNotFoundException, IOException {
		if (indexFileInfo.get(word) == null)
			return new ArrayList<WordDataItem>();
		else
			return dictFileParser.getWordDatas(word, indexFileInfo.get(word), dictInfo);
	}

}
