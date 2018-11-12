package Project;

/* Created by
 * Group 5
 * Bala Guna Teja Karlapudi
 * Nikhil Jadhav
 * Phyllis Jones
 * Saketh Kumar Kappala
 */


//Import statements
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ActionRules {
	public static PrintWriter writer;
	public static Scanner input;
	public static int supportThreshold;
	public static int confidenceThreshold;
	public static String dataFilepath;
	public static List<String> attrNames = new ArrayList<String>();
	public static List<String> stableAttr = new ArrayList<String>();
	public static List<String> flexAttr = new ArrayList<String>();
	public static String decisionAttr,decisionFrom,decisionTo;
	
	public static ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>(); 
	static Map<String, HashSet<String>> distinctAttrValue = new HashMap<String, HashSet<String>>();
	static Map<HashSet<String>, HashSet<String>> attrValue = new HashMap<HashSet<String>, HashSet<String>>();
	static Map<HashSet<String>, HashSet<String>> singleItemsets = new HashMap<HashSet<String>, HashSet<String>>();
	
	static Map<HashSet<String>, HashSet<String>> reducedAttributeValues = new HashMap<HashSet<String>, HashSet<String>>();
	static Map<String, HashSet<String>> decisionValues = new HashMap<String, HashSet<String>>();
	static Map<ArrayList<String>, HashSet<String>> markedValues = new HashMap<ArrayList<String>, HashSet<String>>();
	
	public static Map<ArrayList<String>,String> certainRules = new HashMap<ArrayList<String>,String>();
	public static Map<ArrayList<String>,String> certainRulesForPrintActionRules = new HashMap<ArrayList<String>,String>();
	public static Map<ArrayList<String>,HashSet<String>> possibleRules = new HashMap<ArrayList<String>,HashSet<String>>();
	
	
    //mapping the support threshold from Input class to the local variable
	public static void SetSupportThreshold(int supportThreshold) {
		ActionRules.supportThreshold = supportThreshold;
	}
	//mapping the confidence threshold from Input class to the local variable
	public static void SetConfidenceThreshold(int confidenceThreshold) {
		ActionRules.confidenceThreshold = confidenceThreshold;
	}
	//mapping the file path from Input class to the local variable
	public static void SetDataFilePath(String dataFilePath) {
		ActionRules.dataFilepath = dataFilePath;
		readData();
	}
	 //mapping the flexible attributes from Input class to the local variable
	public static void SetAttributeNames(String[] attributeNames) {
		ActionRules.attrNames = new ArrayList<String>(Arrays.asList(attributeNames));
	    flexAttr = new ArrayList<String>(Arrays.asList(attributeNames));
	}
	 //mapping the decision attributes from Input class to the local variable
	public static void SetDecisionAttribute(String decisionAttribute) {
		ActionRules.decisionAttr = decisionAttribute;
	    flexAttr.remove(decisionAttribute);
		HashSet<String> listOfDecisionValues = distinctAttrValue.get(decisionAttribute);
		removeDecisionValueFromAttributes(listOfDecisionValues);
	
	}
	 //mapping the stable attributes from Input class to the local variable
	public static void SetStableAttribute(int[] stableAttributesIndex) {
	    for (int attributeIndex:stableAttributesIndex) {
	        stableAttr.add(attrNames.get(attributeIndex));
	    }

	    for (String attribute:stableAttr) {
	        if(flexAttr.contains(attribute)) {
	            flexAttr.remove(attribute);
	        }
	    }
	}
    //fetch decision from value from InputRead class to the local variable
	public static void SetDecisionFromValue(String inputDecisionFrom)
	{
		inputDecisionFrom = decisionAttr + inputDecisionFrom;
		
		if (inputDecisionFrom != null && !inputDecisionFrom.isEmpty()){
			if(decisionValues.keySet().contains(inputDecisionFrom)){
				ActionRules.decisionFrom = inputDecisionFrom;
			}
		}
	    else {
	        printMessage("Invalid value.");
	    }
	}
    //fetch decision to value from InputRead to the local variable
	public static void SetDecisionToValue(String inputDecisionTo){
		
		inputDecisionTo = decisionAttr + inputDecisionTo;
		
		if (inputDecisionTo != null && !inputDecisionTo.isEmpty()){
			if(decisionValues.keySet().contains(inputDecisionTo)){
				ActionRules.decisionTo = inputDecisionTo;
			}
		}
	    else {
	        printMessage("Invalid value.");
	    }
	}

	//Print String,List,Map
	private static void printMessage(String content){
		System.out.println(content);
		writer.println(content);
	}
	
	public static void printList(List<String> list){
		Iterator<String> iterate = list.iterator();
		
		while(iterate.hasNext()){
			printMessage(iterate.next().toString());
		}
	}
	
	private static void printCertainRulesMap(Map<ArrayList<String>, String> value) {
		printMessage("\nCertain Rules:");
		if (value == null || value.isEmpty()){
			printMessage("None");
		}
		for(Map.Entry<ArrayList<String>,String> set : value.entrySet()){
			if (set.getValue().equals(decisionTo)){
				printMessage("set key: "+set.getKey().toString());
				printMessage("decision to: "+decisionTo.toString());
				certainRulesForPrintActionRules.put(set.getKey(), decisionTo.toString());
				printMessage(set.getKey().toString() + " -> "+decisionTo);
			}
		}
	}
	
    //Print all possible rule maps using decision to value,support,confidence taken from the user
	private static void printPossibleRulesMap(Map<ArrayList<String>, HashSet<String>> value) {
		if(!value.isEmpty()){
			printMessage("\nPossible Rules:");
			for(Map.Entry<ArrayList<String>,HashSet<String>> set : value.entrySet()){
				for(String possibleValue:set.getValue()){
					if (possibleValue.equals(decisionTo)){
						int support = calculateSupport(set.getKey(),possibleValue);
						int confidence = calculateConfidence(set.getKey(),possibleValue);
						printMessage(set.getKey().toString() + " -> " + possibleValue + " [Support: " + support + ", Confidence: " + confidence +"%]");
					}
				}
			}
		}
	}
	
	
    //Print all marked values using the "markedvalues" hashmap
	private static void printMarkedValues() {
		printMessage("\nMarked Values:");
		for(Map.Entry<ArrayList<String>, HashSet<String>> markedSet : markedValues.entrySet()){
			attrValue.remove(new HashSet<String>(markedSet.getKey()));
		
			printMessage(markedSet.toString());
		}
	}

	private static int findSupport(ArrayList<String> tempList) {
		int count = 0;
		
		for(ArrayList<String> data : data){	
			if(data.containsAll(tempList))
				count++;
		}	
		return count;
	}
	
	private static int calculateSupport(ArrayList<String> key, String value) {
		ArrayList<String> tempList = new ArrayList<String>();
		
		for(String val : key){
			tempList.add(val);
		}		
		if(!value.isEmpty())
			tempList.add(value);
	
		return findSupport(tempList);
		
	}
// calculate the confidence 
	private static int calculateConfidence(ArrayList<String> key,
			String value) {
		int num = calculateSupport(key, value);
		int den = calculateSupport(key, "");
		int confidence = 0;
		if (den != 0){
			confidence = (num * 100)/den;
		} 
		return confidence;
	}
	
	private static void readData() {
		try {
			input = new Scanner(new File(dataFilepath));
			int lineNo = 0;
			
			while(input.hasNextLine()){
				String[] lineData = input.nextLine().split(",");
				String key;
				
				lineNo++;
				ArrayList<String> tempList = new ArrayList<String>();
				HashSet<String> set;
				
				for (int i=0;i<lineData.length;i++) {
					String currentAttributeValue = lineData[i];
					String attributeName = attrNames.get(i);
					key = attributeName + currentAttributeValue;
					
					tempList.add(key);

					HashSet<String> mapKey = new HashSet<String>();
					mapKey.add(key);
					setMap(attrValue,lineData[i],mapKey,lineNo);
					setMap(singleItemsets,lineData[i],mapKey,lineNo);
					
					if (distinctAttrValue.containsKey(attributeName)) {
						set = distinctAttrValue.get(attributeName);
						set.add(key);
						
					}else{
						set = new HashSet<String>();
					}
					
					set.add(key);
					distinctAttrValue.put(attributeName, set);
				}
		
				data.add(tempList);
			}
		} catch (FileNotFoundException e) {
			printMessage("File Not Found");
			e.printStackTrace();
		}
	}

	private static void setMap(Map<HashSet<String>, HashSet<String>> values,
			String string, HashSet<String> key, int lineNo) {
		HashSet<String> tempSet;
		
		if (values.containsKey(key)) {
			tempSet = values.get(key);						
		}else{
			tempSet = new HashSet<String>();
		}
		
		tempSet.add("x"+lineNo);
		values.put(key, tempSet);
	}

	private static void removeDecisionValueFromAttributes(HashSet<String> listOfDecisionValues) {
		for(String value : listOfDecisionValues){
			HashSet<String> newHash = new HashSet<String>();
			newHash.add(value);
			ActionRules.decisionValues.put(value, attrValue.get(newHash));
			attrValue.remove(newHash);
		}
	}
//printing the rules generated 
	private static void findRules() {
		int loopCount = 0;
		printMessage("\n***********************************");
		printMessage("\nGenerating Certain Rules");
		printMessage("\n***********************************");
		
		while(!attrValue.isEmpty()){
			printMessage("\nLoop " + (++loopCount) +":");
			printMessage("***********************************");
			
			
			for (Map.Entry<HashSet<String>, HashSet<String>> set : attrValue.entrySet()) {
				ArrayList<String> setKey = new ArrayList<String>();
				setKey.addAll(set.getKey());
				
				if (set.getValue().isEmpty()) {
					continue;
				}else{
					if(!includesMarked(setKey)){
						for(Map.Entry<String, HashSet<String>> decisionSet : decisionValues.entrySet()){
							if(decisionSet.getValue().containsAll(set.getValue())){
								certainRules.put(setKey, decisionSet.getKey());
								markedValues.put(setKey, set.getValue());
								break;
							}
						}
					}
				}
				
				if(!includesMarked(setKey)){
					HashSet<String> possibleRulesSet = new HashSet<String>();
					for(Map.Entry<String, HashSet<String>> decisionSet : decisionValues.entrySet()){
						
						possibleRulesSet.add(decisionSet.getKey());
					}
					possibleRules.put(setKey, possibleRulesSet);
				}
				
			}
			printMarkedValues();
			removeMarkedValues();
			
			printCertainRulesMap(certainRules);
			printPossibleRulesMap(possibleRules);
			
			combinePossibleRules();
		}
	}

	private static boolean includesMarked(ArrayList<String> setKey) {
		for(Map.Entry<ArrayList<String>, HashSet<String>> markedSet : markedValues.entrySet()){
			if(setKey.containsAll(markedSet.getKey())){
				return true;
			}
		}
		return false;
	}	
	
	private static void removeMarkedValues() {
		for(Map.Entry<ArrayList<String>, HashSet<String>> markedSet : markedValues.entrySet()){
			attrValue.remove(new HashSet<String>(markedSet.getKey()));
		}
	}
	
	private static void combinePossibleRules() {
		Set<ArrayList<String>> keySet = possibleRules.keySet();
		ArrayList<ArrayList<String>> keyList = new ArrayList<ArrayList<String>>();
		keyList.addAll(keySet);
		HashSet<String> possibleRule;
		for(int i = 0;i<possibleRules.size();i++){
			for(int j = (i+1);j<possibleRules.size();j++){
				possibleRule = new HashSet<String>(keyList.get(j));
				Iterator<String> iter = possibleRule.iterator();
				HashSet<String> combinedKeys = null;
				while (iter.hasNext()) {
				    combinedKeys = new HashSet<String>(keyList.get(i));
				    if (combinedKeys.add((String)iter.next())){
				    	if(!checkSameGroup(combinedKeys)){
				    		combineAttributeValues(combinedKeys);
				    	}
				    }
				}
			}
		}
		
		certainRules.clear();
		possibleRules.clear();
		
		removeRedundantValues();
		clearAttributeValues();
	}

	private static boolean checkSameGroup(HashSet<String> combinedKeys) {
		List<String> list = new ArrayList<String>(combinedKeys);
		HashSet<String> pair = new HashSet<String>();
		if (combinedKeys.size()==2){
			if (isPairSameGroup(combinedKeys))
				return true;
		} else {
			for(int i = 0;i<list.size()-1;i++){
				for(int j = i+1;j<list.size();j++){
					pair.add(list.get(i));
					pair.add(list.get(j));
					if (isPairSameGroup(pair))
						return true;
					pair.clear();
				}
			}
		}
		return false;
	}

	private static boolean isPairSameGroup(HashSet<String> pair) {
		for(Map.Entry<String, HashSet<String>> singleAttribute : distinctAttrValue.entrySet()){
			if(singleAttribute.getValue().containsAll(pair)){
				return true;
			}
		}
		return false;
	}
	
    private static HashSet<String> findSupportActionSchema(ArrayList<String> key, String value) {
        // Find support of generalized action schema
        ArrayList<String> tempList = new ArrayList<String>();
        
        for(String val : key){
            if (stableAttr.contains(Character.toString(val.charAt(0)))) {
                tempList.add(val);
            }
        }
        
        if(!value.isEmpty())
            tempList.add(value);
        
        HashSet<String> supportActionSchema = findPrintActionRulesSupport(tempList);
        
        // Find support of bad rule leading wrong decision
        tempList.clear();
        for(String val : key){
            tempList.add(val);
        }
        if(!value.isEmpty())
            tempList.add(value);
        
        HashSet<String> supportBadRule = findPrintActionRulesSupport(tempList);
        
        // Correcting support for generalized action schema
        supportActionSchema.removeAll(supportBadRule);
        
        return supportActionSchema;
    }
	
	private static void combineAttributeValues(HashSet<String> combinedKeys) {
		HashSet<String> combinedValues = new HashSet<String>();
			
		for(Map.Entry<HashSet<String>, HashSet<String>> attributeValue : attrValue.entrySet()){
			if(combinedKeys.containsAll(attributeValue.getKey())){
				if(combinedValues.isEmpty()){
					combinedValues.addAll(attributeValue.getValue());
				}else{
					combinedValues.retainAll(attributeValue.getValue());
				}
			}
		}
		if (!checkSameGroup(combinedKeys))
			reducedAttributeValues.put(combinedKeys, combinedValues);
	}

	private static void removeRedundantValues() {
		HashSet<String> mark = new HashSet<String>();
		
		for(Map.Entry<HashSet<String>, HashSet<String>> reducedAttributeValue : reducedAttributeValues.entrySet()){
			for(Map.Entry<HashSet<String>, HashSet<String>> attributeValue : attrValue.entrySet()){
				
				if(attributeValue.getValue().containsAll(reducedAttributeValue.getValue()) || reducedAttributeValue.getValue().isEmpty()){
					mark.addAll(reducedAttributeValue.getKey());
				}
			}
		}
		reducedAttributeValues.remove(mark);
	}
	
    private static HashSet<String> findPrintActionRulesSupport(ArrayList<String> tempList) {
        HashSet<String> tempSet = new HashSet<String>();
        
        int count = 1;
        for(ArrayList<String> data : data){
            if(data.containsAll(tempList))
                tempSet.add("x"+count);
                count++;
        }
        
        return tempSet;
    }

	
	private static void clearAttributeValues() {
		 attrValue.clear();
		 for(Map.Entry<HashSet<String>, HashSet<String>> reducedAttributeValue : reducedAttributeValues.entrySet()){
			 if (!checkSameGroup(reducedAttributeValue.getKey()))
				 attrValue.put(reducedAttributeValue.getKey(), reducedAttributeValue.getValue());
		 }
		 reducedAttributeValues.clear();
	}

	
	private static void generateActionRules() {
        String actionRuleSchema;
        String actionRule;
        String attributeName = "";
        String attributeValueTo = "";
        String attributeValueFrom = "";
		
		printMessage("\n***********************************");
		printMessage("\nGenerating Action Rules");
		printMessage("\n***********************************");
		
        // Run PrintActionRules for every Certain Rule
		for(Map.Entry<ArrayList<String>, String> certainRule : certainRulesForPrintActionRules.entrySet()){
            ArrayList<String> attrInRule = new ArrayList<String>();
            for (String attr : certainRule.getKey()) {
                    attrInRule.add(Character.toString(attr.charAt(0)));
            }

            // Find header of stable attributes that are in given certain rule
            ArrayList<String> header = new ArrayList<String>();
            for (String attr : certainRule.getKey()) {
                if (stableAttr.contains(Character.toString(attr.charAt(0)))) {
                    header.add(attr);
                }
            }

            // Find Action Rule Schema
    		printMessage(" ");
    		printMessage(" ");
			printMessage("Action Rule Schema: ");
            actionRuleSchema = "";
            for (String attr: certainRule.getKey()) {
                attributeName = Character.toString(attr.charAt(0));
                attributeValueFrom = "";
                attributeValueTo = Character.toString(attr.charAt(1));
                
                if (stableAttr.contains(attributeName))
                	actionRuleSchema = formRule(actionRuleSchema, attributeName, "", attributeValueTo, true);
                
                if (flexAttr.contains(attributeName))
                	actionRuleSchema = formRule(actionRuleSchema, attributeName, "", attributeValueTo, false);
            }
            printMessage("[" + actionRuleSchema + "] -> (" + decisionAttr + ", " + decisionFrom + "->" + decisionTo + ")");

            // Find support for Action Rule Schema
    		printMessage(" ");
            HashSet<String> supportActionSchema = findSupportActionSchema(certainRule.getKey(), decisionFrom);
            printMessage("Support for Action Schema: "+supportActionSchema.toString());
        
            // Find list of attribute values, which are not considered in Action Rule Schema
            ArrayList<String> remainingVals = new ArrayList<String>();
            for (String sAttr : stableAttr) {
                if (!attrInRule.contains(sAttr)) {
                    remainingVals.addAll(new ArrayList<String>(distinctAttrValue.get(sAttr)));
                }
            }
            for (String fAttr : flexAttr) {
                if (attrInRule.contains(fAttr)) {
                    for (String fAttrVal: new ArrayList<String>(distinctAttrValue.get(fAttr))) {
                        if (!certainRule.getKey().contains(fAttrVal)) {
                            remainingVals.add(fAttrVal);
                        }
                    }
                }
            }
        
            // Find Marked Sets
    		printMessage(" ");
            printMessage("Marked Sets: ");
            ArrayList<ArrayList<String>> markedRules = new ArrayList<ArrayList<String>>();
            for (String attrVal: remainingVals) {
                ArrayList<String> tempRule = new ArrayList<String>();
                tempRule.addAll(header);
                tempRule.add(attrVal);
                
                HashSet<String> tempSupport = findPrintActionRulesSupport(tempRule);
                
                if (!tempSupport.isEmpty()) {
	                if (supportActionSchema.containsAll(tempSupport)) {
	                    printMessage(tempRule.toString()+": "+tempSupport.toString());
	                    markedRules.add(tempRule);
	                }
                }
            }
            if (markedRules.isEmpty()) {
            	printMessage("None");
            }
        
            // Print Action Rules
    		printMessage(" ");
            printMessage("Action Rules: ");
            for (ArrayList<String> markedRule: markedRules) {
                actionRule = "";

                for (String attr: certainRule.getKey()) {
                    attributeName = Character.toString(attr.charAt(0));
                    attributeValueFrom = "";
                    attributeValueTo = Character.toString(attr.charAt(1));
                    
                    if (stableAttr.contains(attributeName)) {
                        markedRule.remove(attributeName+attributeValueTo);
                        actionRule = formRule(actionRule, attributeName, "", attributeValueTo, true);
                    }
                    
                    if (flexAttr.contains(attributeName)) {
                    	String removeAttr = "";
                        for (String markedAttr: markedRule) {
                            if (Character.toString(markedAttr.charAt(0)).equals(attributeName)) {
                                attributeValueFrom = Character.toString(markedAttr.charAt(1));
                                removeAttr = markedAttr;
                                break;
                            }
                        }
                        
                        if (!attributeValueFrom.isEmpty()) {
                            markedRule.remove(removeAttr);
                            actionRule = formRule(actionRule, attributeName, attributeValueFrom, attributeValueTo, false);
                        }
                        else {
                            actionRule = formRule(actionRule, attributeName, "", attributeValueTo, false);
                        }
                    }
                }
                
                printMessage("[" + actionRule + "] -> (" + decisionAttr + ", " + decisionFrom + "->" + decisionTo + ")");
            }
            if (markedRules.isEmpty()) {
            	printMessage("None");
            }
        }
	}
	
	private static String formRule(String rule, String attributeName, String attributeValueFrom, String attributeValueTo, boolean isStable) {
        if(!rule.isEmpty())
            rule += "^";

        if (isStable) {
            rule += attributeName + attributeValueTo;
        }
        else {
            rule += "(" + attributeName + ", ";
            if (!attributeValueFrom.isEmpty())
                rule += attributeName + attributeValueFrom;
            rule += "->" + attributeName + attributeValueTo + ")";
        }

        return rule;
    }

	public static void GenerateActionRules() throws IOException{
		try {
			//Find All Certain and Possible rules
			writer = new PrintWriter("Output_ActionRules.txt", "UTF-8");
			findRules();
			generateActionRules();
			writer.close();
			
		} catch (Throwable t){
			System.out.println("Fatal error: "+ t.toString());
			t.printStackTrace();
		}
		
		
		ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "Output_ActionRules.txt");
		pb.start();
		
	}
}

