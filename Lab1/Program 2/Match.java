/* 
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor. 
 */ 

package thomsonnfa;

/** 
 * 
 * @author Saksham Rastogi 
 */ 
public class Match {
    
    public static boolean match(NFAState startstate, String input){
        CustomArrayList <NFAState> currStatesList = new CustomArrayList<NFAState> ();
        CustomArrayList <NFAState> nextStateList = new CustomArrayList<NFAState> ();
        int listID = 0; // to check if the state is already current State List
        addState(currStatesList, startstate, listID); //initializing the currentlist with the start state
        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            listID = step(currStatesList, c, nextStateList, listID);
            currStatesList = nextStateList;
            nextStateList = new CustomArrayList<NFAState>();
        }
        return isMatch(currStatesList);
    }
    private static boolean isMatch(CustomArrayList<NFAState> finalList){
        for (int i = 0; i < finalList.size(); i++){
            NFAState s = finalList.get(i);
            if (s.isMatch()){
                return true;
            }
        }
        return false;
    }
    private static int step(CustomArrayList<NFAState> currStatesList, char c, CustomArrayList<NFAState> nextStateList, int listID){
        listID++;
        for (int i = 0; i < currStatesList.size(); i++){
            NFAState s = currStatesList.get(i);
            if(c == s.getChar()){
                addState(nextStateList, s.getOut1(), listID);
            }
        }
        return listID;
    }
    private static void addState(CustomArrayList<NFAState> list, NFAState s, int listID){
        if (s == null || s.getLastlist() == listID)
            return;
        s.setLastlist(listID);
        if(s.isSplit()){ // if s is split state with two unlabelled arrows to new states, add these states
            addState(list, s.getOut1(), listID);
            addState(list, s.getOut2(), listID);
            return;
        }
        list.add(s);
    }
}
