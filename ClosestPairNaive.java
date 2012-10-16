package lab1new;
/**
 * closestPairDC 
 * @author andhess
 * Andrew Hess
 * 413773
 * Oct-01-2012
 * CSE 241 - Lab 1
 */
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
    /**
     * finds the closest pair of points and their distance out of an inputted array of points
     * @param points - the points that we are searching through
     */
    public static void findClosestPair( XYPoint points[] ){
        int nPoints = points.length;
    	double minDist = INF;
    	XYPoint [] closestPoints = new XYPoint[2];
    	for( int i=0; i <= (nPoints - 1); i++ ){
	        int j = i+1;
	        while( j <= nPoints - 1){
    	        double dist = points[i].dist( points[j] );
    	        if( dist < minDist ){
    	            minDist = dist;
    	            if( points[i].isLeftOf( points[j]) ){
    	                closestPoints[0] = points[i];
    	                closestPoints[1] = points[j];
    	            }
    	            else{
    	                closestPoints[0] = points[j];
                        closestPoints[1] = points[i];
    	            }
    	        }
    	        j++;
    	    }
    	}
    	System.out.println(closestPoints[0] + "  " + closestPoints[1] + "  " + minDist );
    }
}
