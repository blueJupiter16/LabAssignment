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
import java.util.Scanner;

public class Main {

    
    public static void main(String[] args) {
        
        Scanner inp=new Scanner(System.in);
        System.out.println("Enter the regex: ");
        String regex=inp.nextLine();
        System.out.println("Enter the string: ");
        String string=inp.nextLine();
        InfixToPostFix.infixToPost(regex);
        
        NFAState startstate = BuildNFA.postFixToNFA(InfixToPostFix.infixToPost(regex));
        Boolean matches = Match.match(startstate, string);
        if(matches)
            System.out.println("MATCH");
        else
            System.out.println("DOESN'T MATCH");
        
        
    }
}
