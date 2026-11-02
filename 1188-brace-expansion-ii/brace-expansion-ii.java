class Solution {
    public List<String> braceExpansionII(String expression) {
        return getExpressions(expression, 0, expression.length());
    }
    private List<String> getExpressions(String str, int start, int end){
        // System.out.println(start + "," + end);
        // Empty string (not empty set to allow concatenation)
        if(start >= end){
            return new ArrayList<>();
        }

        int i = start;
        List<String> leftArr = new ArrayList<>(1);
        if(str.charAt(i) == '{'){
            // If set
            int leftEdge = start+1;
            int bracketDepth = 0;
            i++;
            while(bracketDepth >= 0){
                if(str.charAt(i) == '{'){
                    // Track bracket
                    bracketDepth++;
                }else if(str.charAt(i) == '}'){
                    // Track exit bracket
                    bracketDepth--;
                }else if(bracketDepth == 0 && str.charAt(i) == ','){
                    // End of a set at this level. Merge it into the existing left sets
                    List<String> newArr = getExpressions(str, leftEdge, i);
                    leftArr = uniqueMergeSort(leftArr, newArr);
                    leftEdge = i+1;
                }
                i++;
            }
            // Get last set. i-1 due to it passing the last bracket
            List<String> newArr = getExpressions(str, leftEdge, i-1);
            leftArr = uniqueMergeSort(leftArr, newArr);
        }else{
            // If raw string, add the singlet as the left set
            while(i < end && str.charAt(i) != '{'){
                i++;
            }
            leftArr.add(str.substring(start, i));
            // Hit end of segment, so no rightArr to consider
            if(i == end){
                // System.out.println(leftArr.toString());
                return leftArr;
            }
        }
        List<String> rightArr = getExpressions(str, i, end);

        return crossMerge(leftArr, rightArr);
    }
    private List<String> uniqueMergeSort(List<String> leftArr, List<String> rightArr){
        int i = 0; int j = 0; int index = 0;
        List<String> res = new ArrayList<>(leftArr.size() + rightArr.size());
        while(i < leftArr.size() && j < rightArr.size()){
            int compareVal = leftArr.get(i).compareTo(rightArr.get(j));
            if(compareVal < 0){
                res.add(leftArr.get(i));
                i++;
            }else if(compareVal > 0){
                res.add(rightArr.get(j));
                j++;
            }else{
                // Equal. Skip both
                res.add(leftArr.get(i));
                i++; j++;
            }
        }
        while(i < leftArr.size()){
            res.add(leftArr.get(i));
            i++;
        }
        while(j < rightArr.size()){
            res.add(rightArr.get(j));
            j++;
        }
        // System.out.println(leftArr.toString() + "+" + rightArr.toString() + " -> " +res.toString());
        return res;
    }
    private List<String> crossMerge(List<String> leftArr, List<String> rightArr){
        // Null case handling
        if(leftArr.size() == 0){
            return rightArr;
        }
        if(rightArr.size() == 0){
            return leftArr;
        }
        List<String> res = new ArrayList<>(leftArr.size() * rightArr.size());
        for(String leftStr : leftArr){
            List<String> newSet = new ArrayList<>(rightArr.size());
            for(String rightStr : rightArr){
                newSet.add(leftStr + rightStr);
            }
            res = uniqueMergeSort(res, newSet);
        }
        // System.out.println(leftArr.toString() + "x"+ rightArr.toString() + " -> "+ res.toString());
        return res;
    }
}