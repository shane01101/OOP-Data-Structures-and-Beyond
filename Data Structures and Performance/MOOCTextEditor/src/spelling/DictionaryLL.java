package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	private int size;
	
    // TODO: Add a constructor
	public DictionaryLL()
	{
		dict = new LinkedList<>();
		size = 0;
	}


    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// TODO: Implement this method
    	String lowerWord = word.toLowerCase();
    	boolean addFlag = true;
    	
    	for(String dictStr : dict)
    		if(lowerWord.equals(dictStr))
    		{
    			addFlag = false;
    			break;
    		}

    	if (addFlag)
    	{
    		dict.add(lowerWord);
    		size++;
            return true;
    	}
    	return false;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        // TODO: Implement this method
        return size;
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        //TODO: Implement this method
    	String lowerWord = s.toLowerCase();
    	
    	for(String dictStr : dict)
    		if(lowerWord.equals(dictStr))
    			return true;
        return false;
    }

    
}
