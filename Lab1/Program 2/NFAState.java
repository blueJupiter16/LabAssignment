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

public class NFAState {
    private char c;
    private NFAState out1; //represent the state one of the split state
    private NFAState out2; //represent the state two of the spilt state
    private boolean split;  //represents two outgoing transitions from the state
    private boolean match; //represents the final state
    private boolean hasChar;
    private int lastlist;

    public NFAState(char c){   //Constructor for initializing the literal state
        this.c = c;
        this.out1 = null;
        this.out2 = null;
        this.hasChar = true;
        this.split = false;
        this.match = false;
        this.lastlist = -1;
    }
    
    public NFAState(NFAState out1, NFAState out2){   //Constructor to initialize the split state
        this.hasChar = false;
        this.split = true;
        this.match = false;
        this.out1 = out1;
        this.out2 = out2;
        this.lastlist = -1;
    }
    
    
    public NFAState(){ //Constructor to initialize the final state
        this.hasChar = false;
        this.split = false;
        this.match = true;
        this.out1 = null;
        this.out2 = null;
        this.lastlist = -1;
    }
    
    public boolean isLiteralState(){    //If the state has a character associated with it, then it is a literal state otherwise not
        return hasChar;
    }
    
    public char getChar(){
        return c;
    }
    
    public boolean isSplit(){
        return split;
    }
    
    public boolean isMatch(){
        return match;
    }
    
    public NFAState getOut1(){
        return out1;
    }
    
    public NFAState getOut2(){
        return out2;
    }
    
    public void patch(NFAState s){ //function to make the state transition  point to NFAState s
        if(out1 == null)
            out1 = s;
        if(out2 == null && split)
            out2 = s;
    }
    
    public int getLastlist(){
        return lastlist;
    }
    
    public void setLastlist(int listindex){
        lastlist = listindex;
    }
    
}
