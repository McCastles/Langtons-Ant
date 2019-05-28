#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "headers/ant.h"
#include "headers/board.h"
#include "headers/data_reciever.h"

/* Creating new ant */
ant_t createAnt(int width, int height, char* src, char* alive, char* start_x, char* start_y, char* start_pos)
{
	int tmp_x, tmp_y, tmp_alive, tmp_pos;

	if ( (tmp_alive = readValueFromFile(src, alive)) != 1)
		return NULL;	// Return NULL if not alive 
	if ( (tmp_x = check_x(width, src, start_x)) == -1)
        return NULL;	// Return NULL if out of border x
    if ( (tmp_y = check_y(height, src, start_y)) == -1)
        return NULL;	// Return NULL if out of border y
    if ( (tmp_pos = check_pos(src, start_pos)) == -1)
        return NULL;	// START_POS must contain values 0, 1, 2, 3

	/* Creating new ant  */
	ant_t proto = malloc(sizeof(proto));
	proto->x_coord = tmp_x;
	proto->y_coord = tmp_y;
	proto->ant_pos = tmp_pos;
	proto->isAlive = tmp_alive;

	return proto;
}  

/* Moving ant according to the rules */
void antStep (ant_t proto, board_t b)
{
	/* If stands on white: move right and paint black  */
	if (b->board[proto->x_coord][proto->y_coord] == 0)
	{
		turnRight(proto);
		b->board[proto->x_coord][proto->y_coord] = 1;
	}
	
	else /* Vice versa */
	{
		turnLeft(proto);
		b->board[proto->x_coord][proto->y_coord] = 0;
	}

	move(proto, b); /* Change the location of the ant */

}

/* Change the location according to the rules */
void move(ant_t proto, board_t b)
{
	switch (proto->ant_pos)
    {
    	case UP: 
		{	
			if (proto->y_coord == 0)
				proto->isAlive = 0;
			else
				proto->y_coord--; 
			break;
		}

    	case RIGHT:
		{
			if (proto->x_coord == (b->w-1))
				proto->isAlive = 0;
			else
				proto->x_coord++;
			break;	
		} 
    
		case DOWN:
		{   
			if (proto->y_coord == (b->h)-1)
                proto->isAlive = 0;
            else
                proto->y_coord++;
            break;
        }
    	case LEFT: 
		{
            if (proto->x_coord == 0)
                proto->isAlive = 0;
            else
                proto->x_coord--;
            break;
        }
    }
}

void turnRight(ant_t proto)
{
	switch (proto->ant_pos)
	{
	case UP: proto->ant_pos = RIGHT; break;
	case RIGHT: proto->ant_pos = DOWN; break;
	case DOWN: proto->ant_pos = LEFT; break;
	case LEFT: proto->ant_pos = UP; break;
	}
}


void turnLeft(ant_t proto)
{
    switch (proto->ant_pos)
    {
    case UP: proto->ant_pos = LEFT; break;
    case RIGHT: proto->ant_pos = UP; break;
    case DOWN: proto->ant_pos = RIGHT; break;
    case LEFT: proto->ant_pos = DOWN; break;
    }
}













