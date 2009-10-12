package org.zzdict.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * StarDict info file parser
 * @author zzh
 *
 */
public class StarDictInfoFileParser {
	
	private String infoFileName;
	private FileInputStream fis;

	/**
	 * constructor of StarDictInfoFileParser
	 * @param infoFileName full info file's name
	 * @throws FileNotFoundException if info file can not be found
	 */
	public StarDictInfoFileParser(String infoFileName) throws FileNotFoundException {
		this.infoFileName = infoFileName;
		try{
			fis = new FileInputStream(infoFileName);
		}catch(FileNotFoundException e){
			throw new FileNotFoundException("Info file: "+infoFileName + " could not be found!");
		}
	}

	
	/**
	 * parse .ifo file to get StarDict info 
	 * @return StarDictInfo structure that contains dict infos
	 */
	public StarDictInfo parseStarDictInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}

/**
 * stardict dict infos
 * @author zzh
 *{2}. The ".ifo" file's format.
The .ifo file has the following format:

StarDict's dict ifo file
version=2.4.2
[options]

Note that the current "version" string must be "2.4.2" or "3.0.0".  If it's not,
then StarDict will refuse to read the file.
If version is "3.0.0", StarDict will parse the "idxoffsetbits" option.

[options]
---------
In the example above, [options] expands to any of the following lines
specifying information about the dictionary.  Each option is a keyword
followed by an equal sign, then the value of that option, then a
newline.  The options may be appear in any order.

Note that the dictionary must have at least a bookname, a wordcount and a 
idxfilesize, or the load will fail.  All other information is optional.  All 
strings should be encoded in UTF-8.

Available options:

bookname=      // required
wordcount=     // required
synwordcount=  // required if ".syn" file exists.
idxfilesize=   // required
idxoffsetbits= // New in 3.0.0
author=
email=
website=
description=	// You can use <br> for new line.
date=
sametypesequence= // very important.
dicttype=


wordcount is the count of word entries in .idx file, it must be right.

idxfilesize is the size(in bytes) of the .idx file, even the .idx is compressed 
to a .idx.gz file, this entry must record the original .idx file's size, and it 
must be right too. The .gz file don't contain its original size information, 
but knowing the original size can speed up the extraction to memory, as you 
don't need to call realloc() for many times.

idxoffsetbits can be 64 or 32. If "idxoffsetbits=64", the offset field of the 
.idx file will be 64 bits.

dicttype is used by some special dictionary plugins, such as wordnet. Its value 
can be "wordnet" presently.

The "sametypesequence" option is described in further detail below.

***
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
***

 */
class StarDictInfo{
	/**
	 * Note that the current "version" string must be "2.4.2" or "3.0.0".  If it's not,
	 * then StarDict will refuse to read the file.
	 * If version is "3.0.0", StarDict will parse the "idxoffsetbits" option.
	 */
	String version;
	String bookname;   // required
	int wordcount;     // required
	int synwordcount;  // required if ".syn" file exists.
	long idxfilesize;   // required
	long idxoffsetbits; // New in 3.0.0
	String author;
	String email;
	String website;
	String description;	// You can use <br> for new line.
	String date;
	String sametypesequence; // very important.
	String dicttype;
}