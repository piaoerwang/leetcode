class FileSystem {
    // use a hashmap to store all entries
    Map<String, Integer> paths;
    public FileSystem() {
        paths = new HashMap<>();
        paths.put("", -1);
    }
    
    public boolean createPath(String path, int value) {
        int idx = path.lastIndexOf("/");
        String parent = path.substring(0, idx);
        if (!paths.containsKey(parent)) {
            // could not find the parent
            return false;
        } else {
            return (paths.putIfAbsent(path, value)) == null;
        }
    }
    
    public int get(String path) {
        return paths.getOrDefault(path, -1);  
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */
