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
public class BuildNFA {

    public static NFAState postFixToNFA(String postfix){    //creates NFA out of the postfix expression
        
        CustomStack <Fragment> fragStack = new CustomStack <Fragment> ();
        Fragment finalNFA = new Fragment();
        NFAState matchstate = new NFAState();
        for (int i = 0; i < postfix.length(); i++){
            char c = postfix.charAt(i);
            if(isLiteral(c)){           // if character is a literal the construct a literal state and push it into the fragment stack
                NFAState literalState = new NFAState(c);    
                fragStack.push(new Fragment(literalState, literalState));
            }
            else if (isConcatenation(c)){   // if the operator is concatenation operator then pop the top two literal fragments
                Fragment top2 = fragStack.pop();
                Fragment top1 = fragStack.pop();
                patchFragState(top1, top2.getStart()); // join the fragment2's start state to fragment1
                fragStack.push(new Fragment(top1.getStart(), top2.getOutArrows() ) ); // push the new Fragment 
            }
            else if (isOR(c)){
                Fragment top2 = fragStack.pop();
                Fragment top1 = fragStack.pop();
                NFAState newState = new NFAState(top1.getStart(), top2.getStart());   // create a new state with outward arrows pointing to the starting states of two fragment top1 and top2
                fragStack.push(new Fragment(newState, appendOutArrows(top1.getOutArrows(), top2.getOutArrows()))); // push the fragment into the stack
            }
            else if (isStar(c)){
                Fragment previous = fragStack.pop();
                NFAState newState = new NFAState(previous.getStart(), null); // create a new state that points to fragments's start state
                patchFragState(previous, newState);  
                fragStack.push(new Fragment(newState, newState)); // push the fragment onto the stack

            }
        }
        finalNFA = fragStack.pop(); // pop the final NFA fragment 
        patchFragState(finalNFA, matchstate);    //patch 
        return finalNFA.getStart();
    }

    private static boolean isLiteral(char c) {      
        return !isConcatenation(c) && !isOR(c) && !isStar(c);
    }

    private static boolean isStar(char c) {
        return c == '*';
    }

    private static boolean isOR(char c) {
        return c == '|';
    }

    private static boolean isConcatenation(char c) {
        return c == '.';
    }
    private static CustomArrayList<NFAState> appendOutArrows(CustomArrayList<NFAState> state, CustomArrayList<NFAState> b){ //put the states of two fragments into on single fragment 
        CustomArrayList <NFAState> finalAppended = new CustomArrayList <NFAState> ();
        for (int i = 0; i < state.size(); i++){
            finalAppended.add(state.get(i));
        }
        for (int i = 0; i < b.size(); i++){
            finalAppended.add(b.get(i));
        }
        return finalAppended;
    }
    private static void patchFragState(Fragment frag, NFAState s){      // join the newly created state to the fragment
        CustomArrayList<NFAState> patched = frag.getOutArrows();
        for (int i = 0; i < patched.size(); i++){
            NFAState openarrows = patched.get(i);
            openarrows.patch(s);
        }
    }
}
