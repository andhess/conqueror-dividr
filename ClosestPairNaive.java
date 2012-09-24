public class ClosestPairNaive {
    
    public final static double INF = java.lang.Double.POSITIVE_INFINITY;
    
    //
    // findClosestPair()
    //
    // Given a collection of nPoints points, find and ***print***
    //  * the closest pair of points
    //  * the distance between them
    // in the form "(x1, y1) (x2, y2) distance"
    //
    
    // INPUTS:
    //  - points sorted in nondecreasing order by X coordinate
    //  - points sorted in nondecreasing order by Y coordinate
    //
    
    public static void findClosestPair( XYPoint points[] ){
	int nPoints = points.length;
	double minDist = INF;
	XYPoint point1;
	XYPoint point2;
	
	for( int i = 0; i <= nPoints - 1; i++ ){
		for( int j = 1; j <= nPoints; j++ ){
			double dist = points[i].dist( points[j] );
			if( minDist > dist ){
				minDist = dist;
				if( points[i].x < points[j].x ){
					point1 = points[i];
					point2 = points[j];
				}
				else{
					point1 = points[j];
					point2 = points[i];
				}
			}
		}
	}
	System.out.println( point1 + ", " + point2 + " are the closest pair at a distance of:  " + minDist );
    }
}
