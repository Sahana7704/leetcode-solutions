// 106. Construct Binary Tree from Inorder and Postorder Traversal
// Original solution. Time O(n); auxiliary space O(height), excluding output.
#include <algorithm>
#include <array>
#include <climits>
#include <cmath>
#include <functional>
#include <map>
#include <numeric>
#include <queue>
#include <set>
#include <stack>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>
using namespace std;

class Solution {
public:
    TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
        if(inorder.empty())return nullptr;auto root=new TreeNode(postorder[inorder.size()-1]);vector<TreeNode*> stack{root};int j=(int)inorder.size()-1;for(int k=(int)inorder.size()-2;k>=0;--k){auto node=stack.back();auto child=new TreeNode(postorder[k]);if(node->val!=inorder[j])node->right=child;else{while(!stack.empty()&&stack.back()->val==inorder[j]){node=stack.back();stack.pop_back();j+=-1;}node->left=child;}stack.push_back(child);}return root;
    }
};