package writerReaderProblem;

/**
 * This class making a readers and writers,
 * and define that what they do when they enter and exit
 * the operations which they allowed to do
 */
public class WriterPreference {
    private boolean activeWriter;
    private int waitWriter;
    private int actReader;
    private int sharedInt;

    /**
     * Check if reader want to join,
     * and if yes then increment number of them
     */
    public synchronized void acquireRead(){
        // we have to keep track of number of waiting writers
        while(activeWriter || waitWriter > 0){
            try{
                wait(); //we are using this method to communicate between threads. This method puting thread into waiting state
            }catch(InterruptedException ignored){}
        }
        actReader++;
    }
    /**
     * If reader doesn't read then decrement number of them
     */
    public synchronized void releaseRead(){
        actReader--;
        if(actReader == 0){
            notifyAll(); //this method wakes up all threads that are waiting
            // now there is a room for writers
        }
    }
    /**
     * check if writer want to write something (in this scenario we increment 'sharedInt'),
     * and if yes then increment number of them and change to true
     */
    public synchronized void acquireWrite(){
        // acquire writer when thread want an access
        waitWriter++;
        while(activeWriter || actReader > 0){
            try{
                wait(); //we are using this method to communicate between threads. This method puting thread into waiting state
            }catch(InterruptedException ignored) {}
        }
        waitWriter--;
        activeWriter = true;
    }
    /**
     * if writer doesn't modify anything
     * then change value of his status to false
     */
    public synchronized void releaseWrite(){
        activeWriter = false;
        notifyAll(); //this method wakes up all threads that are waiting
        // now there is a room for readers
    }
    /**
     * this method is used by readers to read value sharedInt,
     * which is incremented by writers
     *
     * @return int
     */
    public int readInt(){
        return sharedInt;
    }
    /**
     * this method is used to the test and it return a value of that if a writer is active or not
     *
     * @return boolean
     */
    public boolean readActiveWriter(){
        return activeWriter;
    }
    /**
     * this method is used for checking a number of active readers ath the moment
     *
     * @return int
     */
    public int readActiveReaders(){
        return actReader;
    }
    /**
     * this method is used by writers to increment value of 'sharedInt'
     */
    public void incrementInt(){
        sharedInt++;
    }
}