package org.zzdict.utils;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class StarDictParserTest {
	StarDictParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new StarDictParser(
			"src/test/resources/stardict/stardict1.3-2.4.2");
	}
	
	@Test(timeout=100)
	public void testGetWordDatas() {
		List<WordDataItem> list;
		
		try {
			list = parser.getWordDatas("hook");
			Assert.assertEquals(list.size(), 2);
			Assert.assertEquals(list.get(0).type, WordDataType.ENGLISH_PHONETIC_UTF8_TEXT);
			Assert.assertEquals(((EnglishPhoneticUtf8TextWordDataItem)list.get(0)).getPhonetic(),
					"huk");
			Assert.assertEquals(list.get(1).type, WordDataType.UTF8_PURE_TEXT);
			Assert.assertEquals(((PureTextWordDataItem)list.get(1)).getWordExplanation(),
					"n. 钩,钩状,镰刀;\nv. 挂...于钩上,弯成钩状,偷窃;");
			list = parser.getWordDatas("a don't existed word");
			Assert.assertEquals(list.size(), 0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
