/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.test;

import static java.lang.System.out;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.json.JSONObject;

/**
 *
 * @author: Bryan Anaya.
 * 
 * @description: 
 * Para el ejercicio se pueden usar deferentes tipos de estructura, todo depende de la necesidad de proyecto 
 * y los datos a manejer, por ejemplo, en caso de almacenar correos electronicos de los usuarios, no es 
 * necesario tener datos repetidos ya en teoria deben ser unicos por lo cual se puede manejar una collecion 
 * como los set<> con las interfaces de HashSet o treeSet dependiento de la importancia del orden en los datos
 * ya que la colleccion no admiten duplicidad en sus registro.
 * 
 * Por otro lado estan las listas, en caso de que para los datos manejados se acepten duplicidad por tema de
 * analitica de datos se pueden usar estructuras List, LinkedList que son versatiles.
 * 
 * Para el proceso de almacenamiento y segun lo recomendado se desarrollan 3 opciones para el procesamiento
 * de la informacion haciendo uso de Steam API de Java 8+.
 */
public class testAnalyticLabWorks {
    
    
    
    
    
    private final Set<String> data = Stream.of("Bryan.Anaya@uao.edu.co", "michaelR@.com", "test@google.com.co","AnalyticLab@gmail.com",
        "bryananaya9321@live.com","Maki@outlook.com.co","Jdk.@org","banaya93@gmail.com","aawpds@gmail.com")
        .collect(Collectors.toCollection(HashSet::new)); //Collection for option 1.
    
    private Set<String> data2;   //Collection for option 2.
    
    private List<String> data3; //Collection for option 3.
    
    
    /**
     * Option 1: Function Filter
     * @param pattern: regex use to filter data in collection.
     */
    public void filter(String pattern){
        if(isNullOrEmpty(pattern)){
            Pattern regex = Pattern.compile(pattern);
            prt(1);
            data.stream().filter(regex.asPredicate()).forEach(System.out::println);
            prtEnd();
        }else{
            prtErr();
        }
    }
    
    /**
     * Option 2: Function to filter and store from data source (array) in a Set<> Collection using a input regex.
     * @param source: array data source
     * @param pattern: regex use to filter data in collection before store.
     */
    public void storeAndFilter(String[] source, String pattern){        
        if(isNullOrEmpty(source)){
            Pattern regex = Pattern.compile(pattern != null ? pattern : "");
            data2 = Arrays.asList(source).stream().filter(regex.asPredicate()).collect(Collectors.toSet());
            prt(2);
            data2.forEach((x) -> System.out.println(x));
            prtEnd();
        }else{
            prtErr();
        }
    }
    
    /**
     * Option 3: Method for store data from string to collection type List
     * @param data: set strings to store. 
     */
    public void storeData(String data){
        if(isNullOrEmpty(data)){
            data3 = Arrays.stream(data.split(",")).collect(Collectors.toList());
            prt(2);
            data3.forEach(System.out::println);
            prtEnd();
        }else{
            prtErr();
        }
    }
    
    /**
     * Option 3: Method for filter data stored in the list.
     * @param pattern: regex use to filter data in collection before store.
     */
    public void filterData(String pattern){
        if(isNullOrEmpty(pattern)){
            Pattern regex = Pattern.compile(pattern);
            prt(3);
            data3.stream().map(x -> regex.matcher(x)).filter(Matcher::find)
                    .map(x -> x.group())
                    .forEach(System.out::println);
            prtEnd();
        }else{
            prtErr();
        }
    }
    
    
    public String CountingDuplicates(String str){
        Map<String, Long> map = Arrays.asList(str.split("")).stream().collect(Collectors.groupingBy(c -> c.toLowerCase(), Collectors.counting()));
        //List<String> tmp = map.entrySet().stream().filter(entry -> entry.getValue() > 1).map(entry -> entry.getKey()).collect(Collectors.toList());
        Map<String, String> fnl = map.entrySet().stream().filter(e -> e.getValue() > 1).collect(Collectors
                .toMap(p->p.getKey(), p-> "'"+p.getKey()+"' ocurrs "+p.getValue()+" times "));
        //String s = fnl.entrySet().toString();
        
        JSONObject jres = new JSONObject(fnl);
        
        System.out.println(jres);
        /*System.out.print(map.size()==0 ? tmp.size()+" no characters repeats more than once " : tmp.size()+" # ");
        tmp.forEach(x -> System.out.print("'"+x+"' "+" occurs "+map.get(x)+" times, "));
        */
        //String res = map.isEmpty() ? "0 # no characters repeats more than once " : map.size()+" # ";
        
        
        
        //prtEnd();
        return null;
        
        
    }
    
    
    public boolean isNullOrEmpty(Object obj){
        return obj != null && !obj.toString().trim().isEmpty();
    }
    
    /** 
     * Aux. method for print in console start region option
     * @param s: Option Number to print
     */
    public void prt(int s){
        System.out.printf("--------- Result Option %s --------\n",s);
    }
    
    /** 
     * Aux. method for print in console end region option
     */
    public void prtEnd(){
        System.out.println("----------------------------------\n");
    }
    
    /** 
     * Aux. method for print in console exception by invalid input
     */
    public void prtErr(){
        System.out.println(" * Invalid input");
    }
    
    //Main 
    public static void main(String[] arg){
        // declaracion de variables auxiliares
        testAnalyticLabWorks test = new testAnalyticLabWorks();
        String[] array1 = {"Bryan.Anaya@uao.edu.co", "michaelR@.com","test@google.com.co","AnalyticLab@gmail.com",
            "bryananaya9321@live.com","Maki@outlook.com.co","Jdk.@org","banaya93@gmail.com","aawpds@gmail.com"};
        String regex = "^(.+)@gmail.com$";
        String pattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        String cadena = "CO-114416189,CO-114566978,MX-5984894894098,EC-49840845566664,EC-41448492489240,"
                + "CO-34534434,PE-554687,56464654-AR,CO-115645454,MX-842894444,EC-44654564411144";
       
        test.filter(null);      // test option 1
        test.filter(regex);     // test option 1
       
        test.storeAndFilter(null, pattern);     // test option 2
        test.storeAndFilter(array1, null);      // test option 2
        test.storeAndFilter(array1, pattern);   // test option 2
       
        test.storeData(null);       // test option 3 (store)
        test.storeData(cadena);     // test option 3 (store)
        test.filterData(null);      // test option 3 (store)
        test.filterData("\\d+");    // test option 3 (store)
        
        
        test.CountingDuplicates("abcde");
        test.CountingDuplicates("aabBcde");
        test.CountingDuplicates("indivisibility");
        test.CountingDuplicates("aA11");
        
    }    
}

