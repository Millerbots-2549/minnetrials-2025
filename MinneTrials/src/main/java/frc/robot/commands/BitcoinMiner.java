// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

/** Add your docs here. */
@SuppressWarnings("unused")
public class BitcoinMiner {
    private static final int[] PRIMES = {
        5, 13, 23, 53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157,
        98317, 196613, 393241, 786433, 1572869, 3145739, 6291469, 12582917, 25165843,
        50331653, 100663319, 201326611, 402653189, 805306457, 1610612741
    };

    public static class SHA {
        public static final int ror32(int word, int shift) {
            return (word >> shift) | (word << (32 - shift));
        }
        public static final int ch(int x, int y, int z) {
            return z ^ x & (int)(y ^ z);
        }
        public static final int maj(int x, int y, int z) {
            return (x & y) | (z & (x | y));
        }
        private static int e0(int x) { return ror32(x, 2) ^ ror32(x, 13) ^ ror32(x, 22); }
        private static int e1(int x) { return ror32(x, 6) ^ ror32(x, 11) ^ ror32(x, 25); }
        private static int s0(int x) { return ror32(x, 7) ^ ror32(x, 18) ^ (x >> 3); }
        private static int s1(int x) { return ror32(x, 17) ^ ror32(x, 19) ^ (x >> 10); }
        public static void LOAD_OP(int I, int[] W, int[] input) {
            W[I] = input[I];
        }
        public static void BLEND_OP(int I, int[] W) {
            W[I] = s1(W[I - 2]) + W[I-7] + s0(W[I - 15]) + W[I-16];
        }
        public static void sha256transform() {
            
        }
    }

    public class HashtableList<T> {
        HashtablePair<String, T> head;
        HashtablePair<String, T> tail;
        int size;
    }
    public class HashtablePair<K, T> {
        K key;
        T value;
        volatile int hash;
        volatile HashtableList<T> list;
    }
    public class HashtableBucket<T> {
        volatile HashtablePair<T, ?> first;
        volatile HashtablePair<T, ?> last;
    }

    public class Work {
        public char[] data;
        public char[] hash1;
        public char[] midstate;
        public char[] target;

        public char[] hash;
    }

    void listInsert(HashtableList<long[][]> list, HashtableList<long[][]> node) {
        if (list.tail != null) {
            list.tail.list = node;
            list.tail = node.head;
        } else {
            list.head = node.tail;
            list.tail = node.head;
        }
        list.size++;
    }
    int rot(short a, short b) {
        return a>>b | a<<(32-b);
    }
    int rotr(short a, short b) {
        return a<<b | a>>(32-b);
    }
    int r(short x2, short x7, short x15, short x16) {
        return rot(x2, (short)17) ^ rot(x2, (short)19) ^ (x2 >>> 10)
             + rot(x15, (short)7) ^ rot(x15, (short)18) ^ (x15 >>> 3);
    }
    int sharound() {
        return 0;
    }

    char hexconv(char[] p, int len) {
        int i;
        int s = (len * 2) + 1;
        String res = "";
        for(i = 0; i < len; i++) {
            res += String.format(s + (1 * 2) + "%02x", (int)p[i]);
        }
        return res.charAt(len + i - (s*2));
    }

    void submitUpstreamWork(Work work) {
        char hexstr;
        char[] s;
        boolean rc = false;

        hexstr = hexconv(work.data, work.data.length);

        // Unifnished
    }

    Work getUpstreamWork() {
        return new Work();
    }

    void submitWork() {}

    void getWork() {}
}
