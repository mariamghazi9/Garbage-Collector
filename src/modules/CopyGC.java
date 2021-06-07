package modules;

import interfaces.GarbageCollector;
import interfaces.INode;

import java.util.ArrayList;

public class CopyGC implements GarbageCollector {

    private ArrayList<INode> roots ;

    public CopyGC(ArrayList<INode> roots) {
        this.roots = roots;
    }


    private ArrayList<INode> copied = new ArrayList<>();


    @Override
    public ArrayList<INode> garbageCollect() {
        copy();
        collect();
        return copied;
    }


    public void copy() {
        copied.addAll(roots);
        int i = 0;
        while (i < copied.size()) {
            for (int j = 0; j < copied.get(i).getChildren().size(); j++) {
                INode n = copied.get(i).getChildren().get(j);
                if (!copied.contains(n))
                    copied.add(n);
            }
            i++;
        }
    }

    public void collect() {
        copied.get(0).setHeapStartIndex(0);
        copied.get(0).setHeapEndIndex(copied.get(0).getSize());
        for (int i = 1; i < copied.size(); i++) {
            copied.get(i).setHeapStartIndex(1 + copied.get(i - 1).getHeapEndIndex());
            copied.get(i).setHeapEndIndex(copied.get(i).getHeapStartIndex() + (copied.get(i).getSize()));
        }
    }
}
