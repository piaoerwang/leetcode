class Solution {
    
    class Node{
        String val;
        Node parent;
        Node(String val){
            this.val=val;
        }
    }
    
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, Node> data = new HashMap<>();
        for(List<String> region:regions){
            String p = region.get(0);
            Node pNode = data.getOrDefault(p, new Node(p));
            data.putIfAbsent(p, pNode);
            for(int i=1; i<region.size(); i++){
                String n = region.get(i);
                Node node = data.getOrDefault(n, new Node(n));
                data.putIfAbsent(n, node);
                node.parent=pNode;
            }
        }
        Set<String> set = new HashSet<>();
        Node cur = data.get(region1);
        while(cur!=null){
            set.add(cur.val);
            cur=cur.parent;
        }
        cur = data.get(region2);
        while(cur!=null){
            if(set.contains(cur.val)) return cur.val;
            cur=cur.parent;
        }
        return null;
    }
}
