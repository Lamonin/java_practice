package Trails;

public class Trail {
    private int[] markers;

    public Trail(int[] markers) {
        this.markers = markers;
    }

    public int getMarkersLength()
    {
        return markers.length;
    }

    public boolean isLevelTrailSegment(int start, int end)
    {
        if (start >= end || start < 0 || start > markers.length - 1 || end > markers.length - 1)
        {
            return false;
        }

        int max = markers[start];
        int min = max;

        for (int i = start; i <= end; i++)
        {

            if (markers[i] > max)
            {
                max = markers[i];
            }
            else if (markers[i] < min)
            {
                min = markers[i];
            }
        }

        return max - min > 10;
    }

    public boolean isDifficult()
    {
        int diffCount = 0;

        for (int i = 0; i < markers.length - 1; i++)
        {
            if (Math.abs(markers[i+1] - markers[i]) >= 30)
            {
                diffCount += 1;
            }
        }

        return diffCount > 2;
    }
}
