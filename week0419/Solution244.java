
    /**
    Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters. 

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
     */
    class WordDistance {
        private String[] words;
        private HashMap<String, List<Integer>> indexs = new HashMap<>();
        private HashMap<String, Integer> map = new HashMap<>();

        public WordDistance(String[] words) {
            int len = words.length;
            for (int i = 0; i < len; i++) {
                String word = words[i];
                List<Integer> list = indexs.getOrDefault(word, null);
                if (list == null)
                    list = new ArrayList<>();
                list.add(i);
                indexs.put(word, list);
            }
        }
        

        public int shortest(String word1, String word2) {
            int min = map.getOrDefault(word1 + " " + word2, -1);
            if (min != -1)
                return min;
            
            min = Integer.MAX_VALUE;
            List<Integer> index1 = indexs.get(word1);
            List<Integer> index2 = indexs.get(word2);

            for (int i = 0; i < index1.size(); i++) {
                for (int j = 0; j < index2.size(); j++) {
                    if (Math.abs(index1.get(i) - index2.get(j)) < min)
                        min = Math.abs(index1.get(i) - index2.get(j));
                }
            }
            map.put(word1 + " " + word2, min);
            return min;
        }
    }