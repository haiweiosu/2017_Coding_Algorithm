/*Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]

By calling next repeatedly until hasNext returns false, 
the order of elements returned by next should be: [1,2,3,4,5,6].*/

public class Vector2D implements Iterator<Integer> {
    
    private int indexList;
    private int indexElement;
    List<List<Integer>> vec;
    
    public Vector2D(List<List<Integer>> vec2d) {
        indexList = 0;
        indexElement = 0;
        vec = vec2d;
    }

    @Override
    public Integer next() {
        return vec.get(indexList).get(indexElement++);
    }

    @Override
    public boolean hasNext() {
        while (indexList < vec.size()) {
            if (indexElement < vec.get(indexList).size()){
                return true;
            }
            indexList++;
            indexElement = 0;
        }
        
        return false;
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */