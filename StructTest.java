package RedBlackTree;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class StructTest {
    private static final Long SEED = 123123L;
    private static final Random RAND = new Random(SEED);

    private static int rand(){
        return RAND.nextInt();
    }

    private static RedBlackTree testing;

    private static long start;

    private static final long I = 500;//сколько раз тестировать

    private void fill(int k) throws InterruptedException {
        for(int i = 0; i < k; i++){
            testing.add(rand());
        }
    }

    private void prepare(String method, int k) throws InterruptedException {
        System.out.print("testing " + method + " " + k + " : ");
        fill(k);
        start = System.nanoTime();
    }

    @BeforeAll
    static void prepare() {
        testing = new RedBlackTree();
    }

    @BeforeEach
    void beforeEach(){
        testing = new RedBlackTree();
        testing.clean();
        //start = System.nanoTime();
    }

    @Test
    void add10000() throws InterruptedException {
        int j = 10000;
        prepare("add", j);
        start = System.nanoTime();
        for(int i = 0; i < I; i++){
            testing.add(rand());
        }
        long t = System.nanoTime() - start;
        System.out.println(t);
        assertEquals(true, true);
    }

    @Test
    void add20000() throws InterruptedException {
        int j = 20000;
        prepare("add", j);
        start = System.nanoTime();
        for(int i = 0; i < I; i++){
            testing.add(rand());
        }
        long t = System.nanoTime() - start;
        System.out.println(t);
        assertEquals(true, true);
    }

    @Test
    void add30000() throws InterruptedException {
        int j = 30000;
        prepare("add", j);
        start = System.nanoTime();
        for(int i = 0; i < I; i++){
            testing.add(rand());
        }
        long t = System.nanoTime() - start;
        System.out.println(t);
        assertEquals(true, true);
    }

    @Test
    void add40000() throws InterruptedException {
        int j = 40000;
        prepare("add", j);
        start = System.nanoTime();
        for(int i = 0; i < I; i++){
            testing.add(rand());
        }
        long t = System.nanoTime() - start;
        System.out.println(t);
        assertEquals(true, true);
    }

    @Test
    void contains10000() throws InterruptedException {
        int j = 10000;
        prepare("contains", j);
        start = System.nanoTime();
        for(int i = 0; i < I; i++){
            testing.contains(rand());
        }
        long t = System.nanoTime() - start;
        System.out.println(t);
        assertEquals(true, true);
    }

    @Test
    void contains20000() throws InterruptedException {
        int j = 20000;
        prepare("contains", j);
        start = System.nanoTime();
        for(int i = 0; i < I; i++){
            testing.contains(rand());
        }
        long t = System.nanoTime() - start;
        System.out.println(t);
        assertEquals(true, true);
    }

    @Test
    void contains30000() throws InterruptedException {
        int j = 30000;
        prepare("contains", j);
        start = System.nanoTime();
        for(int i = 0; i < I; i++){
            testing.contains(rand());
        }
        long t = System.nanoTime() - start;
        System.out.println(t);
        assertEquals(true, true);
    }

    @Test
    void contains40000() throws InterruptedException {
        int j = 40000;
        prepare("contains", j);
        start = System.nanoTime();
        for(int i = 0; i < I; i++){
            testing.contains(rand());
        }
        long t = System.nanoTime() - start;
        System.out.println(t);
        assertEquals(true, true);
    }

    @Test
    void remove10000() throws InterruptedException {
        int j = 10000;
        prepare("remove", j);
        start = System.nanoTime();
        for(int i = 0; i < I; i++){
            testing.remove(rand());
        }
        long t = System.nanoTime() - start;
        System.out.println(t);
        assertEquals(true, true);
    }

    @Test
    void remove20000() throws InterruptedException {
        int j = 20000;
        prepare("remove", j);
        start = System.nanoTime();
        for(int i = 0; i < I; i++){
            testing.remove(rand());
        }
        long t = System.nanoTime() - start;
        System.out.println(t);
        assertEquals(true, true);
    }

    @Test
    void remove30000() throws InterruptedException {
        int j = 30000;
        prepare("remove", j);
        start = System.nanoTime();
        for(int i = 0; i < I; i++){
            testing.remove(rand());
        }
        long t = System.nanoTime() - start;
        System.out.println(t);
        assertEquals(true, true);
    }

    @Test
    void remove40000() throws InterruptedException {
        int j = 40000;
        prepare("remove", j);
        start = System.nanoTime();
        for(int i = 0; i < I; i++){
            testing.remove(rand());
        }
        long t = System.nanoTime() - start;
        System.out.println(t);
        assertEquals(true, true);
    }

    /*@AfterEach
    void time(){
        //long t = System.nanoTime() - start;
        //System.out.println(t);
    }*/

}