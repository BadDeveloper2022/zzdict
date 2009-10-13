package org.zzdict.utils;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StarDictDictFileParserTest {

	private StarDictDictFileParser dictFileParser;
	private StarDictInfoFileParser infoFileParser;
	private StarDictInfo dictInfo;
	
	@Before
	public void setUp() throws Exception {
		dictFileParser = new StarDictDictFileParser(
			"src/test/resources/stardict/stardict1.3-2.4.2/stardict1.3.dict.dz");
		infoFileParser = new StarDictInfoFileParser(
			"src/test/resources/stardict/stardict1.3-2.4.2/stardict1.3.ifo");
		dictInfo = infoFileParser.parseStarDictInfo();
	}

	@Test
	public void testGetWordDatas() {
		String word;
		DictDataInfo dataInfo;
		
		List<WordDataItem> list;
		
		word = "hook";
		dataInfo = new DictDataInfo(635594, 65);
		try {
			list = dictFileParser.getWordDatas(word, dataInfo, dictInfo);
			fail("");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
