package org.zzdict.utils;

import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import junit.framework.Assert;
import org.junit.Test;

public class IndexFileParserTest {

	@Test
	public void testParseIndexFile() {
		IndexFileParser ifp; 
		Map<String,DictDataInfo> resultMap;

		// test1.idx
		ifp = new IndexFileParser("test1.idx");
		try {
			resultMap = ifp.parseIndexFile();
			Assert.assertEquals(0, 0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

}
