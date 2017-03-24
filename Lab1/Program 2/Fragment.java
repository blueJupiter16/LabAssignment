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

public class Fragment { //partial NFA
    private NFAState initialState;
    private CustomArrayList <NFAState> outArrows;
    
    
    public Fragment(NFAState initialState, NFAState out){    //starting state and outward state
        this.outArrows = new CustomArrayList<NFAState>();
        outArrows.add(out);
        this.initialState = initialState;
    }
    
    public Fragment(NFAState initialState, CustomArrayList outArrows){   //initialState state and transition arrows
        this.outArrows = outArrows;
        this.initialState = initialState;
    }
    
    public Fragment(){  //no starting state and no transition arrows
        initialState = null;
        outArrows = null;
    }
   
    public NFAState getStart(){
        return initialState;
    }
   
    public CustomArrayList getOutArrows(){
        return outArrows;
    }

}
