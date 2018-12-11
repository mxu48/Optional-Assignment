/* --------------------------------
   GraphAdjMatrix.java
   Purpose: A Minimum Spanning Tree implementation 
   using Prim's Algorithm and graphs.
   For CS 245 - David Guy Brizan
   @jxu74 Mina Xu
   --------------------------------
*/
public class GraphAdjMatrix implements Graph {
	int vertices = 5;
	private int [][] adjMatrix;

/* --------------------------------
   GraphAdjMatrix (int vertices): Constructs a graph
   of vertices x vertices size using an adjacency matrix.
   - variables -
   int vertices: number of vertices in the graph
   --------------------------------
*/
	public GraphAdjMatrix (int vertices) {
   		vertices = 5;      
        adjMatrix = new int[vertices][vertices];
       
	}

/* --------------------------------
   initGraph(): initializes the graph to entirely infinity
   where infinity = -1
   - variables -
   no new vars.
   --------------------------------
*/

   public void initGraph() { 
      for (int r = 0; r < vertices; r++) {
         for (int c = 0; c < vertices; c++)
            adjMatrix[r][c] = -1;

      } 
   }

  /* --------------------------------
   addEdge(int v1, int v2, int weight): adds a weighted edge of weight
   weight between vertices 1 and vertices 2 in the graph.
   - variables -
   int v1: vertices 1
   int v2: vertices 2
   int weight: weight of the edge
   --------------------------------
 */

	public void addEdge(int v1, int v2, int weight) {
		adjMatrix[v1][v2] = weight;
		adjMatrix[v2][v1] = weight;
	}

/* --------------------------------
   int getEdge(int v2, int v2): returns the weight of the edge between
   v1 or v2, returning -1 if the edge does not exist.
   - variables -
   int w: weight of the edge to be returned
   --------------------------------
*/

	public int getEdge(int v1, int v2) {
		int w;
		if(v1 >= 0 && v2 >= 0) {
			w = adjMatrix[v1][v2];
		} else {
			w = -1;
		}

		return w;
	}

/* --------------------------------
   createSpanningTree(): creates a MST, removes unused edges, and returns MST weight.
   - variables - 
   int min: minimum value of MST
   int [] minVal: lowest cost for each vertices
   boolean [][] known: true/false if this edge is known
   int [][] graphMST: graph of the lowest cost edges between each vertices, 
   non-essential edges set to -1
   --------------------------------
*/

	public int createSpanningTree() {
		int min = Integer.MAX_VALUE;

		int [] minVal = new int [vertices];
		boolean [][] known = new boolean [vertices][vertices];
		int [][] graphMST = new int[vertices][vertices]; 

		/* Initialize lowest costs to min */
		for(int q = 0; q < vertices; q++) {
			minVal[q] = min;
		}

		/* Initialize all edges known to false */
		for(int a = 0; a < vertices; a++) {
			for(int b = 0; b < vertices; b++) {
				known[a][b] = false;
			}
		}

		/* Initialize all MST graph edges to -1 */
		for(int c = 0; c < vertices; c++) {
			for(int d = 0; d < vertices; d++) {
				graphMST[c][d] = -1;
			}
		}

		/* Go through source graph */
		for(int i = 0; i < vertices; i++) {
			for(int j = 0; j < vertices; j++) {
				/* If unexplored, proceed */
				if(known[i][j] == false && known[j][i] == false) {
					/* If edge between vertices i and j is less than min */
					if(adjMatrix[i][j] < minVal[i] && adjMatrix[i][j] != 0 ) {
						/* Update min, add to array of lowest cost */
						min = adjMatrix[i][j];
						minVal[i] = adjMatrix[i][j];
						/* Update edges in MST graph with lowest cost */
						graphMST[i][j] = min;
						graphMST[j][i] = min;
					}
				}
				/* Update to distance/cost = known */
				known[i][j] = true;
				known[j][i] = true;
			}
		}

		int sum = 0;
		/* Minimum cost to span the tree, acounting for -1 root */
		for(int x = 0; x < vertices-1; x++) {
			sum = sum + minVal[x];
		}
		sum -= 1;
		return sum;
	}
	
} /* Close GraphAdjMatrix */

