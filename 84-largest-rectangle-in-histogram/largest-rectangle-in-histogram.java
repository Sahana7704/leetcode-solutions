class Solution 
{
    public int largestRectangleArea(int[] heights) 
    {
        // monotonic stack problem level hard 
        int n = heights.length ; 
        int area = 0 ; 


        // stack holds the indices of the consecutive elements in increasing order 
        int stack [] = new int [n+1] ; 
        int top = -1 ; 
        for(int i = 0  ; i<n+1 ; i++)
        {
            // we sepcifically need to deal with a boundary condition in case all the elements are in indreasing order in such a case we need to force the previously present element in the stack to be popped .. 
            int cur_element ; 
            if (i == n)
            {
                cur_element = 0 ; 
            } 
            else 
            {
                cur_element = heights[i]; 
            }

            // check the stack for elements that are of value greater then the current value of eleemnt and compute area while popping elements ; 
            while (top >= 0 && heights[stack[top]] > cur_element)
            {
                // demo variable for the calcualtion fo the area 
                int temp = heights[stack[top]];
                --top;  // Pop first
                int count = 0;
                if (top == -1)
                {
                    count = i; 
                }
                else 
                {
                    count = i - stack[top] - 1 ; 
                }
                temp = temp * count;

                // updating the area of the array if it is greater then the current maximum areaa 
                if (temp > area)
                {
                    area = temp ; 
                }
            }

            stack[++top] = i ; 

        }

        return area ; 
    }
}