package scrambledstrings;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    public static void main(String[] args) {

        Options options = new Options();

        Option dictionaryArg = new Option("d", "dictionary", true, "dictionary file path");
        dictionaryArg.setRequired(true);
        options.addOption(dictionaryArg);

        Option inputArg = new Option("i", "input", true, "input file path");
        inputArg.setRequired(true);
        options.addOption(inputArg);


        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;//not a good practice, it serves it purpose

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        String dictionary_path = cmd.getOptionValue("dictionary");
        String input_path = cmd.getOptionValue("input");

        System.out.println(input_path);
        System.out.println(dictionary_path);

        String[] inputs = getInputArray(input_path);
        String[] dictionary = getInputArray(dictionary_path);

        //======

        for(String dicInp : inputs){
            int inputLineNumber = 1;
            int totalScore = 0;
            for (String str : dictionary) {
                int score = 0;
                String shuffledString = "";

                ArrayList<String> shuffled = new ArrayList<String>();
                shuffled.add(str);

                //get first and last character for appending
                char firstChar = str.charAt(0);
                char lastChar = str.charAt(str.length()-1);

                //get inner characters
                String myWord = str.substring(1,str.length()-1);

                //improve algo for match checking of shuffled string and input
                for(int x = 0; x<=99999; x++){

                    List<String> letters = Arrays.asList(myWord.split(""));
                    Collections.shuffle(letters);
                    shuffledString = "";
                    for (String letter : letters) {
                        shuffledString += letter;
                    }

                    //lookup shuffled dictionary word on the input file
                    Pattern stringToLookup = Pattern.compile(firstChar + shuffledString.toString() + lastChar, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = stringToLookup.matcher(dicInp);

                    //once we found
                    boolean matchFound = matcher.find();
                    if(matchFound) {
                        score = 1;
                        //need to break since multiple occurences of shuffled dictionary.txt into an input is counted as one
                        break;
                    }
                };
                //count total how many dictionary.txt entries are seen in the input
                totalScore += score;

            }
            System.out.println("Case#" + inputLineNumber + ": " + totalScore);
        }

    }


    public static String[] getInputArray(String path){

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            StringBuilder sb = new StringBuilder();
            List<String> list = new ArrayList<String>();

            while(line != null) {
                list.add(line);
                line = reader.readLine();
            }
            reader.close();

            String[] input_array = list.toArray(new String[0]);

            return input_array;

        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Unable to read the file.");
        }

        return null;

    }

}
