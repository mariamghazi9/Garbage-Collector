package executable;

import interfaces.GarbageCollector;
import interfaces.INode;
import modules.CopyGC;
import utils.FileManager;

import java.io.IOException;
import java.util.ArrayList;

public class MainCopyGC {
    public static void main(String Args[]) throws IOException {
        FileManager fileManager = FileManager.getInstance();
        fileManager.getHeapObjects(Args[0]);
        fileManager.readPointers(Args[1]);
        ArrayList<INode> roots = fileManager.readRoots(Args[2]);
        GarbageCollector garbageCollector = new CopyGC(roots);
        ArrayList<INode> newHeap = garbageCollector.garbageCollect();
        FileManager.writeToCsv(Args[3], newHeap);
    }
}
