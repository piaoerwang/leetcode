class Solution {
    
    boolean cycle = false; //true if there exists cycle
    
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {        
        //make graph
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>(); //(node n1, neighbors n1 is pointing to)
                                                                    //hashset for quick access and don't re-record duplicates
        
        //we want to dfs from the start node (indegree = 0), and return true if we can find a path that hits every node
        //if there are multiple start nodes, means have multiple possible topological orderings, so immediately return false
        
        HashSet<Integer> indegree0 = new HashSet<>(); //as we're making graph, find start node
        //empty graph, so every node starts with indegree 0
        //initialize all nodes in graph
        for(List<Integer> seq: seqs) {
            for(int n: seq) {
                if(!graph.containsKey(n)) graph.put(n, new HashSet<>());
                indegree0.add(n);
            }
        }
            
        //add edges
        for(List<Integer> seq: seqs) {
            for(int i = 0; i < seq.size()-1; i++) {
                int n1 = seq.get(i);
                int n2 = seq.get(i+1);
                
                graph.get(n1).add(n2);
                
                //n2's indegree is > 0
                indegree0.remove(n2);
            }
        }
        
        if(indegree0.size()>1) return false; //multiple possible topological orderings
        
        //silly edge cases
        if(indegree0.size()==0) return false;
        
        //try to find a hamiltonian path (hits every node once)
        //start node is the node with indegree = 0
        int start = indegree0.iterator().next();
        
        //dfs with backtracking, want to explore every possible path
        
        //even if you found hamiltonian path, make sure no cycle
        boolean hamiltonian = dfs(start,new HashSet<>(), graph, new ArrayList<>(), org);
        
        return hamiltonian && !cycle;         
    }
    
    //return true if found a path which hits each node exactly once, and this path == org
    private boolean dfs(int curr, HashSet<Integer> visited, HashMap<Integer, HashSet<Integer>> graph, List<Integer> cand, int[] org) {
        
        //so that can properly backtrack
        boolean res = false;
        
        //detect cycle.
        //DON'T return early, if cycle exists, doesn't matter if you've found a hamiltonian path, need to return false
        //example: [1,2,3,4], [[1,2,4],[2,3,4],[3,2]]. This has a hamiltonian path, but b/c cycle exists, output needs to be false.
        if(visited.contains(curr)) {
            cycle = true;
            return res;
        }
        
        //process node
        visited.add(curr);
        cand.add(curr);
                
        //base case: reach a node with no outward edges, so done with current path.
        if(graph.get(curr).isEmpty()) {
            
            //check hit every node in graph
            res = visited.size() == graph.size();
            
            //now check if cand == org
            res &= cand.size() == org.length;
            for(int i = 0; i < cand.size() && res; i++) { //careful with index out of bounds, notice how I did && res.
                res &= cand.get(i) == org[i];
            }
            
            /***** ALSO need to verify NO cycles. This is only possible after trying all possible paths *****
            //no need for backtracking, founda a hamiltonian path
            if(res) {
                StringBuilder sb = new StringBuilder();
                for(int n: cand) sb.append(n);
                System.out.println(sb.toString());
                return true;
            }
            */
        } 
        else {//recurse on neighbors
            for(int n2: graph.get(curr)) {
               if(dfs(n2,visited,graph,cand,org)) return true;
            }
        }
        
        //backtrack
        visited.remove(curr);
        cand.remove(cand.size()-1);
        
        return res;
    }
}
