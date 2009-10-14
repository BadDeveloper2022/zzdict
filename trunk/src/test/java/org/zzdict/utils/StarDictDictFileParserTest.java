package org.zzdict.utils;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

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

	@Test(timeout=100)
	public void testGetWordDatas() {
		String word;
		DictDataInfo dataInfo;
		
		List<WordDataItem> list;
		
		word = "hook";
		dataInfo = new DictDataInfo(635594, 65);
		try {
			list = dictFileParser.getWordDatas(word, dataInfo, dictInfo);
			Assert.assertEquals(list.size(), 2);
			Assert.assertEquals(list.get(0).type, WordDataType.ENGLISH_PHONETIC_UTF8_TEXT);
			Assert.assertEquals(((EnglishPhoneticUtf8TextWordDataItem)list.get(0)).getPhonetic(),
					"huk");
			Assert.assertEquals(list.get(1).type, WordDataType.UTF8_PURE_TEXT);
			Assert.assertEquals(((PureTextWordDataItem)list.get(1)).getWordExplanation(),
					"n. 钩,钩状,镰刀;\nv. 挂...于钩上,弯成钩状,偷窃;");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test(timeout=100)
	public void testGetWordDatas2(){
		String word;
		DictDataInfo dataInfo;
		
		List<WordDataItem> list;
		
		word = "unbiassed";
		dataInfo = new DictDataInfo(1419824, 14);
		try {
			list = dictFileParser.getWordDatas(word, dataInfo, dictInfo);
			Assert.assertEquals(list.size(), 2);
			Assert.assertEquals(list.get(0).type, WordDataType.ENGLISH_PHONETIC_UTF8_TEXT);
			Assert.assertEquals(((EnglishPhoneticUtf8TextWordDataItem)list.get(0)).getPhonetic(),
					"");
			Assert.assertEquals(list.get(1).type, WordDataType.UTF8_PURE_TEXT);
			Assert.assertEquals(((PureTextWordDataItem)list.get(1)).getWordExplanation(),
					"adj.公正的");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
