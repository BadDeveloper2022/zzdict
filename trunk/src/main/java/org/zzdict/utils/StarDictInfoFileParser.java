package org.zzdict.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * StarDict info file parser
 * @author zzh
 *
 */
public class StarDictInfoFileParser {
	
	private String infoFileName;
	private FileReader reader;

	/**
	 * constructor of StarDictInfoFileParser
	 * @param infoFileName full info file's name
	 * @throws FileNotFoundException if info file can not be found
	 */
	public StarDictInfoFileParser(String infoFileName) throws FileNotFoundException {
		this.infoFileName = infoFileName;
		try{
			reader = new FileReader(infoFileName);
		}catch(FileNotFoundException e){
			throw new FileNotFoundException("Info file: "+infoFileName + " could not be found!");
		}
	}

	
	/**
	 * parse .ifo file to get StarDict info 
	 * @return StarDictInfo structure that contains dict infos
	 * @throws IOException if IO error occure when reading ifo file
	 * @throws MissPropertyException if some required properties are missing
	 */
	public StarDictInfo parseStarDictInfo() throws IOException, MissPropertyException {
		Properties properties = new Properties();
		properties.load(reader);
		StarDictInfo info = new StarDictInfo();
		info.version = properties.getProperty("version");
		if (info.version == null)
			throw new MissPropertyException("miss version property");
		info.author = properties.getProperty("author");
		// TODO Auto-generated method stub
		return info;
	}

}

/**
 * stardict dict infos
 * @author zzh
 */
class StarDictInfo{
	/**
	 * dict version, required
	 * Note that the current "version" string must be "2.4.2" or "3.0.0".  If it's not,
	 * then StarDict will refuse to read the file.
	 * If version is "3.0.0", StarDict will parse the "idxoffsetbits" option.
	 */
	String version;
	
	/**
	 * dict name
	 * required
	 */
	String bookname;
	
	/**
	 * word count 
	 * required, and must match the word count in idx file
	 */
	int wordcount;
	
	/**
	 * syn word count, required if ".syn" file exists.
	 */
	int synwordcount;  
	
	/**
	 * idx file size, must match the original idx file size, even if idx file is gzipped
	 */
	long idxfilesize;
	
	/**
	 * idx offset bits, 64(long) or 32(int). if it is 64, then it can support dict data file large than 4G
	 * New in 3.0.0
	 */
	int idxoffsetbits;
	
	/**
	 * author of this dict
	 */
	String author;
	
	/**
	 * email address of author
	 */
	String email;
	
	/**
	 * website
	 */
	String website;
	
	/**
	 * dict description. You can use \<br\> for new line. 
	 */
	String description;	
	
	/**
	 * create date of this dictionary
	 */
	String date;
	
	/**
	 * The "sametypesequence" option is described in further detail below.
<pre>
sametypesequence

You should first familiarize yourself with the .dict file format
described in the next section so that you can understand what effect
this option has on the .dict file.

If the sametypesequence option is set, it tells StarDict that each
word's data in the .dict file will have the same sequence of datatypes.
In this case, we expect a .dict file that's been optimized in two
ways: the type identifiers should be omitted, and the size marker for
the last data entry of each word should be omitted.

Let's consider some concrete examples of the sametypesequence option.

Suppose that a dictionary records many .wav files, and so sets:
        sametypesequence=W
In this case, each word's entry in the .dict file consists solely of a
wav file.  In the .dict file, you would leave out the 'W' character
before each entry, and you would also omit the 32-bits integer at the
front of each .wav entry that would normally give the entry's length.
You can do this since the length is known from the information in the
idx file.

As another example, suppose a dictionary contains phonetic information
and a meaning for each word.  The sametypesequence option for this
dictionary would be:
        sametypesequence=tm
Once again, you can omit the 't' and 'm' characters before each data
entry in the .dict file.  In addition, you should omit the terminating
'\0' for the 'm' entry for each word in the .dict file, as the length
of the meaning string can be inferred from the length of the phonetic
string (still indicated by a terminating '\0') and the length of the
entire word entry (listed in the .idx file).

So for cases where the last data entry for each word normally requires
a terminating '\0' character, you should omit this character in the
dict file.  And for cases where the last data entry for each word
normally requires an initial 32-bits number giving the length of the
field (such as WAV and PNG entries), you must omit this number in the
dictionary.

Every dictionary should try to use the sametypesequence feature to
save disk space.
</pre>
	 */
	String sametypesequence; // very important.
	
	/**
	 * dict type.
	 * dicttype is used by some special dictionary plugins, such as wordnet. 
	 * Its value can be "wordnet" presently.
	 */
	String dicttype;
}