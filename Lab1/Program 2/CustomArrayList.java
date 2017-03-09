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

public class CustomArrayList <E>{

    private E[] array;
    private int size;
    
    public CustomArrayList(){
        array = (E[])new Object[10];    //Generic Type
        size = 0;
    }
    
    public void add(E object){
        checkSize();
        array[size] = object;
        size++;
    }
    

    public E get(int i){
        return array[i];
    }
    
    public int size(){
        return size;
    }

    private void checkSize(){
        if(array.length == size){
            E[] newArray = (E[])new Object[array.length*2];
            for(int i = 0; i < array.length; i++){
                newArray[i] = array[i];
            }
            array = newArray;
        }

    }


}
