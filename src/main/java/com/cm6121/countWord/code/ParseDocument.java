package com.cm6121.countWord.code;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ParseDocument {

    public List<String> readFile(String... pathDocument) throws IOException {
        StringBuffer sb = new StringBuffer();
        for(String s:pathDocument){
            sb.append(s);
            sb.append("/");
        }

        Path path = Paths.get(System.getProperty("user.home"), sb.toString());
        List<String> strings = Files.readAllLines(path);
        return strings;
    }

    public Document documentParse(List<String> allLines){
        Document document = null;
        String line = allLines.get(1);

        if(allLines.size()>0) {
            String[] split = line.split("\"");
            document = new Document();
            document.setTitle(split[0].substring(0, split[0].length() - 1).trim());
            document.setText(split[1].trim());
            document.setCreationDate(split[2].substring(1).trim());
        }
        else{
            System.out.println("Problems with parsing the file");
        }
        return document;
    }

    public HashMap<String, Integer> readNumberWords(String wordToRead, String parameterSplit){
        HashMap<String, Integer> noOfWords = new HashMap<>();
        wordToRead = wordToRead.replaceAll("\\p{Punct}", " ").trim();
        wordToRead = wordToRead.replaceAll("\\s{1}", " ");
        wordToRead = wordToRead.replaceAll("[^a-zA-Z]", " ").trim();
        wordToRead = wordToRead.toLowerCase();
        String[] words = wordToRead.split(parameterSplit);

        for (String w:words){
            if(w.length()>1){
                if(noOfWords.containsKey(w)){
                    noOfWords.put(w, noOfWords.get(w)+1);
                }
                else{
                    noOfWords.put(w, 1);
                }
            }
        }
        return noOfWords;
    }

    public static <K,V extends Comparable<? super V>> Map<K,V> printWordsOccurrences(Map<K,V> wordsMap){
        List<Map.Entry<K,V>> listWords = new ArrayList<>(wordsMap.entrySet());
        listWords.sort(Map.Entry.comparingByValue());

        Map<K,V> ascendingSort = new LinkedHashMap<>();
        for(Map.Entry<K,V> entry : listWords){
            ascendingSort.put(entry.getKey(), entry.getValue());
        }
        for(Map.Entry<K,V> entry : listWords) {
            System.out.println("Word: " + entry.getKey() + " ;" + " Value: " + entry.getValue());
        }
        return ascendingSort;
    }


    public static <K,V extends Comparable<? super Integer>> Map<K,V> printAllWordsOccurrences(Map<K,V> allWordsMap){
        List<Map.Entry<K,V>> listAllWords = new ArrayList<>(allWordsMap.entrySet());
        listAllWords.sort(Map.Entry.comparingByValue((Comparator<? super V>) Comparator.reverseOrder()));

        Map<K,V> descendingSort = new LinkedHashMap<>();
        for(Map.Entry<K,V> entry : listAllWords){
            descendingSort.put(entry.getKey(), entry.getValue());
        }
        return descendingSort;
    }

    public <K,V> void printFirst20Words(Map<K, V> first20Words){
        int count = 0;
        for(Map.Entry<K,V> entry : first20Words.entrySet()) {
            count++;
            System.out.println("Word: " + entry.getKey() + " ;" + " Value: " + entry.getValue());
            if(count == 20){
                break;
            }
        }

    }
}
