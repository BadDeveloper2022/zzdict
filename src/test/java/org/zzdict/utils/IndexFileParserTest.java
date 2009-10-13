package org.zzdict.utils;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class IndexFileParserTest {

	@Test
	public void testParseIndexFile() {
		StarDictIndexFileParser ifp;
		Map<String, DictDataInfo> resultMap;

		try {
			// stardict1.3.idx contains 51214 words file size is 899,574 bytes
			ifp = new StarDictIndexFileParser(
					"src/test/resources/stardict/stardict1.3-2.4.2/stardict1.3.idx");
			resultMap = ifp.parseIndexFile();
			int i = 0;
			for (Entry<String, DictDataInfo> entry : resultMap.entrySet()) {
				System.out.println("word: " + entry.getKey());
				System.out
						.println("offset: " + entry.getValue().wordDataOffset);
				System.out.println("size: " + entry.getValue().wordDataSize);
				i++;
				if (i > 1000)
					break;
			}

			// assert the count of words is correct
			assertEquals(resultMap.size(), 51214);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileFormatErrorException e) {
			e.printStackTrace();
			fail("file format is wrong. Reason is " + e);
		}
	}

}
