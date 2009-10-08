package org.unidict.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IndexFileParser {
	
	/**
	 * default wordEncoding is UTF8
	 */
	private String wordEncoding = "UTF8";
	private String indexFileName; 
	private int readlimit = 8192;
	
	public IndexFileParser(String indexFileName){
		this.indexFileName = indexFileName;
	}
	
	public IndexFileParser(String indexFileName, String wordEncoding){
		this.indexFileName = indexFileName;
		this.wordEncoding = wordEncoding;
	}
	
	/**
	 * Parse index file
	 * The ".idx" file's format.
	 * The .idx file is just a word list.
	 *
	 * The word list is a sorted list of word entries.
	 * 
	 * Each entry in the word list contains three fields, one after the other:
	 *    word_str;  // a utf-8 string terminated by '\0'.
	 *    word_data_offset;  // word data's offset in .dict file
	 *    word_data_size;  // word data's total size in .dict file     
	 * @exception IOException if I/O error occurs
	 * @exception FileNotFoundException if file named indexFileName is not found 
	 */
	public synchronized Map<String, DictDataInfo> parseIndexFile(String indexFileName) throws IOException, FileNotFoundException{
		Map<String,DictDataInfo> map = new ConcurrentHashMap<String, DictDataInfo>();
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(indexFileName));
		String word;
		while((word = readString(bis)) != null){
			map.put(word, new DictDataInfo(readLong(bis),readInt(bis)));
		}
		
		return null;
	}
	
	/**
	 * read string from a buffered input stream
	 * @param bis the buffered input stream
	 * @return read string, null if input stream reach the end 
	 * @throws IOException 
	 */
	private String readString(BufferedInputStream bis) throws IOException{
		bis.mark(readlimit);
		int pos=getNullCharPosition(bis);
		bis.reset();
		if (pos == -1)
			return null;
		byte[] buf = new byte[pos];
		bis.read(buf);
		String result = new String(buf,wordEncoding);
		//read null char
		bis.read();
		return result;
	}

	/**
	 * get null char position
	 * @param bis buffered input stream
	 * @return null char position, -1 if input stream reach the end
	 * @throws IOException
	 */
	private int getNullCharPosition(BufferedInputStream bis)
			throws IOException {
		int length;
		byte[] buf = new byte[256];
		int pos = -1;
		boolean nullCharFound = false;
		while((length = bis.read(buf)) != -1){
			for(int i=0;i<length;i++){
				pos++;
				if (buf[i] == 0){
					nullCharFound = true;
					break;
				}
			}
			if (nullCharFound)
				break;
		}
		
		return pos;
	}
	
	
	/**
	 * read long from a buffered input stream, bytes are big endian
	 * @param bis the  buffered input stream
	 * @return read long, -1 if input stream reach the end
	 */
	private long readLong(BufferedInputStream bis){
		return -1;
	}
	
	/**
	 * read integer from a buffered input stream, bytes are big endian
	 * @param bis the  buffered input stream
	 * @return read integer, -1 if input stream reach the end
	 */	
	private int readInt(BufferedInputStream bis){
		return -1;
	}
}

class DictDataInfo{
	long wordDataOffset;
	int wordDataSize;
	
	public DictDataInfo(long wordDataOffset, int wordDataSize){
		this.wordDataOffset = wordDataOffset;
		this.wordDataSize = wordDataSize;
	}
}