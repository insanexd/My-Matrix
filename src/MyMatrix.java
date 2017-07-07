import java.util.*;
import java.awt.Point;
import java.util.function.Supplier;

public class MyMatrix<T> implements Matrix<T>{

    //Sub-Class
    private class DepthFirstIterator implements Iterator<T>{
        private int cursor;
        public DepthFirstIterator(){}
        @Override
        public boolean hasNext() {
            //return (currentX < super.getColumnCount() && currentY < this.getRowCount());
            return false;
        }
        @Override
        public T next() throws NoSuchElementException {

            if(this.hasNext()) {
                int current= cursor;
                cursor++;
                return (T)matrixEntries.values().toArray()[current];
            }
            throw new NoSuchElementException();
        }

    }

    //Upper-Class
    private Map<Point, T> matrixEntries = new HashMap();
    private int currentX;
    private int currentY;


    @Override
    public int getRowCount() {
        int numberOfRows = 0;
        for(Map.Entry<Point, T> entry : matrixEntries.entrySet()) {
            if(entry.getKey().getX() >= numberOfRows) {
                numberOfRows =(int) entry.getKey().getX() + 1;
            }
        }
        return numberOfRows;
    }
    @Override
    public int getColumnCount() {
        int numberOfColumns = 0;
        for(Map.Entry<Point, T> entry : matrixEntries.entrySet()) {
            if(entry.getKey().getY() >= numberOfColumns) {
                numberOfColumns =(int) entry.getKey().getY() + 1;
            }
        }
        return numberOfColumns;
    }
    @Override
    public int getObjectCount() {
        int countedObjects = 0;
        for(T t : this.matrixEntries.values()) {
            if(t != null) {
                countedObjects++;
            }
        }
        return countedObjects;
    }
    @Override
    public int getDistinctObjectCount() {
        Set<T> mySet = new HashSet<>();
        for(Map.Entry<Point, T> entry : this.matrixEntries.entrySet()) {
            if(!mySet.contains(entry.getValue())) {
                mySet.add(entry.getValue());
            }
        }
        return mySet.size();
    }
    @Override
    public T get(int row, int column) throws IllegalArgumentException {
        if(row >= this.getRowCount() || column >= this.getColumnCount()) throw new IllegalArgumentException();
        return matrixEntries.get(new Point(row, column));
    }

    @Override
    public T put(int row, int column, T value) {
        T oldEntry = this.matrixEntries.get(new Point(row, column));
        this.matrixEntries.put(new Point(row, column), value);
        return oldEntry;
    }
    @Override
    public boolean contains(T value) {
        for(Map.Entry<Point, T> entry : matrixEntries.entrySet()) {
            if(entry.getValue() == value) {
                return true;
            }
        }
        return false;
    }

    //Iterator
    public Iterator<T> iterator() {
        return this.matrixEntries.values().iterator();
    }

}
