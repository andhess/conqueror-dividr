package lab1new;
/**
 * closestPairDC 
 * @author andhess
 * Andrew Hess
 * 413773
 * Oct-01-2012
 * CSE 241 - Lab 1
 */
public class ClosestPairDC {
    
    public final static double INF = java.lang.Double.POSITIVE_INFINITY;
        static double minDist = 0;
        static XYPoint [] closestPoints = new XYPoint[2];
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
     * Recursively breaks down the inputted arrays and quickly finds the 2 closest points and their distance    
     * @param pointsByX - points sorted in nondecreasing order by X coordinate
     * @param pointsByY - points sorted in nondecreasing order by Y coordinate
     */
    public static void findClosestPair( XYPoint pointsByX[], XYPoint pointsByY[] ){
        int nPoints = pointsByX.length;
        closestPoints = closestPair( pointsByX, pointsByY, nPoints );
        double minDist = closestPoints[0].dist( closestPoints[1] );
        String printout = ( closestPoints[0].x <= closestPoints[1].x ) ? closestPoints[0] + "  " + closestPoints[1] : closestPoints[1] + ", " + closestPoints[0];
        System.out.println( printout + "  " + minDist );
    }

    /**
     * closestPair divides an array of points into smaller arrays, then recursively calls itself to find the closest points in each divided array
     * works with combine to find the 2 closest points
     * @param pointsByX - an array of points, sorted by their x coordinate from least to greatest
     * @param pointsByY - the same array of points, sorted by their y coordinate from least to greatest
     * @param nPoints - the number of points
     * @return the two closest points
     */
    public static XYPoint[] closestPair( XYPoint pointsByX[], XYPoint pointsByY[], int nPoints ){
        //Check Base Cases
        if( nPoints == 1 ){
            closestPoints[0] = pointsByX[0];
            closestPoints[1] = new XYPoint( (int)INF, (int)INF );
        }
        if( nPoints == 2 ){
            closestPoints[0] = pointsByX[0];
            closestPoints[1] = pointsByX[1];
        }

        // Otherwise, Divide and Conquer
        else if( nPoints > 2){
            int mid = (int) Math.ceil( (nPoints)/2.0) -1;
            XYPoint XL [] = new XYPoint[mid + 1];
            XYPoint XR [] = new XYPoint[nPoints - mid -1];
            XYPoint YL [] = new XYPoint[mid + 1];
            XYPoint YR [] = new XYPoint[nPoints - mid -1];
            XYPoint minL [] = new XYPoint[2];
            XYPoint minR [] = new XYPoint[2];
            XYPoint minCombine [] = new XYPoint[2];
            int indexL = 0;
            int indexR = 0;
            
            // Fill XL, XR
            for( int i = 0; i <= ( mid ); i++ ){
                XL[i] = pointsByX[i];
            }
            for( int j = mid+1; j <= ( nPoints -1 ); j++ ){
                XR[j - mid-1] = pointsByX[j];
            }
            XYPoint lastXLeft = XL[XL.length -1];
            
            // Fill YL, YR
            for( int k = 0; k < ( nPoints ); k++ ){
                if( pointsByY[k].isLeftOf( pointsByX[mid] ) || pointsByY[k] == lastXLeft ){
                    YL[indexL] = pointsByY[k];
                    indexL++;
                }
                else{
                    YR[indexR] = pointsByY[k];
                    indexR++;
                }
            }
            
            // Now recursively call this method using the divided arrays
            minL = closestPair( XR, YR, XR.length );
            minR = closestPair( XL, YL, XL.length );
           
            // Get the distance of each half, we only want the smaller of the two though
            double disL = minL[0].dist( minL[1] );
            double disR = minR[0].dist( minR[1] );
            if( disL < disR ){
                minDist = disL;
                closestPoints = minL;
            }
            else{
                minDist = disR;
                closestPoints = minR;
            }
            
            // Call combine, send sortedY and the midpoint
            minCombine = combine( pointsByY, pointsByX[mid] );
            return minCombine;
        }
        return closestPoints;
    }
    /**
     * Called from closestPair, this method checks beyond the divided territories of the divide and conquer to find the absolute closest pair
     * @param pointsByY the- the points of the level of recursion being merged, sorted by y coordinates from least to greatest
     * @param midPointValue - the point that is the midpoint on which the division occured
     * @return the closest point of the 2 sections being combined
     */
    public static XYPoint[] combine( XYPoint pointsByY[], XYPoint midPointValue ){
        XYPoint [] closestPts = closestPoints;
        int yStripLength = 0;
        int ylength = pointsByY.length;
        
        //how big does yStrip need to be?
        for( int i = 0; i <= ylength - 1; i++ ){
            if( Math.abs( midPointValue.x - pointsByY[i].x ) <= minDist ){
                yStripLength++;
            }
        }
        XYPoint yStrip [] = new XYPoint[yStripLength];
 
        // fill up our yStrip
        int r = 0;
        while( yStripLength > 0){
            if( Math.abs( midPointValue.x - pointsByY[r].x ) <= minDist && yStripLength > 0 ){
                yStrip[yStrip.length - yStripLength] = pointsByY[r];
                yStripLength--;
            }
            r++;
        }
        
        // now, can we find any 2 closer points?
        for( int m = 0; m <= ( yStrip.length - 1); m++ ){
            int n = m + 1;
            while( ( n < yStrip.length ) && Math.abs( yStrip[m].y - yStrip[n].y ) <= minDist ){
                double d = yStrip[m].dist( yStrip[n] );
                if( d < minDist ){
                    closestPts[0] = yStrip[m];
                    closestPts[1] = yStrip[n];
                    minDist = d;
                }
                n++;
            }
        }
        return closestPts;
    }
}