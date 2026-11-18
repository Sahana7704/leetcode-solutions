import java.util.*;

class Solution {
    static long[] f0, f1; static long f2;

    static int next(int r) {                    // first free >= r, else smallest free, else -1
        int w = r >>> 6, v = w >>> 6;
        long m = f0[w] & (-1L << r);
        if (m != 0) return (w << 6) | Long.numberOfTrailingZeros(m);
        long m1 = f1[v] & (-2L << w);
        if (m1 == 0) {
            long m2 = f2 & (-2L << v);
            if (m2 == 0) m2 = f2;
            if (m2 == 0) return -1;
            v = Long.numberOfTrailingZeros(m2);
            m1 = f1[v];
        }
        int ww = (v << 6) | Long.numberOfTrailingZeros(m1);
        return (ww << 6) | Long.numberOfTrailingZeros(f0[ww]);
    }

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int n = arrival.length;
        List<Integer> res = new ArrayList<>();
        if (k >= n) { for (int i = 0; i < n; i++) res.add(i); return res; }
        int w0 = (k + 63) >>> 6, w1 = (w0 + 63) >>> 6;
        f0 = new long[w0]; f1 = new long[w1];
        Arrays.fill(f0, -1L); if ((k & 63) != 0) f0[w0 - 1] = (1L << k) - 1;
        Arrays.fill(f1, -1L); if ((w0 & 63) != 0) f1[w1 - 1] = (1L << w0) - 1;
        f2 = (1L << w1) - 1;
        long[] h = new long[k]; int sz = 0, best = 0, r = 0; int[] cnt = new int[k];
        for (int i = 0; i < n; i++) {
            int t = arrival[i]; long lim = (t + 1L) << 32;
            while (sz > 0 && h[0] < lim) {                       // free finished servers
                int s = (int) h[0]; long last = h[--sz]; int c = 0;
                while ((c << 1) + 1 < sz) {
                    int ch = (c << 1) + 1;
                    if (ch + 1 < sz && h[ch + 1] < h[ch]) ch++;
                    if (h[ch] >= last) break;
                    h[c] = h[ch]; c = ch;
                }
                h[c] = last;
                f0[s >>> 6] |= 1L << s; f1[s >>> 12] |= 1L << (s >>> 6); f2 |= 1L << (s >>> 12);
            }
            int x = next(r);
            if (x >= 0) {
                if ((f0[x >>> 6] &= ~(1L << x)) == 0 && (f1[x >>> 12] &= ~(1L << (x >>> 6))) == 0)
                    f2 &= ~(1L << (x >>> 12));
                best = Math.max(best, ++cnt[x]);
                long key = ((t + (long) load[i]) << 32) | x; int c = sz++;
                while (c > 0 && h[(c - 1) >>> 1] > key) { h[c] = h[(c - 1) >>> 1]; c = (c - 1) >>> 1; }
                h[c] = key;
            }
            if (++r == k) r = 0;
        }
        for (int x = 0; x < k; x++) if (cnt[x] == best) res.add(x);
        return res;
    }
}