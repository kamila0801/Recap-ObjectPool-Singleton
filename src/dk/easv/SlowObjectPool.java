package dk.easv;

import java.util.Deque;
import java.util.List;

public class SlowObjectPool {

    private static SlowObjectPool instance;

    private List<SlowObject> objects;
    private Deque<SlowObject> released;

    private SlowObjectPool() { }

    public SlowObjectPool getInstance() {
        if(instance==null) instance = new SlowObjectPool();
        return instance;
    }

    public void create() {
        if(released.isEmpty()) {
            SlowObject newObject = new SlowObject();
            objects.add(newObject);
        } else {
            SlowObject object = released.poll();
            objects.add(object);
        }
    }

    public void release(SlowObject object) {
        objects.remove(object);
        released.add(object);
    }
}
