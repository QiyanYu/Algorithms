/**
 * weightedQuickUnionPathCompression
 */
public class WeightedQuickUnionPathCompression {
    // parent of i
    private int[] parent;
    // the size of each connected components, only the root's value is valid
    private int[] size;
    // number of components
    private int count;

    public WeightedQuickUnionPathCompression(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    /**
     * Returns the root of p element
     */
    public int find(int p) {
        int root = p;
        while (parent[root] != root) {
            root = parent[root];
        }
        while (p != root) {
            int newP = parent[p];
            parent[p] = root;
            p = newP;
        }
        return root;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }

        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }
}