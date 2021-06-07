package interfaces;

import interfaces.INode;

import java.util.ArrayList;

public interface GarbageCollector {
    ArrayList<INode> garbageCollect();
}
