/*****************
 * estrutura de dados que permite o "merge" de dois sites
 * rapidamente.
 * implementado com heuristicas "path compression" e "merge by size".
 * complexidade: O(alpha(n)) (onde alpha eh inverse ackermann function)
 * ver: https://en.wikipedia.org/wiki/Disjoint-set_data_structure#Proof_of_O(log*(n))_time_complexity_of_Union-Find
 *
 *
 * referencia: https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf
 * */
// 0-based
public class UnionFind {
  private int[] parent;
  private int[] size;

  public UnionFind(int n) {
    parent = new int[n];
    for (int i = 0; i < n; i++) parent[i] = i;
    size = new int[n];
    for (int i = 0; i < n; i++) size[i] = 1;
  }

  // path compression
  public int find(int a) {
    if (a == parent[a]) {
      return a;
    } else {
      return a = find(parent[a]);
    }
  }

  public boolean same(int a, int b) {
    return (find(a) == find(b));
  }

  // merge by size
  public void merge(int a, int b) {
    if (same(a, b)) return;
    a = find(a); b = find(b);
    if (size[a] >= size[b]) {
      parent[b] = a;
      size[a] += size[b];
    } else {
      parent[a] = b;
      size[b] += size[a];
    }
  }
}
