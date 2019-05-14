
package p9_package;

/**
 *
 * @author ggear
 */
public class GenericHeapClass<GenericData extends java.lang.Comparable<GenericData>>
extends java.lang.Object {
    /**
     * Initial array capacity
     */
    public final int DEFAULT_ARRAY_CAPACITY = 10;
    /**
     * Array for heap
     */
    private java.lang.Object[] heapArray;
    /**
     * Management data for array
     */
    private int arraySize;
    /**
     * Management data for array
     */
    private int arrayCapacity;
    /**
     * Display flag can be set to observe bubble up and trickle down operations
     */
    private boolean displayFlag;

    /**
     * Default constructor sets up array management conditions and default display flag setting
     */
    public GenericHeapClass()
    {
        heapArray = new Object[DEFAULT_ARRAY_CAPACITY];
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        arraySize = 0;
        displayFlag = false;
    }
    /**
     * Copy constructor copies array and array management conditions and
     * default display flag setting
     * <p>
     * @param copied - GenericHeapClass object to be copied 
     */
    public GenericHeapClass(GenericHeapClass<GenericData> copied)
    {
        this.arraySize = copied.arraySize;
        this.arrayCapacity = copied.arrayCapacity;
        this.heapArray = new Object[this.arrayCapacity];
        this.displayFlag = copied.displayFlag;
        int index = 0;
        // adds all data into duplicate generic heap Array 
        while(index < this.arraySize) 
        {
            this.heapArray[index] = new OpCodeClass((OpCodeClass) copied.heapArray[index]);
            index++;
        }
    }
    /**
     * Accepts GenericData item and adds it to heap
     * <p>
     * Note: uses bubbleUpArrayHeap to resolve unbalanced heap after
     * data addition
     * <p>
     * @param newItem - GenericData item to be added 
     */
    public void addItem(GenericData newItem)
    {
        checkForResize();
        heapArray[arraySize] = (Object) newItem;
        bubbleUpArrayHeap(arraySize);
        arraySize++;
    }
    /**
     * Recursive operation to reset data in the correct order for the min 
     * heap after new data addition
     * <p>
     * @param currentIndex - index of parent item being assessed, 
 and moved up as needed 
     */
    @SuppressWarnings( "unchecked" )
    void bubbleUpArrayHeap( int currentIndex )
   {
    int parentIndex = ( currentIndex - 1 ) / 2;
    GenericData child, parent;

    if( currentIndex > 0 )
       {
        child = (GenericData)heapArray[ currentIndex ];
        parent = (GenericData)heapArray[ parentIndex ];

        if( parent.compareTo( child ) > 0 )
           {
            //swapAtIndices( currentIndex, parentIndex );
            Object saveVal = heapArray[currentIndex];
            heapArray[currentIndex] = heapArray[parentIndex];
            heapArray[parentIndex] = saveVal; 
            bubbleUpArrayHeap( parentIndex );
           }
       }
   }
    /*
    private void bubbleUpArrayHeap(int currentIndex)
    {
        int parentIndex = (currentIndex - 1) / 2; 
        GenericData current, parent;
        current = (GenericData) heapArray[currentIndex];
        parent = (GenericData) heapArray[parentIndex];       
        // check if parent exists
        if( parent != null )
        {
            // if less than, swap and call back up
            if( parent.compareTo(current) > 0 )
            {
                Object saveVal = heapArray[currentIndex];
                heapArray[currentIndex] = heapArray[parentIndex];
                heapArray[parentIndex] = saveVal;               
                bubbleUpArrayHeap(parentIndex);
            }
        }
    }
    */
    /**
     * Automatic resize operation used prior to any new data addition 
     * in the heap
     * <p>
 Tests for full heap array, and resizes to twice the parent 
 capacity as required
     */
    private void checkForResize()
    {
        if( arrayCapacity == arraySize )
        {
            arrayCapacity = 2*arrayCapacity;
            // saves values from last setArray
            Object[] copyValues = this.heapArray;
            this.heapArray = new Object[this.arrayCapacity];    
            int index = 0;
            // adds all values into new array
            while( index < this.arraySize ) 
            {
                this.heapArray[index] = copyValues[index];
                index++;
            }
        }
    }
    /**
     * Tests for empty heap
     * <p>
     * @return boolean result of test
     */
    public boolean isEmpty()
    {
        return arraySize == 0;
    }
    /**
     * Removes GenericData item from top of min heap, thus being the operation
     * with the lowest priority value
     * <p>
     * Note: Uses trickleDownArrayHeap to resolve unbalanced heap after data 
     * removal
     * <p>
     * @return 
     */
    public GenericData removeItem()
    {
        // save item to return
        GenericData removedItem = (GenericData) heapArray[0];
        // swap with end and set end to null
        heapArray[0] = heapArray[arraySize - 1];
        heapArray[arraySize - 1] = null;
        arraySize--;
        trickleDownArrayHeap(0);
        return removedItem;
    }
    /**
     * Utility method to set the display flag for displaying internal 
     * operations of the heap bubble and trickle operations
     * <p>
     * @param setState - flag used to set the state to display, or not
     */
    public void setDisplayFlag(boolean setState)
    {
        displayFlag = setState;
    }
    /**
     * Dumps array to screen as is, no filtering or management
     */
    public void showArray()
    {
        int index = 0;
        while( index < arraySize )
        {
            System.out.print(heapArray[index].toString() + " - ");
            if(index == arraySize - 1){System.out.println("\n");}
            index++;
        }
    }
    /**
     * Recursive operation to reset data in the correct order for the min heap 
     * after data removal
     * <p>
     * @param currentIndex - index of parent item being assessed, 
     * and moved down as required 
     */
    @SuppressWarnings( "unchecked" )
    private void trickleDownArrayHeap(int currentIndex)
    {
    int leftChildIndex = 2 * currentIndex + 1;
    int rightChildIndex = 2 * currentIndex + 2;
    GenericData leftChildData, rightChildData;
    GenericData parentData = (GenericData)heapArray[ currentIndex ];

    if( rightChildIndex < arraySize )
       {
        leftChildData = (GenericData)heapArray[ leftChildIndex ];
        rightChildData = (GenericData)heapArray[ rightChildIndex ];

        if( rightChildData.compareTo( parentData ) < 0  
            && rightChildData.compareTo( leftChildData ) < 0 )
           {
            heapArray[ currentIndex ] = rightChildData;
            heapArray[ rightChildIndex ] = parentData;

            trickleDownArrayHeap( rightChildIndex );
           }
       }

    if( leftChildIndex < arraySize )
       {
        leftChildData = (GenericData)heapArray[ leftChildIndex ];

        if( leftChildData.compareTo( parentData ) < 0 )
           {
            heapArray[ currentIndex ] = leftChildData;
            heapArray[ leftChildIndex ] = parentData;

            trickleDownArrayHeap( leftChildIndex );
           }
       }
   }
}