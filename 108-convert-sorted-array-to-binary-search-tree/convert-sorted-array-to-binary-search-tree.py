# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sortedArrayToBST(self, nums: list[int]) -> TreeNode | None:
        
        
        def divide(ns):

            if len(ns) == 1:
                return TreeNode(ns[0])
            elif len(ns) == 0:
                return None
            
            m = len(ns) // 2

            ns_l = ns[:m]
            ns_r = ns[m+1:]

            tree = TreeNode(ns[m])
            tree.left = divide(ns_l)
            tree.right = divide(ns_r)

            return tree
        
        return divide(nums)
            
