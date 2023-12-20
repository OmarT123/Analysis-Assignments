


public class Assignment2Analysis {

    public static void sequenceAlignment(String x, String y, Double[][] scoringMatrix) {
        int n = x.length();
        int m = y.length();
        double[][] results = new double[n+1][m+1];

        for (int i=0; i<=n; i++) {
            for (int j= 0; j<=m; j++) {
                if (i ==0 || j ==0) {
                    results[i][j] = 0.0;
                } else {
                    double keep = results[i-1][j-1] + scoringMatrix[getIndex(x.charAt(i-1))][getIndex(y.charAt(j-1))];
                    double changeY = results[i-1][j] + scoringMatrix[getIndex(x.charAt(i-1))][getIndex('-')];
                    double changeX = results[i][j - 1] +scoringMatrix[getIndex('-')][getIndex(y.charAt(j-1))];      
                    results[i][j] = Math.max((Math.max(keep, changeY)), changeX);
                }
            }
        }
        
        StringBuilder newX = new StringBuilder();
        StringBuilder newY = new StringBuilder();
        int i = n, j = m;

        while (i > 0 || j > 0) {
            if (i > 0 && j > 0 && results[i][j] == results[i-1][j-1] +scoringMatrix[getIndex(x.charAt(i-1))][getIndex(y.charAt(j-1))]) {
            	newX.insert(0, x.charAt(i-1));
            	newY.insert(0, y.charAt(j-1));
                i--;
                j--;

            } else if (i > 0 && results[i][j] == results[i-1][j]+scoringMatrix[getIndex(x.charAt(i-1))][getIndex('-')]) {
            	newX.insert(0, x.charAt(i-1));
            	newY.insert(0, '-');
                i--;

            } else {
            	newX.insert(0, '-');
            	newY.insert(0, y.charAt(j-1));
                j--;
            }
        }
        System.out.println("Aligned x: "+ newX);
        System.out.println("Aligned y: "+ newY);
    }
    
    public static int getIndex(char a)
    {
    	switch(a)
    	{
    	case 'A':return 0;
    	case 'G':return 1;
    	case 'T':return 2;
    	case 'C':return 3;
    	default:return 4;
    	}
    }
    
    public static void main(String[] args) {
        String x = "TCCCAGTTATGTCAGGGGACACGAGCATGCAGAGAC";
        String y = "AATTGCCGCCGTCGTTTTCAGCAGTTATGTCAGATC";

        Double[][] scoringMatrix = {
                {1.0, -0.8, -0.2, -2.3, -0.6},
                {-0.8, 1.0, -1.1, -0.7, -1.5},
                {-0.2, -1.1, 1.0, -0.5, -0.9},
                {-2.3, -0.7, -0.5, 1.0, -1.0},
                {-0.6, -1.5, -0.9, -1.0, Double.NaN}
        };

        sequenceAlignment(x, y, scoringMatrix);
    }
}
