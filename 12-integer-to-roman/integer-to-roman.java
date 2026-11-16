class Solution {        
        final static byte[] pp = new byte[] {
                  'I', 'I', 'I',
                  'I', 'V',
                  'V', 'I', 'I' ,'I',
                  'I', 'X',
                  'X', 'X', 'X',
                  'X', 'L',
                  'L', 'X', 'X', 'X',
                  'X', 'C',
                  'C', 'C', 'C',
                  'C', 'D',
                  'D', 'C', 'C', 'C',
                  'C', 'M',
                  'M', 'M', 'M'
        };

        final static byte[] r = new byte[16];

        public String intToRoman(int num) {

            var a = num % 10;
            var p = 0;            
            var x = 16;
            int i;

            while (num != 0) {                               
                if (a == 4) {
                    x -= 2;
                    System.arraycopy(pp, p + 3, r, x, 2);
                } else {
                    if (a == 9) {
                        x -= 2;
                        System.arraycopy(pp, p + 9, r, x, 2);
                    } else {
                        if (a < 4) {                        
                            x -= a;
                            System.arraycopy(pp, p , r, x, a);
                        } else {
                            var u = a - 4;
                            x -= u;
                            System.arraycopy(pp, p + 5, r, x, u);  
                        }
                    }
                }
            
                num = num / 10;
                a = num % 10;
                p += 11;
            }
            return new String(r, 0, x, 16 - x);
        }
    }