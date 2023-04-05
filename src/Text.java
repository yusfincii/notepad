import javax.swing.*;
import java.io.*;
import java.util.*;

public class Text 
{
    
    public String text;

    // Constructor
    public Text(String text) 
    {
        this.text = text;
    }
    
    
    public static String check(String text) 
    {      
        // for checking and fixing the text
        ArrayList<String> wordsArrayList = new ArrayList<>();
        
        wordsArrayList = splitText(text);
        text = wordsArrayList.get(0);
        wordsArrayList.remove(0);

        String newText = reformText(text, wordsArrayList);

        return newText;
    }

    public static ArrayList<String> splitText(String oldText) 
    {
        ArrayList<String> words_AL = new ArrayList<String>();
        words_AL.add(oldText);
        
        // - containing words edited
        oldText = oldText.replace("-", " ");              
        
        // the text is splitted according to . ,; expressions
        String[] inputWords = oldText.split("[. ,;]");

        for (int i = 0; i < inputWords.length; i++) 
        {
            if (inputWords[i].equals("")) 
            {           
                // if word equals null it will not added to array list
                continue;
            } 
            else
            {
                words_AL.add(inputWords[i].toString());   // word is added
            }
        }
        return words_AL;
    }

    public static String reformText(String oldText, ArrayList<String> words_AL) 
    {
        
        String fileName = "words.txt";
        ArrayList<String> dict = readFile(fileName);    // calling readFile() method

        String[][] editedTable = new String[words_AL.size()][2];    // keep the old version and new version of the words
        
        Iterator wordsIterator = (Iterator) words_AL.iterator();


        int i = 0;
        while (wordsIterator.hasNext()) 
        {
            String word = (String) wordsIterator.next();

            Iterator dictIterator = (Iterator) dict.iterator();          // for traverse words.txt file
            while (dictIterator.hasNext()) 
            {
                String word_dict = (String) dictIterator.next();
                if (word.equals(word_dict)) 
                {        
                    // if word is in the words.txt, adding to table
                    editedTable[i][0] = word;
                    editedTable[i][1] = word;
                } 
                else 
                {
                    //if word is not in the words.txt, calling the singleTransposition() method.
                    String[] combinations = singleTransposition(word);

                    for (int k = 0; k < combinations.length; k++) 
                    {    
                        // loop for combinations of words with single tranpositions
                        if (combinations[k].equals(word_dict)) 
                        {    
                            // if any combination of word is in words.txt, adding to table
                            editedTable[i][0] = word;
                            editedTable[i][1] = word_dict;
                        }
                    }
                }
                if ((editedTable[i][0] == null) && (editedTable[i][1] == null)) 
                {
                    editedTable[i][0] = word;
                    editedTable[i][1] = word;
                }
            }
            i+=1;
        }

        String newText = oldText.substring(0);
        for (String[] item : editedTable) 
        {       
            // scrolls over the table and creates fixed text
            int sizeOfWord = item[0].length();
            int startIndex = oldText.indexOf(item[0]);
            String sbstr = oldText.substring(startIndex, (startIndex + sizeOfWord));

            newText = newText.replace(sbstr, item[1].toString());
        }

        return newText;
    }

    public static ArrayList<String> readFile(String fileName) 
    {
        ArrayList<String> dict = new ArrayList<>();
        try 
        {             
            // with try-catch blocks, file is reading and every word is added to arrayList
            File file1 = new File("C:\\Users\\ysfnc\\Documents\\NetBeansProjects\\Editor\\src\\words.txt");
            Scanner file = new Scanner(file1);

            try 
            {
                while (file.hasNext()) 
                {
                    String satir = file.next();

                    dict.add(satir);
                }
                file.close();
            } catch (NoSuchElementException e) {
                System.out.println("No Such Element!" + e.toString());
            }

        }catch (FileNotFoundException e) {
            System.out.println("File Not Found!" + e.toString());
            System.exit(0);
        }

        return dict;
    }


    public static String[] singleTransposition(String wrongWord) 
    {
        String[] combinations = new String[wrongWord.length() - 1];

        for (int i = 0; i < wrongWord.length() - 1; i++) 
        {   
            // By exchanging two letters, all possible possibilities are created
            String copyWrongWord = wrongWord.substring(0);    // protected original form of word

            copyWrongWord = wrongWord.substring(0, i) + Character.toString(wrongWord.charAt(i + 1)) + Character.toString(wrongWord.charAt(i)) + wrongWord.substring(i + 2);
            combinations[i] = copyWrongWord;
        }
        return combinations;
    }
    
    
    public static boolean find(String area, String wanted) 
    {
        boolean result = false;
        
        if (area.contains(wanted)) 
        {
            result = true;
            JOptionPane.showMessageDialog(null, "Word found.");
            
        } else 
        {
            JOptionPane.showMessageDialog(null, "Word can not found.");
        }
        
        return result;
    }

}
