/* 
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor. 
 */ 

package thomsonnfa;



public class CustomStack<E>{
    private E[] stack;
    private int top;

    public CustomStack(){   
        stack = (E[])new Object[10];    //Generic Type
        top = -1;
    }
    
    public void push(E object){
        top++;
        checkSize();
        stack[top] = object;
    }
    
    public E pop(){
        E returnObject = stack[top];
        top--;
        return returnObject;
    }
    
    public E peek(){
        return stack[top];
    }
    
    public boolean isEmpty(){
        return (top < 0);
    }
    private void checkSize(){
        if(stack.length == top){
            E[] newArray = (E[])new Object[stack.length*2];
            for(int i = 0; i < stack.length; i++){
                newArray[i] = stack[i];
            }
            stack = newArray;
        }

    }
}
