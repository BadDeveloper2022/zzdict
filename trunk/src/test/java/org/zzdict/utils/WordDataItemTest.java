package org.zzdict.utils;

import junit.framework.Assert;

import org.junit.Test;

public class WordDataItemTest {

	@Test
	public void testWordDataType() {
		Assert.assertEquals(WordDataType.valueOf('l'),
				WordDataType.ANSI_PURE_TEXT);
	}
}
