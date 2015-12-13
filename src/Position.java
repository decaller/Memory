import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by HarridiIlman on 11/12/2015.
 * ini untuk pasang posisi
 */
public class Position implements List<Object> {

    private ArrayList<String> pos = new ArrayList<>();


    public Position() {
    }

    public void setCard(String content) { //set 2 card yg sama karena memory game duh...
        for (int i = 1; i <= 2; i++){
            pos.add(content);
        }

    }

    public ArrayList<String> getPos() {
        return pos;
    }

    public void randomize() {
        //randomize nanti...
        Collections.shuffle(pos);
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean contains(Object o) {
        return false;
    }

    public Iterator<Object> iterator() {
        return null;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public <T> T[] toArray(T[] a) {
        return null;
    }

    public boolean add(Object o) {
        return false;
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(int index, Collection<?> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {

    }

    public Object get(int index) {
        return null;
    }

    public Object set(int index, Object element) {
        return null;
    }

    public void add(int index, Object element) {

    }

    public Object remove(int index) {
        return null;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<Object> listIterator() {
        return null;
    }

    public ListIterator<Object> listIterator(int index) {
        return null;
    }

    public List<Object> subList(int fromIndex, int toIndex) {
        return null;
    }
}
