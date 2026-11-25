class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        int[] size = new int[n];
        int[] sibling = new int[n];
        int[] active = new int[26];

        // Node 0 is the root, so 0 can mark an empty child or sibling link.
        for (int node = 1; node < n; node++) {
            int p = parent[node];
            sibling[node] = size[p];
            size[p] = node;
        }

        int node = 0;
        boolean entering = true;

        while (true) {
            int letter = s.charAt(node) - 'a';

            if (entering) {
                int child = size[node];
                size[node] = active[letter];
                active[letter] = 1;

                if (child != 0) {
                    node = child;
                    continue;
                }
            }

            // Finish this node and restore the previous total for its letter.
            int saved = size[node];
            size[node] = active[letter];
            if (node == 0) return size;

            active[letter] = saved;
            int p = parent[node];
            int receiver = saved != 0 ? letter : s.charAt(p) - 'a';
            active[receiver] += size[node];

            int next = sibling[node];
            entering = next != 0;
            node = entering ? next : p;
        }
    }
}