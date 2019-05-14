/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p9_package;
/**
 * 
 * @author ggeary3
 */
public class GenericHeapClassMain
   {

   public static void main(String[] args)
      {
       int index, maxItems = 20;
       GenericHeapClass<OpCodeClass> ghc = new GenericHeapClass<OpCodeClass>();
       OpCodeClass newItem, removedItem;
       ghc.setDisplayFlag( false );
       System.out.println( "Adding Items to heap");
       System.out.println( "Items are added by priority first, and entry item next.");
       long start = System.nanoTime();
       for( index = 0; index < maxItems; index++ )
          {
           newItem = new OpCodeClass( index + 1 );
           
           ghc.addItem( newItem );
           
           ghc.showArray();
          }
       GenericHeapClass<OpCodeClass> ghCopy = new GenericHeapClass<OpCodeClass>(ghc);
       ghCopy.showArray(); 
       /*
       long end = System.nanoTime();
       long timeTaken = start - end;
       System.out.print("Time taken to load 1,000,000 items into a heap");
       long divide = 1000000000;
       System.out.println(timeTaken);
       */
       /*
       System.out.println( "\nRemoving Items from heap");
       //start = System.nanoTime();
       while( !ghc.isEmpty() )
          {
           removedItem = ghc.removeItem();
           ghc.showArray();
           System.out.println( removedItem.toString() );
          }
       */
       /*
        end = System.nanoTime();
        timeTaken = start - end;
        System.out.print("Time taken to remove 1,000,000 items into a heap");
        divide = 1000000000;
        System.out.println(timeTaken);
*/      
}

   }