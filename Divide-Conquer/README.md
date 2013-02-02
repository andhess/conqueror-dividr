CSE241_Divide-Conquer
=====================

Lab 1: This lab properly implements a Divide and Conquer algorithm to quickly find the 2 closest points from a given array of points.

Below is the writeup that I turned in with the lab.

Divide and Conquer Algorithm Solution


About:
This lab will take in a set of points and return the closest of the 2 plus the distance between them by using 2 different methods.
The first is the naive closest pair, the second is a divide and conquer closest pair.  I ran some tests on it and the divide and conquer is much, much faster.
The code and logic was discussed in class, so my notes had most of it written out in pseudocode.
It has been awhile since I've written code in Java (I've been writing in php and JS lately), so the transition back to a strict language was probably the most difficult step.
All in all, it was a good lab. I spent about 30 minutes each day working on it this past week and thought about the logic for it in the meantime.
That part actually turned out to be pretty fun because I'd think of the solution for the next step at random times and know exactly what to do once I sat back down.

Collaborators:
Most of the lab was straightforward, but I did ask Jeff Stephens two questions on the combine method. I was running into a few ugly OutOfBounds issues at first, but did get it on my own.

Edge Cases:

I thought were worth specifically testing, details below

Case 1: 2 identical coordinates.
    I felt that this could be a good edge case because maybe a poorly written implementation could count this as one point and find the distance between the next closest points.
    As expected, this returned the 2 matching coordinates with a distance of 0.

Case 2: Coordinates all having the same x-coordinate ( in my test I used 3)
    I thought this could be a tricky case as all the comparisons would be purely dependent on whatever the values were for the y term of each point.
    Both algorithms handled this one without any issue.
    
Case 3: I wanted to test the case where there were other points lying on the midpoint. I designed the test points so that 6 was the x-value of a midpoint, but also so that another
    point fell on this dividing line that was drawn.  It wouldn't really affect the naive algorithm, but I was interested to see how the divide and conquer would do.
    No issues were observed.
    
 
PART TWO: testing
all console logging was turned off for this portion because that eats up a lot of time!

********************************
closestPairDC:  These are the results from running 100 trials, each having different sets of points.  All times are in miliseconds (ms)

number of points:   1000    2000    4000    8000    12000    16000   24000   32000
average run time:      5       4       4       6        7       11      15      22       
maximum run time:     16      38      70      98      148      199     173     235
minimum run time:      3       0       1       3        5        7      11      15    


********************************
closestPairNaive:  These are the results from running 100 trials, each having different sets of points.  All times are in miliseconds (ms)

number of points:   1000    2000    4000    8000    12000    16000   24000   32000
average run time:      6      21      84     335      745     1326    2984    5319  
maximum run time:     90      55     107     382      790     1428    3140    5644  
minimum run time:      5      20      81      81      736     1309    2945    5225  


********************************

I also threw these points into a nice graph so it was easy to see how much faster the Naive time grew.  look at chart_1.png

********************************
Now comparing the time differences on running the same set of points vs. a random one every time.

closestPairDC: different inputs for each trial.  Again, time is in miliseconds (ms)

number of points:       128000      128000
number of trials:          100         500
average run time:          101          97
maximum run time:          993         949
minimum run time:           85          84

********************************
closestPairDC: same inputs for each trial.  Again, time is in miliseconds (ms)

number of points:       128000      128000
number of trials:          100         500
average run time:           87          88
maximum run time:          909         875
minimum run time:           79          78

While it can be seen that running the same input does slightly better than always running a random set of generated points, they both are still extremely close.
Running the same algorithm seems to be about 10 ms faster for both the 100 trial and the 500 trial.

*********************************

PART 3: the crossover point
at about what number of points is the closestPairDC always going to pay off?
The naive algorithm can win in the short run because it wouldn't have all the recursive calls to divide and sort the points into halves.

From the data from earlier, it looks like it happens between before n= 1000 and definitely before n=2000 points. But let's make it a little more accurate since that was only off of a 100 test average.

My approach:  Start at n=2000 (since it was clear there that the DC had taken dominance over the Naive) and run through 1000 trials using random generated points.  Get the average
        time from each trial and take note of the difference.  We want to find when the times are the same, so that would be when the averages hold a difference of roughly 0.
        So what I set up was a do->while loop around this set, and based on how big of a difference was, I would decrement n by a certain amount.  So if there was a huge difference (>15),
        then I would decrement n by 100.  As the difference got smaller, I would decrement smaller and smaller amounts until I could get my difference to be as small as possible.
        I also had it add values back to n if it overshot it (i.e. the difference became negative).
        I had my while loop exiting only if the difference was <.01, but it actually got into an infinite loop between 650 and 651, suggesting that the real value was in between and my
        quickly devised solution had too coarse steps to break it down any further.
        
        So, the naive and DC algorithms have a crossover point at (roughly) n = 650 points
        
        
My earlier attempts:  I started out with n being small (n=100) and iterate upwards, but found this to lead to very mixed results. The problem with this is that the runtimes are so small
    that there it was tough for my method to lead to accurate results.  Moving to n=2000 and counting down was much better because the initial differences were large and always decreased,
    whereas that wasn't always the case with the other one.




**************************************
Conclusion:  This was a cool lab.  It was cool being able to see how much faster the DC version actually ends up being.  It was also fun to try and optimize my DC algorithm so its average
    runtime wasn't as high.
