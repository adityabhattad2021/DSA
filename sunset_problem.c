/*
    Initialize an empty list called buildings_viewing_sunset.
    Initialize a variable called max_height to 0.
    This variable will keep track of the maximum height of the buildings that have been processed so far.
    Iterate through the buildings in east-to-west order. For each building, do the following:
        If the building's height is greater than or equal to max_height, add the building to the buildings_viewing_sunset list and update max_height to the building's height.
        If the building's height is less than max_height, do nothing.
    Return the buildings_viewing_sunset list.
*/

// Code doing the same.
#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    int height;
} Building;

void buildings_viewing_sunset(Building *buildings, int num_buildings)
{
    int max_height = 0;
    for (int i = 0; i < num_buildings; i++)
    {
        Building building = buildings[i];
        if (building.height >= max_height)
        {
            max_height = building.height;
            printf("Building %d: height %d\n", i, building.height);
        }
    }
}

int main()
{
    Building buildings[] = {5, 9, 12, 8, 6, 32, 54, 21, 18};
    int num_buildings = sizeof(buildings) / sizeof(buildings[0]);
    buildings_viewing_sunset(buildings, num_buildings);
    return 0;
}
