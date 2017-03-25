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

public class InfixToPostFix {
    
    public static String infixToPost(String infixString){ //function to convert infix to postfix using the Shunting Yard Algorithm
        infixString = addConOperator(infixString);   //add concatenations
        CustomStack <Character> operationStack = new CustomStack<Character>();
        StringBuffer postfixString = new StringBuffer(); //postfix string
        for(int i = 0; i < infixString.length(); i++){
            char c = infixString.charAt(i);


            if(isAnOperator(c)){   
                if(operationStack.isEmpty()){ // if an operatorstack  and stack is empty push the operator
                     operationStack.push(c);
                }
                else{                           // else check for precedence if operator at top is of greater precedence than the character c then pop and keep doing it 
                    char top = operationStack.peek();
                    if(isAnOperator(top)){
                        int operatorPrecedence = precedence(c, top);
                        if (operatorPrecedence <= 0){
                            top = operationStack.pop();
                            postfixString.append(top);
                        }
                    }
                    operationStack.push(c);  // if character c is of higher precedence then simply push it 
                }
            }


           else if(isOpenBracket(c)){   // if open parentheses, push the element
               operationStack.push(c);   

            }


           else if(isCloseBracket(c)){  // if close parentheses, then keep on popping the operator unless open bracter is encountered
               char top = operationStack.pop();
               while(!isOpenBracket(top)){
                   postfixString.append(top);
                   top = operationStack.pop();
               }
            }
            else{                           // if c is literal then simply append the character
                postfixString.append(c);     
            }
        }
        while(!operationStack.isEmpty()){        // if no character left to parse, simply pop all elements and append to string
            postfixString.append(operationStack.pop());
        }
        System.out.println("POSTFIX: "+postfixString);
        return postfixString.toString();
    }

    
    public static String addConOperator(String original){    // function to explicitly add '.' between expressions like aa. So aa becomes a.a
        StringBuffer conString = new StringBuffer();
        conString.append(original.charAt(0));

        for(int i = 1; i < original.length(); i++){
            char c = original.charAt(i);
            if(isConPossible(conString))
                if(isCharacter(c) || isOpenBracket(c))
                    conString.append('.');
            conString.append(c);

        }
        return conString.toString();

    }



    private static int precedence(char a, char b){  //function to check the precedence of the operators
        if(isMultiplication(a)){
            if(isMultiplication(b))
                return 0;
            else
                return 1;
        }

        else if(isConcatenation(a)){
            if(isMultiplication(b))
                return -1;
            else if (b == a)
                return 0;
            else
                return 1;
        }

        else if(isOR(a)){
            if(a == b)
                return 0;
            else return -1;
        }

        return -2;
    }
    private static boolean isConPossible(StringBuffer conString) { //check if the there is a possibilitly of explicitly adding the concatenation operator
        return isCharacter(conString.charAt(conString.length() - 1))
                || isCloseBracket(conString.charAt(conString.length() - 1))
                || isMultiplication(conString.charAt(conString.length() - 1));
    }
    private static boolean isCharacter(char a){
        return !isAnOperator(a) && !isCloseBracket(a) && !isOpenBracket(a);
    }
    private static boolean isOR(char a) {
        return a == '|';
    }

    private static boolean isConcatenation(char a) {
        return a == '.';
    }

    private static boolean isMultiplication(char a) {
        return a == '*' ;
    }
    private static boolean isAnOperator(char a){
        return a == '*' ||   a == '|' ||a == '.';
    }

    private static boolean isCloseBracket(char c) {
        return c == ')';
    }

    private static boolean isOpenBracket(char c) {
        return c == '(';
    }
}
