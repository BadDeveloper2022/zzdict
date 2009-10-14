package org.zzdict.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dict.kernel.FlatDataAccessor;
import org.dict.kernel.IDataAccessor;
import org.dict.zip.DictZipDataAccessor;

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
	 * query word datas in dict file, each word might relate to different word data, 
	 * like explanation, sound, picture etc @see WordDataType
	 * 
	 * @param word
	 *            the word
	 * @param dataInfo
	 *            dataInfo that contains wordDateItem type, position and length
	 *            information, not null
	 * @param dictInfo
	 * 			dict infos, most importantly, it contains samesequencetype info, not null
	 * @return a list of WordDataItem that contains word's explanation, sentences examples
	 *         etc, not null
	 * @throws IOException
	 *             IO errors occur when reading the dict file
	 * @throws FileNotFoundException
	 */
	public List<WordDataItem> getWordDatas(String word, DictDataInfo dataInfo, StarDictInfo dictInfo)
			throws FileNotFoundException, IOException {
		IDataAccessor accessor;

		List<WordDataItem> list = new ArrayList<WordDataItem>();
		
		if (isDictFileGzipped()) {
			accessor = new DictZipDataAccessor(dictFileName);
		}else{
			accessor = new FlatDataAccessor(dictFileName);
		}
		
		//read word datas into a byte buffer
		byte[] buf = accessor.readData(dataInfo.wordDataOffset, dataInfo.wordDataSize);
		
		Position pos = new Position(0);
		WordDataItem item;
		
		if (dictInfo.sametypesequence != null){
			for(char c : dictInfo.sametypesequence.toCharArray()){
				item = getWordData(word,pos.getPos(),buf,WordDataType.valueOf(c),pos);
				list.add(item);
			}
		}else{
			while(pos.getPos() < buf.length){
				item = getWordData(word,pos.getPos(),buf,null,pos);
				list.add(item);
			}
		}
		
		return list;
	}

	/**
	 * get word data since a start position in a byte array
	 * 
	 * @param word the word
	 * @param startPos start position in the byte array
	 * @param wordDatasByteArray byte array
	 * @param type null if we need to find the type in the array, not null if we have a preset type
	 * @param posAfter stored the position after this method for later usage
	 * @return a WordDataItem
	 */
	private WordDataItem getWordData(String word, int startPos, byte[] wordDatasByteArray, WordDataType type, Position posAfter){
		// TODO get word data from a byte array
		if (type == null){
			type = WordDataType.valueOf((char) wordDatasByteArray[startPos]);
			startPos++;
		}
		
		WordDataItem item;
		item = WordDataItem.createWordDataItemByType(type);
		startPos = item.fillDataThroughByteArray(wordDatasByteArray, startPos);
		
		posAfter.setPos(startPos);
		return item;
	}
	
	private boolean isDictFileGzipped() {
		return dictFileName.endsWith(".dict.dz");
	}


}

/**
 * A Position class to pass the position info out through parameter
 * @author zzh
 *
 */
class Position{
	
	/**
	 * current position
	 */
	private int pos;
	
	public Position(int pos){
		this.pos = pos;
	}
	
	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}	
}

