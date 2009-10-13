package org.zzdict.utils;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

public class StarDictInfoFileParserTest {

	@Test
	public void testParseStarDictInfo() {
		try {
			StarDictInfoFileParser infoFileParser = new StarDictInfoFileParser(
					"src/test/resources/stardict/stardict1.3-2.4.2/stardict1.3.ifo");
			StarDictInfo info = infoFileParser.parseStarDictInfo();
			Assert.assertEquals(info.version, new Version("2.4.2"));
			Assert.assertEquals(info.wordcount, 51214);
			Assert.assertEquals(info.idxfilesize, 899574);
			Assert.assertEquals(info.bookname, "stardict1.3英汉辞典");
			Assert.assertEquals(info.author, "马苏安");
			Assert.assertEquals(info.email, "msa@wri.com.cn");
			Assert.assertEquals(info.description, "胡正将其转换到stardict2。");
			Assert.assertEquals(info.date, "2003.05.13");
			Assert.assertEquals(info.sametypesequence, "tm");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WrongPropertyException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

}
